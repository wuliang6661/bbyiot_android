package com.baibeiyun.bbyiot.common;

public interface IConstant {

    /**
     * ---------  主页接口
     */
    //String BASE_URL = "http://192.168.1.200:8096";//内网
    String BASE_URL = "http://112.124.200.87:8096";

    /**
     * 商城模块的ui
     */
    //String BASE_SHOP_URL = "http://192.168.1.200:8090/";//内网
    //String BASE_SHOP_URL = "http://192.168.1.200:8090/";//本地
//    String BASE_SHOP_URL = "http://112.124.200.87:8081/hardware_webservice/";
    String BASE_SHOP_URL = "http://47.98.53.141:8087/hardware_webservice/";


    String WEB_URL = "WEB_URL";
    String BASE_SHOP = "http://47.98.53.141:8087/iot_mobile/views/";
    String BASE_SHOP_LIST_URL = BASE_SHOP + "hardware/hardwareList.html?categoryId=";//列表
    String BASE_SHOP_DETAIL_URL = BASE_SHOP + "hardware/hardwareDetails.html?id=";//商品
    String BASE_SHOP_PINPAIGUAN_URL = BASE_SHOP + "hardware/hardwareList.html?categoryId=pinpaiguan";//品牌馆
    String BASE_SHOP_SOFTWARE_LIST_URL = BASE_SHOP + "software/softwareHome.html";//软件列表
    String BASE_SHOP_SOFTWARE_URL = BASE_SHOP + "software/softwareClassList.html?id=";//软件
    String BASE_SHOP_SEARCH_URL = BASE_SHOP + "hardware/sousuo.html";//搜索


    String PAYRESULT = "PAYRESULT";
    String APP_NAME = "iotapp";


    String SEARCH_HISTORY = "search_history";

    String KEY_LOGIN_NAME = "key_login_name";
    String KEY_LOGIN_PSW = "key_login_psw";
    String KEY_LOGIN_TOKEN = "key_login_token";
    String KEY_LOGIN_BEAN = "key_login_bean";


    String KEY_IS_LOGIN = "key_is_login";

    String ORDER_TYPE = "order_type";
    String ORDER_STATUS = "order_status";


    /**
     * 扫码用到的
     */
    int auto_focus = 1000011;
    int decode = 1000012;
    int decode_failed = 1000013;

    int decode_succeeded = 1000014;
    int restart_preview = 1000027;
    int launch_product_query = 1000017;
    int quit = 1000018;
    int return_scan_result = 1000019;


    /**
     * 萤石云的appkey
     */
    String appKey = "2b4dcf4a609341e690ad4bc017428415";

}
