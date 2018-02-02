package com.xander.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xander.todo.fragment.BaseFragment;
import com.xander.todo.fragment.TodoFragment;


public class MainActivity extends AppCompatActivity {

  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();
  }


  private void initViews() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.inflateMenu(R.menu.main_menu);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
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
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    viewPager = findViewById(R.id.main_viewpager);
    PagersAdapter adapter = new PagersAdapter(getSupportFragmentManager());
    adapter.addFragment(new TodoFragment());
    viewPager.setAdapter(adapter);

  }

  private void jumpToCreateTodo(Long id) {
    Intent createIntent = new Intent(this, CreateToDoActivity.class);
    createIntent.putExtra("_id", id);
    startActivity(createIntent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  private class PagersAdapter extends FragmentPagerAdapter {

    private SparseArray<BaseFragment> fragments = new SparseArray<BaseFragment>();

    public PagersAdapter(FragmentManager fm) {
      super(fm);
    }

    public void addFragment(BaseFragment fragment) {
      fragments.append(fragments.size(), fragment);
    }

    @Override
    public Fragment getItem(int position) {
      return fragments.get(position);
    }

    @Override
    public int getCount() {
      return fragments.size();
    }
  }

}
