package com.ufsm.csi.backend.controller;

import com.ufsm.csi.backend.decorator.UsuarioServiceDecorator;
import com.ufsm.csi.backend.dto.UsuarioFuncionarioDto;
import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.QuadraRepository;
import com.ufsm.csi.backend.repository.UsuarioRepository;
import com.ufsm.csi.backend.security.JWTUtil;
import com.ufsm.csi.backend.service.UsuarioService;
import com.ufsm.csi.backend.service.UsuarioServiceInterface;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController

public class UsuarioController {


    @Autowired
    private UsuarioServiceInterface usuarioServ;

    @Autowired
    private QuadraRepository quadraRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    private ModelMapper modelMapper = new ModelMapper();



    @CrossOrigin
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) throws IOException {
        UsuarioServiceDecorator usuarioService = new UsuarioServiceDecorator(usuarioServ);

        System.out.println("Salvando usuario com login : "+usuario.getLogin());
        return usuarioService.cadastrarUsuario(usuario);

    }


    @PostMapping("/login")
    @CrossOrigin()
    public  ResponseEntity<Object> login(@RequestBody Usuario usuario){
        UsuarioFuncionarioDto usuarioFuncionarioDto = new UsuarioFuncionarioDto();
        UsuarioServiceDecorator usuarioService = new UsuarioServiceDecorator(usuarioServ);
        try{
            usuario.setSenha(DigestUtils.sha256Hex(usuario.getSenha())) ;
            final Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha()));

            if(authentication.isAuthenticated()) {
                //colocamos nossa instancia autenticada no contexto do spring security
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Gerando token de autorizacao ****" + authentication.getAuthorities().toString());
                String token = new JWTUtil().geraToken(usuario);

                Usuario u = usuarioService.getUsuarioByLogin(usuario.getLogin());

                usuario.setToken(token);
                usuario.setId(u.getId());
                usuario.setSenha("");

                usuario.setTipo(authentication.getAuthorities().toString());

                usuarioFuncionarioDto = this.modelMapper.map(usuario, UsuarioFuncionarioDto.class);


                if (usuarioFuncionarioDto.getTipo().equals("[F]") || usuarioFuncionarioDto.getTipo().equals("[A]")) {

                    System.out.println(usuarioFuncionarioDto.getId());
                    usuarioFuncionarioDto.setIdQuadra(this.quadraRepository.getQuadraByUsuario(u.getId()).getId());
                }

                System.out.println(usuarioFuncionarioDto);
                return new ResponseEntity<>(usuarioFuncionarioDto, HttpStatus.OK);


            }

        }catch (Exception e ) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Usuário e/ou senha incorretos" + e.getMessage()
            );
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @GetMapping("/usuario")
    public Optional<Usuario> getUsuarioById(@RequestHeader("X-usuario") String usuario, HttpServletResponse response) throws IOException {
        UsuarioServiceDecorator usuarioService = new UsuarioServiceDecorator(usuarioServ);
        if(!usuario.isEmpty()){
            Integer id ;
            id = parseInt(usuario.replaceAll("id:",""));
            System.out.println("Procurando usuário com id: "+ usuario.replaceAll("id:",""));

            Optional<Usuario> u = usuarioService.getUsuarioById(id);

            if(!u.isEmpty()){
                return u;
            }else{

                throw new ResponseStatusException(
                        HttpStatus.LOCKED, "Usuario e/ou senha incorretos"
                );
            }

        }else{
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Erro ao buscar usuário"
            );
        }

    }

    @CrossOrigin
    @PutMapping("/desativarconta")
    public Optional<Usuario> desativarConta(@RequestHeader("X-usuario") String usuarioLogado,@RequestBody Usuario usuario){
        UsuarioServiceDecorator usuarioService = new UsuarioServiceDecorator(usuarioServ);
        usuario.setAtivo("N");
        try{
            usuarioService.desativarConta(usuario);
            return null;
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }



    }
}
