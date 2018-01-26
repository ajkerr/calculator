package ca.andrewkerr.calculator.operators;

public class SubtractionOperator extends AbstractOperator {
  public SubtractionOperator() {
    super("-");
  }

  @Override
  public int getPrecedence() {
    return 5;
  }

  @Override
  public double evaluate(final double operand1, final double operand2) {
    return operand1 - operand2;
  }
}
