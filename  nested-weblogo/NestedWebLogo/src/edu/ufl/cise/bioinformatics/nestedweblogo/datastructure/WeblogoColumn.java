package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


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
		 
			
	}
	public LinkedHashMap<String, Double> getCharactersMap() {
		return charactersMap;
	}

	/**
	 * Sets the characters map.
	 *
	 * @param charactersMap the characters map
	 */
	//character map is char to map height column wise.
	public void setCharactersMap(LinkedHashMap<String, Double> charactersMap) {
		
		
		Set<Entry<String,Double>> entrySet = charactersMap.entrySet();
		
		Entry<String,Double>[] tempArr =  new Entry[charactersMap.size()];
		tempArr = entrySet.toArray(tempArr);
		
		for(int i=0 ; i<tempArr.length ; i++){
			for(int j=i+1 ; j<tempArr.length ; j++){
				if(tempArr[i].getValue().compareTo(tempArr[j].getValue()) == 1 ){
					Entry<String,Double> tempEntry = tempArr[i];
					tempArr[i] = tempArr[j];
					tempArr[j] = tempEntry;
				}
			}
		}
		
		
		
		//To make valuecomparator object working.
		this.charactersMap = new LinkedHashMap<String, Double>();

		for(int i=0 ; i<tempArr.length ; i++){
			this.charactersMap.put(tempArr[i].getKey(), tempArr[i].getValue());
		}
		
	}
	
	public void printCharactersMap()
	{
		Iterator mapIter = charactersMap.keySet().iterator();
	//	System.out.println("Map Size = "+charactersMap.size());
		while(mapIter.hasNext())
		{
			String key = (String) mapIter.next();
			System.out.println("Character - "+key + " Height - "+charactersMap.get(key));
		}
	}
	
}
