package com.model;

public class AirPollution {
	float co;
	float ho;
	int temperature;
	float humidity;
	float userlat;
	float userlng;
	String uname;
	public float getUserlat() {
		return userlat;
	}
	public void setUserlat(float userlat) {
		this.userlat = userlat;
	}
	public float getUserlng() {
		return userlng;
	}
	public void setUserlng(float userlng) {
		this.userlng = userlng;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public float getCo() {
		return co;
	}
	public void setCo(float co) {
		this.co = co;
	}
	public float getHo() {
		return ho;
	}
	public void setHo(float ho) {
		this.ho = ho;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	

}
