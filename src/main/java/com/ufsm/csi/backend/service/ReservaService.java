package com.ufsm.csi.backend.service;

import com.ufsm.csi.backend.model.Reserva;
import com.ufsm.csi.backend.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private final ReservaRepository reservaRepository;
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }


    public Optional<Reserva> getReservaById(Integer idQuadra){
        return this.reservaRepository.findById(idQuadra);
    }
    public List<Reserva> getReservasByUsuario(Integer idUsuario){
        return this.reservaRepository.getReservasByUsuario(idUsuario);
    }

    public List<Reserva> getReservasByQuadra(Integer idQuadra){
        return this.reservaRepository.getReservasByQuadra(idQuadra);
    }

    public Reserva cadastrarReserva(Reserva reserva){
        return this.reservaRepository.save(reserva);
    }

    public Reserva excluirReserva(Reserva reserva){
        return this.reservaRepository.save(reserva);
    }

}
