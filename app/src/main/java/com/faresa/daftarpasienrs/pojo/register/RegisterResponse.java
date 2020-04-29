package com.faresa.daftarpasienrs.pojo.register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("data")
	private DataRegister dataRegister;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public void setDataRegister(DataRegister dataRegister){
		this.dataRegister = dataRegister;
	}

	public DataRegister getDataRegister(){
		return dataRegister;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"msg = '" + msg + '\'' + 
			",status_code = '" + statusCode + '\'' + 
			",data = '" + dataRegister + '\'' +
			"}";
		}
}