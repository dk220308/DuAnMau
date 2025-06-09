/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author XPS
 */
public class ChiTietHD {
    private int maCTHD;
    private int maHD;
    private int maCTSP;
    private int soLuong;
    private double donGia;

    public ChiTietHD() {
    }

    public ChiTietHD(int maCTHD, int maHD, int maCTSP, int soLuong, double donGia) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maCTSP = maCTSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(int maCTSP) {
        this.maCTSP = maCTSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    
}
