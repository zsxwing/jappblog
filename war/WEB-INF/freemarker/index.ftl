<#import "/spring.ftl" as spring>

<#include "header.ftl">
<div id="content">
    <#escape x as x?html>
	<#if articles??>
		<#list articles as article>
			<div class="postindex">
				<h2 class="posttitle"><a href="/show.html?id=${article.articleID?c}">${article.title}</a></h2>
		        <p class="postmeta">作者: <strong>${article.authorName}</strong> 更新: ${article.date?string("yyyy-MM-dd HH:mm:ss")} 发布: ${article.createDate?string("yyyy-MM-dd HH:mm:ss")}</p>
			    
			    <div class="postentry">
			    <#noescape>${article.summary}</#noescape>
			  	</div>
		        
		        <div class="metadata">
		        <p class="categories">类别: <a href="/?catelog=${article.encodedCatelog}" title="View all posts in ${article.catelog}" rel="category">${article.catelog}</a></p>
		        </div>
			</div>
		</#list>
	</#if>
	</#escape>
	<#if morelink??>
	<div class="postindex">
		<div class="more" ><a href="${morelink}">更多文章</a></div>
	</div>
	</#if>
</div> <!-- end of #content -->

<#include "sidebar.ftl">

<#include "footer.ftl">