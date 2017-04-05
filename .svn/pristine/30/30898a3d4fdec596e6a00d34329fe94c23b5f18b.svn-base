/*用户信息页面*/
var person = {};
var treeNode = {};
var operationNode ={};
var operationId = {};
var refreshFunType = 0;
/**
 * 获取普通下拉选择框的内容，包括政治面貌、学历、学位
 */
function getSelcetData() {
	var ids = arguments[0];
	$.getJSON("../data/managment/selectData.json", function(data){
		$.each(data, function(key,val) {
			for(var i = 0;i<ids.length;i++) {
				if(ids[i].fatherContent === key){
					getOption(val,key,true,ids[i].selectedValue);
				}
			}
		})
	});
}

/**
 * 获取多数据的树内容，包括所学专业、从事专业、职称、高新技术领域、研究行业、研究方向、所在地
 * @param target
 */
function showTreeModal(target) {
	//获取输入框元素
	operationNode = $('#'+target.id).prev();
	//打开模态框
	showModal('../modalTemplates/treeModal.html');
	//访问json文件路径
	var url = "../data/managment/" + target.id + ".json";
	//通过指定url获取指定数据
	$.getJSON(url, function(data){
		$.fn.zTree.init($("#treeModule"), initTree(), data);
	});
	setTimeout(function() {
		$('#treeModalName').html(target.name);
	},100);
}


/**
 * **********************************************  渲染表单开始  *********************************
 */
/**
 * 渲染表单数据
 * @param infoObj
 */
function renderInfo(contentName) {
	//console.log(person);
	//console.log(infoObj)
	//获取表单的所有字段，然后判断事先约定好的class类进行判断，后每一种类型进行数据渲染
	//普通文本框
	operationText(contentName);
	//chosen插件
	operationChosen(contentName);
	//json文件
	var elements = $('#' + contentName + ' input[class*="form-select"]');
	for(var i = 0;i <elements.length;i++) {
		operationJson(elements[i], contentName);
	}
	//文件类，包括图片、附件
	operationFile(contentName);
	//日期
	operationDate(contentName);
	//多行文本框
	operationTextarea(contentName);
	//单选框
	operationRadio(contentName);
	
}

/**
 * 操作普通文本框
 */
function operationText(contentName) {
	var elements = $('#' + contentName +' input[class*="form-text"]');
	for(var i= 0;i< elements.length;i++) {
		elements[i].value = person[elements[i].id];
	}
}
/**
 * 下拉框
 * @param contentName
 */
function operationChosen(contentName) {
	var Ids = [];
	var elements = $('#' +contentName+ ' select');
	for(var i=0;i<elements.length;i++) {
		//传递控件id，控件值
		Ids.push({
			fatherContent : elements[i].id,
			selectedValue : person[elements[i].id]
		});
	}
	getSelcetData(Ids);
}


/**
 * 需要去匹配json文件
 * @param contentName
 */
function operationJson(ele, contentName) {
	var matchName = ele.id;
	var url = "../data/managment/" + matchName.substring(0,matchName.length-2) + ".json";
	$.getJSON(url, function(data){
		match(data, person[matchName],matchName);
	});
}
/**
 * json文件匹配目标Id
 * @param data json文件内容
 * @param matchValue 匹配目标Id
 * @param contentId 存放匹配成功结果容器Id
 */
function match(data,matchValue,contentId) {
	for(var i=0;i < data.length;i++) {
		if(data[i].id == matchValue){
			$('#' + contentId).val(data[i].name);
		}else{
			if("children" in data[i]){
				matchChild(data[i].children,matchValue, contentId);
			}
		}
	}
}

/**
 * json文件匹配目标Id(子内容)
 * @param data json文件子内容
 * @param matchValue 匹配目标Id
 * @param contentId 存放匹配成功结果容器Id
 */
function matchChild(data, matchValue, contentId) {
	for(var i=0;i<data.length;i++) {
		(function() {
			if(data[i].id == matchValue) {
				$('#' + contentId).val(data[i].name);
				return;
			}else{
				if("children" in data[i]){
					matchChild(data[i].children,matchValue,contentId);
				}
			}
		})(i);
	}
}
/**
 * 操作多行文本框
 * @param contentName
 */
function operationTextarea(contentName) {
	var elements = $('#' +contentName+ ' textarea');
	for(var i = 0;i < elements.length;i++) {
		elements[i].value = person[elements[i].id];
	}
}

/**
 * 操作文件
 * @param contentName
 */
function operationFile(contentName) {
	var elements = $('#' +contentName+ ' input[type="file"]');
	//console.log(elements);
	//console.log(elements);
	//获取操作的文件容器
	for(var i = 0;i < elements.length;i++) {
		fileNode = $('#'+elements[i].id).parent().parent().prev();
		var src = "../../" + person[fileNode.attr('id')];
		if($('#' + fileNode.attr('id')).is('img')){
			$('#' + fileNode.attr('id')).attr('src', src);
		}else{
			$('#' + fileNode.attr('id')).empty();
			$('#' + fileNode.attr('id')).append('附件：&nbsp;&nbsp;<a href="'+ src +'">'+ person.enclosureDesc +'</a>')
		}
	}
}

/**
 * 操作日期
 * @param contentName
 */
function operationDate(contentName) {
	var elements = $('#' +contentName+ ' input[class*="datepicker"]');
	for(var i = 0;i < elements.length;i++) {
		$('#' + elements[i].id).val(getLocalTime(person[elements[i].id]));
	}
}

/**
 * 单选框
 * @param contentName
 */
function operationRadio(contentName) {
	var elements = $('#' +contentName+ ' input[type="radio"]');
	for(var i = 0;i < elements.length;i++) {
		if(elements[i].name in person) {
			if(elements[i].value == person[elements[i].name]){
				elements[i].checked = true;
			}
		}
	}
}

/**
 * 手动回显输入框
 * @param contentName
 */
function operationHmChosen(contentName) {
	var element = $('#' +contentName+ ' select[class*="hm-chosen"]');
	
	var options = $('#' + element.attr('id') + ' option');
	for(var i = 0;i< options.length;i++){
		if(options[i].value == person[element.attr('id')]){
			options[i].selected = true;
		}
	}
	$('#'+ element.attr('id')).chosen();
}

/**
 * **********************************************  渲染表单结束  *********************************
 */
/**
 * 模态框选值
 */
function modalSelectValue() {
	if(treeNode.id !== 0) {
		//console.log(treeNode);
		//不是根节点，显示内容
		$(operationNode).val(treeNode.name);
		//设值
		person[operationNode.attr('id')] = treeNode.id;
		//console.log(person);
	}
	$("#modalModule").modal('hide');
}


/**
 * 获取当前表单，遍历其每个字段的值
 */
function getFormField() {
	//普通文本框
	$.each($('form').serializeArray(), function() {
		//判断是否是模态框选择，约定模态框class属性包含form-select
		//console.log($('#' + this.name).attr('class').indexOf('form-select'))
		if(this.name === 'birth' || this.name === 'establishmentDate') {
			//把日期转为时间戳
			person[this.name] = getTimeStamp(this.value);
		}else{
			person[this.name] = this.value;
		}
		
		//console.log(this.name + "=====>" + this.value);
    });
}


/**
 * *******************************     个人用户  ***************************************
 */

/**
 * 获取用户个人数据
 */
function getPersonInfo() {
	$.ajax({
		url : '../userInfo/getPersonInfo',
		type : 'GET',
		dataType :'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				//获取个人用户成功
				person = data.result;
				//console.log(person);
				renderInfo('personManagment', person);
				findUserInfoAuditStatus();
				refreshFunType = 1;
			}
		}
	})
}


/**
 * 审核状态
 */
function findUserInfoAuditStatus(){
	$.getJSON('../data/managment/auditStatus.json', function(data) {
		for(var i = 0;i<data.length;i++) {
			if(data[i].id === person.auditStatus) {
				if(person.auditStatus === 3){
					$('#auditMsg').text(data[i].name + ',请点击“查看审核”按钮查看结果');
				}else{
					$('#auditMsg').text(data[i].name);
				}
			}
		}
	})
	if(person.auditStatus === 3) {
		//审核未通过
		$('.muted').append('<button type="button" class="btn" style="float: right;margin-top: -4px;" onclick="showaAuditStatus();">查看审核</button>')
	}
}

/**
 * 提交个人审核
 */
function saveCheck(){
	//获取表单的数据集合
	var bootstrapValidator = $("#personManagment").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){ 
    	getFormField();
    	updatePersonInfo();
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
}

/**
 * 更新保存个人信息
 */
function updatePersonInfo() {
	//console.log(person);
	$.ajax({
		url : '../userInfo/updatePersonInfo',
		type : 'PUT',
		dataType :'json',
		contentType : 'application/json',
		data: JSON.stringify(person),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		}
	})
}

/**
 * 监听多个个人上传控件
 */
function upload() {
	//上传身份证照片
	uploadIDCardImage();
	//上传个人图片
	uploadPersonImage();
	//上传个人附件
	uploadEnclosure();
}

/**
 * 上传身份证图片
 */
function uploadIDCardImage() {
	$('#idcardImage').fileupload({
		 autoUpload: true,//自动上传
		 dataType : 'json',
		 type: 'POST',
		 url : '../upload/uploadIDCardImage',
		 success : function(data) {
			 if(data.success){
					//上传成功
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			 refreshManagment();
		 }
	})
}

/**
 * 上传个人照片
 */
function uploadPersonImage() {
	$('#personImage').fileupload({
		 autoUpload: true,//自动上传
		 dataType : 'json',
		 type: 'POST',
		 url : '../upload/uploadPersonImage',
		 success : function(data) {
			if(data.success){
				//上传成功
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		 }
	})
}

/**
 * 上传个人附件
 */
function uploadEnclosure(){
	$('#enclosure').fileupload({
		 autoUpload: true,//自动上传
		 dataType : 'json',
		 type: 'POST',
		 url : '../upload/uploadPersonEnclosure',
		 success : function(data) {
			if(data.success){
				//上传成功
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		 }
	})
}

/**
 * ********************************  企业用户模块    *********************************
 */

/**
 * 获取企业用户信息
 */
function getCompanyInfo() {
	$.ajax({
		url : '../userInfo/getCompanyInfo',
		type : 'GET',
		dataType :'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				//获取信息成功
				person = data.result;
				//console.log(person);
				renderInfo('companyManagment', person);
				findUserInfoAuditStatus();
				refreshFunType = 2;
				//console.log(person);
				//手动回显下拉
				operationHmChosen('companyManagment');
				
			}
		}
	})
}




/**
 * 提交企业审核
 */
function saveCheckByCompany(){
	//getFormField();
	//console.log(person);
	//获取表单的数据集合
	var bootstrapValidator = $("#companyManagment").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){ 
    	getFormField();
    	updateCompanyInfo();
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
}

/**
 * 更新企业用户信息
 */
function updateCompanyInfo() {
	//console.log(person);
	$.ajax({
		url : '../userInfo/updateCompanyInfo',
		type : 'PUT',
		dataType :'json',
		contentType : 'application/json',
		data: JSON.stringify(person),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		}
	})
}

/**
 * 上传代码图片
 */
function uploadUnitCodeImage() {
	$('#unitCodeImage').fileupload({
		 autoUpload: true,//自动上传
		 dataType : 'json',
		 type: 'POST',
		 url : '../upload/uploadUnitCodeImage',
		 success : function(data) {
			if(data.success){
				//上传成功
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		 }
	})
}

/**
 * ********************          高校模块  ****************************************************************
 */
/**
 * 获取高校信息
 */
function getCollegeInfo() {
	$.ajax({
		url : '../userInfo/getCollegeInfo',
		type : 'GET',
		dataType :'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				//获取信息成功
				person = data.result;
				renderInfo('collegeAndScientifyManagment', person);
				findUserInfoAuditStatus();
				refreshFunType = 3;
				//手动回显下拉
				operationHmChosen('collegeAndScientifyManagment');
				
			}
		}
	})
}

/**
 * 提交审核
 */
function saveCheckByCollegeAndScientify(){
	//获取表单的数据集合
	var bootstrapValidator = $("#collegeAndScientifyManagment").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){ 
    	getFormField();
    	if(refreshFunType === 3) {
    		updateCollegeInfo(); //高校
    	}else if(refreshFunType === 4) {
    		updateScientifyInfo();//科研
    	}
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
}

/**
 * 更新用户信息
 */
function updateCollegeInfo() {
	//console.log(person);
	$.ajax({
		url : '../userInfo/updateCollegeInfo',
		type : 'PUT',
		dataType :'json',
		contentType : 'application/json',
		data: JSON.stringify(person),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		}
	})
}
/**
 * *********************************   科研单位模块          ***********************************
 */

/**
 * 获取科研单位信息
 */
function getScientifyInfo() {
	$.ajax({
		url : '../userInfo/getScientifyInfo',
		type : 'GET',
		dataType :'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				//获取信息成功
				person = data.result;
				renderInfo('collegeAndScientifyManagment', person);
				findUserInfoAuditStatus();
				refreshFunType = 4;
				//手动回显下拉
				operationHmChosen('collegeAndScientifyManagment');
				
			}
		}
	})
}



/**
 * 更新科研单位信息
 */
function updateScientifyInfo() {
	$.ajax({
		url : '../userInfo/updateScientifyInfo',
		type : 'PUT',
		dataType :'json',
		contentType : 'application/json',
		data: JSON.stringify(person),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		}
	})
}
/**
 * ******************************    中介机构        ************************************
 */
/**
 * 获取中介机构信息
 */
function getAgencyInfo() {
	$.ajax({
		url : '../userInfo/getAgencyInfo',
		type : 'GET',
		dataType :'json',
		contentType : 'application/json',
		success : function(data) {
			if(data.success) {
				//获取信息成功
				person = data.result;
				renderInfo('agencyManagment', person);
				findUserInfoAuditStatus();
				refreshFunType = 5;
				//手动回显下拉
				operationHmChosen('agencyManagment');
				
			}
		}
	})
}

/**
 * 提交审核
 */
function saveCheckByAgency(){
	//获取表单的数据集合
	var bootstrapValidator = $("#agencyManagment").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){ 
    	getFormField();
    	updateAgencyInfo();
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
}


/**
 * 更新中介机构信息
 */
function updateAgencyInfo() {
	//console.log(person);
	$.ajax({
		url : '../userInfo/updateAgencyInfo',
		type : 'PUT',
		dataType :'json',
		contentType : 'application/json',
		data: JSON.stringify(person),
		success : function(data) {
			//console.log(data);
			if(data.success) {
				toastr.success(data.msg);
			}else{
				toastr.error(data.msg);
			}
			refreshManagment();
		}
	})
}

/**
 * 审核状态
 */
function showaAuditStatus() {
	showModal('../modalTemplates/auditStatus.html');
	setTimeout(function() {
		$.getJSON('../data/managment/auditStatus.json', function(data) {
			for(var i = 0;i<data.length;i++) {
				if(data[i].id === person.auditStatus) {
					$('#auditStatus').text(data[i].name);
				}
			}
		})
		if(person.auditResult !== '' && person.auditResult !== null) {
			$('#auditResult').text("审核信息：" + person.auditResult);
		}else if(person.auditResult == null){
			$('#auditResult').text("审核信息：暂无");
		}
	},300);
}

/**
 * *********************************   表单验证模块    ************************************
 */
/**
 * 个人用户验证
 */
function personModuleCheck() {
	$('#personManagment').bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//姓名
			personName : {
				message : '姓名验证失败',
				validators : {
					notEmpty: {
						message : '姓名不能为空'
					}
				}
			},
			//身份证
			idcardNumber : {
				message : '身份证验证失败',
				validators : {
					notEmpty : {
						message : '身份证不能为空'
					}
				}
			},
			//所学专业
			learnMajorId : {
				message : '所学专业验证失败',
				validators : {
					notEmpty : {
						message : '所学专业不能为空'
					}
				}
			},
			//从事专业
			workMajorId : {
				message : '从事专业验证失败',
				validators : {
					notEmpty : {
						message : '从事专业不能为空'
					}
				}
			},
			//高新技术领域
			highTechFieldId : {
				message : '高新技术领域验证失败',
				validators : {
					notEmpty : {
						message : '高新技术领域不能为空'
					}
				}
			},
			//研究行业
			industryId : {
				message : '研究行业验证失败',
				validators : {
					notEmpty : {
						message : '研究行业不能为空'
					}
				}
			},
			//研究方向
			researchDirection : {
				message : '研究方向验证失败',
				validators : {
					notEmpty : {
						message : '研究方向不能为空'
					}
				}
			},
			//关键字
			key : {
				message : '关键字验证失败',
				validators : {
					notEmpty : {
						message : '关键字不能为空'
					}
				}
			},
			//所在地
			locationId : {
				message : '所在地验证失败',
				validators : {
					notEmpty : {
						message : '所在地不能为空'
					}
				}
			},
			//个人描述
			personalProfile : {
				message : '个人描述验证失败',
				validators : {
					notEmpty : {
						message : '个人描述不能为空'
					}
				}
			},
			//联系人
			contactName : {
				message : '联系人验证失败',
				validators : {
					notEmpty : {
						message : '联系人不能为空'
					}
				}
			},
			//手机号码
			phoneNumber : {
				message : '手机号码验证失败',
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					}
				}
			},
			//通讯地址
			contactAddress : {
				message : '通讯地址验证失败',
				validators : {
					notEmpty : {
						message : '通讯地址不能为空'
					}
				}
			},
			//邮编
			postcode : {
				message : '邮编验证失败',
				validators : {
					notEmpty : {
						message : '邮编不能为空'
					}
				}
			},
			//email
			email : {
				message : 'email验证失败',
				validators : {
					notEmpty : {
						message : 'email不能为空'
					},
					emailAddress: {
                        message: 'email地址格式有误'
                    }
				}
			}
		}
	});
}

/**
 * 企业用户表单验证
 */
function companyModuleCheck() {
	$('#companyManagment').bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//企业名称
			unitName : {
				message : '企业名称验证失败',
				validators : {
					notEmpty: {
						message : '企业名称不能为空'
					}
				}
			},
			//法人代表
			legalRepresentative : {
				message : '法人代表验证失败',
				validators : {
					notEmpty: {
						message : '法人代表不能为空'
					}
				}
			},
			//注册资本
			registeredCapital : {
				message : '注册资本验证失败',
				validators : {
					notEmpty: {
						message : '注册资本不能为空'
					}
				}
			},
			//注册地址
			registeredAddress : {
				message : '注册地址验证失败',
				validators : {
					notEmpty: {
						message : '注册地址不能为空'
					}
				}
			},
			//所在地
			locationId : {
				message : '所在地验证失败',
				validators : {
					notEmpty: {
						message : '所在地不能为空'
					}
				}
			},
			//关键字
			key : {
				message : '关键字验证失败',
				validators : {
					notEmpty: {
						message : '关键字不能为空'
					}
				}
			},
			//所属行业
			industryId : {
				message : '所属行业验证失败',
				validators : {
					notEmpty: {
						message : '所属行业不能为空'
					}
				}
			},
			//组织机构代码或统一社会信用代码
			unitCode : {
				message : '组织机构代码或统一社会信用代码验证失败',
				validators : {
					notEmpty: {
						message : '组织机构代码或统一社会信用代码不能为空'
					}
				}
			},
			//企业简介
			unitProfile : {
				message : '企业简介验证失败',
				validators : {
					notEmpty: {
						message : '企业简介不能为空'
					}
				}
			},
			//联系人
			contactName : {
				message : '联系人验证失败',
				validators : {
					notEmpty: {
						message : '联系人不能为空'
					}
				}
			},//手机号码
			phoneNumber : {
				message : '手机号码验证失败',
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					}
				}
			},
			//通讯地址
			contactAddress : {
				message : '通讯地址验证失败',
				validators : {
					notEmpty : {
						message : '通讯地址不能为空'
					}
				}
			},
			//邮编
			postcode : {
				message : '邮编验证失败',
				validators : {
					notEmpty : {
						message : '邮编不能为空'
					}
				}
			},
			//email
			email : {
				message : 'email验证失败',
				validators : {
					notEmpty : {
						message : 'email不能为空'
					},
					emailAddress: {
                        message: 'email地址格式有误'
                    }
				}
			}
		}
		
	});
}


/**
 * 高校和科研表单校验
 */
function collegeAndScientifyModuleCheck() {
	$('#collegeAndScientifyManagment').bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//单位名称
			unitName : {
				message : '企业名称验证失败',
				validators : {
					notEmpty: {
						message : '企业名称不能为空'
					}
				}
			},
			//法人代表
			legalRepresentative : {
				message : '法人代表验证失败',
				validators : {
					notEmpty: {
						message : '法人代表不能为空'
					}
				}
			},
			//单位地址
			unitAddress : {
				message : '单位地址验证失败',
				validators : {
					notEmpty: {
						message : '单位地址不能为空'
					}
				}
			},
			//所在地
			locationId : {
				message : '所在地验证失败',
				validators : {
					notEmpty: {
						message : '所在地不能为空'
					}
				}
			},
			//关键字
			key : {
				message : '关键字验证失败',
				validators : {
					notEmpty: {
						message : '关键字不能为空'
					}
				}
			},
			//组织机构代码或统一社会信用代码
			unitCode : {
				message : '组织机构代码或统一社会信用代码验证失败',
				validators : {
					notEmpty: {
						message : '组织机构代码或统一社会信用代码不能为空'
					}
				}
			},
			//单位简介
			unitProfile : {
				message : '单位简介验证失败',
				validators : {
					notEmpty: {
						message : '单位简介不能为空'
					}
				}
			},
			//联系人
			contactName : {
				message : '联系人验证失败',
				validators : {
					notEmpty: {
						message : '联系人不能为空'
					}
				}
			},//手机号码
			phoneNumber : {
				message : '手机号码验证失败',
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					}
				}
			},
			//通讯地址
			contactAddress : {
				message : '通讯地址验证失败',
				validators : {
					notEmpty : {
						message : '通讯地址不能为空'
					}
				}
			},
			//邮编
			postcode : {
				message : '邮编验证失败',
				validators : {
					notEmpty : {
						message : '邮编不能为空'
					}
				}
			},
			//email
			email : {
				message : 'email验证失败',
				validators : {
					notEmpty : {
						message : 'email不能为空'
					},
					emailAddress: {
                        message: 'email地址格式有误'
                    }
				}
			}
		}
		
	});
}

/**
 * 中介机构信息表单校验
 */
function AgencyModuleCheck() {
	$('#agencyManagment').bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//单位名称
			unitName : {
				message : '单位名称验证失败',
				validators : {
					notEmpty: {
						message : '单位名称不能为空'
					}
				}
			},
			//成立日期
			establishmentDate : {
				message : '成立日期验证失败',
				validators : {
					notEmpty: {
						message : '成立日期不能为空'
					}
				}
			},
			//法人代表
			legalRepresentative : {
				message : '法人代表验证失败',
				validators : {
					notEmpty: {
						message : '法人代表不能为空'
					}
				}
			},
			//单位地址
			unitAddress : {
				message : '单位地址验证失败',
				validators : {
					notEmpty: {
						message : '单位地址不能为空'
					}
				}
			},
			//所在地
			locationId : {
				message : '所在地验证失败',
				validators : {
					notEmpty: {
						message : '所在地不能为空'
					}
				}
			},
			//关键字
			key : {
				message : '关键字验证失败',
				validators : {
					notEmpty: {
						message : '关键字不能为空'
					}
				}
			},
			//组织机构代码或统一社会信用代码
			unitCode : {
				message : '组织机构代码或统一社会信用代码验证失败',
				validators : {
					notEmpty: {
						message : '组织机构代码或统一社会信用代码不能为空'
					}
				}
			},
			//单位简介
			unitProfile : {
				message : '单位简介验证失败',
				validators : {
					notEmpty: {
						message : '单位简介不能为空'
					}
				}
			},
			//联系人
			contactName : {
				message : '联系人验证失败',
				validators : {
					notEmpty: {
						message : '联系人不能为空'
					}
				}
			},//手机号码
			phoneNumber : {
				message : '手机号码验证失败',
				validators : {
					notEmpty : {
						message : '手机号码不能为空'
					}
				}
			},
			//通讯地址
			contactAddress : {
				message : '通讯地址验证失败',
				validators : {
					notEmpty : {
						message : '通讯地址不能为空'
					}
				}
			},
			//邮编
			postcode : {
				message : '邮编验证失败',
				validators : {
					notEmpty : {
						message : '邮编不能为空'
					}
				}
			},
			//email
			email : {
				message : 'email验证失败',
				validators : {
					notEmpty : {
						message : 'email不能为空'
					},
					emailAddress: {
                        message: 'email地址格式有误'
                    }
				}
			}
		}
		
	});
}

/**
 * **************************************  util      **************************************
 */


/**
 * zTree设置信息
 */
function initTree() {
	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false,
			showIcon : false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, zTreeNode) {
				//保存当前获取的节点
				treeNode = zTreeNode;
			}
		}
	}; 
	
	return setting;
}


/**
 * 变相刷新
 */
function refreshManagment() {
	$("html body").animate({scrollTop:0}, 500);
	//重新加载数据
	switch(refreshFunType)  {
		case 1 : getPersonInfo();break;
		case 2 : getCompanyInfo();break;
		case 3 : getCollegeInfo();break;
		case 4 : getScientifyInfo();break;
		case 5 : getAgencyInfo();break;
 	}
}

/**
 * 时间戳转日期
 * @param nS 时间戳字符串
 * @returns 日期格式
 */
function getLocalTime(nS) {  
	var date = new Date(nS);
	 return date.toLocaleDateString();  
}

/**
 * 日期转时间戳
 * @param stamp 日期字符串
 * @returns 时间戳字符串
 */
function getTimeStamp(stamp) {
	var date = new Date(Date.parse(stamp.replace(/-/g, "/")));
    return  date.getTime();
}
