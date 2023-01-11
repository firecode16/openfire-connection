package com.hunter.openfire.connection.model;

public class Persona {

	public static void main(String[] args) {
		String typeUserClient = "C";
		String typeUserVisity = "9";
		String typeUserABO = "D";

		String ord_showTotalTaxesUserType = "C,9";

		char[] array = ord_showTotalTaxesUserType.toCharArray();

		// Using Chars.indexOf(char[] array, char target)
		// method to get the start position of the first
		// occurrence of the specified target within array,
		// or -1 if there is no such occurrence.
		int index = array.toString().indexOf(typeUserABO);
		if (index != -1) {
			System.out.println("Target is present at index " + index);
		} else {
			System.out.println("Target is Not present" + " in the array");
		}

	}
}
