package com.xtjr.application.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by Administrator on 2016/1/14.
 */
public class AuthUtil {
    public static String getMacCode(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return Md5Util.md5String(androidId + "Saltxtjr");
    }

    public static boolean isAuth(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        String key = sp.getString("Key", "");
        return isAuth(context, key);
    }

    public static boolean isAuth(Context context, String key) {
        String macCode = getMacCode(context);
        if (!TextUtils.isEmpty(key)) {
            byte[] desData = Base64.decode(key, Base64.NO_WRAP);
            if (desData != null) {
                byte[] data = DesUtil.desDecrypt(desData, Md5Util.md5String("Saltxtjr"));
                if (data != null) {
                    String authMacCode = new String(data);
                    if (macCode.equals(authMacCode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void saveKey(Context context,String key){
        SharedPreferences.Editor editor=context.getSharedPreferences("Auth",Context.MODE_PRIVATE).edit();
        editor.putString("Key",key);
        editor.commit();
    }

    public static void clearKey(Context context){
        saveKey(context,"");
    }
}