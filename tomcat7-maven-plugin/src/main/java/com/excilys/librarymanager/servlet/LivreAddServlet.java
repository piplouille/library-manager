package com.excilys.librarymanager.servlet;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.service.LivreServiceImpl;

import com.excilys.librarymanager.model.Livre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.util.*;
// import java.text.*;
// import java.io.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class LivreAddServlet extends HttpServlet {
    /*
    Deux méthodes :
    doGet() : afficher formulaire ajout livre, 2 champs <select>

    doPost() : traiter formulaire creation emprunt, si pb : ServletException

    Une fois livre ajouté : redirection vers page de détails du livre ie récupérer son identifiant
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            LivreServiceImpl livre_service = LivreServiceImpl.getInstance();

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
            dispatcher.forward(request, response);
        }
        catch (ServiceException e)
        {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }
}

/*
Tests effectues :
int id_livre = livre_service.create("Le Petit Prince", "Antoine de St Exupery", "9791187192596");
System.out.println(id_livre);

System.out.println("Test Livre getList()");
List<Livre> liste = livre_service.getList();
for (int i = 0; i <liste.size() ; i++) {
    System.out.println(liste.get(i).getTitre());
}
Livre livre = livre_service.getById(id_livre);
livre.setTitre("Vol de nuit");
livre_service.update(livre);
List<Livre> liste = livre_service.getList();
System.out.println(liste.size());
livre_service.delete(livre.getId());
liste = livre_service.getList();
System.out.println(liste.size());
System.out.println(livre_service.count());
List<Livre> liste = livre_service.getListDispo();
System.out.println(liste.size());
*/