package jp.torifuku.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import jp.torifuku.net.BitmapDownloader;
import lombok.Getter;

/**
 * BitmapWorkerTask
 */
@Getter
public class BitmapWorkerTask extends AsyncTask<MyAdapter.AdapterData, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private MyAdapter.AdapterData data = null;

    /**
     * ctor
     * package-private
     * @param imageView
     */
    BitmapWorkerTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    // Download image in background.
    @Override
    protected Bitmap doInBackground(MyAdapter.AdapterData... params) {
        data = params[0];
        return BitmapDownloader.download(data.getUrl());
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }
}
