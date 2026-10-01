package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class AdminController_24110188 extends HttpServlet {
	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		r.getRequestDispatcher("/WEB-INF/views/admin/index.jsp").forward(r, s);
	}
}
