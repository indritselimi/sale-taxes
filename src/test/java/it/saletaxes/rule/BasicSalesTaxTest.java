package it.saletaxes.rule;

import static java.util.Arrays.asList;
import it.saletaxes.product.Product;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */

@RunWith(value = Parameterized.class)
public class BasicSalesTaxTest extends AbstractSalesTaxTest {
	public BasicSalesTaxTest( BigDecimal price, BigDecimal tax ) {
		super( price, tax );
	}

	@Override
	protected Rule<Product, BigDecimal> theTaxRule() {
		return new BasicSalesTax( new TaxPriceRounder() );
	}

	@Parameters
	public static Collection<BigDecimal[]> dataParameters() {
		return asList( new BigDecimal[][] { 
				{ forAnAmountOf( 10 ), theTaxIs( 1 ) },
				{ forAnAmountOf( 10.30 ), theTaxIs( 1.05 ) }, 
				{ forAnAmountOf( 0.30 ), theTaxIs( 0.05 ) },
				{ forAnAmountOf( 10.67 ), theTaxIs( 1.05 ) }, 
				{ forAnAmountOf( 15.58 ), theTaxIs( 1.55 ) }, 
				{ forAnAmountOf( 15.88 ), theTaxIs( 1.60 ) } 
				} );
	}

	@Test
	public void theBasicTaxForProductsAmountsToTenPercentWithRoundingToTheNEAREASTmultipleOfFiveCents() {
		applyRuleAndCheckCalculatedTaxAmount( aProduct().taxable() );
	}
}
