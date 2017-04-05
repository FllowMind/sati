//用户信息审核页面
var auditType = 0;
var auditUserType;
var auditCondition = {
	 auditStatus : "",
	 startDate : "",
	 userType : "",
	 endDate : "",
 	 pageNo : 0,
 	 pageSize : 0,
}
/**
 * 获取个人用户审核信息
 */
function getAuditUserInfo() {
	auditType = 1; 
	/**
	 *	获取数据
	 */
	getAuditDetails(auditType,1,1,5);
}


/**
 * 获取指定用户类型的审核用户人数
 */
function getAuditDetails(userType,auditStatus,pageNo,pageSize){
	auditUserType = userType;
	auditCondition.pageNo = pageNo;
	auditCondition.pageSize = pageSize;
	auditCondition.userType = userType;
	auditCondition.auditStatus = auditStatus;
	console.log(auditCondition);
	getAuditOutlinePage(false);
}

/**
 * 指定条件查询
 */
function findAuditByCondition(pageNo,pageSize) {
	auditCondition.userType = auditUserType;
	auditCondition.auditStatus = $('#auditStatus').val() == -1 ? '' : $('#auditStatus').val();
	auditCondition.startDate = getTimeStamp($('#startDate').val() || '') || '';
	auditCondition.endDate = getTimeStamp($('#endDate').val() || '') || '';
	auditCondition.pageNo = pageNo;
	auditCondition.pageSize = pageSize;
	getAuditOutlinePage(true);
}

/**
 * 查询用户
 */
function getAuditOutlinePage(findType) {
	$.ajax({
		url : '../audit/getAuditOutlinePage',
		type : "post",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(auditCondition),
		success : function(data) {
			console.log(data);
			if (data.success) {
				if(findType && data.result.resultList.length === 0) {
					toastr.success("该查询条件为查询到用户请重新填写查询条件");
				}else{
					toastr.success(data.msg);
					$('#findContent').hide();
				}
				//获取所有个人用户成功
				person=data.result;
				//渲染所有需要审核个人用户信息
				renderAuditUserInfo(person);
			} else {
				alert(data.msg);
			}
		
		},
		error : function(data) {
			alert("连接服务器错误！")
		}
		
	})
}
 
/**
 * 渲染所有需要审核个人用户信息（公用的）
 */
function renderAuditUserInfo(data){
	$('.aa').empty();
	for(var i=0;i<data.resultList.length;i++){
		$('.aa').prepend("<tr><td>"+data.resultList[i].userId+"</td><td>"+getLocalTime(data.resultList[i].submitTime)+"</td><td><button class=\"btn btn-info\" onclick=\"getAuditInfoDetails('"+data.resultList[i].userId+"');\">审核</button></td></tr>");
	}
	$('.shenherenshu').html(data.resultList.length);
}

/**
 * 切换查询框
 */
function showFindContent() {
	//切换显示
	$('#findContent').toggle();
	setTimeout(function() {
		//填充下拉框
		$.get('../data/managment/auditStatus.json', function(data) {
			for(var i = 0 ; i< data.length;i++) {
				if(data[i].id === -1) {
					delete data[i];
				}
			}
	    	getOption(data, 'auditStatus');
	    });
		//设置日期选择
		$(".datepicker").datepicker({
	    	format: 'yyyy/mm/dd',
	        startDate: '-3d'
	    });
	},150)
	 
}

/**
 * 获取公司审核信息
 */
function getCompanyAuditInfo() {
	auditType = 2; 
	/**
	 *	获取数据
	 */
	getAuditDetails(auditType,1,1,5);
}

/**
 * 获取高校审核信息
 */
function getCollegeAuditInfo() {
	auditType = 4; 
	
    //获取所有需要审核的高校用户
	getAuditDetails(auditType,1,1,5);
}



/**
 * 获取科研审核信息
 */
function getScientifyAuditInfo() {
	auditType = 5; 
    //获取所有需要审核的科研用户
	getAuditDetails(auditType,1,1,5);
      
}




/**
 * 获取中介机构审核信息
 */
function getAgencyAuditInfo() {
	auditType = 3; 
	
	 //获取所有需要审核的中介用户
	getAuditDetails(auditType,1,1,5);
}



/**
 * 获取个人用户详情页的数据
 */
function  getAuditUserContent(id, contentName){
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify({
			"userId" : id,
		}),
		url :  '../audit/getAuditUserInfoDetails',
		success : function(data) {
			if (data.success) {
				//获取所有个人用户成功
				person=data.result;
				//渲染需要审核个人用户的具体信息
				//renderAuditUserInfoContent(person);
				renderAuditContent(contentName);
			} else {
				alert(data.msg);
			}
		},
		error : function(data) {
			alert("连接服务器错误！")
		}
	});
}


/**
 * 渲染表格
 * @param contentName
 */
function renderAuditContent(contentName) {
	//普通文本
	auditText(contentName);
	//json文件
	auditJson(contentName);
	//选项
	auditSelect(contentName);
	//日期
	auditDate(contentName);
	//图片
	auditImg(contentName);
}

/**
 * 操作普通文本
 * @param contentName
 */
function auditText(contentName) {
	var elements = $('#'+ contentName +' span[class*="details-text"]');
	for(var i = 0;i < elements.length;i++) {
		$('#' + elements[i].id).text(person[elements[i].id]);
	}
}
/**
 * json文件
 * @param filedName
 */
function auditJson(contentName) {
	var elements = $('#'+ contentName +' span[class*="details-json"]');
	for(var i = 0;i < elements.length;i++) {
		matchAuditJson(elements[i].id);
	}
}

/***
 * 匹配json文件
 * @param filedName
 */
function matchAuditJson(filedName) {
	var matchValue = matchJsonFile(filedName, person[filedName]);
	$('#' + filedName).text(matchValue);
	
}
/**
 * 选择框类型
 * @param contentName
 */
function auditSelect(contentName) {
	$.ajaxSettings.async = true;
	var elements = $('#' +contentName+ ' span[class*="details-select"]');
	var Ids = [];
	for(var i=0;i<elements.length;i++) {
		//传递控件id，控件值
		Ids.push({
			fatherContent : elements[i].id,
			selectedValue : person[elements[i].id]
		});
	}
	matchSelect("../data/audit/selectData.json", Ids);
	
}

/**
 * 选项框的内容
 * @param url
 * @param Ids
 */
function matchSelect(url,Ids) {
	$.getJSON(url, function(data) {
		$.each(data, function(key,val) {
			for(var i = 0;i<Ids.length;i++) {
				if(Ids[i].fatherContent === key){
					for(var j = 0;j < val.length;j++) {
						if(Ids[i].selectedValue === val[j].id) {
							$('#' + Ids[i].fatherContent).text(val[j].name);
						}
					}
				}
			}
		})
	})
}

/**
 * 操作日期
 * @param contentName
 */
function auditDate(contentName){
	var elements = $('#' +contentName+ ' span[class*="details-date"]');
	for(var i = 0;i<elements.length;i++) {
		$('#' + elements[i].id).text(getLocalTime(person[elements[i].id]));
	}
}

/**
 * 操作图片
 * @param contentName
 */
function auditImg(contentName){
	var elements = $('#' +contentName+ ' img');
	for(var i = 0;i < elements.length;i++) {
		var src = "../../" + person[elements[i].id];
		$('#' + elements[i].id).attr('src', src);
	}
}


/**
 * 弹出审核不通过的原因
 */
function fillFalseModal() {
	//打开模态框
	showModal('../modalTemplates/fillFalseModal.html');
}

/***
 * 提交审核的信息
 * @param auditStatus 审核状态 3表示不通过，4表示通过
 */
function saveAuditInfos(auditStatus) {
	var auditInfo = {
			auditInfoId : person.auditInfoId,//审核id
			auditResult : '', //审核结果
			submitTime : person.submitTime,//提交时间
			auditTime  : person.auditTime,//审核时间
			auditStatus :auditStatus,//审核状态
			auditType : 1,//审核类型，1为用户类型
			userId : person.personId,//被审核人Id
			userName : person.personName,//被审核人姓名
			userType : auditType//被审核人用户类型
	}
	if($('#auditResult').val() == ''){
		toastr.error("审核不通过需要填写原因");
	}else{
		if($('#auditResult').val()!=undefined){
			auditInfo.auditResult = $('#auditResult').val();
			$("#modalModule").modal('hide');
		}else{
			auditInfo.auditResult = '审核通过';
		}
		//提交审核信息
		$.ajax({
			url : '../audit/auditInfos',
			type : 'POST',
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify(auditInfo),
			success : function(data) {
				if(data.success) {
					toastr.success(data.msg);
				}else{
					toastr.error(data.msg);
				}
				//刷新
				getAuditDetails(auditUserType);
				//清空详情页
				$('#detailsModule').empty();
			}
		})
	}
}


/**
 * 跳转详情页
 */
function getAuditInfoDetails(id) {
	getDetailsModule(id);
	/**
	 * 填充详情页信息
	 */
}

/**
 *	判断跳转的详情页
 */
function getDetailsModule(id) {
	switch(auditType) {
		case 1 : getAuditUserDetails(id);break; //个人用户审核详情页	
		case 2 : getAuditCompanyDetails(id);break;//企业详情页
		case 3 : getAuditAgencyDetails(id);break;//中介机构详情页
		case 4 : getAuditCollegeAndScientifyDetails(id);break;//高校审核详情页
		case 5 : getAuditCollegeAndScientifyDetails(id);break;//科研审核详情页
	}
}



