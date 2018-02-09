package com.xander.todo.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

/**
 * Created by Xander on 2018/2/9.
 */

public class Utils {

  public static void showToast(Context context, int msgStrId) {
    Utils.showToast(context, context.getString(msgStrId));
  }

  public static void showToast(Context context, String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }
}
