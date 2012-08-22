<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#setting time_zone="GMT+08" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" /> 
	<link rel="stylesheet" href="/theme/macpress/style.css" type="text/css" media="screen" />
	<title>${title?html}</title>
	<#if show??>
		<#include "showheader.ftl">
	</#if>
	<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="/rss.html" />

    <#if blog.trackSnippet??>
        ${blog.trackSnippet}
    </#if>
</head>

<body>
<!-- 这里可以加背景
<body style="background:url(<?php bloginfo('template_directory'); ?>/background/<?php echo $mcp_background_img; ?>) top left no-repeat #fff;">
-->
<div class="menuwrap">
	 <div class="menubar">
	      <!-- <ul class="pagelist">
	       <li class="page_item page-item-2"><a href="http://wp-themes.com/?page_id=2" title="About">About</a></li>
	       </ul> -->
	       <ul class="subscribe">
	       <li><strong><a href="/rss.html" rel="alternate" type="application/rss+xml"><img class="sub-button" src="/theme/macpress/images/feedicon.png" alt="" />订阅</a></strong></li>
	       </ul>
	 </div>
</div> <!-- end of #menuwrap -->

<div id="outline">
	<div id="header">
	     <div class="logo">
         <h1 class="blogtitle">{<a href="/">${blog.name}</a>}</h1>
         <p class="description">${blog.description}</p>
         </div>
	</div> <!-- end of #header -->

<div class="left-category-bar">
</div>
<div class="category-bar">
     <div class="cat-menubar">
	      <ul class="search">
	      <li>
	          <form action="http://www.google.com.hk/search" method="get">
	          <p><input class="searchbox" name="q" type="text" value="Google..." onfocus="if (this.value == 'Google...') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Google...';}" /></p> 
	          <input value="${blog.host}" name="sitesearch" type="hidden">
	          </form>
          </li>
	      </ul>
	 </div>
</div> <!-- end of #category-bar -->
<div class="right-category-bar">
</div>

<div id="wrapper">