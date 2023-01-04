package ru.elerphore.kte.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService {
    String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String orderNumberPrefix;
    Integer orderNumberCounterLastValue = -1;


    public String generateOrderNumber() {
        orderNumberCounterLastValue++;

        return orderNumberPrefix + "000" + orderNumberCounterLastValue.toString();
    }

    private char getRandomChar() {
        Random random = new Random();
        int index = random.nextInt(allowedChars.length());
        return allowedChars.charAt(index);
    }

    @PostConstruct
    private void generateRandomPrefix() {
        orderNumberPrefix = String.join("", Arrays.asList(1, 2).stream().map(n -> "" + getRandomChar()).collect(Collectors.toList()));
    }

}
