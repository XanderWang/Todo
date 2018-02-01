package com.xander.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.xander.todo.data.ToDoBean;
import com.xander.todo.mvp.IToDoView;
import com.xander.todo.widget.ItemDecoration;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IToDoView {

  private ToDoAdapter toDoAdapter;
  private RecyclerView todoRV;

  private ToDoPresenter todoPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();
    initPresenter();
    //todoPresenter.loadToDoList();
  }

  @Override public void initPresenter() {
    todoPresenter = new ToDoPresenter();
    todoPresenter.setIView(this);
  }

  private void initViews() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.inflateMenu(R.menu.main_menu);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.create:
            jumpToCreateTodo(-1L);
            break;
        }
        return true;
      }
    });

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    todoRV = findViewById(R.id.todo_list);
    toDoAdapter = new ToDoAdapter();
    toDoAdapter.setItemClickListener(new ToDoAdapter.OnItemClickListener() {
      @Override public void onItemClick(ToDoBean toDoBean) {
        jumpToCreateTodo(toDoBean.get_id());
      }
    });

    LinearLayoutManager linearLayoutManager =
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    todoRV.setLayoutManager(linearLayoutManager);
    todoRV.addItemDecoration(new ItemDecoration(10));

    todoRV.setAdapter(toDoAdapter);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  private void jumpToCreateTodo(Long id) {
    Intent createIntent = new Intent(this, CreateToDoActivity.class);
    createIntent.putExtra("_id",id);
    startActivity(createIntent);
  }

  @Override protected void onStart() {
    super.onStart();
    todoPresenter.loadToDoList();
  }

  @Override public ToDoAppliation getToDoApplication() {
    return (ToDoAppliation) getApplication();
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
