package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
	
	//Parses fasta file and returns string array of sequences.
	public String[] parseFasta(String filepath)
	{
		try 
		{
			String[] sequenceArray;		
			FileReader dataFile;
			String dataLine = new String();
			String dataString = new String();
			ArrayList<String> dataList = new ArrayList<String>();
			dataFile = new FileReader(filepath);
			BufferedReader dataReader = new BufferedReader(dataFile);							
			//Reading data file
			dataLine = dataReader.readLine();				
			while (dataLine != null) 
			{
				if(dataLine.trim().length()==0 )
				{
					dataLine = dataReader.readLine();						
				}						
				else if (dataLine.charAt(0) == '>') 
				{
					dataString = new String();
					dataLine = dataReader.readLine();
				} 
				else 
				{
					do 
					{
						dataString = dataString.concat(dataLine);
						dataLine = dataReader.readLine();
					} while ((dataLine != null)&& (dataLine.trim().length()!=0)&& (dataLine.charAt(0) != '>'));
					dataList.add(dataString);						
				}

			}
			sequenceArray = new String[dataList.size()];
			for(int i = 0; i < dataList.size();i++)
			{
				sequenceArray[i] = dataList.get(i);
			}
			return sequenceArray;
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			
		}		
	}

}
