package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Usuario;

import java.io.IOException;
import java.util.Optional;

public interface UsuarioServiceInterface {
    Usuario cadastrarUsuario(Usuario u) throws IOException;

    Usuario login(Usuario u) throws IOException;

    Optional<Usuario> getUsuarioById(Integer id) throws IOException;

    Usuario getUsuarioByLogin(String login) throws IOException;

    Usuario getUsuarioByCpf(String cpf) throws IOException;

    Usuario desativarConta(Usuario u) throws IOException;

    void tornaFuncionario(Integer idUsuario) throws IOException;
}
