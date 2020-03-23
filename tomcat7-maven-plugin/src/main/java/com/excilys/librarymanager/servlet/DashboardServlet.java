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

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.service.EmpruntServiceImpl;
import com.excilys.librarymanager.service.LivreServiceImpl;

// import com.excilys.librarymanager.dao.MembreDaoImpl;

import com.excilys.librarymanager.model.Emprunt;
// import com.excilys.librarymanager.model.Membre;

import java.util.ArrayList;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MembreServiceImpl membre_service = MembreServiceImpl.getInstance();
            int no_membres = membre_service.count();
            request.setAttribute("no_membres", no_membres);

            EmpruntServiceImpl emprunt_service = EmpruntServiceImpl.getInstance();
            int no_emprunts = emprunt_service.count();
            request.setAttribute("no_emprunts", no_emprunts);

            List<Emprunt> liste = emprunt_service.getListCurrent();
            request.setAttribute("liste_emprunts", liste);

            LivreServiceImpl livre_service = LivreServiceImpl.getInstance();
            int no_livres = livre_service.count();
            request.setAttribute("no_livres", no_livres);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            dispatcher.forward(request, response);
        }
        catch (ServiceException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }
}

/*
int id_membre = membre_service.create("Debesse", "Laetitia", "1 rue", "yo@ensta.fr", "06");
System.out.println(id_membre);
List<Membre> liste = membre_service.getList();
for (int i = 0; i <liste.size() ; i++) {
    System.out.println(liste.get(i).getNom());
}
Membre membre = membre_service.getById(id_membre);
membre.setNom("POUTOU");
membre_service.update(membre);
liste = membre_service.getList();
System.out.println(liste.size());
membre_service.delete(membre.getKey());
liste = membre_service.getList();
System.out.println(liste.size());
System.out.println(membre_service.count());
*/