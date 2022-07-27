package com.ufsm.csi.backend.model;

import javax.persistence.*;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String data;
    private String hora;
    private String dataCancel;
    private String motivoCancel;

    @OneToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name="idEspaco")
    private Espaco espaco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDataCancel() {
        return dataCancel;
    }

    public void setDataCancel(String dataCancel) {
        this.dataCancel = dataCancel;
    }

    public String getMotivoCancel() {
        return motivoCancel;
    }

    public void setMotivoCancel(String motivoCancel) {
        this.motivoCancel = motivoCancel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", dataCancel='" + dataCancel + '\'' +
                ", motivoCancel='" + motivoCancel + '\'' +
                ", usuario=" + usuario +
                ", espaco=" + espaco +
                '}';
    }
}
