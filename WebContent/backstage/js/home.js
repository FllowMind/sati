/**
 * Created by Administrator on 2017/3/11.
 */
var user = {};
$(function() {
	getDefault();
	//提示的条件初始化
    toastr.options = {
		"positionClass": "toast-top-center",//显示位置
		"timeOut": "2000" //展现时间
    }
    
}());

///平台默认页
function getDefault() {
    $.get('./templates/home.html', function(data) {
        $('#content').html(data);
        //等待加载
        loading();
        //获取菜单
        getMenu(); 
      //获取当前用户
        getSessionUser();
    });
}

//添加供给页
function getSupply() {
    $.get('./templates/addSupply.html', function(data) {
        $('#content').html(data);
    });
}

//添加需求页
function getDemand() {
    $.get('./templates/addDemand.html', function(data) {
        $('#content').html(data);
    });
}


//供给或需求管理页面
function getManage(type){
    $.get('./templates/manage.html', function(data) {
        $('#content').html(data);
        if(type == 1){
            //供给
            $('#title-name').text('供给');
            $('#nav-name').text('供给')
        }else if(type == 2) {
            //需求
            $('#title-name').text('需求');
            $('#nav-name').text('需求');
        }
    });
}



//跳转管理页
function getManagment() {
	//获取导航的名称
	var navTitle = arguments[0].name;
	$.get('./templates/managment.html', function(data) {
        $('#content').html(data);
        $('#navTitle').text(navTitle);
    });
}



//跳转菜单管理页
function getMenuManagment() {
	var navTitle = arguments[0].name;
	$.get('./templates/menuManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        //获取所有菜单
        getPermissonMenus();
    });
}

//权限管理
function getPermissionManagment(){
	var navTitle = arguments[0].name;
	$.get('./templates/permissionManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        loading();
        //获取所有权限
        getPermissionList();
        //获取所有角色
        //getRoles();
        //监听角色改变菜单
        //getRolePermission();
    });
}


/**
 * 角色管理
 */
function getRoleManagment() {
	var navTitle = arguments[0].name;
	$.get('./templates/roleManagment.html', function(data) {
		$('#content').html(data);
		$('.navTitle').text(navTitle);
		loading();
		 //获取所有角色
        getRoles();
        
        //监听角色变化
        getPermissionByRoleChange();
	});
}



/**
 * 个人信息维护
 */
function getPersonInfoManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/managment/userManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        
        $(".datepicker").datepicker({
        	format: 'yyyy/mm/dd',
            startDate: '-3d'
        });
        //表单验证
        personModuleCheck();
        //获取用户信息
        getPersonInfo();
        //上传控件监听
        upload();
        
    });

}

/**
 * 企业用户维护
 */
function getCompanyInfoManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/managment/companyInfoManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
       
        //获取用户信息
        getCompanyInfo();
        //表单验证
        companyModuleCheck();
        //监听上传
        uploadUnitCodeImage();
    });
}

/**
 * 科研单位信息维护
 */
function getScientifyManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/managment/collegeAndScientifyManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        
        //获取科研单位信息
        getScientifyInfo();
        //表单校验
        collegeAndScientifyModuleCheck();
        //监听上传
        uploadUnitCodeImage();
    });
}

/**
 * 高校单位信息维护
 */
function getCollegeManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/managment/collegeAndScientifyManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        //获取高校信息
        getCollegeInfo();
        //表单校验
        collegeAndScientifyModuleCheck();
        //监听上传
        uploadUnitCodeImage();
    });
}


/**
 * 中介单位信息维护
 */
function getAgencyManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/managment/agencyManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        $(".datepicker").datepicker({
        	format: 'yyyy/mm/dd',
            startDate: '-3d'
        });
        //获取中介机构信息
        getAgencyInfo();
        //中介机构表单校验
        AgencyModuleCheck();
        //监听上传
        uploadUnitCodeImage();
    });
}



/**
 * 个人用户审核管理
 */
function getAuditUserManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/Audit/userAudit.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
     
        //获取个人用户审核信息
        getAuditUserInfo();
    });
}







/**
/**
 * 企业用户审核管理
 */
function getCompanyAuditManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/Audit/userAudit.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        //获取企业用户审核信息
        getCompanyAuditInfo();
    });
}





/**
 * 高校用户管理
 */
function getAuditCollegeManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/Audit/userAudit.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
       
        
        //获取高校审核信息
        getCollegeAuditInfo();
    });
}




/**
 * 科研单位用户审核
 */
function getAuditScientifyManament() {
	var navTitle = arguments[0].name;
    $.get('./templates/Audit/userAudit.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);

        //获取科研单位审核信息
        getScientifyAuditInfo();
    });
}


/**
 * 中介单位用户审核
 */
function getAuditAgencyManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/Audit/userAudit.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        //获取中介审核信息
        getAgencyAuditInfo();
    });
}




/**
 * ********************  详情页        ****************************
 */
/**
 * 个人用户详情页
 */
function getAuditUserDetails(id) {
	 $.get('./templates/Audit/auditUserDetails.html', function(data) {
	       $('#detailsModule').html(data);
	       
	     //获取个人用户详情页的数据
	      getAuditUserContent(id, 'auditUserDetailsForm');
     });
	 
}



/**
 * 公司详情页
 */
function getAuditCompanyDetails(id) {
	 $.get('./templates/Audit/auditCompanyDetails.html', function(data) {
	       $('#detailsModule').html(data);
    });
	 //获取指定企业详情页信息
	 getAuditUserContent(id, 'auditCompanyDetailsForm');
}
/**
 * 高校科研详情页
 */
function getAuditCollegeAndScientifyDetails(id) {
	$.get('./templates/Audit/auditCollegeAndScientifyDetails.html', function(data) {
	       $('#detailsModule').html(data);
	       
	       getAuditUserContent(id, 'auditCollegeAndScientifyDetailsForm');
	});
}

/**
 * 中介机构详情页
 */
function getAuditAgencyDetails(id) {
	$.get('./templates/Audit/auditAgencyDetails.html', function(data) {
	       $('#detailsModule').html(data);
	       
	       getAuditUserContent(id, 'auditAgencyDetailsForm');
	});
}

/**
 * ********************  详情页   end     ****************************
 */
/**
 * 发布我的产品成果
 */
function getProducePublish() {
	var navTitle = arguments[0].name;
    $.get('./templates/Produce/producePublish.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        $('.block-content').hide();
        
        //点击发布产品成果即创建一个新的产品成果
        setTimeout(function() {
        	createProduce();
        },100);
    });
}

/**
 * 我的产品成果管理页
 */
function getProduceManagment() {
	var navTitle = arguments[0].name || arguments[0];
    $.get('./templates/Produce/produceManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        //默认查询前5条数据
        getProducesPageByCondition(1, 5);
        $(".datepicker").datepicker({
        	format: 'yyyy/mm/dd',
            startDate: '-3d'
        });
    });
}

/**
 * 产品审核
 */
function getProduceAuditManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/Produce/produceAuditManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        setTimeout(function() {
        	 //默认获取前5条未审核或正在审核的产品成果信息
        	 getProduceAuditInfo();
        	 $(".datepicker").datepicker({
             	format: 'yyyy/mm/dd',
                 startDate: '-3d'
             });
        },300);
    });
}

/**
 * *********************   技术供给     ********************
 */

/**
 * 发布我的技术供给
 */
function getTechSupplyPublish() {
	var navTitle = arguments[0].name;
    $.get('./templates/supply/techSupplyPublish.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        /**
         * 创建一个新的技术供给
         */
        createSupply();
        //setTechData('techSupplyForm');
    });
}

/**
 * 管理我的技术供给
 */
function getTechSupplyManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/supply/techSupplyManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        //默认查询前5条数据
        ordinaryTechPageByCondition(1,5);
        
        
        //下拉框
        setTimeout(function() {
        	$.ajaxSettings.async = true;//清除原先设置
        	 getTechStatusSelect('supplyAuditStatus');
        	 $(".datepicker").datepicker({
             	format: 'yyyy/mm/dd',
                 startDate: '-3d'
             });
        },100);
       
    });
}

/**
 * 审核用户技术供给
 */
function getAuditTechSupplyManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/supply/auditTechSupplyManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        
        setTimeout(function() {
       	 //默认获取前5条未审核或正在审核的产品成果信息
	    	getAuditSupplyByCondition();
	       	$(".datepicker").datepicker({
            	format: 'yyyy/mm/dd',
                startDate: '-3d'
            });
	       	//填充下拉菜单
       },300);
    });
}

/**
 * *****************    技术需求           *****************
 */

/**
 * 发布我的技术需求
 */
function getTechRequirePublish() {
	var navTitle = arguments[0].name;
    $.get('./templates/require/techRequirePublish.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        /**
         * 创建一个技术需求
         */
        createNewTechRequireInfo();
    });
}

/**
 * 管理我的技术需求
 */
function getTechRequireManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/require/techRequireManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        ordinaryTechRequirePageByCondition(1,5);
       
        
        //下拉框
        setTimeout(function() {
        	 getTechStatusSelect('requireAuditStatus');
        	 $(".datepicker").datepicker({
             	format: 'yyyy/mm/dd',
                 startDate: '-3d'
             });
        },300);
    });
}



/**
 * 审核用户技术需求
 */
function getAuditTechRequireManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/require/auditTechRequireManagment.html', function(data) {
        $('#content').html(data);
        $('.navTitle').text(navTitle);
        
        
        setTimeout(function() {
       	 //默认获取前5条未审核或正在审核的产品成果信息
        	getAuditRequireByCondition();
	       	$(".datepicker").datepicker({
            	format: 'yyyy/mm/dd',
                startDate: '-3d'
            });
	       	//填充下拉菜单
       },300);
    });
}



/**
 * *****************************     公告         ******************************
 */


/**
 * 管理公告
 */
function getNoticeManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/notice/noticeManagment.html', function(data) {
    	$('#content').html(data);
        $('.navTitle').text(navTitle);

        
        ordinaryPageByCondition(2,'noticeTable', true);
        setTimeout(function(){
        	$('#infoStatus').chosen();
        	$(".datepicker").datepicker({
            	format: 'yyyy/mm/dd',
                startDate: '-3d'
            });
        },100);
    });
}


/**
 * 发布公告
 */
function getNoticePublish() {
	var navTitle = arguments[0].name;
    $.get('./templates/notice/noticePublish.html', function(data) {
    	$('#content').html(data);
        $('.navTitle').text(navTitle);
         
        setTimeout(function() {
        	createNewInfo(2);
        },100);

    });
}

/** 
 * **********************  政策和法规          ******************
 */
/**
 * 政策法规管理
 */
function getPolicyManagment() {
	var navTitle = arguments[0].name;
    $.get('./templates/policy/policyManagment.html', function(data) {
    	$('#content').html(data);
        $('.navTitle').text(navTitle);

        
        ordinaryPageByCondition(1,'policyTable', true);
        setTimeout(function(){
        	$('#infoStatus').chosen();
        	$(".datepicker").datepicker({
            	format: 'yyyy/mm/dd',
                startDate: '-3d'
            });
        },100);
    });
}

/**
 * 发布政策
 */
function getPolicyPublish() {
	var navTitle = arguments[0].name;
    $.get('./templates/policy/policyPublish.html', function(data) {
    	$('#content').html(data);
        $('.navTitle').text(navTitle);
         
        setTimeout(function() {
        	createNewInfo(1);
        },100);

    });
}

/**
 * 等待加载
 */
function loading() {
	showModal('../modalTemplates/loading.html');
	$("#modalModule").modal({backdrop : 'static',keyboard : false});
	setTimeout(function() {
    	$("#modalModule").modal('hide');
    },2200);
}