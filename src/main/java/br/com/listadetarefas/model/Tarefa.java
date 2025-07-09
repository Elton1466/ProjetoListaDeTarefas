package br.com.listadetarefas.model;

import java.time.LocalDateTime;

public class Tarefa {
    private int id;
    private String texto;
    private boolean concluido;
    private LocalDateTime dataAlteracao;

    public Tarefa(String texto) {
        this.texto = texto;
        this.concluido = false;
        this.dataAlteracao = LocalDateTime.now();
    }

    public Tarefa(int id, String texto, boolean concluido, LocalDateTime dataAlteracao) {
        this.id = id;
        this.texto = texto;
        this.concluido = concluido;
        this.dataAlteracao = dataAlteracao;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
        this.dataAlteracao = LocalDateTime.now();
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | Última alteração: %s", id, texto, (concluido ? "Concluído" : "Pendente"), dataAlteracao);
    }
}
