package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.service.EmpruntServiceImpl;
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

public class EmpruntReturnServlet extends HttpServlet {
    /*
    Deux methodes :
    doGet() : afficher formulaire retour d'emprunt base sur champ <select> contenant emprunts en cours

    doPost() : traiter formulaire de retour d'emprunt, raise ServletException
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();

        List<Emprunt> liste = null;

        int id = -1;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id_emprunt", id);
        }
        
        //Recuperation de la liste des emprunts en cours
        try{
            liste = emprunt_service.getListCurrent();
        }catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        //Envoi de la liste
        request.setAttribute("liste_emprunts", liste);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();

        //Recuperation de l'identifiant de l'emprunt a retourner
        int id_emprunt = Integer.parseInt(request.getParameter("id_emprunt"));

        try{
            emprunt_service.returnBook(id_emprunt);
        }catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        response.sendRedirect("/tomcat7-maven-plugin/emprunt_list");
    }
}