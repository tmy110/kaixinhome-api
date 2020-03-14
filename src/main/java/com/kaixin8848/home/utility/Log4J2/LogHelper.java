package com.kaixin8848.home.utility.Log4J2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Log4 Helper Tool
 * @author zhoulvjie
 */
public class LogHelper {
    public static Logger log = LogManager.getLogger("kaixinhome_log");
    private static  String logFormats = "ModuleBig %s ModuleSmall %s Filter1 %s Filter2 %s Message %s";
    /**
     * 打印警告
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     * @param filter1
     * @param filter2
     */
    public static void warn(Object obj,String moduleBig,String moduleSmall,String filter1,String filter2) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";
            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                log.warn(String.format(logFormats,moduleBig,moduleSmall,filter1,filter2,location + str));
            } else {
                log.warn(String.format(logFormats,moduleBig,moduleSmall,filter1,filter2,location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 打印信息
     *
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     * @param filter1
     * @param filter2
     */
    public static void info(Object obj,String moduleBig,String moduleSmall,String filter1,String filter2) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";
            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                log.info(String.format(logFormats,moduleBig,moduleSmall,filter1,filter2,location + str));
            } else {
                log.info(String.format(logFormats,moduleBig,moduleSmall,filter1,filter2,location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    /**
     * 打印信息
     *
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     */
    public static void info(Object obj,String moduleBig,String moduleSmall) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";
            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                log.info(String.format(logFormats,moduleBig,moduleSmall,"","",location + str));
            } else {
                log.info(String.format(logFormats,moduleBig,moduleSmall,"","",location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    /**
     * 打印错误
     *
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     * @param filter1
     * @param filter2
     */
    public static void error(Object obj,String moduleBig,String moduleSmall,String filter1,String filter2) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";

            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                log.error(String.format(logFormats,moduleBig,moduleSmall,filter1,filter2,location + str));
            } else {
                log.error(String.format(logFormats,moduleBig,moduleSmall,filter1,filter2,location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    /**
     * 打印错误
     *
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     */
    public static void error(Object obj,String moduleBig,String moduleSmall) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";

            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                log.error(String.format(logFormats,moduleBig,moduleSmall,"","",location + str));
            } else {
                log.error(String.format(logFormats,moduleBig,moduleSmall,"","",location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 打印错误
     *
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     * @param message
     */
    public static void error(String message,Object obj,String moduleBig,String moduleSmall) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";

            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = message + sw.toString();
                log.error(String.format(logFormats,moduleBig,moduleSmall,"","",location + str));
            } else {
                log.error(String.format(logFormats,moduleBig,moduleSmall,"","",location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    /**
     * 打印错误
     *
     * @param obj
     * @param moduleBig
     * @param moduleSmall
     * @param filter1
     */
    public static void error(Object obj,String moduleBig,String moduleSmall,String filter1) {
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";

            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                log.error(String.format(logFormats,moduleBig,moduleSmall,filter1,"",location + str));
            } else {
                log.error(String.format(logFormats,moduleBig,moduleSmall,filter1,"",location + obj.toString()));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
