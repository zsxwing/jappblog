<div id="sidebar">
<#escape x as x?html>
<ul class="widgets">
	<li id="operator" class="widget widget_recent_entries">		
		<h2>操作</h2>		
	    <ul>
	    	<#if login??>
	    		<li><a href="${login}">登陆</a></li>
			</#if>
        	<#if logout??>
	    		<li><a href="${logout}">退出</a></li>
			</#if>
			<#if admin??>
	    		<li><a href="/admin/post.html">发表文章</a></li>
	    		<li><a href="/admin/setup.html">设置博客</a></li>
	    		<li><a href="https://appengine.google.com/">管理GAE</a></li>
	    		<#if blog.evernoteDeveloperToken?has_content >
	    		    <li><a href="/admin/evernote_note_list.html">从Evernote选择文章发布</a></li>
	    		</#if>
			</#if>
	    </ul>
	</li>
	<script>
	var operator=document.getElementById("operator");
	var host=window.location.host || document.domain;
	if(host.indexOf("appspot.com")<0 && host.indexOf("localhost:8888")<0) {
	    operator.style.display="none";
	}
	</script>
	<li class="widget widget_recent_entries">		
		<h2>文章类别</h2>
		<script type="text/javascript" src="/wp-cumulus/swfobject.js"></script>
        <script type="text/javascript" src="/wp-cumulus/tagcloud.js"></script>
        <div id="tagclound_content"></div>
        <script type="text/javascript">
            var cats=[];
            var sizes=[];
            <#if catelogs??>
                <#list catelogs as catelog>
                    cats.push("${catelog.encodedName}");
                    sizes.push(${catelog.count});
                </#list>
            </#if>
            var tagCloud=new TagCloud(cats,sizes);
            setTimeout("tagCloud.init()",200);
        </script>	
	    <ul>
	    	<#if catelogs??>
	    		<#list catelogs as catelog>
	    			<li>
	    			<div style="float:left;width:270px;"><a href="/?catelog=${catelog.encodedName}">${catelog.name}(${catelog.count})</a></div>	
	    			<#if admin??>
	    			<div><a href="/admin/deleteCatelog.html?catelog=${catelog.encodedName}" onclick="return confirm('确定要删除 ${catelog.name}?')"><img style="border-width:0;" src="/del.gif"/></a></div>
	    			</#if>
	    			<div style="clear:both;"></div>
	    			</li>
	    		</#list>
	    	</#if>
	    </ul>
	</li>
	<li class="widget widget_recent_entries">		
		<h2>最新的回复</h2>		
	    <ul>
	    	<#if latestReplies??>
	    		<#list latestReplies as reply>
	    			<li>
	    			<strong>${reply.authorName}</strong>说<a href="/show.html?id=${reply.articleID?c}#reply${reply.replyID?c}" >${reply.summaryReply}</a>
	    			</li>
	    		</#list>
	    	</#if>
	    </ul>
	</li>
	<li class="widget widget_recent_entries">      
        <h2><a href="/rss.html" rel="alternate" type="application/rss+xml">订阅<img class="sub-button" src="/theme/macpress/images/feedicon.png" alt="" /></a></h2>      
        <ul>
            <li>
            <a href="http://fusion.google.com/add?source=atgs&feedurl=http%3A//${blog.host}/rss.html" target="_blank"><img src="http://buttons.googlesyndication.com/fusion/add.gif" border="0" alt="Add to Google"></a>
            </li>
            <li>
            <a target="_blank" title="订阅到鲜果 RSS阅读器" href="http://xianguo.com/subscribe?url=http%3A%2F%2F${blog.host}%2Frss.html"><img src="http://xgres.com/static/images/sub/sub_XianGuo_06.gif" border="0" alt="鲜果阅读器订阅图标" /></a>
            </li>
            <li>
            <a target="_blank" href="http://reader.youdao.com/b.do?keyfrom=bookmarklet&url=http%3A%2F%2F${blog.host}%2Frss.html"><img src="http://reader.youdao.com/images/feed-btn-1.gif" border="0" alt="订阅到有道阅读" /></a>
            </li>
            <li>
            </li>
        </ul>
    </li>
	
</ul>
</#escape>
</div>
