package co.incubyte;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EmiServiceShould {
  EmiCalculatorClient emiCalculatorClient;
  EmiService emiService;

  @BeforeEach
  public void init() {
    this.emiCalculatorClient = mock(EmiCalculatorClient.class);
    this.emiService = new EmiService(this.emiCalculatorClient);
  }

  @Test
  @DisplayName("call emi client")
  void call_emi_client() {
    int loanAmount = 200000;
    double interestRate = 3.5;
    int monthlyIncome = 800;
    int durationYears = 30;

    Emi mockedEmi = new Emi(123312, 898);
    when(emiCalculatorClient.fetch(loanAmount, interestRate, durationYears)).thenReturn(mockedEmi);

    Emi calculatedEmi =
        emiService.calculate(loanAmount, interestRate, durationYears, monthlyIncome);

    verify(emiCalculatorClient).fetch(loanAmount, interestRate, durationYears);
  }

  @Test
  @DisplayName("should approve loan if emi is less than half of the monthly income")
  void should_approve_loan_if_emi_is_less_than_half_of_the_monthly_income() {
    int loanAmount = 200000;
    double interestRate = 3.5;
    int monthlyIncome = 3000;
    int durationYears = 30;

    Emi mockedEmi = new Emi(123312, 898);
    when(emiCalculatorClient.fetch(loanAmount, interestRate, durationYears)).thenReturn(mockedEmi);

    Emi calculatedEmi =
        emiService.calculate(loanAmount, interestRate, durationYears, monthlyIncome);

    verify(emiCalculatorClient).fetch(loanAmount, interestRate, durationYears);
    Assertions.assertThat(calculatedEmi.getLoanAproval()).isTrue();
  }

  @Test
  @DisplayName("should not approve loan if emi is more than half of the monthly income")
  void should_not_approve_loan_if_emi_is_more_than_half_of_the_monthly_income() {
    int loanAmount = 200000;
    double interestRate = 3.5;
    int monthlyIncome = 800;
    int durationYears = 30;

    Emi mockedEmi = new Emi(123312, 898);
    when(emiCalculatorClient.fetch(loanAmount, interestRate, durationYears)).thenReturn(mockedEmi);

    Emi calculatedEmi =
        emiService.calculate(loanAmount, interestRate, durationYears, monthlyIncome);

    verify(emiCalculatorClient).fetch(loanAmount, interestRate, durationYears);
    Assertions.assertThat(calculatedEmi.getLoanAproval()).isFalse();
  }
}
