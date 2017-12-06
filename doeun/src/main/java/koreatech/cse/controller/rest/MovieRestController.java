package koreatech.cse.controller.rest;

import koreatech.cse.domain.rest.Movie;
import koreatech.cse.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.logging.Logger;

@Controller
@RequestMapping("/movie")
public class MovieRestController {
    @Inject
    MovieService movieService;
    //Logger logger = Logger.getLogger(this.getClass().getName());

    @RequestMapping(value = "/result", method=RequestMethod.GET)
    public String findResult(Model model, @RequestParam(required = false, defaultValue="") String targetDt) {
        System.out.println("Movie Result GET");
        Movie movie = new Movie();
        if(targetDt.equals(""))
            targetDt="20170101";
        movie.setTargetDt(targetDt);
        try {
            movieService.readUrl(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("list", movie.getTargetDt());
        return "maps";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findMovie(Model model) {
        System.out.println("Find Movie GET");
        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        return "find";
    }

    @Transactional
    @RequestMapping(value="/find", method = RequestMethod.POST)
    public String findMovie(@ModelAttribute("movie") Movie movie, Model model) {

        System.out.println("Find Movie POST");

        model.addAttribute("targetDt",movie.getTargetDt());

        return "redirect:/movie/result";
    }


}
