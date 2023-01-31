package test.java;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

/**
 * It runs the tests in parallel, generates the HTML report and asserts that
 * there are no failures
 */
public class BaseRunner {
    // OS Dependent file separator
    static String fileSeparator = File.separator;
    static String outputFolderName = "target";
    static String reportFolderName = "cucumber-html-reports";
    static String reportFolder = outputFolderName + fileSeparator + reportFolderName;

    /**
     * It runs the tests in parallel.
     *
     * @param path        The path to the feature files.
     * @param tags        This is the tag that you want to run.
     * @param parallel    This is the number of threads you want to run in parallel.
     * @param projectName The name of the project. This is used to generate the
     *                    report.
     */
    public void testParallel(String path, String tags, Integer parallel, String projectName) {
        Results results = Runner.path(path).backupReportDir(false).outputCucumberJson(true).reportDir(reportFolder)
                .tags(tags).parallel(parallel);
        generateReport(results.getReportDir(), projectName);
        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }

    /**
     * It takes the path to the karate output folder and the name of the project as
     * input and generates
     * the report
     *
     * @param karateOutputPath This is the path where the json files are generated.
     * @param projectName      This is the name of the project. It will be used to
     *                         generate the report.
     */
    private static void generateReport(final String karateOutputPath, String projectName) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] { "json" }, true);
        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File(outputFolderName), projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
