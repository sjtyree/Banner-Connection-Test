package hr;

import java.sql.ResultSet;
import java.sql.SQLException;
public class BannerConnectionTestMain
{

	public static void main(String[] args)
	{
		try
		{
			BannerConnectionTest connectionTEST = new BannerConnectionTest();
			ResultSet rs = connectionTEST.getAllEmployees();
			while (rs.next()) {
				System.out.println(rs.getString(1) + " - " +
				  rs.getString(2) + " " + 
				  rs.getString(3));
				}
		}
		catch (SQLException e)
		{
			System.out.println("SQL error");
			System.out.println(e);
		}
		catch (Exception e)
		{
			System.out.println("Something else went wrong");
			System.out.println(e);
		}

	}

}
