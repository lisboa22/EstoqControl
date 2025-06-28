/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author robson
 */
public class DAOFactory {
    public static UsuarioDAO criarUsuarioDAO() {
        return (UsuarioDAO) new UsuarioDAOJDBC();
    }
    /*
    public static FormaPagamentoDAO criarFormaPagamentoDAO() {
        return (FormaPagamentoDAO) new FormaPagamentoDAOJDBC();
    }
    
    public static VendaDAO criarVendaDAO() {
        return (VendaDAO) new VendaDAOJDBC();
    }*/
}
