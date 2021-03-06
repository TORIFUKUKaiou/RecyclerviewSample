/*
 * Copyright (C) 2014 TORIFUKU Kaiou.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
 * TorifukuAdapter
 */
public class TorifukuAdapter<T extends TorifukuAdapter.AdapterData> extends RecyclerView.Adapter<ViewHolder> {

    public interface AdapterData {
        String getTitle();
        String getThumbnailUrl();
    }

    public interface OnClickListener<T> {
        void onClick(T obj);
    }

    private List<T> dataList;
    private int layoutId;
    private int imageViewId;
    private int textViewId;
    private final Bitmap PLACEHOLDER_BITMAP;
    private Resources resources;
    private OnClickListener<T> listener;

    public TorifukuAdapter(List<T> dataList, int layoutId, int imageViewId, int textViewId, int placeHolderImageId, Resources resources) {
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.imageViewId = imageViewId;
        this.textViewId = textViewId;
        PLACEHOLDER_BITMAP = BitmapFactory.decodeResource(resources, placeHolderImageId);
        this.resources = resources;
    }

    public void setOnClickListener(OnClickListener<T> listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(v, imageViewId, textViewId);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final T data = dataList.get(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(data);
                }
            }
        });
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
