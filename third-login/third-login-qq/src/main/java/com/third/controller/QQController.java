package com.third.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.third.util.JwtUtil;

@Controller
public class QQController {
	@Value("${server.port}")
	private String port;
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse resp) {
		return "login";
	}

	@RequestMapping("/qqLogin")
	@ResponseBody
	public String qqLogin(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		map.put("address", "浙江省杭州市");
		String uuid = UUID.randomUUID().toString();
		String jwt = JwtUtil.createJWT(uuid, JSONObject.toJSONString(map), 3600 * 24);
		return jwt;
	}

	@RequestMapping("/getQQInfo")
	@ResponseBody
	public String getQQLoginInfo(String token, HttpServletRequest req, HttpServletResponse resp) {
		return JwtUtil.parseJWT(token).getSubject();
	}
}
