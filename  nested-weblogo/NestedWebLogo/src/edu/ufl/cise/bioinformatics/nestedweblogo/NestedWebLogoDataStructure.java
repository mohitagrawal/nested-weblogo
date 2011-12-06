package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoDataStructure;

public class NestedWebLogoDataStructure {
	
	WeblogoDataStructure sourceWebLogo;
	
	WeblogoDataStructure targetWebLogo;
	
	private int sourceWebLogoStartPosition;
	
	private int sourceWebLogoEndPosition;
	
	private int targetWebLogoStartPosition;
	
	private int targetWebLogoEndPosition;
	
	
	private String wildCardPattern;

	public WeblogoDataStructure getSourceWebLogo() {
		return sourceWebLogo;
	}



	public void setSourceWebLogo(WeblogoDataStructure sourceWebLogo) {
		this.sourceWebLogo = sourceWebLogo;		
	}



	public WeblogoDataStructure getTargetWebLogo() {
		return targetWebLogo;
	}



	public void setTargetWebLogo(WeblogoDataStructure targetWebLogo) {
		this.targetWebLogo = targetWebLogo;
	}



	/*public int getSourceWebLogoStartPosition() {
		return sourceWebLogo.getStartPosition();
	}



	public void setSourceWebLogoStartPosition(int sourceWebLogoStartPosition) {
		sourceWebLogo.setStartPosition(sourceWebLogoStartPosition);
//		this.sourceWebLogoStartPosition = sourceWebLogoStartPosition;
	}



	public int getSourceWebLogoEndPosition() {
		return sourceWebLogo.getEndPosition();
	}



	public void setSourceWebLogoEndPosition(int sourceWebLogoEndPosition) {
		sourceWebLogo.setEndPosition(sourceWebLogoEndPosition);
//		this.sourceWebLogoEndPosition = sourceWebLogoEndPosition;
	}



	public int getTargetWebLogoStartPosition() {
		return targetWebLogo.getStartPosition();
	}



	public void setTargetWebLogoStartPosition(int targetWebLogoStartPosition) {
		targetWebLogo.setStartPosition(targetWebLogoStartPosition);
//		this.targetWebLogoStartPosition = targetWebLogoStartPosition;
	}



	public int getTargetWebLogoEndPosition() {
		return targetWebLogo.getEndPosition();
	}



	public void setTargetWebLogoEndPosition(int targetWebLogoEndPosition) {
		targetWebLogo.setEndPosition(targetWebLogoEndPosition);
//		this.targetWebLogoEndPosition = targetWebLogoEndPosition;
	}*/



	public String getWildCardPattern() {
		return wildCardPattern;
	}



	public void setWildCardPattern(String wildCardPattern) {
		this.wildCardPattern = wildCardPattern;
	}

	

	public static void main(String args[]){
		
	}
}
