package it.saletaxes.receipt;

import it.saletaxes.basket.Basket;
import it.saletaxes.receipt.exception.EmptyReceiptException;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class Receipt {
	private final Basket basket;

	public Receipt( Basket basket ) {
		this.basket = basket;
	}

	public String print() {
		basketMustBeNotEmpty();

		return basket.humanReadableDescription();
	}

	private void basketMustBeNotEmpty() {
		if ( basket.isEmpty() ) {
			throw new EmptyReceiptException(
					"For printing a receipt at least one product must be purchased. Please check your basket." );
		}
	}
}
