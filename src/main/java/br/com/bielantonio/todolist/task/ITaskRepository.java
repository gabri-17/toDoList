package br.com.bielantonio.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID idUser);
//    TaskModel findByIdAndIdUser(UUID id, UUID idUser);
}