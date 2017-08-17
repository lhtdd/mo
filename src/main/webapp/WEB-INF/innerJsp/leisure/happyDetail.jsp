<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="happy-wrapper fl bgFF">
	<div class="happy-item">
		<div class="happy-header">
			<img src="${ctxStatic }/image/lmz.jpg" alt="头像"/>
			<div class="happy-name-wrapper">
				<span class="user-name cl33">天神下凡</span>
				<span class="time clA1">08-14 15:55</span>
			</div>
		</div>
		<div class="happy-content fs16">
			<p>今天趴在男朋友身上，摸他的喉结玩，然后心想这东西平时是会动的，会不会是类似开关之类的东西啊，就手贱一下给摁下去了，然后这货脸色一下就变了，到现在还不理我，真小气……</p>
		</div>
		<div class="happy-operation fs14">
			<ul>
				<li class="praise"><i class="iconfont icon-zan"></i><span>122</span></li>
				<li class="tread"><i class="iconfont icon-cai1"></i><span>38</span></li>
				<li class="comment"><i class="iconfont icon-pinglun-copy1"></i><span>254</span></li>
				<li class="collection fr"><i class="iconfont icon-dianzan4"></i><span>75</span></li>
				<li class="transmit fr"><i class="iconfont icon-zhuanfa01"></i><span>11</span></li>
				<li class="next-item fr"><button id="happy-next-btn" class="layui-btn layui-btn-mini">下一条</button></li>
				<li class="previous-item fr"><button id="happy-previous-btn" class="layui-btn layui-btn-mini">上一条</button></li>
			</ul>
		</div>
		<!-- 发表评论处 -->
		<div class="happy-comment-wrapper">
			<h3 class="fs14 cl99">发表评论</h3>
			<div class="happy-comment">
				<img class="br-circular" src="${ctxStatic }/image/defaultPic.jpg" alt="头像"/>
				<textarea class="layui-textarea fr" placeholder="期待你的神评:)"></textarea>
				<div class="clear"></div>
				<div class="happy-comment-confirm">
					<span>您还可以输入<em>140</em>个字</span>
					<button class="layui-btn">评论</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 展示所有评论 -->
	<div class="all-comment-wrapper">
		<fieldset class="layui-elem-field layui-field-title">
			<legend class="fs16 cl99">所有评论</legend>
			<div class="layui-field-box">
			    <ul class="comment-list">
					<li class="comment-item">
						<div class="comment-header">
							<img src="${ctxStatic }/image/lmz.jpg" alt="头像"/>
							<div class="comment-name-wrapper">
								<span class="user-name cl33">天神下凡</span>
								<span class="time clA1">08-14 15:55</span>
							</div>
							<div class="comment-operation fs14 fr">
								<ul>
									<li class="praise"><i class="iconfont icon-zan"></i><span>122</span></li>
									<li class="tread"><i class="iconfont icon-cai1"></i><span>38</span></li>
									<li class="comment"><i class="iconfont icon-pinglun-copy1"></i><span>254</span></li>
								</ul>
							</div>
						</div>
						<div class="comment-content fs16">
							<p><a href="http://10.186.106.131:8081/mo/leisure/happyDetail?id=1">ddddd</a></p>
						</div>
					</li>
					<li class="comment-item">
						<div class="comment-header">
							<img src="${ctxStatic }/image/lmz.jpg" alt="头像"/>
							<div class="comment-name-wrapper">
								<span class="user-name cl33">天神下凡</span>
								<span class="time clA1">08-14 15:55</span>
							</div>
							<div class="comment-operation fs14 fr">
								<ul>
									<li class="praise"><i class="iconfont icon-zan"></i><span>122</span></li>
									<li class="tread"><i class="iconfont icon-cai1"></i><span>38</span></li>
									<li class="comment"><i class="iconfont icon-pinglun-copy1"></i><span>254</span></li>
								</ul>
							</div>
						</div>
						<div class="comment-content fs16">
							<p><a href="http://10.186.106.131:8081/mo/leisure/happyDetail?id=1">ddddd</a></p>
						</div>
					</li>
					<li class="comment-item">
						<div class="comment-header">
							<img src="${ctxStatic }/image/lmz.jpg" alt="头像"/>
							<div class="comment-name-wrapper">
								<span class="user-name cl33">天神下凡</span>
								<span class="time clA1">08-14 15:55</span>
							</div>
							<div class="comment-operation fs14 fr">
								<ul>
									<li class="praise"><i class="iconfont icon-zan"></i><span>122</span></li>
									<li class="tread"><i class="iconfont icon-cai1"></i><span>38</span></li>
									<li class="comment"><i class="iconfont icon-pinglun-copy1"></i><span>254</span></li>
								</ul>
							</div>
						</div>
						<div class="comment-content fs16">
							<p><a href="http://10.186.106.131:8081/mo/leisure/happyDetail?id=1">ddddd</a></p>
						</div>
					</li>
				</ul>
			</div>
		</fieldset>
	</div>
</div>