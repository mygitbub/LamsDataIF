package com.bwzk.util;

import java.net.URL;

public class CommonUtil {
	/**
	 * 发送get请求不需要返回参数
	 */
	public static void doHttpGet(String urlString) {
	    try {
	        URL url = new URL(urlString);
	        url.openStream().close();
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
