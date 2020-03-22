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
}