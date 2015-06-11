package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**    
 * MyEclipse10 插件配置代码生成器 
 */      
public class tt       
{       
    public tt()       
    {       
    }    
    private String voipAccount;//账号
    private String voipPwd;//密码
    private String nickname;//昵称
    private String pic;//图片地址
    private String roomId;//房间号
    public void print(String path)       
    {       
        List<String> list = getFileList(path);       
        if (list == null)       
        {       
            return;       
        }       
        int length = list.size();       
        for (int i = 0; i < length; i++)       
        {       
            String result = "";       
            String thePath = getFormatPath(getString(list.get(i)));       
            File file = new File(thePath);       
            if (file.isDirectory())       
            {       
                String fileName = file.getName();       
                if (fileName.indexOf("_") < 0)       
                {       
                    print(thePath);       
                    continue;       
                }       
                String[] filenames = fileName.split("_");       
                String filename1 = filenames[0];       
                String filename2 = filenames[1];       
                result = filename1 + "," + filename2 + ",file:/" + path + "/"      
                        + fileName + "\\,4,false";       
                System.out.println(result);       
            } else if (file.isFile())       
            {       
                String fileName = file.getName();       
                if (fileName.indexOf("_") < 0)       
                {       
                    continue;       
                }       
                int last = fileName.lastIndexOf("_");// 最后一个下划线的位置       
                String filename1 = fileName.substring(0, last);       
                String filename2 = fileName.substring(last + 1, fileName       
                        .length() - 4);       
                result = filename1 + "," + filename2 + ",file:/" + path + "/"      
                        + fileName + ",4,false";       
                System.out.println(result);       
            }       
        }       
    }       
    public List<String> getFileList(String path)       
    {       
        path = getFormatPath(path);       
        path = path + "/";       
        File filePath = new File(path);       
        if (!filePath.isDirectory())       
        {       
            return null;       
        }       
        String[] filelist = filePath.list();       
        List<String> filelistFilter = new ArrayList<String>();       
        for (int i = 0; i < filelist.length; i++)       
        {       
            String tempfilename = getFormatPath(path + filelist[i]);       
            filelistFilter.add(tempfilename);       
        }       
        return filelistFilter;       
    }       
    public String getString(Object object)       
    {       
        if (object == null)       
        {       
            return "";       
        }       
        return String.valueOf(object);       
    }       
    public String getFormatPath(String path)       
    {       
        path = path.replaceAll("\\\\", "/");       
        path = path.replaceAll("//", "/");       
        return path;       
    }
    
    public static void getRand20(StringBuffer buff) {
    	Random rand = new Random();
		Long longValue1 = new Long((long)(rand.nextDouble() * 10000000000L));
		Long longValue2 = new Long((long)(rand.nextDouble() * 10000000000L));
		buff.append(longValue1.toString());
		buff.append(longValue2.toString());
		
		while (true) {
			if (buff.length() < 20) {
				buff.append("0");
			} else {
				break;
			}
		}
    }
    
    public static void main(String[] args)       
    {
    	socket();
    }     
    
    
    
    private static	Socket socket = null;
    private static BufferedReader br = null;
    private static PrintWriter pw = null;
    
    private static void socket()
    {
    	connectionToServer("125.211.221.231",10094);
		try {
			socket.sendUrgentData(0xFF);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println("霏霏城");
		pw.flush();
		try {
			socket.close();
			br.close();
			pw.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	private static void connectionToServer(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println("new socket......");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}  
