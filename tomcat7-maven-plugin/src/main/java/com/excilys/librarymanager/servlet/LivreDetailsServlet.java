package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.service.LivreServiceImpl;
import com.excilys.librarymanager.service.EmpruntServiceImpl;
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

public class LivreDetailsServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher infos d'un livre dans formulaire avec les champs pré remplis
            On peut mettre à jour les infos sur livre grace a ca
            Sous formulaire, info relatives a l'emprunt actuel du livre

    doPost() : traiter formulaire suppression livre, si pb : ServletException

    Une fois livre modifié : redirection vers page de détails du livre (celle où il est déjà)
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();
        EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();

        //Recuperation de l'id du livre traite
        int id = Integer.parseInt(request.getParameter("id"));

        //Recuperation des infos du livre et de ses emprunts en cours
        Livre livre = null ;
        List<Emprunt> liste = null;
        try{
            livre = livre_service.getById(id);
            liste = emprunt_service.getListCurrentByLivre(id);
        }catch (ServiceException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }  

        //Renvoi des informations necessaires a la page
        request.setAttribute("livre", livre);
        request.setAttribute("liste_emprunts", liste);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
        dispatcher.forward(request, response);
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();

        //Recuperation de l'id du livre traite
        int id = Integer.parseInt(request.getParameter("id"));

        //Recuperation des parametres de maj du livre
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String isbn = request.getParameter("isbn");

        Livre livre = new Livre(id, titre,auteur,isbn);

        try{
            livre_service.update(livre);
        }catch (ServiceException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } 

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
        dispatcher.forward(request, response);
        
    }
}