package com.example.an.bloodgroupssearchingsystem.View.Search;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.example.an.bloodgroupssearchingsystem.Model.Search.ListSearch;
import com.example.an.bloodgroupssearchingsystem.Presenter.Search.ListSearchAdapter;
import com.example.an.bloodgroupssearchingsystem.Presenter.Search.SearchPresenter;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Donate.Event;
import com.example.an.bloodgroupssearchingsystem.View.Donate.PhieuDangKy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView {

    private Spinner spinnerSearchBlood;
    private Spinner spinnerSearchCounty;
    private SearchPresenter mainPresenter;
    private ListView listViewSearch;
    private Button btSearch;
    private TextView txtResult;
    private EditText edtKhac;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private ArrayList<ListSearch> arrayListSearch;
    private ListSearchAdapter adapter;
    private RelativeLayout rlKhac;
    private SVProgressHUD mSvProgressHUD;
    private boolean checkDialog = false;
    private FrameLayout main;
    private String regexStr = "^[0-9]*$";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        spinnerSearchBlood = (Spinner) view.findViewById(R.id.spinnerBlood);
        spinnerSearchCounty = (Spinner) view.findViewById(R.id.spinnerQuan);
        listViewSearch = (ListView) view.findViewById(R.id.lvSearch);
        btSearch = (Button) view.findViewById(R.id.btn_search);
        txtResult = (TextView) view.findViewById(R.id.txt_Result);
        edtKhac = (EditText) view.findViewById(R.id.edt_Khac);
        main = (FrameLayout) view.findViewById(R.id.main);
        rlKhac = (RelativeLayout) view.findViewById(R.id.rlKhac);
        mSvProgressHUD = new SVProgressHUD(getContext());
        mSvProgressHUD.show();
        mainPresenter = new SearchPresenter(this);

        mainPresenter.LoadSearchBlood();
        mainPresenter.LoadSearchCounty();


        spinnerSearchCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean selectionControl = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (selectionControl) {
                    selectionControl = false;
                } else {
                    spinnerSearchCounty.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSearchBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean selectionControl = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (selectionControl) {
                    selectionControl = false;
                } else {
                    if (spinnerSearchBlood.getSelectedItem().equals("Khác")) {
                        rlKhac.setVisibility(View.VISIBLE);
                    } else {
                        rlKhac.setVisibility(View.GONE);
                        spinnerSearchBlood.setSelection(position);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(edtKhac);
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(edtKhac);
                mSvProgressHUD.show();
                btSearch.setEnabled(false);
                listViewSearch.setAdapter(null);
                if (edtKhac.length() == 0 && spinnerSearchBlood.getSelectedItem().equals("Khác")) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    mSvProgressHUD.dismiss();
                    btSearch.setEnabled(true);
                } else if (edtKhac.length() != 0 && spinnerSearchBlood.getSelectedItem().equals("Khác")
                        && edtKhac.getText().toString().trim().matches(regexStr)) {
                    dialogNhapSo();
                    mSvProgressHUD.dismiss();
                    btSearch.setEnabled(true);
                } else {
                    getSearch();
                }
                txtResult.setVisibility(View.VISIBLE);
                listViewSearch.setVisibility(View.VISIBLE);
            }
        });
        mSvProgressHUD.dismiss();

        // Inflate the layout for this fragment
        return view;

    }


    @Override
    public void displaySearchBlood(List<String> listSearchBlood) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.spinner_textview_align_layout, listSearchBlood);
        adapter.setDropDownViewResource(R.layout.spinner_textview_align_layout);
        spinnerSearchBlood.setAdapter(adapter);
    }

    @Override
    public void displaySearchCounty(List<String> listSearchCounty) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.spinner_textview_align_layout, listSearchCounty);
        adapter.setDropDownViewResource(R.layout.spinner_textview_align_layout);
        spinnerSearchCounty.setAdapter(adapter);
    }

    public void getSearch() {
        arrayListSearch = new ArrayList<ListSearch>();
        mData.child("BloodStorageHospital").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                for (DataSnapshot child : nodeChild) {
                    Search search = child.getValue(Search.class);
                    ListSearch listSearch = child.getValue(ListSearch.class);
                    String arr[] = search.Address.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        if (spinnerSearchBlood.getSelectedItem().toString().equals(search.BloodGroup)
                                && spinnerSearchCounty.getSelectedItem().toString().equals(arr[i])
                                && search.Amount > 0
                                || edtKhac.getText().toString().toUpperCase().equals(search.BloodGroup)
                                && spinnerSearchCounty.getSelectedItem().toString().equals(arr[i])
                        ) {
                            arrayListSearch.add(new ListSearch(listSearch.getName(), listSearch.getPhone()));
                        }
                    }
                    if (spinnerSearchBlood.getSelectedItem().toString().equals(search.BloodGroup)
                            && spinnerSearchCounty.getSelectedItem().toString().equals(" ALL")
                            || edtKhac.getText().toString().toUpperCase().equals(search.BloodGroup)
                            && spinnerSearchCounty.getSelectedItem().toString().equals(" ALL")) {
                        arrayListSearch.add(new ListSearch(listSearch.getName(), listSearch.getPhone()));
                    }
                }
                adapter = new ListSearchAdapter(getContext(), R.layout.dong_list_search, arrayListSearch);
                listViewSearch.setAdapter(adapter);
                if (arrayListSearch.isEmpty()) {
                    checkDialog = true;
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mData.child("HumanDonateBlood").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                for (DataSnapshot child : nodeChild) {
                    Search search = child.getValue(Search.class);
                    ListSearch listSearch = child.getValue(ListSearch.class);
                    String arr[] = search.Address.split(",");
                    for (int i = 0; i < arr.length; i++) {
                        if (spinnerSearchBlood.getSelectedItem().toString().equals(search.BloodGroup)
                                && spinnerSearchCounty.getSelectedItem().toString().equals(arr[i])
                                || edtKhac.getText().toString().toUpperCase().equals(search.BloodGroup)
                                && spinnerSearchCounty.getSelectedItem().toString().equals(arr[i])) {
                            arrayListSearch.add(new ListSearch(listSearch.getName(), listSearch.getPhone()));
                        }
                    }
                    if (spinnerSearchBlood.getSelectedItem().toString().equals(search.BloodGroup)
                            && spinnerSearchCounty.getSelectedItem().toString().equals(" ALL")
                            || edtKhac.getText().toString().toUpperCase().equals(search.BloodGroup)
                            && spinnerSearchCounty.getSelectedItem().toString().equals(" ALL")) {
                        arrayListSearch.add(new ListSearch(listSearch.getName(), listSearch.getPhone()));
                    }
                }
                adapter = new ListSearchAdapter(getContext(), R.layout.dong_list_search, arrayListSearch);
                listViewSearch.setAdapter(adapter);
                if (arrayListSearch.isEmpty() && checkDialog) {
                    dialogKetQua();
                    checkDialog = false;
                } else {

                }
                mSvProgressHUD.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btSearch.setEnabled(true);
    }

    public void dialogKetQua() {
        new AlertDialog.Builder(getContext())
                .setTitle("Thông báo")
                .setMessage("Không có kết quả.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }

    public void dialogNhapSo() {
        new AlertDialog.Builder(getContext())
                .setTitle("Thông báo")
                .setMessage("Không được nhập số.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }

    public int checkName(String name) {
        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isDigit(name.charAt(i))) {
                count++;
            }
        }
        if (count > 0) return 0;
        else return 1;
    }

    public void hideKeyBoard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
