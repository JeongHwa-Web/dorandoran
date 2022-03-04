package org.project.my.service;

import java.util.Map;

public interface NaverLoginService {
	//apiUrl만들기
	Map<String, String> getApiUrl() throws Exception;
	//토큰+개인정보 얻기
	Map<String, String> getTokenUserInfo(String code, String state) throws Exception;
	Map<String, Object> insert(Map<String, String> result);
}
