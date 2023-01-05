package ru.elerphore.kte.web.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elerphore.kte.data.statistic.StatisticRequest;
import ru.elerphore.kte.data.statistic.StatisticResponse;
import ru.elerphore.kte.services.statistic.OnlyOneIdentifierAvailableException;
import ru.elerphore.kte.services.statistic.StatisticService;

import javax.jws.WebService;

@WebService(serviceName = "StatisticEndpoint", portName = "StatisticPort", targetNamespace = "http://kte.assigment.application", endpointInterface = "ru.elerphore.kte.web.statistic.StatisticEndpointInterface")
@RestController
@RequestMapping(path = "/rest/statistic")
public class StatisticEndpoint implements StatisticEndpointInterface {

    private StatisticService statisticService;

    public StatisticEndpoint() { }

    @Autowired
    public StatisticEndpoint(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public StatisticResponse getStatistic(StatisticRequest statisticRequest) throws OnlyOneIdentifierAvailableException {
        return statisticService.getStatistic(statisticRequest);
    }
}
