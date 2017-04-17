///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.com.massasmez.filtro;
//
//
//import br.com.massasmez.controle.LoginControle;
//import java.io.IOException;
//import javax.inject.Inject;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Marcelo
// */
//@WebFilter(filterName = "LoginFilter", urlPatterns = {"/Home.xhtml",
//    "/ajustaEstoque/*",
//    "/categoria/*",
//    "/cidade/*",
//    "/cliente/*",
//    "/compra/*",
//    "/contasReceber/*",
//    "/estado/*",
//    "/funcionario/*",
//    "/ocupacao/*",
//    "/pedido/*",
//    "/produto/*",
//    "/produtosCompra/*",
//    "/usuario/*"})
//public class LoginFilter implements Filter {
//
//    @Inject
//    private LoginControle loginControle;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (loginControle.getUsuario() != null) {
//            chain.doFilter(request, response);
//        } else {
//            HttpServletResponse resp = (HttpServletResponse) response;
//            resp.sendRedirect("/PedidoMassas/Login.xhtml");
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//}
