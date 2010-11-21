package it.saletaxes.factory;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public interface AssistedProvider<TYPE, CONTEXT> {
	TYPE get( CONTEXT context );
}
