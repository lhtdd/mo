<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="menu-fileList fl">
	<div class="menu-title">
		<ul>
			<li class="menu-title-return"><i class="iconfont icon-wenjian-copy1"></i></li>
			<li class="menu-title-fileName"><span>便签夹</span></li>
			<li class="menu-title-settings" id="notepad-add-btn">
				<i class="iconfont icon-xinjian5"></i>
			</li>
		</ul>
	</div>
	<div class="menu-content scrollbar-leftMenu">
		<ul id="notepad-menu">
			<c:if test="${not empty notepads}">
				<c:forEach items="${notepads}" var="notepad">
					<li class="menu-content-item file-notepad">
						<span class='notepad-icon'><i class="iconfont icon-wenjian6"></i></span>
						<input name="notepad-id" type="hidden" value="${notepad.id}" />
						<span class="notepad-name"><em title="${notepad.notepadName}">${notepad.notepadName}</em></span>
						<span class="notepad-date clD2">${fn:substring(notepad.updateTime, 2, 10)}</span>
						<div class="notepad-edit hdn fr">
							<i class='iconfont icon-xiugai fs16'></i>
							<ul class="notepad-edit-tip">
								<li class="notepad-delete-btn">删除</li>
								<li class="notepad-top-btn">置顶</li>
							</ul>
						</div>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${empty notepads}">
				<li class="menu-content-item file-notepad">
					<span class='notepad-icon'><i class="iconfont icon-wenjian6"></i></span>
					<input name="notepad-id" type="hidden" value="" />
					<span class="notepad-name"><em title="便签">便签</em></span>
					<span class="notepad-date clD2"><fmt:formatDate value="${today}" pattern="yy-MM-dd" /></span>
					<div class="notepad-edit hdn fr">
						<i class='iconfont icon-xiugai fs16'></i>
						<ul class="notepad-edit-tip">
							<li class="notepad-delete-btn">删除</li>
							<li class="notepad-top-btn">置顶</li>
						</ul>
					</div>
				</li>
			</c:if>
		</ul>
	</div>
</div>