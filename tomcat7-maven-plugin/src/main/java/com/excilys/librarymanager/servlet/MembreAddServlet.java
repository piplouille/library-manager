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

import com.excilys.librarymanager.service.MembreServiceImpl;

import com.excilys.librarymanager.exception.ServiceException;

public class MembreAddServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire ajout membre, 2 champs <select>

    doPost() : traiter formulaire creation membre, si pb : ServletException

    Une fois livre ajouté : redirection vers page de détails du membre ie récupérer son identifiant
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();

        //Recuperation des parametres du nouveau membre
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");

        //Creation du livre
        int id =-1;
        try{
            id = membre_service.create(nom, prenom, adresse, email, telephone);
        } catch (ServiceException e)
        {
            throw new ServletException("MembreAddServlet doPost()");
        }

        //Redirection
        response.sendRedirect("/tomcat7-maven-plugin/membre_details?id="+id);
    }


}