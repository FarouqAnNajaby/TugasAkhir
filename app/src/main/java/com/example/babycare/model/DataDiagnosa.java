package com.example.babycare.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataDiagnosa implements Parcelable {

	@SerializedName("id_baby")
	private String idBaby;

	@SerializedName("penyakit")
	private String penyakit;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("date")
	private String date;

	@SerializedName("date_birth_baby")
	private String dateBirthBaby;

	@SerializedName("sex_baby")
	private String sexBaby;

	@SerializedName("name_baby")
	private String nameBaby;

	@SerializedName("age")
	private String age;

	@SerializedName("solution")
	private List<String> solution;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("gejala")
	private List<String> gejala;

	protected DataDiagnosa(Parcel in) {
		idBaby = in.readString();
		penyakit = in.readString();
		uuid = in.readString();
		date = in.readString();
		dateBirthBaby = in.readString();
		sexBaby = in.readString();
		nameBaby = in.readString();
		age = in.readString();
		solution = in.createStringArrayList();
		idUser = in.readString();
		gejala = in.createStringArrayList();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(idBaby);
		dest.writeString(penyakit);
		dest.writeString(uuid);
		dest.writeString(date);
		dest.writeString(dateBirthBaby);
		dest.writeString(sexBaby);
		dest.writeString(nameBaby);
		dest.writeString(age);
		dest.writeStringList(solution);
		dest.writeString(idUser);
		dest.writeStringList(gejala);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<DataDiagnosa> CREATOR = new Creator<DataDiagnosa>() {
		@Override
		public DataDiagnosa createFromParcel(Parcel in) {
			return new DataDiagnosa(in);
		}

		@Override
		public DataDiagnosa[] newArray(int size) {
			return new DataDiagnosa[size];
		}
	};

	public String getIdBaby() {
		return idBaby;
	}

	public void setIdBaby(String idBaby) {
		this.idBaby = idBaby;
	}

	public String getPenyakit() {
		return penyakit;
	}

	public void setPenyakit(String penyakit) {
		this.penyakit = penyakit;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateBirthBaby() {
		return dateBirthBaby;
	}

	public void setDateBirthBaby(String dateBirthBaby) {
		this.dateBirthBaby = dateBirthBaby;
	}

	public String getSexBaby() {
		return sexBaby;
	}

	public void setSexBaby(String sexBaby) {
		this.sexBaby = sexBaby;
	}

	public String getNameBaby() {
		return nameBaby;
	}

	public void setNameBaby(String nameBaby) {
		this.nameBaby = nameBaby;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<String> getSolution() {
		return solution;
	}

	public void setSolution(List<String> solution) {
		this.solution = solution;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public List<String> getGejala() {
		return gejala;
	}

	public void setGejala(List<String> gejala) {
		this.gejala = gejala;
	}

	@Override
	public String toString() {
		return "DataDiagnosa{" +
				"idBaby='" + idBaby + '\'' +
				", penyakit='" + penyakit + '\'' +
				", uuid='" + uuid + '\'' +
				", date='" + date + '\'' +
				", dateBirthBaby='" + dateBirthBaby + '\'' +
				", sexBaby='" + sexBaby + '\'' +
				", nameBaby='" + nameBaby + '\'' +
				", age='" + age + '\'' +
				", solution=" + solution +
				", idUser='" + idUser + '\'' +
				", gejala=" + gejala +
				'}';
	}
}