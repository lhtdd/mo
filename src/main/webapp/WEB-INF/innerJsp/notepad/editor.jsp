<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="main-notepad fr">
	<div class="address-file">
		<span>GIT > git安装</span>
		<button id="notepad-save-btn" class="layui-btn notepad-save-btn fr">保存</button>
	</div>
	<div id="content-notepad" class="content-notepad">
		<textarea name="editor1" style="display: none;">
			  记点有用的，别整没用的。
		</textarea>
		<script type="text/javascript">
			var notepadEditor = CKEDITOR.replace('editor1',{
                customConfig : '${ctxStatic }/js/notepad/ck_config.js',
                height: 'auto'
			});
		</script>
	</div>
</div>