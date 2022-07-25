package com.ufsm.csi.backend.controller;

import com.ufsm.csi.backend.model.Quadra;
import com.ufsm.csi.backend.model.TabAuxiliar;
import com.ufsm.csi.backend.service.QuadraService;
import com.ufsm.csi.backend.service.TabAuxiliarService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TabAuxiliarController {

    @Autowired
    private TabAuxiliarService tabAuxiliarService;

    @CrossOrigin
    @GetMapping("/visualizar-tabaux")
    public List<TabAuxiliar> visualizarQuadra(@ModelAttribute("idQuadra") String idQuadra,
                                              @ModelAttribute("codTab") String codTab,
                                              @ModelAttribute("itemTab") String itemTab){
        try{
            System.out.println("Buscando Tabela Auxiliar com código: "+codTab+",item: "+itemTab+", para a quadra: "+idQuadra);
            return this.tabAuxiliarService.getTabAuxiliar(Integer.parseInt(idQuadra),Integer.parseInt(codTab));

        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @CrossOrigin
    @PostMapping("/cadastrar-tabaux")
    public TabAuxiliar cadastrarTabAux(@RequestBody TabAuxiliar tabAuxiliar){
        try{
            System.out.println("Cadastrando Tabela Auxiliar com código: "+tabAuxiliar.getCodTab()+",item: "+tabAuxiliar.getItemTab()+", para a quadra: "+tabAuxiliar.getQuadra().getId());
            return this.tabAuxiliarService.cadastrarTabAux(tabAuxiliar);

        }catch (Exception e ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @CrossOrigin
    @DeleteMapping("/deletar-tabaux")
    public String deletarItemTabAux(@ModelAttribute("idTabAux") String idTabAux){
        try{
            System.out.println("Deletando Tabela Auxiliar com id: "+idTabAux);
            TabAuxiliar t = new TabAuxiliar();
            t.setId(Integer.parseInt(idTabAux));

            this.tabAuxiliarService.deletarItemTabAux(t);
            return "sucess";
        }catch (Exception e ){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }
}
