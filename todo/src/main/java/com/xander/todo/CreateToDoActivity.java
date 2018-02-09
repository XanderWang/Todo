package com.xander.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.xander.todo.data.Constants;
import com.xander.todo.data.bean.ToDoBean;
import com.xander.todo.mvp.IToDoCreateView;

public class CreateToDoActivity extends AppCompatActivity implements IToDoCreateView {

  private CreateToDoPresenter createToDoPresenter;
  private EditText title;
  private EditText content;
  private ToDoBean toDoBean;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_to_do);
    initPresenter();
    parseIntentData();
    initView();
    showToDo(toDoBean);
  }

  private void parseIntentData() {
    Long id = getIntent().getExtras().getLong("_id");
    if (id != -1L) {
      toDoBean = createToDoPresenter.loadToDoBeanByID(id);
    }
  }

  private void initView() {
    final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    //toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View v) {
    //    onBackPressed();
    //  }
    //});
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(null == toDoBean ? R.string.title_create : R.string.title_modify);
    toolbar.inflateMenu(null == toDoBean ? R.menu.create_todo_menu : R.menu.modify_todo_menu);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.save:
            saveTodoBean();
            break;
          case R.id.mark_done:
            if (toDoBean != null) {
              toDoBean.setState(Constants.TODO_HAD_DONE);
              saveTodoBean();
            }
            break;
          case R.id.delete:
            deleteToDoBean();
            break;
          default:
            return false;
        }
        return true;
      }
    });
    title = findViewById(R.id.todo_title);
    content = findViewById(R.id.todo_content);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(null == toDoBean ? R.menu.create_todo_menu : R.menu.modify_todo_menu,
        menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return false;
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
      toDoBean.setState(Constants.TODO_WORKING);
      createToDoPresenter.insertOrUpdateToDo(toDoBean);
    } else {
      toDoBean.setTitle(title.getText().toString());
      toDoBean.setContent(content.getText().toString());
      createToDoPresenter.insertOrUpdateToDo(toDoBean);
    }
  }

  private void deleteToDoBean() {
    if (toDoBean != null) {
      createToDoPresenter.deletToDo(toDoBean);
    }
  }

  @Override public ToDoAppliation getToDoApplication() {
    return (ToDoAppliation) getApplication();
  }

  @Override public void showToDo(ToDoBean toDoBean) {
    if (toDoBean == null) {
      return;
    }
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
