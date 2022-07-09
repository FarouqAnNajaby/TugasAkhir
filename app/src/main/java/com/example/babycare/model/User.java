package com.example.babycare.model;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("name_user")
	private String nameUser;

	@SerializedName("email_user")
	private String emailUser;

	@SerializedName("password_user")
	private String passwordUser;

	@SerializedName("id_user")
	private String idUser;

	public void setNameUser(String nameUser){
		this.nameUser = nameUser;
	}

	public String getNameUser(){
		return nameUser;
	}

	public void setEmailUser(String emailUser){
		this.emailUser = emailUser;
	}

	public String getEmailUser(){
		return emailUser;
	}

	public void setPasswordUser(String passwordUser){
		this.passwordUser = passwordUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"name_user = '" + nameUser + '\'' + 
			",email_user = '" + emailUser + '\'' + 
			",password_user = '" + passwordUser + '\'' + 
			",id_user = '" + idUser + '\'' + 
			"}";
		}
}