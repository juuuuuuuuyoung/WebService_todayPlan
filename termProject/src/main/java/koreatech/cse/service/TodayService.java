package koreatech.cse.service;

import koreatech.cse.domain.rest.Today;
import koreatech.cse.repository.TodayMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service
public class TodayService {
    @Inject
    TodayMapper todayMapper;

    public void makeAPIKey() {
        Today today = new Today();
        UUID uid = UUID.randomUUID();
        today.setAppKey(uid.toString());
        todayMapper.insert();
    }

}
