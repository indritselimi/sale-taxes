package it.saletaxes.basket;

import static java.math.BigDecimal.ZERO;
import it.saletaxes.format.Formatter;
import it.saletaxes.receipt.Receipt;
import it.saletaxes.utilities.Collections;

import java.math.BigDecimal;
import java.util.Set;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class Basket {

	private final Set<BasketLine> lines;

	private final Formatter<Basket> formatter;

	public Basket( Set<BasketLine> lines, Formatter<Basket> formatter ) {
		this.lines = lines;
		this.formatter = formatter;
	}

	public Receipt toReceipt() {
		return new Receipt( this );
	}

	public Set<BasketLine> lines() {
		return lines;
	}

	public BigDecimal taxes() {
		return total().subtract( netPrice() );
	}

	private BigDecimal netPrice() {
		BigDecimal netPrice = ZERO;
		if ( !isEmpty() ) {
			for ( BasketLine line : lines ) {
				netPrice = netPrice.add( line.price() );
			}
		}
		return netPrice;
	}

	public BigDecimal total() {
		BigDecimal total = ZERO;
		if ( !isEmpty() ) {
			for ( BasketLine line : lines ) {
				total = total.add( line.shelfPrice() );
			}
		}
		return total;
	}

	public String humanReadableDescription() {
		return formatter.format( this );
	}

	public boolean isEmpty() {
		return Collections.isEmpty( lines );
	}
}
