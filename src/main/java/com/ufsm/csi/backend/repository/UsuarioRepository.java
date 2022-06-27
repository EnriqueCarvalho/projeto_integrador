package com.ufsm.csi.backend.repository;

import com.ufsm.csi.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


    @Query(value = "SELECT * FROM USUARIO U WHERE U.LOGIN = ? AND ATIVO = 'S'", nativeQuery = true)
    Usuario findByLoginAndSenha(@Param("login") String login);
}
