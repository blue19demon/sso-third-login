package com.third.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.danga.MemCached.MemCachedClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MemcachedStore implements Store {

	@Autowired
	private MemCachedClient memCachedClient;

	@Override
	public Object get(String key) {
		return memCachedClient.get(key);
	}

	@Override
	public void set(String key, Object value) {
		memCachedClient.set(key, value);
	}

	@Override
	public void remove(String key) {
		memCachedClient.delete(key);
	}

}
