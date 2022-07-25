package com.ufsm.csi.backend.repository;

import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {

    @Query(value = "select * from funcionario f  where f.id_quadra = ? ", nativeQuery = true)
    List<Funcionario> getFuncionariosByQuadra(@Param("idQuadra") Integer idQuadra);

}
