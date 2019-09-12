package com.baibeiyun.bbyiot.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baibeiyun.bbyiot.common.IConstant;
import com.baibeiyun.bbyiot.model.event.PayFinishEvent;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import cn.leo.messenger.MagicMessenger;


public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册API 没有这个不会执行onResp
        IWXAPI api = WXAPIFactory.createWXAPI(this, "wxe9601fbdbc0b7989");
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            EventBus.getDefault().post(new PayFinishEvent(baseResp.errCode));
            Bundle bundle = new Bundle();
            bundle.putInt("code", baseResp.errCode);
            MagicMessenger.post(IConstant.PAYRESULT, bundle);
            finish();
        }
    }
}
