package com.baibeiyun.bbyiot.model.request;

import com.baibeiyun.bbyiot.model.DataManager;

public class BaseRequest {
    protected String token = DataManager.getsInstance().getToken();
}
