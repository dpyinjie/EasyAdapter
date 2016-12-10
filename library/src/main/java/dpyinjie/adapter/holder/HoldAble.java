package dpyinjie.adapter.holder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.ImageView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
     */
    HoldAble setText(@IdRes int viewId, CharSequence text);

    /**
     * @param viewId
     * @param textResId
     * @return
     */
    HoldAble setText(@IdRes int viewId, @StringRes int textResId);

    /**
     * @param viewId
     * @param drawableId
     * @return
     */
    HoldAble setImageResource(@IdRes int viewId, @DrawableRes int drawableId);

    /**
     * @param viewId
     * @param uri
     * @return
     */
    HoldAble setImageURI(@IdRes int viewId, @Nullable Uri uri);

    /**
     * @param viewId
     * @param scaleType
     * @return
     */
    HoldAble setScaleType(@IdRes int viewId, ImageView.ScaleType scaleType);

    /**
     * @param viewId
     * @param drawable
     * @return
     */
    HoldAble setImageDrawable(@IdRes int viewId, Drawable drawable);

    /**
     * @param viewId
     * @param bitmap
     * @return
     */
    HoldAble setImageBitmap(@IdRes int viewId, Bitmap bitmap);


    /**
     * @param viewId
     * @param visibility visibility One of {@link View#VISIBLE}, {@link View#INVISIBLE}, or {@link View#GONE}.
     * @return
     */
    HoldAble setVisibility(@IdRes int viewId, @VisibilityFlavour int visibility);

    /**
     * @param viewId
     * @param enabled
     * @return
     */
    HoldAble setEnabled(@IdRes int viewId, boolean enabled);

    /**
     * @param viewId
     * @param colorRes
     * @return
     */
    HoldAble setBackgroundColorRes(@IdRes int viewId, @ColorRes int colorRes);

    /**
     * @param viewId
     * @param colorInt
     * @return
     */
    HoldAble setBackgroundColorInt(@IdRes int viewId, @ColorInt int colorInt);


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
     */
    HoldAble setViewTag(@IdRes int viewId, Object tag);

    /**
     * @param viewId
     * @param key    The key identifying the tag
     * @param tag
     * @return
     */
    HoldAble setViewTag(@IdRes int viewId, int key, Object tag);

    /**
     * @param viewId
     * @param checked
     */
    HoldAble setChecked(@IdRes int viewId, boolean checked);

    /**
     * @param viewId
     * @return
     */
    boolean isChecked(@IdRes int viewId);

    /**
     * @param viewId
     * @see Checkable#toggle()
     */
    HoldAble toggleCheckState(@IdRes int viewId);

    /**
     * @param viewId
     * @param colorRes
     * @return
     */
    HoldAble setTextColorRes(@IdRes int viewId, @ColorRes int colorRes);

    /**
     * @param viewId
     * @param colorInt
     * @return
     */
    HoldAble setTextColorInt(@IdRes int viewId, @ColorInt int colorInt);


    /**
     * @param viewId
     * @param spSize
     * @return
     */
    HoldAble setTextSizePx(@IdRes int viewId, float spSize);

    /**
     * @param viewId
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    HoldAble setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId, @DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom);

    /**
     * @param viewId
     * @param pxValue
     * @return
     */
    HoldAble setCompoundDrawablePaddingPx(@IdRes int viewId, int pxValue);

    /**
     * @param viewId
     * @param dpValue
     * @return
     */
    HoldAble setCompoundDrawablePaddingDp(@IdRes int viewId, int dpValue);

    /**
     * @param viewId
     * @param size
     * @return
     */
    HoldAble setTextSizeSp(@IdRes int viewId, float size);

    /**
     * @param viewId
     * @param strResid
     * @return
     */
    HoldAble setHintId(@IdRes int viewId, @StringRes int strResid);

    /**
     * @param viewId
     * @param hint
     * @return
     */
    HoldAble setHintStr(@IdRes int viewId, CharSequence hint);

    /**
     * @param viewId
     * @param alpha
     * @return
     */
    HoldAble setAlpha(@IdRes int viewId, @FloatRange(from = 0.0, to = 1.0) float alpha);

    /**
     * @param viewId
     * @param clickable
     * @return
     */
    HoldAble setClickable(@IdRes int viewId, boolean clickable);

    /**
     * @param viewId
     * @param longClickable
     * @return
     */
    HoldAble setLongClickable(@IdRes int viewId, boolean longClickable);

    /**
     * @param viewId
     * @param activated
     * @return
     */
    HoldAble setActivated(@IdRes int viewId, boolean activated);

    /**
     * @param viewId
     * @param scaleX
     * @return
     */
    HoldAble setScaleX(@IdRes int viewId, float scaleX);

    /**
     * @param viewId
     * @param scaleY
     * @return
     */
    HoldAble setScaleY(@IdRes int viewId, float scaleY);

    /**
     * @param viewId
     * @param rotation
     * @return
     */
    HoldAble setRotation(@IdRes int viewId, float rotation);

    /**
     * @param viewId
     * @param rotationX
     * @return
     */
    HoldAble setRotationX(@IdRes int viewId, float rotationX);

    /**
     * @param viewId
     * @param rotationY
     * @return
     */
    HoldAble setRotationY(@IdRes int viewId, float rotationY);

    /**
     * @param viewId
     * @param id
     * @return
     */
    HoldAble setId(@IdRes int viewId, @IdRes int id);

    @IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
    @Retention(value = RetentionPolicy.SOURCE)
    @interface VisibilityFlavour {
    }
}
