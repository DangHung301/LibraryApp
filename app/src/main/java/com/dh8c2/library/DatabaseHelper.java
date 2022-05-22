package com.dh8c2.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.dh8c2.library.model.LoaiSach;
import com.dh8c2.library.model.PhieuMuon;
import com.dh8c2.library.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "library";
    public static final int VERSION = 1;

    // Bảng loai_sach
    public static final String LOAI_SACH_TABLE = "loai_sach";
    public static final String ID_LOAI_SACH = "id_loai_sach";
    public static final String TEN_LOAI_SACH = "ten_loai_sach";

    // Bảng sach
    public static final String SACH_TABLE = "sach";
    public static final String ID_SACH = "id_sach";
    public static final String TEN_SACH = "ten_sach";
    public static final String MO_TA = "mo_ta";
    public static final String TAC_GIA = "tac_gia";
    public static final String NAM_XB = "nam_xuat_ban";
    public static final String NHA_XB = "nha_xuat_ban";
    public static final String ANH_SACH = "anh_sach";

    // Bảng phieu_muon
    public static final String PHIEU_MUON_TABLE = "phieu_muon";
    public static final String ID_PHIEU_MUON = "id_pm";
    public static final String NGAY_MUON = "ngay_muon";
    public static final String NGAY_TRA = "ngay_tra";
    public static final String TEN_SV = "ten_sv";
    public static final String MA_SV = "ma_sv";
    public static final String LOP = "lop";
    public static final String DA_TRA = "da_tra";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Tạo bảng loai_sach
        String createLoaiSach = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                LOAI_SACH_TABLE, ID_LOAI_SACH, TEN_LOAI_SACH);
        sqLiteDatabase.execSQL(createLoaiSach);

        // Tạo bảng sach
        String createSach = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                SACH_TABLE, ID_SACH, ID_LOAI_SACH, TEN_SACH, TAC_GIA, NAM_XB, NHA_XB, MO_TA, ANH_SACH);
        sqLiteDatabase.execSQL(createSach);

        // Tạo bảng phieu_muon
        String createPhieuMuon = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER)",
                PHIEU_MUON_TABLE, ID_PHIEU_MUON, TEN_SV, LOP, MA_SV, ID_SACH, NGAY_MUON, NGAY_TRA, DA_TRA);
        sqLiteDatabase.execSQL(createPhieuMuon);
    }

    // Lấy ra tất cả loại sách
    public List<LoaiSach> getLoaiSachList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOAI_SACH_TABLE, null, null,
                null, null, null, null);

        List<LoaiSach> loaiSachList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                LoaiSach loaiSach = new LoaiSach();
                loaiSach.setIdLoaiSach(cursor.getInt(cursor.getColumnIndex(ID_LOAI_SACH)));
                loaiSach.setLoaiSach(cursor.getString(cursor.getColumnIndex(TEN_LOAI_SACH)));
                loaiSachList.add(loaiSach);
            }
        }
        return loaiSachList;
    }

    // Thêm loại sách
    public void addLoaiSach(LoaiSach loaiSach) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_LOAI_SACH, loaiSach.getLoaiSach());
        db.insert(LOAI_SACH_TABLE, null, values);
        db.close();
    }

    // Lấy ra danh sách sách
    public List<Sach> getSachList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SACH_TABLE
                + " INNER JOIN " + LOAI_SACH_TABLE + " ON " + SACH_TABLE + "." +
                ID_LOAI_SACH + " = " + LOAI_SACH_TABLE + "." + ID_LOAI_SACH, null);

        List<Sach> sachList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Sach sach = new Sach();
                sach.setIdSach(cursor.getInt(cursor.getColumnIndex(ID_SACH)));
                sach.setIdLoaiSach(cursor.getInt(cursor.getColumnIndex(ID_LOAI_SACH)));
                sach.setLoaiSach(cursor.getString(cursor.getColumnIndex(TEN_LOAI_SACH)));
                sach.setTenSach(cursor.getString(cursor.getColumnIndex(TEN_SACH)));
                sach.setNamXuatBan(cursor.getString(cursor.getColumnIndex(NAM_XB)));
                sach.setTacGia(cursor.getString(cursor.getColumnIndex(TAC_GIA)));
                sach.setNhaXuatBan(cursor.getString(cursor.getColumnIndex(NHA_XB)));
                sach.setMoTa(cursor.getString(cursor.getColumnIndex(MO_TA)));
                sach.setImgSach(cursor.getString(cursor.getColumnIndex(ANH_SACH)));
                sachList.add(sach);
            }
        }
        return sachList;
    }

//    // Lấy ra tất cả giáo trình
    public List<Sach> getGiaoTrinhList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SACH_TABLE
                + " INNER JOIN " + LOAI_SACH_TABLE + " ON " + SACH_TABLE + "." +
                ID_LOAI_SACH + " = " + LOAI_SACH_TABLE + "." + ID_LOAI_SACH + " WHERE " + SACH_TABLE + "." +
                ID_LOAI_SACH + " = 1", null);

        List<Sach> sachList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Sach giaoTrinh = new Sach();
                giaoTrinh.setTacGia(cursor.getString(cursor.getColumnIndex(TAC_GIA)));
                giaoTrinh.setMoTa(cursor.getString(cursor.getColumnIndex(MO_TA)));
                giaoTrinh.setNamXuatBan(cursor.getString(cursor.getColumnIndex(NAM_XB)));
                giaoTrinh.setNhaXuatBan(cursor.getString(cursor.getColumnIndex(NHA_XB)));
                giaoTrinh.setLoaiSach(cursor.getString(cursor.getColumnIndex(TEN_LOAI_SACH)));
                giaoTrinh.setTenSach(cursor.getString(cursor.getColumnIndex(TEN_SACH)));
                giaoTrinh.setIdLoaiSach(cursor.getInt(cursor.getColumnIndex(ID_LOAI_SACH)));
                giaoTrinh.setIdSach(cursor.getInt(cursor.getColumnIndex(ID_SACH)));
                giaoTrinh.setImgSach(cursor.getString(cursor.getColumnIndex(ANH_SACH)));
                sachList.add(giaoTrinh);
            }
        }
        return sachList;
    }

    //    // Lấy ra tất cả tài liệu
    public List<Sach> getTaiLieuList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SACH_TABLE
                + " INNER JOIN " + LOAI_SACH_TABLE + " ON " + SACH_TABLE + "." +
                ID_LOAI_SACH + " = " + LOAI_SACH_TABLE + "." + ID_LOAI_SACH + " WHERE " + SACH_TABLE + "." +
                ID_LOAI_SACH + " = 2", null);

        List<Sach> sachList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Sach taiLieu = new Sach();
                taiLieu.setTacGia(cursor.getString(cursor.getColumnIndex(TAC_GIA)));
                taiLieu.setMoTa(cursor.getString(cursor.getColumnIndex(MO_TA)));
                taiLieu.setNamXuatBan(cursor.getString(cursor.getColumnIndex(NAM_XB)));
                taiLieu.setNhaXuatBan(cursor.getString(cursor.getColumnIndex(NHA_XB)));
                taiLieu.setLoaiSach(cursor.getString(cursor.getColumnIndex(TEN_LOAI_SACH)));
                taiLieu.setTenSach(cursor.getString(cursor.getColumnIndex(TEN_SACH)));
                taiLieu.setIdLoaiSach(cursor.getInt(cursor.getColumnIndex(ID_LOAI_SACH)));
                taiLieu.setIdSach(cursor.getInt(cursor.getColumnIndex(ID_SACH)));
                taiLieu.setImgSach(cursor.getString(cursor.getColumnIndex(ANH_SACH)));
                sachList.add(taiLieu);
            }
        }
        return sachList;
    }

    // Lấy ra 1 sách
    public Sach getSach(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SACH_TABLE + " INNER JOIN LOAI_SACH ON LOAI_SACH.ID_LOAI_SACH = SACH.ID_LOAI_SACH WHERE SACH."+  ID_SACH +" = " + id, null);
        Sach sach = new Sach();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                sach.setIdSach(cursor.getInt(cursor.getColumnIndex(ID_SACH)));
                sach.setIdLoaiSach(cursor.getInt(cursor.getColumnIndex(ID_LOAI_SACH)));
                sach.setTenSach(cursor.getString(cursor.getColumnIndex(TEN_SACH)));
                sach.setLoaiSach(cursor.getString(cursor.getColumnIndex(TEN_LOAI_SACH)));
                sach.setNhaXuatBan(cursor.getString(cursor.getColumnIndex(NHA_XB)));
                sach.setNamXuatBan(cursor.getString(cursor.getColumnIndex(NAM_XB)));
                sach.setTacGia(cursor.getString(cursor.getColumnIndex(TAC_GIA)));
                sach.setMoTa(cursor.getString(cursor.getColumnIndex(MO_TA)));
                sach.setImgSach(cursor.getString(cursor.getColumnIndex(ANH_SACH)));
            }
        }
        return sach;
    }

    // Thêm sách mới
    public void addSach(Sach sach) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_LOAI_SACH, sach.getIdLoaiSach());
        values.put(TEN_SACH, sach.getTenSach());
        values.put(ANH_SACH, sach.getImgSach());
        values.put(TAC_GIA, sach.getTacGia());
        values.put(NHA_XB, sach.getNhaXuatBan());
        values.put(NAM_XB, sach.getNamXuatBan());
        values.put(MO_TA, sach.getMoTa());
        db.insert(SACH_TABLE, null, values);
        db.close();
    }

    // Xóa sản phẩm
    public void deleteSach(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SACH_TABLE, ID_SACH + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Sửa tên loại sản phẩm
    public void updateSach(Sach sach) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_LOAI_SACH, sach.getIdLoaiSach());
        values.put(TEN_SACH, sach.getTenSach());
        values.put(TAC_GIA, sach.getTacGia());
        values.put(NHA_XB, sach.getNhaXuatBan());
        values.put(NAM_XB, sach.getNamXuatBan());
        values.put(MO_TA, sach.getMoTa());
        values.put(ANH_SACH, sach.getImgSach());
        db.update(SACH_TABLE, values, ID_SACH + " = ?", new String[]{String.valueOf(sach.getIdSach())});
        db.close();
    }

    // thêm hóa phiếu mượn
    public void addPhieuMuon(PhieuMuon phieuMuon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_SV, phieuMuon.getTenSv());
        values.put(ID_SACH, phieuMuon.getIdSach());
        values.put(DA_TRA, phieuMuon.getDaTra());
        values.put(LOP, phieuMuon.getLop());
        values.put(MA_SV, phieuMuon.getMaSv());
        values.put(NGAY_MUON, phieuMuon.getNgayMuon());
        values.put(NGAY_TRA, phieuMuon.getNgayTra());
        db.insert(PHIEU_MUON_TABLE, null, values);
        db.close();
    }

    // Lấy danh sách phiếu mượn
    public List<PhieuMuon> getPhieuMuonList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM phieu_muon INNER JOIN sach ON phieu_muon.id_sach = sach.id_sach", null);

        List<PhieuMuon> phieuMuonList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setIdPhieuMuon(cursor.getInt(cursor.getColumnIndex(ID_PHIEU_MUON)));
                phieuMuon.setNgayMuon(cursor.getString(cursor.getColumnIndex(NGAY_MUON)));
                phieuMuon.setDaTra(cursor.getInt(cursor.getColumnIndex(DA_TRA)));
                phieuMuon.setTenSach(cursor.getString(cursor.getColumnIndex(TEN_SACH)));
                phieuMuon.setIdSach(cursor.getInt(cursor.getColumnIndex(ID_SACH)));
                phieuMuon.setNgayTra(cursor.getString(cursor.getColumnIndex(NGAY_TRA)));
                phieuMuon.setTenSv(cursor.getString(cursor.getColumnIndex(TEN_SV)));
                phieuMuon.setLop(cursor.getString(cursor.getColumnIndex(LOP)));
                phieuMuon.setMaSv(cursor.getString(cursor.getColumnIndex(MA_SV)));
                phieuMuonList.add(phieuMuon);
            }
        }
        return phieuMuonList;
    }
}
