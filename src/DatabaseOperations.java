import java.sql.*;

public class DatabaseOperations {


    /*public void accessDB(String dbFileName,String operation) throws SQLException {

        Connection connection = null;
        try {
            String dbUrl = "jdbc:sqlite:" + dbFileName;
            connection = DriverManager.getConnection(dbUrl);
            createTitanicTable(connection);

            //insertToTable(connection,Data.createStatement("hello") );
            printFullTable(connection, Data.createStatement(operation));

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }
    }*/

    public void createTitanicTable(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS passengers");
        statement.executeUpdate("CREATE TABLE passengers (passengerID INT, survived INT, pClass INT, " +
                "name VARCHAR(100), sex VARCHAR(6), age FLOAT, sibSp INT, parch INT, ticket VARCHAR(30), " +
                "fare FLOAT, cabin VARCHAR(30), embarked VARCHAR(1))");

        statement.close();
    }

    public void insertToTable(Connection connection, String[] sqlArray) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO passengers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        for (int i = 1; i <= sqlArray.length; i++){
            if(sqlArray[i-1].isEmpty()){
                sqlArray[i-1] = null;
            }
            statement.setString(i, sqlArray[i-1]);
        }
        statement.executeUpdate();
        statement.close();
    }

    public ResultSet selectFromTable(Connection connection, String sql) throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public void printFullTable(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery( "SELECT * FROM passengers");
        System.out.println("passengerID, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked");
        while (resultSet.next()) {
            int passengerID = resultSet.getInt("passengerID" );
            int survived = resultSet.getInt("survived");
            int pClass = resultSet.getInt("pClass");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            float age = resultSet.getFloat("age");
            int sibSp = resultSet.getInt("sibSp");
            int parch = resultSet.getInt("parch");
            String ticket = resultSet.getString("ticket");
            float fare = resultSet.getFloat("fare");
            String cabin = resultSet.getString("cabin");
            String embarked = resultSet.getString("embarked");

            System.out.println(Integer.toString(passengerID) + ", " + Integer.toString(survived) + ", " +
                    Integer.toString(pClass) + ", " + name + ", " + sex + ", " + Float.toString(age) + ", " +
                    Integer.toString(sibSp) + ", " + Integer.toString(parch) + ", " + ticket + ", " +
                    Float.toString(fare) + ", " + cabin + ", " + embarked);
        }
        statement.close();
    }
}
