package hr;
/**
 * 
 */
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * @author styree
 *
 */
public class BannerConnectionTest
{
	//username/password@host:port/SERVICE_NAME
//	jdbc:oracle:driver_type:[username/password]@//host_name:port_number:SID
//	String jdbcURL = "STYREE/dd3SVZpZG6TH1a2E2xlP@10.224.208.130:9537:CUOR";
//	String jdbcURL = "jdbc:oracle:thin:@10.224.208.130:9537:ORCL";
	String username = "STYREE";
	String password = "";
	String jdbcURL = "jdbc:oracle:thin:" + username + "/" + password + "@10.224.208.130:9537:CUOR";
	Connection conn;
	Statement stmt;
	ResultSet rset;
	String query;
	String sqlString;
	/**
	 * @param args
	 */
	
	public BannerConnectionTest()
	{
		super();
	}
	
	public void getDBConnection() throws SQLException
	{
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(jdbcURL);
		conn = ds.getConnection(username,password);
	}
	

	public BannerConnectionTest(String jdbcURL, String username, String password, Connection conn, Statement stmt,
			ResultSet rset, String query, String sqlString)
	{
		super();
		this.jdbcURL = jdbcURL;
		this.username = username;
		this.password = password;
		this.conn = conn;
		this.stmt = stmt;
		this.rset = rset;
		this.query = query;
		this.sqlString = sqlString;
	}

	public ResultSet getAllEmployees() throws SQLException
	{
		getDBConnection();
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		query = "select SPRIDEN.SPRIDEN_ID\r\n" + 
				", SPRIDEN.SPRIDEN_FIRST_NAME\r\n" + 
				", SPRIDEN.SPRIDEN_LAST_NAME\r\n" + 
				"from pebempl \r\n" + 
				"join spriden\r\n" + 
				"  on pebempl_pidm = spriden_pidm \r\n" + 
				"  and SPRIDEN.SPRIDEN_CHANGE_IND is null\r\n" + 
				"where PEBEMPL.PEBEMPL_EMPL_STATUS = 'A'\r\n" + 
				"order by SPRIDEN.SPRIDEN_LAST_NAME";
		System.out.println("\nExecuting query: " + query);
		rset = stmt.executeQuery(query); 
		return rset;
	}
	
}
