<#import "/spring.ftl" as spring>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置博客信息</title>
</head>
<body>
<form method="post" action="setup.html">
博客名称:<@spring.formInput "blog.name"/><@spring.showErrors ","/><br />
博客域名(如example.appspot.com，在生成RSS时需要使用):<@spring.formInput "blog.host"/><@spring.showErrors ","/><br />
博客简介:<@spring.formTextarea "blog.description"/><@spring.showErrors ","/><br />
收集访问数据的跟踪代码（会添加到每个页面的head中）:<@spring.formTextarea "blog.trackSnippet"/><@spring.showErrors ","/><br />
Evernote Developer Toekn(如果需要使用从Evernote选择文章发布的功能，必须设置，点<a href="https://www.evernote.com/api/DeveloperToken.action" target="_blank">这里</a>可以申请你个人使用的Evernote Developer Token)<br/>
<@spring.formInput "blog.evernoteDeveloperToken"/><@spring.showErrors ","/>
<p>请到<a href="https://www.google.com/recaptcha/admin/create">recaptcha</a>申请你站点使用的public key和private key，并填写到下面的表单中</p>
<p>这是验证码的功能，有兴趣可以看看<a href="http://www.alibuybuy.com/23532.html">验证码的故事</a></p>
recaptcha Public Key:<@spring.formInput "blog.recaptchaPublicKey"/><@spring.showErrors ","/><br />
recaptcha Private Key:<@spring.formInput "blog.recaptchaPrivateKey"/><@spring.showErrors ","/><br />
<input type="submit" value="提交" />
</form>
</body>
</html>