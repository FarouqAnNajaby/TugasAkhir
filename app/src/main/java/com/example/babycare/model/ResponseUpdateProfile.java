package com.example.babycare.model;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateProfile{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("User")
	private User user;

	@SerializedName("kode")
	private int kode;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
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
			"ResponseUpdateProfile{" + 
			"pesan = '" + pesan + '\'' + 
			",user = '" + user + '\'' + 
			",kode = '" + kode + '\'' + 
			"}";
		}
}