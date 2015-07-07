package com.fp;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

import com.fp.ConsistentHash.HashFunction;

public class Test {

	public static void main(String[] args) throws IOException {
		
		double sum=0,mean=0,median,min,max;
		 ArrayList<String> al = new ArrayList<String>(); 
		 ArrayList<Integer> userId = new ArrayList<Integer>();
		 ArrayList<Double> count = new ArrayList<Double>();
		 String servid;
		 for (int i=1;i<=100;i++){
			
				al.add(i+ "");	
		 }
			
			
	       
	         
			
			 for (int i=0;i<100000;i++){
				 Random ran = new Random();
					userId.add(ran.nextInt(100000));
					//System.out.println("test "+userId);
					} 

	        ConsistentHash<String> consistentHash = new ConsistentHash<String>(new HashFunction(), 1, al); 
		    HashMap<String, ArrayList> Hmap = new HashMap<String, ArrayList>();
		     //FileWriter file = new FileWriter("hello.txt");
	        for (Integer uId : userId)
	        {
	        	ArrayList<Integer> current = new ArrayList<Integer>();
	        	servid=consistentHash.get(uId);
	        	if(Hmap.containsKey(servid)){
	        		current=Hmap.get(servid);
	        		current.add(uId);
	        		}
	        	else{
	    
	        	         ArrayList<Integer> values = new ArrayList<Integer>();
	        	         values.add(uId);
	        	         Hmap.put(servid, values);
	        	     
	        	}
	        }
	        	
	        	/*System.out.println("server"+ servid + "-" + "Uid"+ uId + "\n");
	        	
	        System.out.println(Hmap);
	         file.write("Serv" + servid + "-" + "Uid" + uId + " " );
	       file.append('\n');
	        }
	        System.out.println("\n"+count+"\n");
	     
	           file.close();*/
	        for(Entry<String, ArrayList> entry : Hmap.entrySet()) {
	        	ArrayList<Integer> values = new ArrayList<Integer>();
	        	values=(ArrayList)entry.getValue();
	        	count.add((double) values.size());
	        	//System.out.println(entry.getKey() + "::"+ " "+ values.size());
	        }
	        Collections.sort(count);

	        for(Double value : count){
	        	System.out.println(value);
	        }
	        for(int i=0;i<count.size();i++)
	        {
	        	 
	        	sum+=count.get(i);
	        }
	         mean= sum/count.size();
	         System.out.println("MEAN"+"-"+mean);
	         int middle = count.size()/2;
	         if (count.size()%2 == 1) {
	             median= count.get(middle);
	         } else {
	             median=count.get(middle-1)+ count.get(middle) / 2.0;
	         }
	            System.out.println("MEDIAN-"+ median);
		        System.out.println("VALUE="+ count.size());
		        System.out.println("MIN="+ Collections.min(count));
		        System.out.println("MAX="+ Collections.max(count));
		        System.out.println("25th percentile="+ count.get(count.size()/4));
		        System.out.println("75th percentile="+ count.get((int) (count.size()*(0.75))));
	        }
	
	

}
