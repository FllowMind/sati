/**
 * Created by Administrator on 2017/3/7.
 * 
 **/
 var user = {};
//详情页
(function() {
    getHome();
   
    //提示的条件初始化
    toastr.options = {
		"positionClass": "toast-top-center",//显示位置
		"timeOut": "2000" //展现时间
    }
       
}());

function getHome() {
    $.get('./partials/home.html', function(data) {
        $('#content-body').html(data);
        $("#product-list").imgscroll({
            speed: 10,    //图片滚动速度
            amount: 0,    //图片滚动过渡时间
            width: 1,     //图片滚动步数
            dir: "left"   // "left" 或 "up" 向左或向上滚动
        });
        //获取当前用户
        getSessionUser();
        //获取公告
        getInfoFieldByCondition(2,1,5,'noticeList');
        //获取政策
        getInfoFieldByCondition(1,1,5,'policyList');
        //技术供给
        getFeildByCondition(1,3,'supplyList',1);
        //技术需求
        getFeildByCondition(1,3,'requireList',2);
        //科技企业
        
    });
}






//首页技术供给
function getSupply() {
  $.get('./partials/supply.html', function(data) {
      $('#content-body').html(data);
      
      //获取技术供给
      
  });
}


//详情页
function getDetails() {
  $.get('./partials/details.html', function(data) {
      $('#content-body').html(data);
  });
}

/**
 * 供给详细页
 */
function getSupplyDetails() {
  $.get('./partials/supplyDetails.html', function(data) {
      $('#content-body').html(data);
  });
}

/**
 * 需求详细页
 */
function getRequireDetails() {
	$.get('./partials/requireDetails.html', function(data) {
	      $('#content-body').html(data);
    });
}



//获取登录页面
function getLogin() {
    $.get('./partials/login.html', function(data) {
        $('#content').html(data);
        loginFormValidator();
        //当表单验证失败时不绑定后续事件
        $("#loginForm").submit(function(ev){ev.preventDefault();});
    });
    
}


//获取注册页面
function reg() {
    $.get('./partials/reg.html', function(data) {
        $('#content').html(data);
        
        verificationField();
        //当表单验证失败时不绑定后续事件
        $("#regForm").submit(function(ev){ev.preventDefault();});
    });
}

