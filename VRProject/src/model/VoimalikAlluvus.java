package model;

import java.util.Date;

public class VoimalikAlluvus {
	private int id;
	private String avaja;
	private Date avatud;
	private String muutja;
	private Date muudetud;
	private String sulgeja;
	private String suletud;
	private String kommentaar;
	private Integer riigi_admin_yksuse_id;
	private Integer riigi_admin_yksuse_alluva_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvaja() {
		return avaja;
	}
	public void setAvaja(String avaja) {
		this.avaja = avaja;
	}
	public Date getAvatud() {
		return avatud;
	}
	public void setAvatud(Date avatud) {
		this.avatud = avatud;
	}
	public String getMuutja() {
		return muutja;
	}
	public void setMuutja(String muutja) {
		this.muutja = muutja;
	}
	public Date getMuudetud() {
		return muudetud;
	}
	public void setMuudetud(Date muudetud) {
		this.muudetud = muudetud;
	}
	public String getSulgeja() {
		return sulgeja;
	}
	public void setSulgeja(String sulgeja) {
		this.sulgeja = sulgeja;
	}
	public String getSuletud() {
		return suletud;
	}
	public void setSuletud(String suletud) {
		this.suletud = suletud;
	}
	public String getKommentaar() {
		return kommentaar;
	}
	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
	}
	public Integer getRiigi_admin_yksuse_id() {
		return riigi_admin_yksuse_id;
	}
	public void setRiigi_admin_yksuse_id(int riigi_admin_yksuse_id) {
		this.riigi_admin_yksuse_id = riigi_admin_yksuse_id;
	}
	public Integer getRiigi_admin_yksuse_alluva_id() {
		return riigi_admin_yksuse_alluva_id;
	}
	public void setRiigi_admin_yksuse_alluva_id(int riigi_admin_yksuse_alluva_id) {
		this.riigi_admin_yksuse_alluva_id = riigi_admin_yksuse_alluva_id;
	}
}