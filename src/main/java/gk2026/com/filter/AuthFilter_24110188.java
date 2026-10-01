package gk2026.com.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter_24110188 implements Filter {
	public void doFilter(ServletRequest r, ServletResponse s, FilterChain c) throws IOException, ServletException {
		HttpServletRequest q = (HttpServletRequest) r;
		if (q.getSession().getAttribute("currentUser") == null) {
			((HttpServletResponse) s).sendRedirect(q.getContextPath() + "/login");
			return;
		}
		c.doFilter(r, s);
	}
}
