package com.baibeiyun.bbyiot.module.tools;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.event.PictureEvent;
import com.baibeiyun.bbyiot.module.base.presenter.IPresenter;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.tools.view.ClipView;
import com.baibeiyun.bbyiot.module.tools.view.TouchImageView;
import com.baibeiyun.bbyiot.utils.AppMemoryUtils;
import com.baibeiyun.bbyiot.utils.BitmapUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.SpUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 * 编辑图片  图片剪裁
 */
public class ClipPictureActivity extends BaseActivity implements  OnTouchListener {

    // 头部

    @BindView(R.id.src_pic)
    TouchImageView srcPic;

    private ClipView clipview;

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    /**
     * 动作标志：无
     */
    private static final int NONE = 0;
    /**
     * 动作标志：拖动
     */
    private static final int DRAG = 1;
    /**
     * 动作标志：缩放
     */
    private static final int ZOOM = 2;
    /**
     * 初始化动作标志
     */
    private int mode = NONE;
    /**
     * 记录起始坐标
     */
    private PointF start = new PointF();
    /**
     * 记录缩放时两指中间点坐标
     */
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private Bitmap bitmap;
    private String imageUrl = "";
    private ProgressDialog createProgressDialog;





    @Override
    protected int getContentViewLayoutID() {
         return R.layout.activity_clip_picture;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        init();

        ViewTreeObserver observer = srcPic.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            public void onGlobalLayout() {
                srcPic.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                initClipView(srcPic.getTop());
            }
        });

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void init() {

        imageUrl = getIntent().getStringExtra("picPath");
        LogUtils.w("imageUrl==" + imageUrl);


        setActionBarTitle("图片裁剪");

        mToolbar.inflateMenu(R.menu.addtion);
        mToolbar.getMenu().getItem(0).setTitle("保存");

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mToolbar.getMenu().getItem(0) == item) {
                    save();
                    return true;
                }
                return false;
            }
        });

        srcPic.setOnTouchListener(this);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (createProgressDialog != null && createProgressDialog.isShowing()) {
            createProgressDialog.dismiss();
        }

    }


    /**
     * 保存裁剪的图片
     */
    private void save() {
        new Thread() {
            public void run() {
                try {
                    Bitmap clipBitmap = getBitmap();
                    EventBus.getDefault().post(new PictureEvent(clipBitmap));
                    LogUtils.w("------fs   裁剪的图片");
                    finish();
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                    e.printStackTrace();
                }
            }
        }.start();


    }

    /**
     * 初始化截图区域，并将源图按裁剪框比例缩放
     *
     * @param top
     */
    private void initClipView(int top) {
        try {
            int bitmapSize = SpUtils.getInt("key_bitmap_size",0);
            LogUtils.w("内存剩余==" + ((AppMemoryUtils.getAppAvailMemory() - 10) * 1024 * 1024) + " ， bitmapSize==" + bitmapSize);
            if ((AppMemoryUtils.getAppAvailMemory() - 10) * 1024 * 1024 < bitmapSize) {//内存不足
                ToastUtils.showToast("内存不足,请清理后重试");
                return;
            }

            LogUtils.w("开始获取图片路径");
            bitmap = convertTo(imageUrl);
            LogUtils.w("初始图片大小 size=" + BitmapUtils.getBitmapSize2M(bitmap)+"M");

            clipview = new ClipView(ClipPictureActivity.this);
            clipview.setCustomTopBarHeight(top);
            clipview.addOnDrawCompleteListener(new ClipView.OnDrawListenerComplete() {
                public void onDrawCompelete() {
                    try {
                        clipview.removeOnDrawCompleteListener();
                        int clipHeight = clipview.getClipHeight();
                        int clipWidth = clipview.getClipWidth();
                        int midX = clipview.getClipLeftMargin() + (clipWidth / 2);
                        int midY = clipview.getClipTopMargin() + (clipHeight / 2);

                        int imageWidth = bitmap.getWidth();
                        int imageHeight = bitmap.getHeight();
                        // 按裁剪框求缩放比例
                        float scale = (clipWidth * 1.0f) / imageWidth;
                        if (imageWidth > imageHeight) {
                            scale = (clipHeight * 1.0f) / imageHeight;
                        }

                        // 起始中心点
                        float imageMidX = imageWidth * scale / 2;
                        float imageMidY = clipview.getCustomTopBarHeight()
                                + imageHeight * scale / 2;
                        srcPic.setScaleType(ScaleType.MATRIX);

                        // 缩放
                        matrix.postScale(scale, scale);
                        // 平移
                        matrix.postTranslate(midX - imageMidX, midY - imageMidY);

                        srcPic.setImageMatrix(matrix);
                        srcPic.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        LogUtils.w(e.toString());
                    }
                }
            });

            this.addContentView(clipview, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    /**
     * 获取裁剪框内截图
     *
     * @return
     */
    private Bitmap getBitmap() {
//
        try {
            // 获取截屏
            View view = this.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            // 获取状态栏高度
            Rect frame = new Rect();
            this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;


            int bitmapSize = SpUtils.getInt("key_bitmap_size",0);

            LogUtils.w("内存剩余==" + ((AppMemoryUtils.getAppAvailMemory() - 10) * 1024 * 1024) + " ， bitmapSize==" + bitmapSize);
            if ((AppMemoryUtils.getAppAvailMemory() - 5) * 1024 * 1024 < bitmapSize) {//内存不足
                ToastUtils.showToast("内存不足,请清理后重试");
                return null;
            }


            Bitmap finalBitmap = Bitmap.createBitmap(view.getDrawingCache(),
                    clipview.getClipLeftMargin(), clipview.getClipTopMargin()
                            + statusBarHeight, clipview.getClipWidth() + 1,
                    clipview.getClipHeight());
            // 释放资源
            view.destroyDrawingCache();

            SpUtils.put("key_bitmap_size", (int) BitmapUtils.getBitmapSize(finalBitmap));
            LogUtils.w("裁剪后的图片大小 size==" + BitmapUtils.getBitmapSize2M(finalBitmap)+"M");

            return finalBitmap;
        } catch (Exception e) {
            LogUtils.w(e.toString());
            return null;
        }


    }

    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                // 设置开始点位置
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()
                            - start.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }
        view.setImageMatrix(matrix);
        return true;
    }

    /**
     * 多点触控时，计算最先放下的两指距离
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 多点触控时，计算最先放下的两指中心坐标
     *
     * @param point
     * @param event
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }


    /***
     * 根据路径获得图片并压缩，返回bitmap用于显示
     * @param path  图片路径
     * @return
     */
    private Bitmap convertTo(String path) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 4;// 图片宽高都为原来的二分之一，即图片为原来的四分之一
//        return BitmapFactory.decodeFile(path, options);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }


    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
//            if (width > height) {
//                inSampleSize = Math.round((float) height / (float) reqHeight);
//            } else {
//                inSampleSize = Math.round((float) width / (float) reqWidth);
//            }
            //计算图片高度和我们需要高度的最接近比例值
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            //宽度比例值
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            //取比例值中的较大值作为inSampleSize
            inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;
        }

        LogUtils.w("inSampleSize =" + inSampleSize);
        return inSampleSize;
    }

    /**
     * 收到消息之后关闭所有界面
     */




}
