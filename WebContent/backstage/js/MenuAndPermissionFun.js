//模态框对象
var modalMenu = {};
var modalPermission = {};
//全局树对象
var menuList = [];
var permissionList = [];
var bindPermissionId = '';
var bind = false;
var adminUser = {};
/**
 * 操作类型
 * 1 菜单操作
 * 2 权限操作
 */
var operationType = 0;
//获取登录的用户数据
function getSessionUser() {
	if($.isEmptyObject(user)){//为空，首次登陆
		$.ajax({
			url : '../user/getCurrentUser',
			type : 'GET',
			dataType: "json",
			contentType : 'application/json',
			success : function(data) {
				if(data.success) {
					adminUser = data.result;
					$('#loginUserName').html(data.result.userName);
				}
			},
			error : function(data) {
				console.log(data);
			}
		});
	}else{
		$('#loginUserName').html(user.userName);
	}
	
}


//获取菜单
function getMenu() {
	$.ajax({
		url: '../menu/menuList',
		type : 'GET',
		dataType: 'json',
		success : function(data) {
			
			if(data.success) {
				$('#right-nav').empty();
				$('#right-nav').append(classifyMenus(data.resultList));
				/*allotMenu(data.resultList.firstMenus.sort(function(param1, param2){
					return param1.menuOrder - param2.menuOrder;
				}), data.resultList.secondMenus.sort(function(param1, param2){
					return param1.menuOrder - param2.menuOrder;
				}))*/
				
			}else{
				//获取失败，重新登录
				window.location.href="./../index.html";
			}
		}
	})
}


/**
 * ******************************  菜单分类渲染开始  **************************************
 */

/**
 * 对菜单进行分类
 * @param menus 要进行分类的菜单集合
 */
function classifyMenus(menus) {
	//console.log(menus);
	//params存放集合中存在的字段
	var params = getObjectParams(menus);
	//通过级别关系对菜单进行分类
	//console.log(params);
	for(var i = params.length-1;i > 0;i--) {
		menus[params[i-1]] = allot(menus[params[i]], menus[params[i-1]]);
	}
	return recursionTraversal(menus[params[0]]);
}


/**
 * 菜单等级分类
 * @param child 子菜单
 * @param father 父菜单
 * @returns  分配好的父菜单
 */
function allot(child, father) {
	for(var index = 0;index < father.length; index++) {
		father[index].nodes = [];
	}
	for(var childIndex = 0;childIndex < child.length;childIndex++) {
		for(var fatherIndex = 0; fatherIndex < father.length ; fatherIndex ++){
			if(child[childIndex].fatherMenuId === father[fatherIndex].menuId) {
				father[fatherIndex].nodes.push(child[childIndex]);
				break;
			}
		}
	}
	return father;
}

/**
 * 渲染菜单
 * @param menus 要渲染菜单的集合
 * @returns {String} 返回菜单html模板字符串
 */
function recursionTraversal(menus) {
	var appendMenuStrs = "";
	
	for(index in menus) {
		var firstMenu = menus[index];
		if(firstMenu.status === 1){ //该顶级菜单可用
			var appendMenuStr = "";
			if(firstMenu.nodes.length !== 0) {
				appendMenuStr += oneHasChildTraversal(firstMenu);//父菜单
				appendMenuStr += otherTraversal(2,firstMenu.nodes);//子菜单
				//结尾
				appendMenuStr+='</ul></li>';
			}else{
				appendMenuStr += oneLevelTraversal(firstMenu);
			}
			appendMenuStrs += appendMenuStr;
		}
	}
	//console.log(appendMenuStrs);
	//$('#testMenu').append(appendMenuStrs);
	return appendMenuStrs;
}


function otherTraversal(menuIndex, childMenus) {
	var appendStrs = '';
	for(var index = 0; index < childMenus.length;index++){
		var childMenu = childMenus[index];
		if(childMenu.status === 1) {//可用
			if("nodes" in childMenu){
				if(childMenu.nodes.length !== 0) {
					appendStrs += otherHasChildTraversal(childMenu);
					appendStrs += otherTraversal(menuIndex+1, childMenu.nodes);
					
					appendStrs +='</ul></li>';
				}else{
					//无子节点，转html模板
					appendStrs += otherLevelTraversal(menuIndex, childMenu);
				}
			}else{
				//无子节点，转html模板
				appendStrs += otherLevelTraversal(menuIndex, childMenu);
			}
		}
	}
	return appendStrs;
}


/**
 * 顶级菜单不带子菜单
 * @param menu 要转html模板字符串的菜单项
 * @returns {String} 返回html模板字符串
 */
function oneLevelTraversal(menu){
	var appendStrs = '';
	appendStrs+='<li><a href="#" class="nav-header" name="'+ 
	menu.menuName +'" onclick="'+menu.linkUrl+'(this);"><i class="fa fa-fw fa-list-ul"></i> '+
	menu.menuName +'</a></li>';
	return appendStrs;
}


function oneHasChildTraversal(menu) {
	var appendStrs = '';
	var target = "menu-" + menu.menuId;
	appendStrs +='<li><a href="#" data-target=".'+target +'" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-list-ul"></i> '+ 
	menu.menuName +'<i class="fa fa-collapse"></i></a></li><li><ul class="'+target+' nav nav-list collapse">';
	
	return appendStrs;
}

function otherHasChildTraversal(childManu) {
	var appendStrs = '';
	var target = "menu-" + childManu.menuId;
	appendStrs += '<li class="own-collapse"><a href="#" data-target=".'+ target +'" data-toggle="collapse"><span class="fa fa-caret-right"></span> '+
	childManu.menuName +'</a></li><li><ul class="'+target+' nav nav-list collapse">';
	
	return appendStrs;
	
}


function otherLevelTraversal(index, childMenu) {
	var appendStrs = '';
	if(index  >= 3){
		appendStrs+='<li class="child"><a href="#" style="padding-left:60px;" name="'+ childMenu.menuName +'" onclick="'+ childMenu.linkUrl +'(this);">';
	}else{
		appendStrs+='<li><a style="padding-left:48px;" href="#" name="'+ childMenu.menuName +'" onclick="'+ childMenu.linkUrl +'(this);">';
	}
	appendStrs+=childMenu.menuName +'</a></li>';
	return appendStrs;
}


/**
 * 菜单分类
 * @returns {String}返回 一个html模板字符串
 *//*
function allotMenu() {
	var args = arguments.length;
	//要渲染的数据
	var appendMenuStrs = "";
	//一级菜单添加二级菜单
	for(index in arguments[0]){
		var firstMenus = arguments[0][index];
		var appendMenuStr = "<li>";
		//标识是否有二级菜单
		var second = false;
		for(i in arguments[1]){
			if(arguments[1][i].fatherMenuId === firstMenus.menuId ){
				if(!second && arguments[1][i].status === 1){
					//第一次添加
					//添加一级菜单
					var target = "menu-" + firstMenus.menuId;
					appendMenuStr+='<a href="#" data-target=".'+target +'" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-list-ul"></i> '+ firstMenus.menuName +'<i class="fa fa-collapse"></i></a></li><li>'
					appendMenuStr+='<ul class="'+target+' nav nav-list collapse">';
				}
				appendMenuStr+='<li><a href="#" name="'+ arguments[1][i].menuName +'" onclick="'+ arguments[1][i].linkUrl +'(this);"><span class="fa fa-caret-right"></span> '+ arguments[1][i].menuName +'</a></li>'				
				second = true;
			}
		}
		if(second){
			appendMenuStr+='</ul></li>';
		}else if(!second && firstMenus.status === 1){
			//没有二级菜单
			appendMenuStr+='<a href="#" class="nav-header" name="'+ arguments[1][i].menuName +'" onclick="'+firstMenus.linkUrl+'(this);"><i class="fa fa-fw fa-list-ul"></i> '+ firstMenus.menuName +'</a></li>';
		}
		
		appendMenuStrs +=appendMenuStr;
	}
	return appendMenuStrs;
}*/

/**
 * ******************************  菜单分类渲染结束  **************************************
 */

/**
 * 获取菜单管理,获取所有菜单
 */
function getPermissonMenus() {
	$.ajax({
		url: '../menu/listAllMenus',
		type : 'GET',
		dataType: 'json',
		success : function(data) {
			//设置操作类型为菜单操作
			operationType = 1;
			
			//params存放结果对象属性
			var params = [];
			//存放最终结果
			var menus = [];
			//遍历
			for(var prop in data.resultList){
				params.push(prop);
			}
			//排序
			params.sort();
			//console.log(params);
			//循环分类
			for(var i = 0 ; i< params.length; i++) {
				if(i !== params.length-1){
					menus = gradeMenuList(menus,
						data.resultList[params[i]], 
						data.resultList[params[i+1]]
					)
				}
			}
			//console.log(menus);
			//设置排序号
			$('#addMenuModule').attr('value', menus.length);
			menuList = menus;
			//去除一级权限没子权限
			for(index in menus){
				if(menus[index].nodes.length == 0) {
					delete menus[index].nodes;
				}
			}
			
			//渲染菜单树
			renderTree('menuTree', menus);
			
			/*renderTree('menuTree',gradeMenu(data.resultList.firstMenus.sort(
					function(param1, param2){
						return param1.menuOrder - param2.menuOrder;
					}), data.resultList.secondMenus.sort(
					function(param1, param2){
						return param1.menuOrder - param2.menuOrder;
					})
				)
			);*/
		}
	});
}



//退出
function logout() {
	/*注销用户*/
	$.ajax({
		url: '../user/logout',
		type : 'GET',
		dataType: 'json',
		success : function(result) {
			if(result.success){
				//注销成功
				window.location.href="./../index.html";
			}else{
				//注销失败
				toastr.error(result.msg);
			};
		}
	});
}


/**
 * 获取用户类型选项信息
 */
function getOptionInfo() {
    $.get('./templates/optionInfo.html', function (data) {
        $('#content').html(data);
        //默认获取选项内容
        $.get('../data/typeData.json', function(data) {
        	getOption(data, 'typeOption');
        });
    });
    
}
/**
 * 添加下拉框的数据
 * @param data 要添加的数据
 * @param fatherContent 要添加的数据的父容器名
 */
function getOption(data,fatherContentName) {
	//console.log(arguments[3]);
	//console.log(fatherContentName);
    $('#'+ fatherContentName).append('<option value="999"></option>');
    for (index in data) {
        $('#'+ fatherContentName).append('<option value="' + data[index].id + '">' + data[index].name + '</option>');
    }
    //判断是否需要默认选中
    if(arguments.length > 2 && arguments[2]) {
    	$('#' + fatherContentName +' option[value="'+ arguments[3] +'"]').attr('selected','selected');
    }
    $('#'+ fatherContentName).chosen();
}


/**
 * 添加字段时动态添加选项
 */
function addFieldOption() {
    $('.modal-body tbody').append('<tr><td class="span2">选项名：</td><td class="span4"><input type="text" placeholder="该字段的选项名"/></td></tr>');
}




/**
 * 分级菜单通用接口
 * @returns
 */
function gradeMenuList() {
	var menu = arguments[0];
	//console.log(menu);
	//console.log(arguments[0]);
	/*console.log('===========================');
	console.log(arguments[0]);
	console.log(arguments[1]);
	console.log(arguments[2]);
	console.log('===========================');*/
	
	if(menu.length === 0) {
		for(index in arguments[1]){
			//一级菜单
			var firstMenu = {
				  text :arguments[1][index].menuName,
				  nodes : [],
				  menuId : arguments[1][index].menuId,
				  href: arguments[1][index].fatherMenuId + "`" + arguments[1][index].menuId + "`" + arguments[1][index].status
			}
			for(i in arguments[2]){
				//二级菜单
				if(arguments[2][i].fatherMenuId === arguments[1][index].menuId){
					firstMenu.nodes.push({
						text : arguments[2][i].menuName,
						menuId : arguments[2][i].menuId,
						href: arguments[2][i].fatherMenuId + "`" + arguments[2][i].menuId + "`"+ arguments[2][i].status 
					});
				}
			}
			menu.push(firstMenu);
		}
	}else{
		//二级菜单及以下
		for(var chlidIndex = 0; chlidIndex < arguments[1].length;chlidIndex++){
			var father = arguments[1][chlidIndex];
			//次级权限
			father.nodes = [];
			var childJudge = false;
			//每个顶级权限的子权限
			for(secondIndex in arguments[2]){
				var chlid = arguments[2][secondIndex];
				//每个下级的上级的上级的Id
				if(chlid.fatherMenuId === father.menuId){
					father.nodes.push({
						text : chlid.menuName,
						fatherMenuId : father.menuId,
						menuId : chlid.menuId,//当前权限的Id
						href: father.menuId + "`" + chlid.menuId + "`"+ chlid.status 
					})
					childJudge = true;
				}
			}
			//console.log(father);
			//添加到父级
			if(childJudge){
				for(index in menu) {
					for(var i = 0;i< menu[index].nodes.length; i++){
						var nodesElement = menu[index].nodes[i];
						//console.log(nodes);
						if(father.menuId === nodesElement.menuId){
							//console.log(123);
							//console.log(arguments[1][childIndex]);
							nodesElement.nodes = father.nodes;
						}
					}
				}
			}else{
				 delete father.nodes;
			}
			
		}
	}
	//console.log(menu);
	return menu;
}

/*//分级菜单
function gradeMenu() {
	var menus = [];
	//console.log(arguments[0]);
	for(index in arguments[0]){
		var child = false;
		//一级菜单
		var firstMenu = {
			  text :arguments[0][index].menuName,
			  nodes : [],
			  href: arguments[0][index].fatherMenuId + "`" + arguments[0][index].menuId + "`" + arguments[0][index].status
		}
		for(i in arguments[1]){
			//二级菜单
			if(arguments[1][i].fatherMenuId === arguments[0][index].menuId){
				firstMenu.nodes.push({
					text : arguments[1][i].menuName,
					href: arguments[1][i].fatherMenuId + "`" + arguments[1][i].menuId + "`"+ arguments[1][i].status 
				});
				child = true;
			}
		}
		if(!child){
			//没有子节点
			delete firstMenu.nodes;
		}
		menus.push(firstMenu);
	}
	//设置排序号
	$('#addMenuModule').attr('value', menus.length);
	menuList = menus;
	return menus;
}*/

//渲染树级菜单
function renderTree() {
	var contentName = arguments[0];
	$('#' + arguments[0]).treeview({
        data: arguments[1],         
        color: "#428bca",
        expandIcon: "icon icon-plus",//合上图标
        collapseIcon: "icon icon-minus",//展开图标
        //nodeIcon: "icon icon-tag",//节点图标
        levels : 1,
        showCheckbox : true,
        //selectedIcon : 'icon icon-ok',//选中图标
        state: {
        	expanded : false
        },
		onNodeSelected : function(event, node) {
			if(bind) {
				var Ids = skipEmptyElementForArray(node.href.split("`"));
				bindPermissionId = Ids[1];
				if(contentName === "UnBindPermissionTree"){
					//未绑定树，绑定权限
					var str = '<p>确定绑定【' +node.text +'】</p>' ;
					showModal('../modalTemplates/bindPermission.html');
					setTimeout(function() {
						$('.modal-body').append(str);
					},300);
				}else if(contentName === "BindPermissionTree"){
					//绑定树，解除绑定
					var str = '<p>确定解除绑定【' +node.text +'】</p>' ;
					showModal('../modalTemplates/unBindPermission.html');
					setTimeout(function() {
						$('.modal-body').append(str);
					},300);
				}
			}
		}
    });
}


//删除菜单节点
function removeMenuNode(target) {
	$.ajax({
		url : '../menu/deleteMenu',
		type: 'DELETE',
		dataType :'json',
		contentType : 'application/json',
		data : JSON.stringify({
			//要删除菜单节点的id
			menuId : target.value
		}),
		success : function(result){
			$("#modalModule").modal('hide');
			if(result.success){
				//成功
				toastr.success(result.msg);
				//更新菜单管理数据
				getPermissonMenus();
			}else{
				toastr.error(result.msg);
			}
		}
	});
}


/**
 * 删除操作返回
 * @param type
 */
function goBack(type) {
	if(type === 1) {
		var textMsg = '';
		if(!$.isEmptyObject(modalMenu)){
			if(modalMenu.chlidMenuLength === 0){
				textMsg = '确定删除该菜单节点？';
			}else{
				textMsg = '该菜单存在子菜单节点，删除操作将删除该菜单下关联的所有子菜单，确定删除该菜单节点？';
			}
		}else if(!$.isEmptyObject(modalPermission)){
			if("nodes" in modalPermission){
				textMsg = '该权限存在子权限节点，删除操作将删除该权限下关联的所有子权限，确定删除该权限节点？';
			}else{
				textMsg = '确定删除该权限节点？';
			}
		}
		$('#removeTitle').text(textMsg);
		$('#model-content-from').fadeOut(50);
		$('#modal-remove').fadeIn(500);
	}else if(type === 2) {
		$('#model-content-from').fadeIn(500);
		$('#modal-remove').fadeOut(50);
	}
}
/**
 * 更新菜单
 */
function editMenuNode() {
	var menu = {
		menuName : $('#editMenuName').val(),
		linkUrl : $('#editLinkUrl').val(),
		menuOrder : $('#editMenuOrder').val(),
		menuLevel : modalMenu.menuLevel,
		menuId : modalMenu.menuId,
		fatherMenuId :$('#fatherMenuOption').val(),
		bindPerminssionId : $('#editPermissionOption').val()
	}
	//console.log(judgeIfEdit(menu));
	//console.log(menu);
	//判断是否真的修改过
	if(!judgeIfEdit(menu,modalMenu)){
		//没有修改过
		toastr.info('未修改信息');
		$("#modalModule").modal('hide');
	}else{
		var bootstrapValidator = $("#editMenuForm").data('bootstrapValidator');
		bootstrapValidator.validate();
	    if(bootstrapValidator.isValid()){ 
	    	//验证成功
	    	$.ajax({
	    		url : '../menu/updateMenu',
	    		type : 'PUT',
	    		dataType : 'json',
	    		contentType : 'application/json',
	    		data : JSON.stringify(menu),
	    		success : function(result) {
	    			$("#modalModule").modal('hide');
	    			if(result.success){
	    				//成功
	    				toastr.success(result.msg);
	    				//更新菜单管理数据
	    				getPermissonMenus();
	    				
	    			}else{
	    				toastr.error(result.msg);
	    			}
	    		}
	    	});
	    }else{
	    	//验证失败
	    	toastr.error('有信息未填写，填写完指定信息再次提交');
	    }
	}
}


/**
 * 判断一个对象是否修改过
 * @param element 要判断的对象
 * @param Module 要对比的对象
 * @returns {Boolean} 返回boolean值
 */
function judgeIfEdit(element,Module){
	var judge = false;
	//console.log(modalMenu);
	//console.log(element);
	for(var e in element){
		//判断是否为数字
		var prop = element[e];
		if(typeof Module[e] === "number"){
			prop = parseInt(element[e]);
		}
		if(Module[e] != prop){
			return true;
		}
	}
	return judge;
}


/**
 * 添加子集菜单
 */
function addMenuNode() {
	var menu = {
		menuName : $('#menuName').val(),
		linkUrl : $('#linkUrl').val(),
		menuOrder : $('#menuOrder').val(),
		menuLevel : modalMenu.menuLevel + 1,
		fatherMenuId : modalMenu.menuId,
		bindPerminssionId : $('#permissionOption').val()
	}
	//console.log(menu);
	var bootstrapValidator = $("#addMenuForm").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){ 
    	//验证成功
    	$.ajax({
    		url : '../menu/addMenu',
    		type : 'POST',
    		dataType : 'json',
    		contentType : 'application/json',
    		data : JSON.stringify(menu),
    		success : function(result) {
    			$("#modalModule").modal('hide');
    			if(result.success){
    				//成功
    				toastr.success(result.msg);
    				//更新菜单管理数据
    				getMenu();
    				getPermissonMenus();
    			}else{
    				toastr.error(result.msg);
    			}
    		}
    	});
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
}

/**
 * 通过指定的id获取菜单信息
 * @param menuId 指定id值
 */
function getSingleMenu(menuId) {
	$.ajax({
		url : '../menu/getMenu',
		type: 'POST',
		dataType :'json',
		contentType : 'application/json',
		data : JSON.stringify({
			menuId : menuId
		}),
		success: function(data) {
			if(data.success) {
				modalMenu = data.result;
			}
		}
	});
}


/**
 * 获取所有未绑定的数据
 */
function getUnbindPermissions(fatherContent) {
	$.ajax({
		url :'../perm/getUnBindPermissions',
		type: 'GET',
		dataType :'json',
		contentType : 'application/json',
		success: function(data) {
			if(data.success) {
				//渲染到下拉框
				var permissionData = [];
				for(var i = 0;i<data.resultList.length;i++) {
					permissionData.push({
						id : data.resultList[i].permissionId,
						name : data.resultList[i].description + "【"+ data.resultList[i].permissionName + "】"
					});
				}
				if(arguments.length > 1){
					getOption(permissionData,fatherContent, arguments[1], arguments[2]);
				}else{
					getOption(permissionData,fatherContent);
				}
			}
		}
	});
}

/**
 * 修改页面获取权限
 */
function getSinglebindPermissionByMenuId() {
	var arg = arguments;
	$.ajax({
		url :'../perm/getUnBindPermissionsByMenuId',
		type: 'POST',
		dataType :'json',
		contentType : 'application/json',
		data : JSON.stringify({
			menuId : arguments[0]
		}),
		success: function(data) {
			if(data.success) {
				//渲染到下拉框
				var permissionData = [];
				for(var i = 0;i<data.resultList.length;i++) {
					permissionData.push({
						id : data.resultList[i].permissionId,
						name : data.resultList[i].description + "【"+ data.resultList[i].permissionName + "】"
					});
				}
				console.log(permissionData);
				if(arg.length > 2) {
					getOption(permissionData,arg[1], arg[2], arg[3]);
				}else{
					getOption(permissionData,arg[1]);
				}
				
			}
		}
	})
}


/**
 * 获取指定菜单的子菜单个数
 */
/*function getSingleChlidMenuLength() {
	//console.log(modalMenu);
	return modalMenu.chlidMenuLength;
	console.log(menuIds);
	console.log(menuList);
	//只能做到一级
	for(index in menuList){
		if(menuIds === menuList[index].href){
			if("nodes" in menuList[index]){
				return menuList[index].nodes.length;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
}*/
/**
 * 更新菜单的状态
 * @param target 点击的按钮，从其value中获取菜单Id
 */
function updateMenuStatus(target) {
	//console.log(target.value);
	$.ajax({
		url :'../menu/updateMenuStatus',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			menuId : target.value
		}),
		success : function(result) {
			if(result.success) {
				//关闭模态框
				$('#modalModule').modal('hide')
				//更新成功
				toastr.success(result.msg);
				//刷新
				getMenu();
				getPermissonMenus();
			}else{
				//更新失败
				toastr.error(result.msg);
			}
		}
	})
}
/**
 * 菜单节点单击事件：点击菜单项即可展开子元素
 * @param target 菜单节点元素
 *//*
function itemOnclick(target){
	if($.isEmptyObject(modalPermission)){
		//找到当前节点id  
	    var nodeid = $(target).attr('data-nodeid');  
	    var tree
	    if(operationType === 1){
	    	tree  = $('#menuTree');  
	    }else{
	    	tree = $('#permissionTree');  
	    }
	   
	    //获取当前节点对象  
	    var node = tree.treeview('getNode', nodeid);  
	      
	    if(node.state.expanded){   
	        //处于展开状态则折叠  
	        tree.treeview('collapseNode', node.nodeId);    
	    } else {  
	        //展开  
	        tree.treeview('expandNode', node.nodeId);  
	    }  
	}
} */ 

/**
 * 菜单双击操作菜单
 * @param target 双击菜单的节点元素
 */
function itemOndblclick(target){
	//获取当前节点对象
	var tree;
	if(operationType === 1){
		tree= $('#menuTree');
	}else if(operationType === 2){
		tree= $('#permissionTree');
	}
	//找到当前节点id  
    var nodeid = $(target).attr('data-nodeid');
    //获取当前节点对象
	var node = tree.treeview('getNode', nodeid);
	var Ids = skipEmptyElementForArray(node.href.split("`"));
	//该节点代表的菜单的状态
	var status = Ids[Ids.length-1];
	if(operationType === 1){
		//打开模态框
		showModal('../modalTemplates/MenuMessage.html');
		//获取一个菜单的信息
		//使用"-"作为分割符，如果数组长度为2，即为一级菜单，长度为3，即为二级菜单，依次类推,最后一位是状态
		//保存模态框信息
		//获取该节点的数据
		getSingleMenu(Ids[1]);
		//获取子菜单
		setTimeout(function() {
			getChildMenu(Ids[1]);
		},150);
		setTimeout(function() {
			//console.log(modalMenu);
			$('#menuNameTitle').html(node.text);//菜单名
			$('#menuPanel .btn').attr('value',Ids[1]);
			//console.log(modalMenu);
			//修改表单验证
			//获取该节点的子对象个数,并传入表单验证
			menuModuleCheck(modalMenu.chlidMenuLength);
			editMenuModuleCheck(modalMenu.chlidMenuLength);
			//获取未绑定的权限
			getSinglebindPermissionByMenuId('', 'permissionOption');
			setTimeout(function() {
				getSinglebindPermissionByMenuId(modalMenu.menuId,'editPermissionOption',true, modalMenu.bindPerminssionId);
			},200);
			//填充表单数据
			//console.log(modalMenu);
			$('#editMenuName').val(modalMenu.menuName);
			//console.log($('#editMenuName').val());
			$('#editLinkUrl').val(modalMenu.linkUrl);
			$('#editMenuOrder').val(modalMenu.menuOrder);
			//填充父级菜单选项
			setMenuOption(modalMenu.menuLevel);
		},500);
	}else if(operationType === 2){
		//权限操作
		//打开模态框
		showModal('../modalTemplates/PermissionMessage.html');
		//获取单个权限的信息
		getSinglePermisson(Ids[1]);
		//获取子权限
		getChildPermission(node.nodes);
		//console.log(node);
		setTimeout(function() {
			$('#permissionNameTitle').html(node.text);//权限名
			$('#permissionPanel .btn').attr('value',Ids[1]);
			//表单模块校验
			permissionModuleCheck('addPermissonForm');
			permissionModuleCheck('editPermissonForm');
			$('#permissionMark').chosen();
			//权限标记
			$('#editPermissionMark option').each(function() {
				if($(this).val() == modalPermission.permissionMark){
					$(this).attr('selected', 'selected');
				}
			});
			$('#editPermissionMark').chosen();
			//父权限
			if(modalPermission.permissionLevel > 1) {
				getFatherPermissionByLevel(modalPermission.permissionLevel-1, modalPermission.fatherPermissionId);
			}else{
				$('#fatherPermission').hide();
			}
			//填充表单数据
			$('#editPermissonForm #permissionName').val(modalPermission.permissionName);
			$('#editPermissonForm #description').val(modalPermission.description);
			//$('#editPermissonForm #permissionMark').val(modalPermission.permissionMark);
			$('#editPermissonForm input[value="'+ modalPermission.status +'"]').attr('checked','checked');
		},300);
	}
	setTimeout(function() {
		if(status == 1){
			$('#status').html('可用');
		}else{
			$('#status').html('不可用');
		}
	},100);
	
}


/**
 * 设置菜单
 * @param level 菜单等级
 */
function setMenuOption(level) {
	var addFatherOptions = [];
	//console.log(menuList);
	if(level == 2) {
		//二级菜单菜单
		//获取数据
		for(index in menuList){
			addFatherOptions.push({
				id : skipEmptyElementForArray(menuList[index].href.split("`"))[1],
				name : menuList[index].text
			});
		}
		
	}else if(level > 2){
		//二级以上菜单
		$.ajax({
			url : '../menu/getAllMenusByLevel',
			type : 'POST',
    		dataType: "json",
    		contentType : 'application/json',
    		data : JSON.stringify({
    			menuLevel : level-1
    		}),
    		success : function(data) {
    			if(data.success) {
    				for(index in data.resultList){
    					var menu = data.resultList[index];
        				addFatherOptions.push({
        					id : menu.menuId,
        					name : menu.menuName
        				});
        			}
    			}
    		}
		})
	}
	setTimeout(function() {
		if(level >= 2){
			//渲染数据
			getOption(addFatherOptions, 'fatherMenuOption', true, modalMenu.fatherMenuId);
			$('#fatherMenuContent').show();
		}
	},200);
	
}

/**
 * 添加菜单模块
 */
function showMenuModule(target){
	showModal('../modalTemplates/addMenu.html');
	setTimeout(function() {
		menuModuleCheck(target.value);
		//获取未绑定的权限
		getSinglebindPermissionByMenuId('', 'permissionOption');
		//当表单验证失败时不绑定后续事件
        $("#addMenuForm").submit(function(ev){ev.preventDefault();});
	},180);
}


/**
 * 添加菜单
 */
function addMenuModule(level) {
	var menu = {
		menuName : $('#menuName').val(),
		linkUrl : $('#linkUrl').val(),
		menuOrder : $('#menuOrder').val(),
		menuLevel : level,
		bindPerminssionId : $('#permissionOption').val()
	}
	var bootstrapValidator = $("#addMenuForm").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){
    	$.ajax({
    		url : '../menu/addMenu',
    		type : 'POST',
    		dataType: "json",
    		contentType : 'application/json',
    		data : JSON.stringify(menu),
    		success: function(result) {
    			$("#modalModule").modal('hide');
    			if(result.success){
    				//成功
    				toastr.success(result.msg);
    				//更新菜单
    				getMenu();
    				getPermissonMenus();
    			}else{
    				toastr.error(result.msg);
    			}
    		}
    	});
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
	 
}

/**
 * 获取子菜单
 * @param mId 指定查询的菜单Id
 */
function getChildMenu(mId) {
	$.ajax({
		url : '../menu/getChildMenus',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			menuId : mId
		}),
		success : function(data) {
			//console.log(data);
			if(data.success){
				//获取子菜单成功
				//设置子菜单数
				//console.log(modalMenu);
				modalMenu.chlidMenuLength = data.resultList.length;
			
				//console.log(modalMenu);
				if(data.resultList.length !==0) {
					//console.log(data.resultList);
					$('#childMenuMsg').text('子菜单如下：');
					var childMenuStr = '<ul class="list-group">';
					for(index in data.resultList){
						childMenuStr += '<li class="list-group-item">'+ data.resultList[index].menuName +'</li>'
					}
					childMenuStr +='</ul>';
					//console.log(childMenuStr)
					$('#childMenuContent').append(childMenuStr);
				}else{
					//console.log(123);
					$('#childMenuMsg').text('当前菜单无子节点，添加一个吧');
				}
			}
		}
	});
}






/***
 * **************************   权限管理       ***************************************************************
 */

//获取角色信息
function getRoles() {
	$.ajax({
		url : '../perm/getAllRoles',
		type : 'GET',
		dataType: 'json',
		success : function(data) {
			//console.log(data);
			if(data.success) {
				var roles = [];
				for(index in data.result){
					roles.push({
						id : data.result[index].roleId,
						name : data.result[index].description
					});
				}
				getOption(roles, 'roleOption');
			}
		}
	});
}

/**
 * 获取所有权限
 */
function getPermissionList() {
	$.ajax({
		url: '../perm/getAllPermissions',
		type : 'GET',
		dataType: 'json',
		success : function(data) {
			//console.log(data);
			//设置操作权限
			operationType = 2;
			//console.log(data);
			//params存放结果对象属性
			var params = [];
			//存放最终结果
			var permissions = [];
			//遍历
			for(var prop in data.resultList){
				params.push(prop);
			}
			//排序
			params.sort();
			//循环分类
			for(var i = 0 ; i< params.length; i++) {
				if(i !== params.length-1){
					permissions = gradePermissionList(permissions,
						data.resultList[params[i]], 
						data.resultList[params[i+1]]
					)
				}
			}
			//去除一级权限没子权限
			for(index in permissions){
				if(permissions[index].nodes.length == 0) {
					delete permissions[index].nodes;
				}
			}
			//设置全局对象
			permissionList = permissions;
			//console.log(permissions);
			//渲染权限树
			//console.log(permissionList);
			renderTree('permissionTree',permissions);
		}
	});
}

/**
 * 通用分配权限
 * @returns
 */
function gradePermissionList() {
	var permission = arguments[0];
	/*console.log("*************************");
	console.log(permission);
	console.log(arguments[1]);
	console.log(arguments[2]);
	console.log("*************************");*/
	if(permission.length === 0) {
		/*console.log(arguments[1].length);*/
		//最顶级
		for(index in arguments[1]){
			var father = {
				  text :arguments[1][index].description,//权限值
				  nodes : [],//权限子集
				  permissionId : arguments[1][index].permissionId,//当前权限的Id
				  href: arguments[1][index].fatherPermissionId + "`" + arguments[1][index].permissionId + "`" + arguments[1][index].status,
			}
			for(var childIndex = 0;childIndex < arguments[2].length;childIndex++) {
				if(arguments[2][childIndex].fatherPermissionId === arguments[1][index].permissionId){
					father.nodes.push({
						text : arguments[2][childIndex].description,//权限值
						fatherPermissionId : arguments[1][index].permissionId,//父权限Id
					    permissionId : arguments[2][childIndex].permissionId,//当前权限的Id
						href: arguments[2][childIndex].fatherPermissionId + "`" + arguments[2][childIndex].permissionId + "`"+ arguments[2][childIndex].status 
					});
				}else{
					//改
				}
			}
			//console.log(father);
			permission.push(father);
		}
	}else{
		//最顶级权限
		for(var chlidIndex = 0; chlidIndex < arguments[1].length;chlidIndex++){
			var father = arguments[1][chlidIndex];
			//次级权限
			father.nodes = [];
			var childJudge = false;
			//每个顶级权限的子权限
			for(secondIndex in arguments[2]){
				var chlid = arguments[2][secondIndex];
				//每个下级的上级的上级的Id
				if(chlid.fatherPermissionId === father.permissionId){
					father.nodes.push({
						text : chlid.description,
						fatherPermissionId : father.permissionId,
						permissionId : chlid.permissionId,//当前权限的Id
						href: father.permissionId + "`" + chlid.permissionId + "`"+ chlid.status 
					})
					childJudge = true;
				}
			}
			//console.log(father);
			//添加到父级
			if(childJudge){
				for(index in permission) {
					for(var i = 0;i< permission[index].nodes.length; i++){
						var nodesElement = permission[index].nodes[i];
						//console.log(nodes);
						if(father.permissionId === nodesElement.permissionId){
							//console.log(123);
							//console.log(arguments[1][childIndex]);
							nodesElement.nodes = father.nodes;
						}
					}
				}
			}else{
				 delete father.nodes;
			}
			
		}
	}
	//console.log(permission);
	return permission;
}




/**
 * 展示添加权限操作框
 */
function showPermissionModule(target) {
	showModal('../modalTemplates/addPermission.html');
	setTimeout(function() {
		$('#permissionMark').chosen();
		permissionModuleCheck('addPermissonForm');
		//当表单验证失败时不绑定后续事件
        $("#addMenuForm").submit(function(ev){ev.preventDefault();});
	},100);
}


/**
 * 添加权限
 * @param level 权限登记
 */
function addPermissionModule(level) {
	var permission = {
		permissionName : $('#permissionName').val(),//权限名
		description : $('#description').val(),//权限描述
		permissionMark : $('#permissionMark').val(),//权限标记
		permissionLevel : level,//父权限等级加1
		fatherPermissionId : '',//父权限Id
		status : 1//权限状态，默认为1
	};
	if(level === 2){
		//二级权限需要填写父权限
		permission.fatherPermissionId = modalPermission.permissionId;
		permission.permissionLevel = modalPermission.permissionLevel + 1;
	}
	//console.log(permission);
	var bootstrapValidator = $("#addPermissonForm").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){
    	$.ajax({
    		url : '../perm/addPermission',
    		type : 'POST',
    		dataType: "json",
    		contentType : 'application/json',
    		data : JSON.stringify(permission),
    		success: function(result) {
    			$("#modalModule").modal('hide');
    			if(result.success){
    				//成功
    				toastr.success(result.msg);
    				//更新权限树
    				getPermissionList();
    			}else{
    				toastr.error(result.msg);
    			}
    		}
    	});
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
	 
}

/**
 * 单选值判断
 */
function judgeRedio(obj) {
	for(i in obj) {
		if(obj[i].checked) {
			return obj[i].value;
		}
	}
}
/**
 * 获取单个权限的信息
 * @param permissionId 权限Id
 */
function getSinglePermisson(permissionId) {
	$.ajax({
		url : '../perm/getPermission',
		type: 'POST',
		dataType :'json',
		contentType : 'application/json',
		data : JSON.stringify({
			permissionId : permissionId
		}),
		success: function(data) {
			if(data.success) {
				modalPermission = data.result;
			}
		}
	});
}

/**
 * 渲染子权限
 * @param childNodes 要渲染的子权限
 */
function getChildPermission(childNodes) {
	//console.log(childNodes);
	setTimeout(function(){
		if(childNodes == undefined){
			$('#childPermissionMsg').text('当前权限无子节点，添加一个吧');
		}else{
			$('#childPermissionMsg').text('子权限如下：');
			var childPermissionStr = '<ul class="list-group">';
			for(index in childNodes){
				childPermissionStr += '<li class="list-group-item">'+ childNodes[index].text +'</li>'
			}
			childPermissionStr +='</ul>';
			//console.log(childMenuStr)
			$('#childPermissionContent').append(childPermissionStr);
		}
	},300);
}


/**
 * 切换权限状态
 * @param target 当前节点数据
 */
function updatePermissionStatus(target) {
	$.ajax({
		url :'../perm/updatePermissionStatus',
		type : 'PUT',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			permissionId : target.value
		}),
		success : function(result) {
			if(result.success) {
				//关闭模态框
				$('#modalModule').modal('hide');
				//更新成功
				toastr.success(result.msg);
				//刷新
				//getMenu();
				//getPermissonMenus();
			}else{
				//更新失败
				toastr.error(result.msg);
			}
		}
	});
}

/**
 * 删除权限节点
 * @param target
 */
function removePermissionNode(target) {
	$.ajax({
		url : '../perm/deletePermission',
		type: 'DELETE',
		dataType :'json',
		contentType : 'application/json',
		data : JSON.stringify({
			//要删除菜单节点的id
			permissionId : target.value
		}),
		success : function(result){
			$("#modalModule").modal('hide');
			if(result.success){
				//成功
				toastr.success(result.msg);
				//更新权限管理数据
				getPermissionList();
			}else{
				toastr.error(result.msg);
			}
		}
	});
}

/**
 * 更新权限信息
 */
function editPermissionNode() {
	modalPermission.permissionName = $('#editPermissonForm #permissionName').val();//权限模块名
	modalPermission.description = $('#editPermissonForm #description').val();//权限模块描述
	modalPermission.permissionMark = $('#editPermissonForm #editPermissionMark').val();//权限标记
	modalPermission.status = $('#editPermissonForm #status').val();//权限状态
	modalPermission.fatherPermissionId = $('#editPermissonForm #faherPermissionOption').val();//权限父权限
	var bootstrapValidator = $("#editPermissonForm").data('bootstrapValidator');
	bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){ 
    	//验证成功
    	$.ajax({
    		url : '../perm/updatePermission',
    		type : 'PUT',
    		dataType : 'json',
    		contentType : 'application/json',
    		data : JSON.stringify(modalPermission),
    		success : function(result) {
    			$("#modalModule").modal('hide');
    			if(result.success){
    				//成功
    				toastr.success(result.msg);
    				//更新权限管理数据
    				getPermissionList();
    			}else{
    				toastr.error(result.msg);
    			}
    		}
    	});
    }else{
    	//验证失败
    	toastr.error('有信息未填写，填写完指定信息再次提交');
    }
}

/**
 * 通过级别获取父级别权限
 * @param level 权限级别
 * @param fatherPermissionId 原父权限Id
 */
function getFatherPermissionByLevel(level,fatherPermissionId) {
	var addFatherOptions = [];
	$.ajax({
		url : '../perm/getPermissionByLevel',
		type : 'POST',
		dataType: "json",
		contentType : 'application/json',
		data : JSON.stringify({
			permissionLevel : level
		}),
		success : function(data) {
			for(index in data.resultList){
				addFatherOptions.push({
					id : data.resultList[index].permissionId,
					name : data.resultList[index].description
				});
			}
			getOption(addFatherOptions, 'faherPermissionOption', true, fatherPermissionId);
		}
	});
}


/**
 * 监听角色改变
 */
/*function getRolePermission(){
	$("#roleOption").change(function(){
		$.ajax({
			url : '../perm/getAllPermissionsByRole',
			type : 'POST',
    		dataType : 'json',
    		contentType : 'application/json',
    		data : JSON.stringify({
    			roleId : $("#roleOption").val()
    		}),
    		success: function (data) {
    			//console.log(data.resultList);
    			if(data.success) {
    				var permissionResult = [];
    				//console.log(getObjectParams(data.resultList));
    				var params = getObjectParams(data.resultList);
    				//循环分类
    				for(var i = 0 ; i< params.length; i++) {
    					if(i !== params.length-1){
    						permissionResult = gradePermissionList(permissionResult,
    							data.resultList[params[i]], 
    							data.resultList[params[i+1]]
    						)
    					}
    				}
    				//去除一级权限没子权限
    				for(index in permissionResult){
    					if(permissionResult[index].nodes.length == 0) {
    						delete permissionResult[index].nodes;
    					}
    				}
    				renderTree('permissionTree',permissionResult);
    				if(permissionResult.length === 0){
    					$('#treeMsg').fadeIn(500);
    				}else{
    					$('#treeMsg').hide();
    					$('#permissionTree').treeview('expandAll', { levels: 1, silent: true });
    				}
    			}
    		}
		})
	});
}*/

/**
 * 权限页面，监听角色的改变
 */
function getPermissionByRoleChange() {
	$("#roleOption").change(function(){
		refresh();
	});
}


function refresh() {
	loading();
	getBindPermission($("#roleOption").val());
	setTimeout(function() {
		getUnBindPermission($("#roleOption").val());
	},1000);
	
	
}
/**
 * 已绑定权限
 */
function getBindPermission(roleId) {
	bind = true;
	$.ajax({
		url : '../perm/getAllBindPermissionsByRole',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			roleId : roleId
		}),
		success: function (data) {
			//console.log(data);
			//$('#BindPermissionTree').empty();
			renderTree('BindPermissionTree',allotPermission(data.resultList));
		}
	});
}

/**
 * 未绑定权限
 */
function getUnBindPermission(roleId) {
	bind = true;
	$.ajax({
		url : '../perm/getAllUnBindPermissionsByRole',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			roleId : roleId
		}),
		success: function (data) {
			//console.log(data);
			//$('#UnBindPermissionTree').empty();
			renderTree('UnBindPermissionTree',allotPermission(data.resultList));
		}
	});
}

/**
 * 权限分类
 * @param permissionList
 * @returns {Array}
 */
function allotPermission(permissionList){
	var permissionResult = [];
	//console.log(getObjectParams(data.resultList));
	var params = getObjectParams(permissionList);
	//循环分类
	for(var i = 0 ; i< params.length; i++) {
		if(i !== params.length-1){
			permissionResult = gradePermissionList(permissionResult,
				permissionList[params[i]], 
				permissionList[params[i+1]]
			)
		}
	}
	//去除一级权限没子权限
	for(index in permissionResult){
		if(permissionResult[index].nodes.length == 0) {
			delete permissionResult[index].nodes;
		}
	}
	return permissionResult;
}

/**
 * 角色解除绑定权限
 */
function unbindPermission() {
	//cnsole.log(bindPermissionId);
	$.ajax({
		url : '../perm/unBindRolePerminssion',
		type : 'DELETE',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			roleId : $("#roleOption").val(),
			permissionId : bindPermissionId
		}),
		success: function(data) {
			console.log(data);
			if(data.success) {
				toastr.success(data.msg);
				refresh();
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

/**
 * 角色绑定权限
 */
function bindPermission() {
	//console.log(bindPermissionId);
	$.ajax({
		url : '../perm/bindRolePermission',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify({
			roleId : $("#roleOption").val(),
			permissionId : bindPermissionId
		}),
		success: function(data) {
			console.log(data);
			if(data.success) {
				toastr.success(data.msg);
				refresh();
			}else{
				toastr.error(data.msg);
			}
		}
	})
}

 /**
 * *************************   表单验证方法  ************************************************************************
 */
/**
 * 添加菜单表单验证
 * @param order 最小排序
 */
function menuModuleCheck(order) {
	//console.log(modalMenu);
	$('#addMenuForm').bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//菜单模块名
			menuName : {
				message : '菜单模块名验证失败',
				validators : {
					notEmpty: {
						message : '菜单模块名不能为空'
					}
				}
			},
			//方法名
			linkUrl : {
				message : '方法名验证失败',
				validators : {
					notEmpty : {
						message : '访问方式不能为空'
					}
				}
			},
			permissionOption : {
				message : '绑定权限验证失败',
				validators : {
					callback :{
						message : '绑定的权限不能为空',
						callback : function(value) {
							if(value === -1) {
								return false;
							}else{
								return true;
							}
						}
					}
					
				}
			}/*,
			//排序号验证
			menuOrder : {
				message : '排序号验证失败',
				validators :{
					notEmpty: {
                        message: '排序号不能为空'
                    },
                    numeric: {
                        message: '排序号只能为数字'
                    },
                    //自定义方法
                    callback: {  
                        message: '排序号需在' + order + '之后',  
                        callback: function(value) {
                        	if((value-order)>0){
                        		return true;
                        	}else{
                        		return false;  	
                        	}
                        }  
                    }  
				}
			}*/
		}
	});
}

/***
 * 修改菜单表单验证
 * @param order
 */

function editMenuModuleCheck(order) {
	$('#editMenuForm').bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//菜单模块名
			editMenuName : {
				message : '菜单模块名验证失败',
				validators : {
					notEmpty: {
						message : '菜单模块名不能为空'
					}
				}
			},
			//方法名
			editLinkUrl : {
				message : '方法名验证失败',
				validators : {
					notEmpty : {
						message : '访问方式不能为空'
					}
				}
			},
			//排序号验证
			editMenuOrder : {
				message : '排序号验证失败',
				validators :{
					notEmpty: {
                        message: '排序号不能为空'
                    },
                    numeric: {
                        message: '排序号只能为数字'
                    },
                    //自定义方法
                    callback: {  
                        message: '排序号需在' + order + '之后',  
                        callback: function(value) {
                        	if((value-order)>0){
                        		return true;
                        	}else if(value == modalMenu.menuOrder){
                        		return true;
                        	}else{
                        		return false;  	
                        	}
                        }  
                    }  
				}
			}
		}
	});
}

/**
 * 添加权限表单
 * @param moduleName 权限表单模块名
 */
function permissionModuleCheck(moduleName) {
	$('#' + moduleName).bootstrapValidator({
		message : '值不能为空',
		//验证字段
		fields : {
			//菜单模块名
			permissionName : {
				message : '权限模块名验证失败',
				validators : {
					notEmpty: {
						message : '权限模块名不能为空'
					}
				}
			},
			//方法名
			description : {
				message : '权限描述验证失败',
				validators : {
					notEmpty : {
						message : '权限描述不能为空'
					}
				}
			}
		}
	});
}

/**
 * ****************************************************   util     *********************************************
 */

/**
 * 去除字符串的中的空字符串
 * @param arr 要操作的数组
 * @returns {Array} 返回结果数组
 */ 
function skipEmptyElementForArray(arr){  
    var a = [];  
    $.each(arr,function(i,v){  
        var data = $.trim(v);//$.trim()函数来自jQuery  
        if('' != data){  
            a.push(data);  
        }  
    });  
    return a;  
}  



//显示模态框
var showModal = function(url) {   
	  var remote = url; 
	  if (remote != "") {   
		  $("#modalContent").load(remote, function() {
			  $("#modalModule").modal('show');   
		  });  
		  //$('#modalModule').css("width", "200px");
	  }
	  setModal();
};



//设置模态框属性
function setModal() {
	// 模态对话框隐藏时移除数据  
  $("#modalModule").on("hidden", function() {    
  	$(this).removeData("modal");
  	//关闭模态框把数据清楚
  	modalMenu = {};
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

/**
 * 获取数组对象中的属性数组
 * @param obj 要遍历的数组对象
 * @returns {Array} 返回一个排序好的属性数组
 */
function getObjectParams(obj) {
	var params = [];
	for(var prop in obj){
		params.push(prop);
	}
	//排序
	params.sort();
	//返回
	return params
}

