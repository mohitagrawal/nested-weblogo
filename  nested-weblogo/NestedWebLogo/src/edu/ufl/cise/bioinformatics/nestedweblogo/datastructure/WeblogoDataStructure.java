package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.ufl.cise.bioinformatics.nestedweblogo.LogoBoundry;
import edu.ufl.cise.bioinformatics.nestedweblogo.NestedWebLogoDataStructure;
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
	
	Map<String,NestedWebLogoDataStructure> nestedWeblogoMap = new HashMap<String, NestedWebLogoDataStructure>();
	
	private LogoBoundry logoBoundry;

	
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
		//double errorFactor;
		


		for(int i = 0;i< sequence[0].length();i++)
		{
			LinkedHashMap<String, Double> tempMap= new LinkedHashMap<String, Double>();
			//calculate uncertainiy at a index
			Double uncertanityAtIndex = 0.0, frequency;
			//iterate over frequency table map and add it
			buildFrequencyTable(i, sequence);
			
			
					WeblogoColumn column = new WeblogoColumn();
			tempMap = calculateHeightAtIndex(frequencyTable, sequence.length);
			column.setCharactersMap(tempMap);
			columnList.add(column);

			tempMap = null;
			//flush out frequency table
			frequencyTable.clear();
		}		
		printHeightTable();
	}
	
	public LinkedHashMap<String, Double> calculateHeightAtIndex(LinkedHashMap<String, Double> frequencyTable, int numOfSequences)
	{
		int s;
		if(sequenceType == SequenceType.DNA)
			s = 4;
		else
			s = 20;
		System.out.println(" s : "+s);
		Double sum = 0.0;
		Double errorFactor = 0.0; 
		errorFactor = (s -1)/((2*Math.log(2))*numOfSequences);
		System.out.println("Error Factor - "+errorFactor);

		Iterator mapIter = frequencyTable.keySet().iterator();
		//System.out.println("Size of frequency table - "+frequencyTable.size());
		while(mapIter.hasNext())
		{
			String key =(String) mapIter.next();
			Double frequency =  frequencyTable.get(key);	
			sum = sum + frequency;
		}
		LinkedHashMap<String, Double> one = new LinkedHashMap<String, Double>();
		Double uncertainityAtIndex = 0.0;
		int count = 0;
		Iterator mapIter2 = frequencyTable.keySet().iterator();
		while(mapIter2.hasNext())
		{
			String key = (String) mapIter2.next();
			Double frequency = frequencyTable.get(key);
			Double temp  = (frequency/sum)*((Math.log(frequency/sum))/(Math.log(2)));
			if(temp.isNaN() == true)
				temp = 0.0;
			one.put(key, temp);
			uncertainityAtIndex = uncertainityAtIndex + temp;
		//	System.out.println("ONE "+count+" : "+temp);
			count++;
		}
		
		Double informationAtIndex;
		informationAtIndex = (Math.log(s)/(Math.log(2))) - (-1*uncertainityAtIndex + errorFactor);
		//System.out.println("uncertainityAtIndex : "+uncertainityAtIndex);
		LinkedHashMap<String, Double> two = new LinkedHashMap<String, Double>();
		count = 0;
		Iterator mapIter3 = frequencyTable.keySet().iterator();
		while(mapIter3.hasNext())
		{
			String key = (String) mapIter3.next();
			Double frequency = frequencyTable.get(key);
			Double temp = frequency/sum*informationAtIndex;
			//System.out.println("TWO "+count+" :"+temp);
			count ++;
			if(temp>=0.0)
				two.put(key, temp);
		}	 	  	  	  	
		return two;

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
	
	public void clearNestedWebLogoMap(){
		
		nestedWeblogoMap.clear();
	}
	
	public void addEntryToNestedWebLogoMap(NestedWebLogoDataStructure nestedWebLogoDataStructure){
		
		String key = nestedWebLogoDataStructure.getWildCardPattern()+":"+nestedWebLogoDataStructure.getSourceWebLogoStartPosition()+":"
		+nestedWebLogoDataStructure.getSourceWebLogoEndPosition()+":"+nestedWebLogoDataStructure.getTargetWebLogoStartPosition()+":"
		+nestedWebLogoDataStructure.getTargetWebLogoEndPosition();
		
		nestedWeblogoMap.put(key, nestedWebLogoDataStructure);
	}

	public Map<String, NestedWebLogoDataStructure> getNestedWeblogoMap() {
		return nestedWeblogoMap;
	}

	public void setNestedWeblogoMap(Map<String, NestedWebLogoDataStructure> nestedWeblogoMap) {
		this.nestedWeblogoMap = nestedWeblogoMap;
	}


	public LogoBoundry getLogoBoundry() {
		return logoBoundry;
	}

	public void setLogoBoundry(LogoBoundry logoBoundry) {
		this.logoBoundry = logoBoundry;
	}	
	
}
