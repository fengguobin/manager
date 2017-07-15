package cn.edu.zhku.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileNameExtensionFilter;

import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class FileUitl {
	//存储表单信息
	private Map parameters = null;
	//存储上传文件信息
	private Map file = null;
	//最大上传文件大小
	private long max_size = 30 * 1024 * 1024;
	
	public FileUitl(){
		parameters = new HashMap();
		file = new HashMap();
	}
	
	//上传文件方法
	public int upload(HttpServletRequest request,String uploadPath)throws IOException{
		//实例化一个硬盘文件，用于配置上传组件ServletFileUpload
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		//设置上传文件时用于临时存放文件的内存大小，这里设置4kb，多于的部分将临时存放在硬盘
		diskFileItemFactory.setSizeThreshold(4096);
		//用以上实例化上传组件
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		//设置最大上传文件大小
		fileUpload.setFileSizeMax(max_size);
		//解决路径和文件编码问题
		fileUpload.setHeaderEncoding("utf-8");
		List fileList  = null;
		try {
			//fileList = fileUpload.parseRequest(request);
			fileList = fileUpload.parseRequest((RequestContext) request);
		} catch (FileUploadException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Iterator fileIterator = fileList.iterator();
		while (fileIterator.hasNext()) {
			FileItem fileItem = null;
			String sourceFilePath = null;
			String sourceFileName = null;
			String fileExt = null;
			String filePath = null;
			String realPath = null;
			long size = 0;
			//开始迭代遍历
			fileItem = (FileItem)fileIterator.next();
			//如果是上传文件而不是表单信息
			if (!fileItem.isFormField()) {
				//得到源文件的完整路径
				sourceFilePath = fileItem.getName();
				size = fileItem.getSize();
				if (!sourceFilePath.equals("") && size != 0) {
					//得到去除路径的文件名
					sourceFileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("\\") + 1);
					//得到文件的扩展名
					fileExt = sourceFileName.substring(sourceFileName.lastIndexOf(".")+1);
					//以当前系统时间保存上传文件
					long systemTime = System.currentTimeMillis();
					filePath = uploadPath + "/" + systemTime + "." + fileExt;
					realPath = request.getServletContext().getRealPath(filePath);
					try {
						fileItem.write(new File(realPath));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return 0;
					}
					file.put("size",String.valueOf(size));
					file.put("filePath",filePath);
					file.put("fileName",sourceFileName);
				}
			}else {
				//如果不是上传文件而是表单信息，则将表单信息保存在paramters
				String fileName = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");
				parameters.put(fileName,value);
			}
		}
		return 1;
	}
	
	
	//下载文件的方法，参数map存储下载的信息，包括文件地址filePath和fileName
	public int download(ServletContext servletContext,HttpServletResponse response,Map file)throws IOException{
		java.io.BufferedInputStream biStream = null;
		java.io.BufferedOutputStream bosStream = null;
		try {
			String filePath = (String) file.get("filePath");
			String realPath = servletContext.getRealPath(filePath);
			long fileLength = new File(realPath).length();
			response.setHeader("Content-disposition", "attachment;filename = " + new String(((String)file.get("fileName")).getBytes("utf-8"),"iso8859-1"));
			response.setHeader("Context-Length", String.valueOf(fileLength));
			biStream = new BufferedInputStream(new FileInputStream(realPath));
			bosStream = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while ((bytesRead = biStream.read(buff,0,buff.length)) != 1) {
				bosStream.write(buff, 0 , bytesRead);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}finally{
			if (biStream != null) {
				biStream.close();
			}
			if (bosStream != null) {
				bosStream.close();
			}
		}
		return 1;
	}
	
	public Map getFile(){
		return file;
	}
	public Map getParameter(){
		return parameters;
	}
	public void setMax_size(long max_size){
		this.max_size = max_size;
	}
	
}
