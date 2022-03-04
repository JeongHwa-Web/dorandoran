package org.project.my.service;

import java.util.Map;

public interface LoginService {
	Map<String, Object> login(String userid, String passwd);
}
