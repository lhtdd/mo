// 定位鼠标位置
function mouseCoords(ev) {
	if (ev.pageX || ev.pageY) {
		return {
			x : ev.pageX,
			y : ev.pageY
		};
	}
	return {
		x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y : ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}
function getInfo() {
	var s = "";
	s += " 网页可见区域宽：" + document.body.clientWidth;
	s += " 网页可见区域高：" + document.body.clientHeight;
	s += " 网页可见区域宽：" + document.body.offsetWidth + " (包括边线和滚动条的宽)";
	s += " 网页可见区域高：" + document.body.offsetHeight + " (包括边线的宽)";
	s += " 网页正文全文宽：" + document.body.scrollWidth;
	s += " 网页正文全文高：" + document.body.scrollHeight;
	s += " 网页被卷去的高(ff)：" + document.body.scrollTop;
	s += " 网页被卷去的高(ie)：" + document.documentElement.scrollTop;
	s += " 网页被卷去的左：" + document.body.scrollLeft;
	s += " 网页正文部分上：" + window.screenTop;
	s += " 网页正文部分左：" + window.screenLeft;
	s += " 屏幕分辨率的高：" + window.screen.height;
	s += " 屏幕分辨率的宽：" + window.screen.width;
	s += " 屏幕可用工作区高度：" + window.screen.availHeight;
	s += " 屏幕可用工作区宽度：" + window.screen.availWidth;
	s += " 你的屏幕设置是 " + window.screen.colorDepth + " 位彩色";
	s += " 你的屏幕设置 " + window.screen.deviceXDPI + " 像素/英寸";
	alert(s);
}
// getInfo();
/*
 * 滚动条滑动，位置不变的DIV层 div_id：DIV的ID属性值，必填参数 offsetTop：滚动条滑动时DIV层距顶部的高度，可选参数
 */
function fixDiv(div_id, offsetTop) {
	var Obj = $('#' + div_id);
	if (Obj.length != 1) {
		return false;
	}
	var offsetTop = arguments[1] ? arguments[1] : 0;
	var ObjTop = Obj.offset().top;
	$(window).scroll(function() {
		if ($(window).scrollTop() <= ObjTop) {
			Obj.css({
				'position' : 'relative',
				'top' : 0
			});
		} else {
			Obj.css({
				'position' : 'fixed',
				'top' : 0 + offsetTop + 'px',
				'z-index' : 1
			});
		}
	});
}
// 滚动到顶部
// div_id 是滚动按钮的 id值
function rollingTop(div_id) {
	var Obj = $('#' + div_id);
	if (Obj.length != 1) {
		return false;
	}
	$(window).scroll(function() {
		if ($(window).scrollTop() >= 60) {
			Obj.fadeIn(300);
		} else {
			Obj.fadeOut(300);
		}
	});
	Obj.click(function() {
		$('html,body').animate({
			scrollTop : '0px'
		}, 400);
	});
}
