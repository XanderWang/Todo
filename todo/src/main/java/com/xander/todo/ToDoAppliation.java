package com.xander.todo;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.xander.todo.data.DaoMaster;
import com.xander.todo.data.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * Created by xander on 2018/1/31.
 */

public class ToDoAppliation extends Application {

  private DaoSession daosession;

  @Override public void onCreate() {
    super.onCreate();
    if (TextUtils.equals(getProcessName(), getPackageName())) {
      init();
    }
  }

  private void init() {
    DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"todo");
    Database db = devOpenHelper.getWritableDb();
    daosession = new DaoMaster(db).newSession();
  }

  public DaoSession getDaosession() {
    return daosession;
  }

  private String getProcessName() {
    String processName = "";
    int pid = android.os.Process.myPid();
    try {
      @SuppressLint("ServiceCast") ActivityManager activityManager =
          (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
      for (ActivityManager.RunningAppProcessInfo processInfo : activityManager.getRunningAppProcesses()) {
        if (processInfo.pid == pid) {
          processName = processInfo.processName;
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return processName;
  }
}
