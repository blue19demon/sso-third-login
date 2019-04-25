package com.sso;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MysqlStore implements Store{

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Object get(String key) {
		log.info("mysql 获取");
		return null;
	}
	@Override
	public void set(String key, Object value) {
		log.info("mysql 存入");
	}
}
