package de.otto.beluga.codingsession.service.impl;

import de.otto.beluga.codingsession.dto.FizzBuzzRequestDto;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BelugaServiceImplTest {

    @Test
    public void test1ValueFizzBuzz() {
        BelugaServiceImpl solver = new BelugaServiceImpl();
        Integer start = 1;
        Integer end = 1;
        Map<Integer, String> expectedResult = new HashMap<>();
        expectedResult.put(1,"1");
        Map<Integer, String> result = solver.fizzBuzz(start,end);
        Assertions.assertEquals(expectedResult, result, "The fizzBuzz method did not return the expected result.");
    }

    @Test
    public void test6ValuesFizzBuzz() {
        BelugaServiceImpl solver = new BelugaServiceImpl();
        Integer start = 1;
        Integer end = 6;
        Map<Integer, String> expectedResult = new HashMap<>();
        expectedResult.put(1,"1");
        expectedResult.put(2,"2");
        expectedResult.put(3,"FIZZ");
        expectedResult.put(4,"4");
        expectedResult.put(5,"BUZZ");
        expectedResult.put(6,"FIZZ");
        Map<Integer, String> result = solver.fizzBuzz(start,end);
        Assertions.assertEquals(expectedResult, result, "The fizzBuzz method did not return the expected result.");
    }

    @Test
    public void test15ValuesFizzBuzz() {
        BelugaServiceImpl solver = new BelugaServiceImpl();
        Integer start = 1;
        Integer end = 15;
        Map<Integer, String> expectedResult = new HashMap<>();
        expectedResult.put(1,"1");
        expectedResult.put(2,"2");
        expectedResult.put(3,"FIZZ");
        expectedResult.put(4,"4");
        expectedResult.put(5,"BUZZ");
        expectedResult.put(6,"FIZZ");
        expectedResult.put(7,"7");
        expectedResult.put(8,"8");
        expectedResult.put(9,"FIZZ");
        expectedResult.put(10,"BUZZ");
        expectedResult.put(11,"11");
        expectedResult.put(12,"FIZZ");
        expectedResult.put(13,"13");
        expectedResult.put(14,"14");
        expectedResult.put(15,"FIZZBUZZ");
        Map<Integer, String> result = solver.fizzBuzz(start,end);
        Assertions.assertEquals(expectedResult, result, "The fizzBuzz method did not return the expected result.");
    }

}
