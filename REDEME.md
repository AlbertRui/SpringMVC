
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Spring MVC入门 - AlbertRui - 博客园</title>
<link type="text/css" rel="stylesheet" href="/bundles/blog-common.css?v=ON3Mxdo4-HlSMqbNDBZXhFIcGLon3eZDvU8zBESgwkk1"/>
<link id="MainCss" type="text/css" rel="stylesheet" href="/skins/BOOK/bundle-BOOK.css?v=qzznqq7AzRkoka6GRibxinoTgveiXem5TPZDQ_eQWN41"/>
<link id="mobile-style" media="only screen and (max-width: 767px)" type="text/css" rel="stylesheet" href="/skins/BOOK/bundle-BOOK-mobile.css?v=2kvKmvRY2V7A8icv7UeoLCg33YxjDCYf0xhrzgD2C1k1"/>
<link title="RSS" type="application/rss+xml" rel="alternate" href="http://www.cnblogs.com/albertrui/rss"/>
<link title="RSD" type="application/rsd+xml" rel="EditURI" href="http://www.cnblogs.com/albertrui/rsd.xml"/>
<link type="application/wlwmanifest+xml" rel="wlwmanifest" href="http://www.cnblogs.com/albertrui/wlwmanifest.xml"/>
<script src="//common.cnblogs.com/scripts/jquery-2.2.0.min.js"></script>
<script type="text/javascript">var currentBlogApp = 'albertrui', cb_enable_mathjax=false;var isLogined=true;</script>
<script src="/bundles/blog-common.js?v=O-NTEmnhjbG7lSYLc3yeqkrVxfMJyY9iXf4xyjlKikw1" type="text/javascript"></script>
</head>
<body>
<a name="top"></a>

<script type="text/javascript">
	try {
		if (screen.availWidth > 1200) {
			document.getElementById("MainCss").href = '/Skins/BOOK/style2.css';
		}
	} catch (e) { }
</script>

<div id="header">
<div id="HeaderTitle">
<div id="Title">
<a id="Header1_HeaderTitle" class="headermaintitle" href="http://www.cnblogs.com/albertrui/">AlbertRui</a>
</div>
<div id="subTitle"></div>
</div>
</div>

<div id="main">
	
<div id="post_detail">
	<div class="post">
		<h2>
			<a id="cb_post_title_url" href="http://www.cnblogs.com/albertrui/p/8302170.html">Spring MVC入门</a>
		</h2>
		<div id="cnblogs_post_body" class="blogpost-body"><h2>一、Spring MVC概述：</h2>
<p>　　Spring MVC是Spring提供的一个强大而灵活的web框架。借助于注解，Spring MVC提供了几乎是POJO的开发模式，使得控制器的开发和测试更加简单。这些控制器一般不直接处理请求，而是将其委托给Spring上下文中的其他bean，通过Spring的依赖注入功能，这些bean被注入到控制器中。</p>
<p>　　Spring MVC主要由DispatcherServlet、处理器映射、处理器(控制器)、视图解析器、视图组成。他的两个核心是两个核心：</p>
<p>　　处理器映射：选择使用哪个控制器来处理请求 <br>
　　视图解析器：选择结果应该如何渲染</p>
<p>　　通过以上两点，Spring MVC保证了如何选择控制处理请求和如何选择视图展现输出之间的松耦合。</p>
<h2>二、SpringMVC运行原理</h2>
<h2 id="2springmvc运行原理"><a name="t1"></a></h2>
<p><img title="" src="http://img.blog.csdn.net/20160427094830780" alt="这里写图片描述"></p>
<p>　　(1) Http请求：客户端请求提交到DispatcherServlet。 <br>
　　(2) 寻找处理器：由DispatcherServlet控制器查询一个或多个HandlerMapping，找到处理请求的Controller。 <br>
　　(3) 调用处理器：DispatcherServlet将请求提交到Controller。 <br>
　　(4)(5)调用业务处理和返回结果：Controller调用业务逻辑处理后，返回ModelAndView。 <br>
　　(6)(7)处理视图映射并返回模型： DispatcherServlet查询一个或多个ViewResoler视图解析器，找到ModelAndView指定的视图。 <br>
　　(8) Http响应：视图负责将结果显示到客户端。</p>
<h2>三、SpringMVC接口解释</h2>
<h2 id="3springmvc接口解释"><a name="t2"></a></h2>
<p>（1）DispatcherServlet接口： <br>
Spring提供的前端控制器，所有的请求都有经过它来统一分发。在DispatcherServlet将请求分发给Spring Controller之前，需要借助于Spring提供的HandlerMapping定位到具体的Controller。 <br>
（2）HandlerMapping接口： <br>
能够完成客户请求到Controller映射。 <br>
（3）Controller接口： <br>
需要为并发用户处理上述请求，因此实现Controller接口时，必须保证线程安全并且可重用。 <br>
Controller将处理用户请求，这和Struts 
Action扮演的角色是一致的。一旦Controller处理完用户请求，则返回ModelAndView对象给DispatcherServlet前端控制器，ModelAndView中包含了模型（Model）和视图（View）。
 <br>
从宏观角度考虑，DispatcherServlet是整个Web应用的控制器；从微观考虑，Controller是单个Http请求处理过程中的控制器，而ModelAndView是Http请求过程中返回的模型（Model）和视图（View）。 <br>
（4）ViewResolver接口： <br>
Spring提供的视图解析器（ViewResolver）在Web应用中查找View对象，从而将相应结果渲染给客户</p>
<h2>四、DispatcherServlet</h2>
<h2 id="4dispatcherservlet"><a name="t3"></a></h2>
<p>是整个Spring MVC的核心。它负责接收HTTP请求组织协调Spring MVC的各个组成部分。其主要工作有以下三项： <br>
 （1）截获符合特定格式的URL请求。 <br>
 （2）初始化DispatcherServlet上下文对应WebApplicationContext，并将其与业务层、持久化层的WebApplicationContext建立关联。 <br>
 （3）初始化Spring MVC的各个组成组件，并装配到DispatcherServlet中。</p>
<h2>五、SpringMVC配置详解(注释很详细)<a name="t4"></a></h2>
<p>　　Java代码结构如下：</p>
<p><img src="https://images2017.cnblogs.com/blog/1268854/201801/1268854-20180117121525490-280892340.png" alt=""></p>
<p>项目地址：<a href="https://github.com/AlbertRui/SpringMVC" target="_blank">https://github.com/AlbertRui/SpringMVC</a></p>
<p>参考：<a href="http://blog.csdn.net/jianyuerensheng/article/details/51258942" target="_blank">http://blog.csdn.net/jianyuerensheng/article/details/51258942</a></p></div><div id="MySignature"></div>
<div class="clear"></div>
<div id="blog_post_info_block">
<div id="BlogPostCategory"></div>
<div id="EntryTag"></div>
<div id="blog_post_info">
</div>
<div class="clear"></div>
<div id="post_next_prev"></div>
</div>


		<p class="postfoot">
			posted on <span id="post-date">2018-01-17 15:18</span> <a href='http://www.cnblogs.com/albertrui/'>AlbertRui</a> 阅读(<span id="post_view_count">...</span>) 评论(<span id="post_comment_count">...</span>)  <a href ="https://i.cnblogs.com/EditPosts.aspx?postid=8302170" rel="nofollow">编辑</a> <a href="#" onclick="AddToWz(8302170);return false;">收藏</a>
		</p>
	</div>
	<script type="text/javascript">var allowComments=true,cb_blogId=391630,cb_entryId=8302170,cb_blogApp=currentBlogApp,cb_blogUserGuid='55939cf9-b677-4c86-4ffc-08d4ef52ecb6',cb_entryCreatedDate='2018/1/17 15:18:00';loadViewCount(cb_entryId);var cb_postType=1;</script>
	
	</div><a name="!comments"></a><div id="blog-comments-placeholder"></div><script type="text/javascript">var commentManager = new blogCommentManager();commentManager.renderComments(0);</script>
<div id='comment_form' class='commentform'>
<a name='commentform'></a>
<div id='divCommentShow'></div>
<div id='comment_nav'><span id='span_refresh_tips'></span><a href='javascript:void(0);' onclick='return RefreshCommentList();' id='lnk_RefreshComments' runat='server' clientidmode='Static'>刷新评论</a><a href='#' onclick='return RefreshPage();'>刷新页面</a><a href='#top'>返回顶部</a></div>
<div id='comment_form_container'></div>
<div class='ad_text_commentbox' id='ad_text_under_commentbox'></div>
<div id='ad_t2'></div>
<div id='opt_under_post'></div>
<div id='cnblogs_c1' class='c_ad_block'></div>
<div id='under_post_news'></div>
<div id='cnblogs_c2' class='c_ad_block'></div>
<div id='under_post_kb'></div>
<div id='HistoryToday' class='c_ad_block'></div>
<script type='text/javascript'>
    fixPostBody();
    setTimeout(function () { incrementViewCount(cb_entryId); }, 50);
    deliverAdT2();
    deliverAdC1();
    deliverAdC2();    
    loadNewsAndKb();
    loadBlogSignature();
    LoadPostInfoBlock(cb_blogId, cb_entryId, cb_blogApp, cb_blogUserGuid);
    GetPrevNextPost(cb_entryId, cb_blogId, cb_entryCreatedDate, cb_postType);
    loadOptUnderPost();
    GetHistoryToday(cb_blogId, cb_blogApp, cb_entryCreatedDate);   
</script>
</div>


	
<p id="footer">
	Powered by: 
	<br />
	
	<a id="Footer1_Hyperlink3" NAME="Hyperlink1" href="http://www.cnblogs.com/" style="font-family:Verdana;font-size:12px;">博客园</a>
	<br />
	Copyright &copy; AlbertRui
</p>
</div>
<div id="rightmenu">
	
		<div id="blog-calendar" style="display:none"></div><script type="text/javascript">loadBlogDefaultCalendar();</script>
		
<h3>导航</h3>
<ul>
			<li><a id="blog_nav_sitehome" href="http://www.cnblogs.com/">博客园</a></li>
			<li><a id="blog_nav_myhome" href="http://www.cnblogs.com/albertrui/">首页</a></li>
			<li><a id="blog_nav_newpost" rel="nofollow" href="https://i.cnblogs.com/EditPosts.aspx?opt=1">新随笔</a></li>
			<li><a id="blog_nav_contact" accesskey="9" rel="nofollow" href="https://msg.cnblogs.com/send/AlbertRui">联系</a></li>
			<li><a id="blog_nav_rss" href="http://www.cnblogs.com/albertrui/rss">订阅</a><a id="blog_nav_rss_image" href="http://www.cnblogs.com/albertrui/rss"><img src="//www.cnblogs.com/images/xml.gif" alt="订阅" /></a>
			<li><a id="blog_nav_admin" rel="nofollow" href="https://i.cnblogs.com/">管理</a></li>
</ul>
		<div id="blog_stats">
<h3>统计</h3>
	<ul>
		<li>随笔 - 21
		<li>文章 - 0
		<li>评论 - 1
		<li>引用 - 0
	</li>
</ul></div>
		
<h3>公告</h3>
	<div id="blog-news"></div><script type="text/javascript">loadBlogNews();</script>

		<div id="blog-sidecolumn"></div><script type="text/javascript">loadBlogSideColumn();</script>
	
</div>
			
			
			
			
			 

	

</body>
</html>
