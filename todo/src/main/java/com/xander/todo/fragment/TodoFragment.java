package com.xander.todo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xander.todo.CreateToDoActivity;
import com.xander.todo.R;
import com.xander.todo.ToDoAppliation;
import com.xander.todo.data.bean.ToDoBean;
import com.xander.todo.mvp.IToDoView;
import com.xander.todo.widget.ItemDecoration;

import java.util.List;

/**
 * Created by Xander on 2018/2/2.
 */

public class TodoFragment extends BaseFragment implements IToDoView {

  private ToDoAdapter toDoAdapter;
  private ToDoPresenter todoPresenter;
  private ConstraintLayout rootLayout;
  private RecyclerView todoRV;

  @Override public BaseFragment getInstans() {
    return super.getInstans();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    rootLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_todo, container, false);
    initViews(rootLayout);
    initPresenter();
    return rootLayout;
  }

  private void initViews(View rootView) {
    todoRV = rootView.findViewById(R.id.todo_list);
    toDoAdapter = new ToDoAdapter();
    toDoAdapter.setItemClickListener(new ToDoAdapter.OnItemClickListener() {
      @Override public void onItemClick(ToDoBean toDoBean) {
        jumpToCreateTodo(toDoBean.get_id());
      }

      @Override public void onCheckBoxClick(ToDoBean toDoBean) {

      }
    });

    LinearLayoutManager linearLayoutManager =
        new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

    todoRV.setLayoutManager(linearLayoutManager);
    todoRV.addItemDecoration(
        new ItemDecoration(getResources().getDimensionPixelSize(R.dimen.list_item_devider)));

    todoRV.setAdapter(toDoAdapter);
  }

  @Override public void initPresenter() {
    todoPresenter = new ToDoPresenter();
    todoPresenter.setIView(this);
  }

  private void jumpToCreateTodo(Long id) {
    Intent createIntent = new Intent(this.getContext(), CreateToDoActivity.class);
    createIntent.putExtra("_id", id);
    startActivity(createIntent);
  }

  @Override public void onStart() {
    super.onStart();
    todoPresenter.loadToDoList();
  }

  @Override public ToDoAppliation getToDoApplication() {
    return (ToDoAppliation) getActivity().getApplication();
  }

  @Override public void showLoading() {

  }

  @Override public void dismissLoading() {

  }

  @Override public void showToDos(List<ToDoBean> toDoBeanList) {
    toDoAdapter.setToDoBeans(toDoBeanList);
  }

  @Override public void showMoreToDos(List<ToDoBean> toDoBeanList) {

  }
}
