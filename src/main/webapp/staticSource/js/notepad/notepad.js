$(function() {
    initNotepadIndex();
    scrollbarLeftMenuShow();
	// 默认选中第一个便签并展示
    function initNotepadIndex() {
        removeCurrentSelectedNotepadBack();
        $(".menu-content ul li:first").addClass("notepad-selected");
        var curNotepadName = $(".menu-content ul li:first").find(".notepad-name em").html();
        setTitle(curNotepadName);
    }
    // 去掉当前选择的便签背景样式
    function removeCurrentSelectedNotepadBack() {
        $(".file-notepad").each(function () {
            if($(this).hasClass("notepad-selected")){
                $(this).removeClass("notepad-selected");
            }
        });
    }
	// 文件列表添加滚动条--展示
    function scrollbarLeftMenuShow() {
        $('.scrollbar-leftMenu').niceScroll({
            cursorcolor : "#999999",zindex:10
        }).show();
    }
    function scrollbarLeftMenuResize() {
        $('.scrollbar-leftMenu').niceScroll({
            cursorcolor : "#999999"
        }).resize();
    }
    // 获取便签ID
    function getNotepadId() {
        return $("#notepad-menu .notepad-selected").find("input[name=notepad-id]").val().trim();
    }
    // 设置列表内当前选中的便签名称
    function setSelectedNotepadName(newName, updateTime) {
        if (newName != null && newName.trim() != ''){
            $(".file-notepad").each(function () {
                if($(this).hasClass("notepad-selected")){
                    var oldName = $(this).find(".notepad-name em").html();
                    if (oldName.trim() != newName.trim()){
                        $(this).find(".notepad-name em").html(newName.trim());
                        $(this).find(".notepad-name em").attr("title",newName.trim());
                    }
                    if (updateTime != '' && updateTime.length > 10){
                        $(this).find(".notepad-date").html(updateTime.substring(2,10));
                    }
                }
            });
        }
    }
    // 获取列表内当前选中的便签名称
    function getSelectedNotepadName() {
        var name = '';
        $(".file-notepad").each(function () {
            if($(this).hasClass("notepad-selected")){
                name = $(this).find(".notepad-name em").html().trim();
            }
        });
        return name;
    }
    // 设置标题
    function setTitle(title) {
        $(".main-notepad .address-file").find("input[name=notepadName]").val(title.trim());
    }
    // 获取标题
    function getTitle() {
        return $(".main-notepad .address-file").find("input[name=notepadName]").val().trim();
    }
    // 设置编辑器内容
    function setContent(content) {
        if (content == null){
            content = "";
        }
        notepadEditor.setData(content);
    }
    // 获取编辑器内容
    function getContent() {
        return notepadEditor.getData();
    }
    // 新增一个标签
    function addNotepad(notepad) {
        var str = "<li class='menu-content-item file-notepad'>";
                str += "<span class='notepad-icon'><i class='iconfont icon-wenjian6'></i></span>";
                str += "<input name='notepad-id' type='hidden' value='"+notepad.id+"' />";
                str += "<span class='notepad-name'><em title='"+notepad.notepadName+"'>"+notepad.notepadName+"</em></span>";
                str += "<span class='notepad-date clD2'>"+notepad.updateTime.substring(2,10)+"</span>";
                str += "<div class='notepad-edit hdn fr'>";
                    str += "<i class='iconfont icon-xiugai fs16'></i>";
                    str += "<ul class='notepad-edit-tip'>";
                        str += "<li class='notepad-delete-btn'>删除</li>";
                        str += "<li class='notepad-top-btn'>置顶</li>";
                    str += "</ul>";
                str += "</div>";
            str += "</li>";
        $("#notepad-menu").prepend(str);
        initNotepadIndex();
        setContent(notepad.content);

    }
    // 选中相邻便签
    function selectedNotepad(obj) {
        removeCurrentSelectedNotepadBack();
        obj.addClass("notepad-selected");
        var $notepadId = obj.find("input[name=notepad-id]").val();
        if ($notepadId != ''){
            $.ajax({
                url:'notepad/'+$notepadId+'/authc',
                type:'GET',
                async:true,
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if (data.flag == 'yes'){
                        setTitle(data.notepad.notepadName);
                        setContent(data.notepad.content);
                    }else{
                        // 要求登录先
                        layer.msg(data.errorMsg);
                    }
                }
            });
        }
    }

    function selectNextNotepad(obj) {
        var nextLi = obj.next();
        var nextId = nextLi.find("input[name=notepad-id]").val();
        if (typeof(nextId) == 'undefined'){
            var preLi = obj.prev();
            var preId = preLi.find("input[name=notepad-id]").val();
            if (typeof(preId) == 'undefined'){
                window.location.href = $("#mol").val();
            }else {
                selectedNotepad(preLi);
            }
        }else {
            selectedNotepad(nextLi);
        }
    }
    layui.use([ 'layer'], function() {
        var layer = layui.layer;
        /* 新增事件 */
        $("#notepad-add-btn").on("click",function () {
            var op = {};
            op._method="PUT";
            $.ajax({
                url:'notepad/add/authc',
                type:'POST',
                async:true,
                data:op,
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if (data.flag == '0000'){
                        if (getNotepadId() == ''){
                            window.location.href = $("#mol").val();
                        }else {
                            addNotepad(data.notepad);
                            scrollbarLeftMenuResize();
                        }
                    }else{
                        // 要求登录先
                        layer.msg(data.errorMsg);
                    }
                }
            });
        });
        /* 文件列表点击事件 */
        $("#notepad-menu").on("click",".file-notepad",function() {
            selectedNotepad($(this));
        });
        /* 编辑器提交 */
        function dealNotepad() {
            var notepad = {};
            notepad.id = getNotepadId();
            notepad.notepadName = getTitle();
            notepad.content = getContent();
            $.ajax({
                url:'notepad/content/authc',
                type:'POST',
                async:true,
                data:notepad,
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if (data.flag == 'yes'){
                        if (notepad.id == ''){
                            window.location.href = $("#mol").val();
                        }
                        setSelectedNotepadName(data.notepad.notepadName, data.notepad.updateTime);
                        layer.msg("保存成功");
                    }else{
                        // 要求登录先
                        var $loginPop = $('#login-pop');
                        $loginPop.find("input[name=event_object]").val("notepad-save-btn");
                        layer.open({
                            type : 1,
                            content : $loginPop,
                            title:false,
                            area: ['500px', '340px'],
                            shadeClose : true
                        })
                    }
                }
            });
        }
        // 点击保存按钮
        $("#notepad-save-btn").on("click",function () {
            dealNotepad();
        });
        // 修改标题函数
        function updateNotepadName() {
            var curName = getSelectedNotepadName();
            if (getTitle() == ""){
                layer.msg("标题不能为空");
                setTitle(curName);
            }else {
                if (getNotepadId() != ""){
                    if (getTitle() != curName){
                        dealNotepad();
                    }
                }
            }
        }
        // 标题框失去焦点
        $(".main-notepad .address-file").find("input[name=notepadName]").blur(function () {
            updateNotepadName();
        });
        // 标题框enter事件
        $(".main-notepad .address-file").find("input[name=notepadName]").keyup(function () {
            if(event.keyCode == 13 ){
                updateNotepadName();
            }
        });
        /* 删除操作 */
        $("#notepad-menu").on("click",'.notepad-delete-btn',function (event) {
            event.stopPropagation();
            var obj = $(this);
            var $curNotepadDiv = obj.parents(".file-notepad");
            var $notepadId = $curNotepadDiv.find("input[name=notepad-id]").val();
            if ($notepadId != ''){
                var notepad = {};
                notepad._method="DELETE";
                notepad.notepadId=$notepadId;
                $.ajax({
                    url:'notepad/'+$notepadId+'/authc',
                    type:'POST',
                    async:true,
                    data:notepad,
                    timeout:5000,
                    dataType:'json',
                    success:function(data){
                        if (data.flag == '0000'){
                            selectNextNotepad($curNotepadDiv);
                            $curNotepadDiv.remove();
                            scrollbarLeftMenuResize();
                        }else{
                            // 要求登录先
                            layer.msg(data.errorMsg);
                        }
                    }
                });
            }
        });

        /* 置顶操作 */
        $("#notepad-menu").on("click",'.notepad-top-btn',function (event) {
            event.stopPropagation();
            var obj = $(this);
            var $curNotepadDiv = obj.parents(".file-notepad");
            var $notepadId = $curNotepadDiv.find("input[name=notepad-id]").val();
            if ($notepadId != ''){
                var notepad = {};
                notepad._method="PUT";
                notepad.notepadId=$notepadId;
                $.ajax({
                    url:'notepad/'+$notepadId+'/authc',
                    type:'POST',
                    async:true,
                    data:notepad,
                    timeout:5000,
                    dataType:'json',
                    success:function(data){
                        if (data.flag == '0000'){
                            obj.parents("#notepad-menu").prepend($curNotepadDiv);
                            selectedNotepad($curNotepadDiv);
                            layer.msg("置顶成功");
                        }else{
                            // 要求登录先
                            layer.msg(data.errorMsg);
                        }
                    }
                });
            }
        });
    });

});