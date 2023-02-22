package co.incubyte;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://mocki.io/v1/e281557b-53b1-4bfa-aa09-ccbab5eccfad")
public interface EmiCalculatorClient {

  @Get
  Emi fetch(int loanAmount, double interestRate, int durationYears);
}
