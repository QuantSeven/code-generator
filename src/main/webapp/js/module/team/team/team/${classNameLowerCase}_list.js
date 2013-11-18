$("document").readyfn(function() {
	var context = this, selectItem = null, selectItems = null;
	var i18nTeam = pousheng.getI18N('team'); // 初始化的时候获取国际化的文字
	var teamListGrid = $("#teamListGrid").datagrid();

	/****************************--双击查看记录--******************************/
	teamListGrid.datagrid("option", "onDblClickRow", function(index, item) {
		selectItem = item;
		showView();
		
		
	});
	
	/****************************--添加记录--*********************************/
	$("#team-toolbar").on("click","#add",function(){
		var options = {
			title : i18nTeam.txt.add,
			url : 'team/addForm'
		};
		modelDialog(options);
	});
	
	/****************************--编辑记录--*********************************/
	$("#team-toolbar").on("click","#edit",function(){
		selectItems = teamListGrid.datagrid("getSelectedRows");
		if ($.isEmptyObject(selectItems)) {
			pousheng.warnMsg(i18nTeam.msg.selectonerecordedit);
			return;
		}
		if($.isArray(selectItems) && selectItems.length > 1) {
			pousheng.warnMsg(i18nTeam.msg.onerecordedit);
			return;
		}
		selectItem = selectItems[0];
		var options = {
			title : i18nTeam.txt.add,
			url : 'team/editForm',
			requestParam : {
				teamId : selectItem.teamId
			}
		};
		modelDialog(options);
	});
	
	/****************************--删除记录--*********************************/
	$("#team-toolbar").on("click","#delete",function(){
		selectItems = teamListGrid.datagrid("getSelectedRowsIdKey");
		if (!selectItems) {
			pousheng.warnMsg(i18nTeam.msg.selectonerecorddelete);
			return;
		}
		pousheng.confirm(i18nTeam.msg.confirmdelete, function(r) {
			if (r) {
				pousheng.ajaxData("team/deleteAll", {
					data : {
						teamIds : selectItems
					}
					
				}).done(function() {
					teamListGrid.datagrid("refresh", null, null);
				});
			}
		});
	});
	/****************************--查看记录信息--*********************************/
	$("#team-toolbar").on("click","#view",function(){
		selectItems = teamListGrid.datagrid("getSelectedRows");
		if ($.isEmptyObject(selectItems)) {
			pousheng.warnMsg(i18nTeam.msg.selectonerecordview);
			return;
		}
		if($.isArray(selectItems) && selectItems.length > 1) {
			pousheng.warnMsg(i18nTeam.msg.onerecordview);
			return;
		}
		selectItem = selectItems[0];
		showView();
	});
	/****************************--查询--*********************************/
	$("#team-toolbar").on("click","#search",function(){
         teamListGrid.datagrid("refresh",null,$(context).find("form").getFieldValues());
	});
	
	
	/*************************--查看记录信息公共弹出窗口--*************************/
	var showView = function view() {
		$.modal({
			title : i18nTeam.txt.view,
			width : 600,
			height : 400,
			remote : "team/view",
			requestParam : {
				teamId : selectItem.teamId
			},
			ready : function(event, context) {
				// 弹出框口页面的初始化操作
				$(context).viewform(); // 先替换基本的标签
			},
			buttons : [ {
				text : btn.close,
				cls : "btn-primary",
				click : function() {
					$(this).modal("close");
				}
			} ]
		});
	};
	
	/*************************--添加、编辑的公共弹出窗口--*************************/
	var modelDialog = function(options) {
		var settings = {
			title : '',
			url : '',
			requestParam : ''
		};
		$.extend(true, settings, options); // true深度拷贝
		$.modal({
			title : settings.title,
			width : 600,
			height : 400,
			remote : settings.url,
			requestParam : settings.requestParam,
			ready : function(event, context) {
				// 弹出框口页面的初始化操作
			},
			buttons : [ {
				text : btn.save,
				cls : "btn-primary",
				click : function() {
					var $this = $(this);
					$this.find("form").trigger("submit", {
						success : function(data) {
							teamListGrid.datagrid("refresh", null, null);
							$this.modal("close");
						}
					});
				}
			}, {
				text : btn.cancel,
				click : function() {
					$(this).modal("close");
				}
			} ]
		});
	};
});
