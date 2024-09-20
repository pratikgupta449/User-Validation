package com.ty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;

public class ConnectionPage {

	static Connection con=null;

	static String Driver="com.mysql.cj.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/";
	static String db="db2";
	static String user="root";
	static String pwd="root";

	//creating arrayList to store multiple connection.
	static List<Connection> connectionPool=new ArrayList<Connection>();

	//defining size of ArrayList
	static int PoolSize=5;

	//creating static block which will execute when we run the program.
	static {

		try {
			Class.forName(Driver);
			for(int i=0;i<PoolSize;i++) {

				//creating connection method
				Connection con=createConnection();

				//adding connection into arrayList
				connectionPool.add(con);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//connection method
	public static Connection createConnection() {
		Connection con=null;
		try {
			con=DriverManager.getConnection(url+db,user,pwd);
			System.out.println("Connection cretaed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	} 

	//method to adding or removing connection from arrayList(connectionPool)
	public static Connection giveConnection() {
		if(!connectionPool.isEmpty()) {
			return connectionPool.remove(0);
		}
		else {
			Connection con=createConnection();
			return con;
		}
	}
	
	//method checking arrayList(connectionPool) having space or not if not then closing connection.
	public static void submitConnection(Connection con) {
		if(connectionPool.size()<PoolSize) {
			connectionPool.add(con);
		}
		else {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}






