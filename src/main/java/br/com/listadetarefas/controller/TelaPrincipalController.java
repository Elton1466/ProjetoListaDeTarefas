package br.com.listadetarefas.controller;

import br.com.listadetarefas.dao.TarefaDAO;
import br.com.listadetarefas.model.Tarefa;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

    public class TelaPrincipalController {

        @FXML private TextField textoTarefaTextField;
        @FXML private ListView<Tarefa> tarefasListView;
        @FXML private ToggleButton todasToggle, pendentesToggle, concluidasToggle;

        private final TarefaDAO tarefaDAO = new TarefaDAO();
        private final ToggleGroup filtroToggleGroup = new ToggleGroup();

        @FXML
        public void initialize() {
            todasToggle.setToggleGroup(filtroToggleGroup);
            pendentesToggle.setToggleGroup(filtroToggleGroup);
            concluidasToggle.setToggleGroup(filtroToggleGroup);
            pendentesToggle.setSelected(true);

            tarefasListView.setCellFactory(param -> new TarefaCell());
            filtrar();
        }

        @FXML
        private void adicionarTarefa() {
            String texto = textoTarefaTextField.getText();
            if (texto != null && !texto.trim().isEmpty()) {
                tarefaDAO.inserir(new Tarefa(texto));
                textoTarefaTextField.clear();
                filtrar();
            }
        }

        @FXML
        private void filtrar() {
            List<Tarefa> tarefas;
            if (concluidasToggle.isSelected()) {
                tarefas = tarefaDAO.listarConcluidas();
            } else if (todasToggle.isSelected()) {
                tarefas = tarefaDAO.listarTodas();
            } else {
                tarefas = tarefaDAO.listarPendentes();
            }
            tarefasListView.getItems().setAll(tarefas);
        }

        private class TarefaCell extends ListCell<Tarefa> {
            private final HBox layout = new HBox(10);
            private final CheckBox checkBox = new CheckBox();
            private final Label label = new Label();
            private final Button removerButton = new Button("Remover");
            private Tarefa tarefaAtual;

            public TarefaCell() {
                super();
                layout.getChildren().addAll(checkBox, label, removerButton);
                HBox.setHgrow(label, Priority.ALWAYS);
                layout.setAlignment(Pos.CENTER_LEFT);

                checkBox.setOnAction(event -> {
                    if (tarefaAtual != null) {
                        tarefaDAO.alterarStatus(tarefaAtual.getId(), checkBox.isSelected());
                        filtrar();
                    }
                });

                removerButton.setOnAction(event -> {
                    if (tarefaAtual != null) {
                        tarefaDAO.remover(tarefaAtual.getId());
                        filtrar();
                    }
                });
            }

            @Override
            protected void updateItem(Tarefa tarefa, boolean empty) {
                super.updateItem(tarefa, empty);
                tarefaAtual = tarefa;

                if (empty || tarefa == null) {
                    setGraphic(null);
                } else {
                    label.setText(tarefa.getTexto());
                    checkBox.setSelected(tarefa.isConcluido());
                    setGraphic(layout);

                    if (tarefa.isConcluido()) {
                        checkBox.setStyle("-fx-color: #48B33C;");
                    } else {
                        checkBox.setStyle("");
                    }
                }
            }
        }
    }

