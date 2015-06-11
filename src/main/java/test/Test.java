package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmount.util.SendReport;

public class Test {
	Socket socket = null;
	BufferedReader br = null;
	PrintWriter pw = null;
	public static void main(String args[]) {
		new Test().sendData("2E410E0202000000000000000000000000B02E4103020300BA","1234567890","0","CAN","CAR");
	}
	private void sendData(String sendContent,String sendIP,String sendType,String sendHead,String sendTerminal)
	{
		SendReport sendReport=new SendReport();
		sendReport.setSendContent(sendContent);
		sendReport.setSendIP(sendIP);
		sendReport.setSendTerminal(sendTerminal);
		sendReport.setSendType(sendType);
		sendReport.setSendHead(sendHead);
		Gson gson=new Gson();
	    Type listType = new TypeToken<SendReport>(){}.getType();
	    String json=gson.toJson(sendReport, listType);
	    
		try {
			if (socket == null) {
/*				socket.close();
				socket = null;*/
				socket = new Socket("192.168.1.106", 8004);
			}
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			pw.println(json);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
