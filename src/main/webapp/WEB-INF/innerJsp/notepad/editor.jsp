<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="main-notepad fr">
	<div class="address-file">
		<label class="fs14 clEE">标题</label>
		<input class="fs16" type="text" name="notepadName">
		<button id="notepad-save-btn" class="layui-btn notepad-save-btn fr">保存</button>
	</div>
	<div id="content-notepad" class="content-notepad">
		<textarea name="editor1" style="display: none;">
			  ${notepadFirst.content}
		</textarea>
		<script type="text/javascript">
			var notepadEditor = CKEDITOR.replace('editor1',{
                customConfig : '${ctxStatic }/js/notepad/ck_config.js',
                height: '720px'
			});
		</script>
	</div>
</div>