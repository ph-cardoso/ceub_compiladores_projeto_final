package br.dev.phcardoso.app;

import br.dev.phcardoso.compilador.Compilador;
import br.dev.phcardoso.utils.FileManipulator;
import br.dev.phcardoso.utils.TerminalUtils;

import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final String fileName;

    private final List<String> fileLines;

    public App(Scanner scanner, String fileName) {
        this.scanner = scanner;
        this.fileName = fileName;
        fileLines = FileManipulator.readFile(fileName);
    }

    private void showMenu() {
        System.out.println("1 - Limpar comentários");
        System.out.println("2 - Exibir instruções");
        System.out.println("3 - Exibir lexemas");
        System.out.println("0 - Sair");
        System.out.println();
    }

    private void showIntroduction() {
        System.out.println("Bem vindo ao compilador!");
        System.out.println("As operações serão realizadas no arquivo: /files/" + fileName);
        System.out.println();
    }

    public int run() {
        showIntroduction();
        TerminalUtils.pressEnterToContinue();
        while (true) {
            showMenu();
            System.out.print("Digite a opção desejada: ");
            int option = scanner.nextInt();
            System.out.println();

            TerminalUtils.clearTerminal();
            switch (option) {
                case 1:
                    List<String> fileLinesWithoutComments = FileManipulator.removeComments(fileLines);
                    FileManipulator.writeFile("semComentarios.txt", fileLinesWithoutComments);
                    for ( String fileLine : fileLinesWithoutComments ) {
                        System.out.println(fileLine);
                    }
                    break;
                case 2:
                    List<String> instructions =  FileManipulator.getInstructions(
                            FileManipulator.removeComments(fileLines)
                    );
                    FileManipulator.writeFile("instrucoes.txt", instructions);
                    for ( String instruction : instructions ) {
                        System.out.println(instruction);
                    }
                    break;
                case 3:
                    List<List<String>> lexemas = Compilador.getLexemasList(
                            FileManipulator.getInstructions(
                                    FileManipulator.removeComments(fileLines)
                            )
                    );
                    FileManipulator.writeFileWithList("lexemas.txt", lexemas);
                    for ( List<String> lexema : lexemas ) {
                        System.out.println(lexema);
                    }
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar o compilador!");
                    System.out.println();
                    return 0;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }


}
