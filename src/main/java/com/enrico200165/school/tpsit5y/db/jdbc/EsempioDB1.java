/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.school.tpsit5y.db.jdbc;


import java.sql.*;

public class EsempioDB1 {
    public static void main(String[] args) {
        // Driver JDBC per MySQL
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        String URL_mioDB = "jdbc:mysql://localhost:3306/proveJava";
        String query = "SELECT Cognome, Nome, Indirizzo, Citta FROM amici";

        // Caricamento del driver
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trovato: " + e);
            System.exit(1);
        }

        // Connessione al database
        Connection connessione = null;
        try {
            System.out.println("Connessione con: " + URL_mioDB);
            connessione = DriverManager.getConnection(URL_mioDB, "root", "");
        } catch (SQLException e) {
            System.out.println("Errore nella connessione: " + e);
            System.exit(1);
        }

        // Esecuzione della query
        try (Statement statement = connessione.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
             
            // Scorro e mostro i risultati
            while (resultSet.next()) {
                String cognome = resultSet.getString("Cognome");
                String nome = resultSet.getString("Nome");
                String indirizzo = resultSet.getString("Indirizzo");
                String citta = resultSet.getString("Citta");
                System.out.println("Cognome: " + cognome);
                System.out.println("Nome: " + nome);
                System.out.println("Indirizzo: " + indirizzo);
                System.out.println("Citta': " + citta);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Errore nella query SQL: " + e);
        } finally {
            // Chiusura della connessione
            if (connessione != null) {
                try {
                    connessione.close();
                } catch (SQLException e) {
                    System.out.println("Errore nella chiusura connessione: " + e);
                }
            }
        }
    }
}
