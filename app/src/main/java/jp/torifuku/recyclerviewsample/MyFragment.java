package jp.torifuku.recyclerviewsample;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.torifuku.recyclerviewsample.model.Data;
import jp.torifuku.ui.TorifukuAdapter;

/**
 * MyFragment
 */
public class MyFragment extends Fragment {
    private static final String KEY = "key_data_list";

    private List<Data> dataList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<?> adapter;

    static Fragment newInstance(ArrayList<Data> list) {
        Fragment f = new MyFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY, list);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = getArguments().getParcelableArrayList(KEY);
        adapter = new TorifukuAdapter(dataList, R.layout.item, R.id.imageView, R.id.textView, R.drawable.empty_photo, getResources());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView = (RecyclerView) result.findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a StaggeredGridLayoutManager
        final int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 3 : 2;
        layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);

        return result;
    }
}
