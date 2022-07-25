package com.ufsm.csi.backend.repository;

import com.ufsm.csi.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


    @Query(value = "SELECT * FROM USUARIO U WHERE U.LOGIN = ? AND ATIVO = 'S'", nativeQuery = true)
    Usuario findByLoginAndSenha(@Param("login") String login);

    @Query(value = "SELECT * FROM USUARIO U WHERE U.CPF = ? AND ATIVO = 'S'", nativeQuery = true)
    Usuario findByCpf(@Param("login") String cpf);


    @Modifying
    @Query(value = "UPDATE USUARIO U SET U.TIPO='F' WHERE U.ID=?1", nativeQuery = true)
    int tornaFuncionario(Integer idUsuario);
}
