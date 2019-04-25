package com.sso;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisStore implements Store{

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	@Override
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
}
