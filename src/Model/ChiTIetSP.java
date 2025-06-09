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
    private String mauSac;
    private String kichThuoc;
    private int soLuong;
    private double gia;

    public ChiTIetSP() {
    }

    public ChiTIetSP(int maCTSP, int maSP, String mauSac, String kichThuoc, int soLuong, double gia) {
        this.maCTSP = maCTSP;
        this.maSP = maSP;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.soLuong = soLuong;
        this.gia = gia;
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

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    
}
