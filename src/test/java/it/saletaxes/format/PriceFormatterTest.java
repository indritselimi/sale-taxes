package it.saletaxes.format;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class PriceFormatterTest {
	private Formatter<BigDecimal> formatter;

	@Before
	public void setUp() {
		formatter = new PriceFormatter();
	}

	@Test
	public void thePriceAmountsAreDisplayedWithTwoDigitCents() {
		theFormatPriceOf( 0, is( "0.00" ) );
		theFormatPriceOf( 1.5, is( "1.50" ) );
		theFormatPriceOf( 10, is( "10.00" ) );
		theFormatPriceOf( 12345.67, is( "12345.67" ) );
	}

	@Test
	public void aZeroIsDisplayedBeforeCentsWhenPriceIsLessThanOne() {
		theFormatPriceOf( 0, is( "0.00" ) );
		theFormatPriceOf( 0.5, is( "0.50" ) );
	}
	
	private String priceFormat( double value ) {
		return formatter.format( new BigDecimal( value ) );
	}

	private void theFormatPriceOf( double price, Matcher<String> matcher ) {
		assertThat( "The price format ", priceFormat( price ), matcher );
	}

	private Matcher<String> is( String value ) {
		return Matchers.is( equalTo( value ) );
	}
}
