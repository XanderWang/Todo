package com.xander.todo.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xander on 2018/1/31.
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {

  private int itemOffset = 10;

  public ItemDecoration(int offset) {
    super();
    itemOffset = offset;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {

    int index = parent.findContainingViewHolder(view).getAdapterPosition();
    if (index == 0) {
      outRect.contains(0, 0, 0, 0);
    } else {
      outRect.contains(0, itemOffset, 0, 0);
    }
  }
}
