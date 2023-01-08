package com.zy.devicelibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.zy.devicelibrary.broadcast.BatteryBroadcastReceiver;
import com.zy.devicelibrary.data.BatteryStatusData;
import com.zy.devicelibrary.utils.GeneralUtils;
import com.zy.devicelibrary.utils.PhoneUtils;

public class UtilsApp {


    @SuppressLint("StaticFieldLeak")
    private static Application sApp;
    /**
     * 全局设备Id
     */
    public static String deviceId = "";
    /**
     * 电池信息
     */
    public static BatteryStatusData batteryStatusData;
    /**
     * 通过广播获取电池信息
     */
    public static BatteryBroadcastReceiver batteryBroadcastReceiver;

    public static boolean mRegisterTag = false;

    public static void init(final Application app) {
        if (app == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        if (sApp == null) {
            sApp = app;
            GeneralUtils.getGaid();
            initBoadcast();
            return;
        }
    }


    public static Application getApp() {
        if (sApp != null) return sApp;
        if (sApp == null) throw new NullPointerException("reflect failed.");
        return sApp;
    }

    public static String getIMEI() {
        String imei = PhoneUtils.getIMEI();
        return imei + "\n";
    }

    public static String getInfo() {
        String msg = PhoneUtils.getV1();
        msg += PhoneUtils.getV2();
        msg += PhoneUtils.getV3();
        msg += PhoneUtils.getV4();
        msg += PhoneUtils.getV5();
        msg += PhoneUtils.getV6();
        msg += PhoneUtils.getV7();
        msg += PhoneUtils.getV8();
        msg += PhoneUtils.getV9();
        msg += PhoneUtils.getV10();
        msg += PhoneUtils.getV11();

        return msg;
    }

    public static void initBoadcast() {
        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
        IntentFilter intent = new IntentFilter();
        intent.addAction(Intent.ACTION_BATTERY_CHANGED);
        UtilsApp.getApp().registerReceiver(batteryBroadcastReceiver, intent);
        mRegisterTag = true;
    }


    public static void removeBoadcast() {
        if (mRegisterTag) {
            UtilsApp.getApp().unregisterReceiver(batteryBroadcastReceiver);
            mRegisterTag = false;
        }
    }

}
