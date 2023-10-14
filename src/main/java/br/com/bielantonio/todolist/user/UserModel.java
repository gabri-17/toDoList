package br.com.bielantonio.todolist.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {
//    Atributos de uma classe do objeto
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

//    @Column(name = "usuario")
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //getters  / setters

//    //inserir valor
//    public void setUsername(String username) {
//        //Corresponde a [linha 7] atribui a ela o valor dentro do setUsername
//        this.username = username;
//    }
//
//    //buscar valor
//    public String getUsername() {
//        return username;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
}


