package dpyinjie.adapter.multitype;

/**
 * Created by YinJie on 2016/12/8 09:58.
 */
public interface RecItemMultiSupport<D> {


    int getItemLayoutId(int viewType);


    int getItemViewType(int position, D d);
}
