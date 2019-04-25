package com.sso;

public interface Store {
	public Object get(String key);
	
	public void set(String key, Object value);
}
