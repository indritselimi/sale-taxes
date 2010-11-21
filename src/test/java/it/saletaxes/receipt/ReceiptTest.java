package it.saletaxes.receipt;

import it.saletaxes.basket.Basket;
import it.saletaxes.receipt.exception.EmptyReceiptException;

import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class ReceiptTest {
	@Test(expected = EmptyReceiptException.class)
	public void receiptToBePrintedMustContainsAValidInformation() {
		final Basket anEmptyBasket = emptyBasket();

		new Receipt( anEmptyBasket ).print();
	}

	private Basket emptyBasket() {
		return new Basket( null, null );
	}
}
