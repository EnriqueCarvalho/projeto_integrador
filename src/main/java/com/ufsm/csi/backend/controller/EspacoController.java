package com.ufsm.csi.backend.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.service.EspacoService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EspacoController {

    @Autowired
    private EspacoService espacoService;

    @CrossOrigin
    @GetMapping("/visualizar-espacos")
    public List<Espaco> getEspacosByQuadra(@ModelAttribute("idQuadra") String idQuadra){
        System.out.println("Aqui");

        try{
            List<Espaco> espacos = this.espacoService.getEspacosByQuadra(Integer.parseInt(idQuadra));
            System.out.println("Buscando quadra para o usuario : "+idQuadra);
            return espacos;
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }


    }

    @CrossOrigin
    @GetMapping("/visualizar-espaco")
    public Optional<Espaco> getEspacoById(@ModelAttribute("idEspaco") String idEspaco){
        System.out.println("Aqui");

        try{
            Optional<Espaco> espaco = this.espacoService.getEspacoById(Integer.parseInt(idEspaco));
            System.out.println("Buscando espaco com id: : "+idEspaco);
            return espaco;
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }

    }


    @CrossOrigin
    @DeleteMapping("/excluir-espaco")
    public String excluirEspaco(@ModelAttribute("idEspaco") String idEspaco){
        System.out.println("Aqui");

        try{
            this.espacoService.excluirEspaco(Integer.parseInt(idEspaco));
            System.out.println("Excluindo espaco com id: : "+idEspaco);
            return "sucess";
        }catch (Exception e ){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }


    @CrossOrigin
    @PostMapping("/cadastrar-espaco")
    public String cadastrarEspaco(@RequestBody Espaco espaco){

        try{
            System.out.println("Cadastrando o espaco "+espaco.getNome()+", para a quadra "+espaco.getQuadra().getId());

            this.espacoService.cadastrarEspaco(espaco);
            return "true";
        }catch (Exception e ){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }

    }
}
