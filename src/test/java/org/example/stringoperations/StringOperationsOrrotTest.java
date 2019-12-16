package org.example.stringoperations;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringOperationsOrrotTest {

    @Test
    public void shouldComputeOneParenthesis() {

        String input = "( + 4 5 )";

        Integer result = StringOperationsOrrot.computeExpression(input);

        assertThat(result).isEqualTo(9);
    }

    @Test
    public void shouldComputeTwoParenthesis() {

        // ( 4 + 5 + (10 - 4 ) )
        // ( 9 + (6))
        String input = "( + 4 5 ( - 10 4 ) )";

        Integer result = StringOperationsOrrot.computeExpression(input);

        assertThat(result).isEqualTo(15);
    }

    @Test
    public void shouldComputeThreeParenthesis() {

        // 4 + 5 + ( 30 - 4 - ( 2 * 3 * 4 ) )
        // 9 + ( 26 - ( 24 ) )
        // 9 + ( 2 )
        String input = "( + 4 5 ( - 30 4 ( * 2 3 4 ) ) )";

        Integer result = StringOperationsOrrot.computeExpression(input);

        assertThat(result).isEqualTo(11);
    }

    @Test
    public void shouldComputeSpecialThreeParenthesis() {

        // 4 + 5 + ( 30 - 4 - ( 2 * 3 * 4 ) - 2 ) + 7
        // 9 + ( 26 - ( 24 ) - 2 ) + 7
        // 9 + ( 0 ) + 7
        String input = "( + 4 5 ( - 30 4 ( * 2 3 4 ) 2 ) 7 )";

        Integer result = StringOperationsOrrot.computeExpression(input);

        assertThat(result).isEqualTo(16);
    }
}
