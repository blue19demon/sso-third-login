package com.third.service;

public interface Store {
	public Object get(String key);
	
	public void set(String key, Object value);

	public void remove(String key);
}
