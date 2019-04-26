package com.third.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class R {

	private int code;
	private String msg;
	private Object data;
	private String port;
	
	
	private static R r=new R();
	
	public static R success() {
		r.setCode(200);
		r.setMsg("操作成功");
		return r;
	}
	
	public static R success(Object data) {
		success();
		r.data=data;
		return r;
	}
	
	public static R error(String msg) {
		r=new R();
		r.setCode(500);
		r.setMsg(msg);
		return r;
	}

	public R data(Object data) {
		r.data=data;
		return r;
	}

	public R setPort(String port) {
		r.port=port;
		return r;
	}

}
