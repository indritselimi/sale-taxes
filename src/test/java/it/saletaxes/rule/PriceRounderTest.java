package it.saletaxes.rule;

import static it.saletaxes.Utils.price;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.util.Collection;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
@RunWith(value = Parameterized.class)
public class PriceRounderTest {
	private Rounder<BigDecimal> rounder;

	private final BigDecimal price;

	private final BigDecimal expected;

	@Before
	public void setUp() {
		rounder = new TaxPriceRounder();
	}

	public PriceRounderTest( BigDecimal price, BigDecimal expected ) {
		this.price = price;
		this.expected = expected;
	}

	@Parameters
	public static Collection<BigDecimal[]> dataParameters() {
		return asList( new BigDecimal[][] { 
				{ theNeareast5CentOf( 0.00 ), is( 0.0 ) },
				{ theNeareast5CentOf( 0.05 ), is( 0.05 ) },
				{ theNeareast5CentOf( 1.02 ), is( 1.00 ) },
				{ theNeareast5CentOf( 1.03 ), is( 1.05 ) },
				{ theNeareast5CentOf( 1.06 ), is( 1.05 ) },
				{ theNeareast5CentOf( 1.08 ), is( 1.10 ) },
				{ theNeareast5CentOf( 10.08 ), is( 10.10 ) },
				{ theNeareast5CentOf( 11.0001 ), is( 11.00 ) },
				{ theNeareast5CentOf( 1100303.23 ), is( 1100303.25 ) } 
				} );
	}

	@Test
	public void roundToTheNeareastFiveCents() {
		assertThat( "The rounded value", rounder.round( price ), Matchers.is( equalTo( expected  ) ) );
	}

	private static BigDecimal theNeareast5CentOf( double value ) {
		return price( value );
	}

	private static BigDecimal is( double value ) {
		return price( value );
	}
}
