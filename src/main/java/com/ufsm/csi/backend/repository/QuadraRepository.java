package com.ufsm.csi.backend.repository;

import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuadraRepository extends JpaRepository <Quadra,Integer> {

    @Query(value = "SELECT Q.* FROM QUADRA Q JOIN FUNCIONARIO F ON F.ID_QUADRA = Q.ID WHERE F.ID_USUARIO = ?", nativeQuery = true)
    Quadra getQuadraByUsuario(@Param("idUsuario") Integer idUsuario);
}
