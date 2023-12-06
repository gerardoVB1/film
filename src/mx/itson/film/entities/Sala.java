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
import java.util.List;
import mx.itson.film.persistence.ConnectionDB;

/**
 * La clase Sala representa una entidad de pelicula con atributos y proporciona métodos para realizar operaciones CRUD.
 * @author Gerardo valdez
 */
public class Sala {
    
    private String nombre;
    private String estado;
    private int capacidad;
    
     /**
     * Método estático para obtener todas las películas que coinciden con un filtro.
     * @param filtro Filtro para buscar películas por título.
     * @return Lista de películas que coinciden con el filtro.
     */
     public static List<Sala> getAll(String filtro){
       List<Sala> salas = new ArrayList<>();
       try{
          Connection conexion = ConnectionDB.get();
          PreparedStatement statement = conexion.prepareStatement("SELECT * FROM sala WHERE name LIKE ?");
          statement.setString(1, "%" + filtro + "%");

          ResultSet resultSet = statement.executeQuery();

          while(resultSet.next()){
              Sala s = new Sala();
              s.setNombre(resultSet.getString(1));
              s.setEstado(resultSet.getString(2));
              s.setCapacidad(resultSet.getInt(3));
              salas.add(s);
           }  
       }catch(SQLException ex){
           
       }
       return salas;

    /**
     * Método para guardar una nueva sala en la base de datos.
     * @param nombre Nombre de la sala.
     * @param estado Estado de la sala.
     * @param capacidad Capacidad de la sala.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    }
    public boolean save(String nombre, String estado, int capacidad){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "INSERT INTO sala (nombre, estado, capacidad) VALUES (?,?,?)";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, nombre);
           statement.setString(2, estado);
           statement.setInt(3, capacidad);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }
    
    /**
     * Método para actualizar una sala existente en la base de datos.
     * @param id ID de la sala a actualizar.
     * @param nombre Nuevo nombre de la sala.
     * @param estado Nuevo estado de la sala.
     * @param capacidad Nueva capacidad de la sala.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
     public boolean update(int id, String nombre, String estado, int capacidad){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "UPDATE sala SET nombre = ?, estado = ?, capacidad = ? WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, nombre);
           statement.setString(2, estado);
           statement.setInt(3, capacidad);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }

     /**
     * Método para eliminar una sala por su ID.
     * @param id ID de la sala a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean delete(int id){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "DELETE FROM sala WHERE id = ?";
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
}
