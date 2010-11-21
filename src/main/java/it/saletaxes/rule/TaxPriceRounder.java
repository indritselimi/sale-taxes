package it.saletaxes.rule;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Nonnull;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class TaxPriceRounder implements Rounder<BigDecimal> {
	public BigDecimal round( @Nonnull BigDecimal price ) {
		final BigDecimal scaled = toACentPrecision( price );

		return roundToTheNeareast5Cents( scaled );
	}

	private BigDecimal roundToTheNeareast5Cents( final BigDecimal scaled ) {
		return scaled.divide( new BigDecimal( 5 ), RoundingMode.HALF_UP ).multiply( new BigDecimal( 5 ) );
	}

	private BigDecimal toACentPrecision( BigDecimal price ) {
		return price.setScale( 2, RoundingMode.HALF_UP );
	}
}
