package cn.edu.zhku.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.net.www.content.audio.wav;
import cn.edu.zhku.service.CommonService;
import cn.edu.zhku.util.FileUitl;

public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DownLoadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		CommonService cs = new CommonService();
		//得到下载文件的信息，map对象，封装另外filepath和filename的信息
		Map file = cs.getDownLoadFile(id);
		//实现文件下载动作
		FileUitl fu = new FileUitl();
		int r = fu.download(this.getServletContext(), response, file);
		//更新下载次数
		if (r == 1) {
			cs.updateHits(id);
		}
	}
}
