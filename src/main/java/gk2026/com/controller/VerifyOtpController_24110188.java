package gk2026.com.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import gk2026.com.model.*;
import gk2026.com.service.*;
import gk2026.com.service.impl.*;
import gk2026.com.util.*;
import java.io.*;

public class VerifyOtpController_24110188 extends HttpServlet {
	private final UserService_24110188 service = new UserServiceImpl_24110188();

	protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		r.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(r, s);
	}

	protected void doPost(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
		Users_24110188 u = (Users_24110188) r.getSession().getAttribute("pendingUser");
		if (u != null && "resend".equals(r.getParameter("action"))) {
			u.setCode("%06d".formatted(new java.util.Random().nextInt(1000000)));
			try {
				MailUtil_24110188.send(u.getEmail(), u.getCode());
			} catch (Exception ignored) {
			}
			r.setAttribute("message", "Đã gửi lại OTP.");
			doGet(r, s);
			return;
		}
		if (u != null && u.getCode().equals(r.getParameter("code"))) {
			u.setStatus(true);
			service.register(u);
			r.getSession().removeAttribute("pendingUser");
			s.sendRedirect(r.getContextPath() + "/login");
		} else {
			r.setAttribute("error", "OTP không đúng.");
			doGet(r, s);
		}
	}
}
