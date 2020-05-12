package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	// this variable will be shared for entire test execution
	public static RequestSpecification req;
	JsonPath json;

	// this method is used to build request spec which will set
	// baseURI and for loggers
	public RequestSpecification requestSpec() throws IOException {
		// the if is used to long request spec in one file
		if (req == null) {
			// writing logs into logger file
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	// reading the property file returning the URL
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		String currentDirectory = System.getProperty("user.dir");
		// reading URL from file
		FileInputStream fis = new FileInputStream(currentDirectory + "/src/main/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty("baseURL");
	}

	// Converting Json response to String
	public String getJsonPath(Response response, String key) {
		String res = response.asString();
		json = new JsonPath(res);
		return (json.get(key).toString());
	}

}
