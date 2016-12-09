/**
 *
 */
package dpyinjie.adapter.multitype;

/**
 * Created by dpyinjie on 16/5/25.
 */
public interface ExpGroupItemMultiSupport<G> {

    /**
     * @param groupPosition
     * @param data
     * @return
     */
    public int getGroupLayoutId(int groupPosition, G data);

    /**
     * @param groupPosition
     * @return
     */
    public int getGroupType(int groupPosition);

    /**
     * @return
     */
    public int getGroupTypeCount();

}
