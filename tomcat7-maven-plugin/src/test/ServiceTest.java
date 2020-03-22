package com.excilys.librarymanager.test;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Emprunt;
import com.excilys.librarymanager.model.Abonnement;

import com.excilys.librarymanager.service.LivreServiceImpl;
import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.service.EmpruntServiceImpl;

import com.excilys.librarymanager.exception.ServiceException;
import java.time.LocalDate;

public class ServiceTest {
    public static void main() {
        Membre laetitia = new Membre(1,"Debesse","Laetitia","1024 Bvd des Marechaux","debesse@ensta.fr","5462",Abonnement.PREMIUM);
        Membre madeleine = new Membre(0, "Becker", "Madeleine", "1024 Bvd des Marechaux", "madeleine@ensta.fr", "0600", Abonnement.VIP);
        Livre roman = new Livre(0, "Le Petit Prince", "Antoine de St Exupery", "9791187192596");
        Emprunt emprunt = new Emprunt(0, madeleine, roman, LocalDate.now(), LocalDate.of(2020, 4, 3));
        MembreServiceImpl mSImpl = MembreServiceImpl.getInstance();
        LivreServiceImpl lSImpl = LivreServiceImpl.getInstance();
        EmpruntServiceImpl eSImpl = EmpruntServiceImpl.getInstance();
        try {
            mSImpl.create(madeleine.getNom(), madeleine.getPrenom(), madeleine.getAdresse(), madeleine.getEmail(),
                    madeleine.getTelephone());
            lSImpl.create(roman.getTitre(), roman.getAuteur(), roman.getIsbn());
            eSImpl.create(emprunt.getMembre().getKey(), emprunt.getLivre().getId(), emprunt.getDateEmprunt());
            eSImpl.getListCurrent();
            eSImpl.returnBook(emprunt.getId());
            lSImpl.getList();
            mSImpl.getList();
        } catch (ServiceException error) {
        }
    }
}