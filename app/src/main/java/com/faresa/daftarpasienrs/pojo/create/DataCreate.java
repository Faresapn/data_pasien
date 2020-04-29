package com.faresa.daftarpasienrs.pojo.create;

import com.google.gson.annotations.SerializedName;

public class DataCreate {

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("pasien_id")
	private int pasienId;

	@SerializedName("keluhan_penyakit")
	private String keluhanPenyakit;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("alamat")
	private String alamat;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPasienId(int pasienId){
		this.pasienId = pasienId;
	}

	public int getPasienId(){
		return pasienId;
	}

	public void setKeluhanPenyakit(String keluhanPenyakit){
		this.keluhanPenyakit = keluhanPenyakit;
	}

	public String getKeluhanPenyakit(){
		return keluhanPenyakit;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",pasien_id = '" + pasienId + '\'' + 
			",keluhan_penyakit = '" + keluhanPenyakit + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}