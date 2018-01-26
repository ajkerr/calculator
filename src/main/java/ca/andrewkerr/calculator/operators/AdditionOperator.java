package ca.andrewkerr.calculator.operators;

public class AdditionOperator extends AbstractOperator {
  public AdditionOperator() {
    super("+");
  }

  @Override
  public int getPrecedence() {
    return 5;
  }

  @Override
  public double evaluate(final double operand1, final double operand2) {
    return operand1 + operand2;
  }
}
