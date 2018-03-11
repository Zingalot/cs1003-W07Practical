
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class W07Practical {

    public static void main(String[] args) throws SQLException, IOException {

        //Converting arguments to variables
        String filepath = args[0];
        int operation = 0;
        switch (args[1]) {
            case "create":
                operation = 0;
                break;
            case "query1":
                operation = 1;
                break;
            case "query2":
                operation = 2;
                break;
            case "query3":
                operation = 3;
                break;
            case "query4":
                operation = 4;
                break;
            default:
                System.out.println("Usage:  java -cp sqlite-jdbc.jar:. W07Practical <db_file> <action> [input_file]");
                System.exit(0);
        }

        //Connecting to database
        DatabaseOperations titanicDatabase = new DatabaseOperations();
        Connection connection = null;
        try {
            String dbUrl = "jdbc:sqlite:" + filepath;
            connection = DriverManager.getConnection(dbUrl);

            //"Create" option
            if(operation == 0){
                try {
                    List<String[]> data = Reader.readInputFile(args[2]);
                    titanicDatabase.createTitanicTable(connection);
                    for (int k = 1; k < data.size() - 1; k++) {
                        titanicDatabase.insertToTable(connection, data.get(k));
                    }
                    System.out.println("OK");
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            //"Query 1" option
            if(operation == 1){
                titanicDatabase.printFullTable(connection);
            }

            //"Query 2" option
            if(operation == 2){
                ResultSet results = titanicDatabase.selectFromTable(connection, Data.createStatement(operation));
                results.next();
                System.out.println("Number of Survivors");
                System.out.println(results.getInt(1));
            }

            //
            if(operation == 3){
                ResultSet results = titanicDatabase.selectFromTable(connection, Data.createStatement(operation));
                System.out.println("pClass, survived, count");
                while (results.next()){

                       int survived = results.getInt("survived");
                       int pClass = results.getInt("pClass");
                       int count = results.getInt("COUNT(*)");
                       System.out.println(Integer.toString(pClass) + ", " + Integer.toString(survived) + ", " + Integer.toString(count));
                }
            }

            if(operation == 4){
                ResultSet results = titanicDatabase.selectFromTable(connection, Data.createStatement(operation));
                System.out.println("sex, survived, minimum age");
                while (results.next()){

                    String sex = results.getString("sex");
                    int survived = results.getInt("survived");
                    float age = results.getFloat("MIN(age)");
                    System.out.println(sex + ", " + Integer.toString(survived) + ", " + Float.toString(age));
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) connection.close();
        }





    }

}
