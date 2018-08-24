

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;


public class Calculate_average_time {

	public Calculate_average_time() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		FileReader reader = new FileReader("/home/ubuntu/tomcat/webapps/Movie_Website/Log.txt");
		BufferedReader br = new BufferedReader(reader);
		long query_sum=0;
		long JDBC_sum=0;
		int lines=0;
		String str = null;
		while((str=br.readLine())!=null) {
			String a[] = str.split(",");
			query_sum += Long.parseLong(a[0]);
			JDBC_sum += Long.parseLong(a[1]);
			lines++;		
		}
		long Average_query_time= query_sum/lines;
		long Average_JDBC_time= JDBC_sum/lines;
		FileWriter out = new FileWriter("/home/ubuntu/Average_time.txt");
		BufferedWriter bw = new BufferedWriter(out); 
		String str2 = Average_query_time+","+Average_JDBC_time+"\n";
		bw.append(str2);
		bw.close();
		out.close();
		
	}

}
