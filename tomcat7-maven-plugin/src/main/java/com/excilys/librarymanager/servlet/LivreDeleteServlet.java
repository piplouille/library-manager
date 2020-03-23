package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.service.LivreServiceImpl;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.util.*;
// import java.text.*;
// import java.io.*;

import java.io.IOException;

public class LivreDeleteServlet extends HttpServlet {
    /*
    Deux methodes :
    doGet() : afficher formulaire suppression livre type <select> contenant emprunts en cours,
            si param id fournis alors livre correspndant preselectionne dans <select>

    doPost() : traiter formulaire suppression livre, si pb : ServletException
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();
        
        //Recuperation de l'id du livre traite
        int id = Integer.parseInt(request.getParameter("id"));

        //Recuperation des informations du livre
        Livre livre = null;
        try{
            livre = livre_service.getById(id);
        }catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
        
        //Envoi du livre
        request.setAttribute("livre", livre);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();
        
        //Recuperation de l'id du livre traite
        int id = Integer.parseInt(request.getParameter("id"));

        //Suppression du livre
        try{
            livre_service.delete(id);
        }catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        response.sendRedirect("/tomcat7-maven-plugin/livre_list");
        // RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
        // dispatcher.forward(request, response);
    }
}