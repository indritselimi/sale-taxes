package it.saletaxes;

import static it.saletaxes.factory.ApplicationFactory.newBasketLineBuilderProvider;
import static it.saletaxes.factory.ApplicationFactory.newBasketProvider;
import static it.saletaxes.factory.ApplicationFactory.newProductBuilderProvider;
import it.saletaxes.basket.Basket;
import it.saletaxes.basket.BasketLine;
import it.saletaxes.factory.AssistedProvider;
import it.saletaxes.factory.Provider;
import it.saletaxes.factory.basket.BasketLineBuilder;
import it.saletaxes.factory.product.ProductBuilder;
import it.saletaxes.product.Product;
import it.saletaxes.utilities.Collections;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Read source code. 
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class Application {
	
	public static void main( String... args ) {
		Product book = aProduct().withDescription( "book" ).withPrice( new BigDecimal( 10 ) ).build();
		Product iMac = aProduct().withDescription( "iMac i7" ).withPrice( new BigDecimal( 1126.87 ) )
				.taxable().whichIsImported().build();
		Product ham = aProduct().withDescription( "hamburger" ).withPrice( new BigDecimal( 3.34 ) )
				.whichIsImported().build();

		final Basket basket = aBasket( 
				aBasketLine().product( book ).quantity( 1 ).build(), 
				aBasketLine().product( iMac ).quantity( 2 ).build(), 
				aBasketLine().product( ham ).quantity( 3 ).build() );

		new Displayer<String>() {
			public void display( String text ) {
				System.out.println( text );
			}
		}.display( basket.toReceipt().print() );
	}

	private static final AssistedProvider<Basket, Set<BasketLine>> basketProvider = newBasketProvider();
	private static Basket aBasket( BasketLine bookLine, BasketLine iMacLine, BasketLine hamLine ) {
		return basketProvider.get( Collections.unmodifiableSet( bookLine, iMacLine, hamLine ) );
	}

	private static final Provider<BasketLineBuilder> basketLineBuilderProvider = newBasketLineBuilderProvider();
	private static BasketLineBuilder aBasketLine() {
		return basketLineBuilderProvider.get();
	}

	private static final Provider<ProductBuilder> productBuilderProvider = newProductBuilderProvider();
	private static ProductBuilder aProduct() {
		return productBuilderProvider.get();
	}

	interface Displayer<DISPLAYABLE> {
		void display( DISPLAYABLE d );
	}
}
