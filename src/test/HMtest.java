package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HMtest {
	public static void main(String[] args) {
		HashMap<String,Double> hm = new HashMap<String,Double>();
		hm.put("zhao", (double)3);
		hm.put("yan", (double)1);
		hm.put("machine", (double) 2);
		double total = 0;
//	    Iterator it = hm.entrySet().iterator();
//	    while (it.hasNext()) {
//	        Map.Entry pair = (Map.Entry)it.next();
//	        hm.put((String)pair.getKey(), (double)pair.getValue() + 1);
//
//	        //it.remove(); // avoids a ConcurrentModificationException
//	    }
		for (String key : hm.keySet()) {
			total = total + hm.get(key)+ 1;
			hm.put(key, hm.get(key)+ 1);
		}
		for (String key : hm.keySet()) {
			hm.put(key, hm.get(key)/total);
			// ConcurrentModificationException
			//  it is not generally permissible for one thread to modify a Collection while another thread is iterating over it. 
//			if (key.equals("zhao")) {
//				hm.remove(key);
//			}     
		}
//		hm.remove("zhao");
		HashMap<String,Double> sortedhm = sortByValues(hm);
		
		List<Entry<String,Double>> entryList =
			    new ArrayList<Map.Entry<String, Double>>(sortedhm.entrySet());
		Entry<String, Double> lastEntry =
			    entryList.get(entryList.size()-2);
		
		
        System.out.println(lastEntry.getKey() + lastEntry.getValue());
        System.out.println(hm);
//        double x = 3.5; int y = 1;
//        System.out.format("%.04f", x+y);
        int[] aaa = new int[5];
        System.out.println(aaa[0]);
	}
	
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<String, Double> sortByValues(HashMap<String,Double> map) { 
		List<?> list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }

}
