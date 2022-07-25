package com.epam.rd.java.basic.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part6 {

    private String fileName;
    private String[] input;

    public static void main(String[] args) {
        new Part6().console(args[0], args[1], args[2], args[3]);
    }

    public void console(String input, String fileName, String task, String operation) {
        if (!("--input".equals(input) || "-i".equals(input))) {
            System.err.println("Wrong operation");
            return;
        }
        if (!("--task".equals(task) || "-t".equals(task))) {
            System.err.println("Wrong task");
            return;
        }
        this.fileName = fileName;
        initialize();
        switch (operation) {
            case "frequency":
                Part61.getFrequency(this.input);
                break;
            case "length":
                Part62.getLength(this.input);
                break;
            case "duplicates":
                Part63.getDuplicates(this.input);
                break;
            default:
        }
    }

    private String getInput() {
        StringBuilder sb = new StringBuilder();
        try (Scanner file = new Scanner(new File(fileName), "CP1251")) {
            while (file.hasNext()) {
                sb.append(file.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", fileName));
        }
        return sb.toString();
    }

    private void initialize() {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(getInput());
        while (m.find()) {
            sb.append(m.group()).append(" ");
        }
        input = sb.toString().split(" ");
    }

}
