var person = [];
//登录
function toLogin() {
	//获取登录用户的信息
	var user = {
		userId : $('#userId').val(),
		password : $('#password').val()
	}
	var bootstrapValidator = $("#loginForm").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){
	    //校验成功
    	$.ajax({
			url : 'user/loginAction',
			type : 'POST',
			dataType: "json",
			contentType : 'application/json',
			data : JSON.stringify(user),
			success : function(result) {
				if(result.success){
					toastr.success(result.msg);
					setTimeout(function() {
						//跳转后台页
						window.location.href="./backstage/index.html";
					},200);
				}else{
					toastr.error(result.msg);
				}
			}
		});
    }else {
    	//数据校验失败
    	toastr.error("输入用户账号和密码进行登录");
	}
}

//回车登录
function keyUpLogin() {
    if(window.event.keyCode==13){
    	toLogin();
    }
};

//注册
function toReg() {
	var bootstrapValidator = $("#regForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	   if(bootstrapValidator.isValid()){
		  //表单验证成功，得到需要的数据，封装后提交
		  var regUser = {
				  userId : $('#userId').val(),//用户账号
				  password : $('#regPassword').val(),//用户密码
				  userType : $('#userType li[class="active"]').attr("value"),//用户类型
				  email : $('#regEmail').val(),//邮箱
				  phoneNumber : $('#regPhone').val()//手机
		  } 
		  //提交
		  $.ajax({
				url : 'user/register',
				type : 'POST',
				dataType: "json",
				contentType : 'application/json',
				data : JSON.stringify(regUser),
				success : function(result) {
					if(result.success) {
						toastr.success(result.msg);
						setTimeout(function() {
							//跳转后台页
							window.location.href="./backstage/index.html";
						},1000);
					}else{
						toastr.error(result.msg);
					}
				}
		  });
	   }else {
		   //表单验证不成功，不提交数据，并告知用户
		   toastr.error('按提示填写注册所需信息再点击提交');
		   return;
	   }
}
//获取登录的用户数据
function getSessionUser() {
	if($.isEmptyObject(user)){//为空
		$.ajax({
			url : 'user/getCurrentUser',
			type : 'GET',
			dataType: "json",
			contentType : 'application/json',
			success : function(result) {
				if(result.success) {
					//已经登录返回首页
					$('#logoutTitle').hide();
					$('#loginTitle').show();
					user = result.result;
				}else{
					//未登录页面
					$('#loginTitle').hide();
					$('#logoutTitle').show();
					user = {};
				}
			},
			error : function(data) {
				console.log(data);
			}
		})
	}else{
		$('#logoutTitle').hide();
		$('#loginTitle').show();
	}
}

/*首页注销*/
function logout() {
	// 显示模态对话框
	$.ajax({
		url: 'user/logout',
		type : 'GET',
		dataType: 'json',
		success : function(result) {
			if(result.success){
				//注销成功
				toastr.success(result.msg);
				setTimeout(function() {
					window.location.href="./index.html";
				},200);
			}else{
				//注销失败
				toastr.error(result.msg);
			};
		}
	});
}



/**
 * *****************************   信息获取          *******************
 */

/**
 * **************************   政策通告     ***********************
 */

var infoCondition = {
		key  : '', //被搜索关键字
		infoStatus : '1',//已发布
		startDate : '',//最早发布时间
		endDate : '',//最迟发布时间
		pageNo : '',//当前页
		pageSize : '',//一页数据的条数
		infoType : '',//信息类型，1为政策法规，2为系统公告
}

/**
 * 默认查询
 * @param infoType
 * @param pageNo
 * @param pageSize
 * @param contentName
 */
function getInfoFieldByCondition(infoType, pageNo, pageSize,contentName) {
	infoCondition.infoType = infoType;
	infoCondition.pageNo = pageNo;
	infoCondition.pageSize = pageSize;
	getInfoPageByCondition(contentName);
}

/**
 * 查询通告或法规
 * @param contentName
 */
function getInfoPageByCondition(contentName) {
	$.ajax({
		url : './info/getInfoPageByCondition',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(infoCondition),
		success : function(data) {
			if(data.success) {
				renderInfo(data.result.resultList,contentName);
			}else{
				//失败，重新刷新页面
				window.location.reload();
			}
		}
	})
}

/**
 * 渲染信息类
 * @param data
 * @param contentName
 */
function renderInfo(data,contentName) {
	//console.log(data);
	for(var i = 0 ; i <data.length;i++) {
		$('#' + contentName).append(assembledInfo(i, data[i]));
	}
}
/**
 * 拼装字符串
 * @param index
 * @param obj
 * @returns {String}
 */
function assembledInfo(index, obj) {
	var assembledStr = '<li><a href="#" class="notice-list-item" onclick="getInfoDetails('+ obj.infoId +');">';
	if(index === 0){
		assembledStr += '<i class="new"></i>';
	}
	assembledStr += ''+ obj.infoTitle +'</a><span>'+ getLocalIndexTime(obj.publishTime) +'</span></li>';
	
	return assembledStr;
}


/**
 * 获取信息详情页
 * @param infoId 信息Id
 */
function getInfoDetails(infoId) {
	$.ajax({
		url : './info/getInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			infoId :infoId
		}),
		success : function(data) {
			console.log(data);
			if(data.success) {
				//跳转详情页
				getDetails();
				setTimeout(function() {
					renderDetailsForm('detailsForm', data.result);
				},200);
			}
		}, 
		error : function() {
			alert('连接服务器失败，刷新试一试');
		}
	})
}

/**
 * 渲染内容
 * @param contentName
 * @param obj
 */
function renderDetailsForm(contentName, obj) {
	//普通信息
	renderDetailsText(contentName,obj);
	//日期
	renderDetailsDate(contentName, obj);
	renderDetailsFile(contentName, obj);
}

function renderDetailsText(contentName,obj) {
	$.each(obj, function(key, val) {
		$('#'+contentName  +' .' + key).text(val);
	})
}

function renderDetailsDate(contentName, obj){
	$('#' + contentName + ' span[class*="detailsDate"]').text(getLocalIndexTime(obj.publishTime));
}

function renderDetailsFile(contentName, obj) {
	$('#'+ contentName + ' #fileUrl').attr('href' , '../../' + obj.fileUrl);
	$('#' + contentName + ' #infoContent').html(obj.infoContent);
}



/**
 * ****************      技术供给和需求         ******************
 */


var techCondition = {
		key : '',//关键字
		industryId : '', //所属行业
		htfId : '',//高新技术领域
		startDate : '',//最早发布时间
		endDate : '',//最迟发布时间
		auditStatus : 4,//审核状态
		techStatus  : '',//产品状态
		pageNo : '',//页数
		pageSize : ''//每页条数
}

/**
 * 填充默认信息
 * @param pageNo
 * @param pageSize
 * @param contentName
 */
function getFeildByCondition(pageNo, pageSize,contentName, techType) {
	techCondition.pageNo = pageNo;
	techCondition.pageSize = pageSize;
	if(techType === 1) {
		getSupplyByCondition(contentName);
	}else if(techType === 2) {
		getRequireByConditon(contentName);
	}
}

/**
 * 查询技术供给数据
 * @param contentName
 */
function getSupplyByCondition(contentName) {
	$.ajax({
		url : './supply/getTechPageByCondition',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(techCondition),
		success: function(data) {
			//console.log(data);
			renderSupply(data.result.resultList, contentName);
		}
	})	
}
/**
 * 渲染首页
 * @param data
 * @param contentName
 */
function renderSupply(data, contentName) {
	for(var i = 0;i< data.length;i++) {
		$('#' + contentName).append(assembledSupply(data[i]));
	}
}

/**
 * 拼装技术供给字符串
 * @param obj
 */
function assembledSupply(obj) {
	return '<li><div class="info-title"><i class="info-left"></i><a href="#" onclick="getSupplyInfoDetails('+
	obj.tsiId +')">'+ obj.infoTitle +'</a><span><i class="item-time"></i>'+ getLocalIndexTime(obj.createTime) +
	' </span><span><i class="item-instuly"></i>'+ matchJsonFile('industryId',obj.industryId) +
	'</span><span><i class="item-location"></i>'+ matchJsonFile('locationId',obj.locationId) +
	'</span></div><p class="info-content">联系人：'+obj.contactName +'&nbsp;&nbsp;&nbsp;&nbsp;联系电话：'+
	obj.phoneNumber +'&nbsp;&nbsp;&nbsp;&nbsp;交易价格：'+obj.price +'</p></li>';
}

/***
 * 技术供给详细信息
 * @param supplyId
 */
function getSupplyInfoDetails(supplyId) {
	$.ajax({
		url : './supply/getTechSupplyInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			tsiId : supplyId
		}),
		success :function(data) {
			console.log(data);
			if(data.success){
				person = data.result;
				getSupplyDetails();
				setTimeout(function() {
					renderTechTable('supplyDetailsForm');
				},150);
			}else{
				alert('获取数据失败，刷新试一试');
			}
		},
		error : function(data){
			alert('连接服务器失败，尝试刷新一下');
		}
	})
}

/**
 * 渲染表格
 * @param contentName 容器名
 */
function renderTechTable(contentName) {
	//渲染普通文本
	renderTechText(contentName);
	//渲染下拉框
	renderTechChosen(contentName);
	//渲染json文件
	renderTechJson(contentName);
	
	$('#' + contentName + ' span[name="infoTitle"]').text(person.infoTitle);
}
/**
 * 渲染普通文本
 */
function renderTechText(contentName) {
	var elements = $('#'+ contentName +' span[class*="details-text"]');
	for(var i = 0;i < elements.length;i++) {
		$('#' + elements[i].id).text(person[elements[i].id]);
	}
}

/**
 * 渲染下拉框式
 * @param contentName
 */
function renderTechChosen(contentName){
	var elements = $('#' +contentName+ ' span[class*="details-select"]');
	for(var i = 0; i < elements.length;i++) {
		(function(){
			$('#' + elements[i].id).text(matchTechChosenFile(elements[i].id, person[elements[i].id]));
		})(i);
	}
}

/**
 * 渲染json文件
 * @param contentName
 */
function renderTechJson(contentName) {
	var elements = $('#' +contentName+ ' span[class*="details-json"]');
	for(var i = 0;i< elements.length;i++) {
		(function(){
			$('#' + contentName + ' #' + elements[i].id).text(matchJsonFile(elements[i].id, person[elements[i].id]));
		})(i);
	}
}


/**
 * 匹配Chosen文件
 * @param fieldName 字段名
 * @param matchValue 匹配值
 * @returns {String} 返回匹配成功结果
 */
function matchTechChosenFile(fieldName, matchValue) {
	var returnData = '';
	var url = "./data/supply/" + fieldName.substring(0,fieldName.length-2) + ".json";
	$.ajaxSettings.async = false;//设置为同步
	$.getJSON(url, function(data) {
		for(var i = 0;i<data.length;i++) {
			if(data[i].id === matchValue){
				returnData = data[i].name;
				return;
			}
		}
	});
	return returnData;
}


/**
 * 查询需求信息
 * @param contentName
 */
function getRequireByConditon(contentName) {
	$.ajax({
		url : './requirement/getTechPageByCondition',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(techCondition),
		success: function(data) {
			//console.log(data);
			renderRequire(data.result.resultList, contentName);
		}
	})	
}
/**
 * 渲染需求首页
 * @param data
 * @param contentName
 */
function renderRequire(data, contentName) {
	for(var i = 0;i< data.length;i++) {
		$('#' + contentName).append(assembledRequire(data[i]));
	}
}
/**
 * 拼装技术需求字符串
 * @param obj
 */
function assembledRequire(obj) {
	return '<li><div class="info-title"><i class="info-left"></i><a href="#" onclick="getRequireInfoDetails('+
	obj.triId +')">'+ obj.infoTitle +'</a><span><i class="item-time"></i>'+ getLocalIndexTime(obj.createTime) +
	' </span><span><i class="item-instuly"></i>'+ matchJsonFile('industryId',obj.industryId) +
	'</span><span><i class="item-location"></i>'+ matchJsonFile('locationId',obj.locationId) +
	'</span></div><p class="info-content">联系人：'+obj.contactName +'&nbsp;&nbsp;&nbsp;&nbsp;联系电话：'+
	obj.phoneNumber +'</p></li>';
}



/**
 * 获取详细的需求信息
 * @param requireId
 */
function getRequireInfoDetails(requireId) {
	$.ajax({
		url : './requirement/getTechRequireInfoById',
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json',
			data :  JSON.stringify({
				triId : requireId
			}),
			success :function(data) {
				console.log(data);
				if(data.success){
					person = data.result;
					getRequireDetails();
					setTimeout(function() {
						renderTechTable('requireDetailsForm');
					},150);
				}else{
					alert('获取数据失败，刷新试一试');
				}
			},
			error : function(data){
				alert('连接服务器失败，尝试刷新一下');
			}
	})
	
}

/**
 * 获取科技企业
 */
function getScientify() {
	
}







/**
 * 匹配json文件
 * @param fieldName 字段名
 * @param matchValue 匹配值
 * @returns {String} 返回匹配成功结果
 */
function matchJsonFile(fieldName, matchValue) {
	var returnData = '';
	var url = "./data/audit/" + fieldName.substring(0,fieldName.length-2) + ".json";
	$.ajaxSettings.async = false;//设置为同步
	$.getJSON(url, function(data) {
		for(var i = 0;i<data.length;i++) {
			if(data[i].id === matchValue){
				returnData = data[i].name;
				return;
			}
		}
	});
	return returnData;
}


/**
 * 时间戳转日期
 * @param nS 时间戳字符串
 * @returns 日期格式
 */
function getLocalIndexTime(nS) {  
	var date = new Date(nS);
	 return getdate(date);
}

/**
 * 转日期格式
 * @param date 由时间戳转日期格式
 * @returns {String} 返回一个完成转换的日期
 */
function getdate(date) {
    y = date.getFullYear(),
    m = date.getMonth() + 1,
    d = date.getDate();
    return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
}


//显示模态框
var showModal = function() {   
	  var remote = "./modalTemplates/logout.html"; 
	  if (remote != "") {   
		  $("#modalContent").load(remote, function() {
			  $('.modal-dialog').css("width", "200px");
			  $("#modalModule").modal('show');   
		  });  
	  }
	  setModal();
};



//设置模态框属性
function setModal() {
	// 模态对话框隐藏时移除数据  
    $("#modalModule").on("hidden", function() {    
    	$(this).removeData("modal");  
	});
    //显示时居中
    $('#modalModule').on('show.bs.modal', centerModals);
    //页面大小变化是仍然保证模态框水平垂直居中
    $(window).on('resize', centerModals);
}
//模态框居中
function centerModals() { 
	$('#modalModule').each(function(i) {   
		var $clone = $(this).clone().css('display','block').appendTo('body');
		var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
		top = top > 0 ? top : 0; 
		$clone.remove(); 
		$(this).find('.modal-content').css("margin-top", top);   
	});
}; 



//注册表单验证
function loginFormValidator() {
	$('#loginForm').bootstrapValidator({
		message : '值不能为空',
		fields : {
			userId : {
				message : '用户名验证失败',
				validators : {
					notEmpty: {
						message : '用户名不能为空'
					}
				}
			},
			password : {
				message : '密码不能为空',
				validators : {
					notEmpty : {
						message : '密码不能为空'
					}
				}
			}
		}
	});
}

//验证注册字段
function verificationField(){
	$('#regForm').bootstrapValidator({
		message : '值不能为空',
		/*feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},*/
		//验证字段
		fields : {
			//用户账号
			userId : {
				message : '用户名验证失败',
				validators : {
					notEmpty: {
						message : '用户名不能为空'
					}
				}
			},
			//密码
			regPassword : {
				message : '密码验证错误',
				validators : {
					notEmpty : {
						message : '密码不能为空'
					},
					//验证两次密码是否相同
					identical: {
                        field: 'regPassword2', //需要进行比较的input name值
                        message: '两次密码不一致'
                    }
				}
			},
			//确认密码
			regPassword2 : {
				message : '确认密码验证错误',
				validators : {
					notEmpty : {
						message : '确认密码不能为空'
					},
					identical: {//相同
                        field: 'regPassword',
                        message: '两次密码不一致'
                    }
				}
			},
			//邮箱格式
			regEmail : {
				message : '邮箱验证失败',
				validators : {
					notEmpty : {
						message : '邮箱不能为空'
					},
					emailAddress : {
						message : '邮箱地址格式错误'
					}
				}
			},
			//手机验证
			regPhone : {
				message : '手机验证失败',
				validators :{
					notEmpty: {
                        message: '手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    },
                    regexp: {
                        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    }
				}
			}
		}
	});
}