package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entity.Table;



public class GetTable {

	static LinkedList<Table> tablelist;
	Statement select;
	String query,query1;
	ResultSet result;
	
	public GetTable() {
		// TODO Auto-generated constructor stub
		tablelist=new LinkedList<Table>();
	}

	public LinkedList<Entity.Table> getTable() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		tablelist.clear();
		Connection connection = DataBaseConnect.getconn();
		
		 select = connection.createStatement();
		 query = "show tables";
		 
		 Set_para();
		 return tablelist;
	}
	
	
	public void Set_para() throws SQLException {
		
		ResultSet tables = select.executeQuery(query);
		int i=0;
		while (tables.next()) {
			String name = tables.getString(1);
			System.out.println(name);
			tablelist.add(new Table("NULL"));
			tablelist.get(i).settablename(name);
			i++;
		}
		
		for(int j=0;j<tablelist.size();j++)
		{
			 query1 = "show columns from "+ tablelist.get(j).gettablename();
			 ResultSet columns = select.executeQuery(query1);
			 while (columns.next()) 
			 {
				 tablelist.get(j).setField(columns.getString(1));
				 tablelist.get(j).setType(columns.getString(2));
				 tablelist.get(j).setNull(columns.getString(3));
				 tablelist.get(j).setKey(columns.getString(4));
				 tablelist.get(j).setDefault(columns.getString(5));
				 tablelist.get(j).setExtra(columns.getString(6));
			 }
		}	
	}
	
	
//	public static void sort(Entity.Movie new_record) {
//		int n = toplist.size();
//		boolean if_insert = false;
//		if (toplist.size() != 0) {
//			for (int i = 0; i < n; i++) {
//				if (toplist.get(i).getrate() < new_record.getrate()) {
//					toplist.add(i, new_record);
//					if_insert = true;
//					break;
//				}
//			}
//		} else {
//			toplist.addLast(new_record);
//			if_insert = true;
//		}
//		if (if_insert == false) {
//			toplist.addLast(new_record);
//		}
//	}


}

	
