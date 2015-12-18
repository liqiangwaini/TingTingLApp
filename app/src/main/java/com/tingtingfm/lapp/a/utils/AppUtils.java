package com.tingtingfm.lapp.a.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.tingtingfm.lapp.a.TTApplication;


public class AppUtils {
    /**
     * 获取软件的版本名称
     * 
     * @return
     */
    public static String getVersionName() {
    	Context context = TTApplication.getAppContext();
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取软件的版本号
     * 
     * @return
     */
    public static int getVersionCode(String packageName) {
    	Context context = TTApplication.getAppContext();
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String getApplicationName() {
        String applicationName = "";
        try {
            PackageManager pm = TTApplication.getAppContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(TTApplication.getAppContext().getPackageName(), 0);
            applicationName = pi.applicationInfo.loadLabel(pm).toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return applicationName;
    }
}
