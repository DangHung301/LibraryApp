package com.dh8c2.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.dh8c2.library.model.Sach;

import java.util.List;

public class SachListActivity extends AppCompatActivity {
    SachAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_list);

        RecyclerView recyclerView = findViewById(R.id.sach_list_recycler);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Sach> sachList = databaseHelper.getSachList();
        adapter = new SachAdapter(SachAdapter.DELETE_TYPE);
        adapter.setList(sachList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume(); DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Sach> sachList = databaseHelper.getSachList();
        adapter.setList(sachList);
        adapter.notifyDataSetChanged();
    }
}