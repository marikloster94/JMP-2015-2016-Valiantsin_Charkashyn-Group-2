package com.epam.model.ajax;

import java.util.List;

import org.hsqldb.rights.User;

import com.epam.model.Booking;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	private String msg;

	@JsonView(Views.Public.class)
	private String code;

	@JsonView(Views.Public.class)
	private List<Booking> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Booking> getResult() {
		return result;
	}

	public void setResult(List<Booking> result) {
		this.result = result;
	}

}
