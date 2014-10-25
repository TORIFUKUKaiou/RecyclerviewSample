package jp.torifuku.recyclerviewsample;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.displayingbitmaps.provider.Images;

import java.util.ArrayList;

import jp.torifuku.recyclerviewsample.model.Data;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            new AsyncTask<Void, Void, ArrayList<Data>>() {
                @Override
                protected ArrayList<Data> doInBackground(Void... params) {
                    ArrayList<Data> result = new ArrayList<Data>();
                    for (int i = 0; i < Images.imageThumbUrls.length; i++) {
                        result.add(new Data("title"+i, Images.imageThumbUrls[i], Images.imageUrls[i]));
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(ArrayList<Data> result) {
                    Fragment f = MyFragment.newInstance(result);
                    MyActivity.super.getFragmentManager().beginTransaction().add(R.id.sample_content_fragment, f).commit();
                }
            }.execute();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
