package com.example.babycare.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Baby implements Parcelable {

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

	public Baby() {
	}

	protected Baby(Parcel in) {
		idBaby = in.readString();
		sexBaby = in.readString();
		nameBaby = in.readString();
		idUser = in.readString();
		age = in.readString();
		dateBirthBaby = in.readString();
	}

	public static final Creator<Baby> CREATOR = new Creator<Baby>() {
		@Override
		public Baby createFromParcel(Parcel in) {
			return new Baby(in);
		}

		@Override
		public Baby[] newArray(int size) {
			return new Baby[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(idBaby);
		dest.writeString(sexBaby);
		dest.writeString(nameBaby);
		dest.writeString(idUser);
		dest.writeString(age);
		dest.writeString(dateBirthBaby);
	}
}