/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Equipamento;

/**
 *
 * @author robson
 */
public class EquipamentoDAOJDBC implements EquipamentoDAO{
    private List<Equipamento> equipamento;
    
    //Inserir Usuário
    @Override
    public int inserir(Equipamento equipamento) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("insert into equipamentos(equipamento, numero_serie, id_fabricante, data) ")
                .append("VALUES (?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        int linha = 0;
        try {  
            linha = DAOGenerico.executarComando(insert, equipamento.getEquipamento(),
                                                        equipamento.getNumero_serie(),
                                                        equipamento.getId_fabricante(),
                                                        equipamento.getData()
                                                        );
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Não é possível inserir: o email já está vinculado a outro Usuário.");      
            //ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "bNão é possível inserir: o email já está vinculado a outro Usuário.");      
            //ex.printStackTrace();
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        
        return linha;
    }

    //Listar Usuários
    @Override
    public List<Equipamento> listar() {
        ResultSet rset;
        String select = "SELECT * FROM equipamentos ORDER BY id";
        List<Equipamento> equipamentos = new ArrayList<>();
        try {        
            rset = DAOGenerico.executarConsulta(select);
            while (rset.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setId(rset.getInt("id"));
                equipamento.setEquipamento(rset.getString("equipamento"));
                equipamento.setNumero_serie(rset.getString("numero_serie"));
                equipamento.setId_fabricante(rset.getInt("id_fabricante"));
                equipamento.setData(rset.getDate("data"));
                equipamentos.add(equipamento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return equipamentos;
    }

    //Listar um único Usuário.
    @Override
    public Equipamento listar(int id) {
        ResultSet rset;
        String select = "SELECT * FROM equipamentos WHERE id = ?";
        Equipamento equipamento = new Equipamento();
        try {        
            rset = DAOGenerico.executarConsulta(select,id);
            
            while (rset.next()) {
                
                equipamento.setId(rset.getInt("id"));
                equipamento.setEquipamento(rset.getString("equipamento"));
                equipamento.setNumero_serie(rset.getString("numero_serie"));
                equipamento.setId_fabricante(rset.getInt("id_fabricante"));
                equipamento.setData(rset.getDate("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return equipamento;
    }
    
    //Editar Usuário
    @Override
    public int editar(Equipamento equipamento) {
      StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE equipamentos SET ")
                .append("equipamento = ?, ")
                .append("numero_serie = ?, ")
                .append("id_fabricante = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            linha = DAOGenerico.executarComando(update, equipamento.getEquipamento(),
                                                        equipamento.getNumero_serie(),
                                                        equipamento.getId_fabricante(),
                                                        equipamento.getId());
        } catch (Exception e) { 
            e.printStackTrace();
        } 
        return linha; 
    }
    
    @Override
    public int apagar(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM equipamentos ")
                .append("WHERE id = ?");
        String delete = sqlBuilder.toString();
        int linha = 0;        
        linha = DAOGenerico.executarComando(delete, id);
        return linha;
    }
}
