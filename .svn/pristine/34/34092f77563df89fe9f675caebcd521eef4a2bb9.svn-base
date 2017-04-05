/**
 * 	**********************     技术供给 **********************
 */
/**
 * 技术查询条件集
 */
var techCondition = {
	key : '',//关键字
	industryId : '', //所属行业
	htfId : '',//高新技术领域
	startDate : '',//最早发布时间
	endDate : '',//最迟发布时间
	auditStatus : '',//审核状态
	techStatus  : '',//产品状态
	pageNo : '',//页数
	pageSize : ''//每页条数
}
var person = [];
//删除技术供给Id
var removeSupplyId = '';
//删除技术供给Id
var removeRequired = '';

var uploadSupplyType = 0;

/**
 * 创建新的技术供给
 */
function createSupply() {
	$.ajax({
		url : '../supply/createNewTechSupplyInfo',
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				person = data.result;
				//渲染表单
				renderInfo('techSupplyForm');
				//填充数据
				setTechData('techSupplyForm', false);
				
				
				//console.log(person);
				$('.block-content').addClass('in');
				$('.block-content').fadeIn(500);
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 保存技术供给信息
 */
function saveTechSupplyInfo(){
	//获取表单的所有信息
	getFormField();
	//console.log(person);
	$.ajax({
		url : '../supply/saveTechSupplyInfo',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(person),
		success : function(data) {
			if(data.success) {
				//成功，跳转到管理页面
				toastr.success(data.msg);
				getTechSupplyManagment({name : '管理我的技术供给'});
			}else{
				toastr.error(data.msg);
			}
		}
		
	})
}

/**
 * 提交技术供给
 */
function submitTechSupplyInfo() {
	getFormField();
	//console.log(person);
	$.ajax({
		url : '../supply/submitTechSupplyInfo',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(person),
		success : function(data) {
			if(data.success) {
				//成功，跳转到管理页面
				toastr.success(data.msg);
				getTechSupplyManagment({name : '管理我的技术供给'});
			}else{
				toastr.error(data.msg);
			}
		}
		
	})
}

/**
 * 普通查询，即只是默认查询状态
 */
function ordinaryTechPageByCondition(pageNo, pageSize){
	//清空查询条件
	clearCondition();
	techCondition.pageNo = pageNo; 
	techCondition.pageSize = pageSize;
	getTechPageByCondition();
}

/**
 * 清除查询条件
 */
function clearCondition() {
	for(index in techCondition) {
		techCondition[index] = '';
	}
}
/**
 * 通过指定条件查询
 */
function SpecifyTechPageByCondition() {
	//clearCondition();//清空查询条件
	techCondition.pageNo = 1; 
	techCondition.pageSize = 5;
	//console.log(person);
	techCondition.key = $('#key').val();//关键字
	techCondition.auditStatus = $('#supplyAuditStatus').val() == "999" ? '' : $('#supplyAuditStatus').val();//状态
	techCondition.startDate = getTimeStamp($('#startDate').val()) || '';//最早时间
	techCondition.endDate = getTimeStamp($('#endDate').val()) || '';//最迟时间
	//console.log(person);
	techCondition.industryId = person.industryId || ''; //所属行业
	techCondition.htfId= person.htfId || '';//高新技术领域
	techCondition.techStatus= $('#techStatus').val() == "999" ? '' : $('#supplyAuditStatus').val();//技术需求
	//console.log(techCondition);
	getTechPageByCondition();
}

/**
 * 按指定条件查询数据
 */
function getTechPageByCondition() {
	$.ajax({
		url : '../supply/getTechPageByCondition',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(techCondition),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				//获取成功
				//渲染进表格
				tableRowLength = data.result.resultList.length;
				renderSupplyTable(data.result.resultList);
			}
		}
	})
}

/**
 * 删除技术供给信息
 */
function removeTechSupply() {
	$.ajax({
		url : '../supply/removeTechSupply',
		type : 'DELETE',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			tsiId : removeSupplyId
		}),
		success : function(data) {
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			//以当前查询条件查询数据并刷新表格
			getTechPageByCondition();
			 $("#modalModule").modal('hide');   
		}
	})
}

/**
 * 通过Id查询一整条
 * @param supplyId
 */
function getTechSupplyInfoById(supplyId) {
	var state = arguments[1];
	$.ajax({
		url : '../supply/getTechSupplyInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			tsiId : supplyId
		}),
		success : function(data) {
			if(data.success) {
				person = data.result;
				console.log(person);
				if(state) {
					//只更新对应的附件
					switch(uploadSupplyType) {
						case 1 : operationSupplyImg('supplyImgContent');break;
						case 2 : operationSupplyVideo('videoContent');break;
						case 3 : operationSupplyText('textContent');break;
					}
				}else{
					//获取成功，跳转
					$.get('./templates/supply/techSupplyPublish.html', function(data) {
				        $('#content').html(data);
				        $('.navTitle').text('技术供给详情');
				        //渲染数据
				        renderInfo('techSupplyForm');
				        operationSupplyImg('supplyImgContent');
				        operationSupplyVideo('videoContent');
				        operationSupplyText('textContent');
				        findUserInfoAuditStatus();
				        setTimeout(function() {
				        	//填充数据
							setTechData('techSupplyForm', true);
				        },100);
				    });
				}
				
				//console.log(person);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 渲染图片
 * @param contentName
 */
function operationSupplyImg(contentName) {
	$('#' + contentName).empty();
	if(person.imageUrl !== null) {
		$('#' + contentName).append(generateSupplyImgList(person.imageUrl,person.imageDesc));
	}
}
/**
 * 渲染视频
 * @param contentName
 */
function operationSupplyVideo(contentName) {
	$('#' +contentName).empty();
	if(person.videoUrl !== null) {
		var url = '../../' + person.videoUrl;
		$('#' + contentName).append('附件：&nbsp;&nbsp;<a href="'+ url +'">'+ person.videoDesc +'</a>');
	}
	
}
/**
 * 渲染文本文件
 * @param contentName
 */
function operationSupplyText(contentName) {
	$('#' +contentName).empty();
	if(person.textUrl !== null) {
		var url = '../../' + person.textUrl;
		$('#' + contentName).append('附件：&nbsp;&nbsp;<a href="'+ url +'">'+ person.textDesc +'</a>');
	}
	
}

/***
 * 渲染图片
 * @param imageUrl 图片路径
 * @param imageDesc 图片描述
 * @returns {String} 返回字符串
 */
function generateSupplyImgList(imageUrl, imageDesc) {
	var url = '../../' + imageUrl;
	return "<li class='marquee-item'><img class='marquee-image' src='"+ url +"' alt='...'><p class='marquee-content'><a href='#'> " +imageDesc +"</a> </p></li>"
}
/**
 * 上传附件监听（附件图片、附件文本、附件视频）
 */
function uploadSupply(desc) {
	//附件图片
	switch(uploadSupplyType){
		case 1 : uploadTechSupplyEnclosure(desc, '../upload/uploadTechSupplyImageEnclosure');break;//图片
		case 2 : uploadTechSupplyEnclosure(desc, '../upload/uploadTechSupplyVideoEnclosure');break;//视频
		case 3 : uploadTechSupplyEnclosure(desc,'../upload/uploadTechSupplyTextEnclosure');break;//文本
	}
	
}

/**
 * 上传附件图片
 */
function uploadTechSupplyEnclosure(desc,url) {
	if(desc!==''){
		$('#supplyFile').fileupload({
			 autoUpload: true,//自动上传
			 dataType : 'json',
			 type: 'POST',
			 url : url,
			 formData : {
				 desc : desc,//描述
				 bindId : person.tsiId//供给id
			 },
			 success : function(data) {
				 if(data.success){
						//上传成功
					toastr.success(data.msg);
				}else{
					toastr.error(data.msg);
				}
				 //更新数据
				 getTechSupplyInfoById(person.tsiId,true);
				 $("#modalModule").modal('hide');
			 }
		})
	}
}



/**
 * 打开删除技术供给确认模态框
 * @param supplyId 指定输出供给Id
 */
function showRemoveModal(supplyId) {
	removeSupplyId = supplyId;
	showModal('../modalTemplates/removeSupply.html');
}

/**
 * 上传模态框
 * @param title 标题类型
 */
function showSupplyModal(title, uploadType) {
	uploadSupplyType = uploadType;//设置上传类型
	showModal('../modalTemplates/uploadSupply.html');
	setTimeout(function() {
		$('.title').text(title);
		
		$('#desc')[0].focus();
		$('#uploadSupplyContent input[class*="imgContentBtn"]').attr('disabled', "true");
		//监听描述的改变，只有由输入才能上传文件
		$('#desc').on('keyup', function() {
			if($('#desc').val() !== ''){
				$('#uploadSupplyContent input[class*="imgContentBtn"]').removeAttr('disabled');
				//监听上传
				uploadSupply($('#desc').val());
			}else{
				$('#uploadSupplyContent input[class*="imgContentBtn"]').attr('disabled', 'disabled');
			}
		});
	},300);
	
}

/**
 * 渲染技术供给表格
 * @param data 技术供给数据
 */
function renderSupplyTable(data) {
	$('#supplyTable').empty();
	for(var i = 0;i<data.length;i++) {
		$('#supplyTable').append(assembledSupplyStr(i,data[i],'supplyTable'));
	}
}

/**
 * 拼装字符串
 * @param obj 要拼装的供给对象
 */
function assembledSupplyStr(index, obj,contentName) {
	//console.log(obj);
	return '<tr><td>'+ obj.infoTitle +'</td><td>'+ matchJsonFile('locationId', obj.locationId) +
	'</td><td>'+ matchJsonFile('industryId', obj.industryId) +'</td><td>'+ matchJsonFile('htfId', obj.htfId)+
	'</td><td>'+ obj.price+'</td><td>'+ obj.contactName +'</td><td>'+ getLocalTime(obj.createTime) +
	'</td><td>'+ toggleStauts(obj.status,'已成交', '未成交')+'</td><td><button type="button" class="btn btn-info" style="margin-right:14px;" onclick="getTechSupplyInfoById('+ obj.tsiId +
	', false)">详情</button><button type="button" class="btn btn-default" style="margin-right:14px;" onclick="updateTechSupplyStatus('+ index +', '+contentName+' ,' + obj.tsiId +
	')">切换状态</button><button type="button" class="btn btn-danger" onclick="showRemoveModal('+ obj.tsiId +');">删除</button></td></tr>'
}


/**
 * 更新技术供给状态
 * @param index
 * @param contentName
 * @param supplyId
 */
function updateTechSupplyStatus(index, contentName, supplyId) {
	$.ajax({
		url : '../supply/updateTechSupplyStatus',
		type : 'PUT',
		dataType : 'json',
		async : false,
		cache : false,
		contentType : 'application/json',
		data : JSON.stringify({
			tsiId : supplyId
		}),	
		success : function(result) {
			if(result.success) {
				toastr.success(result.msg);
				updateAssignTechSupplyPerform(contentName.id, index, supplyId);//更新表格指定列
			}else{
				toastr.error(result.msg);
			}
		}
	})
}

/**
 * 更新菜单项
 * @param contentName
 * @param rowIndex
 * @param supplyId
 */
function updateAssignTechSupplyPerform(contentName, rowIndex, supplyId) {
	$.ajax({
		url : '../supply/getTechSupplyInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			tsiId : supplyId
		}),
		success : function(data) {
			if(data.success) {
				$('#' + contentName +' tr:eq('+ rowIndex +')').remove();
				if(tableRowLength === (rowIndex + 1)){
					//最后一行
					$('#' + contentName).append(assembledSupplyStr(rowIndex,  data.result, contentName));
				}else{
					$('#' + contentName +' tr:eq('+ rowIndex +')').before(assembledSupplyStr(rowIndex,  data.result, contentName));
				}
			}else{
				toastr.error(data.msg);
			}
		}
	})
}


/**
 * 填充下拉框数据
 */
function setTechData(contentName, state) {
	console.log(person);
	var Ids = [];
	var elements = $('#' +contentName+ ' select');
	for(var i=0;i<elements.length;i++) {
		if(state) {
			//传递控件id，控件值
			Ids.push({
				fatherContent : elements[i].id,
				selectedValue : person[elements[i].id] || ''
			});
		}else{
			Ids.push({
				fatherContent : elements[i].id
			});
		}
		
	}
	getTechSelectData(Ids, state);
	
}
/**
 * 填充下拉框内容
 */
function getTechSelectData() {
	var ids = arguments[0];
	var state = arguments[1];
	for(var i = 0 ;i< ids.length;i++) {
		(function(i) {
			var matchName = ids[i].fatherContent;
			var url = '../data/supply/' + matchName.substring(0,matchName.length-2) + '.json';
			$.getJSON(url,  function(data){
				//console.log(matchName);
				//getOption(val,key,true,ids[i].selectedValue);
				if(state) {
					getOption(data,matchName,true,ids[i].selectedValue);
				}else{
					getOption(data, matchName);
				}
			});
		})(i);
	}
}

/**
 * 填充下拉选择框
 * @param matchName
 */
function getTechStatusSelect(matchName) {
	var url = '../data/managment/auditStatus.json';
	$.getJSON(url,  function(data){
		//data.push({"id" : "", "name" : "全部"});
		//console.log(data);
		getOption(data, matchName);
	});
	$('#techStatus').chosen();
}
/**
 * 	**********************     技术需求   **********************
 */

/**
 * 创建一个新的技术需求
 */
function createNewTechRequireInfo() {
	$.ajax({
		url : '../requirement/createNewTechRequireInfo',
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				person = data.result;
				//渲染表单
				renderInfo('techRequireForm');
				//填充数据
				setTechData('techRequireForm', false);
				
				//console.log(person);
				$('.block-content').addClass('in');
				$('.block-content').fadeIn(500);
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 保存一个技术需求
 */
function saveTechRequireInfo() {
	//获取表单的所有信息
	getFormField();
	//console.log(person);
	$.ajax({
		url : '../requirement/saveTechRequireInfo',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(person),
		success : function(data) {
			if(data.success) {
				//成功，跳转到管理页面
				toastr.success(data.msg);
				getTechRequireManagment({name : '管理我的技术需求'});
			}else{
				toastr.error(data.msg);
			}
		}
		
	})
}
/**
 * 提交技术需求
 */
function submitTechRequireInfo() {
	getFormField();
	//console.log(person);
	$.ajax({
		url : '../requirement/submitTechRequireInfo',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(person),
		success : function(data) {
			if(data.success) {
				//成功，跳转到管理页面
				toastr.success(data.msg);
				getTechRequireManagment({name : '管理我的技术需求'});
				$(".datepicker").datepicker({
		        	format: 'yyyy/mm/dd',
		            startDate: '-3d'
		        });
		        
		        //下拉框
		        setTimeout(function() {
		        	 getTechStatusSelect('requireAuditStatus');
		        },200);
			}else{
				toastr.error(data.msg);
			}
		}
		
	})
}

/**
 * 普通查询，即只是默认查询状态
 */
function ordinaryTechRequirePageByCondition(pageNo, pageSize){
	//清空查询条件
	for(index in techCondition) {
		techCondition[index] = '';
	}
	techCondition.pageNo = pageNo; 
	techCondition.pageSize = pageSize;
	getTechRequiePageByCondition();
}
/**
 * 通过指定条件查询
 */
function SpecifyTechRequirePageByCondition() {
	//console.log(person);
	techCondition.key = $('#key').val();//关键字
	techCondition.auditStatus = $('#requireAuditStatus').val() == "999" ? '' : $('#requireAuditStatus').val();//状态
	techCondition.startDate = getTimeStamp($('#startDate').val()) || '';//最早时间
	techCondition.endDate = getTimeStamp($('#endDate').val()) || '';//最迟时间
	//console.log(person);
	techCondition.industryId = person.industryId || ''; //所属行业
	techCondition.htfId = person.htfId || '';//高新技术领域
	techCondition.techStatus= $('#techStatus').val() == "999" ? '' : $('#techStatus').val();//技术需求
	//console.log(techCondition);
	getTechRequiePageByCondition();
}


/**
 * 按指定条件查询数据
 */
function getTechRequiePageByCondition() {
	$.ajax({
		url : '../requirement/getTechPageByCondition',
		type : 'POST',
		dataType : 'json',
		cache : false,
		contentType : 'application/json',
		data :  JSON.stringify(techCondition),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				//获取成功
				//渲染进表格
				tableRowLength = data.result.resultList.length;
				renderRequireTable(data.result.resultList);
			}
		}
	})
}
/**
 * 打开删除技术确认模态框
 * @param supplyId 指定输出供给Id
 */
function showRemoveRequireModal(requireId) {
	removeRequired = requireId;
	//console.log(removeRequired);
	showModal('../modalTemplates/removeRequire.html');
}

/**
 * 删除技术需求信息
 */
function removeTechRequire() {
	$.ajax({
		url : '../requirement/removeTechRequireInfo',
		type : 'DELETE',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			triId : removeRequired
		}),
		success : function(data) {
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			//以当前查询条件查询数据并刷新表格
			getTechRequiePageByCondition();
			 $("#modalModule").modal('hide');   
		}
	})
}

/**
 * 通过Id查询一整条
 * @param supplyId
 */
function getTechRequireInfoById(requireId) {
	$.ajax({
		url : '../requirement/getTechRequireInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			triId : requireId
		}),
		success : function(data) {
			if(data.success) {
				person = data.result;
				//console.log(person);
				//获取成功，跳转
				$.get('./templates/require/techRequirePublish.html', function(data) {
			        $('#content').html(data);
			        $('.navTitle').text('技术需求详情');
			        //渲染数据
			        renderInfo('techRequireForm');
			        setTimeout(function() {
			        	//填充数据
						setTechData('techRequireForm', true);
			        },100);
			    });
				findUserInfoAuditStatus();
				//console.log(person);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 渲染技术需求表格
 * @param data 技术供给数据
 */
function renderRequireTable(data) {
	$('#requireTable').empty();
	for(var i = 0;i<data.length;i++) {
		$('#requireTable').append(assembledRequireStr(i, data[i],'requireTable'));
	}
}

/**
 * 拼装字符串
 * @param obj 要拼装的供给对象
 */
function assembledRequireStr(index, obj,contentName) {
	//console.log(obj);
	return '<tr><td>'+ obj.infoTitle +'</td><td>'+ matchJsonFile('locationId', obj.locationId) +
	'</td><td>'+ matchJsonFile('industryId', obj.industryId) +'</td><td>'+ matchJsonFile('htfId', obj.htfId)+
	'</td><td>'+ obj.contactName +'</td><td>'+ getLocalTime(obj.createTime) +
	'</td><td>'+ toggleStauts(obj.status,'已解决', '未解决')+'</td><td><button type="button" class="btn btn-info" style="margin-right:14px;" onclick="getTechRequireInfoById('+
	obj.triId +')">详情</button><button type="button" class="btn btn-default" style="margin-right:14px;" onclick="updateTechRequireStatus('+ index +', '+contentName+' ,' + obj.triId +
	')">切换状态</button><button type="button" class="btn btn-danger" onclick="showRemoveRequireModal('+ obj.triId +');">删除</button></td></tr>'
}

/**
 * 技术需求切换状态
 * @param index
 * @param contentName
 * @param requireId
 */
function updateTechRequireStatus(index, contentName, requireId) {
	$.ajax({
		url : '../requirement/updateTechRequireStatus',
		type : 'PUT',
		dataType : 'json',
		async : false,
		cache : false,
		contentType : 'application/json',
		data : JSON.stringify({
			triId : requireId
		}),	
		success : function(result) {
			if(result.success) {
				toastr.success(result.msg);
				updateAssignTechRequirePerform(contentName.id, index, requireId);//更新表格指定列
			}else{
				toastr.error(result.msg);
			}
		}
	})
}

/**
 * 更新菜单项
 * @param contentName
 * @param rowIndex
 * @param requireId
 */
function updateAssignTechRequirePerform(contentName, rowIndex, requireId) {
	$.ajax({
		url : '../requirement/getTechRequireInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			triId : requireId
		}),
		success : function(data) {
			if(data.success) {
				//person = data.result;
				//console.log(person);
				//获取成功，跳转
				$('#' + contentName +' tr:eq('+ rowIndex +')').remove();
				if(tableRowLength === (rowIndex + 1)){
					//最后一行
					$('#' + contentName).append(assembledRequireStr(rowIndex,data.result, contentName));
				}else{
					$('#' + contentName +' tr:eq('+ rowIndex +')').before(assembledRequireStr(rowIndex,  data.result, contentName));
				}
			}else{
				toastr.error(data.msg);
			}
		}
	})
}


/**
 * ************************  技术供给审核          **************************
 */

/**
 * 默认获取待审核的前5条数据
 */
function getAuditSupplyByCondition() {
	if(!arguments[0]) {
		techCondition.auditStatus = 1;//待审核
	}
	techCondition.pageNo = 1;
	techCondition.pageSize = 5;
	console.log(techCondition);
	setAuditStautsInfo();
	getTechAuditPageByCondition('../supply/getTechPageByCondition',1);//默认前5条信息
	
	//监听审核状态的改变
	$('#auditStatusContent').on('change', function() {
		techCondition.auditStatus = this.value;
		getTechAuditPageByCondition('../supply/getTechPageByCondition',1);//默认前5条信息
	})
}

/**
 * 指定url渲染表格
 * @param url
 */
function getTechAuditPageByCondition(url,techType) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(techCondition),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				//获取成功
				//渲染进表格
				renderTech('supplyAuditTable', data.result.resultList,techType);
			}
		}
	})
}

/**
 * 渲染审核类型
 * @param contentName 数据显示容器
 * @param data 数据
 * @param techType 操作类型 1为供给。2为审核。主要区分Id名
 */
function renderTech(contentName, data, techType) {
	$('#' + contentName).empty();
	for(var i=0;i<data.length;i++){
		var appendStr = "<tr><td>"+data[i].infoTitle+"</td><td>"+getLocalTime(data[i].createTime)+"</td><td>";
		if(techType === 1) {
			//供给
			appendStr += "<button class=\"btn btn-info\" onclick=\"findAuditSupply('"+data[i].tsiId+"');\">审核</button></td></tr>";
		}else if(techType === 2) {
			//需求
			appendStr += "<button class=\"btn btn-info\" onclick=\"findAuditRequire('"+data[i].triId+"');\">审核</button></td></tr>";
		}
		$('#' + contentName).prepend(appendStr);
	}
	$('.resultLength').html(data.length);
}



/**
 * 审核页面查询一条完整的供给信息
 * @param supplyId 供给Id
 */
function findAuditSupply(supplyId) {
	$.ajax({
		url : '../supply/getTechSupplyInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			tsiId : supplyId
		}),
		success : function(data) {
			if(data.success) {
				person = data.result;
				$('.block-content').fadeIn(500);
				//渲染表格
				setTimeout(function() {
					renderTechTable('auditSupplyDetailsForm');
				},150);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 审核页面查询
 */
function findTechAuditCondition(type) {
	//clearCondition();
	techCondition.industryId = person.industryId || '';
	techCondition.key = $('#key').val() || '';
	techCondition.startDate = getTimeStamp($('#startDate').val()) || '';
	techCondition.endDate = getTimeStamp($('#endDate').val()) || '';
	if(type === 1) {
		getAuditSupplyByCondition(true);
	}else if(type === 2) {
		getAuditRequireByCondition(true);
	}
	
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
	
}


/***
 * 提交审核的信息
 * @param auditStatus 审核状态 3表示不通过，4表示通过
 */
function saveAuditTechInfos(auditType, auditStatus) {
	var auditInfo = {
			auditInfoId : person.auditInfoId,//审核id
			auditResult : '', //审核结果
			auditStatus :auditStatus,//审核状态
			auditType : auditType,//审核类型，1为用户类型
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
				if(auditType === 2) {
					getAuditSupplyByCondition();//供给
				}else if (auditType === 3) {
					getAuditRequireByCondition();//需求
				}
				
				//清空详情页
				$('#techDetails').hide();
			}
		})
	}
}

/**
 * 打开审核供给失败模态框
 */
function fillFalseTechSupplyModal() {
	showModal('../modalTemplates/fillFalseTechSupplyModal.html');
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
	var url = "../data/supply/" + fieldName.substring(0,fieldName.length-2) + ".json";
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
 * ************************  技术需求审核          **************************
 */


/**
 * 默认获取待审核的前5条数据
 */
function getAuditRequireByCondition() {
	//clearCondition();
	if(!arguments[0]){
		techCondition.auditStatus = 1;//待审核
	}
	techCondition.pageNo = 1;
	techCondition.pageSize = 5;
	setAuditStautsInfo();
	getTechAuditPageByCondition('../requirement/getTechPageByCondition',2);//默认前5条信息
	
	//监听审核状态的改变
	$('#auditStatusContent').on('change', function() {
		techCondition.auditStatus = this.value;
		getTechAuditPageByCondition('../requirement/getTechPageByCondition',2);
	});
}

/**
 * 审核页面查询一条完整的需求信息
 * @param requireId 需求Id
 */
function findAuditRequire(requireId) {
	$.ajax({
		url : '../requirement/getTechRequireInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			triId : requireId
		}),
		success : function(data) {
			if(data.success) {
				person = data.result;
				console.log(person);
				$('.block-content').fadeIn(500);
				//渲染表格
				setTimeout(function() {
					renderTechTable('auditRequireDetailsForm');
				},150);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}


/**
 * 打开审核需求失败模态框
 */
function fillFalseTechRequireModal() {
	showModal('../modalTemplates/fillFalseTechRequireModal.html');
}


