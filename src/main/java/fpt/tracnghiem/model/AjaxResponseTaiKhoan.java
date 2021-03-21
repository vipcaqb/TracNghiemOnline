package fpt.tracnghiem.model;

import java.util.List;

import fpt.tracnghiem.entity.TaiKhoan;

public class AjaxResponseTaiKhoan {
	String msg;
	List<TaiKhoan> result;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<TaiKhoan> getResult() {
		return result;
	}
	public void setResult(List<TaiKhoan> result) {
		this.result = result;
	}
	public AjaxResponseTaiKhoan(String msg, List<TaiKhoan> result) {
		super();
		this.msg = msg;
		this.result = result;
	}
	public AjaxResponseTaiKhoan() {
		super();
	}
	
}
