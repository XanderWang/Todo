package com.xander.todo;

import com.xander.todo.data.DaoSession;
import com.xander.todo.data.bean.ToDoBean;
import com.xander.todo.data.ToDoBeanDao;
import com.xander.todo.mvp.IToDoCreatePresenter;
import com.xander.todo.mvp.IToDoCreateView;
import java.util.List;

/**
 * Created by xander on 2018/1/31.
 */

public class CreateToDoPresenter implements IToDoCreatePresenter<IToDoCreateView> {

  private IToDoCreateView iToDoCreateView;
  private DaoSession daoSession;

  @Override public void deletToDo(ToDoBean toDoBean) {
    daoSession.getToDoBeanDao().delete(toDoBean);
    iToDoCreateView.hadSaveTodo();
  }

  @Override public ToDoBean loadToDoBeanByID(Long _id) {
    List<ToDoBean> list = daoSession.getToDoBeanDao().queryBuilder()
        .where(ToDoBeanDao.Properties._id.eq(_id))
        .build()
        .list();
    if( null != list && list.size() == 1 ) {
      return list.get(0);
    }
    return null;
  }

  @Override public void setIView(IToDoCreateView view) {
    iToDoCreateView = view;
    daoSession = iToDoCreateView.getToDoApplication().getDaosession();
  }

  @Override public void release() {
    iToDoCreateView = null;
    daoSession = null;
  }

  @Override public void insertOrUpdateToDo(ToDoBean toDoBean) {
    try {
      daoSession.getToDoBeanDao().insertOrReplace(toDoBean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    iToDoCreateView.hadSaveTodo();
  }
}
