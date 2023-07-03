package com.azhar.daftarpesantren.pesantren;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 07-03-2023
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class ModelPesantren implements Serializable {

    String id;
    String nama;
    String nspp;
    String alamat;
    String kyai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNspp() {
        return nspp;
    }

    public void setNspp(String nspp) {
        this.nspp = nspp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKyai() {
        return kyai;
    }

    public void setKyai(String kyai) {
        this.kyai = kyai;
    }
}
