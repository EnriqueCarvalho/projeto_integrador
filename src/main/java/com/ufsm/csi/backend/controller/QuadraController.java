package com.ufsm.csi.backend.controller;

import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.service.QuadraService;
import com.ufsm.csi.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class QuadraController {

    @Autowired
    private QuadraService quadraService;

    @CrossOrigin
    @GetMapping("/visualizar-quadra")
    public Quadra visualizarQuadra(@ModelAttribute("idUsuario") String idUsuario){

        try{
            System.out.println("Buscando quadra para o usuario : "+idUsuario);
            return this.quadraService.getQuadra(Integer.parseInt(idUsuario));

        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }


    @CrossOrigin
    @PutMapping("/salvar-quadra")
    public String salvarQuadra(@ModelAttribute("idUsuario") String idUsuario,
                               @RequestBody() Quadra quadra){

        try{
            this.quadraService.salvarQuadra(quadra);
            return "sucess";
        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }

    }
}
