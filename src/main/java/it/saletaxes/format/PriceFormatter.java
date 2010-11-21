package it.saletaxes.format;

import static java.util.Locale.UK;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class PriceFormatter implements Formatter<BigDecimal> {
	private final Locale locale;

	private static final Locale UK_AS_DEFAULT = UK;

	public PriceFormatter() {
		this( UK_AS_DEFAULT );
	}

	protected PriceFormatter( Locale locale ) {
		this.locale = locale;
	}

	public String format( BigDecimal price ) {
		DecimalFormat formatter = new DecimalFormat( "#0.00", new DecimalFormatSymbols( locale ) );
		return formatter.format( price );
	}
}
