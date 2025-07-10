package br.com.listadetarefas.ui;

import br.com.listadetarefas.model.Tarefa;

import java.util.List;
import java.util.Scanner;

public class TerminalListaDeTarefasUI implements ListaDeTarefasUI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void exibirMenu() {
        System.out.println("\n===== MENU DE TAREFAS =====");
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Alterar status de tarefa");
        System.out.println("3. Listar todas as tarefas");
        System.out.println("4. Listar tarefas pendentes");
        System.out.println("5. Listar tarefas concluídas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    @Override
    public int obterOpcao() {
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    @Override
    public String obterDescricaoDaTarefa() {
        System.out.print("Digite a descrição da tarefa: ");
        return scanner.nextLine();
    }

    @Override
    public int obterIdDaTarefa() {
        System.out.print("ID da tarefa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public boolean obterStatusDaTarefa() {
        while (true) {
            System.out.print("Marcar como concluída? (true/false): ");
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("true") || entrada.equals("false")) {
                return Boolean.parseBoolean(entrada);
            } else {
                System.out.println("Entrada inválida. Digite apenas 'true' ou 'false'.");
            }
        }
    }

    @Override
    public void exibirTarefas(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void fechar() {
        scanner.close();
    }
}

