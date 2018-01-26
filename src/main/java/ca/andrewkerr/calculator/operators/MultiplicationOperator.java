package ca.andrewkerr.calculator.operators;

public class MultiplicationOperator extends AbstractOperator {
  public MultiplicationOperator() {
    super("*");
  }

  @Override
  public int getPrecedence() {
    return 10;
  }

  @Override
  public double evaluate(final double operand1, final double operand2) {
    return operand1 * operand2;
  }
}
