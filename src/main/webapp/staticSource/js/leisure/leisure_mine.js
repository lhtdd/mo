$(function() {
	fixDiv("header");

	function jointHappyItem(item) {
		var happyItem = "";
        happyItem += '<li class="happy-item">';
        happyItem += '<div class="happy-header">';
        happyItem += '<img src="'+item.userImagePath+'" alt="头像"/>';
        happyItem += '<div class="happy-name-wrapper">';
        happyItem += '<span class="user-name cl33 fs14">'+item.alias+'</span>';
        happyItem += '<span class="time clA1">'+item.inTime.substring(2,10)+'</span>';
        happyItem += '</div>';
        happyItem += '</div>';
        happyItem += '<div class="happy-content fs16">';
        happyItem += '<input type="hidden" name="happyId" value="'+item.happyId+'">';
        happyItem += '<p>'+item.happyText+'</p>';
        happyItem += '</div>';
        happyItem += '<div class="happy-operation fs14">';
        happyItem += '<ul>';
        happyItem += '<li class="collection has-collection"><i class="iconfont icon-dianzan4"></i><span>'+item.collectHits+'</span></li>';
        happyItem += '</ul>';
        happyItem += '</div>';
        happyItem += '</li>';
        return happyItem;
    }
	
    layui.use('flow', function(){
        var flow = layui.flow;
        flow.load({
            elem: '.happy-list',
            isAuto: false,
            done: function(page, next){
                var lis = [];
                var goUrl = 'leisure/happy/'+page+'/authc';
                $.get(goUrl, function(res){
                    layui.each(res.happyItems, function(index, item){
                    	var happyItem = jointHappyItem(item);
                        lis.push(happyItem);
                    });
                    next(lis.join(''), res.moreHappyItems);
                });
            }
        });
    });

    // 取消收藏
    $(".happy-list").on("click",'.happy-item .collection', function(){
        var flag = subCollection($(this), "PUT");
        if (flag){
            $(this).parents(".happy-item").remove();
        }
    });

    function subCollection(obj, method) {
        var subFlag = false;
        var happyId = obj.parents(".happy-item").find("input[name='happyId']").val();
        var happy = {};
        happy.happyId = happyId;
        happy._method = method;
        $.ajax({
            url:'leisure/collection/authc',
            type:'POST',
            async:false,
            data:happy,
            timeout:5000,
            dataType:'json',
            success:function(data){
                if (data.flag == 'yes'){
                    subFlag = true;
                    layer.msg(data.errorMsg);
                }else{
                    // 要求登录先
                    var $loginPop = $('#login-pop');
                    layer.open({
                        type : 1,
                        content : $loginPop,
                        title:false,
                        area: ['500px', '340px'],
                        shadeClose : true,
                        success: function () {
                            $(".verifyImg").click();
                        }
                    })
                }
            }
        });
        return subFlag;
    }

});