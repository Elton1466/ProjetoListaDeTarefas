// src/main/java/br/com/seuprojeto/util/Conexao.java
package br.com.listadetarefas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:sqlite:tarefas.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}