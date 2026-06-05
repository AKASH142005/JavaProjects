package JdbcDemo;

    import java.sql.*;
    class JdbcDemo{
        public static void main(String[] args) throws SQLException {
            insertRecords();
            readRecord();

        }
    public static void readRecord() throws SQLException {
        String url ="jdbc:mysql://localhost:3306/jdbcDemo";
        String userName ="root";
        String passWord ="ac14042005";
        String query ="select * from Employee";

        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()) {
            System.out.print("Id : " + rs.getInt(1));
            System.out.print("  Name : " + rs.getString(2));
            System.out.print("  Salary : " + rs.getInt(3));
            System.out.println();
        }
        con.close();
    }
    public static void insertRecords() throws SQLException{
        String url ="jdbc:mysql://localhost:3306/jdbcDemo";
        String userName ="root";
        String passWord ="ac14042005";
        String query ="insert into Employee values(6,'Aranker',303000)";

        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,userName,passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);
        System.out.println(rows);
        con.close();

    }
        public static void insertVar() throws SQLException{
            String url ="jdbc:mysql://localhost:3306/jdbcDemo";
            String userName ="root";
            String passWord ="ac14042005";

            int id= 5;
            String name = "varun";
            int salary = 50000;
            String query ="insert into Employee values(" +id+ ",'" +name+"'," +salary+ ");";

            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,userName,passWord);
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
            System.out.println(rows);
            con.close();

        }
    }

