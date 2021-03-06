package com.geetest.sdk.java.web.demo;

/**
 * GeetestWeb配置文件
 * Web用户配置文件。此处填写用户自己申请的验证模块ID/KEY
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "b46d1900d0a894591916ea94ea91bd2c";
	private static final String geetest_key = "36fc3fe98530eea08dfc6ce76e3d24c4";

	//静态常量方法  获得验证码id和秘钥
	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}

}
