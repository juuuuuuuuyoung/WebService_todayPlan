package koreatech.cse.service;


import koreatech.cse.domain.rest.Weather;
import org.json.simple.JSONArray;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Service
public class WeatherService {

    public boolean loadWeatherByArea(Weather weather) {

        /** 주소로 위도 경도 파싱 */
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Authorization", "KakaoAK a4330178b5106aa974e333858b635131");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/search/address?")
                .queryParam("query", weather.getLocation())
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(),HttpMethod.GET, request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();

        try {
            System.out.println(url.toURL().toString());
        } catch (Exception e) {};

        JSONParser jsonParser = new JSONParser();
        System.out.println(result);

        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) jsonParser.parse(result);
        } catch(Exception e) {}

        JSONArray subObj = (JSONArray) jsonObj.get("documents");
        JSONObject subObj1= (JSONObject) subObj.get(0);
        String address_name= (String) subObj1.get("address_name");
        String y = (String) subObj1.get("y");
        String x = (String) subObj1.get("x");

        System.out.println("##");
        System.out.println(address_name + " " + y + ", " + x);
        System.out.println("##");

        weather.setLocation(address_name);
        weather.setLat(y);
        weather.setLon(x);


        /** 날씨 로드 */
        parseWeatherInformation(weather);

        /** 미세먼지 로드 */
        parseDustInformation(weather);
        return true;
    }

    public boolean loadWeatherByAxis(Weather weather) {
        /** 위도 경도로 주소 파싱 */
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Authorization", "KakaoAK a4330178b5106aa974e333858b635131");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/geo/coord2address?")
                .queryParam("y", weather.getLat())
                .queryParam("x", weather.getLon())
                .build()
                .toUri();

        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(),HttpMethod.GET, request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();

        System.out.println(result);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) jsonParser.parse(result);
        } catch(Exception e) {}

        JSONArray subObj = (JSONArray) jsonObj.get("documents");
        JSONObject subObj1= (JSONObject) subObj.get(0);
        JSONObject subObj2= (JSONObject) subObj1.get("address");
        String address_name= (String) subObj2.get("address_name");

        weather.setLocation(address_name);

        /** 날씨 로드 */
        parseWeatherInformation(weather);


        /** 미세먼지 로드 */
        parseDustInformation(weather);
        return true;
    }

    private void parseWeatherInformation(Weather weather) {
        HttpHeaders weather_headers = new HttpHeaders();

        weather_headers.add("Content-Type", "application/json;charset=UTF-8");
        weather_headers.add("appKey", "0a504381-511e-31ed-8e64-e545017eb4fa");

        HttpEntity weather_request = new HttpEntity(weather_headers);
        ResponseEntity<String> weather_response = null;
        RestTemplate weather_rest = new RestTemplate();
        URI weather_url = UriComponentsBuilder.fromUriString("http://apis.skplanetx.com/weather/summary?")
                .queryParam("version", 1)
                .queryParam("lat", weather.getLat())
                .queryParam("lon", weather.getLon())
                .build()
                .toUri();

        String weather_result = "";
        try {
            weather_response = weather_rest.exchange(weather_url.toURL().toString(), HttpMethod.GET, weather_request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        weather_result = weather_response.getBody();
        System.out.println("WEATHER : " + weather_result);

        JSONParser jsonParser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) jsonParser.parse(weather_result);
        } catch(Exception e) {}

        JSONObject obj1= (JSONObject) jobj.get("weather");
        JSONArray obj2= (JSONArray) obj1.get("summary");
        JSONObject obj3= (JSONObject) obj2.get(0);
        JSONObject todayObj= (JSONObject) obj3.get("today");
        JSONObject temperatureObj= (JSONObject) todayObj.get("temperature");
        JSONObject skyObj= (JSONObject) todayObj.get("sky");

        String tmax = (String) temperatureObj.get("tmax");
        String tmin = (String) temperatureObj.get("tmin");
        String sky = (String) skyObj.get("name");

        weather.setTmin(tmin);
        weather.setTmax(tmax);
        weather.setSky(sky);
    }

    private void parseDustInformation(Weather weather) {
        HttpHeaders dust_headers = new HttpHeaders();

        dust_headers.add("Content-Type", "application/json;charset=UTF-8");
        dust_headers.add("appKey", "0a504381-511e-31ed-8e64-e545017eb4fa");

        HttpEntity dust_request = new HttpEntity(dust_headers);
        ResponseEntity<String> dust_response = null;
        RestTemplate dust_rest = new RestTemplate();
        URI dust_url = UriComponentsBuilder.fromUriString("http://apis.skplanetx.com/weather/dust?")
                .queryParam("version", 1)
                .queryParam("lat", weather.getLat())
                .queryParam("lon", weather.getLon())
                .build()
                .toUri();

        String dust_result = "";
        try {
            dust_response = dust_rest.exchange(dust_url.toURL().toString(), HttpMethod.GET, dust_request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        dust_result = dust_response.getBody();
        System.out.println("DUST : " + dust_result);
        JSONParser jsonParser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) jsonParser.parse(dust_result);
        } catch(Exception e) {}

        JSONObject obj1= (JSONObject) jobj.get("weather");
        JSONArray obj2= (JSONArray) obj1.get("dust");
        JSONObject obj3= (JSONObject) obj2.get(0);
        JSONObject pm10Obj= (JSONObject) obj3.get("pm10");
        String dustValue= (String) pm10Obj.get("value");
        String dustGrade= (String) pm10Obj.get("grade");

        weather.setDustValue(dustValue);
        weather.setDustGrade(dustGrade);
    }
//    public String restTemplate(Map<String, Object> map) throws Exception{
//        String baseUrl = "openapi.epost.go.kr";
//        String url = "";
//        String responseStr = "";
//        String svcKey      = "서비스키값";
//
//        RestTemplate restTpl = new RestTemplate();
//        URI uri = UriComponentsBuilder.newInstance()
//                .scheme("http")
//                .host(baseUrl)
//                .path("/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd")
//                .queryParam("ServiceKey"   , URLDecoder.decode(svcKey, "UTF-8"))
//                .queryParam("searchSe"     , (String) map.get("searchSe"     ))
//                .queryParam("srchwrd"      , (String) map.get("srchwrd"      ))
//                .queryParam("countPerPage" , (String) map.get("countPerPage" ))
//                .queryParam("currentPage"  , (String) map.get("currentPage"  ))
//                .build()
//                .encode()
//                .toUri();
//
//
//
//        responseStr = restTpl.getForObject(uri, String.class);
//
//        // [getForEntity]
//        // ResponseEntity<String> response = restTpl.getForEntity(uri, String.class);
//        // 응답코드 확인
//        // System.out.println(response.getStatusCode().is2xxSuccessful());
//        // responseStr = response.getBody();
//        //log.info(responseStr);
//
//        return responseStr;
//    }

}
