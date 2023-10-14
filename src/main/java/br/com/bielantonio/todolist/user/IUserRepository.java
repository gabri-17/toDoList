package br.com.bielantonio.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Contrato: representação dos métodos disponíveis dentro das classe (sem implementação)
//<> : atributos mais dinâmicos
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
