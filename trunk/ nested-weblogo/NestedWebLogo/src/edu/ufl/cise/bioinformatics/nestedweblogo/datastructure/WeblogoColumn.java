package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class WeblogoColumn.
 */
public class WeblogoColumn {
	
	/** The characters map. */
	private LinkedHashMap<String,Float> charactersMap;

	/**
	 * Gets the characters map.
	 *
	 * @return the characters map
	 */
	public LinkedHashMap<String, Float> getCharactersMap() {
		return charactersMap;
	}

	/**
	 * Sets the characters map.
	 *
	 * @param charactersMap the characters map
	 */
	public void setCharactersMap(LinkedHashMap<String, Float> charactersMap) {
		this.charactersMap = charactersMap;
	}
	
}
