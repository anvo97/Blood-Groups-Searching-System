package com.example.an.bloodgroupssearchingsystem.View.Search;


import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.Search.ListSearch;
import com.example.an.bloodgroupssearchingsystem.Presenter.Search.ListSearchAdapter;
import com.example.an.bloodgroupssearchingsystem.Presenter.Search.SearchPresenter;
import com.example.an.bloodgroupssearchingsystem.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView{

    private MaterialSpinner spinnerSearchBlood;
    private MaterialSpinner spinnerSearchCounty;
    private SearchPresenter mainPresenter;
    private ListView listViewSearch;
    private Button btSearch;
    private TextView txtResult;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search,container,false);

        spinnerSearchBlood = (MaterialSpinner)view.findViewById(R.id.spinnerBlood);
        spinnerSearchCounty = (MaterialSpinner)view.findViewById(R.id.spinnerQuan);
        listViewSearch = (ListView)view.findViewById(R.id.lvSearch);
        btSearch = (Button)view.findViewById(R.id.btn_search);
        txtResult = (TextView)view.findViewById(R.id.txt_Result);

        mainPresenter=new SearchPresenter(this);

        mainPresenter.LoadSearchBlood();
        mainPresenter.LoadSearchCounty();
        mainPresenter.LoadListSearch();

        spinnerSearchBlood.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        spinnerSearchCounty.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult.setVisibility(View.VISIBLE);
                listViewSearch.setVisibility(View.VISIBLE);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }


    @Override
    public void displaySearchBlood(List<String> listSearchBlood) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.support_simple_spinner_dropdown_item,listSearchBlood);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSearchBlood.setAdapter(adapter);
    }

    @Override
    public void displaySearchCounty(List<String> listSearchCounty) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.support_simple_spinner_dropdown_item,listSearchCounty);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSearchCounty.setAdapter(adapter);
    }

    @Override
    public void displayListSearch(List<ListSearch> listSearch) {
        ListSearchAdapter listSearchAdapter = new ListSearchAdapter(getContext(), R.layout.dong_list_search,listSearch);
        listViewSearch.setAdapter(listSearchAdapter);
    }
}
