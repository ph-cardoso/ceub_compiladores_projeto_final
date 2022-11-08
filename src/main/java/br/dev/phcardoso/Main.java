package br.dev.phcardoso;

import br.dev.phcardoso.app.App;
import br.dev.phcardoso.utils.FileManipulator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        App app = new App(sc, "input.txt");

        int rc = app.run();

        sc.close();

        System.exit(rc);
    }
}