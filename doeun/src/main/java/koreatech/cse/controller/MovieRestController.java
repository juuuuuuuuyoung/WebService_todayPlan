package koreatech.cse.controller;

import koreatech.cse.domain.rest.Movie;
import koreatech.cse.repository.AreaMapper;
import koreatech.cse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;

@Controller
@RequestMapping("/movie")
public class MovieRestController {
    @Inject
    MovieService movieService;
    @Inject
    AreaMapper areaMapper;

    @RequestMapping(value = "/result", method=RequestMethod.GET)
    public String findResult(Model model, @RequestParam(required = false, defaultValue="") String targetDt) {
        System.out.println("Movie Result GET");

        ArrayList<Movie> movies = new ArrayList<Movie>();

        // 예시
        String wideArea = areaMapper.findOneFullCD("천안");

        if(targetDt.equals(""))
            targetDt="20170101"; // 초기값을 2017 1월 1일로 지정
        try {
            movies = movieService.readUrl(targetDt, wideArea, "Y", "K");
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("targetDt", targetDt);
        model.addAttribute("movies", movies);
        return "result2";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findMovie(Model model) {
        System.out.println("Find Movie GET");
        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        return "find2";
    }

    @Transactional
    @RequestMapping(value="/find", method = RequestMethod.POST)
    public String findMovie(@ModelAttribute("movie") Movie movie, Model model) {

        System.out.println("Find Movie POST");

        model.addAttribute("targetDt",movie.getTargetDt());

        return "redirect:/movie/result";
    }


}
