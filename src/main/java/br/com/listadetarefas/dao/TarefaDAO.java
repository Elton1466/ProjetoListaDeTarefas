package br.com.listadetarefas.dao;

import br.com.listadetarefas.model.Tarefa;
import br.com.listadetarefas.util.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    public TarefaDAO() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "texto TEXT NOT NULL," +
                "concluido BOOLEAN NOT NULL," +
                "data_alteracao TEXT NOT NULL)";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas(texto, concluido, data_alteracao) VALUES(?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tarefa.getTexto());
            pstmt.setBoolean(2, tarefa.isConcluido());
            pstmt.setString(3, tarefa.getDataAlteracao().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarStatus(int id, boolean status) {
        String sql = "UPDATE tarefas SET concluido = ?, data_alteracao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, status);
            pstmt.setString(2, LocalDateTime.now().toString());
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Tarefa> listarPorFiltro(String filtro) {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas " + filtro;

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tarefa t = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("texto"),
                        rs.getBoolean("concluido"),
                        LocalDateTime.parse(rs.getString("data_alteracao"))
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Tarefa> listarTodas() {
        return listarPorFiltro("");
    }

    public List<Tarefa> listarPendentes() {
        return listarPorFiltro("WHERE concluido = 0");
    }

    public List<Tarefa> listarConcluidas() {
        return listarPorFiltro("WHERE concluido = 1");
    }
}