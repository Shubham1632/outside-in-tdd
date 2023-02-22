package co.incubyte;

import jakarta.inject.Singleton;

@Singleton
public class EmiService {

  private final EmiCalculatorClient emiCalculatorClient;

  public EmiService(EmiCalculatorClient emiCalculatorClient) {

    this.emiCalculatorClient = emiCalculatorClient;
  }

  public Emi calculate(int loanAmount, double interestRate, int durationYears, int monthlyIncome) {
    Emi fetchedEmi = emiCalculatorClient.fetch(loanAmount, interestRate, durationYears);

    boolean loanEligibility = fetchedEmi.getMonthlyPayment() * 2 < monthlyIncome;
    fetchedEmi.setLoanAproval(loanEligibility);

    return fetchedEmi;
  }
}
