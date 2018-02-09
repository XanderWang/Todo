package com.xander.todo.mvp;

import com.xander.todo.data.bean.ToDoBean;
import java.util.List;

/**
 * Created by xander on 2018/1/6.
 */

public interface IToDoPresenter<V extends IView> extends IPresenter<V> {

  void loadToDoList();

  void updateToDos(List<ToDoBean> toDoBeanList);


  void deleteToDos(List<ToDoBean> toDoBeanList);

}
