package test.java;

import org.junit.jupiter.api.Test;

public class TestRunner extends BaseRunner {
    String basePath = "classpath:test/java";
    String projName = "Users";
    String featurePath = "examples/users";
    String executeTags = "@EXECUTE";
    int parallelExec = 1;

    @Test
    public void testParallel() {
        super.testParallel(basePath + featurePath, executeTags, parallelExec, projName);
    }
}