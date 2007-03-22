import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;

/**
 * Klasse CardChecker.
 * Liest aus einem Screenshot die Karten aus.
 * @author Christian
 *
 */
public class CardChecker {

    // Liste mit den Karten auf dem Tisch
    private LinkedList<Card> tableCards = new LinkedList<Card>();
    // Liste mit eigenen Handkarten
    private LinkedList<Card> ownCards = new LinkedList<Card>();
    // Initialisiere Objekte fuer Screenshoterstellung
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Betrachte den gesamten Bildschirm
    Rectangle rectangle = new Rectangle(0, 0, screenSize.width,
            screenSize.height);
    // Initialisierung der Robot-Klasse
    Robot robot;
    // Image zum bearbeiten des Screenshots
    BufferedImage image;
    
    
    /**
     * Konstruktor.
     * Initialisiert die Robot Klasse und holt den Screenshot.
     */
    public CardChecker() {
        // Initialisiere Robot Objekt fuer Methodenbereitstellung 
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        // schiesse Screenshot
        image = robot.createScreenCapture(rectangle);
    }

    /**
     * Ermittelt Kartenwert.
     * @param image Screenshot
     * @param anpasser x-Anpassung
     * @param eigenerAnpasser y-Anpassung fuer eigene Karten
     * @return Wert der Karte
     */
    public static int ermittelWert(BufferedImage image, int anpasser,
            int eigenerAnpasser) {
        // Zaehlvariable fuer durchgelaufene Pixel, die ungleich weiss sind
        int count = 0;
        // Schleifen laufen Pixel in einem Rechteck ab
        for (int i = 274 + anpasser; i < 287 + anpasser; i++) {
            for (int j = 187 + eigenerAnpasser; j < 196 + eigenerAnpasser; j++) {
                // Wenn der Pixel nicht weiß ist: ...
                if (image.getRGB(i, j) != -1) {
                    // Pruefe auf eindeutige Pixelkoordinaten die nur
                    // bei einem Kartenwert farbig sind
                    if ((i == 275 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 0) {
                        return 14;
                    }
                    if ((i == 276 + anpasser) && (j == 190 + eigenerAnpasser)
                            && count == 0) {
                        return 2;
                    }

                    if ((i == 279 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 0) {
                        return 7;
                    }
                    if ((i == 276 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 0) {
                        return 11;
                    }
                    if ((i == 275 + anpasser) && (j == 187 + eigenerAnpasser)
                            && count == 0) {
                        return 12;
                    }
                    if ((i == 276 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 0) {
                        return 13;
                    }
                    // Pruefe weiter
                    if ((i == 277 + anpasser) && (j == 187 + eigenerAnpasser)
                            && count == 2) {
                        return 4;
                    }
                    if ((i == 277 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 2) {
                        return 9;
                    }
                    // und weiter
                    if ((i == 276 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 4) {
                        return 10;
                    }
                    // und weiter
                    if ((i == 278 + anpasser) && (j == 190 + eigenerAnpasser)
                            && count == 5) {
                        return 3;
                    }
                    if ((i == 278 + anpasser) && (j == 189 + eigenerAnpasser)
                            && count == 5) {
                        return 5;
                    }
                    // spaetestens beim 11. Durchgang sollte der Wert gefunden sein
                    if ((i == 278 + anpasser) && (j == 189 + eigenerAnpasser)
                            && count == 10) {
                        return 6;
                    }
                    if ((i == 278 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 10) {
                        return 8;
                    }
                    // zur Bestimmung des Kartenwerts erhoehe den Pixelzaehler.
                    if (image.getRGB(i, j) != -1) {
                        count++;
                    }
                }
            }
        }
        // Es wurden noch keine Karten ausgespielt
        return 0;
    }

    /**
     * Ermittelt die Farbe der Tischkarte.
     *
     */
    public void ermittleTischKarten() {
        try {
            // Initialisiere Tischkarten
            tableCards = new LinkedList<Card>();
            // Initialisiere X-Anpassung
            int anpasser = 0;
            // Initialisiere Y-Anpassung fuer eigene Karten
            int eigenerAnpasser = 0;
            // Schleifen pruefen ob Karten ausgespielt wurden. 
            // Wenn ja, ist der Pruefpixel nicht mit gruener Feldfarbe gefuellt
            for (int i = 1; i < 6; i++) {
                if (image.getRGB(274 + anpasser, 202) != -1) {
                    if (image.getRGB(274 + anpasser, 202) == -14109143
                            || image.getRGB(274 + anpasser, 202) == -14531295
                            || image.getRGB(274 + anpasser, 202) == -14308827
                            || image.getRGB(274 + anpasser, 202) == -14714079
                            || image.getRGB(274 + anpasser, 202) == -14372570) {
                        // Noch keine Karte ausgespielt. Not a card
                        tableCards.addLast(new Card("N", 0));
                    } else {
                        // Karo bestimmt, ermittel Wert.
                        tableCards.addLast(new Card("d", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }
                } else if (image.getRGB(275 + anpasser, 198) != -1) {
                    if (image.getRGB(275 + anpasser, 198) == -14240472
                            || image.getRGB(275 + anpasser, 198) == -14374106
                            || image.getRGB(275 + anpasser, 198) == -14374619
                            || image.getRGB(275 + anpasser, 198) == -14245082
                            || image.getRGB(275 + anpasser, 198) == -14306778) {
                        // Noch keine Karte ausgespielt. Not a card
                        tableCards.addLast(new Card("N", 0));
                    } else {
                        // Herz bestimmt, ermittel Wert.
                        tableCards.addLast(new Card("h", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }
                } else if (image.getRGB(275 + anpasser, 200) != -1) {
                    if (image.getRGB(275 + anpasser, 200) == -14174168
                            || image.getRGB(275 + anpasser, 200) == -14111449
                            || image.getRGB(275 + anpasser, 200) == -14375131
                            || image.getRGB(275 + anpasser, 200) == -14448347
                            || image.getRGB(275 + anpasser, 200) == -14307291) {
                        // Noch keine Karte ausgespielt. Not a card
                        tableCards.addLast(new Card("N", 0));
                    } else {
                        // Kreuz bestimmt, ermittel Wert.
                        tableCards.addLast(new Card("c", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }
                } else {
                    if (image.getRGB(274 + anpasser, 202) == -14239704
                            || image.getRGB(274 + anpasser, 202) == -14308314
                            || image.getRGB(274 + anpasser, 202) == -14440409
                            || image.getRGB(274 + anpasser, 202) == -14930405
                            || image.getRGB(274 + anpasser, 202) == -14372056) {
                        // Noch keine Karte ausgespielt. Not a card
                        tableCards.addLast(new Card("N", 0));
                    } else {
                        // Pik bestimmt, ermittel Wert.
                        tableCards.addLast(new Card("s", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }

                }
                // passe X-Richtung fuer naechste Karte an
                anpasser += 54;
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ermittelt die eigenen Karten.
     *
     */
    public void ermittleEigeneKarten() {
        try {
            // Initialisiere Tischkarten
            ownCards = new LinkedList<Card>();
            // Initialisiere x-Anpassung
            int anpasser = 440;
            // Initialisiere y-Anpassung
            int eigenerAnpasser = 59;
            // Teste ob Karten ausgespilt sind
            if (image.getRGB(748, 244) != -12894323) {
                for (int i = 6; i < 8; i++) {
                    // Und pruefe auf Kartenfarbe
                    if (image.getRGB(274 + anpasser, 202 + eigenerAnpasser) != -1) {
                        // Karo bestimmt, ermittel Wert.
                        ownCards.addLast(new Card("d", ermittelWert(image, anpasser,
                                eigenerAnpasser)));

                    } else if (image.getRGB(275 + anpasser,
                            198 + eigenerAnpasser) != -1) {
                        // Herz bestimmt, ermittel Wert.
                        ownCards.addLast(new Card("h", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                    } else if (image.getRGB(275 + anpasser,
                            200 + eigenerAnpasser) != -1) {
                        // Kreuz bestimmt, ermittel Wert.
                        ownCards.addLast(new Card("c", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                    } else {
                        // Pik bestimmt, ermittel Wert.
                        ownCards.addLast(new Card("s", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                    }
                    // Passe Koordinaten an
                    anpasser += 15;
                    eigenerAnpasser += 4;
                }
            } else {
//                System.out.println("Eigene Karten noch nicht erhalten!");
            }
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gibt alle offenen Karten (genannt: TableCards) als LinkedList zurueck.
     * 
     * @return TableCards
     */
    public LinkedList<Card> getTableCards() {
        ermittleTischKarten();
        return tableCards;
    }

    /**
     * Gibt die eigenen Karten als LinkedList zurueck.
     * 
     * @return eigene Karten
     */
    public LinkedList<Card> getOwnCards() {
        ermittleEigeneKarten();
        return ownCards;
    }

    /**
     * Liefert eine Uebersicht der ausgespielten und eigenen Karten.
     * 
     * @return Uebersicht.
     */
    public String toString() {
        String str = "Auf dem Tisch:\nFlop: " + tableCards.get(0) + ", " + tableCards.get(1) + ", " + tableCards.get(2) + "\n";
        str += "Turn: " + tableCards.get(3) + "\nRiver: " + tableCards.get(4) + "\n\n";
        if (ownCards.size() != 0) {
            str += "Eigene Hand: " + ownCards.get(0) + ", " + ownCards.get(1);
        }
        return str;
        
    }
    

}
