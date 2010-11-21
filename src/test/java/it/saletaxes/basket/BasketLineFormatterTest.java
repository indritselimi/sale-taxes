package it.saletaxes.basket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.format.Formatter;
import it.saletaxes.format.PriceFormatter;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketLineFormatterTest {
	private Formatter<BasketLine> formatter;

	@Before
	public void setUp() {
		formatter = new BasketLineFormatter( new PriceFormatter() );
	}

	@Test
	public void thFormatContainsTheQuantityLineDescriptionAndTheShelfPrice() {
		BasketLine line = aBasketLineWithDescriptionQuantityAndShelfPrice( "products", 1, new BigDecimal(
				1.67 ) );

		assertThat( "The basket line format", formatter.format( line ), is( equalTo( "1 products: 1.67" ) ) );
	}

	@Test
	public void ifTheLineIsCreatedFromAnImportedProductTheFormatContainsSupplementaryInformation() {
		BasketLine line = aLineWithDescriptionQuantityAndShelfPriceWhichIsCreatedFromAnImportedProduct(
				"products", 2, new BigDecimal( 3.34 ) );

		assertThat( "The basket line format", formatter.format( line ), is( equalTo( "2 imported products: 3.34" ) ) );
	}

	private BasketLine aBasketLineWithDescriptionQuantityAndShelfPrice( final String description,
			final int quantity, final BigDecimal shelfPrice ) {
		return aBasketLine( description, quantity, shelfPrice, false );
	}

	private BasketLine aLineWithDescriptionQuantityAndShelfPriceWhichIsCreatedFromAnImportedProduct(
			final String description, final int quantity, final BigDecimal shelfPrice ) {
		return aBasketLine( description, quantity, shelfPrice, true );
	}

	private BasketLine aBasketLine( final String description, final int quantity,
			final BigDecimal shelfPrice, final boolean fromImported ) {
		BasketLine line = new BasketLine( null, 0, null ) {
			@Override
			public String description() {
				return description;
			}

			@Override
			public boolean isFromAnImportedProduct() {
				return fromImported;
			}

			@Override
			public int quantity() {
				return quantity;
			}

			@Override
			public BigDecimal shelfPrice() {
				return shelfPrice;
			}
		};
		return line;
	}

	public ProductBuilder aProduct() {
		return new ProductBuilder( null );
	}
}
