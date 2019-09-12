package com.baibeiyun.bbyiot.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import com.baibeiyun.bbyiot.application.BaseApplication;

import java.io.File;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.List;

/**
 * 我的设备
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class WDevice {
    // 手机网络类型
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean _hasBigScreen = null;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    private static Integer _loadFactor = null;

    private static int _pageSize = -1;
    public static float displayDensity = 0.0F;

    static {
		GTE_ICS = Build.VERSION.SDK_INT >= 14;
		GTE_HC = Build.VERSION.SDK_INT >= 11;
		PRE_HC = Build.VERSION.SDK_INT >= 11 ? false : true;
    }


	/**
	 * 拍照权限，返回true 表示可以使用  返回false表示不可以使用
	 * @return
	 */
	public static boolean cameraIsCanUse() {
		boolean isCanUse = true;
		Camera mCamera = null;
		try {
			mCamera = Camera.open();
			Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
			mCamera.setParameters(mParameters);
		} catch (Exception e) {
			isCanUse = false;
		}

		if (mCamera != null) {
			try {
				mCamera.release();
			} catch (Exception e) {
				e.printStackTrace();
				return isCanUse;
			}
		}
		return isCanUse;
	}

    public static float dpToPixel(Context ctx, float dp) {
		return dp * (getDisplayMetrics(ctx).densityDpi / 160F);
    }


	/**
	 * 安卓获取手机mac地址
	 *
	 * @return
	 */
	public static String getMacAddress() {

		String macAddress =null;
		@SuppressLint("WifiManagerLeak") WifiManager wifiManager =
				(WifiManager) BaseApplication.getContext().getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = (null== wifiManager ?null: wifiManager.getConnectionInfo());

		if(!wifiManager.isWifiEnabled())
		{
			//必须先打开，才能获取到MAC地址
			wifiManager.setWifiEnabled(true);
			wifiManager.setWifiEnabled(false);
		}
		if(null!= info) {
			macAddress = info.getMacAddress();
		}
		return macAddress;
	}



    public static int getDefaultLoadFactor(Context ctx) {
	if (_loadFactor == null) {
	    Integer integer = Integer.valueOf(0xf & ctx.getResources().getConfiguration().screenLayout);
	    _loadFactor = integer;
	    _loadFactor = Integer.valueOf(Math.max(integer.intValue(), 1));
	}
	return _loadFactor.intValue();
    }

    public static float getDensity(Context ctx) {
	if (displayDensity == 0.0)
	    displayDensity = getDisplayMetrics(ctx).density;
	return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics(Context ctx) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		((WindowManager) ctx.getSystemService(
			Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
			displaymetrics);
		return displaymetrics;
    }

    public static float getScreenHeight(Context ctx) {
	return getDisplayMetrics(ctx).heightPixels;
    }

    public static float getScreenWidth(Context ctx) {
	return getDisplayMetrics(ctx).widthPixels;
    }

    public static int[] getRealScreenSize(Activity activity) {
		int[] size = new int[2];
		int screenWidth = 0, screenHeight = 0;
		WindowManager w = activity.getWindowManager();
		Display d = w.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		d.getMetrics(metrics);
		// since SDK_INT = 1;
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
		    try {
			screenWidth = (Integer) Display.class.getMethod("getRawWidth")
				.invoke(d);
			screenHeight = (Integer) Display.class
				.getMethod("getRawHeight").invoke(d);
		    } catch (Exception ignored) {
		    }
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 17)
		    try {
			Point realSize = new Point();
			Display.class.getMethod("getRealSize", Point.class).invoke(d,
				realSize);
			screenWidth = realSize.x;
			screenHeight = realSize.y;
		    } catch (Exception ignored) {
		    }
		size[0] = screenWidth;
		size[1] = screenHeight;
		return size;
    }

    public static int getStatusBarHeight(Context ctx) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		try {
		    c = Class.forName("com.android.internal.R$dimen");
		    obj = c.newInstance();
		    field = c.getField("status_bar_height");
		    x = Integer.parseInt(field.get(obj).toString());
		    return ctx.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return 0;
    }



    public static boolean hasBigScreen(Context ctx) {
		boolean flag = true;
		if (_hasBigScreen == null) {
		    boolean flag1;
		    if ((0xf & ctx.getResources().getConfiguration().screenLayout) >= 3)
			flag1 = flag;
		    else
			flag1 = false;
		    Boolean boolean1 = Boolean.valueOf(flag1);
		    _hasBigScreen = boolean1;
		    if (!boolean1.booleanValue()) {
			if (getDensity(ctx) <= 1.5F)
			    flag = false;
			_hasBigScreen = Boolean.valueOf(flag);
		    }
		}
		return _hasBigScreen.booleanValue();
    }

    public static final boolean hasCamera(Context ctx) {
		if (_hasCamera == null) {
		    PackageManager pckMgr = ctx.getPackageManager();
		    boolean flag = pckMgr
			    .hasSystemFeature("android.hardware.camera.front");
		    boolean flag1 = pckMgr.hasSystemFeature("android.hardware.camera");
		    boolean flag2;
		    if (flag || flag1)
			flag2 = true;
		    else
			flag2 = false;
		    _hasCamera = Boolean.valueOf(flag2);
		}
		return _hasCamera.booleanValue();
    }

    public static boolean hasHardwareMenuKey(Context context) {
		boolean flag = false;
		if (PRE_HC)
		    flag = true;
		else if (GTE_ICS) {
		    flag = ViewConfiguration.get(context).hasPermanentMenuKey();
		} else
		    flag = false;
		return flag;
    }

    public static boolean hasInternet(Context ctx) {
		boolean flag;
		if (((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null)
		    flag = true;
		else
		    flag = false;
		return flag;
    }

    public static boolean gotoGoogleMarket(Activity activity, String pck) {
		try {
		    Intent intent = new Intent();
		    intent.setPackage("com.android.vending");
		    intent.setAction(Intent.ACTION_VIEW);
		    intent.setData(Uri.parse("market://details?id=" + pck));
		    activity.startActivity(intent);
		    return true;
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
    }

    public static boolean isPackageExist(Context ctx, String pckName) {
		try {
		    PackageInfo pckInfo = ctx.getPackageManager()
			    .getPackageInfo(pckName, 0);
		    if (pckInfo != null)
			return true;
		} catch (NameNotFoundException e) {
		    LogUtils.w(e.getMessage());
		}
		return false;
    }

    public static void hideAnimatedView(View view) {
		if (PRE_HC && view != null)
		    view.setPadding(view.getWidth(), 0, 0, 0);
    }

    public static void hideSoftKeyboard(Context ctx, View view) {
		if (view == null)
		    return;
		((InputMethodManager) ctx.getSystemService(
			Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
			view.getWindowToken(), 0);
    }

    public static boolean isLandscape(Context ctx) {
		boolean flag;
		if (ctx.getResources().getConfiguration().orientation == 2)
		    flag = true;
		else
		    flag = false;
		return flag;
    }

    public static boolean isPortrait(Context ctx) {
		boolean flag = true;
		if (ctx.getResources().getConfiguration().orientation != 1)
		    flag = false;
		return flag;
    }

    public static boolean isTablet(Context ctx) {
		if (_isTablet == null) {
		    boolean flag;
		    if ((0xf & ctx.getResources()
			    .getConfiguration().screenLayout) >= 3)
			flag = true;
		    else
			flag = false;
		    _isTablet = Boolean.valueOf(flag);
		}
		return _isTablet.booleanValue();
    }

    public static float pixelsToDp(Context ctx, float f) {
    	return f / (getDisplayMetrics(ctx).densityDpi / 160F);
    }

    public static void showAnimatedView(View view) {
		if (PRE_HC && view != null)
		    view.setPadding(0, 0, 0, 0);
    }

    public static void showSoftKeyboard(Dialog dialog) {
    	dialog.getWindow().setSoftInputMode(4);
    }

    public static void showSoftKeyboard(Context ctx, View view) {
		((InputMethodManager) ctx.getSystemService(
			Context.INPUT_METHOD_SERVICE)).showSoftInput(view,
			InputMethodManager.SHOW_FORCED);
    }

    public static void toogleSoftKeyboard(Context ctx, View view) {
		((InputMethodManager) ctx.getSystemService(
			Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
			InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isSdcardReady() {
		return Environment.MEDIA_MOUNTED.equals(Environment
			.getExternalStorageState());
    }

    public static String getCurCountryLan(Context ctx) {
		return ctx.getResources().getConfiguration().locale
			.getLanguage()
			+ "-"
			+ ctx.getResources().getConfiguration().locale
				.getCountry();
    }

    public static boolean isZhCN(Context ctx) {
		String lang = ctx.getResources()
			.getConfiguration().locale.getCountry();
		if (lang.equalsIgnoreCase("CN")) {
		    return true;
		}
		return false;
    }

    public static String percent(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		str = nf.format(p3);
		return str;
    }

    public static String percent2(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(0);
		str = nf.format(p3);
		return str;
    }

    public static void gotoMarket(Context context, String pck) {
		if (!isHaveMarket(context)) {
	//	    AppContext.showToast("你手机中没有安装应用市场！");
		    return;
		}
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + pck));
		if (intent.resolveActivity(context.getPackageManager()) != null) {
		    context.startActivity(intent);
		}
    }

    public static boolean isHaveMarket(Context context) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_MARKET");
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
		return infos.size() > 0;
    }

    public static void openAppInMarket(Context context) {
		if (context != null) {
		    String pckName = context.getPackageName();
		    try {
			gotoMarket(context, pckName);
		    } catch (Exception ex) {
			try {
			    String otherMarketUri = "http://market.android.com/details?id="
				    + pckName;
			    Intent intent = new Intent(Intent.ACTION_VIEW,
				    Uri.parse(otherMarketUri));
			    context.startActivity(intent);
			} catch (Exception e) {
	
			}
		    }
		}
    }

    public static void setFullScreen(Activity activity) {
		WindowManager.LayoutParams params = activity.getWindow()
			.getAttributes();
		params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		activity.getWindow().setAttributes(params);
		activity.getWindow().addFlags(
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void cancelFullScreen(Activity activity) {
		WindowManager.LayoutParams params = activity.getWindow()
			.getAttributes();
		params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
		activity.getWindow().setAttributes(params);
		activity.getWindow().clearFlags(
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static PackageInfo getPackageInfo(Context ctx, String pckName) {
		try {
		    return ctx.getPackageManager()
			    .getPackageInfo(pckName, 0);
		} catch (NameNotFoundException e) {
		    LogUtils.w(e.getMessage());
		}
		return null;
    }

    public static int getVersionCode(Context ctx) {
		int versionCode = 0;
		try {
		    versionCode = ctx.getPackageManager()
			    .getPackageInfo(ctx.getPackageName(),
				    0).versionCode;
		} catch (NameNotFoundException ex) {
		    versionCode = 0;
		}
		return versionCode;
    }

    public static int getVersionCode(Context ctx, String packageName) {
		int versionCode = 0;
		try {
		    versionCode = ctx.getPackageManager()
			    .getPackageInfo(packageName, 0).versionCode;
		} catch (NameNotFoundException ex) {
		    versionCode = 0;
		}
		return versionCode;
    }

    public static String getVersionName(Context ctx) {
		String name = "";
		try {
		    name = ctx.getPackageManager()
			    .getPackageInfo(ctx.getPackageName(),
				    0).versionName;
		} catch (NameNotFoundException ex) {
		    name = "";
		}
		return name;
    }

    public static  boolean isScreenOn(Context ctx) {
		PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
		return pm.isScreenOn();
    }

    public static void installAPK(Context context, File file) {
		if (file == null || !file.exists())
		    return;
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
			"application/vnd.android.package-archive");
		context.startActivity(intent);
    }

    public static Intent getInstallApkIntent(File file) {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
			"application/vnd.android.package-archive");
		return intent;
    }

    public static void openDial(Context context, String number) {
		Uri uri = Uri.parse("tel:" + number);
		Intent it = new Intent(Intent.ACTION_DIAL, uri);
		context.startActivity(it);
    }

    public static void openSMS(Context context, String smsBody, String tel) {
		Uri uri = Uri.parse("smsto:" + tel);
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", smsBody);
		context.startActivity(it);
    }

    public static void openDail(Context context) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
    }

    public static void openSendMsg(Context context) {
		Uri uri = Uri.parse("smsto:");
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
    }

    public static void openCamera(Context context) {
		Intent intent = new Intent(); // 调用照相机
		intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
		intent.setFlags(0x34c40000);
		context.startActivity(intent);
    }

	/**
	 *获取手机IMEI号
	 * @param ctx
	 * @return
	 */
	public static String getIMEI(Context ctx) {
		try {
			TelephonyManager tel = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
			return tel.getDeviceId();
		}catch (Exception e){
			return "111";
		}
    }

    public static String getPhoneType() {
    	return Build.MODEL;
    }

    public static void openApp(Context context, String packageName) {
		Intent mainIntent = context.getPackageManager()
			.getLaunchIntentForPackage(packageName);
		if (mainIntent == null) {
		    mainIntent = new Intent(packageName);
		} else {
		    LogUtils.d("Action:" + mainIntent.getAction());
		}
		context.startActivity(mainIntent);
    }

    public static boolean openAppActivity(Context context, String packageName,
                                          String activityName) {
	Intent intent = new Intent(Intent.ACTION_MAIN);
	intent.addCategory(Intent.CATEGORY_LAUNCHER);
	ComponentName cn = new ComponentName(packageName, activityName);
	intent.setComponent(cn);
	try {
	    context.startActivity(intent);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean isWifiOpen(Context ctx) {
		boolean isWifiConnect = false;
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		// check the networkInfos numbers
		NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
		for (int i = 0; i < networkInfos.length; i++) {
		    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
			if (networkInfos[i].getType() == ConnectivityManager.TYPE_MOBILE) {
			    isWifiConnect = false;
			}
			if (networkInfos[i].getType() == ConnectivityManager.TYPE_WIFI) {
			    isWifiConnect = true;
			}
		    }
		}
		return isWifiConnect;
    }

    public static void uninstallApk(Context context, String packageName) {
	if (isPackageExist(context,packageName)) {
	    Uri packageURI = Uri.parse("package:" + packageName);
	    Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
		    packageURI);
	    context.startActivity(uninstallIntent);
	}
    }

    @SuppressWarnings("deprecation")
    public static void copyTextToBoard(Context ctx, String string) {
	if (TextUtils.isEmpty(string))
	    return;
	ClipboardManager clip = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
	clip.setText(string);
//	AppContext.showToast(R.string.copy_success);
    }

    /**
     * 发送邮件
     * 
     * @param context
     * @param subject
     *            主题
     * @param content
     *            内容
     * @param emails
     *            邮件地址
     */
    public static void sendEmail(Context context, String subject,
                                 String content, String... emails) {
	try {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    // 模拟器
	    // intent.setType("text/plain");
	    intent.setType("message/rfc822"); // 真机
	    intent.putExtra(Intent.EXTRA_EMAIL, emails);
	    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
	    intent.putExtra(Intent.EXTRA_TEXT, content);
	    context.startActivity(intent);
	} catch (ActivityNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public static int getStatuBarHeight(Context ctx) {
	Class<?> c = null;
	Object obj = null;
	Field field = null;
	int x = 0, sbar = 38;// 默认为38，貌似大部分是这样的
	try {
	    c = Class.forName("com.android.internal.R$dimen");
	    obj = c.newInstance();
	    field = c.getField("status_bar_height");
	    x = Integer.parseInt(field.get(obj).toString());
	    sbar = ctx.getResources()
		    .getDimensionPixelSize(x);

	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	return sbar;
    }

    public static int getActionBarHeight(Context context) {
	int actionBarHeight = 0;
	TypedValue tv = new TypedValue();
	if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
		tv, true))
	    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
		    context.getResources().getDisplayMetrics());

	if (actionBarHeight == 0
		&& context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
			tv, true)) {
	    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
		    context.getResources().getDisplayMetrics());
	}

	return actionBarHeight;
    }

    public static boolean hasStatusBar(Activity activity) {
		WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
		if ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
		    return false;
		} else {
		    return true;
		}
    }

    /**
     * 调用系统安装了的应用分享
     * 
     * @param context
     * @param title
     * @param url
     */
    public static void showSystemShareOption(Activity context,
                                             final String title, final String url) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享：" + title);
		intent.putExtra(Intent.EXTRA_TEXT, title + " " + url);
		context.startActivity(Intent.createChooser(intent, "选择分享"));
    }

    /**
     * 获取当前网络类型
     * 
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType(Context ctx) {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
		    return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
		    String extraInfo = networkInfo.getExtraInfo();
		    if (!StringUtils.isEmpty(extraInfo)) {
			if (extraInfo.toLowerCase().equals("cmnet")) {
			    netType = NETTYPE_CMNET;
			} else {
			    netType = NETTYPE_CMWAP;
			}
		    }
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
		    netType = NETTYPE_WIFI;
		}
		return netType;
    }
}
