package com.bonc;

import com.bonc.rabbit.MsgProducer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootfreemarkerApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	/*@Test
	public  void jsonSchema(){
		String result="error";
		JsonNode schema = readJsonFile("src/main/resources/Schema.json");
		String json = "{\"id\":1234,\"name\":12345678}";
		JsonNode jsonNode = null;
		try {
			jsonNode = JsonLoader.fromString(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProcessingReport report = null;
		report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, jsonNode);
		//System.out.println(report.isSuccess());
		if(report.isSuccess()){
			result="success";
		}else {
			result="json error!";
		}
		System.out.println(result);
		Iterator<ProcessingMessage> it = report.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		//return result;

	}*/

	/*private JsonNode readJsonFile(String filePath) {

		JsonNode instance = null;
		try {
			instance = new JsonNodeReader().fromReader(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
	}*/


}
