package com.demo.zhulong.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Administrator
 * @Tags: 
 * @Date: 2017年9月6日 上午11:03:12
 */
public class ConverVideoRelated {

	private final static String CONVERT_FILE_PATH = "E:\\changeVideo\\ddhuaxiaomianyang.rmvb";
	private final static String AVI_FILE_PATH = "E:\\changeVideo\\ddhuaxiaomianyang.avi";
	private final static String FLV_FILE_PATH = "E:\\changeVideo\\ddhuaxiaomianyang.flv";
	
	private static final String MENCODE_PATH = "E:\\changeVideo\\ffmpeg\\mencoder";
	private static final String FFMPEG_PATH = "E:\\changeVideo\\ffmpeg\\ffmpeg.exe";
	private static final String SCREEN_SHOT_PATH = "E:\\changeVideo\\screenshot\\";
	
	
	public static void main(String[] args) {
		if (!checkfile(CONVERT_FILE_PATH)) { // 判断转化文件的路径是不是一个文件
			System.out.println(CONVERT_FILE_PATH + " is not file");
			return;
		}
		if (process(CONVERT_FILE_PATH, FLV_FILE_PATH, FFMPEG_PATH, AVI_FILE_PATH, MENCODE_PATH, SCREEN_SHOT_PATH)) { // 执行转码任务
			System.out.println("ok");
		}
	}

	private static boolean process(String CONVERT_FILE_PATH, String FLV_FILE_PATH, String FFMPEG_PATH, String AVI_FILE_PATH, String MENCODE_PATH, String SCREEN_SHOT_PATH) {
		// 判断视频的类型
		int type = checkContentType(CONVERT_FILE_PATH);
		boolean status = false;
		// 如果是ffmpeg可以转换的类型直接转码，否则先用mencoder转码成AVI
		if (type == 0) {
			System.out.println("直接将文件转为flv文件");
			status = processFLV(CONVERT_FILE_PATH, FLV_FILE_PATH, FFMPEG_PATH,SCREEN_SHOT_PATH);// 直接将文件转为flv文件
		} else if (type == 1) {
			String avifilepath = processAVI(CONVERT_FILE_PATH, AVI_FILE_PATH, MENCODE_PATH);
			if (avifilepath == null){
				return false;// avi文件没有得到
			}
			System.out.println("将转化后的AVI转为FLV");
			status = processFLV(CONVERT_FILE_PATH, FLV_FILE_PATH, FFMPEG_PATH,SCREEN_SHOT_PATH);// 将avi转为flv
		}
		return status;
	}

	
	
	/**
	 * @Description: 检查是否是文件
	 * @Author: Administrator
	 * @Tags: @param path
	 * @Tags: @return
	 * @Date: 2017年9月6日 上午11:46:47
	 * @return: boolean
	 */
	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * @Description: 检查视频文件类型
	 * @Author: Administrator
	 * @Tags: @param CONVERT_FILE_PATH
	 * @Tags: @return
	 * @Date: 2017年9月6日 上午11:12:02
	 * @return: int
	 */
	private static int checkContentType(String CONVERT_FILE_PATH) {
		String type = CONVERT_FILE_PATH.substring(CONVERT_FILE_PATH.lastIndexOf(".") + 1, CONVERT_FILE_PATH.length()).toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	

	/**
	 * @Description:文件转成AVI格式 
	 * 				对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
	 * @Author: Administrator
	 * @Tags: @param type 文件类型
	 * @Tags: @param CONVERT_FILE_PATH 原始输入文件路径
	 * @Tags: @param AVI_FILE_PATH 转换的AVI输出文件路径
	 * @Tags: @param MENCODE_PATH mencode转换文件的路径
	 * @Tags: @return
	 * @Date: 2017年9月6日 上午11:13:56
	 * @return: String
	 */
	private static String processAVI(String CONVERT_FILE_PATH, String AVI_FILE_PATH, String MENCODE_PATH) {
		
		System.out.println("processAVI ========= start");   
		
		List<String> commend = new ArrayList<String>();
		commend.add(MENCODE_PATH);
		commend.add(CONVERT_FILE_PATH);
		commend.add("-oac");
		commend.add("lavc");
		commend.add("-lavcopts");
		commend.add("acodec=mp3:abitrate=64");
		commend.add("-ovc");
		commend.add("xvid");
		commend.add("-xvidencopts");
		commend.add("bitrate=600");
		commend.add("-of");
		commend.add("avi");
		commend.add("-o");
		commend.add(AVI_FILE_PATH);
		
		try {  
			
			long startTime = System.currentTimeMillis();
			System.out.println("开始时间："+startTime);
			
			//调用线程命令启动转码
			Process videoProcess = new ProcessBuilder(commend).redirectErrorStream(true).start();
            
			new PrintStream(videoProcess.getInputStream()).start();
			    
			//等Mencoder进程转换结束，再调用ffmepg进程
			videoProcess.waitFor();
			            
			System.out.println("processAVI ========= end");   
			
			long endTime = System.currentTimeMillis();
			System.out.println("结束时间："+endTime);
			
			System.out.println("结束时间："+(endTime-startTime));
			
			return AVI_FILE_PATH;
			
		} catch (Exception e) {
			System.out.println("processAVI ERROR =========");   
			e.printStackTrace();
			return null;
		}
	}

	// 
	/**
	 * @Description:文件转成FLV格式 
	 * 				ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	 * @Author: Administrator
	 * @Tags: @param CONVERT_FILE_PATH 输入文件
	 * @Tags: @param FLV_FILE_PATH 输出文件
	 * @Tags: @param FFMPEG_PATH ffmepg文件路径
	 * @Tags: @return
	 * @Date: 2017年9月6日 上午11:16:17
	 * @return: boolean
	 */
	private static boolean processFLV(String CONVERT_FILE_PATH,String FLV_FILE_PATH,String FFMPEG_PATH,String SCREEN_SHOT_PATH) {

		if (!checkfile(CONVERT_FILE_PATH)) {
			System.out.println(CONVERT_FILE_PATH + " is not file");
			return false;
		}

		System.out.println("processFLV ========= start");
		
		List<String> commend = new ArrayList<String>();

		commend.add(FFMPEG_PATH);  
        commend.add("-i");  
        commend.add(CONVERT_FILE_PATH);  
        commend.add("-ab");  
        commend.add("128");  
        commend.add("-acodec");  
        commend.add("libmp3lame");  
        commend.add("-ac");  
        commend.add("1");  
        commend.add("-ar");  
        commend.add("22050");  
        commend.add("-r");  
        commend.add("29.97");  
        //高品质   
        commend.add("-qscale");  
        commend.add("6");  
        //低品质  
//      commend.add("-b");  
//      commend.add("512");  
        commend.add("-y");  
          
        commend.add(FLV_FILE_PATH);  

		try {
			Runtime runtime = Runtime.getRuntime();
			Process proce = null;
			// 视频截图命令，封面图。 10是代表第10秒的时候截图
			String cmd = "";
			String cut =  FFMPEG_PATH  + " -i " + CONVERT_FILE_PATH
					+ "   -y   -f   image2   -ss   10   -t   0.001   -s   600x500  "  + SCREEN_SHOT_PATH + "a.jpg";
			String cutCmd = cmd + cut;
			
			proce = runtime.exec(cutCmd);
			
			// 调用线程命令进行转码
			ProcessBuilder builder = new ProcessBuilder(commend);
			builder.command(commend);
			builder.start();
//			Process process = builder.start();

			//等待flv转换进程结束
//			process.waitFor();
			
			System.out.println("processFLV ========= end");
			
			return true;
			
		} catch (Exception e) {
			System.out.println("processFLV ERROR =========");
			e.printStackTrace();
			return false;
		}
	}

}



class PrintStream extends Thread 
{
    java.io.InputStream __is = null;
    public PrintStream(java.io.InputStream is) 
    {
        __is = is;
    } 

    public void run() 
    {
        try 
        {
            while(this != null) 
            {
                int _ch = __is.read();
                if(_ch != -1) 
                    System.out.print((char)_ch); 
                else break;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
    }
}



