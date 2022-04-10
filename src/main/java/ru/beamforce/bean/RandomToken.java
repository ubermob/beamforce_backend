package ru.beamforce.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
@Component
public class RandomToken {

	private final Random random;
	private final char[] elements;
	private final int defaultLength;

	public RandomToken() {
		random = new Random();
		elements = chars();
		this.defaultLength = 42;
	}

	public RandomToken(int defaultLength) {
		random = new Random();
		elements = chars();
		this.defaultLength = defaultLength;
	}

	@Bean
	public RandomToken getRandomToken() {
		return new RandomToken();
	}

	public String getToken() {
		return getToken(defaultLength);
	}

	public String getToken(int length) {
		return getToken(length, 75);
	}

	// token contain 0-9 and A-Z only
	public String getTinyToken() {
		return getTinyToken(defaultLength);
	}

	// token contain 0-9 and A-Z only
	public String getTinyToken(int length) {
		return getToken(length, 36);
	}

	private String getToken(int length, int bound) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(getElement(random.nextInt(bound)));
		}
		return builder.toString();
	}

	private char getElement(int index) {
		return elements[index];
	}

	private char[] chars() {
		// size == 10 + 13 * 5 == 75
		return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
				, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'
				, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
				, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
				, 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
				, '-', '_', '+', '*', '=', '@', '%', '$', '?', '!', '^', ';', ':'};
	}
}