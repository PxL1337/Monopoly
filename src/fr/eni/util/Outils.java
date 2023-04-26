package fr.eni.util;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Outils {
    private static Random r = new Random();
    private static Scanner s = new Scanner(System.in);


    public static int nombreAlea(int max) {
        return Outils.r.nextInt(max);
    }

    private static int saisie(String message, int min, int max) {
        int val = 0;
        boolean ok;
        do {
            try {
                val = Outils.s.nextInt();
                ok = val >= min && val <= max;
            } catch (InputMismatchException e) {
                ok = false;
            } finally {
                Outils.s.nextLine();
            }
            if (!ok) {
                System.out.println("Saisie incorrecte, veuillez recommencer");
            }
        }   while (!ok);
        assert val >= min && val <= max;
        return val;
    }

    public static String saisie(String message) {
        System.out.println(message);
        return Outils.s.nextLine();
    }

    public static boolean ouiNon(String message) {
        System.out.printf("%s (Oui/Non) : ", message);
        String rep = Outils.s.nextLine();
        return rep.equalsIgnoreCase("oui");
    }

    public static <T> int choix(String message, Iterable<T> propositions) {
        System.out.println(message);
        int i = 1;
        int ret;
        for (T t : propositions) {
            System.out.printf("%d - %s%n", i, t);
            i++;
        }
        if (i == 2) {
            System.out.println("Une seule possibilité, automatiquement retenue");
            ret = 0;
        } else {
            ret = Outils.saisie("Votre choix", 1, i - 1) - 1;
        }
        return ret;
    }

    public static <T extends Comparable<T>> T max(T elem1, T elem2) {
        T maximum;
        if (elem1.compareTo(elem2) > 0)
            maximum = elem1;
        else
            maximum = elem2;
        return maximum;
    }

}
