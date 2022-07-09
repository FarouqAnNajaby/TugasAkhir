package com.example.babycare.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetBaby{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<Baby> data;

	@SerializedName("kode")
	private int kode;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<Baby> data){
		this.data = data;
	}

	public List<Baby> getData(){
		return data;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetBaby{" + 
			"pesan = '" + pesan + '\'' + 
			",data = '" + data + '\'' + 
			",kode = '" + kode + '\'' + 
			"}";
		}
}