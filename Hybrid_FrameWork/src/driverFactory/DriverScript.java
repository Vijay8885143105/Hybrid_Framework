package driverFactory;

import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath ="./TestInput/DataEngine.xlsx";
	String outputpath ="./TestOutPut/HybridResults.xlsx";
	String TCSheet="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void startTest() throws Throwable
	{
		boolean res =false;
		String tcres="";
		//create refrenece object
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in both sheets
		int TCCount =xl.rowCount(TCSheet);
		int TSCount = xl.rowCount(TSSheet);
		//iterate all rows in TCSheet
		for(int i=1;i<=TCCount;i++)
		{
			//read module status cell
			String modulestatus = xl.getCellData(TCSheet, i, 2);
			if(modulestatus.equalsIgnoreCase("Y"))
			{
				//read tcid cell
				String tcid =xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSheet
				for(int j=1;j<=TSCount;j++)
				{
					//read tsid cell from TSsheet
					String tsid = xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read all keyword 
						String keyword = xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("adminLogin"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
						res = FunctionLibrary.adminLogin(para1, para2);	
						}
						else if(keyword.equalsIgnoreCase("branchCreate"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 7);
							String para4 = xl.getCellData(TSSheet, j, 8);
							String para5 =xl.getCellData(TSSheet, j, 9);
							String para6 = xl.getCellData(TSSheet, j, 10);
							String para7 =xl.getCellData(TSSheet, j, 11);
							String para8 = xl.getCellData(TSSheet, j, 12);
							String para9 = xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.Branches_Click();
							res = FunctionLibrary.branchCreate(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						}
						else if(keyword.equalsIgnoreCase("branchUpdate"))
						{
							String para1 =xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							String para3 =xl.getCellData(TSSheet, j, 9);
							String para4 = xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.Branches_Click();
							res = FunctionLibrary.branchUpdate(para1, para2, para3, para4);
						}
						else if(keyword.equalsIgnoreCase("adminLogout"))
						{
							res = FunctionLibrary.adminLogout();
						}
						
						String tsres="";
						if(res)
						{
							//if res is true write as pass into status cell
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						else
						{
							//if res is false write as fail into status cell
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						tcres=tsres;
					}
				}
				//write as tcres into TCSheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//which testcase flag to N write as Blocked into status cell in TCsheet
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
	}

}
