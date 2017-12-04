package koreatech.cse.service;

import koreatech.cse.domain.rest.Festival;
import org.json.simple.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

@Service
public class festivalService {

    public String findLonLatByAddr(String txt) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Authorization", "KakaoAK a4330178b5106aa974e333858b635131");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();

        URI url = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/search/address?")
                .queryParam("query", txt)
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(), HttpMethod.GET, request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();

        try {
            System.out.println(url.toURL().toString());
        } catch (Exception e) {};
        System.out.println(result);
        String lon="", lat="";

        for(int i=result.indexOf("y")+4; result.charAt(i)!='"';i++) {
            lat += result.charAt(i);
        }
        for(int i=result.indexOf("x")+4; result.charAt(i)!='"';i++) {
            lon += result.charAt(i);
        }

        String dest = lat+","+lon;
        return dest;
    }

    //축제 좌표 리스트 반환 함수
    public ArrayList<String> findFestivalByDate(String eventStartDate){
        ArrayList<String> Address= new ArrayList<String>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml; charset=UTF-8");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();

        String serviceKey = "l%2FXKLcW5Jj0okWYGuXHBNNOAAmPof7oCXymC6dSQupnglgZ6ePTSmrNu5y3g7NuA6QsToKdDZuL38FkjoPgZyw%3D%3D";
        String serviceKey_Decoder = null;
        try {
            serviceKey_Decoder = URLDecoder.decode(serviceKey.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URI url = UriComponentsBuilder.fromUriString("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey="+serviceKey_Decoder+"&arrange=A&listYN=Y&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest")
                .queryParam("eventStartDate", eventStartDate)
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(), HttpMethod.GET, request, String.class);

        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();

        try {
            System.out.println(url.toURL().toString());
        } catch (Exception e) {};
        System.out.println(result);


        while (result.length()>0) {
            if(result.indexOf("<item>") == -1) break;
            String pointx = "", pointy = "";
            for (int i = result.indexOf("<mapx>") + 6; i < result.indexOf("</mapx>"); i++) {
                    pointx += result.charAt(i);
            }
            for (int i = result.indexOf("<mapy>") + 6; i < result.indexOf("</mapy>"); i++) {
                    pointy += result.charAt(i);
            }
            String addr = pointy + "," + pointx;
            Address.add(addr);

            int start = result.indexOf("</item>")+6;
            int end = result.length()-1;

            result = result.substring(start,end);
        }
        return Address ;
    }

    //json파일에서 소요시간 찾기
    public Long findPathTimeByAddr(String destinationPoint){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("https://maps.googleapis.com/maps/api/directions/json?origin=36.765371699999996,127.28594249999999&mode=transit")
                .queryParam("destination", destinationPoint)
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(),HttpMethod.GET, request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        result = response.getBody(); //result에 결과 다 넣기
        try {
            System.out.println(url.toURL().toString());
        } catch (Exception e) {};

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) jsonParser.parse(result);
        } catch(Exception e) {}

        JSONArray subObj = (JSONArray) jsonObj.get("routes");
        JSONObject subObj1= (JSONObject) subObj.get(0);
        JSONArray subObj2 = (JSONArray) subObj1.get("legs");
        JSONObject subObj3= (JSONObject) subObj2.get(0);
        JSONObject subObj4= (JSONObject) subObj3.get("duration");
        Long subObj5= (Long) subObj4.get("value");

       // System.out.println("소요시간 : "+ subObj5);
        return subObj5;
    }

    //json파일 string로 반환하기
    public String findPathByTime(String destinationPoint){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("https://maps.googleapis.com/maps/api/directions/json?origin=36.765371699999996,127.28594249999999&mode=transit")
                .queryParam("destination", destinationPoint)
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(),HttpMethod.GET, request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        result = response.getBody(); //result에 결과 다 넣기
        try {
            System.out.println(url.toURL().toString());
        } catch (Exception e) {};
        return result;
    }

    //소요시간 짧은거 축제 이름 알아내기
    public String findNameByminNum(int minNum ,String eventStartDate){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml; charset=UTF-8");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();

        String serviceKey = "l%2FXKLcW5Jj0okWYGuXHBNNOAAmPof7oCXymC6dSQupnglgZ6ePTSmrNu5y3g7NuA6QsToKdDZuL38FkjoPgZyw%3D%3D";
        String serviceKey_Decoder = null;
        try {
            serviceKey_Decoder = URLDecoder.decode(serviceKey.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        URI url = UriComponentsBuilder.fromUriString("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey="+serviceKey_Decoder+"&arrange=A&listYN=Y&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest")
                .queryParam("eventStartDate", eventStartDate)
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(), HttpMethod.GET, request, String.class);

        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        result = response.getBody();
        try {
            System.out.println(url.toURL().toString());
        } catch (Exception e) {};

        for(int i = 0; i < minNum; i++){
            int start = result.indexOf("</item>")+6;
            int end = result.length()-1;
            result = result.substring(start,end);
        }
        String festivalName="";
        for (int i = result.indexOf("<title>") + 7; i < result.indexOf("</title>"); i++) {
            festivalName += result.charAt(i);
        }
        return festivalName ;
    }
}
