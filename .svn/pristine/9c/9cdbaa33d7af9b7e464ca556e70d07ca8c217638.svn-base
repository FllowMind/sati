/**
 * 
 */

$(document).ready(function() {
	// 获取一页指定信息状态
	$("#getInfoPageByCondition").click(function() {

		var path = this.href;
		var infoType = $("#infoType").val().trim();
		var infoId = $("#infoId").val().trim();
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"infoType" : infoType,
				"pageNo" : "1",
				"pageSize" : "5"
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 更改指定信息状态
	$("#updateInfoStatusById").click(function() {

		var path = this.href;
		var infoType = $("#infoType").val().trim();
		var infoId = $("#infoId").val().trim();
		$.ajax({
			type : "PUT",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"infoId" : infoId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 删除指定信息
	$("#removeInfoById").click(function() {

		var path = this.href;
		var infoType = $("#infoType").val().trim();
		var infoId = $("#infoId").val().trim();
		$.ajax({
			type : "DELETE",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"infoId" : infoId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取指定信息
	$("#getInfoById").click(function() {

		var path = this.href;
		var infoType = $("#infoType").val().trim();
		var infoId = $("#infoId").val().trim();
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"infoId" : infoId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 创建信息
	$("#createNewInfo").click(function() {

		var path = this.href;
		var infoType = $("#infoType").val().trim();
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"infoType" : infoType
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 删除技术供需求信息
	$("#removeTechRequireInfo").click(function() {

		var path = this.href;
		var requireId = $("#requireId").val().trim();
		$.ajax({
			type : "DELETE",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"triId" : requireId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 更新技术供需求信息
	$("#updateTechRequireStatus").click(function() {

		var path = this.href;
		var requireId = $("#requireId").val().trim();
		$.ajax({
			type : "PUT",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"triId" : requireId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取指定需求信息(管理员)
	$("#getAuditTechRequireInfoById").click(function() {

		var path = this.href;
		var requireId = $("#requireId").val().trim();
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"triId" : requireId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取指定需求信息
	$("#getTechRequireInfoById").click(function() {

		var path = this.href;
		var requireId = $("#requireId").val().trim();
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"triId" : requireId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取技术需求信息数目
	$("#getTechRequireCountByCondition").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"pageNo" : "1",
				"pageSize" : "5"
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取一页技术需求信息
	$("#getTechPageByCondition2").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"pageNo" : "1",
				"pageSize" : "5",
				"publisherId" : "admin",
				"startDate" : new Date("2017-03-11 04:56:36"),
				"endDate" : new Date("2017-03-31 05:35:08")
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 创建新的技术需求信息
	$("#createNewTechRequireInfo").click(function() {

		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			contentType : 'application/json',
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取产品成果数据列表
	$("#getTechPageByCondition").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"pageNo" : "1",
				"pageSize" : "5"
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取产品成果数目
	$("#getProduceCountByCondition").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"pageNo" : "1",
				"pageSize" : "5"
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 创建技术供给信息
	$("#createNewTechSupplyInfo").click(function() {
		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			contentType : 'application/json',
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 删除指定技术供给信息
	$("#removeTechSupply").click(function() {
		var path = this.href;
		var produceId = $("#supplyId").val().trim();
		$.ajax({
			type : "DELETE",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"tsiId" : produceId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取指定技术供给信息
	$("#getAuditTechSupplyInfoById").click(function() {
		var path = this.href;
		var produceId = $("#supplyId").val().trim();
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"tsiId" : produceId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取指定技术供给信息
	$("#getTechSupplyInfoById").click(function() {
		var path = this.href;
		var produceId = $("#supplyId").val().trim();
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"tsiId" : produceId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 获取指定产品成果
	$("#getProduceById").click(function() {
		var path = this.href;
		var produceId = $("#produceId").val().trim();
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"produceId" : produceId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 
	$("#createNewProduce").click(function() {
		var path = this.href;
		var produceId = $("#produceId").val().trim();
		$.ajax({
			type : "GET",
			dataType : "json",
			contentType : 'application/json',
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 创建产品成果
	$("#removeProduce").click(function() {
		var path = this.href;
		var produceId = $("#produceId").val().trim();
		$.ajax({
			type : "DELETE",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"produceId" : produceId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 更新推荐状态
	$("#updateProduceStatus").click(function() {
		var path = this.href;
		var produceId = $("#produceId").val().trim();
		$.ajax({
			type : "PUT",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"produceId" : produceId,
				"produceStatus" : '1'
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 更新推荐状态
	$("#updateProduceStatus").click(function() {
		var path = this.href;
		var produceId = $("#produceId").val().trim();
		$.ajax({
			type : "PUT",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"produceId" : produceId,
				"produceStatus" : '1'
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});
	// 更新推荐状态
	$("#updateIsRecommenedStatus").click(function() {
		var path = this.href;
		var produceId = $("#produceId").val().trim();
		$.ajax({
			type : "PUT",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"produceId" : produceId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else if (data.resultList != null) {
						alert(JSON.stringify(data.resultList));
					} else {
						alert(data.msg);
					}
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});

	// 获取产品成果数据列表
	$("#getProducesPageByCondition").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"pageNo" : "1",
				"pageSize" : "5",
				"auditStatus" : "1",
				"startDate" : $.myTime.DateToUnix('2017-03-11 04:56:36'),
				"endDate" : $.myTime.DateToUnix('2017-03-31 05:35:08')
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}
		})

		return false;
	});

	// 获取审核的细节信息
	$("#getAuditUserInfoDetails").click(function() {
		// alert("获取审核的细节信息");
		var userId = $("#auditUser").val().trim();
		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"userId" : userId
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					if (data.result != null) {
						alert(JSON.stringify(data.result));
					} else {
						alert(JSON.stringify(data.resultList));
					}
				} else {
					alert("数据失败！" + data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器失败！");
			}

		})

		return false;
	});

	$("#test").click(function() {
		var userId = $("#userId").val().trim();
		var userType = $("#userType").val();
		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"userId" : userId,
				"userType" : userType,
				"password" : "1234",
				"email" : "platform@qq.com",
				"phoneNumber" : "18813973456"

			}),
			url : path,
			success : function(data) {
				alert(data.msg);
			}

		})

		return false;
	});

	function getPath() {

		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0, index + 1);
		return result;
	}

	$("#delete").click(function() {

		var userId = $("#userId").val();
		var path = this.href;
		$.ajax({
			type : "DELETE",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"userId" : userId
			}),
			url : path,
			success : function(data) {
				alert(data.msg);
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});

	$("#menu").click(function() {

		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			url : path,
			success : function(data) {
				alert(JSON.stringify(data));
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});

	$("#updateMenu").click(function() {
		var path = this.href;
		$.ajax({
			type : "PUT",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify({
				"menuId" : 11
			}),
			url : path,
			success : function(data) {
				alert(JSON.stringify(data));
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});
	$("#addMenu").click(function() {
		var path = this.href;
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify({

			}),
			url : path,
			success : function(data) {
				alert(data.msg);
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});

	$("#addPermission").click(function() {
		var path = this.href;
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify({

			}),
			url : path,
			success : function(data) {
				alert(data.msg);
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});

	$("#getCurrentUser").click(function() {
		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});
	$("#getAllRoles").click(function() {
		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			url : path,
			success : function(data) {
				alert(JSON.stringify(data.result));
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});
	$("#getPersonInfo").click(function() {
		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});
	$("#getChildMenus").click(function() {
		var path = this.href;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data : JSON.stringify({
				"menuId" : "11"
			}),
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});

	$("#uploadIDCardImage").click(function() {
		var path = this.href;
		$.ajax({
			type : "POST",
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg);
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("失败");
			}

		})
		return false;
	});

	$("#getAuditOutlinePage").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"pageNo" : "2",
				"pageSize" : "1"
			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});

	$("#getUnBindPermissions").click(function() {
		var path = this.href;
		$.ajax({
			type : "GET",
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.resultList));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});
	$("#getUnBindPermissionsByMenuId").click(function() {
		var path = this.href;
		$.ajax({
			type : "POST",
			dataType : "json",
			url : path,
			contentType : 'application/json',
			data : JSON.stringify({}),
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.resultList));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});

	$("#submit").click(function() {
		var userId = $("#userId1").val().trim();
		var password = $("#password").val().trim();
		var path = getPath() + "/user/loginAction";
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			url : path,
			data : JSON.stringify({
				"userId" : userId,
				"password" : password
			}),
			success : function(data) {
				alert(data.msg);
			},
			error : function(data) {
				alert("连接服务器失败！");
			}

		})

		return false;
	});

	$("#getMenu").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"menuId" : "11",

			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});

	$("#getPermissionByLevel").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify({
				"permissionLevel" : "2",

			}),
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.resultList));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});
	$("#getUserInfoAuditStatus").click(function() {

		var path = this.href;
		$.ajax({
			type : "get",
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});
	$("#getUnAuditUserInfosCountByUserType").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			contentType : "application/json",
			data : JSON.stringify({
				"userType" : "3",

			}),
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});

	$("#getAllMenusByLevel").click(function() {

		var path = this.href;
		$.ajax({
			type : "post",
			contentType : "application/json",
			data : JSON.stringify({
				"menuLevel" : "3",

			}),
			dataType : "json",
			url : path,
			success : function(data) {
				if (data.success) {
					alert(data.msg + JSON.stringify(data.result));
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("连接服务器错误！")
			}

		})

		return false;
	});

});

(function($) {
	$
			.extend({
				myTime : {
					/**
					 * 当前时间戳
					 * 
					 * @return <int> unix时间戳(秒)
					 */
					CurTime : function() {
						return Date.parse(new Date()) / 1000;
					},
					/**
					 * 日期 转换为 Unix时间戳
					 * 
					 * @param <string>
					 *            2014-01-01 20:20:20 日期格式
					 * @return <int> unix时间戳(秒)
					 */
					DateToUnix : function(string) {
						var f = string.split(' ', 2);
						var d = (f[0] ? f[0] : '').split('-', 3);
						var t = (f[1] ? f[1] : '').split(':', 3);
						return (new Date(parseInt(d[0], 10) || null, (parseInt(
								d[1], 10) || 1) - 1,
								parseInt(d[2], 10) || null, parseInt(t[0], 10)
										|| null, parseInt(t[1], 10) || null,
								parseInt(t[2], 10) || null)).getTime() / 1000;
					},
					/**
					 * 时间戳转换日期
					 * 
					 * @param <int>
					 *            unixTime 待时间戳(秒)
					 * @param <bool>
					 *            isFull 返回完整时间(Y-m-d 或者 Y-m-d H:i:s)
					 * @param <int>
					 *            timeZone 时区
					 */
					UnixToDate : function(unixTime, isFull, timeZone) {
						if (typeof (timeZone) == 'number') {
							unixTime = parseInt(unixTime) + parseInt(timeZone)
									* 60 * 60;
						}
						var time = new Date(unixTime * 1000);
						var ymdhis = "";
						ymdhis += time.getUTCFullYear() + "-";
						ymdhis += (time.getUTCMonth() + 1) + "-";
						ymdhis += time.getUTCDate();
						if (isFull === true) {
							ymdhis += " " + time.getUTCHours() + ":";
							ymdhis += time.getUTCMinutes() + ":";
							ymdhis += time.getUTCSeconds();
						}
						return ymdhis;
					}
				}
			});
})(jQuery);