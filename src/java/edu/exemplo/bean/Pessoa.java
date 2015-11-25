/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.exemplo.bean;

import edu.dao.PessoaDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author julio
 */
@ManagedBean
@RequestScoped
public class Pessoa {
    
    private int id;
    private String nome, localDesaparecimento, telefoneContato;
    private boolean encontrada;
    
    @ManagedProperty("#{param.pessoaId}")
    private int pessoaId;
    
    public int getPessoaId() {
        return this.pessoaId;        
    }
    
    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
        
        if (this.pessoaId > 0)
        {
            PessoaDao pessoaDao = new PessoaDao();
            Pessoa pessoa = pessoaDao.getPessoa(this.pessoaId);            
            this.setId(pessoa.id);
            this.setNome(pessoa.nome);
            this.setLocalDesaparecimento(pessoa.localDesaparecimento);
            this.setTelefoneContato(pessoa.telefoneContato);
            this.setEncontrada(pessoa.encontrada);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalDesaparecimento() {
        return localDesaparecimento;
    }

    public void setLocalDesaparecimento(String localDesaparecimento) {
        this.localDesaparecimento = localDesaparecimento;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }
    
    public String salvarPessoa() {
        PessoaDao pessoaDao = new PessoaDao();
        
        if (this.id > 0)
        {
            pessoaDao.updatePessoa(this);
        }
        else
        {
            this.encontrada = false;
            pessoaDao.insertPessoa(this);
        }
        
        return "pessoas";
    }    
}
