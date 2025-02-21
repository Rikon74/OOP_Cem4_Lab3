package com.example.laba3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.stream.Collectors;

@WebServlet(name = "LaptopServlet", value = "/laptop")
public class LaptopServlet extends HttpServlet {

    private static final String FILE_PATH = "C:\\Users\\Rikon\\Desktop\\OOP\\laba3\\src\\main\\java\\com\\example\\laba3\\laptop.json";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonRequest = request.getReader().lines().collect(Collectors.joining());
        JSONObject newLaptopJson = new JSONObject(jsonRequest);

        JSONArray laptopsJsonArray = new JSONArray(readJSON());
        laptopsJsonArray.put(newLaptopJson);

        writeJSON(laptopsJsonArray.toString());

        response.getWriter().write(laptopsJsonArray.toString());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String laptopsJson = readJSON();
        response.getWriter().write(laptopsJson);
    }


    private String readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    private void writeJSON(String laptopsJson) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            fileWriter.write(laptopsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
