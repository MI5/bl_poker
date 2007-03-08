import java.awt.*;
import java.awt.image.*;


public class CardChecker {

    public static String ermittelWert(BufferedImage image, int anpasser,
            int eigenerAnpasser) {
        int count = 0;
        // Ermittel Ass
        for (int i = 274 + anpasser; i < 287 + anpasser; i++) {
            for (int j = 187 + eigenerAnpasser; j < 196 + eigenerAnpasser; j++) {
                if (image.getRGB(i, j) != -1) {
                    // Entscheidbar in einem Durchgang
                    if ((i == 275 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 0) {
                        return "ASS";
                    }
                    if ((i == 276 + anpasser) && (j == 190 + eigenerAnpasser)
                            && count == 0) {
                        return "ZWEI";
                    }

                    if ((i == 279 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 0) {
                        return "SIEBEN";
                    }
                    if ((i == 276 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 0) {
                        return "BUBE";
                    }
                    if ((i == 275 + anpasser) && (j == 187 + eigenerAnpasser)
                            && count == 0) {
                        return "DAME";
                    }
                    if ((i == 276 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 0) {
                        return "KÃ–NIG";
                    }

                    // Entscheidbar in drei DurchgÃ¤ngen

                    if ((i == 277 + anpasser) && (j == 187 + eigenerAnpasser)
                            && count == 2) {
                        return "VIER";
                    }
                    if ((i == 277 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 2) {
                        return "NEUN";
                    }

                    // Entscheidbar in 5 Durchgaengen

                    if ((i == 276 + anpasser) && (j == 191 + eigenerAnpasser)
                            && count == 4) {
                        return "ZEHN";
                    }

                    // Entscheidbar in 6 DurchgÃ¤ngen

                    if ((i == 278 + anpasser) && (j == 190 + eigenerAnpasser)
                            && count == 5) {
                        return "DREI";
                    }
                    if ((i == 278 + anpasser) && (j == 189 + eigenerAnpasser)
                            && count == 5) {
                        return "FÃœNF";
                    }

                    // Entscheidbar in 11 Durchgaengen
                    if ((i == 278 + anpasser) && (j == 189 + eigenerAnpasser)
                            && count == 10) {
                        return "SECHS";
                    }

                    if ((i == 278 + anpasser) && (j == 188 + eigenerAnpasser)
                            && count == 10) {
                        return "ACHT";
                    }

                    if (image.getRGB(i, j) != -1) {
                        count++;
                    }
                }

            }

        }
        return "Keine Zuweisung";

    }
    
    
    
    
    
    
    
    
    
    

    public static void main(String[] args) {

        try {
            // Get the screen size
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rectangle = new Rectangle(0, 0, screenSize.width,
                    screenSize.height);

            Robot robot = new Robot();

            BufferedImage image = robot.createScreenCapture(rectangle);

            int anpasser = 0;
            int eigenerAnpasser = 0;
            for (int i = 1; i < 6; i++) {
                if (image.getRGB(274 + anpasser, 202) != -1) {
                    if (image.getRGB(274 + anpasser, 202) == -14109143
                            || image.getRGB(274 + anpasser, 202) == -14531295
                            || image.getRGB(274 + anpasser, 202) == -14308827
                            || image.getRGB(274 + anpasser, 202) == -14714079
                            || image.getRGB(274 + anpasser, 202) == -14372570) {
                        System.out.println(i + ".Karte: nicht ausgespielt");
                    } else {
                        System.out.print(i + ".Karte: Karo ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
                    }
                } else if (image.getRGB(275 + anpasser, 198) != -1) {
                    if (image.getRGB(275 + anpasser, 198) == -14240472
                            || image.getRGB(275 + anpasser, 198) == -14374106
                            || image.getRGB(275 + anpasser, 198) == -14374619
                            || image.getRGB(275 + anpasser, 198) == -14245082
                            || image.getRGB(275 + anpasser, 198) == -14306778) {
                        System.out.println(i + ".Karte: nicht ausgespielt");
                    } else {
                        System.out.print(i + ".Karte: Herz ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
                    }
                } else if (image.getRGB(275 + anpasser, 200) != -1) {
                    if (image.getRGB(275 + anpasser, 200) == -14174168
                            || image.getRGB(275 + anpasser, 200) == -14111449
                            || image.getRGB(275 + anpasser, 200) == -14375131
                            || image.getRGB(275 + anpasser, 200) == -14448347
                            || image.getRGB(275 + anpasser, 200) == -14307291) {
                        System.out.println(i + ".Karte: nicht ausgespielt");
                    } else {
                        System.out.print(i + ".Karte: Kreuz ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
                    }
                } else {
                    if (image.getRGB(274 + anpasser, 202) == -14239704
                            || image.getRGB(274 + anpasser, 202) == -14308314
                            || image.getRGB(274 + anpasser, 202) == -14440409
                            || image.getRGB(274 + anpasser, 202) == -14930405
                            || image.getRGB(274 + anpasser, 202) == -14372056) {
                        System.out.println(i + ".Karte: nicht ausgespielt");
                    } else {
                        System.out.print(i + ".Karte: Pik ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
                    }

                }
                anpasser += 54;
            }

            System.out.println("EIGENE KARTEN:");
            anpasser = 440;
            eigenerAnpasser = 59;
            if (image.getRGB(748, 244) != -12894323) {
                for (int i = 6; i < 8; i++) {
                    if (image.getRGB(274 + anpasser, 202 + eigenerAnpasser) != -1) {
                        System.out.print(i + ".Karte: Karo ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));

                    } else if (image.getRGB(275 + anpasser, 198 + eigenerAnpasser) != -1) {
                        System.out.print(i + ".Karte: Herz ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
                    } else if (image.getRGB(275 + anpasser, 200 + eigenerAnpasser) != -1) {
                        System.out.print(i + ".Karte: Kreuz ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
                    } else {
                        System.out.print(i + ".Karte: Pik ");
                        System.out.println(ermittelWert(image, anpasser,
                                eigenerAnpasser));
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
}
