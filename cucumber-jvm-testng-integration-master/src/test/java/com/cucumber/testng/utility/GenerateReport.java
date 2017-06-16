package com.cucumber.testng.utility;

import net.masterthought.cucumber.ReportBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author kmathai
 *
 */
public class GenerateReport {
    public static void GenerateDetailedReport(){
    	try{
        	
            String RootDir = System.getProperty("user.dir");
            File reportOutputDirectory = new File("target/detailedReport");
            List<String> list = new ArrayList<String>();
            list.add("target/cucumber.json");
       

            String pluginUrlPath = "";
            String buildNumber = "1";
            String buildProject = "cucumber-jvm";
            boolean skippedFails = true;
            boolean pendingFails = true;
            boolean undefinedFails = true;
            boolean missingFails = true;
            boolean flashCharts = true;
            boolean runWithJenkins = false;
            boolean highCharts = false;
            boolean parallelTesting = true;
            boolean artifactsEnabled = false;
            String artifactConfig = "";

            ReportBuilder reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber,
                    buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, runWithJenkins,
                    highCharts, parallelTesting);

            reportBuilder.generateReports();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
