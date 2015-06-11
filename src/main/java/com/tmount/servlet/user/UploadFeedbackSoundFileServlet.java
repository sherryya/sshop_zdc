package com.tmount.servlet.user;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * 反馈内容文件上传
 * @author dell
 *
 */
public class UploadFeedbackSoundFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getSession().getServletContext().getRealPath("/upload/feedback/");
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		path=path+"/"+year+"/"+month+"/"+day+"/";
		System.out.println(path);
		File file =new File(path);    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdirs();    
		}
		System.out.println(path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileName ="";
		try {
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item : list){
				String name = item.getName();
				fileName = name.substring(name.lastIndexOf("\\") + 1);
				InputStream is = item.getInputStream();
				File f = new File(path+"/"+ fileName);
				FileOutputStream fos = new FileOutputStream(f);
				int hasRead = 0;
				byte[] buf = new byte[1024];
				while((hasRead = is.read(buf)) > 0){
					fos.write(buf, 0, hasRead);
				}
				fos.close();
				is.close();
			}
			out.print("/"+year+"/"+month+"/"+day+"/"+ fileName);
		} catch (Exception e1) {
			e1.printStackTrace();
			out.print("0");
		}
		out.flush();
		out.close();
	}
}
