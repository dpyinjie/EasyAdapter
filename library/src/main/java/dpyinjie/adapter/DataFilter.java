/**
 *
 */
package dpyinjie.adapter;

/**
 * Created by dpyinjie on 16/5/25.
 */
public interface DataFilter<D> {

    /**
     * 返回true表示不过滤此条数据
     *
     * @param data
     * @return
     * @see BaseListAdapter#filter(DataFilter)
     */
    boolean accept(D data);

}
