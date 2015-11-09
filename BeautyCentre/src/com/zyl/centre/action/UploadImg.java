package com.zyl.centre.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zyl.centre.common.utils.CommonUtils;
import com.zyl.centre.entity.Imgsrc;
import com.zyl.centre.service.*;

public class UploadImg extends ActionSupport{
	 private List<File> file;
	 private List<String> fileFileName;
	 private List<String> fileContentType;
	 private HashMap<String, String> dataMap;
	 
	 
	 
	 public List<File> getFile() {
		return file;
	}




	public HashMap<String, String> getDataMap() {
		return dataMap;
	}




	public void setDataMap(HashMap<String, String> dataMap) {
		this.dataMap = dataMap;
	}




	public void setFile(List<File> file) {
		this.file = file;
	}




	public List<String> getFileFileName() {
		return fileFileName;
	}




	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}




	public List<String> getFileContentType() {
		return fileContentType;
	}




	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}




	@Override
		public String execute() throws Exception {
		 String root = ServletActionContext.getRequest().getRealPath("upload");
		 System.out.println(root+'\n');
		 
		 for(int i = 0; i<file.size(); i++){
			 InputStream is = new FileInputStream(file.get(i));
			 File destFile = new File(root, this.getFileFileName().get(i));
			 OutputStream os = new FileOutputStream(destFile);
			 byte[] buffer = new byte[400];
			 int length = 0;
			 while((length = is.read(buffer)) > 0)
			 {
				 os.write(buffer, 0, length);
			 }
			 is.close();
			 os.close();
		 }
		 

			return SUCCESS;
		}
	
	public void up() throws IOException{
		 String root = ServletActionContext.getRequest().getRealPath("upload");
		 System.out.println(root+'\n');
		 Map<String, Object> reMap = new HashMap<String, Object>();
//			System.out.println(user.getRealname()+user.getUsername());
			System.out.println(file.size());
			 InputStream is;
			 File destFile ;
			 OutputStream os;
		 for(int i = 0; i<file.size(); i++){
			 is = new FileInputStream(file.get(i));
			 destFile = new File(root, this.getFileFileName().get(i));
			 os = new FileOutputStream(destFile);
			 byte[] buffer = new byte[400];
			 int length = 0;
			 while((length = is.read(buffer)) > 0)
			 {
				 os.write(buffer, 0, length);
			 }
			 is.close();
			 os.close();
		 }
	//	 dataMap.put("ResultMassage", SUCCESS);
		 reMap.put("ResultMessage", CommonUtils.SUCCESS);
		 CommonUtils.toJson(ServletActionContext.getResponse(), reMap);
	}
	
}