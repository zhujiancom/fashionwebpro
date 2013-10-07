package com.zj.common.utils;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.xwork.builder.EqualsBuilder;
import org.apache.commons.lang.xwork.builder.HashCodeBuilder;

public class Pair<K, V> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final K key;
	private final V value;
	
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(key).append(value).toHashCode();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj != null){
			Pair p = (Pair) obj;
			isEqual = new EqualsBuilder().append(key,p.getKey()).append(value, p.getValue()).isEquals();
		}
		return isEqual;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}
