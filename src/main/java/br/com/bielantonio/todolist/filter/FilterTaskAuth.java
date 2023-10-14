package br.com.bielantonio.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.bielantonio.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        var servletPath = request.getServletPath();
        System.out.println("PATH " + servletPath);
        if (servletPath.startsWith("/tasks/")) {

            // Pegar a autenticação (usuário e senha)
            var authorization = request.getHeader("Authorization");
            var auth_Encoded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(auth_Encoded);

            var authString = new String(authDecode);

//        ["bielantonio", "12345"]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Validar usuário
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                System.out.println("Authorization");
                System.out.println(username);
                System.out.println(password);
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    // Segue viagem

                    /*request: tudo que está vindo da requisição
                    * response: tudo que é enviado para o usuário
                    * */
                    request.setAttribute("idUser", user.getId());
                    chain.doFilter(request, response);

                } else {
                    response.sendError(401);
                }
            }
        } else {


            chain.doFilter(request, response);
        }
    }
}
