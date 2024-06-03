package ru.itis.proservice.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.itis.proservice.entity.Employee;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new Gson();
    private static final String filePath = "src/main/resources/json/data.json";

    public static String toJson(Employee employee) {
        return gson.toJson(employee);
    }

    public static Employee fromJson(String json, Class<Employee> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static List<Employee> readEmployeesFromFile() {
        List<Employee> employees = new ArrayList<>();
        try {
            Reader reader = new FileReader(filePath);

            employees = gson.fromJson(reader, new TypeToken<List<Employee>>() {}.getType());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
