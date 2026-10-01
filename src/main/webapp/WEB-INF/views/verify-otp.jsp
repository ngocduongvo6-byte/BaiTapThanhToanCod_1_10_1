<%@ page contentType="text/html;charset=UTF-8"%><h1>Xác thực OTP</h1>
<p class="error">${error}</p>
<p>${message}</p>
<form method="post">
	<input name="code" maxlength="6" required placeholder="OTP 6 số">
	<button>Xác thực</button>
</form>
<form method="post">
	<input type="hidden" name="action" value="resend">
	<button>Gửi lại OTP</button>
</form>
