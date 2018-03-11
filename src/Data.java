import java.sql.PreparedStatement;

public class Data {


    public static String createStatement(int input){
        String sql = "";

        switch (input) {
            case 1:
                sql = "SELECT * FROM passengers";
                break;
            case 2:
                sql = "SELECT COUNT(*) FROM passengers WHERE survived = 1";
                break;
            case 3:
                sql = "SELECT pClass, survived, COUNT(*) FROM passengers GROUP BY pClass, Survived";
                break;
            case 4:
                sql = "SELECT sex, survived, MIN(age) FROM passengers GROUP BY sex, survived";
                break;
            default:
                sql = "";
                break;
        }
        return sql;
    }
}
