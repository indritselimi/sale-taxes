package it.saletaxes.factory.basket;

import it.saletaxes.basket.Basket;
import it.saletaxes.basket.BasketLine;
import it.saletaxes.factory.AssistedProvider;
import it.saletaxes.format.Formatter;

import java.util.Set;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketProvider implements AssistedProvider<Basket, Set<BasketLine>> {
	private final Formatter<Basket> formatter;

	public BasketProvider( Formatter<Basket> formatter ) {
		this.formatter = formatter;
	}

	public Basket get( Set<BasketLine> lines ) {
		return new Basket( lines, formatter );
	}
}
