<#import "/spring.ftl" as spring>

<#include "header.ftl">

<#escape x as x?html>
<div id="content">
	<div class="postsingle">
		    <h2 class="posttitle"><span class="intitle">${article.title}</span></h2>
            <p class="postmeta">作者: <strong>${article.authorName}</strong> 日期: ${article.date?string("yyyy-MM-dd HH:mm:ss")} </p>
		    
		    <div class="postentry">
		    <#noescape>${article.content}</#noescape>
		  	</div>
            
            <div class="metadata">
            <p class="categories">类别: <a href="/?catelog=${article.encodedCatelog}" title="View all posts in ${article.catelog}" rel="category">${article.catelog}</a></p>
            <#if admin??>
            <p class="tags">操作: <a href="/admin/post.html?id=${article.articleID?c}" >编辑</a>, <a href="/admin/delete.html?id=${article.articleID?c}" onclick="return confirm('确定要删除 ${article.title}?')" >删除</a></p>
            </#if>
            </div>

	</div>
    <div class="postsingle">
  			
    		
    		<h3 id="comments">回复</h3>
    		<ul class="commentlist" id="singlecomments">
	    		<#if replies??>
	    		<#assign i=0 />
	    		<#list replies as reply>
	    		<#if i % 2 == 0>
					<li class="comment even thread-even depth-1" id="comment-1">
				<#else>
					<li class="comment odd thread-odd depth-1" id="comment-1">
				</#if>
					<div id="div-comment-1" class="comment-body">
						<div class="comment-author vcard">
						<#if admin??>
						<a href="/admin/deleteReply.html?articleID=${article.articleID?c}&replyID=${reply.replyID?c}"><img src="del.gif"/></a>
						</#if>
						<a name="reply${reply.replyID?c}" id="reply${reply.replyID?c}"></a><cite class="fn"><strong>${reply.authorName}</strong></cite> <span class="says">说:</span></div>
						<div class="comment-meta commentmetadata">${reply.date?string("yyyy-MM-dd HH:mm:ss")}</div>
						
						<div class="reply">${reply.content}</div>
					</div>
				</li>
				<#assign i=i+1 />
				</#list>
				</#if>
			</ul>
			
			
    </div>
    
    <#if article.allowReply >
		<#include "reply.ftl">
	</#if>

</div> <!-- end of #content -->
</#escape>

<#include "sidebar.ftl">


<#include "showtail.ftl">

<#include "footer.ftl">