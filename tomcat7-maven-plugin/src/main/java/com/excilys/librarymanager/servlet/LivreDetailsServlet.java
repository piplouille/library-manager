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

public class LivreDetailsServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher infos d'un livr dans formulaire vec champs pré remplis
            On peut mettre à jour les info sur livre grace a ca
            Sous formulaire, info relatives a l'emprunt actuel du livre

    doPost() : traiter formulaire suppression livre, si pb : ServletException

    Une fois livre modifié : redirection vers page de détails du livre (celle où il est déjà)
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
        dispatcher.forward(request, response);
    }
}