package com.iberdrola.respadel.util;

/**
 * Utilidades genéricas
 * 
 * @author u247429
 *
 */
public class Utils {

	public static void main(String args[]) {
		System.out.println(wildCardListMatch("U247429", "u*,admin"));
		System.out.println(wildCardListMatch("U247429", "x*,admin"));
	}

	public static boolean wildCardListMatch(String text, String commaSeparatedPatterns) {
		String[] patterns = commaSeparatedPatterns.toLowerCase().split("\\,");
		for (String pattern : patterns) {
			if (wildCardMatch(text.toLowerCase(), pattern)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Performs a wildcard matching for the text and pattern provided.
	 * 
	 * @param text
	 *            the text to be tested for matches.
	 * 
	 * @param pattern
	 *            the pattern to be matched for. This can contain the wildcard
	 *            character '*' (asterisk).
	 * 
	 * @return <tt>true</tt> if a match is found, <tt>false</tt> otherwise.
	 */
	public static boolean wildCardMatch(String text, String pattern) {
		// Create the cards by splitting using a RegEx. If more speed
		// is desired, a simpler character based splitting can be done.
		String[] cards = pattern.split("\\*");

		// Iterate over the cards.
		for (String card : cards) {
			int idx = text.indexOf(card);

			// Card not detected in the text.
			if (idx == -1) {
				return false;
			}

			// Move ahead, towards the right of the text.
			text = text.substring(idx + card.length());
		}

		return true;
	}

}
