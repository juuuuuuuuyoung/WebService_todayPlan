package koreatech.cse.controller.rest;

import koreatech.cse.domain.rest.Today;
import koreatech.cse.repository.TodayMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/thermometer")
public class TodayRestController {
    @Inject
    private TodayMapper todayMapper;

    @Transactional
    @RequestMapping(value="/today/{appKey}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Today> today(@PathVariable("appKey") String appKey) {
        Today today = todayMapper.findOne(appKey);
        if (today == null) {
            System.out.println("Today appKey with  (" + appKey + ") is not found");
            return new ResponseEntity<Today>(HttpStatus.NOT_FOUND);
        }
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

    @Transactional
    @RequestMapping(value = "/today/", method = RequestMethod.POST)
    public ResponseEntity<Void> createToday(@RequestBody Today today, UriComponentsBuilder ucBuilder) {
        if (todayMapper.findOne(today.getAppKey()) != null) {
            System.out.println("A today appKey with appKey (" +
                    today.getAppKey() + ") already exists.\n" +
                    "please recreate appKey");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        todayMapper.insert(today);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                ucBuilder.path("/today/{appKey}").buildAndExpand(today.getAppKey()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(value = "/today/{appKey}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateToday(@PathVariable("appKey") String appKey, @RequestBody Today today) {
        Today storedToday = todayMapper.findOne(appKey);

        if (storedToday == null) {
            System.out.println("No today sensor with id (" + appKey + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        storedToday.setToday(today.getToday());
        storedToday.setLocation(today.getLocation());
        storedToday.setDatetime(today.getDatetime());

        todayMapper.update(storedToday);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/today/{appKey}", method = RequestMethod.DELETE)
    public ResponseEntity<Today> deleteToday(@PathVariable("appKey") String appKey) {
        Today storedToday = todayMapper.findOne(appKey);

        if (storedToday == null) {
            System.out.println("appKey is not valid");
            return new ResponseEntity<Today>(HttpStatus.NOT_FOUND);
        }

        todayMapper.delete(storedToday.getId());

        return new ResponseEntity<Today>(HttpStatus.NO_CONTENT);
    }
}
