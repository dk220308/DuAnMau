/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author XPS
 */
public class TaiKhoan {
     private String tenTK;
    private String matKhau;
    private int maNV;
    private String vaitro;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTK, String matKhau, int maNV, String vaitro) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.vaitro = vaitro;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    
}
