package de.otto.beluga.codingsession.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "FizzBuzz Model Request")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FizzBuzzRequestDto {
    @Schema(description = "Start Number")
    @NotNull(message = "Start number cannot be null")
    @Min(value = 1, message = "Start number must be greater than 0")
    private Integer start;

    @Schema(description = "End Number")
    @NotNull(message = "End number cannot be null")
    @Min(value = 1, message = "End number must be greater than 0")
    private Integer end;
}
