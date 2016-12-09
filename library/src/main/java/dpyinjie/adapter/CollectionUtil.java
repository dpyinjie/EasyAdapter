package dpyinjie.adapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by YinJie on 2016/12/9 20:59.
 */
public class CollectionUtil {

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
     * 确保一个非空的 List
     *
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
