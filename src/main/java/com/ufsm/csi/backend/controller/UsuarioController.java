package com.ufsm.csi.backend.controller;

import com.ufsm.csi.backend.dto.UsuarioFuncionarioDto;
import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.QuadraRepository;
import com.ufsm.csi.backend.repository.UsuarioRepository;
import com.ufsm.csi.backend.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController

public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private QuadraRepository quadraRepository;



    private ModelMapper modelMapper = new ModelMapper();



    @CrossOrigin
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){

        System.out.println("Salvando usuario com login : "+usuario.getLogin());
        return this.usuarioService.cadastrarUsuario(usuario);

    }

    @CrossOrigin
    @PostMapping("/login")
    public UsuarioFuncionarioDto login(@RequestBody Usuario usuario){
        UsuarioFuncionarioDto usuarioFuncionarioDto = new UsuarioFuncionarioDto();
        try{
            System.out.println("Procurando usuário com login: "+usuario.getLogin());

            usuarioFuncionarioDto = this.modelMapper.map(this.usuarioService.login(usuario), UsuarioFuncionarioDto.class);

            if(usuarioFuncionarioDto.getTipo().equals("F") || usuarioFuncionarioDto.getTipo().equals("A")){

                usuarioFuncionarioDto.setIdQuadra(this.quadraRepository.getQuadraByUsuario(usuarioFuncionarioDto.getId()).getId());
            }

            return usuarioFuncionarioDto;
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Usuário e/ou senha incorretos"
            );
        }



    }

    @CrossOrigin
    @GetMapping("/usuario")
    public Optional<Usuario> getUsuarioById(@RequestHeader("X-usuario") String usuario, HttpServletResponse response) throws IOException {

        if(!usuario.isEmpty()){
            Integer id ;
            id = parseInt(usuario.replaceAll("id:",""));
            System.out.println("Procurando usuário com id: "+ usuario.replaceAll("id:",""));

            Optional<Usuario> u = this.usuarioService.getUsuarioById(id);

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

        usuario.setAtivo("N");
        try{
            this.usuarioService.desativarConta(usuario);
            return null;
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }



    }
}
