/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author XPS
 */
public class GioHang {
    private int maCTSP;
    private int maHD;
    private int maSP;
    private String tenSP;
    private String size;
    private String msac;
    private int sluong;
    private double donGia;
    private double ttien;

    public GioHang() {
    }

    public GioHang(int maCTSP, int maHD, int maSP, String tenSP, String size, String msac, int sluong, double donGia, double ttien) {
        this.maCTSP = maCTSP;
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.size = size;
        this.msac = msac;
        this.sluong = sluong;
        this.donGia = donGia;
        this.ttien = ttien;
    }

    public int getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(int maCTSP) {
        this.maCTSP = maCTSP;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMsac() {
        return msac;
    }

    public void setMsac(String msac) {
        this.msac = msac;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTtien() {
        return ttien;
    }

    public void setTtien(double ttien) {
        this.ttien = ttien;
    }

    public void setTongTien(double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
