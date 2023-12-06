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
 * La clase Pelicula representa una entidad de pelicula con atributos y proporciona métodos para realizar operaciones CRUD.
 * @author Gerardo valdez
 */
public class Pelicula {

    private String titulo;
    private String actores;
    private String clasificación;
    private int duracion;
    private String idioma;
    
    /**
     * Método estático para obtener todas las películas que coinciden con un filtro.
     * @param filtro Filtro para buscar películas por título.
     * @return Lista de películas que coinciden con el filtro.
     */
    
    public static List<Pelicula> getAll(String filtro){
       List<Pelicula> peliculas = new ArrayList<>();
       try{
          Connection conexion = ConnectionDB.get();
          PreparedStatement statement = conexion.prepareStatement("SELECT * FROM pelicula WHERE name LIKE ?");
          statement.setString(1, "%" + filtro + "%");

          ResultSet resultSet = statement.executeQuery();

          while(resultSet.next()){
              Pelicula p = new Pelicula();
              p.setTitulo(resultSet.getString(1));
              p.setActores(resultSet.getString(2));
              p.setClasificación(resultSet.getString(3));
              p.setIdioma(resultSet.getString(4));
              p.setDuracion(resultSet.getInt(5));
              peliculas.add(p);
           }  
       }catch(SQLException ex){
           
       }
       return peliculas;

    }
   /**
     * Método para guardar una nueva película en la base de datos.
     * @param titulo Título de la película.
     * @param actores Actores de la película.
     * @param clasificacion Clasificación de la película.
     * @param duracion Duración de la película en minutos.
     * @param idioma Idioma de la película.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean save(String titulo, String actores, String clasificacion, int duracion, String idioma){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "INSERT INTO pelicula (titulo, actores, clasificacion, duracion, idioma) VALUES (?,?,?,?,?)";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, titulo);
           statement.setString(2, actores);
           statement.setString(3, clasificacion);
           statement.setInt(4, duracion);
           statement.setString(5, idioma);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }
    
    /**
     * Método para actualizar una película existente en la base de datos.
     * @param id ID de la película a actualizar.
     * @param titulo Nuevo título de la película.
     * @param actores Nuevos actores de la película.
     * @param clasificacion Nueva clasificación de la película.
     * @param duracion Nueva duración de la película en minutos.
     * @param idioma Nuevo idioma de la película.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    
     public boolean update(int id, String titulo, String actores, String clasificacion, int duracion, String idioma){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "UPDATE pelicula SET titulo = ?, actores = ?, clasificacion = ?, duracion = ?, idioma = ? WHERE id = ?";
           PreparedStatement statement = conexion.prepareStatement(query);
           statement.setString(1, titulo);
           statement.setString(2, actores);
           statement.setString(3, clasificacion);
           statement.setInt(4, duracion);
           statement.setString(5, idioma);
           statement.setInt(6, id);
           statement.execute();

           result = statement.getUpdateCount() == 1;

           conexion.close();
       } catch(Exception ex){
           System.err.println("Error: " + ex.getMessage());
       }
       return result;
    }

     /**
     * Método para eliminar una película por su ID.
     * @param id ID de la película a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean delete(int id){
       boolean result = false;
       try {
           Connection conexion = ConnectionDB.get();
           String query = "DELETE FROM pelicula WHERE id = ?";
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
     * @return the actores
     */
    public String getActores() {
        return actores;
    }

    /**
     * @param actores the actores to set
     */
    public void setActores(String actores) {
        this.actores = actores;
    }

    /**
     * @return the clasificación
     */
    public String getClasificación() {
        return clasificación;
    }

    /**
     * @param clasificación the clasificación to set
     */
    public void setClasificación(String clasificación) {
        this.clasificación = clasificación;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
   
    
}
