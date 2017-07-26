package cn.edu.zhku.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.service.AdminService;
import cn.edu.zhku.util.FileUtil;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public AdminServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//分析文件路径，根据路径进行处理
		String requestPath=request.getRequestURI();
		int i=requestPath.lastIndexOf('/');
		String path=requestPath.substring(i);
		RequestDispatcher rd=null;
		//创建模型对象
		AdminService as=new AdminService();
		if(path.equals("/add")){
			//添加文件操作
			String uploadPath="/software";
			FileUtil fu=new FileUtil();
			int r=fu.upload(request, uploadPath);
			//返回操作结果result.jsp
			if(r==1 && as.add(fu.getFile(),fu.getParameters())==1){
				request.setAttribute("result", "添加成功");
			}else{
				request.setAttribute("result", "添加失败");
			}
			rd=request.getRequestDispatcher("/admin/result.jsp");
			rd.forward(request, response);
		}else if(path.equals("/update")){
			//¸更新文件
			String id=request.getParameter("id");
			Map file=as.getById(id);
			request.setAttribute("file", file);
			rd=request.getRequestDispatcher("/admin/update.jsp");
			rd.forward(request, response);
		}else if(path.equals("/dealUpdate")){
			//更新上传文件和更新文件信息
			int result=0;
			String uploadPath="/software";
			FileUtil fu=new FileUtil();
			int r=fu.upload(request, uploadPath);
			if(r==1){
			  Map file=(Map)fu.getFile();
			  Map parameters=(Map)fu.getParameters();
			  if(!file.isEmpty()){
				//更新上传文件
				//获取绝对路径
				String realPath=this.getServletContext().getRealPath((String)parameters.get("filePath"));
				//删除原文件，更新数据库中文件路径和文件名
				result=as.updateFile(realPath,file,parameters);
			  }
			  //更新文件信息
			  result=as.updateInfo(parameters);
			}
			//返回结果文件result.jsp
			if(result==1){
				request.setAttribute("result", "更新成功");
			}else{
				request.setAttribute("result", "¸更新失败");
			}
			rd=request.getRequestDispatcher("/admin/result.jsp");
			rd.forward(request, response);
			
		}else if(path.equals("/delete")){
			//删除文件及信息
			
		}else if(path.equals("/list")){
			//文件分页显示，带分页处理
			// 获取显示页数
			String page = request.getParameter("page");
			//当前页数
			int curPage = 0; 
			//没有获取page值
			if (page == null || page.length() < 1) { 
				curPage = 1;
			} else {
				curPage = Integer.parseInt(page);
			}
            PageBean pageBean=as.list(curPage);
            request.setAttribute("pageBean", pageBean);
            rd=request.getRequestDispatcher("/admin/list.jsp");
			rd.forward(request, response);
		}
	}
}
