package com.xander.todo.mvp;

import com.xander.todo.ToDoAppliation;

/**
 * Created by xander on 2018/1/6.
 */

public interface IView {

  void initPresenter();

  ToDoAppliation getToDoApplication();
}
