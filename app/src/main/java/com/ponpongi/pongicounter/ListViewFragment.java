package com.ponpongi.pongicounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ponpongi.pongicounter.notifier.DataUpdateNotifier;
import com.ponpongi.pongicounter.touchHelper.SimpleItemTouchHelperCallback;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment implements DataUpdateNotifier {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ItemAdapter adapter;
    private List<CounterItem> data_list;

    public ListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param data_list Data to display.
     * @return A new instance of fragment ListViewFragment.
     */
    public static ListViewFragment newInstance(List<CounterItem> data_list) {
        ListViewFragment fragment = new ListViewFragment();
        fragment.setData(data_list);
        return fragment;
    }

    private void setData(List<CounterItem> data_list) {
        this.data_list = data_list;
    }

    @Override
    public void notifyDataUpdate(List<CounterItem> items) {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        //set list adapter
        recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(data_list, false);
        recyclerView.setAdapter(adapter);

        // drag and drop
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
