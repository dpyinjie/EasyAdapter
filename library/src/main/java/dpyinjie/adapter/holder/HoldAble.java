package dpyinjie.adapter.holder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dpyinjie on 16/5/25.
 */
interface HoldAble {

    /**
     * @return
     */
    View getConvertView();

    /**
     * @param viewId
     * @return
     */
    <T extends View> T getView(@IdRes int viewId);

    /**
     * @param viewId
     * @param text
     * @return
     * @see TextView#setText(CharSequence)
     */
    HoldAble setText(@IdRes int viewId, CharSequence text);

    /**
     * @param viewId
     * @param textResId
     * @return
     * @see TextView#setText(int)
     */
    HoldAble setText(@IdRes int viewId, @StringRes int textResId);

    /**
     * @param viewId
     * @param drawableId
     * @return
     * @see ImageView#setImageResource(int)
     */
    HoldAble setImageResource(@IdRes int viewId, @DrawableRes int drawableId);

    /**
     * @param viewId
     * @param drawable
     * @return
     * @see ImageView#setImageDrawable(Drawable)
     */
    HoldAble setImageDrawable(@IdRes int viewId, Drawable drawable);

    /**
     * @param viewId
     * @param bitmap
     * @return
     * @see ImageView#setImageBitmap(Bitmap)
     */
    HoldAble setImageBitmap(@IdRes int viewId, Bitmap bitmap);

    /**
     * @param viewId
     * @param uri
     * @return
     */
    HoldAble setImageUri(@IdRes int viewId, String uri);

    /**
     * @param viewId
     * @param visibility visibility One of {@link View#VISIBLE}, {@link View#INVISIBLE}, or {@link View#GONE}.
     * @return
     * @see View#setVisibility(int)
     */
    HoldAble setVisibility(@IdRes int viewId, int visibility);

    /**
     * @param viewId
     * @param enabled
     * @return
     * @see View#setEnabled(boolean)
     */
    HoldAble setEnabled(@IdRes int viewId, boolean enabled);

    /**
     * @param viewId
     * @param colorRes
     * @return
     * @see View#setBackgroundColor(int)
     */
    HoldAble setBackgroundColor(@IdRes int viewId, @ColorInt int colorRes);

    /**
     * @param viewId
     * @param drawable
     * @return
     * @see View#setBackgroundResource(int)
     */
    HoldAble setBackgroundResource(@IdRes int viewId, @DrawableRes int drawable);

    /**
     * @param viewId
     * @param drawable
     * @return
     * @see View#setBackgroundDrawable(Drawable)
     * @deprecated use {@link #setBackground(int, Drawable)} instead
     */
    @Deprecated
    HoldAble setBackgroundDrawable(@IdRes int viewId, Drawable drawable);

    /**
     * @param viewId
     * @param drawable
     * @return
     * @see View#setBackground(Drawable)
     */
    HoldAble setBackground(@IdRes int viewId, Drawable drawable);

    /**
     * @param viewId
     * @param clickListener
     * @return
     */
    HoldAble setOnClickListener(@IdRes int viewId, OnClickListener clickListener);

    /**
     * @param viewId
     * @param tag
     * @return
     * @see View#setTag(Object)
     */
    HoldAble setViewTag(@IdRes int viewId, Object tag);

    /**
     * @param viewId
     * @param key    The key identifying the tag
     * @param tag
     * @return
     * @see View#setTag(int, Object)
     */
    HoldAble setViewTag(@IdRes int viewId, int key, Object tag);

    /**
     * @param viewId
     * @param checked
     * @see Checkable#setChecked(boolean)
     */
    HoldAble setChecked(@IdRes int viewId, boolean checked);

    /**
     * @param viewId
     * @return
     * @see Checkable#isChecked()
     */
    boolean isChecked(@IdRes int viewId);

    /**
     * @param viewId
     * @see Checkable#toggle()
     */
    HoldAble toggle(@IdRes int viewId);

    /**
     * @param viewId
     * @param colorRes
     * @return
     */
    HoldAble setTextColor(@IdRes int viewId, @ColorInt int colorRes);

}
