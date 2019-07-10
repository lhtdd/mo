$(function() {
	//fixDiv("header");
	fixDiv("happy-mine-operation","10");

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
        happyItem += '<li class="collection"><i class="iconfont icon-dianzan4"></i><span class="collectHits">'+item.collectHits+'</span></li>';
        happyItem += '</ul>';
        happyItem += '</div>';
        happyItem += '</li>';
        return happyItem;
    }
	
    layui.use('flow', function(){
        var flow = layui.flow;
        flow.load({
            elem: '.happy-list'
            ,done: function(page, next){
                var lis = [];
                var lastHappyId = 0;
                var itemCount = $(".happy-list").children(".happy-item").length;
                if (itemCount > 0){
                    lastHappyId = $(".happy-list .happy-item:last-of-type").find("input[name='happyId']").val();
                }
                var goUrl = 'leisure/happy/'+page+'/'+lastHappyId;
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

	// 收藏
	$(".happy-list").on("click",'.happy-item .collection', function(){
        if ($(this).hasClass("has-collection")){
            cancelCollectHappy($(this));
        }else {
            collectHappy($(this));
        }
    });

    function collectHappy(obj) {
        var flag = subCollection(obj, "POST");
        if (flag){
            var collectSpan = obj.find(".collectHits");
            var oldHits = parseInt(collectSpan.text());
            collectSpan.text(oldHits+1);
            obj.addClass("has-collection");
        }
    }

    function cancelCollectHappy(obj) {
        var flag = subCollection(obj, "PUT");
        if (flag){
            var collectSpan = obj.find(".collectHits");
            var oldHits = parseInt(collectSpan.text());
            collectSpan.text(oldHits-1);
            obj.removeClass("has-collection");
        }
    }

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
                        shadeClose : true
                    })
                }
            }
        });
        return subFlag;
    }

	// 点击我的收藏
	$("#happy-mine-btn").click(function(){
        window.location.href = "leisure/index_mine/authc";
	});
});