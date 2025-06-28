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


/**
 *
 * @author robson
 */
public class UsuarioDAOJDBC implements UsuarioDAO{
    
    private List<Usuario> usuario;
    
    //Inserir Usuário
    @Override
    public int inserir(Usuario usuario) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("insert into usuarios(nome, usuario, email, celular, funcao, senha, data) ")
                .append("VALUES (?, ?, ?, ?, ?, ?, ?)");
     
        String insert = sqlBuilder.toString();
        int linha = 0;
        try {  
            linha = DAOGenerico.executarComando(insert, usuario.getNome(),
                                                        usuario.getUsuario(),
                                                        usuario.getEmail(),
                                                        usuario.getCelular(),
                                                        usuario.getFuncao(),
                                                        usuario.getSenha(),
                                                        usuario.getData()
                                                        );
        } catch (Exception e) {
            e.printStackTrace();
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
                usuario.setFuncao(rset.getString("funcao"));
                usuario.setSenha(rset.getString("senha"));
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
                usuario.setFuncao(rset.getString("funcao"));
                usuario.setSenha(rset.getString("senha"));
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
                .append("funcao = ?, ")
                .append("senha = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            linha = DAOGenerico.executarComando(update, usuario.getNome(),
                                                        usuario.getUsuario(),
                                                        usuario.getEmail(),
                                                        usuario.getCelular(),
                                                        usuario.getFuncao(),
                                                        usuario.getSenha(),
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
