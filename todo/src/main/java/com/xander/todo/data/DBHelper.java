package com.xander.todo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.greenrobot.greendao.database.Database;

/**
 * Created by xander on 2018/2/4.
 */

public class DBHelper extends DaoMaster.DevOpenHelper {

  public DBHelper(Context context, String name) {
    super(context, name);
  }

  public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
    super(context, name, factory);
  }

  @Override public void onCreate(Database db) {
    super.onCreate(db);
  }

  @Override public void onUpgrade(Database db, int oldVersion, int newVersion) {
    super.onUpgrade(db, oldVersion, newVersion);
  }

}
