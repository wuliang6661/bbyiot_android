package com.baibeiyun.bbyiot.module.mine.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.model.Response.FeedbackTypeResponse;
import com.baibeiyun.bbyiot.model.request.AddFeedbackRequest;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.module.mine.contract.FeedbackContract;
import com.baibeiyun.bbyiot.module.mine.presenter.FeedbackPresenter;
import com.baibeiyun.bbyiot.module.mine.view.ImageUploadMoreView;
import com.baibeiyun.bbyiot.module.mine.view.SelectGroupDialog;
import com.baibeiyun.bbyiot.utils.ListUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.photo.PhotoGraphView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

//意见反馈
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackContract.View {

    @BindView(R.id.act_feedback_ll_pic)
    LinearLayout ll_pic;
    @BindView(R.id.tv_fankui)
    TextView tv_fankui;
    @BindView(R.id.et_miaoshu)
    EditText et_miaoshu;

    @BindView(R.id.et_lianxiren)
    EditText et_lianxiren;

    private ImageUploadMoreView uploadView;
    private ArrayList<String> mSelectPath;
    private List<FeedbackTypeResponse> mFeedbackTypeList;
    private SelectGroupDialog selectFeedbackTypeDialog;
    private int mTypeId = -1;

    List<String> mImgUrlList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_feedback;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {
        setActionBarTitle("意见反馈");

        uploadView = new ImageUploadMoreView(this);
        uploadView.setData((Bitmap) null);
        ll_pic.addView(uploadView);

    }

    @OnClick({R.id.ll_fankui, R.id.tv_submit})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_fankui:
                if (mFeedbackTypeList == null) {
                    mPresenter.getUserFeedbackType();
                } else {
                    showDialog();
                }
                break;
            case R.id.tv_submit:
                if (mTypeId == -1) {
                    ToastUtils.showToast("请选择反馈类型");
                    return;
                }
                List<String> pathFiles = uploadView.getPathFiles();
                if (!ListUtils.isEmpty(pathFiles)) {
                    mPresenter.fileUpload(pathFiles);
                    return;
                }

                submit();
                break;
        }
    }

    void submit() {
        AddFeedbackRequest request = new AddFeedbackRequest();
        request.setTypeId(mTypeId);
        request.setContact(et_lianxiren.getText().toString());
        request.setContent(et_miaoshu.getText().toString());

        StringBuilder url = new StringBuilder();
        for (int i = 0; i < mImgUrlList.size(); i++) {
            if (i == mImgUrlList.size() - 1) {
                url.append(mImgUrlList.get(i));
            } else {
                url.append(mImgUrlList.get(i)).append(",");
            }
        }
        request.setImage(url.toString());
        mPresenter.addUserFeedback(request);
    }

    @Override
    public void fileUploadFinish() {
        submit();
    }

    @Override
    public void addUserFeedbackFinish() {
        finish();
    }

    /**
     * 相册 、相机回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            LogUtils.w("activity回调  " + requestCode + "");
            switch (requestCode) {

                case PhotoGraphView.SELECT_PIC_BY_PICK_PHOTO:// 1 相册
                    //EventBus.getDefault().post(new PhotoEvent(1, requestCode, data));
                    if (resultCode == RESULT_OK) {
                        mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        for (String path : mSelectPath) {
                            //Bitmap bitmap = BitmapUtils.pathToBitmap(path);
                            uploadView.setData(path);
                        }
                    }
                    break;

                case PhotoGraphView.SELECT_PIC_BY_TACK_PHOTO:// 2 拍照
                    LogUtils.w("拍照");
                    if (resultCode == RESULT_OK) {
                        //String absolutePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/camera.jpg";
                        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp/" + BaseApplication.currentTime + ".jpg";
                        LogUtils.w("absolutePath == " + absolutePath);

                        uploadView.setData(absolutePath);
//                        if (!StringUtils.isEmpty(absolutePath)) {
//                            Bitmap bitmap = BitmapUtils.pathToBitmap(absolutePath);
//                            uploadView.setData(bitmap);
//                        }
                    }
                    break;
            }

        } catch (Exception e) {
            LogUtils.w(e.getMessage());
        }
    }

    /**
     * ----------------权限回调-----------------------
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        LogUtils.w("权限 requestCode== " + requestCode);
        switch (requestCode) {
            case PhotoGraphView.PERMISSIONS_1://1//拍照-相机权限
                if (grantResults[0] != -1) {
                    LogUtils.w("--------------------");
                    uploadView.takePhoto();
                }
                break;
            case PhotoGraphView.PERMISSIONS_2://2//相册 --手机存储权限
                if (grantResults[0] != -1) {
                    uploadView.pickPhoto();
                }
                break;
            case PhotoGraphView.PERMISSIONS_3://3
                if (grantResults[0] != -1) {
                    //uploadView.clipPicture();
                }
                break;

        }
    }


    @Override
    protected FeedbackPresenter getPresenter() {
        return new FeedbackPresenter(this);
    }

    @Override
    public void getUserFeedbackTypeFinish(List<FeedbackTypeResponse> list) {
        mFeedbackTypeList = list;
        showDialog();
    }


    void showDialog() {
        if (selectFeedbackTypeDialog == null) {
            selectFeedbackTypeDialog = new SelectGroupDialog(this);
        }
        final List<String> selectGroupBeanList = new ArrayList<>();
        if (mFeedbackTypeList != null) {
            for (FeedbackTypeResponse response : mFeedbackTypeList) {
                selectGroupBeanList.add(response.getName());
            }
        }
        selectFeedbackTypeDialog.show();
        selectFeedbackTypeDialog.setData(selectGroupBeanList);
        selectFeedbackTypeDialog.setOnClickLinsenter(new SelectGroupDialog.OnClickLinsenter() {
            @Override
            public void cofirm(int position) {
                try {
                    if (mFeedbackTypeList != null) {
                        tv_fankui.setText(mFeedbackTypeList.get(position).getName());
                        mTypeId = mFeedbackTypeList.get(position).getId();
                    }
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }

                selectFeedbackTypeDialog.dismiss();
            }
        });
    }
}
