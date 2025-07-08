# Aplicação Lista de Tarefas

## Descrição do Projeto
Este projeto é uma aplicação de lista de tarefas, o sistema permite cadastrar, alterar o status e filtrar tarefas em uma lista. As tarefas são armazenadas em um banco de dados SQLite, utilizando a API JDBC. O objetivo principal é praticar o uso de banco de dados e manipulação de dados em Java, além de trabalhar com o fluxo de desenvolvimento colaborativo em um projeto de software.

## Funcionalidades
### Cadastrar tarefa:

  Adiciona uma nova tarefa à lista.

### Alterar status de tarefa:

  Modifica o status da tarefa, marcando-a como "concluída" ou "pendente".

### Filtrar tarefas:
  Filtra as tarefas por:
  
  Todas as Tarefas: Exibe todas as tarefas cadastradas.
  
  Tarefas Pendentes: Exibe as tarefas que ainda não foram concluídas.
  
  Tarefas Concluídas: Exibe as tarefas que já foram concluídas.

### Cada tarefa possui os seguintes campos:

  Texto: Descrição da tarefa a ser realizada.
  
  Concluído: Indica se a tarefa foi ou não concluída.
  
  Data de Alteração: A data em que o status da tarefa foi alterado pela última vez.

## Tecnologias Utilizadas
Java: Linguagem de programação para desenvolvimento da aplicação.

JDBC: API Java para interação com o banco de dados.

SQLite: Banco de dados utilizado para armazenar as tarefas.

Git: Controle de versão utilizado para o desenvolvimento colaborativo.

## Requisitos
JDK 8 ou superior.

Banco de dados SQLite (será criado automaticamente pela aplicação).

IDE para desenvolvimento (como IntelliJ IDEA, Eclipse, ou qualquer outra de sua preferência).
