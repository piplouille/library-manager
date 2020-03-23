package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.service.LivreServiceImpl;
import com.excilys.librarymanager.model.Livre;
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

public class LivreListServlet extends HttpServlet {
    /*
    Une methode :
    doGet() : afficher livres
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LivreServiceImpl livre_service = LivreServiceImpl.getInstance();

        //Creation de la liste des livres
        List<Livre> liste = null;
        try{
            liste = livre_service.getList();
        } catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }

        //Envoi de la liste des livres
        request.setAttribute("liste_livres", liste);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_list.jsp");
        dispatcher.forward(request, response);
    }
}