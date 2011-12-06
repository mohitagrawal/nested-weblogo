package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ufl.cise.bioinformatics.nestedweblogo.NestedWebLogoDataStructure;
import edu.ufl.cise.bioinformatics.nestedweblogo.utils.Utilities;

public class NestedWebLogoCreator {

	private String filePath;

	public NestedWebLogoCreator(String filePath) {
		this.filePath = filePath;
	}
	
	public void createnestedWebLogo(WeblogoDataStructure webLogo){
		
	}
	
	
	public void createWebLogo(WeblogoDataStructure webLogo){
		if(webLogo == null){
			return;
		}
		System.out.println("Start: "+webLogo.getStartPosition());
		System.out.println("End  : "+webLogo.getEndPosition());
		Map<String, NestedWebLogoDataStructure> map = webLogo.getNestedWeblogoMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			createNestedWebLogo(map.get(key),webLogo.getSequences());
		}
	}
	
	public void createNestedWebLogo(NestedWebLogoDataStructure nestedWebLogo,String[] sequences){
		System.out.println("Nested web logo : wild card : "+nestedWebLogo.getWildCardPattern());
		nestedWebLogo = getNestedLogo(nestedWebLogo, nestedWebLogo.getWildCardPattern(), sequences);
		createWebLogo(nestedWebLogo.getSourceWebLogo());
		createWebLogo(nestedWebLogo.getTargetWebLogo());
	}

	public NestedWebLogoDataStructure getNestedLogo(NestedWebLogoDataStructure nestedWebLogo, String wildCard, String[] characterSequences) {
		WeblogoDataStructure sourceWeblogo = nestedWebLogo.getSourceWebLogo();
		WeblogoDataStructure targetWeblogo = nestedWebLogo.getTargetWebLogo();
		
		int sourceStart = sourceWeblogo.getStartPosition() - 1;
		int sourceEnd = sourceWeblogo.getEndPosition() - 1;
		int targetEnd = targetWeblogo.getEndPosition() - 1;
		int targetStart = targetWeblogo.getStartPosition() -1;

		String[] inputSequences, inputSourceSubSequences, inputTargetSubSequences;

		Utilities utili = new Utilities();
		// String filePath = utili.getFilePath();
		
		if(wildCard == null || wildCard.trim().length() == 0){
			wildCard = "*";
		}

		if(characterSequences == null){
			inputSequences = utili.parseFasta(filePath);
		}else{
			inputSequences = characterSequences;
		}		

		// Exception handling
		if (!((sourceStart < sourceEnd) && (sourceEnd < inputSequences[0].length()))) {
			System.err.println("Invalid source start and end parameters. EXITING!!!");
			// return null;
		}
		if (!((targetStart < targetEnd) && (targetStart > sourceEnd) && (targetEnd < inputSequences[0].length()))) {
			System.err.println("Invalid target start and end parameters. EXITING!!!!");
			// return null;
		}

		ArrayList<String> matchedSourceSubSequence = new ArrayList<String>();
		ArrayList<String> matchedTargetSubSequence = new ArrayList<String>();
		
		int i = 0;
		inputSourceSubSequences = new String[inputSequences.length];
		inputTargetSubSequences = new String[inputSequences.length];
		while (i < inputSequences.length) {
			// matches input sequence with wild and then puts corresponding
			// subsequence for source and target window
			inputSourceSubSequences[i] = (String) inputSequences[i].subSequence(sourceStart, sourceEnd + 1);
			inputTargetSubSequences[i] = (String) inputSequences[i].subSequence(targetStart, targetEnd + 1);
			if (IsMatch(inputSourceSubSequences[i], wildCard)) {
				// {plus one in end is for substring
				matchedSourceSubSequence.add(inputSourceSubSequences[i]);
				matchedTargetSubSequence.add(inputTargetSubSequences[i]);
			}
			i++;
		}

		String[] matchedSourceString = new String[matchedSourceSubSequence.size()];
		String[] matchedTargetString = new String[matchedTargetSubSequence.size()];
		// Converting arraylist to String[] for weblogo creation
		i = 0;
		while (i < matchedSourceSubSequence.size()) {
			matchedSourceString[i] = matchedSourceSubSequence.get(i);
			matchedTargetString[i] = matchedTargetSubSequence.get(i);
			i++;
		}

		System.out.println(" Source Matched String");
		printString(matchedSourceString);
		System.out.println(" Target Matched String");
		printString(matchedTargetString);

		// crreating source and target weblogo

		sourceWeblogo.calculateHeight(matchedSourceString);
		targetWeblogo.calculateHeight(matchedTargetString);

		sourceWeblogo.setSequences(matchedSourceString);
		nestedWebLogo.setSourceWebLogo(sourceWeblogo);
		nestedWebLogo.getSourceWebLogo().setEndPosition(sourceEnd + 1);
		nestedWebLogo.getSourceWebLogo().setStartPosition(sourceStart + 1);

		targetWeblogo.setSequences(matchedTargetString);
		nestedWebLogo.setTargetWebLogo(targetWeblogo);
		nestedWebLogo.getTargetWebLogo().setEndPosition(targetEnd + 1);
		nestedWebLogo.getTargetWebLogo().setStartPosition(targetStart + 1);
		nestedWebLogo.setWildCardPattern(wildCard);
		return nestedWebLogo;
	}
	
	public NestedWebLogoDataStructure getNestedLogo(int sourceStart, int sourceEnd, int targetStart, int targetEnd, String wildCard, String[] characterSequences) {
		sourceStart--;
		sourceEnd--;
		targetEnd--;
		targetStart--;

		WeblogoDataStructure sourceWeblogo = WeblogoDataStructure.getInstance();
		WeblogoDataStructure targetWeblogo = WeblogoDataStructure.getInstance();
		String[] inputSequences, sourceSubSequence, targetSubSequence, inputSourceSubSequences, inputTargetSubSequences;

		Utilities utili = new Utilities();
		// String filePath = utili.getFilePath();

		if(characterSequences == null){
			inputSequences = utili.parseFasta(filePath);
		}else{
			inputSequences = characterSequences;
		}		

		// Exception handling
		if (!((sourceStart < sourceEnd) && (sourceEnd < inputSequences[0].length()))) {
			System.err.println("Invalid source start and end parameters. EXITING!!!");
			// return null;
		}
		if (!((targetStart < targetEnd) && (targetStart > sourceEnd) && (targetEnd < inputSequences[0].length()))) {
			System.err.println("Invalid target start and end parameters. EXITING!!!!");
			// return null;
		}
		// sourceSubSequence = makeSubSequence(sourceStart, sourceEnd,
		// inputSequences);
		// targetSubSequence = makeSubSequence(targetStart, targetEnd,
		// inputSequences);
		ArrayList<String> matchedSourceSubSequence = new ArrayList();
		ArrayList<String> matchedTargetSubSequence = new ArrayList();
		int i = 0;
		inputSourceSubSequences = new String[inputSequences.length];
		inputTargetSubSequences = new String[inputSequences.length];
		while (i < inputSequences.length) {
			// matches input sequence with wild and then puts corresponding
			// subsequence for source and target window
			inputSourceSubSequences[i] = (String) inputSequences[i].subSequence(sourceStart, sourceEnd + 1);
			inputTargetSubSequences[i] = (String) inputSequences[i].subSequence(targetStart, targetEnd + 1);
			if (IsMatch(inputSourceSubSequences[i], wildCard)) {
				// {plus one in end is for substring
				matchedSourceSubSequence.add(inputSourceSubSequences[i]);
				matchedTargetSubSequence.add(inputTargetSubSequences[i]);
			}
			i++;
		}

		String[] matchedSourceString = new String[matchedSourceSubSequence.size()];
		String[] matchedTargetString = new String[matchedTargetSubSequence.size()];
		// Converting arraylist to String[] for weblogo creation
		i = 0;
		while (i < matchedSourceSubSequence.size()) {
			matchedSourceString[i] = matchedSourceSubSequence.get(i);
			matchedTargetString[i] = matchedTargetSubSequence.get(i);
			i++;
		}

		System.out.println(" Source Matched String");
		printString(matchedSourceString);
		System.out.println(" Target Matched String");
		printString(matchedTargetString);

		// crreating source and target weblogo

		sourceWeblogo.calculateHeight(matchedSourceString);
		targetWeblogo.calculateHeight(matchedTargetString);
		NestedWebLogoDataStructure nestedWeblogo = new NestedWebLogoDataStructure();

		sourceWeblogo.setSequences(matchedSourceString);
		nestedWeblogo.setSourceWebLogo(sourceWeblogo);
		nestedWeblogo.getSourceWebLogo().setEndPosition(sourceEnd + 1);
		nestedWeblogo.getSourceWebLogo().setStartPosition(sourceStart + 1);

		targetWeblogo.setSequences(matchedTargetString);
		nestedWeblogo.setTargetWebLogo(targetWeblogo);
		nestedWeblogo.getTargetWebLogo().setEndPosition(targetEnd + 1);
		nestedWeblogo.getTargetWebLogo().setStartPosition(targetStart + 1);
		nestedWeblogo.setWildCardPattern(wildCard);
		return nestedWeblogo;
	}

	public String[] makeSubSequence(int start, int end, String[] inputSequences) {
		String[] subSequence = new String[inputSequences.length];
		int i = 0;
		while (i < inputSequences.length) {
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

	public void printString(String[] toPrint) {
		int i = 0;
		while (i < toPrint.length) {
			System.out.println(i + " " + toPrint[i]);
			i++;
		}
	}

}
