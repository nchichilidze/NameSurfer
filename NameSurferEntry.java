
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	public int[] decades = new int[NDECADES];
	public String name;
	

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers
	 * giving the rank of that name for each decade.
	 */
	public NameSurferEntry(String line) {
		name = line.substring(0, line.indexOf(" "));
		line = line.substring(line.indexOf(" ") + 1);
		for (int i = 0; i < NDECADES; i++) {
			if (i == NDECADES - 1) {
				int year = Integer.parseInt(line);
				decades[i] = year;
			} else {
				String dec = line.substring(0, line.indexOf(" "));
				line = line.substring(line.indexOf(" ")+1);
				int year = Integer.parseInt(dec);
				decades[i] = year;
			}
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The
	 * decade value is an integer indicating how many decades have passed since
	 * the first year in the database, which is given by the constant
	 * START_DECADE. If a name does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		return decades[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String result = name + " [";
		for (int i = 0; i < NDECADES; i++) { 
			if (i == NDECADES - 1) { 
				result = result + decades[i];
			} else { 
				result = result + decades[i] + " ";
			}
		}
		return result + "]";
	}
}
