package koreatech.cse.controller.rest;

import koreatech.cse.domain.Searchable;
import koreatech.cse.domain.rest.Festival;
import koreatech.cse.service.festivalService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
//@RestController
@RequestMapping("/festival")
public class FestivalRestController {

    @Inject
    private  festivalService festivalService;

    @RequestMapping("/register")// 정보를 입력할 수 있도록 화면을 보여줌
    public String signup(Model model) {
        //System.out.println("GET RESGISTEr");
        Festival festival = new Festival();//비어있는 festival객체를 만들어서

        model.addAttribute("festival", festival);//화면으로 넘겨줌


        return "register";
    }

    @Transactional
    @RequestMapping(value="/register", method= RequestMethod.POST)  //정보가 실제로 전달되는 곳
    public String findPath(@ModelAttribute Festival festival, Model model) {
        //현재 위치
        //String origin =festival.getLocation();
        //현재 날짜 구하기
        SimpleDateFormat formatter01 = new SimpleDateFormat("yyyyMMdd");
        //현재 날짜를 삽입
        String eventStartDate=formatter01.format(new Date());
        System.out.println("eventStartDate : "+eventStartDate);
        //축제 정보 조회해서 주소 받기
        ArrayList<String> point  = festivalService.findFestivalByDate(eventStartDate);

        Long time = 1000000000L;
        int minNum = -1;

        //축제 좌표 개수인 10개만큼 for문 돌리기
        for(int i = 0; i < point.size(); i++) {
            //축제 좌표 리스트 확인
           // System.out.println(point.get(i));

            //좌표리스트에서 하나씩 넘겨서 소요시간 찾고 제일 작은 거 저장
            Long temp = festivalService.findPathTimeByAddr(point.get(i));
            //System.out.println("temp "+temp);
            if(temp < time) {
                time = temp;
                minNum = i;
            }
        }
        //소요시간 제일 작은거 json 파일 스트링 저장
        String minTimePath = festivalService.findPathByTime(point.get(minNum));
        // 뷰로 리다이렉트해서 json 파일 띄어주기

        //축제 이름은 여기!
        String title = festivalService.findNameByminNum(minNum,eventStartDate);
        System.out.println(title);

        System.out.println(festival.toString()); //json형태로 출력

        //출력을 하고싶은데 못하네요......
        model.addAttribute("path",minTimePath);
        model.addAttribute("name",title);

        return "redirect:/festival/pathResult";
    }

}
