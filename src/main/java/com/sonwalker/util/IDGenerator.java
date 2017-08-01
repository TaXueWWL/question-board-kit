package com.sonwalker.util;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

/**
 * @author wuwl@19pay.com.cn
 * @date 2017-7-28
 * @describe id主键生成器
 */

public class IDGenerator {
	
	public static SimpleDateFormat getSimpleDateFormatInstance() {
		SimpleDateFormat simpleDateFormat = null;
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		}
		return simpleDateFormat;
	}
	
	/**
	 * id主键生成逻辑 
	 * @return
	 */
	public static String generateId() {
		StringBuffer id = new StringBuffer();
		String header = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
		String content = getSimpleDateFormatInstance().format(System.currentTimeMillis());
		int tail = new Random().nextInt(999);
		id.append("QBT").append(header).append(content).append(tail);
		return id.toString();
	}
	
	public static void main(String[] args) {
		int i = 0;
		while (i < 99) {
			i++;
			System.out.println(generateId());
		}
	}
}
