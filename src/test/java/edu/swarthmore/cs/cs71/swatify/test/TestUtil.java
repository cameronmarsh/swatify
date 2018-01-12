package edu.swarthmore.cs.cs71.swatify.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.fail;

/**
 * Source: https://github.com/mscharhag/blog-examples/blob/master/sparkdemo/src/test/java/com/mscharhag/sparkdemo/UserControllerIntegrationTest.java
 */

public class TestUtil {
    /**
     * Make an HTTP request to the local server.
     *
     * @param method      The HTTP method to use.
     * @param path        The path (at localhost) to make the request to.
     * @param requestBody The body of the request.
     * @return A TestResponse object containing the body and status of the response.
     */
    public static TestResponse request(String method, String path, String requestBody) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);

            if (!requestBody.isEmpty()) {
                OutputStream os = connection.getOutputStream(); // Warning: auto-changes connection method to POST.
                os.write(requestBody.getBytes("UTF-8"));
                os.close();
            }

            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Make a body-less request to the server.
     *
     * @param method The HTTP method to use.
     * @param path   The path (at localhost) to make the request to.
     * @return A TestResponse object containing the body and status of the response.
     */
    public static TestResponse request(String method, String path) {
        return request(method, path, "");
    }

    public static class TestResponse {
        private final String body;
        private final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public int getStatus() {
            return status;
        }

        public JsonObject json() {
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(body).getAsJsonObject();

            if (object == null) {
                object = new JsonObject();
            }

            return object;
        }

        public JsonArray jsonArray() {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(body).getAsJsonArray();

            if (array == null) {
                array = new JsonArray();
            }

            return array;
        }
    }
}
