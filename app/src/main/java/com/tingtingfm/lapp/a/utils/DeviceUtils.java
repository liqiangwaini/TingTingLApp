package com.tingtingfm.lapp.a.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.tingtingfm.lapp.a.TTApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceUtils {
    private static DisplayMetrics dm;
    private static TelephonyManager telephonyManager = null;
    private static ConnectivityManager connManager = null;

    public static int getScreenWidth() {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) TTApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) TTApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
        }

        return dm.heightPixels;
    }

    /**
     * 获取当前手机的密度
     *
     * @return
     */
    public static float getDensity() {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) TTApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
        }

        return dm.density;
    }
    
    /**
     * 获取手机分辨率 USystem.getScreenResolution()<BR>
     * 
     * @return
     */
    public static String getScreenResolution() {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) TTApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels + "X" + dm.heightPixels;
    }

    /**
     * 获取屏幕尺寸 USystem.getScreenSize()<BR>
     * 
     * @param context
     * @return
     */
    public static String getScreenSize(Context context) {
        if (dm == null) {
            dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        double dia = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        double screenInches = dia / (160 * dm.density);
        return String.format("%.1f", screenInches);
    }

    /**
     * 获取系统版本 DeviceUtils.getSDKVersion()<BR>
     * 
     * @return string
     */
    public static String getAndroidSDKVersion() {
        return android.os.Build.VERSION.SDK;
    }

    /**
     * 获取操作系统的版本号
     * 
     * @return String 系统版本号
     */
    public static String getSysRelease() {
        return android.os.Build.VERSION.RELEASE;
    }
    
    /**
     * 获取手机型号
     * 
     * @return String 手机型号
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机品牌
     * 
     * @return String 手机品牌
     */
    public static String getBrand() {
        return android.os.Build.BRAND;
    }
    
    /**
     *  是否为开发者 调试模式
     *  return boolean true为调试模式
     */
    public static boolean enableAdb(){
    	int adb_enabled = Settings.Secure.getInt(TTApplication.getAppContext().getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
		return adb_enabled > 0;
    }

    /**
     * 
     * 读取手机串号 IMEI
     *
     *            上下文
     * @return String 手机串号
     */
    public static String getTelephoneSerialNum() {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) TTApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        }
        
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return "";
    }
    
    public static String getDeviceId() {
        try {
        	String temp = getTelephoneSerialNum();
        	if (TextUtils.isEmpty(temp)) {
        		temp = getLocalMacAddress();
        	}
            return signUnionMD5(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String signUnionMD5(String content) throws Exception {
        String tosign = (content == null ? "" : content);

        return "android_" +  encryption(tosign);
    }
    
    public static String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
				i = b[offset] & 0xff;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
    
    /**
     * 获取手机MAC地址
     *
     * @return
     */
    public static String getLocalMacAddress() {
        WifiManager wifi = (WifiManager) TTApplication.getAppContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 判断网络是否连接
     * 
     * @param context
     * @return
     */
    public static boolean isNetWork(Context context) {
        if (connManager == null) {
            connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable()) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络类型 USystem.getNetType()<BR>
     * 
     * @return
     */
    public static String getNetType() {
        ConnectivityManager connManager = (ConnectivityManager) TTApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo gprs = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.getState() == State.CONNECTED) {
            return "WIFI";
        } else if (gprs != null && gprs.getState() == State.CONNECTED) {
            return "3G/GPRS";
        }
        return "no-net";
    }
}
