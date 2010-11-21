package it.saletaxes.rule;

import static java.math.BigDecimal.ZERO;
import it.saletaxes.product.Product;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public abstract class AbstractTaxRule implements Rule<Product, BigDecimal> {
	private final @Nonnull
	Rounder<BigDecimal> rounder;

	protected AbstractTaxRule( Rounder<BigDecimal> rounder ) {
		this.rounder = rounder;
	}

	public BigDecimal apply( Product product ) {
		return isApplicableTo( product ) ? round( calculate( product ) ) : ZERO;
	}

	protected abstract BigDecimal calculate( Product product );

	protected BigDecimal round( BigDecimal tax ) {
		return rounder.round( tax );
	}

	protected static BigDecimal percentage( BigDecimal percent, BigDecimal price ) {
		return price.multiply( percent );
	}
}