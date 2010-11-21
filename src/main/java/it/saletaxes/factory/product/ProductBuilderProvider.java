package it.saletaxes.factory.product;

import java.math.BigDecimal;
import java.util.List;

import it.saletaxes.factory.Provider;
import it.saletaxes.product.Product;
import it.saletaxes.rule.Rule;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class ProductBuilderProvider implements Provider<ProductBuilder> {
	private final List<Rule<Product, BigDecimal>> taxRules;

	public ProductBuilderProvider( List<Rule<Product, BigDecimal>> taxRules ) {
		this.taxRules = taxRules;
	}

	public ProductBuilder get() {
		return new ProductBuilder( taxRules );
	}
}
