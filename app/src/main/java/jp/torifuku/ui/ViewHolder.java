package jp.torifuku.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lombok.Getter;

/**
 * ViewHolder
 */
@Getter
public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;

    /**
     * ctor
     * package-private
     * @param view
     * @param imageViewId
     * @param textViewId
     */
    ViewHolder(View view, int imageViewId, int textViewId) {
        super(view);
        imageView = (ImageView) view.findViewById(imageViewId);
        textView = (TextView) view.findViewById(textViewId);
    }
}
