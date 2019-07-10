package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Faz as conexões com o banco de dados.
 *
 * @author Grupo 5 LC - IFTO
 */
public class Conexao {

    /**
     * <p style="font:16px arial">Strings de conexões</p>
     */
    private static final String[][] DSNS = {
        { /*Índice:0*/
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://187.84.237.168:41890/boleto_db",
            "boleto_user",
            "_[y+4pU*w7Z"
        },
        { /*Índice:1*/
            "org.postgresql.Driver",
            "jdbc:postgresql://localhost:5432/banco_ifto",
            "postgres",
            "chkdsk"
        }
    };

    public Conexao() {
    } //Possibilita instancias

    /**
     * <h2>Abre a conexão com o banco.</h2>
     *
     * @return
     * @throws Exception
     */
    public static Connection abrir() throws Exception {
        try {
            int index = 0; //Com 0, conecta ao banco Mysql; com 1, conecta ao banco postgre.
            // Registrar o driver
            Class.forName(DSNS[index][0]).newInstance();
            // Capturar a conexão
            Connection conn = DriverManager.getConnection(DSNS[index][1], DSNS[index][2], DSNS[index][3]);
            // Retorna a conexao aberta
            return conn;
        } catch (SQLException e) {
            switch (e.getSQLState()) {
                case "28000":
                    JOptionPane.showMessageDialog(null, "A senha do banco está incorreta.\n" + e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                case "08S01":
                    JOptionPane.showMessageDialog(null, "O servidor de banco de dados SQL não está ligado.\n" + e.getMessage().replaceAll("\\.", "\n"),
                            "Atenção", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Houve um erro.\n" + e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        return null;
    }

    /**
     * Fecha a Conexão
     *
     * @return
     * @throws Exception
     */
    public static boolean fecharConexao() throws Exception {

        try {

            abrir().close();
            return true;

        } catch (SQLException e) {

            return false;

        }

    }

    public static void main(String[] args) throws Exception {

        PreparedStatement stmt = Conexao.abrir().prepareStatement("show databases;");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
}
