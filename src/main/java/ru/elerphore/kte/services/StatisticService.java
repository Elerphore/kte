package ru.elerphore.kte.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    @Scheduled(fixedRate = 360000)
    void statistics() {

    }
}
