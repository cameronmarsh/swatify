package edu.swarthmore.cs.cs71.swatify.test;

import edu.swarthmore.cs.cs71.swatify.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static spark.Spark.awaitInitialization;
import static spark.Spark.stop;

public class ControllerTestBase {
    @BeforeClass
    public static void setUp() throws Exception {
        Application.establishRoutes();

        awaitInitialization();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        // We had a concurrency issue with running multiple tests. Calling stop() tears down routes for all the tests
//        stop();
    }
}
