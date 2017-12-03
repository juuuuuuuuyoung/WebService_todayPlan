package koreatech.cse.controller.rest;

import koreatech.cse.domain.Book;
import koreatech.cse.domain.rest.Movie;
import koreatech.cse.domain.rest.Today;
import koreatech.cse.domain.rest.Weather;
import koreatech.cse.repository.AreaMapper;
import koreatech.cse.repository.BookMapper;
import koreatech.cse.service.BookService;
import koreatech.cse.service.MovieService;
import koreatech.cse.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

    // {location} 위치의 {targetDt}의 날짜의 영화 가져오기 --
    @Transactional
    @RequestMapping(value="/today", method= RequestMethod.GET, produces = "application/json")
    //public ResponseEntity<Today> today2(@PathVariable("location") String location, @PathVariable("targetDt") String targetDt) {
    public ResponseEntity<Today> today2(@RequestParam("location") String location, @RequestParam(name = "targetDt") String targetDt,
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

        if(targetDt.equals("")) {
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

        return new ResponseEntity<Today>(today, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value="/today/location/{location}", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Today>> todayByLocation(
            @PathVariable("location") String location) {
        ArrayList<Today> todayList = new ArrayList<Today>();
        //List<Today> todayList = todayMapper.findByLocation(location);

        if (todayList.size() == 0) {
            System.out.println("Today sensors with location of " + location + " are not found");
            return new ResponseEntity<List<Today>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Today>>(todayList, HttpStatus.OK);
    }

//    @Transactional
//    @RequestMapping(value = "/today/", method = RequestMethod.POST)
//    public ResponseEntity<Void> createToday(@RequestBody String userName, UriComponentsBuilder ucBuilder) {
//        if (todayMapper.findOne(userName) != null) {
//            System.out.println("A today appKey with userName (" +
//                    userName + ") already exists.\n");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
//
//        Today today = new Today();
//        today.setUserName(userName);
//        todayService.makeAPIKey(today);
//
//        todayMapper.insert(today);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(
//                ucBuilder.path("/today/{appKey}").buildAndExpand(today.getAppKey()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }
//
//    @Transactional
//    @RequestMapping(value = "/today/{appKey}", method = RequestMethod.PUT)
//    public ResponseEntity<Void> updateToday(@PathVariable("appKey") String appKey, @RequestBody Today today) {
//        Today storedToday = todayMapper.findOne(appKey);
//
//        if (storedToday == null) {
//            System.out.println("No today sensor with id (" + appKey + " not found");
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
//
////        storedToday.setToday(today.getToday());
////        storedToday.setLocation(today.getLocation());
////        storedToday.setDatetime(today.getDatetime());
////
////        todayMapper.update(storedToday);
//
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }
//
//    @Transactional
//    @RequestMapping(value = "/today/{appKey}", method = RequestMethod.DELETE)
//    public ResponseEntity<Today> deleteToday(@PathVariable("appKey") String appKey) {
//        Today storedToday = todayMapper.findOne(appKey);
//
//        if (storedToday == null) {
//            System.out.println("appKey is not valid");
//            return new ResponseEntity<Today>(HttpStatus.NOT_FOUND);
//        }
//
//        todayMapper.delete(storedToday.getId());
//
//        return new ResponseEntity<Today>(HttpStatus.NO_CONTENT);
//    }
}
