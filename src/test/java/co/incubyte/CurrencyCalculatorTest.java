package co.incubyte;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
public class CurrencyCalculatorTest {
  @Client("/")
  @Inject()
  HttpClient httpClient;

  @Test
  @DisplayName("EMI should be calculated")
  void emi_should_be_calculated() {

    int loanAmount = 200000;
    double interestRate = 3.5;

    int monthlyIncome = 1400;
    Emi emi =
        httpClient
            .toBlocking()
            .retrieve(
                "emi-calculator/30?loanAmount="
                    + loanAmount
                    + "&interestRate="
                    + interestRate
                    + "&monthlyIncome="
                    + monthlyIncome,
                Emi.class);
    int interestPaid = emi.getInterestPaid();

    Assertions.assertThat(interestPaid).isEqualTo(123312);
  }

  @Test
  @DisplayName("should approve loan if emi is less than half of the monthly income")
  void should_approve_loan_if_emi_is_less_than_half_of_the_monthly_income() {
    int loanAmount = 200000;
    double interestRate = 3.5;
    int monthlyIncome = 1800;

    Emi emi =
        httpClient
            .toBlocking()
            .retrieve(
                "emi-calculator/30?loanAmount="
                    + loanAmount
                    + "&interestRate="
                    + interestRate
                    + "&monthlyIncome="
                    + monthlyIncome,
                Emi.class);
    boolean approval = emi.getLoanAproval();
    Assertions.assertThat(approval).isTrue();
  }
}
