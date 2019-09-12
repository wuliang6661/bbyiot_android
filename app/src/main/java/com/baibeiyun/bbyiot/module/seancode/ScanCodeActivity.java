package com.baibeiyun.bbyiot.module.seancode;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.BaseResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseActivity;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;
import com.baibeiyun.bbyiot.view.dialog.CommonDialog;
import com.baibeiyun.bbyiot.zxing.camera.CameraManager;
import com.baibeiyun.bbyiot.zxing.decoding.CaptureActivityHandler;
import com.baibeiyun.bbyiot.zxing.decoding.InactivityTimer;
import com.baibeiyun.bbyiot.zxing.view.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.Vector;

import butterknife.BindView;
import butterknife.OnClick;

public class ScanCodeActivity extends BaseActivity<ScanCodePresenter> implements SeanCodeContract.View, Callback {

    @BindView(R.id.viewfinder_view)
    ViewfinderView viewfinderView;

    @BindView(R.id.tv_title)
    TextView tv_title;

    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private boolean playBeep;
    private CaptureActivityHandler handler;
    private MediaPlayer mediaPlayer;
    private boolean vibrate;
    private static final float BEEP_VOLUME = 0.10f;
    private CommonDialog commonDialog;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_scan_code;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents() {

        CameraManager.init(getApplication());

        tv_title.setText("扫码激活");


        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);

        setOnrealHight();
    }

    @Override
    protected ScanCodePresenter getPresenter() {
        return new ScanCodePresenter(this);
    }

    @OnClick(R.id.back)
    void click(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

        viewfinderView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }


    /**
     * 处理扫描结果
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        LogUtils.w("1111111111111  扫描结果 " + resultString);
        showScenCodeSucess(resultString);
    }

    void showScenCodeSucess(final String resultString) {
        if (commonDialog == null) {
            commonDialog = new CommonDialog(this);
        }
        commonDialog.show();

        //String title, String content, String cancel, String ok
        commonDialog.setTxet("激活设备", "是否激活" + resultString, "否", "是");
        commonDialog.setOnDialogLinsenter(new CommonDialog.OnDialogLinsenter() {
            @Override
            public void confirm() {
                mPresenter.activateDevice(resultString);
            }

            @Override
            public void cancel() {
                finish();
            }
        });
    }

    @Override
    public void activateDeviceFinish(BaseResponse response) {
        try {
            if (response != null) {
                ToastUtils.showToast(response.getMsg());
            }
            finish();
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }
    //----------------  end


    private static final long VIBRATE_DURATION = 200L;

    @SuppressLint("MissingPermission")
    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {

            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }

        handler = new CaptureActivityHandler(this, decodeFormats, characterSet);

    }


    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    /**
     * 确定对应的按键高度
     */
    private void setOnrealHight() {
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }


}
