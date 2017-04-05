/*产品成果*/
/*var produce = {};//产品成果类
*/var removeProduceId = '';
var tableRowLength = 0;
//查询条件集合
var produceCondition = {
		produceKey  : '', //被搜索关键字
		produceTypeId : '',//产品类别
		startDate : '',//最早发布时间
		endDate : '',//最迟发布时间
		pageNo : '',//当前页
		pageSize : '',//一页数据的条数
		auditStatus : ''//审核状态
}
/**
 * 创建产品成果
 */
function createProduce() {
	//创建产品成果
	$.ajax({
		url : '../produce/createNewProduce',
		type : 'GET',
		dateType : 'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				person = data.result;
				//动画样式
				$('.block-content').addClass('in');
				$('.block-content').fadeIn(500);
				toastr.success(data.msg);
				$("#product-list").imgscroll({
		            speed: 10,    //图片滚动速度
		            amount: 0,    //图片滚动过渡时间
		            width: 1,     //图片滚动步数
		            dir: "left"   // "left" 或 "up" 向左或向上滚动
		        });
			}else{
				toastr.error(data.msg);
			}
		}
	})
}


/**
 * 通过指定条件查询产品成果信息
 * @param pageNo
 * @param pageSize
 */
function getProducesPageByCondition(pageNo, pageSize) {
	//设置查询参数
	produceCondition.pageNo = pageNo;
	produceCondition.pageSize = pageSize;
	//console.log(produceCondition);
	$.ajax({
		url : '../produce/getProducesPageByCondition',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(produceCondition),
		success : function (data) {
			//console.log(data);
			if(data.success) {
				tableRowLength = data.result.resultList.length;
				renderProduceList(data.result.resultList);
			}
		}
	})
}

/**
 * 清空查询条件
 */
function emptyCondition() {
	//清空查询条件
	for(index in produceCondition) {
		produceCondition[index] = '';
	}
	//清空表单
	var elements = $('#findConditionTable input');
	for(var i = 0;i < elements.length;i++) {
		$('#' + elements[i].id).val('');
	}
	//清空下拉
	var selects = $('#findConditionTable select');
	for(var j = 0;j < selects.length;j++) {
		$('#' + selects[j].id).val('');
		$('#' + selects[j].id).trigger("chosen:updated");
	}
}

/**
 * 渲染表格
 */
function renderProduceList(produceList) {
	//清空查询条件
	emptyCondition();
	//清空容器中原先的内容
	$('#produceTable').empty();
	
	for(var i = 0;i<produceList.length;i++) {
		var appendStr = generateStr(i, "produceTable", produceList[i]);
		
		//添加到容器中
		$('#produceTable').append(appendStr);
	}
	
}

/**
 * 上传图片
 */
function uploadProduce(desc) {
	//console.log(desc);
	if(desc!==''){
		$('#produceImage').fileupload({
			 autoUpload: true,//自动上传
			 dataType : 'json',
			 type: 'POST',
			 url : '../upload/uploadProduceImage',
			 formData : {
				 desc : desc,
				 bindId : person.produceId
			 },
			 success : function(data) {
				 if(data.success){
						//上传成功
					toastr.success(data.msg);
				}else{
					toastr.error(data.msg);
				}
				 //更新数据
				getProduceDetails(person.produceId,true);
				 $("#modalModule").modal('hide');
			 }
		})
	}
}
/**
 * 生成表格指定数据格式
 * @param obj
 * @returns {String}
 */
function generateStr(index, contentName, obj) {
	var tableStr = '<tr><td>' + obj.produceName + 
	'</td><td>'+ matchJsonFile('produceTypeId',obj.produceTypeId) +
	'</td><td>'+ toggleStauts(obj.produceStatus,'可用','禁止') +
	'</td><td>'+ toggleStauts(obj.isRecommend,'是','否')+'</td><td>'+ obj.produceKey +
	'</td><td>'+ obj.pageView +'</td><td>'+ getLocalTime(obj.publishTime) +
	'</td><td><button type="button" class="btn btn-info tableButton" onclick="getProduceDetails('+ obj.produceId +');">详情</button>';
	//需要判断是否为平台管理员级别
	if(adminUser.userType >= 6){
		tableStr += '<button type="button" class="btn btn-success tableButton" onclick="updateProduceStatus('+ index +','+ contentName +',' + obj.produceId +','+ obj.produceStatus +
		')">切换状态</button><button type="button" class="btn btn-primary tableButton" onclick="updateIsRecommenedStatus('+ index +', '+contentName+' ,' + obj.produceId +');">切换推荐</button>';
	}
	tableStr += '<button type="button" class="btn btn-danger tableButton" onclick="deleteModal('+ obj.produceId +');">删除</button></td></tr>';
	
	return tableStr;
}

/**
 * 获取一条数据的详细信息
 * @param produceId
 */
function getProduceDetails(produceId) {
	var state = arguments[1];
	$.ajax({
		url : '../produce/getProduceInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			produceId : produceId
		}),	
		success :  function(data) {
			person = data.result;
			if(state) {
	        	operationProduceImg('produceImgList');
	        	operationProduceChosen();
			}else{
				$.get('./templates/Produce/producePublish.html', function(data) {
			        $('#content').html(data);
			        $('.navTitle').text('产品成果详情');
			        
			        findUserInfoAuditStatus();
			        //渲染数据
		        	renderProduceForm();
				});
			}
	       /*$("#product-list").imgscroll({
	            speed: 10,    //图片滚动速度
	            amount: 0,    //图片滚动过渡时间
	            width: 1,     //图片滚动步数
	            dir: "left"   // "left" 或 "up" 向左或向上滚动
	        });*/
		}
	})
}

/**
 * 渲染表单
 */
function renderProduceForm() {
	//渲染菜单
	operationText('produceForm');
	operationTextarea('produceForm');
	//单选框
	operationRadio('produceForm');
	//下拉框
	operationProduceChosen();
	operationProduceImg('produceImgList');
	operationProduceJson('produceForm');
	
}

/**
 * 操作下拉框
 */
function operationProduceChosen() {
	if(person.images.length === 0) {
		$('#homePageImageId').hide();
	}else{
		var data = [];
		for(var i = 0;i < person.images.length;i++) {
			data.push({
				id : person.images[i].produceImageId,
				name: person.images[i].produceImageDesc
			})
		}
		getOption(data,'homePageImageId',true,person.homePageImageId);
	}
	
}

/**
 * 遍历图片
 */
function operationProduceImg(contentName) {
	$('#' + contentName).empty();
	for(var i = 0;i < person.images.length;i++) {
		$('#' + contentName).append(generateImgList(person.images[i]))
	}
}
/**
 * 渲染图片
 * @param data
 * @returns {String}
 */
function generateImgList(data) {
	var url = '../../' + data.produceImageUrl;
	return "<li class='marquee-item'><img class='marquee-image' src='"+ url +"' alt='...'><p class='marquee-content'><a href='#'> " +data.produceImageDesc +"</a> </p></li>"
}

/**
 * 匹配json文件
 * @param contentName
 */
function operationProduceJson(contentName) {
	var elements = $('#' +contentName+ ' input[class*="form-json"]');
	for(var i= 0; i <elements.length;i++) {
		$('#' +elements[i].id ).val(matchJsonFile(elements[i].id,person[elements[i].id]));
	}
}

/**
 * 保存产品成果信息为草稿
 */
function saveProduce() {
	getProduceFormFiled();
	console.log(person);
	$.ajax({
		url : '../produce/saveProduce',
		type : 'PUT',
		dateType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(person),	
		success : function(data) {
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			getProduceManagment({name : '管理我的产品成果'});
		}
	});
}

/**
 * 提交一个新的产品成果
 */
function submitNewProduce() {
	getProduceFormFiled();
	setTimeout(function() {
		$.ajax({
			url : '../produce/submitNewProduce',
			type : 'PUT',
			dateType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(person),	
			success : function(data) {
				if(data.success) {
					toastr.success(data.msg);
				}else{
					toastr.error(data.msg);
				} 	
				getProduceManagment({name : '管理我的产品成果'});
			}
		})
	},300);
}
/**
 * 获取表单信息
 */
function getProduceFormFiled() {
	//console.log($('#produceForm'));
	$.each($('#produceForm').serializeArray(), function() {
		/*console.log(this.name + "====>" + this.value);*/
		if(this.name === 'produceTypeId'){
			var value = this.value;
			$.getJSON('../data/audit/produceType.json', function(data) {
				for(var i = 0;i< data.length;i++) {
					if(data[i].name === value) {
						person.produceTypeId = data[i].id;
					}
				}
			})
		}else{
			person[this.name] = this.value;
		}
    });
}


/**
 * 更新产品成果状态
 * @param produceId
 * @param produceStatus
 */
function updateProduceStatus(index, contentName, produceId, produceStatus) {
	$.ajax({
		url : '../produce/updateProduceStatus',
		type : 'PUT',
		dateType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			produceId : produceId,
			produceStatus : produceStatus === 1 ? 2 : 1
		}),	
		success : function(result) {
			if(result.success) {
				toastr.success(result.msg);
				updateAssignPerform(contentName.id, index, produceId);//更新表格指定列
			}else{
				toastr.error(result.msg);
			}
		}
	})
}


/***
 * 更新产品成果推荐状态
 */
function updateIsRecommenedStatus(index, contentName, produceId) {
	$.ajax({
		url : '../produce/updateIsRecommenedStatus',
		type : 'PUT',
		dataType : 'json',
		async : false,
		cache : false,
		contentType : 'application/json',
		data : JSON.stringify({
			produceId : produceId
		}),	
		success : function(result) {
			if(result.success) {
				toastr.success(result.msg);
				updateAssignPerform(contentName.id, index, produceId);//更新表格指定列
			}else{
				toastr.error(result.msg);
			}
		}
	})
}

/**
 * 更新菜单指定项
 * @param contentName
 * @param index
 */
function updateAssignPerform(contentName, rowIndex,  produceId) {
	//通过id获取指定数据'
	$.ajax({
		url : '../produce/getProduceInfoById',
		type : 'POST',
		dataType : 'json',
		async : false,
		cache : false,
		contentType : 'application/json',
		data : JSON.stringify({
			produceId : produceId
		}),	
		success :  function(data) {
			 //console.log(data.result);
			//先删除指定列，然后添加数据
			//console.log(generateStr(rowIndex, contentName, data.result));
			$('#' + contentName +' tr:eq('+ rowIndex +')').remove();
			if(tableRowLength === (rowIndex + 1)){
				//最后一行
				$('#' + contentName).append(generateStr(rowIndex, contentName, data.result));
			}else{
				$('#' + contentName +' tr:eq('+ rowIndex +')').before(generateStr(rowIndex, contentName, data.result));
			}
		}
	})
}



/**
 * 指定条件查询
 */
function findProduceCondition(){
	//填充查询条件
	produceCondition.produceTypeId = person.produceTypeId || '';
	produceCondition.produceKey = $('#produceKey').val() || '';
	produceCondition.startDate = getTimeStamp($('#startDate').val()) || '';
	produceCondition.endDate = getTimeStamp($('#endDate').val()) || '';
	getProducesPageByCondition(1,5);
}


/**
 * 删除产品成果模态框
 */
function deleteModal(produceId){
	showModal('../modalTemplates/removeProduce.html');
	
	removeProduceId = produceId;
}
/**
 * 确定删除指定产品成果
 */
function removeProduce() {
	$.ajax({
		url : '../produce/removeProduce',
		type : 'DELETE',
		data : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			produceId : removeProduceId
		}),	
		success : function(result) {
			if(result.success) {
				toastr.success(result.msg);
			}else{
				toastr.error(result.msg);
			}
			$("#modalModule").modal('hide');
			getProducesPageByCondition(1,5);
		}
	})
}

/**
 * 打开上传图片的模态框
 */
function showproduceImg() {
	showModal('../modalTemplates/produceImg.html');
	setTimeout(function() {
		$('#desc')[0].focus();
		$('#produceImgContent input[class*="imgContentBtn"]').attr('disabled', "true");
		//监听描述的改变，只有由输入才能上传文件
		$('#desc').on('keyup', function() {
			if($('#desc').val() !== ''){
				$('#produceImgContent input[class*="imgContentBtn"]').removeAttr('disabled');
				//监听上传
				uploadProduce($('#desc').val());
			}else{
				$('#produceImgContent input[class*="imgContentBtn"]').attr('disabled', 'disabled');
			}
		});
		
	},200);
}


/**
 * 状态判断
 * @param ele 判断对象
 * @param successMsg 成功状态
 * @param errMsg 失败状态
 * @returns
 */
function toggleStauts(ele, successMsg, errMsg) {
	//首次渲染
	return ele === 1 ? successMsg : errMsg;
}

/**
 * 匹配json文件
 * @param fieldName 字段名
 * @param matchValue 匹配值
 * @returns {String} 返回匹配成功结果
 */
function matchJsonFile(fieldName, matchValue) {
	var returnData = '';
	var url = "../data/audit/" + fieldName.substring(0,fieldName.length-2) + ".json";
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
 * **********************   产品成果审核              ************************************
 */
/**
 * 产品审核条件查询模型
 */
var produceCondition = {
		produceKey : '',//产品关键字
		produceTypeId  : '',//产品类别Id
		startDate : '',//最早发布时间
		endDate : '',//最迟发布时间
		pageNo : '',//当前页
		pageSize : ''//每页显示条数
}
/**
 * 设置当前状态为产品成果审核和获取默认前五条信息
 */
function getProduceAuditInfo() {
	auditType = 6;//设置当前操作状态为产品成果审核
	getAuditProducePage(1,5);
	setAuditStautsInfo();
	
	//监听审核状态的改变
	$('#auditStatusContent').on('change', function() {
		produceCondition.auditStatus = this.value;
		getAuditProducesPageByCondition();
	})
}

/**
 * 填充审核状态
 */
function setAuditStautsInfo() {
	var selectData = [];
	//填充下拉框
	$.getJSON('../data/managment/auditManagementStatus.json',function(data) {
		for(var i = 0; i<data.length;i++) {
			selectData.push({
				id : data[i].id,
				name : data[i].name
			});
		}
	})
	setTimeout(function() {
		getOption(selectData,'auditStatusContent',true,1);
	},200);
}
/**
 * 每页默认查询的数据
 * @param pageNo
 * @param pageSize
 */
function getAuditProducePage(No, Size) {
	//清空其他模块查询的条件信息
	for(index in produceCondition) {
		produceCondition[index] = '';
	}
	//设置本次查询的条件信息
	produceCondition.pageNo = No;
	produceCondition.pageSize  = Size;
	produceCondition.auditStatus = -1;//获取待审核名单
	getAuditProducesPageByCondition();
}

/**
 * 查询指定条件的产品成果信息集合
 */
function getAuditProducesPageByCondition() {
	//console.log(produceCondition);
	$.ajax({
		url : '../produce/getProducesPageByCondition',
		type : "POST",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(produceCondition),
		success : function(data) {
			renderProduce('produceAuditTable', data.result.resultList);
		}
	})
}

/**
 * 渲染表单
 * @param data
 */
function renderProduce(contentName, data) {
	
	$('#' + contentName).empty();
	for(var i=0;i<data.length;i++){
		$('#' + contentName).prepend("<tr><td>"+data[i].produceName+"</td><td>"+getLocalTime(data[i].publishTime)+"</td><td><button class=\"btn btn-info\" onclick=\"findAuditProduce('"+data[i].produceId+"');\">审核</button></td></tr>");
	}
	$('.resultLength').html(data.length);
}

/**
 * 获取审核产品成果的详细信息
 * @param produceId
 */
function findAuditProduce(produceId) {
	//获取主要信息
	getAuditProduce(produceId);
	$('.block-content').fadeIn(500);
	//渲染表格
	setTimeout(function() {
		renderProduceTable('produceDetailsTable');
	},200);
	
}

/**
 * 获取审核产品成果中的一条详细信息
 * @param produceId
 */
function getAuditProduce(produceId) {
	$.ajax({
		url : '../produce/getProduceInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			produceId : produceId
		}),	
		success :  function(data) {
			person = data.result;
		}
	})
}


function renderProduceTable(contentName) {
	//普通文本框
	auditText(contentName);
	//手动渲染
	auditProduceSelect(contentName);
	//json文件
	$('#' + contentName +' #produceTypeId').text(matchJsonFile('produceTypeId',person.produceTypeId))
	//渲染图片
	operationProduceImg('produceAuditImgList');
	//渲染首页图片
	operationProduceHomeImg();
}
/**
 * 选项
 * @param contentName
 */
function auditProduceSelect(contentName) {
	//console.log(person);
	$('#' + contentName +' #isRecommend').text(toggleStauts(person.isRecommend,'是','否'));
}

/**
 * 渲染首页图片
 */
function operationProduceHomeImg() {
	//homepageImg
	for(var i = 0;i < person.images.length;i++) {
		if(person.images[i].produceImageId ===  person.homePageImageId){
			var url = '../../' + person.images[i].produceImageUrl;
			$('#homepageImg').attr('src', url);
			$('#homepageImgText').text(person.images[i].produceImageDesc);
		}
	}
}

/**
 * 产品审核通过
 */
function saveProduceAudit(auditStatus) {
	var auditInfo = {
			auditInfoId : person.auditInfoId,//审核id
			auditResult : '', //审核结果
			auditStatus :auditStatus,//审核状态
			auditType : 4,//审核类型，4为产品成果
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
		console.log(auditInfo)
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
				getProduceAuditInfo();
				//清空详情页
				$('#produceDetails').fadeOut(500);
			}
		})
	}
}



/**
 * 弹出审核不通过的原因
 */
function fillProduceFalseModal() {
	//打开模态框
	showModal('../modalTemplates/fillProduceFalseModal.html');
}

/**
 * 审核页面查询
 */
function findProduceAuditCondition() {
	produceCondition.produceTypeId = person.produceTypeId || '';
	produceCondition.produceKey = $('#produceKey').val() || '';
	produceCondition.startDate = getTimeStamp($('#startDate').val()) || '';
	produceCondition.endDate = getTimeStamp($('#endDate').val()) || '';
	getAuditProducesPageByCondition();
}