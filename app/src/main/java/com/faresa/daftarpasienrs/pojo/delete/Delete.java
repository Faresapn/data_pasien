package com.faresa.daftarpasienrs.pojo.delete;

import com.google.gson.annotations.SerializedName;

public class Delete {

	@SerializedName("msg")
	private String msg;

	@SerializedName("status_code")
	private String statusCode;

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

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"msg = '" + msg + '\'' + 
			",status_code = '" + statusCode + '\'' + 
			"}";
		}
}