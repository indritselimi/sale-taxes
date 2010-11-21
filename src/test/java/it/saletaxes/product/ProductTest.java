package it.saletaxes.product;

import static it.saletaxes.Utils.price;
import static it.saletaxes.utilities.Collections.unmodifiableList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.rule.Rule;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class ProductTest {
	private Rule<Product, BigDecimal> plusOneRule;

	@Before
	public void setUp() {
		plusOneRule = new Rule<Product, BigDecimal>() {
			public BigDecimal apply( @SuppressWarnings("unused") Product product ) {
				return new BigDecimal( 1.00 );
			}

			public boolean isApplicableTo( @SuppressWarnings( { "unused" }) Product product ) {
				return true;
			}
		};
	}

	@Test
	public void shelfPriceContainsTheSaleTax() {
		final int value = 10;
		final BigDecimal price = price( value );
		@SuppressWarnings("unchecked")
		Product product = newProduct( price, plusOneRule );

		assertThat( "The product shelf price", product.shelfPrice(), is( equalTo( price( value + 1 ) ) ) );
	}

	@Test
	public void shelfPriceContainsASaleTaxForEachAppliedRule() {
		final int value = 10;
		@SuppressWarnings("unchecked")
		Product product = newProduct( price( value ), plusOneRule, plusOneRule );

		assertThat( "The product shelf price", product.shelfPrice(), is( equalTo( price( value + 2 ) ) ) );
	}

	private Product newProduct( final BigDecimal price, Rule<Product, BigDecimal>... rules ) {
		return new ProductBuilder( unmodifiableList( rules ) ).withPrice( price ).build();
	}
}
