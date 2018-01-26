package ca.andrewkerr.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.andrewkerr.calculator.operators.AbstractOperator;
import ca.andrewkerr.calculator.operators.ExponentOperator;

class CalculatorTest {

  // First, test the basics of binary operations.

  @Test
  void testBinaryAddition() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateBinaryOperation(2, "+", 3);
    assertEquals(5, result);
  }

  @Test
  void testBinarySubstraction() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateBinaryOperation(2, "-", 3);
    assertEquals(-1, result);
  }

  @Test
  void testBinaryMultiplication() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateBinaryOperation(2, "*", 3);
    assertEquals(6, result);
  }

  @Test
  void testBinaryDivision() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateBinaryOperation(6, "/", 3);
    assertEquals(2, result);
  }

  @Test
  void testBinaryDivisionByZero() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateBinaryOperation(6, "/", 0);
    // Might be better to have the calculator throw an explicit division by zero exception instead
    assertEquals(Double.POSITIVE_INFINITY, result);
  }

  @Test
  void testUnknownOperator() {
    final Calculator calculator = new Calculator();
    assertThrows(IllegalArgumentException.class,
      () -> { calculator.evaluateBinaryOperation(6, "^", 3); });
  }

  @Test
  void testBinaryCustomOperator() {
    final List<AbstractOperator> customOperators = new ArrayList<>();
    customOperators.add(new ExponentOperator());

    final Calculator calculator = new Calculator(customOperators);
    final double result = calculator.evaluateBinaryOperation(2, "^", 3);
    assertEquals(8, result);
  }

  @Test
  void testEmptyExpression() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateExpression("");
    assertEquals(0, result);
  }

  @Test
  void testSimpleExpression() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateExpression("2 + 3");
    assertEquals(5, result);
  }

  @Test
  void testComplexExpression() {
    final Calculator calculator = new Calculator();
    final double result = calculator.evaluateExpression("2 + 3 * 4");
    assertEquals(14, result);
  }

  @Test
  void testComplexExpressionWithUnknownOperator() {
    final Calculator calculator = new Calculator();
    assertThrows(IllegalArgumentException.class,
       () -> { calculator.evaluateExpression("2 + 3 * 4 ^ 2"); });
  }

  @Test
  void testComplexExpressionWithCustomOperator() {
    final List<AbstractOperator> customOperators = new ArrayList<>();
    customOperators.add(new ExponentOperator());
    final Calculator calculator = new Calculator(customOperators);
    final double result = calculator.evaluateExpression("2 + 3 * 2 ^ 3");
    assertEquals(26, result);
  }
}
