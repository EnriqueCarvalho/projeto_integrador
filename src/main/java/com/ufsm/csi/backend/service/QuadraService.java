package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.QuadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuadraService {

    @Autowired
    private QuadraRepository quadraRepository;


    public Quadra getQuadra(Integer idUsuario){
        return this.quadraRepository.getQuadraByUsuario(idUsuario);
    }

    public List<Quadra> getQuadras(){
        return this.quadraRepository.findAll();
    }

    public Optional<Quadra> getQuadraById(Integer idQuadra){
        return this.quadraRepository.findById(idQuadra);
    }

    public Quadra salvarQuadra(Quadra quadra){
        return this.quadraRepository.save(quadra);
    }


}
