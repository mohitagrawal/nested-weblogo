package edu.ufl.cise.bioinformatics.nestedweblogo;

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



	public void setSourceWebLogo(WeblogoDataStructure sourceWebLogo, int start, int end) {
		this.sourceWebLogo = sourceWebLogo;
		this.sourceWebLogoStartPosition = start;
		this.sourceWebLogoEndPosition = end;
	}



	public WeblogoDataStructure getTargetWebLogo() {
		return targetWebLogo;
	}



	public void setTargetWebLogo(WeblogoDataStructure targetWebLogo, int start, int end) {
		this.targetWebLogo = targetWebLogo;
		this.targetWebLogoStartPosition = start;
		this.targetWebLogoEndPosition = end;
	}



	public int getSourceWebLogoStartPosition() {
		return sourceWebLogoStartPosition;
	}



	public void setSourceWebLogoStartPosition(int sourceWebLogoStartPosition) {
		this.sourceWebLogoStartPosition = sourceWebLogoStartPosition;
	}



	public int getSourceWebLogoEndPosition() {
		return sourceWebLogoEndPosition;
	}



	public void setSourceWebLogoEndPosition(int sourceWebLogoEndPosition) {
		this.sourceWebLogoEndPosition = sourceWebLogoEndPosition;
	}



	public int getTargetWebLogoStartPosition() {
		return targetWebLogoStartPosition;
	}



	public void setTargetWebLogoStartPosition(int targetWebLogoStartPosition) {
		this.targetWebLogoStartPosition = targetWebLogoStartPosition;
	}



	public int getTargetWebLogoEndPosition() {
		return targetWebLogoEndPosition;
	}



	public void setTargetWebLogoEndPosition(int targetWebLogoEndPosition) {
		this.targetWebLogoEndPosition = targetWebLogoEndPosition;
	}



	public String getWildCardPattern() {
		return wildCardPattern;
	}



	public void setWildCardPattern(String wildCardPattern) {
		this.wildCardPattern = wildCardPattern;
	}



	public static void main(String args[]){
		
	}
}
