package dpyinjie.adapter.multitype;

public interface RecMultiItemSupport<D> {


    int getItemLayoutId(int viewType);


    int getItemViewType(int position, D d);
}
