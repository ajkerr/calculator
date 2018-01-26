package ca.andrewkerr.calculator.operators;

public abstract class AbstractOperator {

  private final String symbol;

  public AbstractOperator(final String symbol) {
    this.symbol = symbol;
  }

  /**
   * The symbol used for the operator, e.g. "+", "-", etc.
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * The precedence of the operator. multiplication and division, for example, will have a higher priority than addition and subtraction.
   */
  public abstract int getPrecedence();

  /**
   * Evaluates the operator using the given operands.
   */
  public abstract double evaluate(double operand1, double operand2);
}
