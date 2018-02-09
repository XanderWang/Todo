package com.xander.todo.mvp;

import com.xander.todo.data.bean.ToDoBean;

/**
 * Created by xander on 2018/1/31.
 */

public interface IToDoCreateView extends IView {

  void showToDo(ToDoBean toDoBean);

  void hadSaveTodo();

}
