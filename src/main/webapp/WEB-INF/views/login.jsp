<%@ page contentType="text/html;charset=UTF-8"%><h1>Đăng nhập</h1>
<p class="error">${error}</p>
<form method="post">
	<input type="hidden" name="redirect" value="${redirect}">
	<label>Username <input name="username" required></label><label>Password
		<input type="password" name="password" required>
	</label>
	<button>Đăng nhập</button>
</form>
<a href="register">Đăng ký</a>
