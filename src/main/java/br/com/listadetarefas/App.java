package br.com.listadetarefas;

import br.com.listadetarefas.dao.TarefaDAO;
import br.com.listadetarefas.model.Tarefa;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final TarefaDAO dao = new TarefaDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE TAREFAS =====");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Alterar status de tarefa");
            System.out.println("3. Listar todas as tarefas");
            System.out.println("4. Listar tarefas pendentes");
            System.out.println("5. Listar tarefas concluídas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a descrição da tarefa: ");
                    String texto = scanner.nextLine();
                    dao.inserir(new Tarefa(texto));
                    break;
                case 2:
                    System.out.print("ID da tarefa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    boolean statusValido = false;
                    boolean status = false;
                    while (!statusValido) {
                        System.out.print("Marcar como concluída? (true/false): ");
                        String entrada = scanner.nextLine().trim().toLowerCase();
                        if (entrada.equals("true") || entrada.equals("false")) {
                            status = Boolean.parseBoolean(entrada);
                            statusValido = true;
                        } else {
                            System.out.println("Entrada inválida. Digite apenas 'true' ou 'false'.");
                        }
                    }
                    dao.alterarStatus(id, status);
                    break;
                case 3:
                    listar(dao.listarTodas());
                    break;
                case 4:
                    listar(dao.listarPendentes());
                    break;
                case 5:
                    listar(dao.listarConcluidas());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listar(List<Tarefa> tarefas) {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }
}