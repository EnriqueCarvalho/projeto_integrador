package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.UsuarioRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario cadastrarUsuario(Usuario u){

        u.setSenha(DigestUtils.sha256Hex(u.getSenha()));

        if(u.getTipo().isEmpty()){
            u.setTipo("C");
        }
        u.setAtivo("S");

        Usuario usuario = this.usuarioRepository.save(u);
        return usuario;
    }

    @Override
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

    @Override
    public Optional<Usuario> getUsuarioById(Integer id){


        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        usuario.get().setSenha("");
        return usuario;
    }

    @Override
    public Usuario getUsuarioByLogin(String login){
        Usuario usuario = this.usuarioRepository.findByLogin(login);
        usuario.setSenha("");
        return usuario;
    }

    @Override
    public Usuario getUsuarioByCpf(String cpf){
        Usuario usuario = this.usuarioRepository.findByCpf(cpf);
        return usuario;
    }

    @Override
    public Usuario desativarConta(Usuario u){
       this.usuarioRepository.save(u);
        return null;
    }

    @Override
    public void tornaFuncionario(Integer idUsuario){
        this.usuarioRepository.tornaFuncionario(idUsuario);
    }
}
