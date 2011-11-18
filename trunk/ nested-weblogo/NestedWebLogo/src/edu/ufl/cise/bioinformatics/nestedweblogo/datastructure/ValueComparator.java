package edu.ufl.cise.bioinformatics.nestedweblogo.datastructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class ValueComparator implements Comparator {

	  HashMap<String, Double> base;
	  public ValueComparator(TreeMap<String, Double> base1) {
//	      this.base = base;
		  base = new HashMap<String, Double>();
		  Set<String> keys = base1.keySet();
		  
		  for (String key : keys) {
			base.put(new String(key), base1.get(key).doubleValue());
		}
	  }

	  public int compare(Object a, Object b) {
//		  System.out.println("a: "+a);
//		  System.out.println("b: "+b);
		  
		  Double value1 = base.get(a);
		  Double value2 = base.get(b);
		  
		  System.out.println(a+ " < "+b);
		  
		  if(a.equals(b)){
			  return 0;
		  }
		  
		  if(value1.doubleValue() < value2.doubleValue()){
			  return -1;
		  }else{
			  return 1;
		  }
		  
		  /*if(value1.compareTo(value2) == 0){
			  return -1;
		  }else{
			  return value1.compareTo(value2);
		  }		  */
//		  return value1.compareTo(value2);
	  }
	}
