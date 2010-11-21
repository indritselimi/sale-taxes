package it.saletaxes.basket;

import it.saletaxes.format.Formatter;
import it.saletaxes.product.Product;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketLine {
	private final @Nonnull
	Product product;

	private final int quantity;

	private final @Nonnull
	Formatter<BasketLine> formatter;

	public BasketLine( Product product, int quantity, Formatter<BasketLine> formatter ) {
		this.product = product;
		this.quantity = quantity;
		this.formatter = formatter;
	}

	public String humanReadableDescription() {
		return formatter.format( this );
	}

	public BigDecimal price() {
		return product.price().multiply( new BigDecimal( quantity ) );
	}

	public BigDecimal shelfPrice() {
		return product.shelfPrice().multiply( new BigDecimal( quantity ) );
	}

	public int quantity() {
		return quantity;
	}

	public boolean isFromAnImportedProduct() {
		return product.isImported();
	}

	public String description() {
		return product.description();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( product == null ) ? 0 : product.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof BasketLine ) )
			return false;
		BasketLine other = ( BasketLine )obj;
		if ( product == null ) {
			if ( other.product != null )
				return false;
		} else if ( !product.equals( other.product ) )
			return false;
		return true;
	}
}
