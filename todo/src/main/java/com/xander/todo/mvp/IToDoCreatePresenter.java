package com.xander.todo.mvp;

import com.xander.todo.data.ToDoBean;

/**
 * 创建 TODO 的Presenter
 * Created by xander on 2018/1/31.
 */

public interface IToDoCreatePresenter<V extends IView> extends IPresenter<V> {

  void insertOrUpdateToDo(ToDoBean toDoBean);

  void deletToDo( ToDoBean toDoBean );

  void searchToDoByID(Long _id);
}
