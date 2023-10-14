package br.com.bielantonio.todolist; /*pacote onde a classe está inserida*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication /*Annotations: interface que vai exutar alguma coisa (função)*/
public class TodolistApplication {

	/*Método principal para executar alguma coisa*/
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
