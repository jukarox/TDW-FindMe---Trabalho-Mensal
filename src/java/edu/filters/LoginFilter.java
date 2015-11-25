/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.filters;

import edu.exemplo.bean.Login;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julio
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/restricted/*","/faces/restricted/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { 
        // captura ManagedBean de login e verifica se logado
        Login login = (Login) ((HttpServletRequest) request) .getSession().getAttribute("login"); 
        if (login == null || login.isLogado() == false) {
            String contextPath = ((HttpServletRequest) request) .getContextPath(); 
            ((HttpServletResponse) response).sendRedirect (contextPath + "/faces/index.xhtml"); 
        }
        else { 
            chain.doFilter(request, response); 
        }
    }


    @Override
    public void destroy() {
    }
}
