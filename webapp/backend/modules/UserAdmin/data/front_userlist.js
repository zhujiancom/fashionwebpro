$(function(){
		$("#accountGrid").flexigrid({
			url:"account_showAllUsers.action",
			searchUrl:'sysuser_doSearch.action',
			dataType:"json",
			showcheckbox: true,
			resizable:false,
			colModel:[{
				display:'Login ID',
				name:"loginId",
				width:80,
				align:'center',
				sortable:true,
				hide:false
			},{
				display:'Chinese Name',
				name:"cname",
				width:90,
				align:'center'
			},{
				display:'English Name',
				name:"ename",
				width:90,
				align:'center'
			},{
				display:'Role',
				name:"sysRoles",
				width:90,
				align:'center'
			},{
				display:'Nick Name',
				name:"nickname",
				width:90,
				align:'center'
			},{
				display:'Birthday',
				name:"birthday",
				width:100,
				align:'center'
			},{
				display:'Nationality',
				name:"nationality",
				width:95,
				align:'center'
			},{
				display:'Online',
				name:"status",
				width:50,
				align:'center',
				process:convertStatus
			},{
				display:'Status',
				name:"isEnable",
				width:50,
				align:'center',
				process:convertEnable
			},{
				display:'Gender',
				name:"sex",
				width:50,
				align:'center'
			},{
				display:'E-mail',
				name:"email",
				width:200,
				align:'center'
			},{
				display:'Phone Number',
				name:"moblie",
				width:100,
				align:'center'
			},{
				display:'QQ Number',
				name:"qqnum",
				width:100,
				align:'center'
			},{
				display:'Self-sign',
				name:"selfsign",
				width:300,
				align:'center'
			},{
				display:'Login Time',
				name:"loginTime",
				width:100,
				align:'center'
			},{
				display:'Register Time',
				name:"registeTime",
				width:100,
				align:'center'
			},{
				display:'Login Count',
				name:"loginCount",
				width:30,
				align:'center'
			}],
			buttons:[{
				name:'Add',
				bclass:'add',
				onpress:action
			},{
				separator:true
			},{
				name:'Edit',
				bclass:'edit',
				onpress:action
			},{
				separator:true
			},{
				name:'Delete',
				bclass:'delete',
				onpress:action
			}],
			searchitems:[{
				display:'Login ID',
				name:'loginId',
				isdefault:true
			},{
				display:'Status',
				name:"isEnable"
			}],
			usepager:true,
			title:'User List Table', //后台用户列表信息
			userRp:true,
			rp:10,
			rpOptions: [2,5,10,15], //可选择设定的每页结果数
			showTableToggleBtn:true,
			width:1061,
			height:376,
			sortname:'loginId',
			sortorder:'desc'
		});
		function convertStatus(value,pid){
				//pid  :就是这列所在行的id截取row后面的数字得到的结果,tr的id格式：row123,pid的数值为123
			var _this = $(value);
			if(_this.html() == "1"){
				_this.html("<span style='color:blue'>OnLine</span>");
			}else{
				_this.html("<span style='color:red'>OffLine</span>");
			}
		};
		
		function convertEnable(value,pid){
			var _this = $(value);
			if(_this.html() == "1"){
				var url = '/'+projectname+"/backend/sysuser_disable.action";
				_this.html("<a href='javascript:disable("+pid+",\""+url+"\")' >Disable</a>");
			}else{
				var url = '/'+projectname+"/backend/sysuser_enable.action";
				_this.html("<a id='enable' href='javascript:enable("+pid+",\""+url+"\")'>Enable</a>");
			}
		};
		var projectname = getProjectName();
		function action(com,grid){
			switch(com){
				case 'Add':
					openDialog("Add New User Information",'/'+projectname+'/backend/modules/UserAdmin/back/backenduser_add.jsp',800,500);
	                break;
				case 'Delete':
					selected_count = $('.trSelected', grid).length; 
					if (selected_count == 0) {  
	                    $.dialog.alert('Please select at least one record !');  
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
					if(confirm("sure delete?")){
						delItem(ids,'/'+projectname+'/backend/sysuser_deleteUsers.action','accountGrid');
					}
					break;
				case 'Edit':
					selected_count = $('.trSelected', grid).length;
					if (selected_count == 0) {  
						$.dialog.alert('Please select one record at least !');  
	                    return;  
	                }
					if (selected_count > 1) {  
						$.dialog.alert('Sorry, Just can Edit one record at same time!'); 
	                    return;  
	                }  
					var id = $(".trSelected",grid).attr("id").substring(3);
					openDialog("Modify User Info",'/'+projectname+'/backend/sysuser_modifyForward.action?id='+id,800,500);
	                break;
				case 'Search':
					break;
			}
		}
});