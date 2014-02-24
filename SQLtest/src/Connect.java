
public class Connect {
	private java.sql.Connection con = null;
	private final String url = "jdbc:sqlserver://";
	private final String serverName= "fourwaylo.com";
	private final String portNumber = "8889";
	private final String databaseName = "csproj";
	private final String userName = "csproj";
	private final String password = "DoYourHomework";
	private final String selectMethod = "cursor";
	
	public Connect(){}
	
	private String getConnectionUrl(){
		return url+serverName+":"+portNumber+";databaseName="+databaseName+";selectMethod=" + selectMethod+ ";";
	}
	
	private java.sql.Connection getConnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerConnecion");
				con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
				if(con != null) System.out.println("Connection Successful");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error Trace in getConnection() : " + e.getMessage());
			}
			return con;
		}
		
		public void displayDbProperties(){
			java.sql.DatabaseMetaData dm = null;
			java.sql.ResultSet rs = null;
			try{
				con = this.getConnection();
				if(con!=null){
					dm = con.getMetaData();
					System.out.println("Driver Information");
					System.out.println("\tDriver Name: " + dm.getDriverName());
					System.out.println("\tDriver Version: " + dm.getDriverVersion());
					System.out.println("Avalilable Catalogs ");
					rs = dm.getCatalogs();
					while(rs.next()){
						System.out.println("\tcatalog: " + rs.getString(1));
					}
					rs.close();
					rs = null;
					closeConnection();
			}else System.out.println("Error: No active Connection");
		}catch(Exception e){
			e.printStackTrace();
		}
		dm = null;
	}
	private void closeConnection(){
		try{
			if(con!= null)
				con.close();
			con = null;
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) throws Exception{
		Connect myDbTest = new Connect();
		myDbTest.displayDbProperties();
	}
}

