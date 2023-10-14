package br.com.bielantonio.todolist.user;



/*Convenção: nome de classe começa com letra maiúscula - para cada nova palavra, coloca-se uma
letra maiúscula*/

/*
* Modificador
* public - qualquer um pode acessar essa minha classe;
* private - somente alguns atributos permitidos;
* protected - quando está na mesma estrutura do projeto
* */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
    /*
   Métodos de acesso do HTTP
   * GET - Buscar uma informação
   * POST - Adicionar um dado/informação
   * PUT - Alterar um dado/info
   * DELETE - Remover um dado
   * PATCH - Alterar somente uma parte da info/dado
   * */
    //Void: sem retorno no método - só executa alguma lógica.
//    Body -> Método (Funcionalidade) de uma classe

    @Autowired
    private IUserRepository userRepository;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if( user != null){
//            System.out.println("Usuário já existe");
            //Mensagem de erro
            //Status Code -> status dentro da requisição (sucesso ou erro)
            //Colocar dentro de um objeto de erro
            //Criar erros customizáveis
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!!");
        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString( 12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated =  this.userRepository.save(userModel);
        //ResponseEntity: passar para retorno algo que deu sucesso ou erro
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
//        System.out.println(userModel.getUsername());
    }
}
