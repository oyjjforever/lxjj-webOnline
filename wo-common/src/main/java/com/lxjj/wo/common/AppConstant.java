package com.lxjj.wo.common;

import java.util.HashMap;

/**
 * 系统常量
 */
public class AppConstant {

    public static final HashMap<String, HashMap<String,String>> APP_CONSTANT_BILL_OPE = new HashMap<>();

    static{
        //开票成功和失败次数：openBillCount
        HashMap<String,String> openBillCountMap = new HashMap<>();
        openBillCountMap.put("success","OpenBillCounter5m");
        openBillCountMap.put("failure","OpenBillFailCounter5m");
        openBillCountMap.put("successDes","开票成功次数");
        openBillCountMap.put("failureDes","开票失败次数");
        APP_CONSTANT_BILL_OPE.put("openBillCount",openBillCountMap);

        //开票成功金额和失败金额
        HashMap<String,String> openBollAmountMap = new HashMap<>();
        openBollAmountMap.put("success","OpenBillAmountCounter5m");
        openBollAmountMap.put("failure","OpenBillFailAmountCounter5m");
        openBollAmountMap.put("successDes","开票成功金额");
        openBollAmountMap.put("failureDes","开票失败金额");
        APP_CONSTANT_BILL_OPE.put("openBillAmount",openBollAmountMap);

        //开缴款书成功次数和失败次数
        HashMap<String,String> openPayBookCount = new HashMap<>();
        openPayBookCount.put("success","OpenPayBookCounter5m");
        openPayBookCount.put("failure","OpenPayBookFailCounter5m");
        openPayBookCount.put("successDes","开缴款书成功次数");
        openPayBookCount.put("failureDes","开缴款书失败次数");
        APP_CONSTANT_BILL_OPE.put("openPayBookCount",openPayBookCount);

        //开缴款书成功和失败金额：openPayBookAmount
        HashMap<String,String> openPayBookAmountMap = new HashMap<>();
        openPayBookAmountMap.put("success","OpenPayBookAmountCounter5m");
        openPayBookAmountMap.put("failure","OpenPayBookFailAmountCounter5m");
        openPayBookAmountMap.put("successDes","开缴款书成功金额");
        openPayBookAmountMap.put("failureDes","开缴款书失败金额");
        APP_CONSTANT_BILL_OPE.put("openPayBookAmount",openPayBookAmountMap);

        //制票成功和失败次数： makeBillCount
        HashMap<String,String> makeBillCountMap = new HashMap<>();
        makeBillCountMap.put("success","IssueCounter5m");
        makeBillCountMap.put("failure","IssueFailCounter5m");
        makeBillCountMap.put("successDes","制票成功次数");
        makeBillCountMap.put("failureDes","制票失败次数");
        APP_CONSTANT_BILL_OPE.put("makeBillCount",makeBillCountMap);


    }
}
