package com.faresa.daftarpasienrs.pojo.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("data")
	private DataLogin dataLogin;

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

	public void setDataLogin(DataLogin dataLogin){
		this.dataLogin = dataLogin;
	}

	public DataLogin getDataLogin(){
		return dataLogin;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"msg = '" + msg + '\'' + 
			",status_code = '" + statusCode + '\'' + 
			",data = '" + dataLogin + '\'' +
			"}";
		}
}