package dpyinjie.adapter;

import android.content.Context;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * @param context
     * @param spValue
     * @return
     */
    public static int px2sp(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue / scale + 0.5f);
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isEmptyOrNull(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * @param items
     * @param <T>
     * @return
     */
    public static <T> boolean isEmptyOrNull(T... items) {
        return items == null || items.length == 0;
    }


    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection != null && collection.size() == 0;
    }

    /**
     * @param origList
     * @return
     */
    public static <E> List<E> ensureNotNull(List<E> origList) {
        if (origList == null) {
            return Collections.emptyList();
        }
        return origList;
    }
}
