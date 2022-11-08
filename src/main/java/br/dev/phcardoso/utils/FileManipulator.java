package br.dev.phcardoso.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class FileManipulator {
    public static List<String> removeComments(List<String> fileLines) {
        List<String> fileLinesWithoutComments = new ArrayList<>(fileLines);
        // Retirando comentários ( // e /* */ )
        for (int i = 0; i < fileLinesWithoutComments.size(); i++) {
            String fileLine = fileLinesWithoutComments.get(i);
            if (fileLine.contains("//")) {
                fileLine = fileLine.substring(0, fileLine.indexOf("//"));
                fileLinesWithoutComments.set(i, fileLine);
            } else if (fileLine.contains("/*") && fileLine.contains("*/")) {
                fileLine = fileLine.substring(0, fileLine.indexOf("/*")) + fileLine.substring(fileLine.indexOf("*/") + 2);
                fileLinesWithoutComments.set(i, fileLine);
            } else if (fileLine.contains("/*")) {
                fileLine = fileLine.substring(0, fileLine.indexOf("/*"));
                fileLinesWithoutComments.set(i, fileLine);
                while (!fileLinesWithoutComments.get(i).contains("*/")) {
                    fileLinesWithoutComments.remove(i);
                }
                fileLine = fileLinesWithoutComments.get(i).substring(fileLinesWithoutComments.get(i).indexOf("*/") + 2);
                fileLinesWithoutComments.set(i, fileLine);
            }
        }

        return fileLinesWithoutComments;
    }

    public static List<String> getInstructions(List<String> fileLines) {
        List<String> instructions = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for ( String fileLine : fileLines ) {
            for (int i = 0; i < fileLine.length(); i++) {
                if (fileLine.charAt(i) == ';') {
                    builder.append(fileLine.charAt(i));
                    instructions.add(builder.toString());
                    builder = new StringBuilder();
                } else {
                    // Removendo espaços no começo de cada instrução
                    if (fileLine.charAt(i) == ' ' && builder.length() == 0) {
                        continue;
                    }

                    if (i == fileLine.length() -1) {
                        builder.append(fileLine.charAt(i));
                        builder.append(' ');
                    } else {
                        builder.append(fileLine.charAt(i));
                    }
                }
            }
        }

        return instructions;
    }

    public static List<String> readFile(String path) {
        List<String> fileLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("files/input/" + path))) {
            String line;
            // Lendo as linhas do arquivo
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileLines;
    }

    public static void writeFile(String fileName, List<String> lines) {
        try (java.io.FileWriter writer = new java.io.FileWriter("files/output/" + fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFileWithList(String fileName, List<List<String>> lines) {
        try (java.io.FileWriter writer = new java.io.FileWriter("files/output/" + fileName)) {
            for (List<String> line : lines) {
                writer.write(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
