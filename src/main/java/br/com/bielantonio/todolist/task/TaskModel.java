package br.com.bielantonio.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_taks")
public class TaskModel {

    /*
    *
    * ID
    * Usuário (ID_USUARIO)
    * Descrição
    * Titulo
    * Data de início
    * Data de término
    * Prioridade
    *
    * */

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50) //varchar: padrão tem 255 caracteres
    private String title;
    private LocalDateTime startAt;
    private  LocalDateTime endAt;
    private String priority;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID idUser;

    //throws Exception: repassando essa exceção para camda acima
    public void setTitle(String title) throws Exception{
        if (title.length() > 50){
            //Criar exceção
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title=title;
    }


}
