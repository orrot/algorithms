package org.example.smallestpositiveint;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SmallestPositiveIntegerTest {

    @Test
    public void testScenario1() {
        Integer[] arrayA = {1, 3, 6, 4, 1, 2};

        Integer returnedInt = SmallestPositiveIntegerOrrot.findSmallestPositiveInteger(arrayA); // my method here;

        assertThat(returnedInt).isEqualTo(5);
    }

    @Test
    public void testScenario2() {
        Integer[] arrayA = {1, 2, 3};

        Integer returnedInt = SmallestPositiveIntegerOrrot.findSmallestPositiveInteger(arrayA); // my method here;

        assertThat(returnedInt).isEqualTo(4);
    }

    @Test
    public void testScenario3() {
        Integer[] arrayA = {-1, -3};

        Integer returnedInt = SmallestPositiveIntegerOrrot.findSmallestPositiveInteger(arrayA); // my method here;

        assertThat(returnedInt).isEqualTo(1);
    }

    @Test
    public void testScenario4() {
        Integer[] arrayA = {-4};

        Integer returnedInt = SmallestPositiveIntegerOrrot.findSmallestPositiveInteger(arrayA); // my method here;

        assertThat(returnedInt).isEqualTo(1);
    }

    @Test
    public void testScenario5() {
        Integer[] arrayA = {100};

        Integer returnedInt = SmallestPositiveIntegerOrrot.findSmallestPositiveInteger(arrayA); // my method here;

        assertThat(returnedInt).isEqualTo(1);
    }

    @Test
    public void testScenario6() {
        Integer[] arrayA = {0};

        Integer returnedInt = SmallestPositiveIntegerOrrot.findSmallestPositiveInteger(arrayA); // my method here;

        assertThat(returnedInt).isEqualTo(1);
    }
}
