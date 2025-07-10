package br.com.listadetarefas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlPath = "TelaPrincipal.fxml";
        URL fxmlUrl = Thread.currentThread().getContextClassLoader().getResource(fxmlPath);

        if (fxmlUrl == null) {
            throw new java.io.FileNotFoundException("ERRO CRÍTICO: 'TelaPrincipal.fxml' não foi encontrado! Certifique-se de que o arquivo está na pasta 'resources'.");
        }

        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("Controle de Tarefas");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}