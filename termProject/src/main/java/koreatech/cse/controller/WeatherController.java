package koreatech.cse.controller;

import koreatech.cse.domain.rest.Weather;
import koreatech.cse.repository.WeatherMapper;
import koreatech.cse.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.logging.Logger;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    @Inject
    private WeatherMapper weatherMapper;

    @Inject
    private WeatherService weatherService;

    @RequestMapping(value = "/result/address",  method= RequestMethod.GET)
    public String findResultByAddress(Model model, @RequestParam(required = false, defaultValue = "") String province, @RequestParam(required = false, defaultValue = "") String city, @RequestParam(required = false, defaultValue = "") String region) {
        System.out.println("Location Result GET");

        Weather weather = new Weather();
        if (province.equals("") && city.equals("") && region.equals(""))
            city="서울";
        weather.setLocation(province+city+region);

        weatherService.loadWeatherByArea(weather);

        System.out.println("@Controller@");
        System.out.println(weather.getLon());
        System.out.println(weather.getLocation());
        System.out.println(weather.getSky());
        System.out.println(weather.getDustValue());
        System.out.println(weather.getDustGrade());

        //model.addAttribute("weather", weather);
        model.addAttribute("location", weather.getLocation());
        model.addAttribute("sky", weather.getSky());
        model.addAttribute("tmin", weather.getTmin());
        model.addAttribute("tmax", weather.getTmax());
        model.addAttribute("dustValue", weather.getDustValue());
        model.addAttribute("dustGrade", weather.getDustGrade());
        //model.addAttribute("recommend", !weather.getSky().equals("비 옴"))

        return "result";
    }

    @RequestMapping(value = "/result/axis",  method= RequestMethod.GET)
    public String findResultByAxis(Model model, @RequestParam String lat, @RequestParam String lon) {
        System.out.println("Location Result GET2");

        Weather weather = new Weather();
        weather.setLat(lat);
        weather.setLon(lon);

        weatherService.loadWeatherByAxis(weather);

        System.out.println("@Controller@");
        System.out.println(weather.getLon());
        System.out.println(weather.getLocation());
        System.out.println(weather.getSky());
        System.out.println(weather.getDustValue());
        System.out.println(weather.getDustGrade());

        model.addAttribute("weather", weather);
        model.addAttribute("location", weather.getLocation());
        model.addAttribute("sky", weather.getSky());
        model.addAttribute("tmin", weather.getTmin());
        model.addAttribute("tmax", weather.getTmax());
        model.addAttribute("dustValue", weather.getDustValue());
        model.addAttribute("dustGrade", weather.getDustGrade());

        return "redirect:/result";
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

        if(weather.getProvince() != null || weather.getCity() != null || weather.getProvince() != null) {
            model.addAttribute("province", weather.getProvince());
            model.addAttribute("city", weather.getCity());
            model.addAttribute("region", weather.getRegion());

            return "redirect:/weather/result/address";
        }

        if (!weather.getLat().equals("") && !weather.getLat().equals("") ) {
            model.addAttribute("lat", weather.getLat());
            model.addAttribute("lon", weather.getLon());

            return "redirect:/weather/result/axis";
        }

        return "find";

    }




}
