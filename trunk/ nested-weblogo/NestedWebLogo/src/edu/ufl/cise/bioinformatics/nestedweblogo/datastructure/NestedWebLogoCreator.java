package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ufl.cise.bioinformatics.nestedweblogo.NestedWebLogoDataStructure;
import edu.ufl.cise.bioinformatics.nestedweblogo.utils.Utilities;

public class NestedWebLogoCreator {
	
	private String filePath;
	
	public NestedWebLogoCreator(String filePath){
		this.filePath = filePath;
	}
	
	public NestedWebLogoDataStructure getNestedLogo(int sourceStart, int sourceEnd, int targetStart, int targetEnd, String wildCard)
	{			
		sourceStart--;
		sourceEnd--;
		targetEnd--;
		targetStart--;
		
		WeblogoDataStructure sourceWeblogo = WeblogoDataStructure.getInstance();
		WeblogoDataStructure targetWeblogo = WeblogoDataStructure.getInstance();
		String[] inputSequences, sourceSubSequence, targetSubSequence, inputSourceSubSequences, inputTargetSubSequences;
		
		Utilities utili = new Utilities();		
//		String filePath = utili.getFilePath();
		
		inputSequences = utili.parseFasta(filePath);
		
		//Exception handling
		if(!((sourceStart < sourceEnd) && (sourceEnd <inputSequences[0].length())))
		{
			System.err.println("Invalid source start and end parameters. EXITING!!!");
			//return null;
		}
		if(!((targetStart < targetEnd)  && (targetStart > sourceEnd) && (targetEnd < inputSequences[0].length())))
		{
			System.err.println("Invalid target start and end parameters. EXITING!!!!");
			//return null;
		}
		//sourceSubSequence = makeSubSequence(sourceStart, sourceEnd, inputSequences);
		//targetSubSequence = makeSubSequence(targetStart, targetEnd, inputSequences);
		ArrayList<String> matchedSourceSubSequence = new ArrayList();
		ArrayList<String> matchedTargetSubSequence = new ArrayList();
		int i = 0;
		inputSourceSubSequences = new String[inputSequences.length];
		inputTargetSubSequences = new String[inputSequences.length];
		while(i < inputSequences.length)
		{			
			//matches input sequence with wild and then puts corresponding subsequence for source and target window
			inputSourceSubSequences[i] = (String) inputSequences[i].subSequence(sourceStart, sourceEnd+1);
			inputTargetSubSequences[i] = (String) inputSequences[i].subSequence(targetStart, targetEnd+1);
			if(IsMatch(inputSourceSubSequences[i], wildCard))
			{
					// {plus one in end is for substring
					matchedSourceSubSequence.add(inputSourceSubSequences[i]);
					matchedTargetSubSequence.add(inputTargetSubSequences[i]);
			}
			i++;
		}
		
		String[] matchedSourceString = new String[matchedSourceSubSequence.size()];
		String[] matchedTargetString = new String[matchedTargetSubSequence.size()];
		//Converting arraylist to String[] for weblogo creation
		i = 0;
		while (i < matchedSourceSubSequence.size())
		{
			matchedSourceString[i] = matchedSourceSubSequence.get(i);
			matchedTargetString[i] = matchedTargetSubSequence.get(i);
			i++;
		}
		System.out.println(" Source Matched String");
		printString(matchedSourceString);
		System.out.println(" Target Matched String");
		printString(matchedTargetString);
		//crreating source and target weblogo
		sourceWeblogo.calculateHeight(matchedSourceString);
		targetWeblogo.calculateHeight(matchedTargetString);
		NestedWebLogoDataStructure nestedWeblogo = new NestedWebLogoDataStructure();
		nestedWeblogo.setSourceWebLogo(sourceWeblogo);
		nestedWeblogo.setSourceWebLogoEndPosition(sourceEnd+1);
		nestedWeblogo.setSourceWebLogoStartPosition(sourceStart+1);
		nestedWeblogo.setTargetWebLogo(targetWeblogo);
		nestedWeblogo.setTargetWebLogoEndPosition(targetEnd+1);
		nestedWeblogo.setTargetWebLogoStartPosition(targetStart+1);
		nestedWeblogo.setWildCardPattern(wildCard);
		return nestedWeblogo;
	}
		public String[] makeSubSequence(int start, int end, String[] inputSequences)
		{
			String[] subSequence = new String[inputSequences.length];	
			int i =0;
			while (i < inputSequences.length) 
			{
				subSequence[i].subSequence(start, end);
			}
			return subSequence;
			
		}
	   private static boolean IsMatch(String s, String pattern) {
	        try {
	            Pattern patt = Pattern.compile(pattern);
	            Matcher matcher = patt.matcher(s);
	            return matcher.matches();
	        } catch (RuntimeException e) {
	        return false;
	    }           
	}
	   public void printString(String[] toPrint)
	   {
		   int i = 0;
		   while(i < toPrint.length)
		   {
			   System.out.println(i+" "+toPrint[i]);
			   i++;
		   }
	   }
	

}
