package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.TNetwork.magazzino.model.Delivery;

public class test {
	
	/*
	
	public static void main(String[] args) {
		
		Delivery del = new Delivery();
		
		del.setnDelivery("123");
		del.setnDelivery("123456");
		del.setDataConsegna("23/5/09");
		
		String s = null;
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			s = mapper.writeValueAsString(del);
			System.out.println(s);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Delivery temp = null;
		
		try {
			temp = mapper.readValue(s, Delivery.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(temp);
		
	}
	*/

}
