<#import "/spring.ftl" as spring>

<#include "header.ftl">
<div>
	<div class="postsingle">
		<h2 class="posttitle"><span class="intitle">发表文章</span></h2>
        <div class="postentry">
        
        <form action="post.html" method="post">
			<p><label>标题：</label><@spring.formInput "article.title"/><@spring.showErrors ","/></p>
			
			
			<p><label>类别：</label><@spring.formInput "article.catelog"/><@spring.showErrors ","/></p>
			<script type="text/javascript" src="../jquery/js/jquery-1.4.2.min.js"></script>
			<script type="text/javascript" src="../jquery/js/jquery.combobox.js"></script>
			
			<p><@spring.formTextarea "article.content"/></p>
			<p><@spring.showErrors ","/></p>
			<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
			<script type="text/javascript">
			      	CKEDITOR.replace('content',{
			      		skin : 'office2003',
			      		language : 'zh-cn',
						filebrowserImageUploadUrl : '/admin/upload.html',
						filebrowserUploadUrl : '/admin/upload.html',
						filebrowserImageWindowWidth : '640',
        				filebrowserImageWindowHeight : '480',
        				filebrowserFlashUploadUrl : '/admin/upload.html',
        				on: {
        					instanceReady: function(ev) {
        						// catelog的提示功能 
            					jQuery("#catelog").combobox({
									values : [ ${catelogs} ]
								});
        					}
        				}
			      	});
			</script>

			<p><label>允许回复：</label>
				<@spring.formSingleSelect "article.allowReply" replyMap />
			</p>
			
			<p><input type="submit" value="提交" /></p>
			<#if id??>
				<input type="hidden" name="id" value="${id?c}" />
	    	</#if>
		</form>
        
        </div>
    </div>

</div>
<#include "footer.ftl">