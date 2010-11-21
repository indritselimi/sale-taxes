package it.saletaxes.product;

import static it.saletaxes.utilities.Collections.isEmpty;
import it.saletaxes.rule.Rule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class Product {
	private final String description;

	private final @Nonnull @Nonnegative BigDecimal price;

	private final boolean imported;

	private final boolean taxable;

	private final @Nullable
	List<Rule<Product, BigDecimal>> taxRules;

	public Product( String description, BigDecimal price, boolean taxable, boolean imported,
			List<Rule<Product, BigDecimal>> taxRules ) {
		this.description = description;
		this.price = price;
		this.imported = imported;
		this.taxRules = taxRules;
		this.taxable = taxable;
	}

	public String description() {
		return description;
	}

	public BigDecimal shelfPrice() {
		BigDecimal shelfPrice = price();
		if ( !isEmpty( taxRules ) ) {
			for ( Rule<Product, BigDecimal> rule : taxRules ) {
				shelfPrice = shelfPrice.add( rule.apply( this ) );
			}
		}
		return shelfPrice;
	}

	public BigDecimal price() {
		return price.setScale( 2, RoundingMode.HALF_UP );
	}

	public boolean isImported() {
		return imported;
	}

	public boolean isTaxable() {
		return taxable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
		result = prime * result + ( imported ? 1231 : 1237 );
		result = prime * result + ( ( price == null ) ? 0 : price.hashCode() );
		result = prime * result + ( taxable ? 1231 : 1237 );
		return result;
	}

	/**
	 * Implicit assumption that the basket can't contain another same but
	 * imported product.
	 */
	// I'm going to keep things simple for now so I'm not going to fix it. BTW:
	// fixing it is not so difficult.
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof Product ) )
			return false;
		Product other = ( Product )obj;
		if ( description == null ) {
			if ( other.description != null )
				return false;
		} else if ( !description.equals( other.description ) )
			return false;
		if ( imported != other.imported )
			return false;
		if ( price == null ) {
			if ( other.price != null )
				return false;
		} else if ( !price.equals( other.price ) )
			return false;
		if ( taxable != other.taxable )
			return false;
		return true;
	}
}
