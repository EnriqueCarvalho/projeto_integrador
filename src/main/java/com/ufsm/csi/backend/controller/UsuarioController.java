package com.ufsm.csi.backend.controller;

import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.UsuarioRepository;
import com.ufsm.csi.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;



    @CrossOrigin
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){

        System.out.println("Salvando usuario com login : "+usuario.getLogin());
        return this.usuarioService.cadastrarUsuario(usuario);

    }

    @CrossOrigin
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario){

        System.out.println("Procurando usuário com login: "+usuario.getLogin());
        return this.usuarioService.login(usuario);

    }

    @CrossOrigin
    @GetMapping("/usuario")
    public Optional<Usuario> getUsuarioById(@RequestHeader("X-usuario") String usuario){

        if(!usuario.isEmpty()){
            Integer id ;
            id = parseInt(usuario.replaceAll("id:",""));

            System.out.println("Procurando usuário com id: "+ usuario.replaceAll("id:",""));
            return this.usuarioService.getUsuarioById(id);
        }else{
            return null;
        }

    }

    @CrossOrigin
    @PutMapping("/desativarconta")
    public Optional<Usuario> desativarConta(@RequestHeader("X-usuario") String usuarioLogado,@RequestBody Usuario usuario){

        usuario.setAtivo("N");

        this.usuarioService.desativarConta(usuario);
        return null;

    }
}
