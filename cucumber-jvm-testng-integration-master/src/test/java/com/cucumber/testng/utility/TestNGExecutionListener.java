package com.cucumber.testng.utility;

import org.testng.IExecutionListener;
/**
 * 
 * @author kmathai
 *
 */


public class TestNGExecutionListener implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        System.out.println("TestNG is staring the execution");
    }
    @Override
    public void onExecutionFinish() {
        System.out.println("Generating the Detailed Report");
        GenerateReport.GenerateDetailedReport();
        System.out.println("TestNG has finished execution");
    }
}
