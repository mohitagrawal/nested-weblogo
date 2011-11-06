package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.ufl.cise.bioinformatics.nestedweblogo.utils.SequenceType;

/**
 * The Class WeblogoDataStructure.
 */
public class WeblogoDataStructure {
	
	/** The column list. */
	private ArrayList<WeblogoColumn> columnList;
	private LinkedHashMap<String, Double> frequencyTable;

	private SequenceType sequenceType = SequenceType.DNA;
	
	public WeblogoDataStructure() {
		// TODO Auto-generated constructor stub
		columnList = new ArrayList<WeblogoColumn>();
		frequencyTable = new LinkedHashMap<String, Double>();
	}
	/**
	 * Gets the column list.
	 *
	 * @return the column list
	 */
	public ArrayList<WeblogoColumn> getColumnList() {
		return columnList;
	}

	/**
	 * Sets the column list.
	 *
	 * @param columnList the new column list
	 */
	public void setColumnList(ArrayList<WeblogoColumn> columnList) {
		this.columnList = columnList;
		
	}
	//Builds frequency table for a particular index
	public void buildFrequencyTable(Integer index, String[] sequence)
	{
		double frequency;
		char key;
		for(int i = 0; i < sequence.length; i++)
		{			
			frequency = 1;
			key = sequence[i].charAt(index);
			if((!frequencyTable.isEmpty()))
			{
				if( (frequencyTable.containsKey(key)))
				{		
					frequency = frequencyTable.get(sequence[i].charAt(index));
					frequency++;
				}
				frequencyTable.put(key+"", frequency);
			}
		}
	}
	public void calculateHeight(String[] sequence)
	{
		for(int i = 0;i< sequence[0].length();i++)
		{
			LinkedHashMap<String, Double> tempMap= new LinkedHashMap<String, Double>();
			//calculate uncertainiy at a index
			Double uncertanityAtIndex = 0.0, errorFactor = 0.0, frequency;
			//iterate over frequency table map and add it
			buildFrequencyTable(i, sequence);
			Iterator mapIter = frequencyTable.entrySet().iterator();
			while(mapIter.hasNext())
			{
				frequency =  frequencyTable.get(mapIter.next());			
				uncertanityAtIndex = frequency * Math.log(frequency) + uncertanityAtIndex; 	
			}
			uncertanityAtIndex = uncertanityAtIndex * -1 ;
					
			//calculate information at a index
			double informationAtIndex;
			informationAtIndex = 2 - (uncertanityAtIndex + errorFactor);
			
			//multiply frequency of key with informationAtIndex
			while(mapIter.hasNext())
			{
				String key = (String) mapIter.next();
				frequency = frequencyTable.get(key);
				tempMap.put(key, frequency*informationAtIndex);						
			}
			WeblogoColumn column = new WeblogoColumn();
			column.setCharactersMap(tempMap);
			columnList.add(column);
			//flush out frequency table
			frequencyTable.clear();
		}		 				
	}
	
	public void printHeightTable()
	{
		int i = 0;
		while(i < columnList.size())
		{
			
			//String key = (String) mapIter.next();
		//	System.out.println("Character - "+key + " Height - "+map.get(key));						 	
		}
	}

	public SequenceType getSequenceType() {
		return sequenceType;
	}

	public void setSequenceType(SequenceType sequenceType) {
		this.sequenceType = sequenceType;
	}
}
