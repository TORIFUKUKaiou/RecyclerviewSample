package jp.torifuku.recyclerviewsample;

import android.content.Context;
import android.content.Intent;

/**
 * DetailViewProxy
 */
public class DetailViewProxy {

    public void startDetailView(Context context, String url) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(ImageDetailActivity.KEY_URL, url);
        context.startActivity(intent);
    }
}
