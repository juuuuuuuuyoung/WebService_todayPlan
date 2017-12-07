package koreatech.cse.controller.rest;

import koreatech.cse.domain.rest.Today;
import koreatech.cse.service.TodayService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
@RequestMapping("/today")
public class TodayRestController {


    @RequestMapping("/front")// 정보를 입력할 수 있도록 화면을 보여줌
    public String view(Model model) {

        Today today = new Today();//비어있는 today객체를 만들어서
        model.addAttribute("today", today);//화면으로 넘겨줌
        return "front";
    }
    @Transactional
    @RequestMapping(value = "/front", method = RequestMethod.POST)  //정보가 실제로 전달되는 곳
    public String viewInformation(@ModelAttribute Today today, Model model) {

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type", "application/json;charset=UTF-8");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("http://wscproject2017.us-east-2.elasticbeanstalk.com/todayplan/today")
                .queryParam("location", today.getLocation())
                .queryParam("festSortType",today.getFestSortType())
                .queryParam("total",today.getTotal())
                .queryParam("activityType",today.getActivityType())
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
        JSONObject recommendType = (JSONObject)jsonObj.get("recommend");
        String recommend = (String)recommendType.get("name");
        Long idx = (Long) recommendType.get("index");
        int Idx = Integer.parseInt(String.valueOf(idx));

        System.out.println("recommend : "+recommend);

        if(recommend.equals("festival")) {
            model.addAttribute("bestType","festival");
        }
        else if(recommend.equals("movie")) {
            model.addAttribute("bestType","movie");
        }
        else if(recommend.equals("book")) {
            model.addAttribute("bestType","book");
        }

        //축제
        JSONArray festivalObj = (JSONArray) jsonObj.get("festival");
        JSONObject num3Obj = (JSONObject) festivalObj.get(Idx);
        String festivalTitle = (String) num3Obj.get("name");
        String festivalAddress = (String) num3Obj.get("destAddress");
        //String path = (String) num3Obj.get("legs");
        model.addAttribute("festivalTitle",festivalTitle);
        model.addAttribute("festivalAddress",festivalAddress);
         //model.addAttribute("path",path);

        ArrayList<String> recofestival = new ArrayList<String>();
        ArrayList<String> recofestivalAddress = new ArrayList<String>();

        int searchNum;
        if(today.getTotal().equals("")) {
            searchNum=3;
        }
        else{
            searchNum = Integer.parseInt(today.getTotal());
        }
        model.addAttribute("total",searchNum);
        System.out.println("SearchNum : "+searchNum);
        int i=0;
        for(i=0;i<searchNum;i++){
            System.out.println((String)((JSONObject)festivalObj.get(i)).get("name"));
            recofestival.add((String)((JSONObject)festivalObj.get(i)).get("name")) ;
            recofestivalAddress.add((String) ((JSONObject)festivalObj.get(i)).get("destAddress"));
        }
        model.addAttribute("recommendFestival",recofestival);
        model.addAttribute("recommendFestivalAddress",recofestivalAddress);

        //movie
        JSONArray movieObj = (JSONArray) jsonObj.get("movie");
        JSONObject numObj = (JSONObject) movieObj.get(Idx);
        String movieName = (String) numObj.get("movieNm");
        String openDt = (String)numObj.get("openDt") ;
        model.addAttribute("movieName",movieName);
        model.addAttribute("openDt",openDt);

        //book
        JSONArray bookObj = (JSONArray) jsonObj.get("book");
        JSONObject num2Obj = (JSONObject) bookObj.get(Idx);
        String bookName = (String) num2Obj.get("title");
        String bookAuthor = (String) num2Obj.get("author");
        String bookDescription = (String) num2Obj.get("description");
        Long bookPrice = (Long)num2Obj.get("price");
        model.addAttribute("bookName",bookName);
        model.addAttribute("bookAuthor",bookAuthor);
        model.addAttribute("bookDescription",bookDescription);
        model.addAttribute("bookPrice",bookPrice);

        JSONObject subObj = (JSONObject) jsonObj.get("weather");
        String weather = (String) subObj.get("sky"); //눈, 비
        String dust = (String) subObj.get("dustGrade"); //좋음 나쁨
        String lon = (String) subObj.get("lon"); //lon
        String lat = (String) subObj.get("lat");//lat
        String location = (String) subObj.get("location");//lat


        model.addAttribute("weather",weather);
        model.addAttribute("dust",dust);
        model.addAttribute("result",result);
        model.addAttribute("lat",lat);
        model.addAttribute("lon",lon);
        model.addAttribute("address",location);


         return "main";
    }


}