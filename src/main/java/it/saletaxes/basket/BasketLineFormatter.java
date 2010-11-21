package it.saletaxes.basket;

import java.math.BigDecimal;
import java.text.MessageFormat;

import it.saletaxes.format.Formatter;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketLineFormatter implements Formatter<BasketLine> {
	private final Formatter<BigDecimal> priceFormatter;

	public BasketLineFormatter( Formatter<BigDecimal> priceFormatter ) {
		this.priceFormatter = priceFormatter;
	}

	@Override
	public String format( BasketLine line ) {
		return MessageFormat.format( "{0}{1,choice,0|0< imported} {2}: {3}", line.quantity(), line
				.isFromAnImportedProduct() ? 1 : 0, line.description(), priceFormatter.format( line
				.shelfPrice() ) );
	}
}
