package br.com.listadetarefas.ui;

import br.com.listadetarefas.model.Tarefa;
import java.util.List;

public interface ListaDeTarefasUI {
    void exibirMenu();
    int obterOpcao();
    String obterDescricaoDaTarefa();
    int obterIdDaTarefa();
    boolean obterStatusDaTarefa();
    void exibirTarefas(List<Tarefa> tarefas);
    void exibirMensagem(String mensagem);
    void fechar();
}
