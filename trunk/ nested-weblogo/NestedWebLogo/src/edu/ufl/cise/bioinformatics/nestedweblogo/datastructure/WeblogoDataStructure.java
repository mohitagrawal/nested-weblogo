package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.ufl.cise.bioinformatics.nestedweblogo.utils.SequenceType;
import edu.ufl.cise.bioinformatics.nestedweblogo.utils.Utilities;

/**
 * The Class WeblogoDataStructure.
 */
public class WeblogoDataStructure {
	
	/** The column list. */
	
	private ArrayList<WeblogoColumn> columnList;

	private LinkedHashMap<String, Double> frequencyTable;
	
	private ArrayList<String> sequences;

	private SequenceType sequenceType = SequenceType.DNA;
	
	public double leftXPosition;
	public double bottomYPosition;
	public double rightXPosition;
	public double topXPosition;
	
	private int weblogoID;
	
	private static Integer counter = 0;
	
	public static WeblogoDataStructure getInstance(){
		WeblogoDataStructure instance = new WeblogoDataStructure();
		instance.init();
		counter++;
		instance.weblogoID = counter;
		
		return instance;
	}
	
	
	WeblogoDataStructure() {
		
	}
	
	
	
	private void init(){
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
			if((frequencyTable.containsKey(key+"")))
				{		
					frequency = frequencyTable.get(key+"");
					frequency++;
				}
				frequencyTable.put(key+"", frequency);
			
		}
	}
	public void calculateHeight(String[] sequence)
	{
		// value of s for dna is 4 and protein is 20
		int s;
		//source wiki
		if(sequenceType == SequenceType.DNA)
			s = 4;
		else
			s = 20;
		//calculate error factor
		double errorFactor;
		errorFactor = (s -1)/(2*Math.log(2)*sequence.length);
		System.out.println("Error Factor - "+errorFactor);
		for(int i = 0;i< sequence[0].length();i++)
		{
			LinkedHashMap<String, Double> tempMap= new LinkedHashMap<String, Double>();
			//calculate uncertainiy at a index
			Double uncertanityAtIndex = 0.0, frequency;
			//iterate over frequency table map and add it
			buildFrequencyTable(i, sequence);
			Iterator mapIter = frequencyTable.keySet().iterator();
			//System.out.println("Size of frequency table - "+frequencyTable.size());
			while(mapIter.hasNext())
			{
				String key =(String) mapIter.next();
				frequency =  frequencyTable.get(key);	
				//System.out.println(frequency);
				uncertanityAtIndex = frequency * (Math.log(frequency)/Math.log(2)) + uncertanityAtIndex; 	
			}
			uncertanityAtIndex = uncertanityAtIndex * -1 ;
			//calculate error factor
			//calculate information at a index
			double informationAtIndex;
			if(sequenceType == SequenceType.DNA)
				informationAtIndex = 2 - (uncertanityAtIndex + errorFactor);
			else
				// log 20 base 2
				informationAtIndex = (Math.log(20)/Math.log(2)) - (uncertanityAtIndex + errorFactor);
			
			//multiply frequency of key with informationAtIndex
			mapIter = frequencyTable.keySet().iterator();
			while(mapIter.hasNext())
			{
				String key = (String) mapIter.next();
				frequency = frequencyTable.get(key);
				
				//** To check for negative information frequency
				if(informationAtIndex>=0)
					tempMap.put(key, frequency*informationAtIndex);						
			}
			WeblogoColumn column = new WeblogoColumn();
			column.setCharactersMap(tempMap);
			columnList.add(column);
			
			tempMap = null;
			//flush out frequency table
			frequencyTable.clear();
		}		 				
	}
	
	public void printHeightTable()
	{
		int i = 0;
		System.out.println("Total Positions - "+columnList.size());
		while(i < columnList.size())
		{
			System.out.println("Index - "+i);
			columnList.get(i).printCharactersMap();
			i++;
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
	
	public static void main(String args[]){
		Utilities utility = new Utilities();
		utility.getWeblogoDS(utility.getFilePath()).printHeightTable();
	}


	public ArrayList<String> getSequences() {
		return sequences;
	}


	public void setSequences(ArrayList<String> sequences) {
		this.sequences = sequences;
	}
	
	
	
}
