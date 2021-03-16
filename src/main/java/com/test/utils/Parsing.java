package com.test.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.test.dto.CustomerDTO;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import static com.test.constants.Environment.PATH_TO_JSON;

public class Parsing {

    public static List<CustomerDTO> loadCustomers() {

        BufferedReader bufferedReader;
        List<CustomerDTO> customers = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(PATH_TO_JSON));
            Gson gson = new Gson();
            Type listType = new TypeToken<List<CustomerDTO>>() {
            }.getType();
            customers = gson.fromJson(bufferedReader, listType);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static Set<CustomerDTO> loadOldCustomers() {

        BufferedReader bufferedReader;
        Set<CustomerDTO> customers = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(PATH_TO_JSON));
            Gson gson = new Gson();
            Type listType = new TypeToken<Set<CustomerDTO>>() {
            }.getType();
            customers = gson.fromJson(bufferedReader, listType);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static Set<CustomerDTO> uploadedFile(MultipartFile file) {

        String content = null;
        try {
            content = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<Set<CustomerDTO>>() {
        }.getType();
        return gson.fromJson(content, listType);
    }


    public static void saveActualCustomers(Set<CustomerDTO> actualCustomers) {
        try {
            Writer writer = new FileWriter(PATH_TO_JSON);
            Gson gson = new GsonBuilder().create();
            gson.toJson(actualCustomers, writer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearOldCustomerData() {
        File file = new File(PATH_TO_JSON);
        if ((file.exists()) && file.isFile()) {
            file.delete();
        }
    }
}
