package it.saletaxes.rule;

import it.saletaxes.product.Product;

import java.math.BigDecimal;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class ImportedSalesTax extends AbstractTaxRule {
	private static final BigDecimal FIVE_PERCENT = new BigDecimal( 0.05 );

	public ImportedSalesTax( Rounder<BigDecimal> rounder ) {
		super( rounder );
	}

	@Override
	protected BigDecimal calculate( Product product ) {
		return fivePercentOf( product.price() );
	}

	private BigDecimal fivePercentOf( BigDecimal price ) {
		return percentage( FIVE_PERCENT, price );
	}

	public boolean isApplicableTo( Product product ) {
		return product.isImported();
	}
}
