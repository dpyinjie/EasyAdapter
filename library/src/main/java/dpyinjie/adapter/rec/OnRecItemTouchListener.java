package dpyinjie.adapter.rec;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnRecItemTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    /**
     * @param recyclerView
     */
    public OnRecItemTouchListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new
                ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    /**
     * @param holder
     * @param adapterPosition
     * @param layoutPosition
     */
    public void onItemClick(RecyclerView.ViewHolder holder, int adapterPosition, int layoutPosition) {
    }

    /**
     * @param holder
     * @param adapterPosition
     * @param layoutPosition
     */
    public void onItemLongClick(RecyclerView.ViewHolder holder, int adapterPosition, int layoutPosition) {
    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
                int adapterPosition = recyclerView.getChildAdapterPosition(child);
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(holder, adapterPosition, recyclerView.getChildLayoutPosition(child));
                }
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
                int adapterPosition = recyclerView.getChildAdapterPosition(child);
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemLongClick(holder, adapterPosition, recyclerView.getChildLayoutPosition(child));
                }
            }
        }
    }
}
