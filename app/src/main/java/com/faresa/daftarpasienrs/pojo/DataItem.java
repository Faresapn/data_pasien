package com.faresa.daftarpasienrs.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("pasien_id")
	private int pasienId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("keluhan_penyakit")
	private String keluhanPenyakit;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("alamat")
	private String alamat;

	protected DataItem(Parcel in) {
		this.pasienId = in.readInt();
		this.nama = in.readString();
		this.updatedAt = in.readString();
		this.keluhanPenyakit = in.readString();
		this.createdAt = in.readString();
		this.alamat = in.readString();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public void setPasienId(int pasienId){
		this.pasienId = pasienId;
	}

	public int getPasienId(){
		return pasienId;
	}

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
			"DataItem{" + 
			"pasien_id = '" + pasienId + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",keluhan_penyakit = '" + keluhanPenyakit + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.pasienId);
		dest.writeString(this.nama);
		dest.writeString(this.updatedAt);
		dest.writeString(this.keluhanPenyakit);
		dest.writeString(this.createdAt);
		dest.writeString(this.alamat);
	}
}