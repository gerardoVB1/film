/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.film.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * La clase ConnectionDB proporciona un método estático para obtener una conexión a una base de datos MySQL utilizando JDBC.
 * @author Gerardo valdez
 */
public class ConnectionDB {
    /**
     * Método estático para obtener una conexión a la base de datos.
     * @return Objeto Connection que representa la conexión a la base de datos.
     */
    public static Connection get(){
       Connection connection = null;
       try{
           connection = DriverManager.getConnection("jdbc:mysql://localhost/filmdb?user=root&password=12342234=3306");
       }catch(Exception ex){
           System.err.print("Error: " + ex.getMessage());
       }
       return connection;
   }

}