package koreatech.cse.controller.rest;

import koreatech.cse.domain.Book;
import koreatech.cse.domain.rest.Festival;
import koreatech.cse.domain.rest.Movie;
import koreatech.cse.domain.rest.Today;
import koreatech.cse.domain.rest.Weather;
import koreatech.cse.repository.AreaMapper;
import koreatech.cse.repository.BookMapper;
import koreatech.cse.service.BookService;
import koreatech.cse.service.FestivalService;
import koreatech.cse.service.MovieService;
import koreatech.cse.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
    private WeatherService weatherService;
    @Inject
    private MovieService movieService;
    @Inject
    private BookService bookService;
    @Inject
    private FestivalService festivalService;


    @Transactional
    @RequestMapping(value="/today", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Today> today2(@RequestParam("location") String location,
                                        @RequestParam(name = "targetDt", required = false) String targetDt,
                                        @RequestParam(name = "multiMovieYn", required = false) String multiMovieYn,
                                        @RequestParam(name="reNationCd", required = false) String repNationCd,
                                        @RequestParam(name="categoryName", required = false, defaultValue = "국내도서") String categoryName) {
        Today today = new Today();


        /** 날씨 */
        Weather weather = new Weather();
        weather.setLocation(location);

        HttpStatus findLocationRequest = weatherService.loadWeatherByArea(weather);

        /** 요청한 주소가 잘못되었을 때 */
        if ( findLocationRequest == HttpStatus.BAD_REQUEST ) {
            System.out.println("404 NOT FOUND");

            return new ResponseEntity<Today>(HttpStatus.BAD_REQUEST);
        }

        today.setWeather(weather);

        /** 영화 */
        ArrayList<Movie> movies;
        String wideArea = areaMapper.findOneFullCD(weather.getAddr_depth1());

        if( targetDt == null || targetDt.equals("") ) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -1);
            targetDt = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
            System.out.println("targetDt : " + targetDt);
        }
        movies = movieService.readUrl(targetDt, wideArea, multiMovieYn, repNationCd);

        today.setMovie(movies);

        //model.addAttribute("targetDt", targetDt);
        //model.addAttribute("movies", movies);

//        /** 주소 혹은 날짜가 제대로 된 것이 아닐 때 */
//        if (today == null) {
//            System.out.println("Today appKey with  (" + location + ") is not found");
//            return new ResponseEntity<Today>(HttpStatus.NOT_FOUND);
//        }

        /** 책 */
        ArrayList<Book> books = new ArrayList<Book>();
        Book book = new Book();
        String categoryId = bookMapper.findCategoryId(categoryName);
        book.setCategoryId(categoryId);
        book.setCategoryName(categoryName);

        books = bookService.loadBestSellerByCategory(book);

        today.setBook(books);


        /** 축제 */
        //현재 위치
        //String origin =festival.getLocation();
        //현재 날짜 구하기
        SimpleDateFormat formatter01 = new SimpleDateFormat("yyyyMMdd");
        //현재 날짜를 삽입
        String eventStartDate=formatter01.format(new Date());
        System.out.println("eventStartDate : "+ eventStartDate);

        //축제 정보 조회해서 주소 받기 -> festival 목록받기
        ArrayList<Festival> festivals  = festivalService.findFestivalByDate(eventStartDate);

        // 축제에서 날 안좋은것 거르기 --> 거르지 않음..
        festivalService.removeBadWeather(festivals);

        Long time = 1000000000L;
        int minNum = -1;


        festivalService.sortByDistance(festivals);



        today.setFestival(festivals);

        //System.out.println(festival.toString()); //json형태로 출력

        //출력을 하고싶은데 못하네요......
        //model.addAttribute("path",minTimePath);
        //model.addAttribute("name",title);

        //return "redirect:/festival/pathResult";


        return new ResponseEntity<Today>(today, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method= RequestMethod.GET)
    public String findLocation(Model model) {
        System.out.println("Find Location GET");
        Weather weather = new Weather();
        model.addAttribute("weather", weather);
        return "find";
    }

    @RequestMapping(value="/find", method= RequestMethod.POST)
    public String findLocation(@ModelAttribute("weather") Weather weather, Model model) {

        System.out.println("Find Location POST");
        System.out.println(weather.toString());

        if(weather.getAddr_depth1() != null || weather.getAddr_depth2() != null || weather.getAddr_depth3() != null) {
            model.addAttribute("province", weather.getAddr_depth1());
            model.addAttribute("city", weather.getAddr_depth2());
            model.addAttribute("region", weather.getAddr_depth3());

            return "redirect:/weather/result/address";
        }

        if (!weather.getLat().equals("") && !weather.getLat().equals("") ) {
            model.addAttribute("lat", weather.getLat());
            model.addAttribute("lon", weather.getLon());

            return "redirect:/today/result/axis";
        }

        return "find";

    }
}
