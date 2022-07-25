package com.ufsm.csi.backend.controller;


import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Funcionario;
import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.QuadraRepository;
import com.ufsm.csi.backend.service.EspacoService;
import com.ufsm.csi.backend.service.FuncionarioService;
import com.ufsm.csi.backend.service.QuadraService;
import com.ufsm.csi.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private QuadraService quadraService;

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping("/visualizar-funcionarios")
    public List<Funcionario> getFuncionariosByQuadra(@ModelAttribute("idQuadra") String idQuadra){
        System.out.println("Aqui");

        try{
            List<Funcionario> funcionarios = this.funcionarioService.getFuncionariosByQuadra(Integer.parseInt(idQuadra));
            System.out.println("Buscando funcionarios para a quadra: "+idQuadra);
            return funcionarios;
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }


    @CrossOrigin
    @PostMapping("/cadastrar-funcionario")
    public Funcionario cadastrarFuncionario(@RequestBody Funcionario funcionario){
        System.out.println("Cadastrando funcionário com o cpf: "+funcionario.getUsuario().getCpf());
        Usuario u = this.usuarioService.getUsuarioByCpf(funcionario.getUsuario().getCpf());


            if(u != null){
                System.out.println(u.getId());
                this.usuarioService.tornaFuncionario(u.getId());

                funcionario.setUsuario(u);
                funcionario.setQuadra(this.quadraService.getQuadraById(funcionario.getQuadra().getId()).get());
                this.funcionarioService.cadastrarFuncionario(funcionario);
                return funcionario;
            }else{
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Usuario não cadastrado na base de dados"
                );
            }


    }
}
