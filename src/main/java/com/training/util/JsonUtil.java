package com.training.util;

import com.training.model.LoginData;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private JsonUtil() {
    }

    public static Object[][] readJson(String path) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            LoginData[] loginData =
                    mapper.readValue(
                            new File(path),
                            LoginData[].class
                    );

            Object[][] data =
                    new Object[loginData.length][2];

            for (int i = 0; i < loginData.length; i++) {

                data[i][0] = loginData[i].username();
                data[i][1] = loginData[i].password();
            }

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read JSON file: " + path,
                    e
            );
        }
    }
}