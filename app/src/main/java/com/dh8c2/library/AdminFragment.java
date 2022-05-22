package com.dh8c2.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdminFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        RelativeLayout addItem = view.findViewById(R.id.add_sach);
        RelativeLayout listSach = view.findViewById(R.id.list_sach);
        RelativeLayout phieuMuon = view.findViewById(R.id.phieu_muon);

        addItem.setOnClickListener(v-> {
            startActivity(new Intent(getActivity(), AddBookActivity.class));
        });

        listSach.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SachListActivity.class));
        });

        phieuMuon.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), PhieuMuonActivity.class));
        });

        return view;
    }
}
