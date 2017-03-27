package dpyinjie.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import static dpyinjie.widget.LoadMoreView.LoadState.IDLE;


public abstract class LoadMoreView extends FrameLayout {

    private LoadState mState = IDLE;

    public LoadMoreView(Context context) {
        super(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setState(LoadState state) {
        mState = state;
    }

    @LayoutRes
    public abstract int getLoaddingLayoutResId();


    @LayoutRes
    public abstract int getLoadFailLayoutResId();

    @LayoutRes
    public abstract int getNoMoreDataLayoutResId();

    enum LoadState {

        /**
         * 空闲状态/不可见
         */
        IDLE,

        /**
         * 正在加载状态
         */
        LOADDING,

        /**
         * 加载更多失败
         */
        FAIL,

        /**
         * 没有更多数据
         */
        NO_MORE
    }
}
