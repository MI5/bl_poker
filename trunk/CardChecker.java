import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;

public class CardChecker {

    private LinkedList<Card> tableCards = new LinkedList<Card>();

    private LinkedList<Card> ownCards = new LinkedList<Card>();
    
    
    public CardChecker() {
        ermittleTischKarten();
        ermittleEigeneKarten();
    }

    public static int ermittelWert(BufferedImage image, int anpasser,
            int eigenerAnpasser) {
        int count = 0;
        // Ermittel Ass
        for (int i = 274 + anpasser; i < 287 + anpasser; i++) {
            for (int j = 187 + eigenerAnpasser; j < 196 + eigenerAnpasser; j++) {
                if (image.getRGB(i, j) != -1) {
                    // Entscheidbar in einem Durchgang
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

                    // Entscheidbar in drei DurchgÃ¤ngen

                    if ((i == 277 + anpasser) && (j == 187 + eigenerAnpasser)
                            && count == 2) {
                        return 4;
                    }
                    if ((i == 277 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 2) {
                        return 9;
                    }

                    // Entscheidbar in 5 Durchgaengen

                    if ((i == 276 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 4) {
                        return 10;
                    }

                    // Entscheidbar in 6 DurchgÃ¤ngen

                    if ((i == 278 + anpasser) && (j == 190 + eigenerAnpasser)
                            && count == 5) {
                        return 3;
                    }
                    if ((i == 278 + anpasser) && (j == 189 + eigenerAnpasser)
                            && count == 5) {
                        return 5;
                    }

                    // Entscheidbar in 11 Durchgaengen
                    if ((i == 278 + anpasser) && (j == 189 + eigenerAnpasser)
                            && count == 10) {
                        return 6;
                    }

                    if ((i == 278 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 10) {
                        return 8;
                    }

                    if (image.getRGB(i, j) != -1) {
                        count++;
                    }
                }

            }

        }
        return 0;

    }

    public void ermittleTischKarten() {
        try {
            // Get the screen size

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rectangle = new Rectangle(0, 0, screenSize.width,
                    screenSize.height);

            Robot robot = new Robot();

            BufferedImage image = robot.createScreenCapture(rectangle);

            tableCards = new LinkedList<Card>();
            
            int anpasser = 0;
            int eigenerAnpasser = 0;
            for (int i = 1; i < 6; i++) {
                if (image.getRGB(274 + anpasser, 202) != -1) {
                    if (image.getRGB(274 + anpasser, 202) == -14109143
                            || image.getRGB(274 + anpasser, 202) == -14531295
                            || image.getRGB(274 + anpasser, 202) == -14308827
                            || image.getRGB(274 + anpasser, 202) == -14714079
                            || image.getRGB(274 + anpasser, 202) == -14372570) {
//                        System.out.println(i + ".Karte: nicht ausgespielt");
                        tableCards.addLast(new Card("NAC", 0));
                    } else {
//                        System.out.print(i + ".Karte: Karo ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        tableCards.addLast(new Card("KARO", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                       
                    }
                } else if (image.getRGB(275 + anpasser, 198) != -1) {
                    if (image.getRGB(275 + anpasser, 198) == -14240472
                            || image.getRGB(275 + anpasser, 198) == -14374106
                            || image.getRGB(275 + anpasser, 198) == -14374619
                            || image.getRGB(275 + anpasser, 198) == -14245082
                            || image.getRGB(275 + anpasser, 198) == -14306778) {
//                        System.out.println(i + ".Karte: nicht ausgespielt");
                        tableCards.addLast(new Card("NAC", 0));
                    } else {
//                        System.out.print(i + ".Karte: Herz ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        tableCards.addLast(new Card("HERZ", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }
                } else if (image.getRGB(275 + anpasser, 200) != -1) {
                    if (image.getRGB(275 + anpasser, 200) == -14174168
                            || image.getRGB(275 + anpasser, 200) == -14111449
                            || image.getRGB(275 + anpasser, 200) == -14375131
                            || image.getRGB(275 + anpasser, 200) == -14448347
                            || image.getRGB(275 + anpasser, 200) == -14307291) {
//                        System.out.println(i + ".Karte: nicht ausgespielt");
                        tableCards.addLast(new Card("NAC", 0));
                    } else {
//                        System.out.print(i + ".Karte: Kreuz ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        tableCards.addLast(new Card("KREUZ", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }
                } else {
                    if (image.getRGB(274 + anpasser, 202) == -14239704
                            || image.getRGB(274 + anpasser, 202) == -14308314
                            || image.getRGB(274 + anpasser, 202) == -14440409
                            || image.getRGB(274 + anpasser, 202) == -14930405
                            || image.getRGB(274 + anpasser, 202) == -14372056) {
//                        System.out.println(i + ".Karte: nicht ausgespielt");
                        tableCards.addLast(new Card("NAC", 0));
                    } else {
//                        System.out.print(i + ".Karte: Pik ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        tableCards.addLast(new Card("PIK", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                    }

                }
                anpasser += 54;
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ermittleEigeneKarten() {
        try {
            // Get the screen size

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rectangle = new Rectangle(0, 0, screenSize.width,
                    screenSize.height);

            Robot robot = new Robot();

            BufferedImage image = robot.createScreenCapture(rectangle);
            
            
            System.out.println("EIGENE KARTEN:");
            int anpasser = 440;
            int eigenerAnpasser = 59;
            if (image.getRGB(748, 244) != -12894323) {
                for (int i = 6; i < 8; i++) {
                    if (image.getRGB(274 + anpasser, 202 + eigenerAnpasser) != -1) {
//                        System.out.print(i + ".Karte: Karo ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        ownCards.addLast(new Card("KARO", ermittelWert(image, anpasser,
                                eigenerAnpasser)));

                    } else if (image.getRGB(275 + anpasser,
                            198 + eigenerAnpasser) != -1) {
//                        System.out.print(i + ".Karte: Herz ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        ownCards.addLast(new Card("HERZ", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                    } else if (image.getRGB(275 + anpasser,
                            200 + eigenerAnpasser) != -1) {
//                        System.out.print(i + ".Karte: Kreuz ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        ownCards.addLast(new Card("KREUZ", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                    } else {
//                        System.out.print(i + ".Karte: Pik ");
//                        System.out.println(ermittelWert(image, anpasser,
//                                eigenerAnpasser));
                        ownCards.addLast(new Card("PIK", ermittelWert(image, anpasser,
                                eigenerAnpasser)));
                        
                    }
                    anpasser += 15;
                    eigenerAnpasser += 4;
                }
            } else {
                System.out.println("Eigene Karten noch nicht erhalten!");
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
    public LinkedList<Card> returnTableCards() {
        ermittleTischKarten();
        return tableCards;
    }

    /**
     * Gibt die eigenen Karten als LinkedList zurueck.
     * 
     * @return eigene Karten
     */
    public LinkedList<Card> returnOwnCards() {
        ermittleEigeneKarten();
        return ownCards;
    }
    

    public String toString() {
        String str = "Auf dem Tisch:\nFlop: " + tableCards.get(0) + ", " + tableCards.get(1) + ", " + tableCards.get(2) + "\n";
        str += "Turn: " + tableCards.get(3) + "\nRiver: " + tableCards.get(4) + "\n\n";
        //str += "Eigene Hand: " + ownCards.get(0) + ", " + ownCards.get(1);
        return str;
        
    }
    

}
