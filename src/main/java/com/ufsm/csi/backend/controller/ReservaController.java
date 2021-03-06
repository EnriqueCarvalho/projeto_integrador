package com.ufsm.csi.backend.controller;

import com.ufsm.csi.backend.model.Espaco;
import com.ufsm.csi.backend.model.Reserva;
import com.ufsm.csi.backend.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaService;


    @CrossOrigin
    @GetMapping("/visualizar-reserva")
    public Optional<Reserva> getReservaById(@ModelAttribute("idReserva") String idReserva){

        try{
            Integer id = Integer.parseInt(idReserva.replaceAll("id:",""));
            Optional<Reserva> reserva = this.reservaService.getReservaById(id);
            System.out.println("Buscando reserva: "+reserva.toString());
            return reserva;
        }catch (Exception e ){
            System.out.println("Erro: "+ e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não foi possível buscar as reservas"
            );
        }
    }


    @CrossOrigin
    @GetMapping("/visualizar-reservas")
    public List<Reserva> getReservasByUsuario(@RequestHeader("X-usuario") String idUsuario){

        try{
            Integer id = Integer.parseInt(idUsuario.replaceAll("id:",""));
            List<Reserva> reservas = this.reservaService.getReservasByUsuario(id);
            System.out.println("Buscando reservas para o usuário: "+id);
            return reservas;
        }catch (Exception e ){
            System.out.println("Erro: "+ e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não foi possível buscar as reservas"
            );
        }
    }

    @CrossOrigin
    @GetMapping("/visualizar-reservas-quadra")
    public List<Reserva> getReservasByQuadra(@ModelAttribute("idQuadra") String idQuadra){
        try{
            List<Reserva> reservas = this.reservaService.getReservasByQuadra(Integer.parseInt(idQuadra));
            System.out.println("Buscando reservas para a quadra: "+idQuadra);
            return reservas;
        }catch (Exception e ){
            System.out.println("Erro: "+ e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não foi possível buscar as reservas"
            );
        }
    }

    @CrossOrigin
    @PostMapping("/cadastrar-reserva")
    public Reserva cadastrarReserva(@RequestBody Reserva reserva){
        try{
            Reserva reservas = this.reservaService.cadastrarReserva(reserva);
            System.out.println("Cadastrando reserva "+ reserva.toString());
            return reservas;
        }catch (Exception e ){
            System.out.println("Erro: "+ e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não foi possível cadastrar a reserva"
            );
        }
    }

    @CrossOrigin
    @PutMapping("/excluir-reserva")
    public Reserva excluirReserva(@RequestBody Reserva reserva){
        try{
            Reserva reservas = this.reservaService.excluirReserva(reserva);
            System.out.println("Excluindo reserva "+ reserva.toString());
            return reservas;
        }catch (Exception e ){
            System.out.println("Erro: "+ e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Não foi possível excluir a reserva"
            );
        }
    }



}
