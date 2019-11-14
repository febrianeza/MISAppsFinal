package com.kelompok3.misapps.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class KaryawanModel implements Parcelable {
    String employee_name, nomor_induk_pegawai, address, gender, base_url, tempat_lahir, tanggal_lahir, gol_darah, agama, status_perkawinan, kewarganegaraan, berlaku_hingga, tempat_buat, tanggal_buat;

    public KaryawanModel() {

    }

    protected KaryawanModel(Parcel in) {
        employee_name = in.readString();
        nomor_induk_pegawai = in.readString();
        address = in.readString();
        gender = in.readString();
        base_url = in.readString();
        tempat_lahir = in.readString();
        tanggal_lahir = in.readString();
        gol_darah = in.readString();
        agama = in.readString();
        status_perkawinan = in.readString();
        kewarganegaraan = in.readString();
        berlaku_hingga = in.readString();
        tempat_buat = in.readString();
        tanggal_buat = in.readString();
    }

    public static final Creator<KaryawanModel> CREATOR = new Creator<KaryawanModel>() {
        @Override
        public KaryawanModel createFromParcel(Parcel in) {
            return new KaryawanModel(in);
        }

        @Override
        public KaryawanModel[] newArray(int size) {
            return new KaryawanModel[size];
        }
    };

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getNomor_induk_pegawai() {
        return nomor_induk_pegawai;
    }

    public void setNomor_induk_pegawai(String nomor_induk_pegawai) {
        this.nomor_induk_pegawai = nomor_induk_pegawai;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getGol_darah() {
        return gol_darah;
    }

    public void setGol_darah(String gol_darah) {
        this.gol_darah = gol_darah;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus_perkawinan() {
        return status_perkawinan;
    }

    public void setStatus_perkawinan(String status_perkawinan) {
        this.status_perkawinan = status_perkawinan;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getBerlaku_hingga() {
        return berlaku_hingga;
    }

    public void setBerlaku_hingga(String berlaku_hingga) {
        this.berlaku_hingga = berlaku_hingga;
    }

    public String getTempat_buat() {
        return tempat_buat;
    }

    public void setTempat_buat(String tempat_buat) {
        this.tempat_buat = tempat_buat;
    }

    public String getTanggal_buat() {
        return tanggal_buat;
    }

    public void setTanggal_buat(String tanggal_buat) {
        this.tanggal_buat = tanggal_buat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(employee_name);
        parcel.writeString(nomor_induk_pegawai);
        parcel.writeString(address);
        parcel.writeString(gender);
        parcel.writeString(base_url);
        parcel.writeString(tempat_lahir);
        parcel.writeString(tanggal_lahir);
        parcel.writeString(gol_darah);
        parcel.writeString(agama);
        parcel.writeString(status_perkawinan);
        parcel.writeString(kewarganegaraan);
        parcel.writeString(berlaku_hingga);
        parcel.writeString(tempat_buat);
        parcel.writeString(tanggal_buat);
    }
}
