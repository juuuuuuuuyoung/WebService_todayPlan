package koreatech.cse.service;

import koreatech.cse.domain.rest.Festival;
import koreatech.cse.domain.rest.Path;
import koreatech.cse.domain.rest.Weather;
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


import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class FestivalService {

    @Inject
    WeatherService weatherService;

    //축제 좌표 리스트 반환 함수
    public ArrayList<Festival> findFestivalByDate(String eventStartDate){

        ArrayList<Festival> festivals = new ArrayList<Festival>();

        String serviceKey = "l%2FXKLcW5Jj0okWYGuXHBNNOAAmPof7oCXymC6dSQupnglgZ6ePTSmrNu5y3g7NuA6QsToKdDZuL38FkjoPgZyw%3D%3D";
        String serviceKey_Decoder = null;
        try {
            serviceKey_Decoder = URLDecoder.decode(serviceKey.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url = null;
        URLConnection connection = null;
        Document doc = null;
        try {
            url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey=" + serviceKey_Decoder
                    + "&arrange=A&listYN=Y&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest"
                    + "&eventStartDate=" + eventStartDate);
            connection = url.openConnection();
            doc = parseXML(connection.getInputStream());
        } catch (Exception e) {}

        NodeList descNodes = doc.getElementsByTagName("item");

        for(int i=0; i<descNodes.getLength();i++){
            Festival festival = new Festival();
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
                String tagName = node.getNodeName();

                if(tagName.equals("addr1")){
                    System.out.println("addr1 : " + node.getTextContent());
                    festival.setDestAddress(node.getTextContent());
                }else if(tagName.equals("addr2")){
                    festival.setDestAddress(festival.getDestAddress() + node.getTextContent());
                }else if(tagName.equals("firstimage")){
                    festival.setImgUrl(node.getTextContent());
                } else if(tagName.equals("mapx")){
                    festival.setMapX(node.getTextContent());
                } else if(tagName.equals("mapy")){
                    festival.setMapY(node.getTextContent());
                    festival.setDestLocation( festival.getMapY()+","+festival.getMapX());
                    System.out.println(festival.getDestLocation());
                } else if(tagName.equals("title")) {
                    System.out.println("title : " + node.getTextContent());
                    festival.setName(node.getTextContent());
                }
            }
            System.out.println("===============");
            festivals.add(festival);
        }
        return festivals;
    }


    public ArrayList<Festival> removeBadWeather(ArrayList<Festival> festivals) {

        for(int i=0; i<festivals.size(); i++) {
            String lat = festivals.get(i).getMapY();
            String lon = festivals.get(i).getMapX();
            Weather weather = new Weather();
            weather.setLat(lat);
            weather.setLon(lon);
            weatherService.loadWeatherByAxis(weather);

            if ( weather.getSky().equals("비") ) {
                festivals.get(i).setRecommend("비가 와서 추천드리지 않아요");
            } else if(weather.getSky().equals("눈")) {
                festivals.get(i).setRecommend("눈이 와서 추천드리지 않아요");
            } else if(weather.getSky().equals("비 또는 눈")) {
                festivals.get(i).setRecommend("비 또는 눈이 와서 추천드리지 않아요");
            } else {
                if (weather.getDustGrade().equals("나쁨") || weather.getDustGrade().equals("매우나쁨")) {
                    festivals.get(i).setRecommend("미세먼지로 외부활동을 추천하지는 않아요");
                } else {
                    festivals.get(i).setRecommend("추천 관광지");
                }
            }

            festivals.get(i).setSky(weather.getSky());
            festivals.get(i).setDustValue(weather.getDustValue());
            festivals.get(i).setDustGrade(weather.getDustGrade());
        }

        return festivals;
    }
    
    public void sortByDistance(ArrayList<Festival> festivals,String origin, String sortType) {
        /** 소요시간 및 정보 가져오기 **/
        for(int i=0; i<festivals.size(); i++) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> response = null;
            RestTemplate rest = new RestTemplate();
            URI url = UriComponentsBuilder.fromUriString("https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyAY8nrL5q2WEN5L1mr6nyeC1NwJ5Va0W2Q&mode=transit")
                    .queryParam("destination", festivals.get(i).getDestLocation())
                    .queryParam("origin", origin)
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
            ;

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject) jsonParser.parse(result);
            } catch (Exception e) {
            }

            JSONArray subObj = (JSONArray) jsonObj.get("routes");
            JSONObject subObj1 = (JSONObject) subObj.get(0);

            JSONArray legsObj = (JSONArray) subObj1.get("legs");
            JSONObject pathObj = (JSONObject) legsObj.get(0);
            JSONObject durationObj = (JSONObject) pathObj.get("duration");
            Long duration = (Long) durationObj.get("value");
            festivals.get(i).setDuration(duration);

            JSONObject arrivalTimeObj = (JSONObject) pathObj.get("arrival_time");
            Long arrivalTime = (Long) arrivalTimeObj.get("value");
            festivals.get(i).setArrivalTime(arrivalTime);

            JSONObject departureTimeObj = (JSONObject) pathObj.get("departure_time");
            String departureTime = (String) departureTimeObj.get("text");
            festivals.get(i).setDepartureTime(departureTime);

            festivals.get(i).setSortType(sortType);
        }

        /** 소요시간 소팅 **/
        Collections.sort(festivals);
    }

    private Document parseXML(InputStream stream) throws Exception{

        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;

        try{

            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);

        }catch(Exception ex){
            throw ex;
        }

        return doc;
    }
}
