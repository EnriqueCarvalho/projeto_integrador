package com.ufsm.csi.backend.repository;

import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.TabAuxiliar;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabAuxiliarRepository extends JpaRepository<TabAuxiliar, Integer> {

    @Query(value = "SELECT * FROM tab_auxiliar TA WHERE TA.ID_QUADRA = :idQuadra AND TA.COD_TAB = :codTab AND TA.ITEM_TAB <> 0 ORDER BY TA.VALOR_ALFA", nativeQuery = true)
    List<TabAuxiliar> getTabAuxiliar(   @Param("idQuadra") Integer idQuadra,
                                        @Param("codTab") Integer codTab
    );

    @Query(value = "select * from tab_auxiliar tab where tab.cod_tab = :codTab and tab.item_tab <> 0 and tab.valor_alfa not in(select r.hora as valor from reserva r where  r.id_espaco = :idEspaco and r.data = :data and r.motivo_cancel is null)", nativeQuery = true)
    List<TabAuxiliar> getHorariosDisp(  @Param("codTab") Integer codTab,
                                        @Param("idEspaco") Integer idEspaco,
                                        @Param("data") String data
    );
}
