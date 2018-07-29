package com.soft.application.configuration;

import com.matisse.MtDatabase;

public class DatabaseConfiguration {

	private static String hostname="DESKTOP-P46V427";  //hostname
	private static String databasename="auctiondb";  //database name
	
	//method to return matisse database connection
	public static MtDatabase getConnection(){
		MtDatabase db=new MtDatabase(hostname, databasename); //object of matisse database
		db.open();  //opening database
		return db;  //returning database connection
	}
}
