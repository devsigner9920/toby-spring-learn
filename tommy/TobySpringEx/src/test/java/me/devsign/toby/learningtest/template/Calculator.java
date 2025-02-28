package me.devsign.toby.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filepath) throws IOException {
        BufferedReaderCallback sumCallback =
                br -> {
                    int sum = 0;
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sum += Integer.parseInt(line);
                    }
                    return sum;
                };

        return fileReadTemplate(filepath, sumCallback);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        BufferedReaderCallback multiplyCallback =
                br -> {
                    int multiply = 1;
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        multiply *= Integer.parseInt(line);
                    }

                    return multiply;
                };

        return fileReadTemplate(filepath, multiplyCallback);
    }

    public Integer calcLineSum(String filepath) throws IOException {
        LineCallback sumCallback =
                (line, value) -> {
                    return value + Integer.parseInt(line);
                };

        return lineReadTemplate(filepath, sumCallback, 0);
    }

    public Integer calcLineMultiply(String filepath) throws IOException {
        LineCallback multiplyCallback =
                (line, value) -> {
                    return value * Integer.parseInt(line);
                };

        return lineReadTemplate(filepath, multiplyCallback, 1);
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try { br.close(); }
                catch (IOException e) { System.out.println(e.getMessage()); }
            }
        }
    }

    public Integer lineReadTemplate(String filepath, LineCallback callback, int initVal) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            Integer res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) { throw e; }
        finally {
            if (br != null) {
                try { br.close(); }
                catch (IOException e) { System.out.println(e.getMessage()); }
            }
        }
    }

}
