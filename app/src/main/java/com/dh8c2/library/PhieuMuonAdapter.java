package com.dh8c2.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dh8c2.library.model.PhieuMuon;

import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.Holder> {
    private List<PhieuMuon> phieuMuonList;
    public void setList(List<PhieuMuon> list) {
        phieuMuonList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.phieu_muon_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        PhieuMuon phieuMuon = phieuMuonList.get(position);
        holder.tenSv.setText(holder.itemView.getContext().getString(R.string.ten_sv, phieuMuon.getTenSv()));
        holder.maSv.setText(holder.itemView.getContext().getString(R.string.ma_sv, phieuMuon.getMaSv()));
        holder.lop.setText(holder.itemView.getContext().getString(R.string.lop, phieuMuon.getLop()));
        holder.tensach.setText(holder.itemView.getContext().getString(R.string.tensach, phieuMuon.getTenSach()));
        holder.tg.setText(holder.itemView.getContext().getString(R.string.tg, phieuMuon.getTenSach()));
        holder.nxb.setText(holder.itemView.getContext().getString(R.string.ngaytra, phieuMuon.getNgayTra()));
        holder.namxb.setText(holder.itemView.getContext().getString(R.string.ngaymuon, phieuMuon.getNgayMuon()));
    }

    @Override
    public int getItemCount() {
        return phieuMuonList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tenSv, maSv, lop, tensach, tg, nxb, namxb;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tenSv= itemView.findViewById(R.id.ten_sv);
            maSv= itemView.findViewById(R.id.ma_sv);
            lop= itemView.findViewById(R.id.lop);
            tensach= itemView.findViewById(R.id.ten_sach);
            tg= itemView.findViewById(R.id.ten_tac_gia);
            nxb= itemView.findViewById(R.id.nha_xb);
            namxb= itemView.findViewById(R.id.nam_xb);
        }
    }
}
