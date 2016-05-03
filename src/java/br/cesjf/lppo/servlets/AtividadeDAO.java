
package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Atividade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeDAO {

    List<Atividade> listaTodos() throws Exception {
        List<Atividade> todos = new ArrayList<>();
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM atividade");
            while (resultado.next()) {
                Atividade ativ = new Atividade();
                ativ.setId(resultado.getLong("id"));
                ativ.setFuncionario(resultado.getString("funcionario"));
                ativ.setTipo(resultado.getString("tipo"));
                ativ.setHoras(resultado.getInt("horas"));
                todos.add(ativ);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

        return todos;
    }

    void criar(Atividade novaAtiv) throws Exception {
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate(String.format("INSERT INTO atividade(funcionario,tipo,horas) VALUES('%s','%s','s')", novaAtiv.getFuncionario(), novaAtiv.getTipo(), novaAtiv.getHoras()));

        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    void excluirPorId(Long id) throws Exception {
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("DELETE FROM atividade WHERE id="+id);
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);        }
        
    }

}
