package it.saletaxes.rule;

import static it.saletaxes.Utils.price;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.product.Product;

import java.math.BigDecimal;

import org.junit.Before;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public abstract class AbstractSalesTaxTest {
	public Rule<Product, BigDecimal> taxRule;

	private final BigDecimal price;

	private final BigDecimal tax;

	@Before
	public void setUp() {
		this.taxRule = theTaxRule();
	}

	protected abstract Rule<Product, BigDecimal> theTaxRule();

	public AbstractSalesTaxTest( BigDecimal price, BigDecimal tax ) {
		this.price = price;
		this.tax = tax;
	}

	public void applyRuleAndCheckCalculatedTaxAmount( ProductBuilder aProduct ) {
		Product product = aProduct.withPrice( price ).build();
		assertThat( "Sale tax amount", taxRule.apply( product ), is( equalTo( price( tax ) ) ) );
	}

	protected static BigDecimal forAnAmountOf( double price ) {
		return new BigDecimal( price );
	}

	protected static BigDecimal theTaxIs( double tax ) {
		return new BigDecimal( tax );
	}

	protected ProductBuilder aProduct() {
		return new ProductBuilder( null );
	}
}
