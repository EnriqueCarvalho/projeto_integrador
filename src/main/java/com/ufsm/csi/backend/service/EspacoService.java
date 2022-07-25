package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.repository.EspacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    @Autowired
    private final EspacoRepository espacoRepository;
    public EspacoService(EspacoRepository espacoRepository) {
        this.espacoRepository = espacoRepository;
    }

    public List<Espaco> getEspacosByQuadra(Integer idQuadra){
        List<Espaco> espacos =  this.espacoRepository.getEspacosByQuadra(idQuadra);
        return espacos;
    }

    public Optional<Espaco> getEspacoById(Integer idEspaco){
        Optional<Espaco> espaco =  this.espacoRepository.findById(idEspaco);
        return espaco;
    }

    public Espaco cadastrarEspaco(Espaco espaco){
       return this.espacoRepository.save(espaco);

    }

    public void excluirEspaco(Integer idEspaco){
       this.espacoRepository.deleteById(idEspaco);

    }
}
