package com.dh8c2.library;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dh8c2.library.model.LoaiSach;
import com.dh8c2.library.model.Sach;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private TextInputEditText mTenSach, mTenTg, mMoTa, mNamXb, mNhaXb;
    private Spinner mSpinner;
    private Button mBtnAdd;
    private ImageView mAddImage;
    private String mImgPath;
    private int mIdLoaiSach;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        databaseHelper = new DatabaseHelper(this);
        initView();
        mBtnAdd.setOnClickListener(view -> {
            String tenSach = mTenSach.getText().toString();
            if (tenSach.equals("")) {
                mTenSach.setError("Vui lòng nhập tên sách!");
            }

            String tenTg = mTenTg.getText().toString();
            if (tenTg.equals("")) {
                mTenTg.setError("Vui lòng nhập tên tác giả!");
            }

            String nhaXb = mNhaXb.getText().toString();
            if (nhaXb.equals("")) {
                mNhaXb.setError("Vui lòng nhập nhà xuất bản!");
            }

            String namXb = mNamXb.getText().toString();
            if (namXb.equals("")) {
                mNamXb.setError("Vui lòng nhập năm xuất bản!");
            }

            String mota = mMoTa.getText().toString();
            if (mota.equals("")) {
                mMoTa.setError("Vui lòng nhập mô tả!");
            }

            if (!tenSach.equals("") && !tenTg.equals("") && !nhaXb.equals("") && !namXb.equals("") && !mota.equals("")) {
                Sach sach = new Sach(tenSach, mIdLoaiSach, tenTg, namXb, nhaXb, mota, mImgPath);
                databaseHelper.addSach(sach);
                Toast.makeText(AddBookActivity.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        int idSach = getIntent().getIntExtra("id", -1);
        if (idSach != -1) {
            Sach sach = databaseHelper.getSach(idSach);
            if (sach.getImgSach() != null) {
                Uri uri = Uri.parse(sach.getImgSach());
                mAddImage.setImageURI(uri);
                mImgPath = sach.getImgSach();
            }

            mTenSach.setText(sach.getTenSach());
            mTenTg.setText(sach.getTacGia() + "");
            mNhaXb.setText(sach.getNhaXuatBan() + "");
            mNamXb.setText(sach.getNamXuatBan() + "");
            mMoTa.setText(sach.getMoTa() + "");

            mBtnAdd.setText("Đồng ý");
            mBtnAdd.setOnClickListener(view -> {
                String tenSach = mTenSach.getText().toString();
                if (tenSach.equals("")) {
                    mTenSach.setError("Vui lòng nhập tên sách");
                }

                String tenTg = mTenTg.getText().toString();
                if (tenTg.equals("")) {
                    mTenTg.setError("Vui lòng nhập tên tác giả!");
                }

                String nhaXb = mNhaXb.getText().toString();
                if (nhaXb.equals("")) {
                    mNhaXb.setError("Vui lòng nhập nhà xuất bản!");
                }

                String namXb = mNamXb.getText().toString();
                if (namXb.equals("")) {
                    mNamXb.setError("Vui lòng nhập năm xuất bản!");
                }

                String mota = mMoTa.getText().toString();
                if (mota.equals("")) {
                    mMoTa.setError("Vui lòng nhập mô tả!");
                }

                if (!tenSach.equals("") && !tenTg.equals("") && !nhaXb.equals("") && !namXb.equals("") && !mota.equals("")) {
                    Sach sach1 = new Sach(tenSach, mIdLoaiSach, tenTg, namXb, nhaXb, mota, mImgPath);
                    sach1.setIdSach(sach.getIdSach());
                    databaseHelper.updateSach(sach1);
                    Toast.makeText(AddBookActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    private void initView() {
        mTenSach = findViewById(R.id.edt_ten_sach);
        mTenTg = findViewById(R.id.edt_tac_gia);
        mMoTa = findViewById(R.id.edt_mo_ta);
        mNamXb = findViewById(R.id.edt_nam_xb);
        mNhaXb = findViewById(R.id.edt_nha_xb);
        mBtnAdd = findViewById(R.id.btn_add);
        mSpinner = findViewById(R.id.category_spinner);
        mAddImage = findViewById(R.id.add_image);

        List<LoaiSach> loaiSachList = databaseHelper.getLoaiSachList();
        List<String> tenLoaiSachList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            loaiSachList.forEach(loaiSach -> tenLoaiSachList.add(loaiSach.getLoaiSach()));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tenLoaiSachList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Lấy id loại sản phẩm
                mIdLoaiSach = loaiSachList.get(i).getIdLoaiSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Lấy ảnh sản phẩm từ bộ nhớ máy
        mAddImage.setOnClickListener(view -> {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = null;
                if (data != null) {
                    uri = data.getData();
                }
                mAddImage.setImageURI(uri);
                mImgPath = getPathImage(uri);
            }
        }
    }

    private String getPathImage(Uri uri) {
        if (uri == null) {
            return null;
        }

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        return uri.getPath();
    }
}