package br.com.listadetarefas;

import br.com.listadetarefas.dao.TarefaDAO;
import br.com.listadetarefas.model.Tarefa;
import br.com.listadetarefas.ui.TerminalListaDeTarefasUI;
import br.com.listadetarefas.ui.ListaDeTarefasUI;

public class App {
    private static final TarefaDAO dao = new TarefaDAO();
    private static final ListaDeTarefasUI ui = new TerminalListaDeTarefasUI();

    public static void main(String[] args) {
        int opcao;

        do {
            ui.exibirMenu();
            opcao = ui.obterOpcao();

            switch (opcao) {
                case 1:
                    String texto = ui.obterDescricaoDaTarefa();
                    dao.inserir(new Tarefa(texto));
                    break;
                case 2:
                    int id = ui.obterIdDaTarefa();
                    boolean status = ui.obterStatusDaTarefa();
                    dao.alterarStatus(id, status);
                    break;
                case 3:
                    ui.exibirTarefas(dao.listarTodas());
                    break;
                case 4:
                    ui.exibirTarefas(dao.listarPendentes());
                    break;
                case 5:
                    ui.exibirTarefas(dao.listarConcluidas());
                    break;
                case 0:
                    ui.exibirMensagem("Saindo...");
                    break;
                default:
                    ui.exibirMensagem("Opção inválida!");
            }
        } while (opcao != 0);

        ui.fechar();
    }
}