package asu.whirlpool.psychewhirlpool.InstagramClasses;
import asu.whirlpool.psychewhirlpool.R;
import android.support.v7.widget.*;

/**
 * Created by jperez60 on 1/4/2018.
 */

public class EndlessRecycleOnScrollListener extends RecyclerView.OnScrollListener
{
    public static String TAG = EndlessRecycleOnScrollListener.class.getSimpleName();
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 3; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private android.support.v7.widget.LinearLayoutManager mLinearLayoutManager;

    public EndlessRecycleOnScrollListener(android.support.v7.widget.LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            current_page++;

            onLoadMore(current_page);

            loading = true;
        }
    }

    public void reset(int previousTotal, boolean loading) {
        this.previousTotal = previousTotal;
        this.loading = loading;
        current_page = 1;
    }

    public void onLoadMore(int current_page) {

    }

    public static String scroll = "https://www.instagram.com/nasapsyche/?__a=1";
}
