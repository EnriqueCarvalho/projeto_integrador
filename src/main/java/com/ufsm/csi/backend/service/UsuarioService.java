package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.UsuarioRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario cadastrarUsuario(Usuario u){

        if(u.getTipo().isEmpty()){
            u.setTipo("C");
        }
        u.setAtivo("S");

        Usuario usuario = this.usuarioRepository.save(u);
        return usuario;
    }

    public Usuario login(Usuario u){

        Usuario usuario = this.usuarioRepository.findByLoginAndSenha(u.getLogin());

        if(usuario != null){
            System.out.println("Usuário "+u.getLogin()+" encontrado");
            if(usuario.getSenha().equals(u.getSenha())){
                return usuario;
            }else{
                System.out.println("Senha incorreta do Usuário: "+u.getLogin());
            }
        }
        return null;
    }

    public Optional<Usuario> getUsuarioById(Integer id){

        Optional<Usuario> usuario = this.usuarioRepository.findById(id);


        return usuario;
    }

    public Usuario desativarConta(Usuario u){

       this.usuarioRepository.save(u);


        return null;
    }




}
