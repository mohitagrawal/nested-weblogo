package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.ArrayList;

import edu.ufl.cise.bioinformatics.nestedweblogo.utils.SequenceType;

/**
 * The Class WeblogoDataStructure.
 */
public class WeblogoDataStructure {
	
	/** The column list. */
	private ArrayList<WeblogoColumn> columnList;

	private SequenceType sequenceType = SequenceType.DNA;
	
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

	public SequenceType getSequenceType() {
		return sequenceType;
	}

	public void setSequenceType(SequenceType sequenceType) {
		this.sequenceType = sequenceType;
	}
}
