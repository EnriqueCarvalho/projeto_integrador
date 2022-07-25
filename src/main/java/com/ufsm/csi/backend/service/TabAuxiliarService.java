package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.TabAuxiliar;
import com.ufsm.csi.backend.repository.TabAuxiliarRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabAuxiliarService {

    @Autowired
    private final TabAuxiliarRepository tabAuxiliarRepository;
    public TabAuxiliarService(TabAuxiliarRepository tabAuxiliarRepository) {
        this.tabAuxiliarRepository = tabAuxiliarRepository;
    }

    public List<TabAuxiliar> getTabAuxiliar(Integer idQuadra, Integer codTaela){
        return this.tabAuxiliarRepository.getTabAuxiliar(idQuadra,codTaela);
    }

    public TabAuxiliar cadastrarTabAux(TabAuxiliar tabAuxiliar){
        tabAuxiliar.setItemTab(1);
        return this.tabAuxiliarRepository.save(tabAuxiliar);
    }

    public void deletarItemTabAux(TabAuxiliar tabAuxiliar){
        this.tabAuxiliarRepository.delete(tabAuxiliar);
    }
}
