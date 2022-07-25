package com.ufsm.csi.backend.model;

import javax.persistence.*;

@Entity
public class TabAuxiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String valorAlfa;
    private Integer valorNum;
    private Integer itemTab;
    private Integer codTab;

    @OneToOne
    @JoinColumn(name="idQuadra")
    private Quadra quadra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValorAlfa() {
        return valorAlfa;
    }

    public void setValorAlfa(String valorAlfa) {
        this.valorAlfa = valorAlfa;
    }

    public Integer getValorNum() {
        return valorNum;
    }

    public void setValorNum(Integer valorNum) {
        this.valorNum = valorNum;
    }

    public Integer getItemTab() {
        return itemTab;
    }

    public void setItemTab(Integer itemTab) {
        this.itemTab = itemTab;
    }

    public Integer getCodTab() {
        return codTab;
    }

    public void setCodTab(Integer codTab) {
        this.codTab = codTab;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }
}
