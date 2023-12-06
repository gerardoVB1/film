/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.film.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.itson.film.persistence.ConnectionDB;

/**
 * La clase Funcion representa una entidad de pelicula con atributos y proporciona métodos para realizar operaciones CRUD.
 * @author Gerardo valdez
 */
public class Funcion {

    private Date date;
    private String titulo;
    private String sala;
    private String hora;
    
    /**
     * Método estático para obtener todas las funciones que coinciden con un filtro.
     * @param filtro Filtro para buscar funciones por título de la película.
     * @return Lista de funciones que coinciden con el filtro.
     */
    public static List<Funcion> getAll(String filtro){
       List<Funcion> funciones = new ArrayList<>();
       try{
          Connection conexion = ConnectionDB.get();
          PreparedStatement statement = conexion.prepareStatement("SELECT * FROM funcion WHERE name LIKE ?");
          statement.setString(1, "%" + filtro + "%");

          ResultSet resultSet = statement.executeQuery();

          while(resultSet.next()){
              Funcion f = new Funcion();
              f.setDate(resultSet.getDate(1));
              f.setTitulo(resultSet.getString(2));
              f.setSala(resultSet.getString(3));
              f.setHora(resultSet.getString(4));
              funciones.add(f);
           }  
       }catch(SQLException ex){
           
       }
       return funciones;

    /**
     * Método para guardar una nueva función en la base de datos.
     * @param date Fecha de la función.
     * @param titulo Título de la película.
     * @param sala Sala de proyección de la función.
     * @param hora Hora de la función.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    }
    public boolean save(String date, String titulo, String sala, String hora){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "INSERT INTO funcion (date, titulo, sala, hora) VALUES (?,?,?,?)";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, date);
           statement.setString(2, titulo);
           statement.setString(3, sala);
           statement.setString(4, hora);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }
    
    /**
     * Método para actualizar una función existente en la base de datos.
     * @param id ID de la función a actualizar.
     * @param date Nueva fecha de la función.
     * @param titulo Nuevo título de la película.
     * @param sala Nueva sala de proyección de la función.
     * @param hora Nueva hora de la función.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
     public boolean update(int id, String date, String titulo, String sala, String hora){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "UPDATE funcion SET date = ?, titulo = ?, sala = ?, hora = ? WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, date);
           statement.setString(2, titulo);
           statement.setString(3, sala);
           statement.setString(4, hora);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }

    /**
     * Método para eliminar una función por su ID.
     * @param id ID de la función a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean delete(int id){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "DELETE FROM funcion WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setInt(1, id);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the sala
     */
    public String getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(String sala) {
        this.sala = sala;
    }
   
}
