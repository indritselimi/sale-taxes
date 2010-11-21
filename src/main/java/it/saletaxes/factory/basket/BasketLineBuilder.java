package it.saletaxes.factory.basket;

import it.saletaxes.basket.BasketLine;
import it.saletaxes.factory.Builder;
import it.saletaxes.format.Formatter;
import it.saletaxes.product.Product;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketLineBuilder implements Builder<BasketLine> {
	private final Formatter<BasketLine> formatter;

	private Product product;

	private int quantity;

	public BasketLineBuilder( Formatter<BasketLine> formatter ) {
		this.formatter = formatter;
	}

	public BasketLineBuilder product( @SuppressWarnings("hiding") Product product ) {
		this.product = product;
		return this;
	}

	public BasketLineBuilder quantity( @SuppressWarnings("hiding") int quantity ) {
		this.quantity = quantity;
		return this;
	}

	public BasketLine build() {
		return new BasketLine( product, quantity, formatter );
	}
}
