package com.fp;

import java.math.BigInteger;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {

	private final HashFunction hashFunction;
	private final int numberOfReplicas;
	public final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
			Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;

		for (T node : nodes) {
			add(node);
		}
	}

	/*public void add(String node) {
		circle.put(hashFunction.hash(node.ip), node);
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.put(hashFunction.hash(node.ip + i), node);
		}
	}

*/
	  public void add(T node) {
	        for (int i = 0; i < numberOfReplicas; i++) {
	            circle.put(hashFunction.hash(node.toString() + i),
	                    node);
	           // System.out.println(circle + "HSS");
	        }
	    }
	
	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
	}

	public String get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		int hash = hashFunction.hash(key);
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		
		return (String) circle.get(hash);
	}


	static class HashFunction {
		int hash(Object key) {
			
			//System.out.println(MD5.md5(key.toString()).hashCode());
			return (int) ((MD5.md5(key.toString()).hashCode())%(Math.pow(2,32)));
			//return Math.abs((new BigInteger(MD5.md5(key.toString()),16).intValue())% (2^32))	;
		}

	
	}
}
