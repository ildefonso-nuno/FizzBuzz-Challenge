package de.otto.beluga.codingsession.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FizzBuzzRequestDtoTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testNullStart() {
        FizzBuzzRequestDto dto = new FizzBuzzRequestDto(null, 10);
        Set<ConstraintViolation<FizzBuzzRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Start number cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void testNullEnd() {
        FizzBuzzRequestDto dto = new FizzBuzzRequestDto(1, null);
        Set<ConstraintViolation<FizzBuzzRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("End number cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void testNegativeStart() {
        FizzBuzzRequestDto dto = new FizzBuzzRequestDto(-1, 10);
        Set<ConstraintViolation<FizzBuzzRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Start number must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    void testNegativeEnd() {
        FizzBuzzRequestDto dto = new FizzBuzzRequestDto(1, -1);
        Set<ConstraintViolation<FizzBuzzRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("End number must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    void testValidDto() {
        FizzBuzzRequestDto dto = new FizzBuzzRequestDto(1, 10);
        Set<ConstraintViolation<FizzBuzzRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }
}
