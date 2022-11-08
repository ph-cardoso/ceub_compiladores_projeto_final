package br.dev.phcardoso.compilador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Compilador {
    public static List<List<String>> getLexemasList(List<String> instructions) {
        List<List<String>> lexemasList = new ArrayList<>();

        for ( String instruction : instructions ) {
            List<String> lexemas = Arrays.asList(instruction.split(" "));
            lexemasList.add(lexemas);
        }

        return lexemasList;
    }
}
