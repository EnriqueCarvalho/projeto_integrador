package com.ufsm.csi.backend.repository;


import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = "SELECT * FROM RESERVA R WHERE R.ID_USUARIO = ?", nativeQuery = true)
    List<Reserva> getReservasByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT * FROM RESERVA R JOIN ESPACO E ON E.ID = R.ID_ESPACO WHERE E.ID_QUADRA = ?", nativeQuery = true)
    List<Reserva> getReservasByQuadra(@Param("idQuadra") Integer idQuadra);

}
