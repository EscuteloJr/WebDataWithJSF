/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projecto.api;

import com.projecto.api.model.Doc;
import com.projecto.apiRequestTools.TeslaNews;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author escutelojr
 */
@ViewScoped
@Named("repository")
public class Repository implements Serializable {

    private List<Doc> listaDeNews;

    public Repository() {
        listaDeNews = TeslaNews.retornarListaDeNews();
        System.out.println("SIZE LISTA > " + listaDeNews.get(0).getArticles().size());
    }

    @PostConstruct
    public void init() {
        listaDeNews = TeslaNews.retornarListaDeNews();
        System.out.println("SIZE LISTA > " + listaDeNews.size());
    }

    public Doc  getListaDeNews() {
        return listaDeNews.get(0);
    }
    
  

}
