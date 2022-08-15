package com.ufsm.csi.backend.dto;

import com.ufsm.csi.backend.model.Funcionario;
import com.ufsm.csi.backend.model.Quadra;

public class UsuarioFuncionarioDto {

    private Integer id;
    private String nome;
    private String email;
    private String fone;
    private String tipo;
    private String cpf;
    private String ativo;
    private Integer idQuadra;
    private Integer idFuncionario;
    private String token;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Integer getIdQuadra() {
        return idQuadra;
    }

    public void setIdQuadra(Integer idQuadra) {
        this.idQuadra = idQuadra;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UsuarioFuncionarioDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", fone='" + fone + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ativo='" + ativo + '\'' +
                ", idQuadra=" + idQuadra +
                ", idFuncionario=" + idFuncionario +
                ", token='" + token + '\'' +
                '}';
    }
}
