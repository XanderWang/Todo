package com.xander.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.xander.todo.data.ToDoBean;
import com.xander.todo.fragment.ToDoAppliation;
import com.xander.todo.mvp.IToDoCreateView;

public class CreateToDoActivity extends AppCompatActivity implements IToDoCreateView {

  private CreateToDoPresenter createToDoPresenter;
  private EditText title;
  private EditText content;
  private ToDoBean toDoBean;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_to_do);
    initView();
    initPresenter();
    parseIntentData();
  }

  private void parseIntentData() {
    Long id = getIntent().getExtras().getLong("_id");
    if (id != -1L) {
      createToDoPresenter.searchToDoByID(id);
    }
  }

  private void initView() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.inflateMenu(R.menu.create_menu);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.save:
            saveTodoBean();
            break;
        }
        return true;
      }
    });
    title = findViewById(R.id.todo_title);
    content = findViewById(R.id.todo_content);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.create_menu, menu);
    return true;
  }

  @Override public void initPresenter() {
    createToDoPresenter = new CreateToDoPresenter();
    createToDoPresenter.setIView(this);
  }

  private void saveTodoBean() {
    if (TextUtils.isEmpty(title.getText().toString())) {
      Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
      return;
    }
    if (null == toDoBean) {
      toDoBean = new ToDoBean();
      toDoBean.setTitle(title.getText().toString());
      toDoBean.setContent(content.getText().toString());
      toDoBean.setState(ToDoBean.TODO_WORKING);
      createToDoPresenter.insertOrUpdateToDo(toDoBean);
    } else {
      toDoBean.setTitle(title.getText().toString());
      toDoBean.setContent(content.getText().toString());
      createToDoPresenter.insertOrUpdateToDo(toDoBean);
    }
  }

  @Override public ToDoAppliation getToDoApplication() {
    return (ToDoAppliation) getApplication();
  }

  @Override public void showToDo(ToDoBean toDoBean) {
    this.toDoBean = toDoBean;
    title.setText(toDoBean.getTitle());
    content.setText(toDoBean.getContent());
  }

  @Override public void hadSaveTodo() {
    onBackPressed();
  }

  @Override protected void onDestroy() {
    createToDoPresenter.release();
    createToDoPresenter = null;
    super.onDestroy();
  }
}
