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

public class EmpruntListServlet extends HttpServlet {
    /*
    Une methode :
    doGet() : afficher liste des emprunts en cours mais si show=all, totalite des emprunts a afficher
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();

        String show = null;
        if (request.getParameterMap().containsKey("show")) {
            if (request.getParameter("show").equals("all")) {
                show = "all";
            }
        }

        //Creation de la liste des emprunts
        List<Emprunt> liste = null;
        try{
            //Si show=all, on affiche tous les emprunts
            if(show != null)
            {
                liste = emprunt_service.getList();
            }
            //Sinon on n'affiche que les emprunts en cours
            else {
                liste = emprunt_service.getListCurrent();
            }
            
        }catch (ServiceException e)
        {
            System.out.println("Recherche liste emprunt a echoue");
            System.out.println("Exception Message " + e.getLocalizedMessage());
            throw new ServletException();
        }

        //Envoi de la liste
        request.setAttribute("liste", liste);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
        dispatcher.forward(request, response);
    }
}