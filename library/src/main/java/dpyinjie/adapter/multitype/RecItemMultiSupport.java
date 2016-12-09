package dpyinjie.adapter.multitype;

public interface RecItemMultiSupport<D> {


    int getItemLayoutId(int viewType);


    int getItemViewType(int position, D d);
}
