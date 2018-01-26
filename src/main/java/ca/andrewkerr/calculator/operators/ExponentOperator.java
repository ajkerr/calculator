package ca.andrewkerr.calculator.operators;

public class ExponentOperator extends AbstractOperator {
  public ExponentOperator() {
    super("^");
  }

  @Override
  public int getPrecedence() {
    return 15;
  }

  @Override
  public double evaluate(final double operand1, final double operand2) {
    return Math.pow(operand1, operand2);
  }
}
