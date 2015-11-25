/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.exemplo.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Login {

    private String nome, senha;
    private boolean logado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    public String doLogin() {
        if ("admin".equals(nome) && "admin".equals(senha)) {
            this.setLogado(true);
            return "/home.xhtml?faces-redirect-true";
        }
        else {
            return "/index.xhtml";
        }
    }
    
    public String doLogout() {        
        this.setLogado(false);
        return "/index.xhtml";
    }
}
