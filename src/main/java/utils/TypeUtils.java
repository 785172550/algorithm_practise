package utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class TypeUtils {

	private TypeUtils() {
	}

	/**
	 * convert List<T> to List<U>
	 * 
	 * @param from
	 *            list<T> to be
	 * @param func
	 *            a method to convert T to U
	 * @return List<U>
	 */
	public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
		// return from.stream().map(func).collect(Collectors.toList());
		return from.stream().map(func).collect(Collectors.toList());
	}

	/**
	 * 
	 * @param from
	 *            array of T elements
	 * @param func
	 *            a method to convert T to U
	 * @param generator
	 *            a function which produces a new array of the desired type and the
	 *            provided length
	 * @return U[]
	 */
	public static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
		// return Arrays.stream(from).map(func).toArray(generator);
		return Arrays.stream(from).map(func).toArray(generator);
	}

}
