package jp.torifuku.recyclerviewsample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import jp.torifuku.net.BitmapDownloader;

public class ImageDetailActivity extends Activity {

    public static final String KEY_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final String url = getIntent().getStringExtra(KEY_URL);
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return BitmapDownloader.download(url);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }.execute();

    }
}
