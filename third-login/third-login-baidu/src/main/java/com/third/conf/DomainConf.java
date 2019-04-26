package com.third.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
public class DomainConf {

	/**
	 *   应用域名
	 */
	@Value("${app.domain}")
	private String appDomain;
	
	/**
	 * QQ授权登录地址
	 */
	@Value("${qq.auth.address}")
	private String qqAuthAddress;
	
	/**
	 * QQ获取登录信息
	 */
	@Value("${qq.info.address}")
	private String qqInfoAddress;
	
	
	/**
	 * 百度云个人中心地址
	 */
	@Value("${yun.mine.address}")
	private String yunMineAddress;
	
	/**
	 * QQ Redirect授权登录地址
	 */
	@Value("${qq.redirect.auth.address}")
	private String qqRedirectAuthAddress;
	
}
