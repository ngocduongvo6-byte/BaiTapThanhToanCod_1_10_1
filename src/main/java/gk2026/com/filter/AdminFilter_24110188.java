package gk2026.com.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import gk2026.com.model.*;
import java.io.IOException;

public class AdminFilter_24110188 implements Filter {
	public void doFilter(ServletRequest r, ServletResponse s, FilterChain c) throws IOException, ServletException {
		Users_24110188 u = (Users_24110188) ((HttpServletRequest) r).getSession().getAttribute("currentUser");
		if (u == null || u.getRole() == null || !"ADMIN".equals(u.getRole().getRoleName())) {
			((HttpServletResponse) s).sendError(403);
			return;
		}
		c.doFilter(r, s);
	}
}
