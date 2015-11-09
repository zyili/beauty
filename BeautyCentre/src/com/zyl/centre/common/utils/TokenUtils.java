package com.zyl.centre.common.utils;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.zyl.centre.entity.Token;
import com.zyl.centre.service.ITokenService;
import com.zyl.centre.service.TokenService;

public class TokenUtils {

	private static final char[] hexCode = "0123456789abcdefGHIJKLMN".toCharArray();

	public static String toHexString(byte[] data) {
		if (data == null) {
			return null;
		}
		StringBuilder r = new StringBuilder(data.length * 2);
		for (byte b : data) {
			r.append(hexCode[(b >> 4) & 0xF]);
			r.append(hexCode[(b & 0xF)]);
		}
		return r.toString();
	}

	public static String generateValue() {
		String param = getRandomString(new Random().nextInt(37))
				+ UUID.randomUUID().toString();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(param.getBytes());
			byte[] messageDigest = algorithm.digest();
			String token = toHexString(messageDigest);
			if (token.length() > 33) {
				token = token.substring(0, 32);
			}
			return token;
		} catch (Exception e) {
			throw new RuntimeException("Token cannot be generated.", e);
		}
	}

	public static String getRandomString(int length) { 
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static boolean isTokenValid(String tokenCode) {
		@SuppressWarnings("unchecked")
		Map<String, Object> sessionMap = (Map<String, Object>) ServletActionContext
				.getRequest().getSession().getAttribute("sessionMap");
		if (sessionMap != null) {

			return sessionMap.get(tokenCode).toString().equals(tokenCode);
		}
		return false;
	}

	public static Map<String, Object> manageToken(String tokenCode) {
		ITokenService tokenService = new TokenService();
		Map<String, Object> reMap= new HashMap<String, Object>();
		Map<String, Object> map = CommonUtils.getsetSessionMap();// 获取session中的token字符串
		if (map != null) {
			if (tokenCode.equals(map.get("tokenCode").toString())) {
				reMap.put("message", "SUCCESS");
				reMap.put("userid", map.get("userid"));
			}
		}else
		{
			Token token = tokenService.findOneByCode(tokenCode);
			if (token != null) {
				Calendar cal = Calendar.getInstance();//获取当前日期
				Date currentDate = cal.getTime();
				if(currentDate.before(token.getExpiredatetime()))
				{
					reMap.put("message", "SUCCESS");
					reMap.put("userid", token.getUserid());
				}
				else
				{
					/*
					 * token过期
					 * */
					reMap.put("message", "TOKENOUT");
					reMap.put("userid", null);
				}
			}
			else
			{
				/*
				 * 数据库中找打不到该token
				 * */
				reMap.put("message", "ERRORTOKEN");
				reMap.put("userid",null);
			}
		}
		return reMap;
	}
}