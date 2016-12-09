package dpyinjie.adapter.holder;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

public class ListHolder implements HoldAble {

    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViewArray;

    /**
     * @param context
     * @param parent
     * @param layoutId
     * @param position
     */
    private ListHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mContext = context;
        mViewArray = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取 ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ListHolder getHolder(Context context, View convertView, ViewGroup parent, int layoutId,
                                       int position) {
        if (convertView == null) {
            return new ListHolder(context, parent, layoutId, position);
        }
        return (ListHolder) convertView.getTag();
    }

    @Override
    public View getConvertView() {
        return mConvertView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T getView(int viewId) {
        View view = mViewArray.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViewArray.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public ListHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    @Override
    public ListHolder setText(int viewId, int textResId) {
        TextView view = getView(viewId);
        view.setText(textResId);
        return this;
    }

    @Override
    public ListHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    @Override
    public ListHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public ListHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Override
    public ListHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    @Override
    public ListHolder setImageUri(int viewId, String url) {
//		TODO 加载图片
        return this;
    }

    @Override
    public ListHolder setOnClickListener(int viewId, OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ListHolder setBackgroundColorRes(int viewId, int colorRes) {
        View view = getView(viewId);
        view.setBackgroundColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }

    @Override
    public HoldAble setBackgroundColorInt(@IdRes int viewId, @ColorInt int colorInt) {
        View view = getView(viewId);
        view.setBackgroundColor(colorInt);
        return this;
    }

    @Override
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public ListHolder setBackground(int viewId, Drawable background) {
        View view = getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
        return this;
    }

    @Override
    public ListHolder setViewTag(int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    @Override
    public ListHolder setViewTag(int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    @Override
    public ListHolder setChecked(int viewId, boolean check) {
        View view = getView(viewId);
        if (Checkable.class.isInstance(view)) {
            ((Checkable) view).setChecked(check);
        }
        return this;
    }

    @Override
    public HoldAble setEnabled(int viewId, boolean enable) {
        getView(viewId).setEnabled(enable);
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

    @Override
    public boolean isChecked(int viewId) {
        View view = getView(viewId);
        if (Checkable.class.isInstance(view)) {
            return ((Checkable) view).isChecked();
        }
        return false;
    }

    @Override
    public HoldAble toggleCheckState(int viewId) {
        View view = getView(viewId);
        if (Checkable.class.isInstance(view)) {
            Checkable checkView = (Checkable) view;
            checkView.toggle();
        }
        return this;
    }

    @Override
    public HoldAble setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }

    @Override
    public HoldAble setTextColorInt(@IdRes int viewId, @ColorInt int colorInt) {
        TextView view = getView(viewId);
        view.setTextColor(colorInt);
        return this;
    }

}
