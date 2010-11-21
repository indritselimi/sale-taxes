package it.saletaxes.factory.basket;

import it.saletaxes.basket.BasketLine;
import it.saletaxes.factory.Provider;
import it.saletaxes.format.Formatter;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketLineBuilderProvider implements Provider<BasketLineBuilder> {
	private final Formatter<BasketLine> formatter;

	public BasketLineBuilderProvider( Formatter<BasketLine> formatter ) {
		this.formatter = formatter;
	}

	public BasketLineBuilder get() {
		return new BasketLineBuilder( formatter );
	}
}
