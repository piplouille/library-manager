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

public class MembreDeleteServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire suppression membre type <select> contenant membres actuels,
            si param id fournis alors membre correspndant présélectionné dans <select>

    doPost() : traiter formulaire suppression membre, si pb : ServletException
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // On va récupérer l'id de l'url
        


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
        dispatcher.forward(request, response);
    }

    
}