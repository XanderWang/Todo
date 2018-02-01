package com.xander.todo.mvp;

/**
 * 所有的 presenter 的基类
 * Created by xander on 2018/1/6.
 */

public interface IPresenter<V extends IView> {

  void setIView(V view);

  void release();
}
