/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.exemplo.bean;

import edu.dao.PessoaDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author julio
 */
@ManagedBean
@RequestScoped
public class Pessoas {

    private List<Pessoa> pessoas;
    private int idPessoa;
    
    /**
     * Creates a new instance of Alunos
     */
    public Pessoas() {
    }

    public List<Pessoa> getPessoas() {
        PessoaDao dao = new PessoaDao();
        return dao.getPessoasNaoEncontradas();
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    public String editarPessoa(Pessoa pessoa)
    {
        this.idPessoa = pessoa.getId();
        return "pessoa?faces-redirect=true&includeViewParams=true";        
    }   
    
    public void removerPessoa(Pessoa pessoa)
    {
        PessoaDao pessoaDao = new PessoaDao();
        
        pessoaDao.deletePessoa(pessoa.getId());
    }
}
