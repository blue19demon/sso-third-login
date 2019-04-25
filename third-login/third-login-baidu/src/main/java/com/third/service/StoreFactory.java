package com.third.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StoreFactory implements Store{
	@Resource
	private RedisStore redisStore;
	@Resource
	private MysqlStore mysqlStore;
	@Value("${store.type}")
	private String storeType;

	public Store getStore() {
		Store store = redisStore;
		switch (storeType) {
		case "redis":
			store = redisStore;
			break;
		case "mysql":
			store = mysqlStore;
			break;
		default:
			store = redisStore;
			break;
		}
		return store;
	}

	@Override
	public Object get(String key) {
		return getStore().get(key);
	}

	@Override
	public void set(String key, Object value) {
		getStore().set(key, value);
	}

	public void remove(String key) {
		getStore().remove(key);
	}

}
