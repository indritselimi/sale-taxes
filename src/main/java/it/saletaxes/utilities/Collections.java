package it.saletaxes.utilities;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * No documentation yet for this class.
 * 
 * @author Indrit.Selimi indrit.selimi@rcs.it
 */
public class Collections {
	private Collections() {
		// Suppresses default constructor
	}
	
	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<E>();
	}

	public static <E> HashSet<E> newHashSet() {
		return new HashSet<E>();
	}

	public static <E> Set<E> unmodifiableSet( E... elements ) {
		return java.util.Collections.unmodifiableSet( new LinkedHashSet<E>( asList( elements ) ) );
	}

	public static <E> List<E> unmodifiableList( E... elements ) {
		return java.util.Collections.unmodifiableList( asList( elements ) );
	}

	public static boolean isEmpty( @Nullable Collection<?> c ) {
		return c == null || c.size() == 0;
	}

	public static <T> boolean isEmpty( @Nullable T... a ) {
		return a == null || a.length == 0;
	}
}
