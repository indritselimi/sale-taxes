package it.saletaxes.rule;

import it.saletaxes.product.Product;

import java.math.BigDecimal;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class BasicSalesTax extends AbstractTaxRule {
	private static final BigDecimal TEN_PERCENT = new BigDecimal( 0.10 );

	public BasicSalesTax( Rounder<BigDecimal> rounder ) {
		super( rounder );
	}

	@Override
	public BigDecimal calculate( Product product ) {
		return tenPercentOf( product.price() );
	}

	private BigDecimal tenPercentOf( BigDecimal price ) {
		return percentage( TEN_PERCENT, price );
	}

	public boolean isApplicableTo( Product product ) {
		return product.isTaxable();
	}
}
