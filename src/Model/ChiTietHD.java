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
    private int maSP;
    private int maHD;
    private int soLuong;
    private float giaBan;

    public ChiTietHD() {
    }

    public ChiTietHD(int maCTHD, int maSP, int maHD, int soLuong, float giaBan) {
        this.maCTHD = maCTHD;
        this.maSP = maSP;
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    
}
