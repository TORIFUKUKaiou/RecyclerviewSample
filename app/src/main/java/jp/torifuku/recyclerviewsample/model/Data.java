package jp.torifuku.recyclerviewsample.model;

import android.os.Parcel;
import android.os.Parcelable;

import jp.torifuku.ui.TorifukuAdapter;

/**
 * Data
 */
public class Data implements TorifukuAdapter.AdapterData, Parcelable {
    private String title;
    private String url;

    public Data(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
    }

    public static final Parcelable.Creator<Data> CREATOR
            = new Parcelable.Creator<Data>() {
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    private Data(Parcel in) {
        title = in.readString();
        url = in.readString();
    }
}
