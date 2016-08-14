package com.ponpongi.pongicounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ponpongi.pongicounter.notifier.DataUpdateNotifier;
import com.ponpongi.pongicounter.touchHelper.CardItemTouchHelperCallback;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardViewFragment extends Fragment implements DataUpdateNotifier {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ItemAdapter adapter;
    private List<CounterItem> data_list;

    public CardViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param data_list Lit of data to display.
     * @return A new instance of fragment CardViewFragment.
     */
    public static CardViewFragment newInstance(List<CounterItem> data_list) {
        CardViewFragment fragment = new CardViewFragment();
        fragment.setData(data_list);
        return fragment;
    }

    private void setData(List<CounterItem> data_list) {
        this.data_list = data_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.card_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(data_list, true);
        recyclerView.setAdapter(adapter);

        // drag and drop
        ItemTouchHelper.Callback callback = new CardItemTouchHelperCallback(adapter);
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

    @Override
    public void notifyDataUpdate(List<CounterItem> items) {
        this.adapter.notifyDataSetChanged();
    }
}
