package koreatech.cse.controller.rest;

import koreatech.cse.domain.Book;
import koreatech.cse.domain.rest.Festival;
import koreatech.cse.domain.rest.Movie;
import koreatech.cse.domain.rest.Today;
import koreatech.cse.domain.rest.Weather;
import koreatech.cse.repository.AreaMapper;
import koreatech.cse.repository.BookMapper;
import koreatech.cse.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/todayPlan")
public class TodayRestController {
    @Inject
    private AreaMapper areaMapper;
    @Inject
    private BookMapper bookMapper;
    @Inject
    private TodayService todayService;


    @Transactional
    @RequestMapping(value="/today", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Today> today2(@RequestParam("location") String location,
                                        @RequestParam(name = "activityType", required = false, defaultValue = "0") String activityType,
                                        
                                        @RequestParam(name="festSortType",required = false,defaultValue = "0") String festSortType,
                                        @RequestParam(name = "total", required = false, defaultValue = "3") int total)  {

        Today today = new Today();
        today.setResponse("성공");

        /** 날씨 */
        Weather weather = new Weather();
        HttpStatus findLocationRequest = todayService.doWeatherService(location, weather);

        /** 요청한 주소가 잘못되었을 때 */
        if ( findLocationRequest == HttpStatus.BAD_REQUEST ) {
            today.setResponse("위치 파라미터요청이 잘못되었습니다.");
            return new ResponseEntity<Today>(HttpStatus.BAD_REQUEST);
        }
        today.setWeather(weather);

        /**영화*/
        ArrayList<Movie> movies;
        String wideArea = areaMapper.findOneFullCD(weather.getAddr_depth1());
        movies = todayService.doMovieService(wideArea, total);

        /**책*/
        ArrayList<Book> books;
        //String categoryId = bookMapper.findCategoryId(categoryName);
//        if ( categoryId == null ) {
//            today.setResponse("책 카테고리 파라미터요청이 잘못되었습니다.");
//            return new ResponseEntity<Today>(HttpStatus.BAD_REQUEST);
//        }

        books = todayService.doBookService(total);
        today.setBook(books);

        /** 축제 */
        ArrayList<Festival> festivals;
        String lat = weather.getLat();
        String lon = weather.getLon();
        ArrayList<Integer> idxArray;
        festivals = todayService.doFestivalService(lat, lon, festSortType, total);
        idxArray = todayService.sortFestivalIdxByWeather(festivals);

        System.out.println("!!!!!!!!!");
        System.out.println(idxArray);

        /** activityType: 0-movie, book / 1- movie / 2-book */

        if(activityType.equals("0")) {
            today.setMovie(movies);
            today.setBook(books);
        } else if(activityType.equals("1")) {
            today.setMovie(movies);
        } else if(activityType.equals("2")) {
            today.setBook(books);
        } else {
            today.setResponse("실패");
            return new ResponseEntity<Today>(today, HttpStatus.BAD_REQUEST);
        }

        Map<String, Integer> elem = new HashMap<String, Integer>();

        if (festivals.get(idxArray.get(0)).getRecommend().equals("추천")) {
            elem.put("festival", idxArray.get(0));
        } else {
            Random random = new Random();
            int num = random.nextInt(2);
            System.out.println("$$$"+num);
            if (num == 0)
                elem.put("movie", idxArray.get(0));
            else
                elem.put("book", idxArray.get(0));
        }
        today.setRecommend(elem);
        today.setFestival(festivals);

        return new ResponseEntity<Today>(today, HttpStatus.OK);
    }

}
