package JdbcDemo;
//
    import java.sql.*;
    class JdbcDemo {


        public static void main(String[] args) throws SQLException {
            BatchDemo();
        }

        public static void readRecord() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";
            String query = "select * from Employee";

            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.print("Id : " + rs.getInt(1));
                System.out.print("  Name : " + rs.getString(2));
                System.out.print("  Salary : " + rs.getInt(3));
                System.out.println();
            }
            con.close();
        }

        public static void insertRecords() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";
            String query = "insert into Employee values(6,'Aranker',303000)";

            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
            System.out.println(rows);
            con.close();

        }

        public static void insertVar() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";

            int id = 5;
            String name = "varun";
            int salary = 50000;
            String query = "insert into Employee values(" + id + ",'" + name + "'," + salary + ");";
            System.out.println(query);
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
            System.out.println(rows);
            con.close();
        }

        public static void insertPreparedStatement() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";

            int id = 8;
            String name = "Arun";
            int salary = 40000;
            String query = "INSERT INTO Employee VALUES(?,?,?) ";
            Connection con = DriverManager.getConnection(url, userName, passWord);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, salary);

            int rows = pst.executeUpdate();
            System.out.print(rows);
            con.close();

        }

        public static void update() throws SQLException {
            String url = "jdbc:mysql://localhost/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";

            int salary = 10000;
            int id = 2;

            String query = "UPDATE Employee set salary = " + salary + " where emp_no = " + id;
            System.out.println(query);

            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement st = con.createStatement();
            int rows = st.executeUpdate(query);
            System.out.println(rows);
            con.close();
        }

        public static void delete() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";

            int id = 5;

            String query = "delete from Employee where emp_no = ?;";
            Connection con = DriverManager.getConnection(url, userName, passWord);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            System.out.println(rows);
            con.close();
        }

        public static void sp() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";

            Connection con = DriverManager.getConnection(url, userName, passWord);
            CallableStatement cst = con.prepareCall("{Call GetEmp()}");
            ResultSet rs = cst.executeQuery();

            while (rs.next()) {
                System.out.print("ID  : " + rs.getInt(1));
                System.out.print(" Name " + rs.getString(2));
                System.out.println(" Salary : " + rs.getInt(+3));
            }
            con.close();
        }

        public static void spIn() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";

            int id = 2;

            Connection con = DriverManager.getConnection(url, userName, passWord);
            CallableStatement cst = con.prepareCall("{Call GetDetails(?)}");
            cst.setInt(1, id);
            ResultSet rs = cst.executeQuery();

            rs.next();
            System.out.print("ID : " + rs.getString(1));
            System.out.print(" Name : " + rs.getString(2));
            System.out.print(" Salary : " + rs.getString(3));
            con.close();
        }

        public static void spInOut() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            ;
            String userName = "root";
            String passWord = "ac14042005";

            int id = 2;
            Connection con = DriverManager.getConnection(url, userName, passWord);
            CallableStatement cst = con.prepareCall("{Call GetNameBy(?,?)}");
            cst.setInt(1, id);
            cst.registerOutParameter(2, Types.VARCHAR);
            cst.executeUpdate();
            System.out.print(cst.getString(2));

            con.close();
        }

        public static void commitDemo() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/jdbcDemo";
            String userName = "root";
            String passWord = "ac14042005";


            String query1 = "update  Employee set Salary = 1000 where emp_no =1 ";
            String query2 = "update Employee set Salary = 1000 where emp_no = 2 ";
            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement st = con.createStatement();
            con.setAutoCommit(false);


            int row1 = st.executeUpdate(query1);
            int row2 = st.executeUpdate(query2);

            if (row1 > 0 && row2 > 0) {
                con.commit();
            }
            System.out.print(row1);
            con.close();
        }

        public static void BatchDemo() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/jdbcDemo";
        String userName = "root";
        String passWord = "ac14042005";

        String query1 = "Update Employee set salary = 2000 where emp_no = 1;";
        String query2 = "Update Employee set salary = 2000 where emp_no = 2;";
        String query3 = "Update Employee set salary = 2000 where emp_no = 3;";
        String query4 = "Update Employee set salary = 2000 where emp_no = 4;";

        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
            con.setAutoCommit(false);
            st.addBatch(query1);
            st.addBatch(query2);
            st.addBatch(query3);
            st.addBatch(query4);

            int res [] = st.executeBatch();
            for(int num : res){
                if(num > 0) continue;
                else con.rollback();
            }
            con.commit();
            con.close();

    }
    }

