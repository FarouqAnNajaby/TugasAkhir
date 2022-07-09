package com.example.babycare.model;

import com.google.gson.annotations.SerializedName;

public class Baby {

	@SerializedName("id_baby")
	private String idBaby;

	@SerializedName("sex_baby")
	private String sexBaby;

	@SerializedName("name_baby")
	private String nameBaby;

	@SerializedName("date_birth_baby")
	private String dateBirthBaby;

	public void setIdBaby(String idBaby){
		this.idBaby = idBaby;
	}

	public String getIdBaby(){
		return idBaby;
	}

	public void setSexBaby(String sexBaby){
		this.sexBaby = sexBaby;
	}

	public String getSexBaby(){
		return sexBaby;
	}

	public void setNameBaby(String nameBaby){
		this.nameBaby = nameBaby;
	}

	public String getNameBaby(){
		return nameBaby;
	}

	public void setDateBirthBaby(String dateBirthBaby){
		this.dateBirthBaby = dateBirthBaby;
	}

	public String getDateBirthBaby(){
		return dateBirthBaby;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_baby = '" + idBaby + '\'' + 
			",sex_baby = '" + sexBaby + '\'' + 
			",name_baby = '" + nameBaby + '\'' + 
			",date_birth_baby = '" + dateBirthBaby + '\'' + 
			"}";
		}
}