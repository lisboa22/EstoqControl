/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Usuario;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLException;


/**
 *
 * @author robson
 */
public class UsuarioDAOJDBC implements UsuarioDAO{
    
    private List<Usuario> usuario;
    
    //Inserir Usuário
    @Override
    public int inserir(Usuario usuario) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("insert into usuarios(nome, usuario, email, celular, id_permissao, senha, altersenha, data) ")
                .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        String insert = sqlBuilder.toString();
        int linha = 0;
        try {  
            linha = DAOGenerico.executarComando(insert, usuario.getNome(),
                                                        usuario.getUsuario(),
                                                        usuario.getEmail(),
                                                        usuario.getCelular(),
                                                        usuario.getidPermissao(),
                                                        usuario.getSenha(),
                                                        usuario.getAltersenha(),
                                                        usuario.getData()
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
    public List<Usuario> listar() {
        ResultSet rset;
        String select = "SELECT * FROM usuarios ORDER BY id";
        List<Usuario> usuarios = new ArrayList<>();
        try {        
            rset = DAOGenerico.executarConsulta(select);
            while (rset.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setUsuario(rset.getString("usuario"));
                usuario.setEmail(rset.getString("email"));
                usuario.setCelular(rset.getString("celular"));
                usuario.setidPermissao(rset.getInt("id_permissao"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setAltersenha(rset.getInt("altersenha"));
                usuario.setData(rset.getDate("data"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return usuarios;
    }

    //Listar um único Usuário.
    @Override
    public Usuario listar(int id) {
        ResultSet rset;
        String select = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = new Usuario();
        try {        
            rset = DAOGenerico.executarConsulta(select,id);
            
            while (rset.next()) {
                
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setUsuario(rset.getString("usuario"));
                usuario.setEmail(rset.getString("email"));
                usuario.setCelular(rset.getString("celular"));
                usuario.setidPermissao(rset.getInt("id_permissao"));
                usuario.setSenha(rset.getString("senha"));
                usuario.setAltersenha(rset.getInt("altersenha"));
                usuario.setData(rset.getDate("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return usuario;
    }
    
    //Editar Usuário
    @Override
    public int editar(Usuario usuario) {
      StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE usuarios SET ")
                .append("nome = ?, ")
                .append("usuario = ?, ")
                .append("email = ?, ")
                .append("celular = ?, ")
                .append("id_permissao = ?, ")
                .append("senha = ?, ")
                .append("altersenha = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            linha = DAOGenerico.executarComando(update, usuario.getNome(),
                                                        usuario.getUsuario(),
                                                        usuario.getEmail(),
                                                        usuario.getCelular(),
                                                        usuario.getidPermissao(),
                                                        usuario.getSenha(),
                                                        usuario.getAltersenha(),
                                                        usuario.getId());
        } catch (Exception e) { 
            e.printStackTrace();
        } 
        return linha; 
    }
    
    //Editar Senha
    @Override
    public int editarSenha(Usuario usuario) {
      StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE usuarios SET ")
                .append("senha = ?, ")
                .append("altersenha = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            linha = DAOGenerico.executarComando(update, usuario.getSenha(),
                                                        usuario.getAltersenha(),
                                                        usuario.getId());
        } catch (Exception e) { 
            e.printStackTrace();
        } 
        return linha; 
    }
    
    @Override
    public int apagar(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM usuarios ")
                .append("WHERE id = ?");
        String delete = sqlBuilder.toString();
        int linha = 0;        
        linha = DAOGenerico.executarComando(delete, id);
        return linha;
    }
}
