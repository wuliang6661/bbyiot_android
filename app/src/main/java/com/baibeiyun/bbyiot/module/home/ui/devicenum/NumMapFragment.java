package com.baibeiyun.bbyiot.module.home.ui.devicenum;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.model.Response.LocationResponse;
import com.baibeiyun.bbyiot.module.base.ui.BaseFragment;
import com.baibeiyun.bbyiot.module.home.contract.devicenum.NumMapContract;
import com.baibeiyun.bbyiot.module.home.presenter.devicenum.NumMapPresenter;
import com.baibeiyun.bbyiot.utils.LogUtils;
import com.baibeiyun.bbyiot.utils.ToastUtils;

import butterknife.BindView;

public class NumMapFragment extends BaseFragment<NumMapPresenter> implements NumMapContract.View {

    @BindView(R.id.frg_num_map_mapview)
    MapView mMapView;

    AMap aMap;
    private LatLng mLatLng;

    LocationResponse locationResponse;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frg_num_map;
    }

    @Override
    protected void initInstanceState(Bundle savedInstanceState) {
        super.initInstanceState(savedInstanceState);
        LogUtils.w("NumMapFragment  initInstanceState");

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            LogUtils.w("NumMapFragment  未开启定位权限");
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }else {
            LogUtils.w("NumMapFragment  已经开启定位权限");
        }

        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
        }

    }


    @Override
    protected void initViewsAndEvents() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mPresenter.getDeviceLocation();
    }

    @Override
    protected NumMapPresenter getPresenter() {
        return new NumMapPresenter(getActivity());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 200:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 授予权限
                    //处理授权之后逻辑
                    moveLoction();
                } else {
                    // Permission Denied 权限被拒绝
                    ToastUtils.showToast("定位权限被禁用");
                }

                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void getDeviceLocationFinish(LocationResponse response) {
        try {
            this.locationResponse = response;
            moveLoction();

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    private void moveLoction() {
        try {
            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。

            myLocationStyle.showMyLocation(true);

            if (locationResponse == null) {
                return;
            }
            /**
             * 添加覆盖物
             */
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.
                    fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_location_red));

            //LatLng latLng = new LatLng(response.getDimension(), response.getLongitude());
            mLatLng = new LatLng(locationResponse.getLongitude(), locationResponse.getDimension());

            Marker marker = aMap.addMarker(new MarkerOptions().position(mLatLng).icon(bitmapDescriptor));

            //移动地图
            aMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }


}
