package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

class ValueComparator implements Comparator {

	  TreeMap base;
	  public ValueComparator(TreeMap base) {
	      this.base = base;
	  }

	  public int compare(Object a, Object b) {

	    if((Double)base.get(a) > (Double)base.get(b)) {
	      return 1;
	    } else if((Double)base.get(a) == (Double)base.get(b)) {
	      return 0;
	    } else {
	      return -1;
	    }
	  }
	}
