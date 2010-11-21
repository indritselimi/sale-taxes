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
public class ImportedSalesTaxTest extends AbstractSalesTaxTest{
	@Parameters
	public static Collection<BigDecimal[]> dataParameters() {
		return asList( new BigDecimal[][] { 
				{ forAnAmountOf(1), theTaxIs(0.05) },
				{ forAnAmountOf( 10 ), theTaxIs( 0.50 ) },
				{ forAnAmountOf( 30 ), theTaxIs( 1.50 ) },
				{ forAnAmountOf( 11.25 ), theTaxIs( 0.55 ) }
				} );
	}

	@Test
	public void theTaxForImportedProductsAmountToFivePercentWithRoundingToTheNEAREASTmultipleOfFiveCents() {
		applyRuleAndCheckCalculatedTaxAmount(aProduct().whichIsImported());
	}
	
	@Override
	protected Rule<Product, BigDecimal> theTaxRule() {
		return new ImportedSalesTax( new TaxPriceRounder() );	
	}
	
	public ImportedSalesTaxTest( BigDecimal price, BigDecimal tax ) {
		super(price, tax);
	}
}
