package br.com;

import br.com.listadetarefas.model.Tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final List<Tarefa> tarefas = new ArrayList<>();
    private static int proximoId = 1;

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
                    Tarefa nova = new Tarefa(proximoId++, texto, false, LocalDateTime.now());
                    tarefas.add(nova);
                    System.out.println("Tarefa adicionada com sucesso.");
                    break;

                case 2:
                    System.out.print("ID da tarefa: ");
                    int id = scanner.nextInt();
                    System.out.print("Marcar como concluída? (true/false): ");
                    boolean status = scanner.nextBoolean();

                    boolean encontrada = false;
                    for (Tarefa t : tarefas) {
                        if (t.getId() == id) {
                            t.setConcluido(status);
                            encontrada = true;
                            System.out.println("Status atualizado.");
                            break;
                        }
                    }

                    if (!encontrada) {
                        System.out.println("Tarefa não encontrada.");
                    }
                    break;

                case 3:
                    listar(tarefas);
                    break;

                case 4:
                    listar(filtrarPorStatus(false));
                    break;

                case 5:
                    listar(filtrarPorStatus(true));
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listar(List<Tarefa> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static List<Tarefa> filtrarPorStatus(boolean status) {
        List<Tarefa> filtradas = new ArrayList<>();
        for (Tarefa t : tarefas) {
            if (t.isConcluido() == status) {
                filtradas.add(t);
            }
        }
        return filtradas;
    }
}
