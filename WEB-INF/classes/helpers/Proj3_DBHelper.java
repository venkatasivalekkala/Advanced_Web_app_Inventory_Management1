

package helpers;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Proj3_DBHelper implements java.io.Serializable 
{
	private static  String user = "jadrn013";
	private static  String password = "simple";
	private static  String database = "jadrn013";
	private static  String connectionURL = "jdbc:mysql://opatija:3306/" + database +
			"?user=" + user + "&password=" + password;
	
	public static int getOnHandValue(String sku) 
	{
		String query = "SELECT on_hand_quantity from on_hand WHERE sku='"+sku+"'";
		String quant = queryProduct(query);
		
		if(quant != null && quant.isEmpty() == false) 
		{
			return Integer.parseInt(quant);
		}
		return -1;
	}
	
	public static boolean updateOnHandTable(String sku,String dateReceived,String quantity, boolean isIn) 
	{
		int onHandQuant = getOnHandValue(sku);
		int quant;
		
		if(onHandQuant > -1) 
		{
			if(isIn) {
				quant = Integer.parseInt(quantity)+onHandQuant;
			} else {
				quant = onHandQuant-Integer.parseInt(quantity);
			}
			//update
			updateValues(sku, dateReceived, String.valueOf(quant), "on_hand");
		}
		else 
		{
			//insert
			insertValues(sku, dateReceived, quantity, "on_hand");
		}
		return true;
	}

	public static boolean insertValues (String sku,String dateReceived,String quantity, String tableName)
	{
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Vector<String[]> v = new Vector<String[]>();  
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		java.sql.Date prodDate = null;

		try 
		{
			java.util.Date date = formatter.parse(dateReceived);
			prodDate = new java.sql.Date(date.getTime());  
		} 
		catch (Exception e) 
		{
			return false;
		}   

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			String query = "";
			
			if(tableName == "on_hand") 
			{
				 query = " insert into "+tableName+" (sku, last_date_modified, on_hand_quantity)"
						+ " values (?, ?, ?)";
			} 
			else
			 {
				 query = " insert into "+tableName+" (sku, date, qty)"
					+ " values (?, ?, ?)";
			 }

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,sku);
			preparedStatement.setDate(2, prodDate);
			preparedStatement.setInt(3,Integer.parseInt(quantity));
			preparedStatement.executeUpdate();
		}
		catch (Exception e) 
		{
			return false;
		}
		
		finally 
		{
			try 
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}                
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return true;
	}

	public static boolean updateValues (String sku,String dateReceived,String quantity, String tableName)
	{
		PreparedStatement preparedStatement;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Vector<String[]> v = new Vector<String[]>();  
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		java.sql.Date prodDate = null;

		try 
		{
			java.util.Date date = formatter.parse(dateReceived);
			prodDate = new java.sql.Date(date.getTime());  
		} 
		catch (Exception e) 
		{
		 	return false;
		}   

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			String query="";
			
			if(tableName == "on_hand") 
			{
				query = " update "+tableName+" set last_date_modified=?, on_hand_quantity=? where sku=?";
			} 
			else 
			{
				query = " update "+tableName+" set date=?, qty=? where sku=?";
			}
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(3,sku);
			preparedStatement.setDate(1, prodDate);
			preparedStatement.setInt(2,Integer.parseInt(quantity));
			preparedStatement.executeUpdate();
		}
		catch (Exception e) 
		{
			return false;
		}
		return true;
	}
   
	public static Vector<String []> doQuery(String s) 
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Vector<String[]> v = new Vector<String[]>();        

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(s);

			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();

			while(resultSet.next()) 
			{
				String [] tmp = new String[numCols];
				for(int i=0; i < numCols; i++)
					tmp[i] = resultSet.getString(i+1);  // resultSet getString is 1 based
				v.add(tmp);                
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}         
		finally 
		{
			try 
			{
			if(resultSet != null) {
				resultSet.close();
				}
				if(statement != null) {
				statement.close();   
				}
				if(connection != null) {             
				connection.close();
				}
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return v;
	}

	public static String queryProducts(String s) 
	{	
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String result = "";  
		String temp="";  
		String localTemp="";
		    

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();

			if(resultSet == null) 
			{
				return null;
			}

			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();
			
			while(resultSet.next()) 
			{
				for(int i=0; i < numCols; i++)
				{
					String sku="";
					if(i == 0) 
					{
						sku = resultSet.getString(1);
						PreparedStatement localPreparedStatement =connection.prepareStatement("select vendor.name, category.name,product.vendorModel, product.image FROM product inner join vendor ON vendor.vendorID=product.venID inner join  category ON category.categoryID = product.catID WHERE product.sku='"+sku+"'");
                		ResultSet localResultSet = localPreparedStatement.executeQuery();
                		ResultSetMetaData localMd = localResultSet.getMetaData();
                
			   			int localNumCol = localMd.getColumnCount();
			    
						while(localResultSet.next()) 
						{
							for(int j=0; j < localNumCol; j++)
							{
								if(temp.isEmpty() || j==0) 
								{
									temp = temp+localResultSet.getString(j+1); 
								} 
								else 
								{
									temp = temp+"|"+localResultSet.getString(j+1);
								} 
							}  
						}

						try 
						{
							localResultSet.close(); 
							localPreparedStatement.close();              
						}
						catch(SQLException e) {}  // don't do anything if the connection is not open.
					}
					
					if(temp.isEmpty()) 
					{
						temp = temp+resultSet.getString(i+1); 
					} 
					else 
					{
						temp = temp+"|"+resultSet.getString(i+1);
					}  
				}
				temp = temp+"||";            
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}         
		finally 
		{
			try 
			{
				resultSet.close(); 
				preparedStatement.close();              
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return temp;
	}

	public static String queryProduct(String s) 
	{	
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String result = "";        

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(s);

			if(resultSet == null) 
			{
				return null;
			}

			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();

			while(resultSet.next()) 
			{
				String temp="";
				for(int i=0; i < numCols; i++)
				{
					if(temp.isEmpty()) 
					{
						temp = temp+resultSet.getString(i+1); 
					} 
					else 
					{
						temp = temp+"|"+resultSet.getString(i+1);
					} 
				}
				result = temp;               
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}         
		finally 
		{
			try 
			{
				resultSet.close();
				statement.close();                
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return result;
	}
	
	public static ArrayList<String> queryProductDetails(String s) 
	{	
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String result = "";        
		ArrayList<String> product = new ArrayList<String>();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(s);

			if(resultSet == null) 
			{
				return null;
			}

			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();

			while(resultSet.next()) 
			{
				String temp="";
				for(int i=0; i < numCols; i++)
				{
				product.add(resultSet.getString(i+1));
// 					if(temp.isEmpty()) 
// 					{
// 						temp = temp+resultSet.getString(i+1); 
// 					} 
// 					else 
// 					{
// 						temp = temp+"|"+resultSet.getString(i+1);
// 					} 
 				}
// 				result = temp;               
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}         
		finally 
		{
			try 
			{
				resultSet.close();
				statement.close();                
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return product;
	}
  
	public static int doUpdate(String s) 
	{		
		Connection connection = null;
		Statement statement = null;
		int result = -1;   

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();  
			result = statement.executeUpdate(s);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}           
		finally 
		{
			try 
			{
				statement.close();                
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return result;
	}

	public static String getQueryResultTable(Vector<String []> v) 
	{
		StringBuffer toReturn = new StringBuffer();
		toReturn.append("<table>");
		for(int i=0; i < v.size(); i++) 
		{
			String [] tmp = v.elementAt(i);
			toReturn.append("<tr>");        
			
			for(int j=0; j < tmp.length; j++)
				toReturn.append("<td>" + tmp[j] + "</td>");
			
			toReturn.append("</tr>");
		}
		toReturn.append("</table>"); 
		return toReturn.toString();
	}
	
	
	public static String query(String s) 
	{	
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String result = "";  
		String temp="";  
		String localTemp="";
		    

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();

			if(resultSet == null) 
			{
				return null;
			}

			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();
			
			while(resultSet.next()) 
			{
				for(int i=0; i < numCols; i++)
				{
					String sku="";
									
					if(temp.isEmpty()) 
					{
						temp = temp+resultSet.getString(i+1); 
					} 
					else 
					{
						temp = temp+"|"+resultSet.getString(i+1);
					}  
				}
				temp = temp+"||";            
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}         
		finally 
		{
			try 
			{
				resultSet.close(); 
				preparedStatement.close();              
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		return temp;
	}
	
	
	
	
		public static void queryOnly(String s) 
	{	
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String result = "";  
		String temp="";  
		String localTemp="";
		    

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			preparedStatement = connection.prepareStatement(s);
            preparedStatement.executeUpdate();


		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}         
		finally 
		{
			try 
			{
			if(resultSet != null) {
				resultSet.close(); 
				}
				preparedStatement.close();              
				connection.close();
			}
			catch(SQLException e) {}  // don't do anything if the connection is not open.
		}
		
	}
	
	 
}            


