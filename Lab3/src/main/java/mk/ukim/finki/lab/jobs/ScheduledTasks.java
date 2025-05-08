package mk.ukim.finki.lab.jobs;

import mk.ukim.finki.lab.service.Domain.AccommodationService;
import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTasks {
    private final AccommodationService accommodationService;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    public ScheduledTasks(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void refreshMaterializedView() {

    }
//    @PostConstruct
//    public void refreshViewOnStartup() {
//        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW accommodations_per_host_view;");
//    }

}
