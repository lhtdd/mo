<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="main-notepad fr">
	<div class="address-file">
		<span>GIT > git安装</span>
		<button id="notepad-save-btn" class="layui-btn layui-btn-mini notepad-save-btn fr">保存</button>
	</div>
	<div id="content-notepad" class="content-notepad">
		<textarea name="editor1" id="notepad" style="display: none;">
			  把编辑器的初始内容放在这textarea即可
		</textarea>
		<script type="text/javascript">
            CKEDITOR.replace('editor1',{
                customConfig : '${ctxStatic }/js/notepad/ck_config.js'
			});
		</script>
	</div>
</div>