package com.third;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.third.service.MemcachedStore;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BaiduApplication.class }) // 指定启动类
@Slf4j
public class ApplicationTests {
	@Resource
	private MemcachedStore memcachedStore;
	@Test
	public void testOne() {
		try {
			// 存储数据
			memcachedStore.set("runoob", "Free Education");
			// 输出值
			log.info("runoob value in cache - " + memcachedStore.get("runoob"));
			memcachedStore.remove("runoob");
			log.info("runoob value in cache - " + memcachedStore.get("runoob"));
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}
	}
	

}