package com.example.babycare.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataDiagnosa {

	@SerializedName("id_baby")
	private String idBaby;

	@SerializedName("penyakit")
	private String penyakit;

	@SerializedName("solusi")
	private List<String> solusi;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id_gejala")
	private List<String> idgejala;

	public DataDiagnosa(String idBaby, String idUser, List<String> gejala) {
		this.idBaby = idBaby;
		this.idUser = idUser;
		this.idgejala = gejala;
	}

	public DataDiagnosa() {

	}

	public void setIdBaby(String idBaby){
		this.idBaby = idBaby;
	}

	public String getIdBaby(){
		return idBaby;
	}

	public void setPenyakit(String penyakit){
		this.penyakit = penyakit;
	}

	public String getPenyakit(){
		return penyakit;
	}

	public void setSolusi(List<String> solusi){
		this.solusi = solusi;
	}

	public List<String> getSolusi(){
		return solusi;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setGejala(List<String> gejala){
		this.idgejala = gejala;
	}

	public List<String> getGejala(){
		return idgejala;
	}

	@Override
 	public String toString(){
		return
			"DataItem{" +
			"id_baby = '" + idBaby + '\'' +
			",penyakit = '" + penyakit + '\'' +
			",solusi = '" + solusi + '\'' +
			",id_user = '" + idUser + '\'' +
			",gejala = '" + idgejala + '\'' +
			"}";
		}
}