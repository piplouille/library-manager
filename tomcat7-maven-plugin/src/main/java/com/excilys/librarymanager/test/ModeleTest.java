package com.excilys.librarymanager.test;

import java.time.LocalDate;

import com.excilys.librarymanager.model.*;

public class ModeleTest {
    public static void main() {
        Membre madeleine = new Membre(0, "Becker", "Madeleine", "1024 Bvd des Marechaux", "madeleine@ensta.fr", "0600", Abonnement.VIP);
        Livre roman = new Livre(0, "Le Petit Prince", "Antoine de St Exupery", "9791187192596");
        Emprunt emprunt = new Emprunt(0, madeleine, roman, LocalDate.now(), LocalDate.of(2020, 4, 3));
    }
}
