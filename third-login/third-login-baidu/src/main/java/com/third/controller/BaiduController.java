package com.third.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.third.conf.DomainConf;
import com.third.service.StoreFactory;
import com.third.util.CookieUtils;
import com.third.util.R;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BaiduController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${server.port}")
	private String port;

	@Autowired
	private StoreFactory storeFactory;
	
	@Autowired
	private DomainConf domainConf;

	@RequestMapping("/qqRedirectLogin")
	public String qqRedirectLogin(HttpServletRequest req, HttpServletResponse resp) {
		String qqRedirectAuthAddress=domainConf.getQqRedirectAuthAddress();
		String url = String.format(qqRedirectAuthAddress, Base64Utils.encodeToUrlSafeString((domainConf.getAppDomain()+"index").getBytes()));
		return "redirect:"+url;
	}
	
	@RequestMapping("/login")
	public String login(ModelMap map,HttpServletRequest req, HttpServletResponse resp) {
		map.addAttribute("qqAuthAddress",domainConf.getQqAuthAddress());
		map.addAttribute("yunMineAddress",domainConf.getYunMineAddress());
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		storeFactory.remove(CookieUtils.removeCookie(req,resp));
		return "redirect:login";
	}

	
	@RequestMapping("/index")
	public String index(String token, ModelMap map,HttpServletRequest req, HttpServletResponse resp) {
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.add("token", token);
		ResponseEntity<Object> responseEntity=restTemplate.postForEntity(domainConf.getQqInfoAddress(), param,Object.class);
		log.info(responseEntity.getBody().toString());
		Map<String, Object> data=JSONObject.parseObject(responseEntity.getBody().toString(), new TypeReference<Map<String, Object>>(){});
		map.addAttribute("qqUser",data);
		map.addAttribute("yunMineAddress",domainConf.getYunMineAddress());
		storeFactory.set(CookieUtils.setCookie(req,resp), data);
		return "index";
	}
	
	
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Object getUser(HttpServletRequest req, HttpServletResponse resp) {
		String cookieId=CookieUtils.getCookie(req, resp);
		if ("".equals(cookieId)||cookieId==null) {
			return R.error("用户已退出").setPort(port);
		}
		Object o = storeFactory.get(cookieId);
		if (o==null) {
			return R.error("用户已退出").setPort(port);
		}
		return R.success(o).setPort(port);
	}
}
