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

public class LivreAddServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire ajout livre, 2 champs <select>

    doPost() : traiter formulaire creation emprunt, si pb : ServletException

    Une fois livre ajouté : redirection vers page de détails du livre ie récupérer son identifiant
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
        dispatcher.forward(request, response);
        }
        catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }
}