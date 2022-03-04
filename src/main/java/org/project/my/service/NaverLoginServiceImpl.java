package org.project.my.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.project.my.dao.MemberDAO;
import org.project.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class NaverLoginServiceImpl implements NaverLoginService{
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public Map<String, String> getTokenUserInfo(String code, String state) throws Exception {
		String token = getToken(code, state);
		Map<String, String>userInfo = getUserInfo(token);
		return userInfo;
	}
	//네이버 로그인버튼의 url생성
	@Override
	public Map<String, String> getApiUrl() throws Exception {
		String clientId = "30RW41HcyH05pLXjodna";//애플리케이션 클라이언트 아이디값";
		//callback주소
	    //로컬
	    String redirectURI = URLEncoder.encode("http://localhost:8081/my/naver_callback", "UTF-8");
	    //실서버
//	    String redirectURI = URLEncoder.encode("http://49.50.174.243:8080/my/naver_callback", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    //url작성
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    //네이버로그인 apiULR과 인증값(state)를 반환
	    Map<String,String>result = new HashMap<>();
	    result.put("apiURL", apiURL);
	    result.put("state", state);
		return result;
	}

	private String getToken(String code, String state) throws Exception {
		String clientId = "30RW41HcyH05pLXjodna";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "Ll0btu9MKF";//애플리케이션 클라이언트 시크릿값";
	    String redirectURI = URLEncoder.encode("http://localhost:8081/my/naver_callback", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    	  System.out.println(res.toString());
	        JSONObject object = (JSONObject) new JSONParser().parse(res.toString());
	        access_token = (String) object.get("access_token");
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
		return access_token;
	}

	private Map<String, String> getUserInfo(String token) throws Exception {
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);
        System.out.println("responseBody="+responseBody);
        JSONObject object = (JSONObject) new JSONParser().parse(responseBody);
        object = (JSONObject) object.get("response");
        System.out.println(object);
        String email = (String) object.get("email");
        String nickname = (String) object.get("nickname");
        String callnum = (String) object.get("mobile");
        String name = (String) object.get("name");
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userid", email);
        userInfo.put("email", email);
        userInfo.put("nickname", nickname);
        userInfo.put("callnum", callnum);
        userInfo.put("name", name);
		return userInfo;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
	
	private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	@Override
	@Transactional
	public Map<String, Object> insert(Map<String, String> userInfo) {
		String userid = userInfo.get("userid");
		String email = userInfo.get("email");
		String nickname = userInfo.get("nickname");
		String callnum = userInfo.get("callnum");
		String name = userInfo.get("name");
		Map<String, Object>result = new HashMap<>();
		//1)회원조회
		Member dbmember = memberDAO.selectOne(email);
		//2)회원가입된 회원
		if(dbmember!=null) {
			if(dbmember.getSimplejoin()==0) {
				result.put("code", 2);
				result.put("msg", "일반가입 회원입니다.");
			}else if(dbmember.getSimplejoin()==1) {
				result.put("code", 3);
				result.put("msg", "네이버 간편가입 회원입니다.");
			}
			return result;
		}
		//3)회원가입
		Member member = new Member();
		member.setUserid(userid);
		member.setEmail(email);
		member.setPasswd("naver");
		member.setName(name);
		member.setCallnum(callnum);
		member.setNickname(nickname);
		member.setSimplejoin(1);
		memberDAO.insert(member);
		result.put("code", 0);
		result.put("msg", "네이버 간편가입 완료");
		return result;
	}

}
