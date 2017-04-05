package com.jyu.sati.common.util;

/**
 * 
 * 密码工具类
 * @author 淋雨又调皮
 *
 */
public class PasswordUtil {

	
	/**
	 * 加密密码
	 * @param userId 用户账号
	 * @param password 密码
	 * @return
	 */
	public static String getMD5Password(String userId, String password) {
		try {
			password = MD5.encode(password, userId);
			password = MD5.encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}
}
