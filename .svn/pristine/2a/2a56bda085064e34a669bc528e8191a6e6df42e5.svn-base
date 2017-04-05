<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/lib/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/test.js">
	
</script>
<body>

	<center>
		<br>
		<div align="left">
			<form action="loginAction" method="post">
				用户： <select id="userId1">
					<option value="admin">admin</option>
					<option value="person">person</option>
					<option value="company">company</option>
					<option value="college">college</option>
					<option value="agency">agency</option>
					<option value="scientify">scientify</option>
					<option value="platform">platform</option>
				</select> <br> <br> 密码： <input type="text" value="1234"
					id="password" /> <br> <br> <input type="submit"
					value='登录' id="submit" /> <br> <a
					href="${pageContext.request.contextPath }/user/logout">注销</a>
			</form>
		</div>
	</center>


	<hr>

	<p>政策和通知模块</p>
	信息id:
	<input type="text" name="requireId" id="infoId">
	<br> 信息类型:
	<input type="text" name="requireId" id="infoType">
	<br>
	<a href="${pageContext.request.contextPath }/info/createNewInfo"
		id="createNewInfo">创建信息</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath }/info/getInfoById"
		id="getInfoById">获取指定的信息</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/info/getInfoPageByCondition"
		id="getInfoPageByCondition">获取一页信息</a>
	<br>
	<a href="${pageContext.request.contextPath }/info/removeInfoById"
		id="removeInfoById">删除信息信息</a>
	<br>
	<a href="${pageContext.request.contextPath }/info/updateInfoStatusById"
		id="updateInfoStatusById">更新信息状态</a>
	<br>
	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/info/uploadWordFile">
		<p>
			上传政策或通知文件： <input type="file" name="file" /></ p><br> <br>政策或通知id:<input
				type="text" name="bindId"><br> <br>描述:<input
				type="text" name="desc"> <br> <input type="submit"
				value="上传" " /> <br>
	</form>
	<hr>
	<p>技术需求模块</p>
	<a
		href="${pageContext.request.contextPath }/requirement/createNewTechRequireInfo"
		id="createNewTechRequireInfo">创建技术需求</a>
	<br> 技术需求id:
	<input type="text" name="requireId" id="requireId">
	<br>
	<a
		href="${pageContext.request.contextPath }/requirement/getTechRequireInfoById"
		id="getTechRequireInfoById">获取指定的技术需求</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/requirement/getAuditTechRequireInfoById"
		id="getAuditTechRequireInfoById">获取指定的技术需求（管理员）</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/requirement/getTechRequireCountByCondition"
		id="getTechRequireCountByCondition">获取技术需求数目信息</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/requirement/getTechPageByCondition"
		id="getTechPageByCondition2">获取一页技术需求信息</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/requirement/removeTechRequireInfo"
		id="removeTechRequireInfo">删除技术需求信息</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/requirement/updateTechRequireStatus"
		id="updateTechRequireStatus">更新技术供需求状态</a>
	<br>
	<hr>
	<p>技术供给模块</p>
	<a
		href="${pageContext.request.contextPath }/supply/createNewTechSupplyInfo"
		id="createNewTechSupplyInfo">创建技术供给</a>
	<br> 技术供给id:
	<input type="text" name="supplyId" id="supplyId">
	<br>
	<a
		href="${pageContext.request.contextPath }/supply/getTechSupplyInfoById"
		id="getTechSupplyInfoById">获取指定的技术供给</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/supply/getAuditTechSupplyInfoById"
		id="getAuditTechSupplyInfoById">获取指定的技术供给（管理员）</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/supply/getProduceCountByCondition"
		id="getProduceCountByCondition">获取技术供给数目信息</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/supply/getTechPageByCondition"
		id="getTechPageByCondition">获取一页技术供给信息</a>
	<br>
	<a href="${pageContext.request.contextPath }/supply/removeTechSupply"
		id="removeTechSupply">删除技术供给信息</a>
	<br>
	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/upload/uploadTechSupplyTextEnclosure">
		<p>
			上传技术供给附件： <input type="file" name="file" /></ p><br> <br>技术供给id:<input
				type="text" name="bindId"><br> <br>描述:<input
				type="text" name="desc"> <br> <input type="submit"
				value="上传" " /> <br>
	</form>

	<hr>

	<p>产品成果模块</p>
	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/upload/uploadProduceImage">
		<p>
			上传产品成果图片： <input type="file" name="file" /></ p><br> <br>产品id:<input
				type="text" name="bindId"><br> <br>描述:<input
				type="text" name="desc"> <br> <input type="submit"
				value="上传" " /> <br> <br> <a
				href="${pageContext.request.contextPath }/produce/getProducesPageByCondition"
				id="getProducesPageByCondition">获取产品成果数据列表</a> <br>
	</form>
	<br>
	<a
		href="${pageContext.request.contextPath }/audit/getAuditUserInfoDetails"
		id="getAuditUserInfoDetails">获取审核细节信息</a>
	<br> 产品id:
	<input type="text" name="produceId" id="produceId">
	<br>
	<a
		href="${pageContext.request.contextPath }/produce/updateIsRecommenedStatus"
		id="updateIsRecommenedStatus">更新推荐状态</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/produce/updateProduceStatus"
		id="updateProduceStatus">更新产品状态</a>
	<br>
	<a href="${pageContext.request.contextPath }/produce/createNewProduce"
		id="createNewProduce">创建新的产品成果</a>
	<a href="${pageContext.request.contextPath }/produce/removeProduce"
		id="removeProduce">删除产品成果</a>
	<a href="${pageContext.request.contextPath }/produce/getProduceById"
		id="getProduceById">获取指定产品成果</a>
	<hr>
	<a href="${pageContext.request.contextPath }/user/test">测试</a>
	<a href="${pageContext.request.contextPath }/user/delete" id="delete">删除</a>

	用户名：
	<input type="text" id="userId" /> 用户类型：
	<input type="text" id="userType" />
	<a href="${pageContext.request.contextPath }/user/register" id="test">添加</a>
	<br>
	<a href="${pageContext.request.contextPath }/menu/menuList" id="menu">加载菜单</a>
	<a href="${pageContext.request.contextPath }/menu/updateMenuStatus"
		id="updateMenu">禁用/启用菜单</a>
	<a href="${pageContext.request.contextPath }/perm/addPermission"
		id="addPermission">添加权限</a>
	<br>
	<a href="${pageContext.request.contextPath }/user/getCurrentUser"
		id="getCurrentUser">获取当前用户</a>
	<br>
	<a href="${pageContext.request.contextPath }/perm/getAllRoles"
		id="getAllRoles">获取所有角色</a>
	<br>
	<a href="${pageContext.request.contextPath }/userInfo/getPersonInfo"
		id="getPersonInfo">个人信息维护</a>
	<br>
	<a href="${pageContext.request.contextPath }/userInfo/getCompanyInfo"
		id="getPersonInfo">企业信息维护</a>
	<br>
	<a href="${pageContext.request.contextPath }/userInfo/getAgencyInfo"
		id="getPersonInfo">中介机构信息维护</a>
	<br>
	<a href="${pageContext.request.contextPath }/userInfo/getScientifyInfo"
		id="getPersonInfo">科研单位信息维护</a>
	<br>
	<a href="${pageContext.request.contextPath }/userInfo/getCollegeInfo"
		id="getPersonInfo">高校用户信息维护</a>
	<br>
	<a href="${pageContext.request.contextPath }/menu/getChildMenus"
		id="getChildMenus">获取子菜单</a>
	<br>
	<a href="${pageContext.request.contextPath }/upload/uploadIDCardImage"
		id="uploadIDCardImage">上传照片</a>
	<br>

	<form id="uploadForm" method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/upload/uploadPersonEnclosure">
		<p>
			上传文件： <input type="file" name="file" /></ p> <input type="submit"
				value="上传" " />
	</form>
	<a href="${pageContext.request.contextPath }/audit/getAuditOutlinePage"
		id="getAuditOutlinePage">获取审核信息</a>
	<br>
	<a href="${pageContext.request.contextPath }/perm/getUnBindPermissions"
		id="getUnBindPermissions">获取所有未绑定权限</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/perm/getUnBindPermissionsByMenuId"
		id="getUnBindPermissionsByMenuId">获取所有未绑定权限和指定菜单的权限</a>
	<br>
	<a href="${pageContext.request.contextPath }/menu/getMenu" id="getMenu">获取指定菜单</a>
	<br>
	<a href="${pageContext.request.contextPath }/perm/getPermissionByLevel"
		id="getPermissionByLevel">获取指定级别权限</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/audit/getUserInfoAuditStatus"
		id="getUserInfoAuditStatus">获取当前用户信息审核状态</a>
	<br>
	<a
		href="${pageContext.request.contextPath }/audit/getUnAuditUserInfosCountByUserType"
		id="getUnAuditUserInfosCountByUserType">获取未审核信息数目</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath }/menu/getAllMenusByLevel"
		id="getAllMenusByLevel">获取指定级别菜单</a>
	<br>

	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/upload/uploadUnitCodeImage">
		<p>
			上传单位机构代码图片： <input type="file" name="file" /></ p> <input
				type="submit" value="上传" " />
	</form>
	<br>

	<hr>
	<p>审核模块</p>
	被审核用户
	<input type="text" id="auditUser">
	<a
		href="${pageContext.request.contextPath }/audit/getAuditUserInfoDetails"
		id="getAuditUserInfoDetails">获取审核细节信息</a>
	<br>
	<hr>



</body>
</html>