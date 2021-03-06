package com.thh.easy.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.view.Display;
import android.view.WindowManager;

import com.thh.easy.entity.User;

/**
 * Created by cloud on 2015/10/25.
 */
public class Utils {
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 获得系统的屏幕高
     * @param c
     * @return
     */
    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    /**
     * 获得系统的屏幕宽
     * @param c
     * @return
     */
    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    /**
     * 检测网络连接状况
     * @param context
     * @return
     */
    public static boolean checkNetConnection(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet = conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        return wifi || internet;
    }

    public static int getUserId(Context context) {
        SharedPreferences sp =
                context.getSharedPreferences("user_sp", Context.MODE_PRIVATE);
        if (sp.getBoolean("user_login", false)) {
            User u = (User) FileUtil.readObject(context, "user");
            if (u == null) {
                return 0;
            } else {
                return u.getId();
            }

        }
        return 0;
    }

}
