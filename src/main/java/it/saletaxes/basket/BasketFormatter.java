package it.saletaxes.basket;

import static it.saletaxes.utilities.Collections.newArrayList;
import static java.lang.System.getProperty;
import it.saletaxes.format.Formatter;

import java.math.BigDecimal;
import java.util.List;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketFormatter implements Formatter<Basket> {
	private final Formatter<BigDecimal> priceFormatter;

	public BasketFormatter( Formatter<BigDecimal> priceFormatter ) {
		this.priceFormatter = priceFormatter;
	}

	@Override
	public String format( Basket basket ) {
		if ( basket.isEmpty() ) {
			return "The basket is empty";
		}
		final List<String> singleLines = newArrayList();
		for ( BasketLine line : basket.lines() ) {
			singleLines.add( line.humanReadableDescription() );
		}
		singleLines.add( "Sales Taxes: " + priceFormatter.format( basket.taxes() ) );
		singleLines.add( "Total: " + priceFormatter.format( basket.total() ) );

		StringBuilder result = new StringBuilder();
		for ( String singleLine : singleLines ) {
			result.append( singleLine ).append( getProperty( "line.separator" ) );
		}
		return result.toString();
	}
}
