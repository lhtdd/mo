<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>默--关于</title>
    <link rel="stylesheet" href="${ctxStatic }/css/main.css">
    <link rel="stylesheet" href="${ctxStatic }/css/home/about.css">
</head>
<body>
    <%@ include file="../../frameJsp/header.jsp" %>
    <div class="container">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md3">
                <div class="layui-collapse bgFF">
                    <div class="layui-colla-item">
                        <h2 class="layui-colla-title bgFF clEE">简介</h2>
                        <div class="layui-colla-content layui-show">
                            <ul class="mo-ul">
                                <li><a href="${ctx}/home/about">关于--默</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md9">
                <div class="about-mo bgFF">
                    <blockquote class="layui-elem-quote layui-quote-nm cl99">解释一下--MO</blockquote>
                    <p>
                        MO(默)，更想把它解释为一种态度。<br/>
                        就像是眼前的人或事或物，应持有一种稳重、豁达、潇洒的态度。<br/>
                        。。。<br/>
                        是的，自己体会一下。<br/>
                    </p>
                    <blockquote class="layui-elem-quote layui-quote-nm cl99">介绍一下这个网站</blockquote>
                    <p>
                        正如你所看到的，这或许是一个多余的网站。<br/>
                        。。。<br/>
                        没错，当你不知道从哪里获取了这个网站的连接，又一不小心点进来。<br/>
                        在你耐着性子大概浏览一遍以后，应该会有这样的体会吧。<br/>
                        <br/>
                        不过，不知道会不会有点其它的想法。<br/>
                        。。。<br/>
                        <br/>
                        我希望它能成为我们工作，生活，学习的帮手。<br/>
                    </p>
                    <blockquote class="layui-elem-quote layui-quote-nm cl99">介绍一下我</blockquote>
                    <p>
                        没啥好介绍的。<br/>
                        。。。<br/>
                        因为你不一定会遇到这个网站，<br/>
                        遇到了也不一定会浏览这个网站，<br/>
                        浏览了也不一定会对这个网站感兴趣，甚至会略显“轻浮”的“切”，<br/>
                        至于会主动找到页面底部的“关于”（就是本页）想更加深入的了解一下这个网站，<br/>
                        甚至是想找到一个联系方式，探讨一下其它方面的任何问题。。。<br/>
                        <br/>
                        好吧，我是想在这里留下一个联系方式。<br/>
                    </p>
                    <blockquote class="layui-elem-quote layui-quote-nm cl99">联系方式</blockquote>
                    <p>
                        lyao_mo@163.com
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- 网页底部 -->
    <%@ include file="../../frameJsp/footer.jsp" %>
    <script>
        //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
        layui.use('element', function(){
            var element = layui.element;

            //…
        });
    </script>
</body>
</html>