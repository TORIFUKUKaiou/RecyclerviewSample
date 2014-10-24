package jp.torifuku.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * MyAdapter
 */
public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    public interface AdapterData {
        String getTitle();
        String getUrl();
    }

    private List<? extends AdapterData> dataList;
    private int layoutId;
    private int imageViewId;
    private int textViewId;
    private final Bitmap PLACEHOLDER_BITMAP;
    private Resources resources;

    public MyAdapter(List<? extends AdapterData> dataList, int layoutId, int imageViewId, int textViewId, int placeHolderImageId, Resources resources) {
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.imageViewId = imageViewId;
        this.textViewId = textViewId;
        PLACEHOLDER_BITMAP = BitmapFactory.decodeResource(resources, placeHolderImageId);
        this.resources = resources;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(v, imageViewId, textViewId);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final AdapterData data = dataList.get(position);
        viewHolder.getTextView().setText(data.getTitle());
        loadBitmap(data, viewHolder.getImageView());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private void loadBitmap(AdapterData data, ImageView imageView) {
        if (cancelPotentialWork(data, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(resources, PLACEHOLDER_BITMAP, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(data);
        }
    }

    private boolean cancelPotentialWork(AdapterData data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = BitmapWorkerTask.getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final AdapterData bitmapData = bitmapWorkerTask.getData();
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == null || !bitmapData.equals(data)) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }
}
