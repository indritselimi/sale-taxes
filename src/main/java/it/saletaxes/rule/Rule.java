package it.saletaxes.rule;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public interface Rule<PRODUCT, PRICE> {
	PRICE apply( PRODUCT product );

	boolean isApplicableTo( PRODUCT product );
}
