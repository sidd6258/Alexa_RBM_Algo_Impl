package db;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbConnection {
	
	private static void writeToFile(List<String> list, String path) {
        BufferedWriter out = null;
        try {
                File file = new File(path);
                file.delete();
                out = new BufferedWriter(new FileWriter(file, false));
                for (String s : list) {
                        out.write(s);
                        out.newLine();

                }
                out.close();
        } catch (IOException e) {
        }
}
	
	public static void RatingsFileGen(String module) throws IOException {
	      try (
	         // Step 1: Allocate a database 'Connection' object
	         Connection conn = (Connection) DriverManager.getConnection(
	               "jdbc:mysql://ainuco.ddns.net:4326/iTravelDB?useSSL=false", "remoteuser", "ita295");
	               // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
	 
	         // Step 2: Allocate a 'Statement' object in the Connection
	         Statement stmt = conn.createStatement();
	      ) {
	         // Step 3: Execute a SQL SELECT query, the query result
	         //  is returned in a 'ResultSet' object.
	         String strSelect = "select * from booking where module=\""+module+"\"";
	         System.out.println("The SQL query is: " + strSelect); // Echo For debugging
	         System.out.println();
	 
	         ResultSet rset = stmt.executeQuery(strSelect);
	 
	         // Step 4: Process the ResultSet by scrolling the cursor forward via next().
	         //  For each row, retrieve the contents of the cells with getXxx(columnName).
	         System.out.println("The records selected are:");
	         int rowCount = 0;
	         List<String> data=new ArrayList<>();
	         
	         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	            String userId = rset.getString("email");
	            String itemId = rset.getString("mongo_id");
	            int   rating   = rset.getInt("rating");
	            String str=userId + " "+itemId+" "+rating;
	            data.add(str);
	            ++rowCount;
	         }
	         writeToFile(data, "data/"+module+"_booking.txt");
	         rset.close();
	         stmt.close();
	         System.out.println("Total number of records added to file = " + rowCount);
	 
	      } catch(SQLException ex) {
	         ex.printStackTrace();
	      }
	      // Step 5: Close the resources - Done automatically by try-with-resources
	   }
}
