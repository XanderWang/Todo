package com.xander.todo.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Xander on 2018/2/23.
 */

@Entity(nameInDb = "countdown_list")
public class CountdownBean {
  @Id(autoincrement = true) private Long _id;
  @NotNull private String title;
  private long countdownTime;
  @Generated(hash = 941627180)
  public CountdownBean(Long _id, @NotNull String title, long countdownTime) {
      this._id = _id;
      this.title = title;
      this.countdownTime = countdownTime;
  }
  @Generated(hash = 1363893971)
  public CountdownBean() {
  }
  public Long get_id() {
      return this._id;
  }
  public void set_id(Long _id) {
      this._id = _id;
  }
  public String getTitle() {
      return this.title;
  }
  public void setTitle(String title) {
      this.title = title;
  }
  public long getCountdownTime() {
      return this.countdownTime;
  }
  public void setCountdownTime(long countdownTime) {
      this.countdownTime = countdownTime;
  }
}
