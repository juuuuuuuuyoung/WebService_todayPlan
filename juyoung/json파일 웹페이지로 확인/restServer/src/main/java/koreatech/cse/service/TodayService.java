package koreatech.cse.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;

@Service
public class TodayService {

    public void findToday(String location,String searchNumber, String searachType, String activity) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> response = null;
            RestTemplate rest = new RestTemplate();
            URI url = UriComponentsBuilder.fromUriString("https://localhost:8080/today/main/json?")
                    .queryParam("location", location)
                    .queryParam("searchNumber", searchNumber)
                    .queryParam("searachType", searachType)
                    .queryParam("activity", activity)
                    .build()
                    .toUri();

            String result = "";
            try {
                response = rest.exchange(url.toURL().toString(), HttpMethod.GET, request, String.class);
            } catch (MalformedURLException e) {
                System.out.println(e);
            }
            result = response.getBody(); //result에 결과 다 넣기
            try {
                System.out.println(url.toURL().toString());
            } catch (Exception e) {
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject) jsonParser.parse(result);
            } catch (Exception e) {
            }

            JSONObject subObj = (JSONObject) jsonObj.get("weather");
            String weather = (String) subObj.get("sky"); //눈, 비
            String dust = (String) subObj.get("dustGrade"); //좋음 나쁨
            String recommend = (String) subObj.get("recommend"); //book? movie? festival
            String lon = (String) subObj.get("lon"); //lon
            String lat = (String) subObj.get("lat");//lat


            JSONArray movieObj = (JSONArray) jsonObj.get("movie");
            JSONObject numObj = (JSONObject) movieObj.get(0);
            String movieName = (String) numObj.get("movieNm");

            JSONArray bookObj = (JSONArray) jsonObj.get("book");
            JSONObject num2Obj = (JSONObject) bookObj.get(0);
            String bookName = (String) num2Obj.get("title");
            String bookAuthor = (String) num2Obj.get("author");
            String bookDescription = (String) num2Obj.get("description");

            JSONArray festivalObj = (JSONArray) jsonObj.get("festival");
            JSONObject num3Obj = (JSONObject) festivalObj.get(0);
            String festivalTitle = (String) num2Obj.get("name");
    }
}
