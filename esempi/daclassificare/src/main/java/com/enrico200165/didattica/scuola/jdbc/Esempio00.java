/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.didattica.jdbc;

import java.util.ArrayList;
import java.sql.*;

public class Esempio00 {

    public static String strVal(ResultSet rs, int nrCol, 
            ArrayList<String> nomiCols, ArrayList<String> tipiCols) {
        
        String nomeCol = nomiCols.get(nrCol);
        String tipoCol = tipiCols.get(nrCol);
        
        try {
        if (tipoCol.toLowerCase().contains("string")) {            
            return rs.getString(nomeCol);
        } else if (tipoCol.toLowerCase().contains("int")) {
            return ""+rs.getInt(nomeCol);            
        } else if (tipoCol.toLowerCase().contains("double")) {
            return ""+rs.getDouble(nomeCol);            
        } else {
            return "tipo attualmente non gestito: "+ tipoCol;
        }
        } catch (SQLException e ) {
            System.err.println("eccezione "+e);
        }
        return "";
    }
    
    public static void main(String[] args) {
        // carico il driver per la connessione al DB MySQL
        // String DRIVER  = "com.mysql.jdbc.Driver"; VECCHIO, DEPRECATO
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e1) {          // il driver non può essere caricato
            System.out.println("Driver non trovato... classe: "+DRIVER);
            System.exit(1);
        }

        // nome ed indirizzo del database
        String URL_mioDB = "jdbc:mysql://localhost:3306/formichi_prodotti";

        // definizione delle query 
        String query = "select * from prodotti ;";

        // stabilisco la connessione 
        System.out.println("Connessione con: " + URL_mioDB);

        Connection connessione = null;
        try {
            connessione = DriverManager.getConnection(URL_mioDB, "root", "");
        } catch (SQLException e) {                              // gestione dell'errore 
            System.out.println("Errore nella connessione: " + e);
            System.exit(1);
        }

        try // creazione resultSet ed analisi dei dati 
        {
            // creo uno Statement per interagire con il database
            Statement statement = connessione.createStatement();
            // interrogo il DBMS mediante una query SQL
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<String> nomiCols = new ArrayList<>();
            ArrayList<String> tipiCols = new ArrayList<>();
    
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String colName = rsmd.getColumnName(i);
                System.out.println("trovata colonna: "+colName+ " tipo: "+ rsmd.getColumnClassName(i));
                nomiCols.add(colName);
                tipiCols.add(rsmd.getColumnClassName(i));
            }
            int nrCol = nomiCols.size();
            
            // Scorro e mostro i risultati.
            System.out.println("Lette informazioni...");
            while (resultSet.next()) {
                for (int i = 0; i < nrCol; i++) {
                    String val = strVal(resultSet, i, nomiCols, tipiCols);
                    System.out.print(val+", ");
                }
                System.out.println();
            }
        } catch (SQLException e) // gestione dell'errore 
        {
            System.out.println("Errore nella  query SQL");
        } finally {
            if (connessione != null) {
                try {
                    connessione.close();
                } catch (SQLException e) {
                    System.out.println("Errore nella chiusura connessione :<br>");
                }
            }
        }
    }
}
