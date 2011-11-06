package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


// TODO: Auto-generated Javadoc
/**
 * The Class WeblogoColumn.
 */
public class WeblogoColumn {
	
	/** The characters map. */
	
	
	private LinkedHashMap<String,Double> charactersMap;

	/**
	 * Gets the characters map.
	 *
	 * @return the characters map
	 */
	
	public WeblogoColumn()
	{
		charactersMap = new LinkedHashMap<String, Double>();	
	}
	public LinkedHashMap<String, Double> getCharactersMap() {
		return charactersMap;
	}

	/**
	 * Sets the characters map.
	 *
	 * @param charactersMap the characters map
	 */
	public void setCharactersMap(LinkedHashMap<String, Double> charactersMap) {
		this.charactersMap = charactersMap;
	}
	
	public void printCharactersMap()
	{
		Iterator mapIter = charactersMap.entrySet().iterator();
		while(mapIter.hasNext())
		{
			String key = (String) mapIter.next();
			System.out.println("Character - "+key + " Height - "+charactersMap.get(key));
		}
	}
	
}
