/**
 * Klasse Karte.
 * @author Christian
 *
 */
public class Card {
    
    // Farbe der Karte: KREUZ(c), PIK(s), HERZ(h), KARO(d)
    // Fuer Berechnung nur objektiv
    String farbe;
    
    // Wert der Karte
    // 2,3,4,5,6,7,8,9,10 wie gehabt. Bube = 11, Dame = 12, Koenig = 13, Ass =14
    // Wert = 0 => Karte noch nicht belegt.
    // Bewusst diese int-Zuordnung um Vergleiche besser zu realisieren.
    int wert;
    
    /**
     * Konstruktor.
     * Erzeugt eine neue Spielkarte.
     * @param farbe Farbe der Karte
     * @param wert Wert der Karte
     */
    public Card(String farbe, int wert) {
        this.farbe = farbe;
        this.wert = wert;
    }
    
    /**
     * toString Methode.
     * Ermoeglicht eine anschauliche Darstellung der Spielkarte.
     * @return String
     */
    public String toString() {
        if (wert > 9)
            return farbe+""+wert;
        else 
            return farbe+"0"+wert;
    }

    /**
     * Methode prueft, ob die Karte korrekt ist.
     * @return true wenn ja
     */
    public boolean isValidCard() {
        if (wert < 2) {
            return false;
        }
        else {
            return true;
        }
    }
    
    
}
