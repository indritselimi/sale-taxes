package it.saletaxes.factory;

import it.saletaxes.basket.Basket;
import it.saletaxes.basket.BasketFormatter;
import it.saletaxes.basket.BasketLine;
import it.saletaxes.basket.BasketLineFormatter;
import it.saletaxes.factory.basket.BasketLineBuilder;
import it.saletaxes.factory.basket.BasketLineBuilderProvider;
import it.saletaxes.factory.basket.BasketProvider;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.factory.product.ProductBuilderProvider;
import it.saletaxes.format.PriceFormatter;
import it.saletaxes.product.Product;
import it.saletaxes.rule.BasicSalesTax;
import it.saletaxes.rule.ImportedSalesTax;
import it.saletaxes.rule.Rule;
import it.saletaxes.rule.TaxPriceRounder;
import it.saletaxes.utilities.Collections;

import java.math.BigDecimal;
import java.util.Set;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class ApplicationFactory {
	private ApplicationFactory() {
		// suppress
	}

	@SuppressWarnings("unchecked")
	// varargs & generics
	public static Provider<ProductBuilder> newProductBuilderProvider() {
		return new ProductBuilderProvider( Collections.<Rule<Product, BigDecimal>> unmodifiableList(
				newBasicSalesTax(), newImportedSalesTax() ) );
	}

	public static AssistedProvider<Basket, Set<BasketLine>> newBasketProvider() {
		return new BasketProvider( new BasketFormatter( new PriceFormatter() ) );
	}

	public static Provider<BasketLineBuilder> newBasketLineBuilderProvider() {
		return new BasketLineBuilderProvider( new BasketLineFormatter( new PriceFormatter() ) );
	}

	private static Rule<Product, BigDecimal> newBasicSalesTax() {
		return new BasicSalesTax( new TaxPriceRounder() );
	}

	private static Rule<Product, BigDecimal> newImportedSalesTax() {
		return new ImportedSalesTax( new TaxPriceRounder() );
	}
}
