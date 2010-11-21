package it.saletaxes.factory.product;

import it.saletaxes.factory.Builder;
import it.saletaxes.product.Product;
import it.saletaxes.rule.Rule;

import java.math.BigDecimal;
import java.util.List;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class ProductBuilder implements Builder<Product> {
	private final List<Rule<Product, BigDecimal>> taxRules;

	private String description;

	private BigDecimal price;

	private boolean taxable;

	private boolean imported;

	public ProductBuilder( List<Rule<Product, BigDecimal>> taxRules ) {
		this.taxRules = taxRules;
	}

	public ProductBuilder withDescription( @SuppressWarnings("hiding") String description ) {
		this.description = description;
		return this;
	}

	public ProductBuilder withPrice( @SuppressWarnings("hiding") BigDecimal price ) {
		this.price = price;
		return this;
	}

	public ProductBuilder taxable() {
		this.taxable = true;
		return this;
	}

	public ProductBuilder whichIsImported() {
		this.imported = true;
		return this;
	}

	public Product build() {
		return new Product( description, price, taxable, imported, taxRules );
	}
}
