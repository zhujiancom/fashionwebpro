$(function(){
	$("#flex1").flexigrid({
		url:"user_userlist.action",
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'中文名称',
			name:"chinaname",
			width:90,
			align:'center'
		},{
			display:'昵称',
			name:"nickname",
			width:90,
			align:'center'
		},{
			display:'出生日期',
			name:"birthdate",
			width:100,
			align:'center'
		},{
			display:'籍贯',
			name:"nationality",
			width:50,
			align:'center'
		},{
			display:'登录账号',
			name:"loginid",
			width:80,
			align:'center'
		},{
			display:'状态',
			name:"status",
			width:50,
			align:'center',
			process:convertStatus
		},{
			display:'是否可用',
			name:"enable",
			width:50,
			align:'center',
			process:convertEnable
		},{
			display:'性别',
			name:"sex",
			width:50,
			align:'center'
		},{
			display:'电子邮箱',
			name:"email",
			width:200,
			align:'center'
		},{
			display:'手机号',
			name:"moblie",
			width:100,
			align:'center'
		},{
			display:'QQ',
			name:"qqnum",
			width:100,
			align:'center'
		},{
			display:'个性签名',
			name:"selfsign",
			width:300,
			align:'center'
		},{
			display:'登录时间',
			name:"logintime",
			width:100,
			align:'center'
		},{
			display:'注册时间',
			name:"registetime",
			width:100,
			align:'center'
		},{
			display:'登录次数',
			name:"loginCount",
			width:30,
			align:'center'
		}],
		buttons:[{
			name:'新增',
			bclass:'add',
			onpress:action
		},{
			separator:true
		},{
			name:'修改',
			bclass:'edit',
			onpress:action
		},{
			separator:true
		},{
			name:'删除',
			bclass:'delete',
			onpress:action
		}],
		usepager:true,
		title:'用户列表信息',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10, 15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1061,
		height:376,
		sortname:'loginid'
	});
	function convertStatus(value,pid){
			//pid  :就是这列所在行的id截取row后面的数字得到的结果,tr的id格式：row123,pid的数值为123
		var _this = $(value);
		if(_this.html() == "1"){
			_this.html("<span style='color:blue'>在线</span>");
		}else{
			_this.html("<span style='color:red'>离线</span>");
		}
	};
	
	function convertEnable(value,pid){
		var _this = $(value);
		if(_this.html() == "1"){
			var url = "../user_disable.action";
			_this.html("<a href='javascript:disable("+pid+",\""+url+"\")' >禁用</a>");
		}else{
			var url = "../user_enable.action";
			_this.html("<a id='enable' href='javascript:enable("+pid+",\""+url+"\")'>启用</a>");
		}
	};
	var projectname = getProjectName();
	function action(com,grid){
		switch(com){
			case '新增':
				openDialog("新增用户信息",'/'+projectname+'/back/User/user_addORmodify.jsp?actionFlag=1',800,500);
                break;
			case '删除':
				selected_count = $('.trSelected', grid).length; 
				if (selected_count == 0) {  
                    $.dialog.alert('请选择一条记录!');  
                    return;  
                }
				names = "";
				$('.trSelected td:nth-child(2) div', grid).each(function(i){
					if(i){
						names += ",";
					}
					names += $(this).text();
				});
				ids = '';
				$('.trSelected', grid).each(function(i){
					if(i){
						ids += ",";
					}
					ids += $(this).attr("id").substring(3);
				});
				alert(ids);
				if(confirm("确定删除字典项[" + names + "]?")){
					delItem(ids,'/'+projectname+'/back/User/user_deleteuser.action');
				}
				break;
			case '修改':
				selected_count = $('.trSelected', grid).length;
				if (selected_count == 0) {  
					$.dialog.alert('请选择一条记录!');  
                    return;  
                }
				if (selected_count > 1) {  
					$.dialog.alert('抱歉只能同时修改一条记录!'); 
                    return;  
                }  
				var id = $(".trSelected",grid).attr("id").substring(3);
				openDialog("用户信息修改",'/'+projectname+'/back/User/user_modifyforward.action?id='+id+'&actionFlag=2',800,500);
                break;
		}
	}
});