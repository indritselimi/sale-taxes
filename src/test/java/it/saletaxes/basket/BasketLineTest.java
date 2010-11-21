
package it.saletaxes.basket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import it.saletaxes.factory.basket.BasketLineBuilder;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.format.PriceFormatter;
import it.saletaxes.product.Product;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasketLineTest {
	@Test
	public void theBasketLineCanDescribeItselfInAHumanReadableFormat() {
		Product product = aProduct().withDescription( "products" ).withPrice( new BigDecimal( 2.54 ) )
				.whichIsImported().build();

		BasketLine line = aLine().product( product ).quantity( 10 ).build();

		assertThat( "Line human readable description", line.humanReadableDescription(),
				is( equalTo( "10 imported products: 25.40" ) ) );
	}

	private BasketLineBuilder aLine() {
		return new BasketLineBuilder( new BasketLineFormatter( new PriceFormatter() ) );
	}

	public ProductBuilder aProduct() {
		return new ProductBuilder( null );
	}
}
