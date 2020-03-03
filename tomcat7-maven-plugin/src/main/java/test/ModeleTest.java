package test;

import model.Abonnement;
import model.Emprunt;
import model.Livre;
import model.Membre;

public class ModeleTest {
    public static void main() {
        Membre madeleine = new Membre(0, "Becker", "Madeleine", "1024 Bvd des Maréchaux", "madeleine@ensta.fr", "0600", Abonnement.VIP);
    }
}
