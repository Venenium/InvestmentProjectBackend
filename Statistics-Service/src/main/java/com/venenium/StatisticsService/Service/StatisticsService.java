package com.venenium.StatisticsService.Service;

import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public String getDataForOne(String investmentName, String clientUsername) {

        List<Statistics> result = statisticsRepository.findByNameAndUsername(investmentName, clientUsername);
        String finalvar = "";

        for (int j = 0; j < result.size() ; j++) {

            finalvar += "{name:";
            finalvar += result.get(j).getName() + ",";
            finalvar += "Capital";
            finalvar += String.valueOf(result.get(j).getActual()) + "},";
        }

        return finalvar;

    }

    public Statistics addStatistic(Statistics statistics) {
        Statistics newstats = new Statistics();
        newstats.setName(statistics.getName());
        newstats.setDate(statistics.getDate());
        newstats.setActual(statistics.getActual());

        if (statistics.getUsername() == "" || statistics.getUsername() == null)
            newstats.setUsername("venenium");

        return statisticsRepository.save(newstats);
    }

    public List<Statistics> allStatistics() {

        return statisticsRepository.findAll();
    }
}
