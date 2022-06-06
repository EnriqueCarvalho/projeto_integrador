package com.ufsm.csi.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quadra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private  String cnpj;
    private String cep;
    private String ruaEnd;
    private String bairroEnd;
    private String nroEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRuaEnd() {
        return ruaEnd;
    }

    public void setRuaEnd(String ruaEnd) {
        this.ruaEnd = ruaEnd;
    }

    public String getBairroEnd() {
        return bairroEnd;
    }

    public void setBairroEnd(String bairroEnd) {
        this.bairroEnd = bairroEnd;
    }

    public String getNroEnd() {
        return nroEnd;
    }

    public void setNroEnd(String nroEnd) {
        this.nroEnd = nroEnd;
    }
}
