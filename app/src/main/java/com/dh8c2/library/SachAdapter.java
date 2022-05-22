package com.dh8c2.library;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dh8c2.library.model.PhieuMuon;
import com.dh8c2.library.model.Sach;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachHolder> {
    private List<Sach> sachList;
    public static final int DELETE_TYPE = 0;
    public static final int NORMAL_TYPE = 1;
    public void setList(List<Sach> sachList) {
        this.sachList = sachList;
    }
    private int type;

    public SachAdapter(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public SachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sach_item, parent, false);
        return new SachHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SachHolder holder, int position) {
        Sach sach = sachList.get(position);
        holder.tenSach.setText(sach.getTenSach());
        if (sach.getImgSach() != null) {
            Uri uri = Uri.parse(sach.getImgSach());
            holder.imageSach.setImageURI(uri);
        }
        holder.tenTacGia.setText(sach.getTacGia());
        holder.namXb.setText(sach.getNamXuatBan());
        holder.nhaXb.setText(sach.getNhaXuatBan());
        if (type == DELETE_TYPE) {
            holder.mBtnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.mBtnDelete.setVisibility(View.GONE);
        }
        if (type == DELETE_TYPE) {
            holder.mBtnDelete.setOnClickListener(view -> {
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                databaseHelper.deleteSach(sach.getIdSach());
                Toast.makeText(view.getContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                sachList = databaseHelper.getSachList();
                notifyDataSetChanged();
            });
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), AddBookActivity.class);
                intent.putExtra("id", sach.getIdSach());
                v.getContext().startActivity(intent);
            });
        } else {
            holder.itemView.setOnClickListener(v -> {
                Dialog customDialog = CustomDialog.getInstance(v.getContext());
                EditText tensv = customDialog.findViewById(R.id.ten_sv);
                EditText masv = customDialog.findViewById(R.id.ma_sv);
                EditText lop = customDialog.findViewById(R.id.lop);
                TextView ngayMuon = customDialog.findViewById(R.id.ngay_muon);
                EditText ngayTra = customDialog.findViewById(R.id.ngay_tra);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date date = new Date(System.currentTimeMillis());
                ngayMuon.setText(dateFormat.format(date));

                customDialog.show();
                Button muon = customDialog.findViewById(R.id.order_ok);
                Button huy = customDialog.findViewById(R.id.order_cancel);
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDialog.dismiss();
                    }
                });
                muon.setOnClickListener(view -> {
                    PhieuMuon phieuMuon = new PhieuMuon();
                    if (tensv.getText().toString() != "" && masv.getText().toString() != "" && lop.getText().toString() != "" && ngayTra.getText().toString() != "") {
                        phieuMuon.setMaSv(masv.getText().toString());
                        phieuMuon.setTenSv(tensv.getText().toString());
                        phieuMuon.setLop(lop.getText().toString());
                        phieuMuon.setNgayTra(ngayTra.getText().toString());
                        phieuMuon.setNgayMuon(ngayMuon.getText().toString());
                        phieuMuon.setTenSach(sach.getTenSach());
                        phieuMuon.setIdSach(sach.getIdSach());
                        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                        databaseHelper.addPhieuMuon(phieuMuon);
                        Toast.makeText(view.getContext(), "Mượn thành công!", Toast.LENGTH_SHORT).show();
                        customDialog.dismiss();
                    } else {
                        Toast.makeText(view.getContext(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }

    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class SachHolder extends RecyclerView.ViewHolder {
        ImageView imageSach;
        TextView tenSach, tenTacGia, namXb, nhaXb;
        Button mBtnDelete;
        public SachHolder(@NonNull View itemView) {
            super(itemView);
            imageSach = itemView.findViewById(R.id.ic_sach);
            tenSach = itemView.findViewById(R.id.ten_sach);
            tenTacGia = itemView.findViewById(R.id.ten_tac_gia);
            mBtnDelete = itemView.findViewById(R.id.btnDelete);
            nhaXb = itemView.findViewById(R.id.nha_xb);
            namXb = itemView.findViewById(R.id.nam_xb);
        }
    }
}
