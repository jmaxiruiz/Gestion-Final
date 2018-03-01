/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maxi
 */
public class Cliente {
    private int idcliente;
    private String nombre;
    private int telefono;
    private String direccion ;

    public Cliente() {
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idcliente=" + idcliente + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
    
    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarCliente(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into cliente (nombre, telefono, direccion) values (?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getNombre());
            st.setInt(2, this.getTelefono());
            st.setString(3, this.getDireccion());
            st.execute();
            System.out.println("SE GRABO EL CLIENTE EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarCliente(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from cliente where idCliente = " + this.getIdcliente()+ ";";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL CLIENTE DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarCliente(){
        try {
            Conexion conexion = new Conexion();
            String query = "update cliente set nombre ='" + this.getNombre() + "', telefono ='"+ this.getTelefono() + "', direccion= '" + this.getDireccion() + "' where idCliente =" + this.getIdcliente()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL CLIENTE EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarCliente(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from cliente;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    
}
