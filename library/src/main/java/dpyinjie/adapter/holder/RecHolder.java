package dpyinjie.adapter.holder;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

public class RecHolder extends RecyclerView.ViewHolder implements HoldAble {

    private Context mContext;
    private View mItemView;
    private SparseArray<View> mViewArray;


    public RecHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mItemView = itemView;
        mViewArray = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T getView(int viewId) {
        View view = mViewArray.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViewArray.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public RecHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    @Override
    public RecHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    @Override
    public RecHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public RecHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    @Override
    public RecHolder setImageUri(int viewId, String url) {
//		TODO 加载图片
        return this;
    }

    @Override
    public RecHolder setOnClickListener(int viewId, OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
        return this;
    }

    @Override
    public View getConvertView() {
        return mItemView;
    }

    @Override
    public HoldAble setText(int viewId, int textResId) {
        TextView view = getView(viewId);
        view.setText(textResId);
        return this;
    }

    @Override
    public HoldAble setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public HoldAble setEnabled(int viewId, boolean enable) {
        getView(viewId).setEnabled(enable);
        return this;
    }

    @Override
    public HoldAble setBackgroundColor(int viewId, int colorRes) {
        View view = getView(viewId);
        view.setBackgroundColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }

    @Override
    public HoldAble setBackgroundResource(int viewId, int drawable) {
        getView(viewId).setBackgroundResource(drawable);
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public HoldAble setBackgroundDrawable(int viewId, Drawable drawable) {
        getView(viewId).setBackgroundDrawable(drawable);
        return this;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    public HoldAble setBackground(int viewId, Drawable drawable) {
        View view = getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }

    @Override
    public HoldAble setViewTag(int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    @Override
    public HoldAble setViewTag(int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    @Override
    public HoldAble setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        if (Checkable.class.isInstance(view)) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }

    @Override
    public boolean isChecked(int viewId) {
        View view = getView(viewId);
        if (Checkable.class.isInstance(view)) {
            return ((Checkable) view).isChecked();
        }
        return false;
    }

    @Override
    public HoldAble toggle(int viewId) {
        View view = getView(viewId);
        if (Checkable.class.isInstance(view)) {
            setChecked(viewId, !isChecked(viewId));
        }
        return this;
    }

    @Override
    public HoldAble setTextColor(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }
}
