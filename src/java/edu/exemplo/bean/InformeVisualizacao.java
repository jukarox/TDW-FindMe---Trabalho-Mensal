/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.exemplo.bean;

import java.util.Date;

/**
 *
 * @author julio
 */
public class InformeVisualizacao {
    
    private int id;
    private Date dataVisualizacao, dataInforme;
    private String ultimoEndereco, descricao;
    private int pessoaId;
    private int informadoPor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(Date dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

    public Date getDataInforme() {
        return dataInforme;
    }

    public void setDataInforme(Date dataInforme) {
        this.dataInforme = dataInforme;
    }

    public String getUltimoEndereco() {
        return ultimoEndereco;
    }

    public void setUltimoEndereco(String ultimoEndereco) {
        this.ultimoEndereco = ultimoEndereco;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getInformadoPor() {
        return informadoPor;
    }

    public void setInformadoPor(int informadoPor) {
        this.informadoPor = informadoPor;
    }
   
}
