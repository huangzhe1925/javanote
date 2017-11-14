package com.hz.javanote.stringtest;

import java.util.Formatter;

public class Receipt {

	private double total = 0;
	public static double TAX_RATE=0.06;
	private Formatter f=new Formatter(System.out);
	
	public void printTitle(){
		f.format("%-15s %5s %10s\n", "Item","Qty","Price");
		f.format("%-15s %5s %10s\n", "-----","-----","-----");
	}
	
	public void print(String name,int qty,double price){
		f.format("%-15.15s %5d %10.2f\n", name,qty,price);
		total+=price;
	}
	
	public void printTotal(){
		f.format("%-15s %5s %10.2f\n", "Tax","",total*TAX_RATE);
		f.format("%-15s %5s %10s\n", "","","-----");
		f.format("%-15s %5s %10.2f\n", "Total","",total*(TAX_RATE+1));
		
	}
	
	public static void main(String args[]){
		Receipt receipt=new Receipt();
		receipt.printTitle();
		receipt.print("Jack' Magic Beans", 4, 4.25);
		receipt.print("Princess Peas", 3, 5.1);
		receipt.print("Three Bears Porridge", 1, 14.29);
		receipt.printTotal();
		
	}
}
