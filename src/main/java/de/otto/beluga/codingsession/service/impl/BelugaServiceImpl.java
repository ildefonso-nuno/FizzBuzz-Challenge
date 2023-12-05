package de.otto.beluga.codingsession.service.impl;

import de.otto.beluga.codingsession.exception.StartLargerThanEndException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class BelugaServiceImpl implements de.otto.beluga.codingsession.service.BelugaService {
    @Override
    public Map<Integer, String> fizzBuzz(Integer start, Integer end) {

        // validate that start is not larger than end
        if(start > end) throw new StartLargerThanEndException();

        Map<Integer, String> result = new HashMap<>();

        //FizzBuzz implementation
        for(int i = start; i<=end; i++) {
            if(i%15==0) {
                result.put(i,"FIZZBUZZ");
            } else if(i%3==0) {
                result.put(i,"FIZZ");
            } else if(i%5==0) {
                result.put(i,"BUZZ");
            } else {
                result.put(i,Integer.toString(i));
            }
        }

        return result;
    }
}
