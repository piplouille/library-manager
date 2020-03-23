package com.excilys.librarymanager.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.util.*;
// import java.text.*;
// import java.io.*;

import java.io.IOException;

import java.util.List;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Abonnement;
import com.excilys.librarymanager.model.Emprunt;

import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.service.EmpruntServiceImpl;

public class MembreDetailsServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher infos d'un membre dans formulaire vec champs pré remplis
            On peut mettre à jour les info sur membre grace a ca
            Sous formulaire, info relatives aux emprunts actuels du membre

    doPost() : traiter formulaire update membre, si pb : ServletException

    Une fois membre modifié : redirection vers page de détails du membre (celle où il est déjà)
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // On va récupérer l'id de l'url
        int id = Integer.parseInt(request.getParameter("id"));

        // Et récupérer le membre correspondant s'il existe
        Membre membre = null;
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();
        EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();
        List<Emprunt> liste = null;
        try {
            membre = membre_service.getById(id);

            liste = emprunt_service.getListCurrentByMembre(id);
        }
        catch (Exception e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
        // System.out.println(liste.size());
        request.setAttribute("membre", membre);
        request.setAttribute("liste_emprunts", liste);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String abonnement = request.getParameter("abonnement");
        Abonnement abo = Abonnement.BASIC;
        switch (abonnement) {
            case "BASIC": break;
            case "PREMIUM": abo = Abonnement.PREMIUM; break;
            case "VIP": abo = Abonnement.VIP; break;
        }

        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();
        Membre membre = null;
        //Suppression du membre
        try{
            membre = membre_service.getById(id);

            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setAdresse(adresse);
            membre.setEmail(email);
            membre.setTelephone(telephone);
            membre.setAbonnement(abo);

            membre_service.update(membre);
        }catch (Exception e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        response.sendRedirect("/tomcat7-maven-plugin/membre_list");
    }
}