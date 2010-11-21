package it.saletaxes.basket;

import static it.saletaxes.Utils.price;
import static it.saletaxes.utilities.Collections.unmodifiableSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import it.saletaxes.Utils;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.product.Product;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it 23/mag/2010 17.53.47
 */
/*
 * FIXME: These tests tries to be focused on the behavioral of the basket, but
 * they aren't 100% exact about amount calculations because the tests doesn't
 * take care yet about cumulative rounding.
 */
public class BasketTest {
	@Test
	public void theNetPriceIsTheResultOfTheSumOfLinesShelfPrices() {
		final int shelfPriceOfLine1 = 2;
		final int shelfPriceOfLine2 = 3;
		Basket basket = aBasketWithLines( aLineWithPredetrminedShelfPrice( shelfPriceOfLine1 ),
				aLineWithPredetrminedShelfPrice( shelfPriceOfLine2 ) );

		assertThat( "The basket total price", basket.total(), is( equalTo( price( shelfPriceOfLine1
				+ shelfPriceOfLine2 ) ) ) );
	}

	@Test
	public void theTaxesAreCalculatedAsTheDifferenceBetweenTotalAndNetPrices() {
		final int netPriceOfLine1 = 1;
		final int netPriceOfLine2 = 2;
		final int shelfPriceOfLine1 = 3;
		final int shelfPriceOfLine2 = 4;

		Basket basket = aBasketWithLines( aLineWithPredetrminedPrices( netPriceOfLine1, shelfPriceOfLine1 ),
				aLineWithPredetrminedPrices( netPriceOfLine2, shelfPriceOfLine2 ) );

		assertThat( "The basket taxes", basket.taxes(),
				is( equalTo( price( ( shelfPriceOfLine1 + shelfPriceOfLine2 )
						- ( netPriceOfLine1 + netPriceOfLine2 ) ) ) ) );
	}

	private Basket aBasketWithLines( BasketLine line, BasketLine line2 ) {
		return new Basket( unmodifiableSet( line, line2 ), null );
	}

	private static BasketLine aLineWithPredetrminedShelfPrice( final double shelfPrice ) {
		return aLineWithPredetrminedPrices( 0, shelfPrice );
	}

	private static BasketLine aLineWithPredetrminedPrices( final double price, final double shelfPrice ) {
		final Product product = new ProductBuilder( null ).withDescription(
				"product: price " + price + "shelf price " + shelfPrice ).build();
		return new BasketLine( product, 1, null ) {
			@Override
			public BigDecimal price() {
				return Utils.price( price );
			}

			@Override
			public BigDecimal shelfPrice() {
				return Utils.price( shelfPrice );
			}
		};
	}
}
