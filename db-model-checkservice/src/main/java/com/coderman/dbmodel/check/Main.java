//package com.coderman.dbmodel.check;
//
//
//import com.coderman.dbmodel.check.bean.MessageBean;
//
//import com.coderman.dbmodel.check.service.check.CheckMysqlService;
//import com.coderman.dbmodel.check.service.check.CheckTableService;
//import com.coderman.dbmodel.check.service.check.MessageToExcelService;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created byon 2018-5-15.
// *
// * @author: fanchunshuai
// * @version: V1.0
// * @Desc:
// */
//
//
//public class Main {
//	public static void main(String[] args) {
//
//		String projectPath = System.getProperty("user.dir") + "\\" + args[0];
//		System.out.println("hello db checker , take you to heaven .......");
//
//		System.out.println("projectPath = " + projectPath);
//
//		long startTime = System.currentTimeMillis();
//		try {
//			GlobalService globalService = new GlobalService(projectPath);
//			List<MessageBean> resultList = new ArrayList<>();
//			List<MessageBean> result;
//
//			//单库检查
//			if (globalService.isSingle() == true) {
//				CheckTableService checkTableService = new CheckTableService(globalService);
//				CheckMysqlService checkMysqlService = new CheckMysqlService(globalService);
//				result = checkMysqlService.checkEvent();
//				resultList.addAll(result);
//				result = checkMysqlService.checkProceduerCount();
//				resultList.addAll(result);
//				result = checkMysqlService.checkTrigger();
//				resultList.addAll(result);
//				result = checkMysqlService.checkViewCount();
//				resultList.addAll(result);
//				List<MessageBean> resultTableList = checkTableService.checkTableInfo();
//				resultList.addAll(resultTableList);
//			} else {
//				//双库检查
//				DoubleDBCheckService checkService = new DoubleDBCheckService(globalService);
//				result = checkService.contrastService();
//				resultList.addAll(result);
//			}
//
//			System.out.println("messageList.size  = " + resultList.size());
//			MessageToExcelService messageToExcelService = new MessageToExcelService();
//			messageToExcelService.writeToExcel(resultList, projectPath + "\\result.xlsx");
//
//
//			long endTime = System.currentTimeMillis();
//
//			System.out.println("db model check work is down,use " + (endTime - startTime) + "ms,see the result.txt !");
//		} catch (Exception e) {
//			System.out.println("db model check work has error ,please contact fanchunshuai@daojia-inc.com!");
//			e.printStackTrace();
//		}
//	}
//}
