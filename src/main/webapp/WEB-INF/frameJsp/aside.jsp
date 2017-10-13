<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctxStatic }/js/aside/aside.js?r=<%=Math.random() %>"></script>
<div class="aside">
	<ul class="aside-bar">
		<li class="aside-bar-collect btn-url-add">
			<i class="iconfont icon-shoucang"></i>
		</li>
		<li class="aside-bar-item">
			<i class="iconfont icon-changyong"></i>
			<span>常用</span>
			<div class="aside-bar-content scrollbar-aside">
				<div class="scrollbar-wrap">
					<ul>
						<li class="url-item">
							<div class="url-content" style="background:url(../image/taobao.ico) no-repeat left center;">
								<a class="url-info" href="https://www.taobao.com" target="_blank"><em title="淘宝网">淘宝网</em></a>
								<div class="url-edit">
									<i class='iconfont icon-xiugai fs12'></i>
									<div class="url-edit-tip">
										<ul>
											<li>编辑</li>
											<li>删除</li>
										</ul>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</li>
		
		<li class="aside-bar-other">
			<i class="iconfont icon-xinjian2"></i>
		</li>
	</ul>
</div>