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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ViewHolder
 */
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

    ImageView getImageView() {
        return imageView;
    }

    TextView getTextView() {
        return textView;
    }
}
