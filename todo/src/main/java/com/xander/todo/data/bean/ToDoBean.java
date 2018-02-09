package com.xander.todo.data.bean;

import com.xander.todo.data.Constants;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xander on 2018/1/6.
 */

@Entity(nameInDb = "todo_list") public class ToDoBean {

  @Id(autoincrement = true) private Long _id;
  @NotNull private String title = "";
  private String content = "";
  private String createTime = "";
  private int state = Constants.TODO_WORKING;

  public ToDoBean() {
  }

  @Generated(hash = 1475638118)
  public ToDoBean(Long _id, @NotNull String title, String content,
          String createTime, int state) {
      this._id = _id;
      this.title = title;
      this.content = content;
      this.createTime = createTime;
      this.state = state;
  }

  public Long get_id() {
    return _id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean hasDone() {
    return state == Constants.TODO_HAD_DONE;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public void set_id(Long _id) {
    this._id = _id;
  }

  public String getCreateTime() {
      return this.createTime;
  }

  public void setCreateTime(String createTime) {
      this.createTime = createTime;
  }
}
