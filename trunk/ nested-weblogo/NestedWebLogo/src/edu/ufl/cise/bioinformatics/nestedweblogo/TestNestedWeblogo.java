package edu.ufl.cise.bioinformatics.nestedweblogo;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.NestedWebLogoCreator;

public class TestNestedWeblogo 
{
	public static void main(String args[])
	{
		NestedWebLogoDataStructure nestedlogo = new NestedWebLogoDataStructure();
		NestedWebLogoCreator createnested = new NestedWebLogoCreator();
		nestedlogo = createnested.getNestedLogo(2, 4, 5, 7, "A.*");
	}	
}
