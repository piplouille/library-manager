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

public class LivreDeleteServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire suppression livre type <select> contenant emprunts en cours,
            si param id fournis alors livre correspndant présélectionné dans <select>

    doPost() : traiter formulaire suppression livre, si pb : ServletException

    Une fois livre ajouté : redirection vers page de détails du livre ie récupérer son identifiant
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
        dispatcher.forward(request, response);
    }
}