package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.service.LivreServiceImpl;
import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.service.EmpruntServiceImpl;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
// import java.util.*;
// import java.text.*;
// import java.io.*;

import java.io.IOException;

import java.time.LocalDate;

public class EmpruntAddServlet extends HttpServlet {
    /*
    Deux mehodes :
    doGet() : afficher formulaire ajout emprunt, 2 champs <select> qui ne contiennent que livres disponibles

    doPost() : traiter formulaire creation emprunt, si pb : ServletException
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();

        //Creation des listes
        List<Livre> liste_livres = null;
        List<Membre> liste_membres = null;

        //Recuperation des listes
        try{
            liste_livres = livre_service.getListDispo();
            liste_membres = membre_service.getListMembreEmpruntPossible();
        }catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }

        //Envoi des listes
        request.setAttribute("liste_livres", liste_livres);
        request.setAttribute("liste_membres", liste_membres);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();
        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();

        //Recuperation des id du livre et du membre
        int idLivre = Integer.parseInt(request.getParameter("idLivre"));
        int idMembre = Integer.parseInt(request.getParameter("idMembre"));

        //Recuperation du livre et du membre, creation de l'emprunt
        Livre livre = null;
        Membre membre = null;
        Emprunt emprunt = null ;
        try{
            livre = livre_service.getById(idLivre);
            membre = membre_service.getById(idMembre);
            emprunt_service.create(idMembre, idLivre, LocalDate.now());
        }catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        response.sendRedirect("/tomcat7-maven-plugin/emprunt_list");
    }
}