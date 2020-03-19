package com.excilys.librarymanager.test;

import java.time.LocalDate;

import com.excilys.librarymanager.dao.EmpruntDaoImpl;
import com.excilys.librarymanager.dao.LivreDaoImpl;
import com.excilys.librarymanager.dao.MembreDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.*;

public class ModeleTest {
    public static void main() {
        Membre madeleine = new Membre(0, "Becker", "Madeleine", "1024 Bvd des Marechaux", "madeleine@ensta.fr", "0600",
                Abonnement.VIP);
        Livre roman = new Livre(0, "Le Petit Prince", "Antoine de St Exupery", "9791187192596");
        Emprunt emprunt = new Emprunt(0, madeleine, roman, LocalDate.now(), LocalDate.of(2020, 4, 3));

        MembreDaoImpl mDaoImpl = MembreDaoImpl.getInstance();
        LivreDaoImpl lDaoImpl = LivreDaoImpl.getInstance();
        EmpruntDaoImpl eDaoImpl = EmpruntDaoImpl.getInstance();
        try {
            mDaoImpl.create(madeleine.getNom(), madeleine.getPrenom(), madeleine.getAdresse(), madeleine.getEmail(),
                    madeleine.getTelephone());
            lDaoImpl.create(roman.getTitre(), roman.getAuteur(), roman.getIsbn());
            eDaoImpl.create(emprunt.getMembre().getKey(), emprunt.getLivre().getId(), emprunt.getDateEmprunt());
            eDaoImpl.getList();
            lDaoImpl.getList();
            mDaoImpl.getList();
        } catch (DaoException error) {
        }

    }
}
