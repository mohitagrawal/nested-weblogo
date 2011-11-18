package edu.ufl.cise.bioinformatics.nestedweblogo;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.NestedWebLogoCreator;
import edu.ufl.cise.bioinformatics.nestedweblogo.utils.Utilities;

public class TestNestedWeblogo 
{
	public static void main(String args[])
	{
		NestedWebLogoDataStructure nestedlogo = new NestedWebLogoDataStructure();
		NestedWebLogoCreator createnested = new NestedWebLogoCreator(new Utilities().getFilePath());
		nestedlogo = createnested.getNestedLogo(2, 4, 5, 7, "T.*");
		System.out.println("temp");
	}	
}
