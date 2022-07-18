package com.example.babycare.model;

import com.google.gson.annotations.SerializedName;

public class Baby {

	@SerializedName("id_baby")
	private String idBaby;

	@SerializedName("sex_baby")
	private String sexBaby;

	@SerializedName("name_baby")
	private String nameBaby;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("age")
	private String age;

	@SerializedName("date_birth_baby")
	private String dateBirthBaby;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

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

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Baby{" +
				"idBaby='" + idBaby + '\'' +
				", sexBaby='" + sexBaby + '\'' +
				", nameBaby='" + nameBaby + '\'' +
				", idUser='" + idUser + '\'' +
				", age='" + age + '\'' +
				", dateBirthBaby='" + dateBirthBaby + '\'' +
				'}';
	}
}