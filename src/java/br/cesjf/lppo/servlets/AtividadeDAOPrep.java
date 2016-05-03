package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Atividade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeDAOPrep {
    
    private PreparedStatement operacaoListarTodos;
    private PreparedStatement operacaoCriar;
    private PreparedStatement operacaoExcluirPorId;
    
    public AtividadeDAOPrep() throws Exception{
        try{
            operacaoListarTodos = ConexaoJDBC.getInstance().prepareStatement("SELECT * FROM atividade");
        }catch (SQLException ex){
            Logger.getLogger(AtividadeDAOPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
    List<Atividade> listaTodos() throws Exception {
        List<Atividade> todos = new ArrayList<>();
        try {
 
           ResultSet resultado = operacaoListarTodos.executeQuery();
            while (resultado.next()) {
                Atividade ativ = new Atividade();
                ativ.setId(resultado.getLong("id"));
                ativ.setFuncionario(resultado.getString("funcionario"));
                ativ.setTipo(resultado.getString("tipo"));
                ativ.setHoras(resultado.getInt("horas"));
                todos.add(ativ);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAOPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

        return todos;
    }

    void criar(Atividade novaAtiv) throws Exception {
        try {
            
            operacaoCriar = ConexaoJDBC.getInstance().prepareStatement("INSERT INTO atividade(funcionario, tipo, horas) VALUES(?,?,?)", new String[]{"id"});
            operacaoCriar.setString(1, novaAtiv.getFuncionario());
            operacaoCriar.setString(2, novaAtiv.getTipo());
            operacaoCriar.setInt(3, novaAtiv.getHoras());
            
            operacaoCriar.executeUpdate();
            ResultSet keys = operacaoCriar.getGeneratedKeys();
            if(keys.next()){
                novaAtiv.setId(keys.getLong(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAOPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    void excluirPorId(Long id) throws Exception {
        try {
            
            operacaoExcluirPorId = ConexaoJDBC.getInstance().prepareStatement("DELETE FROM atividade WHERE id=?");
            operacaoExcluirPorId.setLong(1, id);
            operacaoExcluirPorId.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAOPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

    }

}
