package com.tmount.servlet.user;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;

import com.tmount.business.user.service.UserService;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.system.SpringApplicationContextFactory;
/**
 * 个人头像文件上传
 * @author dell
 *
 */

public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ApplicationContext applicationContext = SpringApplicationContextFactory.newInstance();
		userService = applicationContext.getBean(UserService.class);
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getSession().getServletContext().getRealPath("/upload/emp/");
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
			//jobj.put("result", 1);
			UsAccount usAccount=new UsAccount();
			usAccount.setAccount_name(fileName.split("\\.")[0]);
			usAccount.setPic_name(fileName);
			userService.updatePicName(usAccount);
			out.print(fileName);
		} catch (Exception e1) {
			e1.printStackTrace();
			out.print("0");
		}
		out.flush();
		out.close();
	}
}
