package com.xander.todo.mvp;

import com.xander.todo.data.ToDoBean;
import java.util.List;

/**
 * Created by xander on 2018/1/6.
 */

public interface IToDoView extends IView {

  void showLoading();

  void dismissLoading();

  void showToDos(List<ToDoBean> toDoBeanList);

  void showMoreToDos(List<ToDoBean> toDoBeanList);
}
