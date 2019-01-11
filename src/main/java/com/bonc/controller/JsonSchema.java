package com.bonc.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 10:21 2018/12/4.
 * @Modified By:
 */
@RestController
public class JsonSchema {

//    @PostMapping("/json")
//    public String jsonSchema(){
//        JsonNode schema = readJsonFile("src/main/resources/Schema.json");
//        String json = "{\"id\":1234,\"name\":jsonSchema}";
//        JsonNode jsonNode = null;
//        try {
//            jsonNode = JsonLoader.fromString(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ProcessingReport report = null;
//        report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, jsonNode);
//        System.out.println(report.isSuccess());
//        return "asdf";
//
//    }
//
//    private JsonNode readJsonFile(String filePath) {
//
//        JsonNode instance = null;
//        try {
//            instance = new JsonNodeReader().fromReader(new FileReader(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return instance;
//    }


}
