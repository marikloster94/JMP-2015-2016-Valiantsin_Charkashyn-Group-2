package com.epam.model.ajax;

import java.util.List;

import com.epam.model.Booking;
import com.epam.model.ShowTime;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	private String msg;

	@JsonView(Views.Public.class)
	private String code;

	@JsonView(Views.Public.class)
	private List<ShowTime> result;

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

	public List<ShowTime> getResult() {
		return result;
	}

	public void setResult(List<ShowTime> result) {
		this.result = result;
	}

}
