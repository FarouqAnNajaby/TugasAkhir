package com.example.babycare.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDiagnosa{

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("data")
	private List<DataDiagnosa> data;

	@SerializedName("message")
	private String message;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("date")
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setData(List<DataDiagnosa> data){
		this.data = data;
	}

	public List<DataDiagnosa> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return uuid;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDiagnosa{" + 
			"status_code = '" + statusCode + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",uuid = '" + uuid + '\'' + 
			"}";
		}
}