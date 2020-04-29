package com.faresa.daftarpasienrs.pojo.create;
import com.google.gson.annotations.SerializedName;


public class CreateResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("data")
	private DataCreate dataCreate;

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

	public void setDataCreate(DataCreate dataCreate){
		this.dataCreate = dataCreate;
	}

	public DataCreate getDataCreate(){
		return dataCreate;
	}

	@Override
 	public String toString(){
		return 
			"CreateResponse{" + 
			"msg = '" + msg + '\'' + 
			",status_code = '" + statusCode + '\'' + 
			",data = '" + dataCreate + '\'' +
			"}";
		}
}