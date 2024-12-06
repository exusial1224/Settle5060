package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Membership implements Serializable{
	private int mbr_id;
	private String mbr_name;
	private String mbr_password;
	private String mbr_mail;
	private String mbr_tel;
	private String mbr_address;
	private LocalDate mbr_birth;
	private LocalDateTime mbr_reg;
	private LocalDateTime mbr_mod;


	public int getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(int mbr_id) {
		this.mbr_id = mbr_id;
	}
	public String getMbr_name() {
		return mbr_name;
	}
	public void setMbr_name(String mbr_name) {
		this.mbr_name = mbr_name;
	}
	public String getMbr_password() {
		return mbr_password;
	}
	public void setMbr_password(String mbr_password) {
		this.mbr_password = mbr_password;
	}
	public String getMbr_mail() {
		return mbr_mail;
	}
	public void setMbr_mail(String mbr_mail) {
		this.mbr_mail = mbr_mail;
	}
	public String getMbr_tel() {
		return mbr_tel;
	}
	public void setMbr_tel(String mbr_tel) {
		this.mbr_tel = mbr_tel;
	}
	public String getMbr_address() {
		return mbr_address;
	}
	public void setMbr_address(String mbr_address) {
		this.mbr_address = mbr_address;
	}
	public LocalDate getMbr_birth() {
		return mbr_birth;
	}
	public void setMbr_birth(LocalDate mbr_birth) {
		this.mbr_birth = mbr_birth;
	}
	public LocalDateTime getMbr_reg() {
		return mbr_reg;
	}
	public void setMbr_reg(LocalDateTime mbr_reg) {
		this.mbr_reg = mbr_reg;
	}
	public LocalDateTime getMbr_mod() {
		return mbr_mod;
	}
	public void setMbr_mod(LocalDateTime mbr_mod) {
		this.mbr_mod = mbr_mod;
	}


}
