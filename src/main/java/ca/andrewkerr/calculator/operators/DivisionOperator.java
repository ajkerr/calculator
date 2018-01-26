package ca.andrewkerr.calculator.operators;

public class DivisionOperator extends AbstractOperator {
  public DivisionOperator() {
    super("/");
  }

  @Override
  public int getPrecedence() {
    return 10;
  }

  @Override
  public double evaluate(final double operand1, final double operand2) {
    return operand1 / operand2;
  }
}
