package com.etc.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.news.biz.NewsBiz;
import com.etc.news.bizimpl.NewsBizImpl;
import com.etc.news.entity.News;
import com.etc.news.entity.Type;
import com.etc.news.entity.User;
import com.etc.news.util.FileUp;
import com.etc.news.util.RenamePolicyCos;
import com.oreilly.servlet.MultipartRequest;

public class DoSaveNews extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoSaveNews() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 设定当前文件上传的目标文件夹
		String savePath = this.getServletContext().getRealPath("/upload");

		// 设定文件上传的最大容量 100M
		int maxSize = 10 * 10 * 1024 * 1024;
		// 创建文件上传请求域对象
		MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "utf-8",
				new RenamePolicyCos());

		// 获取表单传递过来的值
		String newsId = multipartRequest.getParameter("newsid");
		String newstitle = multipartRequest.getParameter("newstitle");
		String newscontent = multipartRequest.getParameter("newscontent");
		String userId = multipartRequest.getParameter("newsauthor");
		String typeId = multipartRequest.getParameter("newstype");
		String files = "";
		try {
			files = FileUp.uploadfiles(multipartRequest);
			if (files == null) {
				files = multipartRequest.getParameter("newsimage");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();

		// 根据news_id来修改相应的数据

		NewsBiz biz = new NewsBizImpl();
		// 将上述数据封装成news对象
		News news = new News();
		news.setNews_id(Integer.valueOf(newsId));
		news.setNews_title(newstitle);
		news.setNews_content(newscontent);
		// 设置上传的新闻图片名称
		news.setNews_image(files);
		// 作者
		User user = new User();
		user.setUser_id(Integer.valueOf(userId));
		news.setNews_author(user);
		// 新闻类型
		Type type = new Type();
		type.setType_id(Integer.valueOf(typeId));
		news.setNews_type(type);

		// 时间
		Date date = new Date(); // util date 即有时间又有日期
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		news.setNews_time(df.format(date));

		if (biz.updateNewsById(news)) {
			out.print("<script>alert('保存成功!!!');location.href='DoQueryNewsPage';</script>");

		} else {
			out.print("<script>alert('保存失败!!!');history.go(-1);</script>");

		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
