package com.venenium.StatisticsService.Service;
import com.venenium.StatisticsService.Model.Statistics;
import com.venenium.StatisticsService.Repository.StatisticsRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String finalvar = "[";

        for (int j = 0; j < result.size() ; j++) {
            if (j == result.size()-1){
                finalvar += "{\"Date\":";
                finalvar += "\"" + result.get(j).getStart() + "\"" + ",";
                finalvar += "\"Capital\":";
                finalvar += String.valueOf(result.get(j).getActual()) + "}";
            }

            else {
                finalvar += "{\"Date\":";
                finalvar += "\"" + result.get(j).getStart() + "\"" + ",";
                finalvar += "\"Capital\":";
                finalvar += String.valueOf(result.get(j).getActual()) + "},";
            }

        }
        finalvar +="]";

        return finalvar;

    }

    public Statistics addStatistic(Statistics statistics) {
        Statistics newstats = new Statistics();
        newstats.setName(statistics.getName());
        newstats.setStart(statistics.getStart());
        newstats.setActual(statistics.getActual());
        newstats.setUsername(statistics.getUsername());

        return statisticsRepository.save(newstats);
    }

    public List<Statistics> allStatistics() {

        return statisticsRepository.findAll();
    }

    public void delete(String name) {

        statisticsRepository.deleteAllByName(name);
    }

    public void getDataDonut(ArrayList<Statistics> investments) {

        String result = "[{";

        for (int i = 0; i < investments.size(); i++) {
        }
        System.out.println(investments);

    }
}
