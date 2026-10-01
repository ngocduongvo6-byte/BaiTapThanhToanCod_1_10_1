package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class LogoutController_24110188 extends HttpServlet {
	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws IOException {
		r.getSession().invalidate();
		s.sendRedirect(r.getContextPath() + "/home");
	}
}
