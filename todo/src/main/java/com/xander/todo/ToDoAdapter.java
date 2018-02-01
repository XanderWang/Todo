package com.xander.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xander.todo.data.ToDoBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xander on 2018/1/6.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoHolder> {

  private List<ToDoBean> toDoBeans = new ArrayList<>();
  private boolean isSelectedMode = false;
  private OnItemClickListener itemClickListener;

  public ToDoAdapter() {
  }

  public void setToDoBeans(List<ToDoBean> toDoBeans) {
    this.toDoBeans.clear();
    this.toDoBeans.addAll(toDoBeans);
    notifyItemRangeChanged(0, toDoBeans.size());
  }

  public void setItemClickListener(OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  @Override public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View todoItem = inflater.inflate(R.layout.todo_item, parent, false);
    //todoItem.setLayoutParams(new ViewGroup.LayoutParams(-1,-2));
    ToDoHolder toDoHolder = new ToDoHolder(todoItem);
    return toDoHolder;
  }

  @Override public void onBindViewHolder(ToDoHolder holder, int position) {
    holder.updateUI(toDoBeans.get(position), isSelectedMode);
  }

  @Override public int getItemCount() {
    return null != toDoBeans ? toDoBeans.size() : 0;
  }

  public class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView title;
    private CheckBox checkBox;
    private ToDoBean toDoBean;
    public ToDoHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.todo_title);
      title.setOnClickListener(this);
      checkBox = itemView.findViewById(R.id.todo_checkbox);
      checkBox.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      if( v == title ) {
        if ( itemClickListener != null ) {
          itemClickListener.onItemClick(toDoBean);
        }
      }
    }

    public void updateUI(ToDoBean toDoBean, boolean isSelectedMode) {
      this.toDoBean = toDoBean;
      title.setText(toDoBean.getTitle());
      //checkBox.setVisibility(isSelectedMode ? View.VISIBLE : View.GONE);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(ToDoBean toDoBean);
  }
}
