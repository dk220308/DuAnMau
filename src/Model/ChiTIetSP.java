/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author XPS
 */
public class ChiTIetSP {
    private int maCTSP;
    private int maSP;
    private String kichThuoc;
    private String mauSac;
    private double gia;
    private int soLuong;

    public ChiTIetSP() {
    }

    public ChiTIetSP(int maCTSP, int maSP, String kichThuoc, String mauSac, double gia, int soLuong) {
        this.maCTSP = maCTSP;
        this.maSP = maSP;
        this.kichThuoc = kichThuoc;
        this.mauSac = mauSac;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public int getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(int maCTSP) {
        this.maCTSP = maCTSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    
}
