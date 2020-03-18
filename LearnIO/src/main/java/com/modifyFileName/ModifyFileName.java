package com.modifyFileName;


import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author halfOfGame
 * @create 2020-03-18,17:11
 */
public class ModifyFileName {
    public static void main(String[] args) throws IOException, ParseException {
        File path = new File("C:\\Users\\Dm20\\Desktop\\JVM_1");
        Collection<File> files = FileUtils.listFiles(path, null, true);
        int count = 0;
        String fileName = "";
        for (File file : files) {
            if (count % 2 == 0) {
                fileName = parseJson(file.getAbsolutePath(), "PartName");
            } else {
                move(file.getAbsolutePath(), "C:\\Users\\Dm20\\Desktop\\JVM_2\\" + fileName + ".flv");
            }
            count++;
        }
    }

    //给出文件路径和Key，返回value
    public static String parseJson(String filePath, String key) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        return (String) jsonObject.get(key);
    }

    //移动文件或者修改文件路径
    public static void move(String sourceFilePath, String targetFilePath) throws IOException {
        Files.move(Paths.get(sourceFilePath), Paths.get(targetFilePath));
    }

}

