package com.sso;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Value("${server.port}")
	private String port;

	@Autowired
	private StoreFactory storeFactory;

	@RequestMapping("/login")
	public Object login(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		storeFactory.set(CookieUtils.setCookie(req,resp), map);
		return R.success().data(map).setPort(port);
	}

	@RequestMapping("/getUser")
	public Object getUser(HttpServletRequest req, HttpServletResponse resp) {
		Object o=storeFactory.get(CookieUtils.getCookie(req, resp));
		return R.success(o).setPort(port);
	}
}
