package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Emi {

  private final int interestPaid;
  private boolean loanAproval;
  private final int monthlyPayment;

  public Emi(
      @JsonProperty("total_interest_paid") int interestPaid,
      @JsonProperty("total") int monthlyPayment) {
    this.interestPaid = interestPaid;
    this.monthlyPayment = monthlyPayment;
  }

  public int getInterestPaid() {
    return interestPaid;
  }

  public boolean getLoanAproval() {
    return loanAproval;
  }

  public void setLoanAproval(boolean loanAproval) {
    this.loanAproval = loanAproval;
  }

  public int getMonthlyPayment() {
    return monthlyPayment;
  }
}
