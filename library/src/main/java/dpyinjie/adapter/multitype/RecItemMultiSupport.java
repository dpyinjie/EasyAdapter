package dpyinjie.adapter.multitype;

/**
 * Created by YinJie on 2016/12/8 09:58.
 */
public interface RecItemMultiSupport<D> {

    /**
     * 获取对应的 Item 数据的布局 id
     *
     * @param viewType
     * @return
     */
    int getItemLayoutId(int viewType);

    /**
     * @param d
     * @return
     */
    int getItemViewType(D d);
}
