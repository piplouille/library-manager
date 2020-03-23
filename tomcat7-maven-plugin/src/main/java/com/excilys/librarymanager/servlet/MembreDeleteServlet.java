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

import com.excilys.librarymanager.service.MembreServiceImpl;

import com.excilys.librarymanager.model.Membre;

public class MembreDeleteServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire suppression membre type <select> contenant membres actuels,
            si param id fournis alors membre correspndant présélectionné dans <select>

    doPost() : traiter formulaire suppression membre, si pb : ServletException
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // On va récupérer l'id de l'url
        int id = Integer.parseInt(request.getParameter("id"));

        // Et récupérer le membre correspondant s'il existe
        Membre membre = null;
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();

        try {
            membre = membre_service.getById(id);
        }
        catch (Exception e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }

        request.setAttribute("membre", membre);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();

        //Suppression du membre
        try{
            membre_service.delete(id);
        }catch (Exception e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        response.sendRedirect("/tomcat7-maven-plugin/membre_list");
    }

    
}