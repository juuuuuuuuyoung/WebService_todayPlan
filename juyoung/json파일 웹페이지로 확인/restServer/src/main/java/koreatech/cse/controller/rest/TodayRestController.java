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

@Controller
@RequestMapping("/today")
public class TodayRestController {

    @Inject
    private TodayService todayService;

    @RequestMapping("/front")// 정보를 입력할 수 있도록 화면을 보여줌
    public String view(Model model) {

        Today today = new Today();//비어있는 today객체를 만들어서
        model.addAttribute("today", today);//화면으로 넘겨줌
        return "front";
    }
    @Transactional
    @RequestMapping(value = "/front", method = RequestMethod.POST)  //정보가 실제로 전달되는 곳
    public String viewInformation(@ModelAttribute Today today, Model model) {
        System.out.println(today.getSearchType());

        String weather = "좋음";
        String dust = "나쁨";
        String recommend = "book";

        //model.addAttribute("dust", "나쁨");
        model.addAttribute(weather);
        model.addAttribute(dust);
        model.addAttribute(recommend);

        // return "main";
        return "redirect:/today/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String viewSometing(Model model, @RequestParam(required = false, defaultValue = "") String weather, @RequestParam(required = false, defaultValue = "") String dust, @RequestParam(required = false, defaultValue = "") String recommend) {
        //model.addAttribute("weather","good");
        //model.addAttribute("dust","good");
        //model.addAttribute("recommend","book");
        Today today = new Today();

        model.addAttribute(today);
        model.addAttribute("weather","구름");
        model.addAttribute("dust","보통");
        model.addAttribute("recommend","festival");
        model.addAttribute("address","천안시 동남구 병천면 가전리");
        model.addAttribute("lat","36.765371699999996");
        model.addAttribute("lon","127.28594249999999");

        return "/main";
    }
    /* @Transactional
     @RequestMapping(value="/front", method= RequestMethod.POST)  //정보가 실제로 전달되는 곳
     public String viewInformation(@ModelAttribute Today today, Model model) {
         System.out.println(today.getSearchType());
         //todayService.findToday(today.getLocation(),today.getSearchNumber(),today.getSearchType(),today.getActivity());


         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-Type", "application/json;charset=UTF-8");
         HttpEntity request = new HttpEntity(headers);
         ResponseEntity<String> response = null;
         RestTemplate rest = new RestTemplate();
         URI url = UriComponentsBuilder.fromUriString("https://localhost:8080/today/main/json?")
                 .queryParam("location", today.getLocation())
                 .queryParam("searchNumber", today.getSearchNumber())
                 .queryParam("searachType", today.getSearchType())
                 .queryParam("activity", today.getActivity())
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


         model.addAttribute(weather);
         model.addAttribute(dust);
         model.addAttribute(recommend);

         return "main";
     }
 */

}