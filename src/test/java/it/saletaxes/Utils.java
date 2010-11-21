package it.saletaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class Utils {
	public static BigDecimal price( double value ) {
		return price( new BigDecimal( value ) );
	}

	public static BigDecimal price( BigDecimal value ) {
		return value.setScale( 2, RoundingMode.HALF_UP );
	}
}
