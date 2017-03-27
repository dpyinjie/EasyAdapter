package dpyinjie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import dpyinjie.adapter.R;


public class PagingListView extends ListView implements OnScrollListener {

    private boolean mIsLoadingMore;
    private boolean mHasMoreData;
    private boolean mScrollToBottom;

    private LoadMoreFooterView mFooterView;
    private OnLoadMoreListener mOnLoadMoreListener;

    /**
     * @param context
     */
    public PagingListView(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public PagingListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public PagingListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWidget(context);
    }

    /**
     * @param context
     */
    private void initWidget(Context context) {
        setOnScrollListener(this);
        mFooterView = new LoadMoreFooterView(context);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mScrollToBottom = (firstVisibleItem + visibleItemCount >= totalItemCount) && totalItemCount > 0;
        if (!mIsLoadingMore && mHasMoreData && mScrollToBottom) {
            if (mOnLoadMoreListener != null) {
                mIsLoadingMore = true;
                mOnLoadMoreListener.onLoadMore();
                mFooterView.startLoadMore();
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    /**
     * @return
     */
    public boolean isOnLoadingMore() {
        return mIsLoadingMore;
    }

    /**
     */
    public void setLoadMoreCompleted() {
        mFooterView.onLoadMoreSuccess();
        mIsLoadingMore = false;
    }

    /**
     */
    public void setLoadMoreFail() {
        mFooterView.onLoadMoreFail();
        mIsLoadingMore = false;
    }

    /**
     * @param hasMore
     */
    public void setHasMoreItems(boolean hasMore) {
        mHasMoreData = hasMore;
        if (mHasMoreData) {
            if (getFooterViewsCount() < 1) {
                addFooterView(mFooterView);
            }
        } else {
            removeFooterView(mFooterView);
        }
    }

    /**
     * @param listener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    //
    class LoadMoreFooterView extends LoadMoreView {

        private Button mRetryButton;
        private ProgressBar mWaitingProgressBar;

        /**
         * @param context
         */
        public LoadMoreFooterView(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.layout_bottom_load_more_listview_footer, this, true);
            mWaitingProgressBar = (ProgressBar) findViewById(R.id.load_more_progressBar);
            mRetryButton = (Button) findViewById(R.id.btn_load_more_retry);
            mRetryButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (mOnLoadMoreListener != null) {
                        mIsLoadingMore = true;
                        mOnLoadMoreListener.onLoadMore();
                        startLoadMore();
                    }
                }
            });
        }

        /**
         *
         */
        public void onLoadMoreSuccess() {
            mRetryButton.setVisibility(View.INVISIBLE);
            mWaitingProgressBar.setVisibility(View.VISIBLE);
        }

        /**
         *
         */
        public void onLoadMoreFail() {
            mRetryButton.setVisibility(View.VISIBLE);
            mWaitingProgressBar.setVisibility(View.INVISIBLE);
        }

        /**
         *
         */
        public void startLoadMore() {
            mRetryButton.setVisibility(View.INVISIBLE);
            mWaitingProgressBar.setVisibility(View.VISIBLE);
        }

    }
}
