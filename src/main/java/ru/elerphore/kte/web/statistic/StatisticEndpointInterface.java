package ru.elerphore.kte.web.statistic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.elerphore.kte.data.statistic.StatisticRequest;
import ru.elerphore.kte.data.statistic.StatisticResponse;
import ru.elerphore.kte.services.statistic.OnlyOneIdentifierAvailableException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Statistic", targetNamespace = "http://kte.assigment.application")
public interface StatisticEndpointInterface {
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "StatisticRequest")
    @ResponseWrapper(className = "StatisticResponse")
    @GetMapping
    StatisticResponse getStatistic(@WebParam(name = "statisticRequest", targetNamespace = "http://kte.assigment.application") @RequestBody StatisticRequest statisticRequest) throws OnlyOneIdentifierAvailableException;
}
