<div id="respond"><h3>发表回复</h3>
<form method="post" action="reply.html" id="commentform" onsubmit="return false;">
	<p><input type="text" name="author" id="author" value="${userName}" size="22" tabindex="1" /><label for="author">昵称</label></p>
	<p><textarea name="content" id="comment" cols="10" rows="10" tabindex="2"></textarea></p>
	  
	  <div id="recaptcha_div"></div>
	  <script type="text/javascript" src="../jquery/js/jquery-1.4.2.min.js"></script>
	  <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
      <script type="text/javascript">
      	jQuery(function() {
           Recaptcha.create("${blog.recaptchaPublicKey}", "recaptcha_div",
               {theme: "clean"});
      	});
      </script>
	<p><div id="statusbar" style="color:red"></div></p>
	<p><input name="submit" type="submit" id="submit" tabindex="3" value="提交" /></p>
	<p><input name="articleID" type="hidden" value="${article.articleID?c}" /></p>
</form>

<script type="text/javascript" src="../jquery/js/jquery.form.js"></script>
<script type="text/javascript">
jQuery(function(){
	jQuery("#submit").removeAttr("disabled");
	
	var options = {
		success: function(data){
			if(data == "success") {
				jQuery("#statusbar").html(data);
				 window.location.reload();
				return;
			}
			jQuery("#statusbar").html(data);
			Recaptcha.reload();
			jQuery("#submit").removeAttr("disabled");
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			jQuery("#statusbar").html("服务器发生错误");
			Recaptcha.reload();
			jQuery("#submit").removeAttr("disabled");
		}
	};
	jQuery("#commentform").submit(function(){
		jQuery("#submit").attr({"disabled":"disabled"});
		jQuery("#statusbar").html("正在提交回复...");
		jQuery(this).ajaxSubmit(options);
		return false;
	}); 
});
</script>
</div>