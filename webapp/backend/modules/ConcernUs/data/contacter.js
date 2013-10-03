$(function(){
	$("#ContactGrid").flexigrid({
		url:"contact_showAll.action",
		searchUrl:'contact_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'Contact Name',
			name:"contactName",
			width:100,
			align:'center'
		},{
			display:'Email',
			name:"email",
			width:150,
			align:'center'
		},{
			display:'Tel',
			name:"Tel",
			width:10,
			align:'center'
		},{
			display:'Addr',
			name:"address",
			width:200,
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
			display:'Contact Name',
			name:'contactName',
			isdefault:true
		},{
			display:'Email',
			name:"email"
		}],
		usepager:true,
		title:'Contacter List Table',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10, 15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1061,
		height:376,
		sortname:'contactName',
		sortorder:'desc'
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
			case 'Add':
				openDialog("Add New Contacter Information",'/'+projectname+'/backend/modules/ConcernUs/add.jsp',800,500);
                break;
			case 'Delete':
				selected_count = $('.trSelected', grid).length; 
				if (selected_count == 0) {  
                    $.dialog.alert('Please select at least  one record!');  
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
					delItem(ids,'/'+projectname+'/backend/contact_delete.action','#ContactGrid');
				}
				break;
			case 'Edit':
				selected_count = $('.trSelected', grid).length;
				if (selected_count == 0) {  
					$.dialog.alert('Please select at least one record !');  
                    return;  
                }
				if (selected_count > 1) {  
					$.dialog.alert('Sorry,  Edit one record at one time!'); 
                    return;  
                }  
				var id = $(".trSelected",grid).attr("id").substring(3);
				openDialog("Modify Contact Information",'/'+projectname+'/backend/contact_modifyForward.action?id='+id,800,500);
                break;
		}
	}
});