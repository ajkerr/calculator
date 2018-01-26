# Calculator Programming Challenge

## Usage

Load the project in Eclipse and run the JUnit 5 tests. I think Eclipse Oxygen is required for JUnit 5.

## Limitations

* The obvious one is that the solution is not complete. Operator precedence is ignored. If I remember my computer science correctly, we'd want to convert the expression from infix to postfix, paying attention to operator precedence, and then evaluate it that way. I ran out of time to figure out this algorithm.
* BigDecimal might be more appropriate for a real calculator than doubles, due to precision issues.
* There's no script for build/run/test. Maven or gradle would be good choices. I did development in Eclipse for expediency.
* I think that the exponentiation operator associates from right-to-left, not left-to-right. Support would need to be added for that. 
