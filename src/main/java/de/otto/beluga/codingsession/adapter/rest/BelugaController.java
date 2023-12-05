package de.otto.beluga.codingsession.adapter.rest;

import de.otto.beluga.codingsession.dto.FizzBuzzRequestDto;
import de.otto.beluga.codingsession.service.BelugaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/beluga")
public class BelugaController {

  private BelugaService belugaService;

  @GetMapping
  public String hello() {
    return "Hello Beluga!";
  }

  @Operation(
          summary = "FizzBuzz REST API",
          description = "Get back a map with the FizzBuzz result game from a start to an end integer"
  )
  @ApiResponse(
          responseCode = "200",
          description = "HTTP Status 200 SUCCESS"
  )
  @PostMapping("fizzbuzz")
  public ResponseEntity<Map<Integer, String>> fizzBuzz(@Valid @RequestBody FizzBuzzRequestDto request){
    return ResponseEntity.ok(belugaService.fizzBuzz(request.getStart(), request.getEnd()));
  }
}
