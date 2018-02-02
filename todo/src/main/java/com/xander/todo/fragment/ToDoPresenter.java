package com.xander.todo.fragment;

import com.xander.todo.data.DaoSession;
import com.xander.todo.data.ToDoBean;
import com.xander.todo.mvp.IToDoPresenter;
import com.xander.todo.mvp.IToDoView;
import java.util.List;

/**
 * Created by xander on 2018/1/6.
 */

public class ToDoPresenter implements IToDoPresenter<IToDoView> {

  private IToDoView iToDoView;
  private DaoSession daoSession;

  public ToDoPresenter() {
  }

  @Override public void release() {

  }

  @Override public void setIView(IToDoView view) {
    iToDoView = view;
    daoSession = iToDoView.getToDoApplication().getDaosession();
  }

  @Override public void loadToDoList() {
    List<ToDoBean> toDoBeanList = daoSession.getToDoBeanDao()
        .queryBuilder().list();
    iToDoView.showToDos(toDoBeanList);
  }

  @Override public void updateToDos(List<ToDoBean> toDoBeanList) {

  }

  @Override public void deleteToDos(List<ToDoBean> toDoBeanList) {

  }
}
