package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/")
public class EmiCalculatorController {
  private final EmiService emiService;

  public EmiCalculatorController(EmiService emiService) {
    this.emiService = emiService;
  }

  @Get("emi-calculator/{durationYears}")
  public Emi calculate(
      @QueryValue int loanAmount,
      @QueryValue double interestRate,
      int durationYears,
      int monthlyIncome) {
    return this.emiService.calculate(loanAmount, interestRate, durationYears, monthlyIncome);
  }
}
