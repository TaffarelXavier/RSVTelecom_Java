/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taffarel <taffarel_deus@hotmail.com>
 */
public class Pagamentos {

    /**
     *
     * @param mes
     * @param ano
     * @return
     */
    public static int vericarSeExiste(String mes, String ano) {
        PreparedStatement stm;
        try {
            stm = Conexao.abrir().prepareStatement("SELECT COUNT(*) as total FROM `pagamento` AS t1 WHERE t1.mes = ? AND ano = ?;");
            stm.setString(1, mes);
            stm.setString(2, ano);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString("total"));
            }
        } catch (Exception ex) {
            Logger.getLogger(Pagamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static int pago(String mes, String ano) {

        /*try {
            $this->conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $stmt = $this->conexao->prepare("SELECT * FROM `pagamento` AS t1 WHERE t1.mes = ? AND ano = ?;");
            $stmt->bindParam(1, $mes, PDO::PARAM_STR);
            $stmt->bindParam(2, $ano, PDO::PARAM_INT);
            $stmt->execute();
            $dados = $stmt->fetch();
            return $dados[3];
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }*/
        return -1;
    }

    /**
     * 
     * @param valorPago
     * @param pagamentoId
     * @param mes
     * @param ano
     * @return 
     */
    public static int setPagamento(String valorPago, int pagamentoId, int mes, int ano) {
        PreparedStatement stmt;
        try {
            stmt = Conexao.abrir().prepareStatement("UPDATE `pagamento` SET `pago` = ?"
                    + "WHERE `pagamento`.`id` = ? AND `pagamento`.`mes` = ? AND `pagamento`.`ano` = ?;",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, valorPago);
            stmt.setInt(2, pagamentoId);
            stmt.setInt(3, mes + 1);
            stmt.setInt(4, ano);
            return stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Pagamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     *
     * @param mes
     * @param ano
     * @return
     */
    public static int inserir(String mes, String ano) {
        /* try {
            $this->conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $stmt = $this->conexao->prepare("INSERT INTO `pagamento` (`id`, `mes`, `ano`, `pago`) VALUES (NULL, ?, ?, 'nao');");
            $stmt->bindParam(1, $mes, PDO::PARAM_INT);
            $stmt->bindParam(2, $ano, PDO::PARAM_INT);
            $stmt->execute();
            return (int) $stmt->rowCount();
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }*/
        return -1;
    }

    public static ResultSet getPagamentos() {
        try {
            PreparedStatement stm = Conexao.abrir().prepareStatement("SELECT * FROM `pagamento`;");

            ResultSet rs = stm.executeQuery();
            return rs;
        } catch (Exception ex) {
            Logger.getLogger(Pagamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
