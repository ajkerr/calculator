package ca.andrewkerr.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import ca.andrewkerr.calculator.operators.AbstractOperator;
import ca.andrewkerr.calculator.operators.AdditionOperator;
import ca.andrewkerr.calculator.operators.DivisionOperator;
import ca.andrewkerr.calculator.operators.MultiplicationOperator;
import ca.andrewkerr.calculator.operators.SubtractionOperator;

public class Calculator {

  /** Default supported operators that work out of the box */
  private static final List<AbstractOperator> DEFAULT_OPERATORS = Arrays.asList(
     new AdditionOperator(),
     new SubtractionOperator(),
     new MultiplicationOperator(),
     new DivisionOperator());

  /**
   * Operators supported by this instance, keyed by operator symbol.
   */
  private Map<String, AbstractOperator> operators;

  /**
   * Default constructor. The calculator will support the default operators.
   */
  public Calculator() {
    initializeOperators(DEFAULT_OPERATORS);
  }

  /**
   * Use this constructor to add additional custom operators to the calculator.
   */
  public Calculator(final List<AbstractOperator> customOperators) {
    final List<AbstractOperator> supportedOperators = new ArrayList<>(DEFAULT_OPERATORS);
    supportedOperators.addAll(customOperators);
    initializeOperators(supportedOperators);
  }

  /**
   * Evaluates a simple binary operation
   */
  public double evaluateBinaryOperation(final double operand1, final String operatorSymbol, final double operand2) {
    if (!operators.containsKey(operatorSymbol)) {
      throw new IllegalArgumentException("Unsupported operator: " + operatorSymbol);
    }
    return operators.get(operatorSymbol).evaluate(operand1, operand2);
  }

  /**
   * Evaluates an arithemetic expression
   */
  public double evaluateExpression(final String expression) {
    if (expression == null || "".equals(expression)) {
      return 0;
    }

    // We make a bold assumption here that all items in the expression are separated by whitespace.
    // So 3 + 5 * 2 is good, but 3+5*2 isn't.
    final List<String> tokens = Arrays.asList(expression.split(("\\s+")));
    System.out.println(tokens.size());

    // The most efficient solution is to convert the infix expression to postfix (reverse polish notation),
    // and then evaluate the expression from that. This should give an O(n) solution.
    // Could also create a tree of binary expressions such that higher precedence operators are evaluated first.

    final Stack<Double> numbers = new Stack<>();
    final Stack<String> operators = new Stack<>();

    // In our simple grammar, tokens should alternate between numbers and operators.
    for (int i = tokens.size() - 1; i >= 0; --i) {
      if (i % 2 == 0) {
        // This should be a number.
        numbers.push(Double.parseDouble(tokens.get(i)));
      } else {
        operators.push(tokens.get(i));
      }
    }

    double result = 0;

    // This totally ignores operator precedence, and just evaluates left to right. :-(
    while (!numbers.isEmpty()) {
      if (numbers.size() == 1) {
        result = numbers.pop();
        break;
      }
      final double operand1 = numbers.pop();
      final String operator = operators.pop();
      final double operand2 = numbers.pop();
      numbers.push(evaluateBinaryOperation(operand1, operator, operand2));
    }

    return result;
  }

  private void initializeOperators(final List<AbstractOperator> supportedOperators) {
    // Convert the list of operators into a map keyed by operator symbol.
    operators = supportedOperators.stream().collect(Collectors.toMap(AbstractOperator::getSymbol, Function.identity()));
  }
}
