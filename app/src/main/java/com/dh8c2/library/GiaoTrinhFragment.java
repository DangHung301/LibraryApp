package com.dh8c2.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dh8c2.library.model.Sach;

import java.util.List;

public class GiaoTrinhFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giao_trinh, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.sach_list_recycler);
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
        List<Sach> sachList = databaseHelper.getGiaoTrinhList();
        SachAdapter adapter = new SachAdapter(SachAdapter.NORMAL_TYPE);
        adapter.setList(sachList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
