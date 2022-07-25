package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Funcionario;
import com.ufsm.csi.backend.repository.EspacoRepository;
import com.ufsm.csi.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private final FuncionarioRepository funcionarioRepository;
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    public List<Funcionario> getFuncionariosByQuadra(Integer idQuadra){
        List<Funcionario> funcionarios =  this.funcionarioRepository.getFuncionariosByQuadra(idQuadra);
        return funcionarios;
    }

    public Funcionario cadastrarFuncionario(Funcionario f){
        f.setPerm("F");
        Funcionario funcionarios =  this.funcionarioRepository.save(f);
        return funcionarios;
    }

}
