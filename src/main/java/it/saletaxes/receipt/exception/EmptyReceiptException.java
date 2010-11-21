package it.saletaxes.receipt.exception;

import it.saletaxes.exception.LogRuntimeException;

/**
 * No documentation yet for this class.	
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class EmptyReceiptException extends LogRuntimeException {
	public EmptyReceiptException( String message ) {
		super( message );
	}
}
