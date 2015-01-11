package org.zezutom.java8.examples.datetime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests date / time manipulation using a simple real-life use cases
 *
 * @see org.zezutom.java8.examples.datetime.PaydayCalculator
 */
public class PaydayCalculatorTest {

    private PaydayCalculator paydayCalculator;

    @Before
    public void setUp() {
        paydayCalculator = new PaydayCalculator();
    }

    @Test
    public void weeklyPayInIreland() {
        assertThat(paydayCalculator.weeklyPayInIreland(2015, 1), is("2 9 16 23 30"));
        assertThat(paydayCalculator.weeklyPayInIreland(2015, 4), is("3 10 17 24"));
    }

    @Test
    public void monthlyPayInIreland() {
        assertThat(paydayCalculator.monthlyPayInIreland(2015, 1), is("2015-01-30"));
        assertThat(paydayCalculator.monthlyPayInIreland(2015, 4), is("2015-04-24"));
    }



    @Test
    public void paydayInSweden() {

        // February 2015, 25th falls on a weekday
        assertThat(paydayCalculator.paydayInSweden(2015, 2), is("2015-02-25"));

        // October 2014, 25th falls on a Saturday
        assertThat(paydayCalculator.paydayInSweden(2014, 10), is("2014-10-24"));

        // May 2014, 25th falls on a Sunday
        assertThat(paydayCalculator.paydayInSweden(2014, 5), is("2014-05-23"));
    }

    @Test
    public void happyFridaysInSweden() {
        assertThat(paydayCalculator.happyFridaysInSweden(2015),
                is("23/1 24/4 24/7 25/9 23/10 25/12"));
    }
}
