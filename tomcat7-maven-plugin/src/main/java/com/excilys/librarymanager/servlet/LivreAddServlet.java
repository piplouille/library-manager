package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.exception.ServletException;

import com.excilys.librarymanager.service.LivreServiceImpl;

import com.excilys.librarymanager.model.Livre;

import javax.servlet.RequestDispatcher;
// import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.util.*;
// import java.text.*;
// import java.io.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class LivreAddServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire ajout livre, 2 champs <select>

    doPost() : traiter formulaire creation emprunt, si pb : ServletException

    Une fois livre ajouté : redirection vers page de détails du livre ie récupérer son identifiant
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e) {
            throw new ServletException("LivreAddServlet : doGet()");
        }
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();

        //Recuperation des parametres du nouveau livre
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String isbn = request.getParameter("isbn");

        //Creation du livre
        int id =-1;
        try{
            id = livre_service.create(titre,auteur,isbn);
        } catch (ServiceException e)
        {
            throw new ServletException("LivreAddServlet doPost()");
        }

        //Redirection
        response.sendRedirect("/tomcat7-maven-plugin/livre_details?id="+id);

        // RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
        // dispatcher.forward(request, response);
    }
}

/*
Tests effectues :

int id_livre = livre_service.create("Le Petit Prince", "Antoine de St Exupery", "9791187192596");
System.out.println(id_livre);
System.out.println("Test Livre getList()");
List<Livre> liste = livre_service.getList();
for (int i = 0; i <liste.size() ; i++) {
    System.out.println(liste.get(i).getTitre());
}
Livre livre = livre_service.getById(id_livre);
livre.setTitre("Vol de nuit");
livre_service.update(livre);
List<Livre> liste = livre_service.getList();
System.out.println(liste.size());
livre_service.delete(livre.getId());
liste = livre_service.getList();
System.out.println(liste.size());
System.out.println(livre_service.count());
List<Livre> liste = livre_service.getListDispo();
System.out.println(liste.size());
*/