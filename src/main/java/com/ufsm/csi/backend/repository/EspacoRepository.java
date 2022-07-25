package com.ufsm.csi.backend.repository;

import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Integer> {

    @Query(value = "SELECT * FROM ESPACO E WHERE E.ID_QUADRA = ?", nativeQuery = true)
    List<Espaco> getEspacosByQuadra(@Param("idQuadra") Integer idQuadra);
}
