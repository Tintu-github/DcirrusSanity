package main.java.com.dcirrus.login;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class RunDcirrusAutomation 
{

	public static void main(String[] args)
	{
		TestNG testsuite= new TestNG();
		List<String> suites=Lists.newArrayList();
        suites.add(args[0]);
        testsuite.setTestSuites(suites);
        testsuite.run();
    }
}