package it.saletaxes;

import static it.saletaxes.Utils.price;
import static it.saletaxes.utilities.Collections.unmodifiableSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import it.saletaxes.basket.Basket;
import it.saletaxes.basket.BasketFormatter;
import it.saletaxes.basket.BasketLine;
import it.saletaxes.basket.BasketLineFormatter;
import it.saletaxes.factory.AssistedProvider;
import it.saletaxes.factory.Provider;
import it.saletaxes.factory.basket.BasketLineBuilder;
import it.saletaxes.factory.basket.BasketLineBuilderProvider;
import it.saletaxes.factory.basket.BasketProvider;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.format.PriceFormatter;
import it.saletaxes.product.Product;
import it.saletaxes.rule.BasicSalesTax;
import it.saletaxes.rule.ImportedSalesTax;
import it.saletaxes.rule.Rule;
import it.saletaxes.rule.TaxPriceRounder;
import it.saletaxes.utilities.Collections;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class SalesTaxesEndToEndTests {
	@Test
	public void aReceiptForATaxFreeAndNotImportedSingleProductDisplaysTheCurrentPrice() {
		Product book = aProduct().withDescription( "book" ).withPrice( price(12.49) ).build();

		Basket basket = Basket
				.get( unmodifiableSet( BasketLine.get().product( book ).quantity( 1 ).build() ) );

		assertEquals( basket.toReceipt().print(), 
				"1 book: 12.49", 
				"Sales Taxes: 0.00", 
				"Total: 12.49" 
				);
	}

	@Test
	public void aReceiptForANotImportedTaxedSingleProductDisplaysTheShelfPrice() {
		Product musicCD = aProduct().withDescription( "music CD" ).withPrice( price( 14.99 ) )
				.taxable().build();

		Basket basket = Basket.get( unmodifiableSet( BasketLine.get().product( musicCD ).quantity( 1 )
				.build() ) );

		assertEquals( basket.toReceipt().print(), 
				"1 music CD: 16.49", 
				"Sales Taxes: 1.50", 
				"Total: 16.49" 
				);
	}

	@Test
	public void inputExampleDataNumberOne() {
		Product book = aProduct().withDescription( "book" ).withPrice( price( 12.49 ) ).build();
		Product musicCD = aProduct().withDescription( "music CD" ).withPrice( price( 14.99 ) )
				.taxable().build();
		Product chocolateBar = aProduct().withDescription( "chocolate bar" )
				.withPrice( price( 0.85 ) ).build();

		Basket basket = Basket.get( unmodifiableSet( 
				BasketLine.get().product( book ).quantity( 1 ).build(),
				BasketLine.get().product( musicCD ).quantity( 1 ).build(), 
				BasketLine.get().product(chocolateBar ).quantity( 1 ).build() 
				) );

		assertEquals( basket.toReceipt().print(), 
				"1 book: 12.49", 
				"1 music CD: 16.49",
				"1 chocolate bar: 0.85", 
				"Sales Taxes: 1.50", 
				"Total: 29.83" 
				);
	}

	@Test
	public void inputExampleDataNumberTwo() {
		Product chocolates = aProduct().withDescription( "box of chocolates" ).withPrice(
				price( 10.00 ) ).whichIsImported().build();

		Product perfume = aProduct().withDescription( "bottle of perfume" )
				.withPrice( price( 47.50 ) ).taxable().whichIsImported().build();

		Basket basket = Basket.get( unmodifiableSet( 
				BasketLine.get().product( chocolates ).quantity( 1 ).build(), 
				BasketLine.get().product( perfume ).quantity( 1 ).build() 
				) );

		assertEquals( basket.toReceipt().print(), 
				"1 imported box of chocolates: 10.50",
				"1 imported bottle of perfume: 54.65", 
				"Sales Taxes: 7.65", 
				"Total: 65.15" 
				);
	}

	@Test
	/*
	 * FIXME: I think the output 3 of provided example data is wrong for
	 * "box of chocolates" line because in that example the rounding applied is
	 * not to nearest but to the upper 5 cents. Therefore I have changed output
	 * data.
	 */
	public void inputExampleDataNumberThree() {
		final ProductBuilder perfumeBottle = aProduct().withDescription( "bottle of perfume" ).taxable();
		Product perfume = perfumeBottle.withPrice( price( 18.99 ) ).build();
		Product importedPerfume = perfumeBottle.withPrice( price( 27.99 ) ).whichIsImported()
				.build();

		Product pills = aProduct().withDescription( "packet of headache pills" ).withPrice(
				price( 9.75 ) ).build();

		Product importedChocolates = aProduct().withDescription( "box of chocolates" ).withPrice(
				price( 11.25 ) ).whichIsImported().build();

		Basket basket = Basket.get( unmodifiableSet(
						BasketLine.get().product( importedPerfume ).quantity( 1 ).build(),
						BasketLine.get().product( perfume ).quantity( 1 ).build(), 
						BasketLine.get().product( pills ).quantity( 1 ).build(), 
						BasketLine.get().product( importedChocolates ).quantity( 1 ).build() 
						) );

		assertEquals( basket.toReceipt().print(), 
				"1 imported bottle of perfume: 32.19",
				"1 bottle of perfume: 20.89", 
				"1 packet of headache pills: 9.75",
				"1 imported box of chocolates: 11.80", 
				"Sales Taxes: 6.65", 
				"Total: 74.63" 
				);
	}

	public ProductBuilder aProduct() {
		return new ProductBuilder( taxRules );
	}

	@SuppressWarnings("unchecked")
	public static List<Rule<Product, BigDecimal>> taxRules() {
		return Collections.<Rule<Product, BigDecimal>> unmodifiableList( new BasicSalesTax(
				new TaxPriceRounder() ), new ImportedSalesTax( new TaxPriceRounder() ) );
	}

	private void assertEquals( String receipt, String... lines ) {
		assertThat( "Receipt line ", receipt, is( equalTo( multiLine( lines ) ) ) );
	}

	private static String LINE_SEPARATOR = System.getProperty( "line.separator" );
	/*
	 * Pay attention, I have no explicit requirments about the last carriage
	 * return on the receipt, in this case I choode deliberately to insert a new
	 * line at the bottom.
	 */
	private static String multiLine( String... lines ) {
		StringBuilder builder = new StringBuilder();
		for ( String line : lines ) {
			builder.append( line ).append( LINE_SEPARATOR );
		}
		return builder.toString();
	}

	private Provider<BasketLineBuilder> BasketLine;

	private AssistedProvider<Basket, Set<BasketLine>> Basket;

	private List<Rule<Product, BigDecimal>> taxRules;

	@Before
	public void setUp() {
		BasketLine = new BasketLineBuilderProvider( new BasketLineFormatter( new PriceFormatter()) );
		Basket = new BasketProvider( new BasketFormatter( new PriceFormatter() ) );

		taxRules = taxRules();
	}
}
