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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
        dispatcher.forward(request, response);
    }
}