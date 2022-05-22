package com.dh8c2.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dh8c2.library.model.PhieuMuon;

import java.util.List;

public class PhieuMuonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muon);

        RecyclerView recyclerView = findViewById(R.id.phieu_muon_recycler);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<PhieuMuon> list = databaseHelper.getPhieuMuonList();
        adapter.setList(list);
        recyclerView.setAdapter(adapter);
    }
}