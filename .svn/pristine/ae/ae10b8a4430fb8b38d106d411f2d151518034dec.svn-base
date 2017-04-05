/**
 * 公告和政策
*/
var infoCondition = {
		key  : '', //被搜索关键字
		infoStatus : '',//信息状态
		startDate : '',//最早发布时间
		endDate : '',//最迟发布时间
		pageNo : '',//当前页
		pageSize : '',//一页数据的条数
		infoType : ''//信息类型
}
var removeInfoId = 0;
/**
 * 创建一个新的公告
 */
function createNewInfo(infoType){
	$.ajax({
		url : '../info/createNewInfo',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			infoType : infoType
		}),
		success : function (data) {
			if(data.success) {
				person = data.result;
				console.log(person);
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/***
 * 保存信息
 */
function saveInfo(infoTypeParam) {
	getFormField();
	$.ajax({
		url : '../info/saveInfo',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(person),
		success : function (data) {
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			if(infoTypeParam === 1) {
				getPolicyManagment({name : '管理政策法规'});
				setTimeout(function(){
		        	$('#infoStatus').chosen();
		        	$(".datepicker").datepicker({
		            	format: 'yyyy/mm/dd',
		                startDate: '-3d'
		            });
		        },100);
			}else if(infoTypeParam === 2){
				getNoticeManagment({name : '管理系统公告'});
				setTimeout(function(){
		        	$('#infoStatus').chosen();
		        	$(".datepicker").datepicker({
		            	format: 'yyyy/mm/dd',
		                startDate: '-3d'
		            });
		        },100);
			}
			
		}
	})
}
/**
 * 默认查询
 * @param infoType 查询类型
 */
function ordinaryPageByCondition(infoType, contentName,state) {
	if(state) {
		clearInfoCondition();
	}
	
	infoCondition.infoType = infoType;
	infoCondition.pageNo = 1;
	infoCondition.pageSize = 5;
	getInfoPageByCondition(infoType,contentName);
}

/**
 * 按指定条件查询
 */
function findInfoCondition() {
	var contentName = arguments[0].name;
	infoCondition.key = $('#Key').val();
	infoCondition.infoStatus = $('#infoStatus').val() === '999' ? '' : $('#infoStatus').val();
	infoCondition.startDate = getTimeStamp($('#startDate').val()) || '';//最早时间
	infoCondition.endDate = getTimeStamp($('#endDate').val()) || '';//最迟时间
	//console.log(infoCondition);
	getInfoPageByCondition(infoCondition.infoType,contentName);
}

/**
 * 获取一页数据
 * @param infoType
 * @param contentName
 */
function getInfoPageByCondition(infoType, contentName) {
	$.ajax({
		url : '../info/getInfoPageByCondition',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(infoCondition),
		success : function(data) {
			if(data.success) {
				//获取成功
				//渲染进表格
				tableRowLength = data.result.resultList.length;
				renderInfoTable(data.result.resultList,contentName);
				
			}
		}
	})
}

/**
 * 渲染表格
 * @param data
 */
function renderInfoTable(data,contentName) {
	$('#' + contentName).empty();
	for(var i = 0; i<data.length;i++) {
		$('#' + contentName).append(assembledStr(i, data[i],contentName));
	}
	
}

/**
 *	拼装字符串
 * @param index
 * @param obj
 * @param contentName
 */
function assembledStr(index, obj,contentName) {
	return "<tr><td>"+ obj.infoTitle +"</td><td>"+ obj.infoKey +"</td><td>"+ toggleStauts(obj.infoStatus,'已发布', '未发布') +
	"</td><td>"+ getLocalTime(obj.publishTime) +"</td><td><button type='button' class='btn btn-info' style='margin-right:14px;' onclick='getInfoById("+ obj.infoId +")'>详情</button><button type='button' class='btn btn-default' style='margin-right:14px;' onclick='updateInfoStatusById("+
	index +","+ contentName +","+ obj.infoId +")'>切换状态</button><button type='button' class='btn btn-danger' onclick='removeInfoModal("+ obj.infoId +");'>删除</button></td></tr>";
}


/**
 * 更改信息状态
 * @param index
 * @param contentName
 * @param infoId
 */
function updateInfoStatusById(index, contentName, infoId) {
	$.ajax({
		url : '../info/updateInfoStatusById',
		type : 'PUT',
		dataType : 'json',
		async : false,
		cache : false,
		contentType : 'application/json',
		data : JSON.stringify({
			infoId : infoId
		}),	
		success : function(result) {
			if(result.success) {
				toastr.success(result.msg);
				updateAssignTechInfoPerform(contentName.id, index, infoId);//更新表格指定列
			}else{
				toastr.error(result.msg);
			}
		}
	})
}

/**
 * 更新表格项
 * @param contentName
 * @param rowIndex
 * @param infoId
 */
function updateAssignTechInfoPerform(contentName, rowIndex, infoId) {
	$.ajax({
		url : '../info/getInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			infoId : infoId
		}),
		success : function(data) {
			if(data.success) {
				$('#' + contentName +' tr:eq('+ rowIndex +')').remove();
				if(tableRowLength === (rowIndex + 1)){
					//最后一行
					$('#' + contentName).append(assembledStr(rowIndex,  data.result, contentName));
				}else{
					$('#' + contentName +' tr:eq('+ rowIndex +')').before(assembledStr(rowIndex,  data.result, contentName));
				}
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 获取信息详细信息
 * @param infoId
 */
function getInfoById(infoId) {
	var state = arguments[1];
	$.ajax({
		url : '../info/getInfoById',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			infoId : infoId
		}),
		success : function(data) {
			if(data.success) {
				person = data.result;
				console.log(person);
				if(state) {
					//只渲染附件
					operationInfoHref('fileContent');
					//渲染html内容
					if(infoCondition.infoType === 1) {
						operationHtmlText('policyForm');
					}else if(infoCondition.infoType === 2){
						 operationHtmlText('noticeForm');
					}
			       
				}else{
					if(infoCondition.infoType === 1) {
						$.get('./templates/policy/policyPublish.html', function(data) {
					        $('#content').html(data);
					        $('.navTitle').text('信息详情');
					        //渲染数据
					        operationText('policyForm');
					        //渲染html内容
					        operationHtmlText('policyForm');
					        //渲染连接
					        operationInfoHref('fileContent');
					    });
					}else if(infoCondition.infoType === 2) {
						$.get('./templates/notice/noticePublish.html', function(data) {
					        $('#content').html(data);
					        $('.navTitle').text('信息详情');
					        //渲染数据
					        operationText('noticeForm');
					        //渲染html内容
					        operationHtmlText('noticeForm');
					        //渲染连接
					        operationInfoHref('fileContent');
					    });
					}
				}
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 渲染html模板
 * @param contentName
 */
function operationHtmlText(contentName) {
	var elements = $('#' + contentName +' div[class*="form-content"]');
	for(var i = 0 ;i<elements.length;i++) {
		$('#'+ contentName + ' #' + elements[i].id).html(person[elements[i].id]);
	}
}

/**
 * 渲染链接
 * @param contentName
 */
function operationInfoHref(contentName) {
	$('#' +contentName).empty();
	if(person.fileUrl !== null) {
		var url = '../../' + person.fileUrl;
		$('#' + contentName).append('附件：&nbsp;&nbsp;<a href="'+ url +'">'+ person.fileDesc +'</a>');
	}
	
}

/**
 * 发布信息
 */
function publishInfo(infoTypeParam){
	getFormField();
	$.ajax({
		url : '../info/publishInfo',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify(person),
		success : function (data) {
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			if(infoTypeParam === 1) {
				getPolicyManagment({name : '管理政策法规'});
				setTimeout(function(){
		        	$('#infoStatus').chosen();
		        	$(".datepicker").datepicker({
		            	format: 'yyyy/mm/dd',
		                startDate: '-3d'
		            });
		        },100);
			}else if(infoTypeParam === 2){
				getNoticeManagment({name : '管理系统公告'});
				setTimeout(function(){
		        	$('#infoStatus').chosen();
		        	$(".datepicker").datepicker({
		            	format: 'yyyy/mm/dd',
		                startDate: '-3d'
		            });
		        },100);
			}
		}
	})
}
 
/**
 * 打开删除信息模态框
 * @param infoId 信息id
 */
function removeInfoModal(infoId) {
	removeInfoId = infoId;
	showModal('../modalTemplates/removeInfo.html');
}


/**
 * 删除信息
 */
function removeInfo() {
	$.ajax({
		url : '../info/removeInfoById',
		type : 'DELETE',
		dataType : 'json',
		contentType : 'application/json',
		data :  JSON.stringify({
			infoId : removeInfoId
		}),
		success : function(data) {
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			//以当前查询条件查询数据并刷新表格
			if(infoCondition.infoType === 1){
				ordinaryPageByCondition(infoCondition.infoType,'policyTable', false);
			}else if(infoCondition.infoType === 2) {
				ordinaryPageByCondition(infoCondition.infoType,'noticeTable', false);
			}
			$("#modalModule").modal('hide');   
		}
	})
}

/**
 * 上传模态框
 * @param title 标题类型
 */
function showInfoModal() {
	showModal('../modalTemplates/infoFile.html');
	setTimeout(function() {
		$('#desc')[0].focus();
		$('#uploadSupplyContent input[class*="imgContentBtn"]').attr('disabled', "true");
		//监听描述的改变，只有由输入才能上传文件
		$('#desc').on('keyup', function() {
			if($('#desc').val() !== ''){
				$('#uploadSupplyContent input[class*="imgContentBtn"]').removeAttr('disabled');
				//监听上传
				uploadWordFile($('#desc').val());
			}else{
				$('#uploadSupplyContent input[class*="imgContentBtn"]').attr('disabled', 'disabled');
			}
		});
	},300);
	
}

/**
 * 上传附件信息
 * @param desc
 */
function uploadWordFile(desc)  {
	if(desc!==''){
		$("#infoFile").fileupload({
			 autoUpload: true,//自动上传
			 dataType : 'json',
			 type: 'POST',
			 url : '../info/uploadWordFile',
			 formData : {
				 desc : desc,//描述
				 bindId : person.infoId//信息id
			 },
			 success : function(data) {
				 if(data.success){
						//上传成功
					toastr.success(data.msg);
				}else{
					toastr.error(data.msg);
				}
				 //更新数据
				 getInfoById(person.infoId,true);
				 $("#modalModule").modal('hide');
			 }
		})
	}
}

/**
 * 清除查询条件
 */
function clearInfoCondition() {
	for(index in infoCondition) {
		infoCondition[index] = '';
	}
}
