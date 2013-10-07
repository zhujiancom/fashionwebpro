$(function(){
	$("#interviewGrid").flexigrid({
		url:"interview_showAll.action",
		searchUrl:'interview_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'Interview Name(EN)',
			name:"interviewEname",
			width:200,
			align:'center'
		},{
			display:'Interview Name(ZH)',
			name:"interviewCname",
			width:200,
			align:'center'
		},{
			display:'Interviewer',
			name:"interviewer",
			width:100,
			align:'center'
		},{
			display:'Interview Date',
			name:"interviewdate",
			width:100,
			align:'center'
		},{
			display:'Interview Type',
			name:"interviewtype",
			width:50,
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
			display:'Interview Name(EN)',
			name:'interviewEname',
			isdefault:true
		},{
			display:'Interview Name(ZH)',
			name:'interviewCname'
		},{
			display:'Interview Type',
			name:'interviewtype'
		}],
		usepager:true,
		title:'Interview List Table',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10, 15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1061,
		height:376,
		sortname:'interviewEname',
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
				openDialog("Add New Interview Information",'/'+projectname+'/backend/modules/InterviewAdmin/interview_add.jsp',800,500);
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
					delItem(ids,'/'+projectname+'/backend/interview_delete.action','#interviewGrid');
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
				openDialog("Modify Interview Information",'/'+projectname+'/backend/interview_modifyForward.action?id='+id,800,500);
                break;
			case 'Search':
				break;
		}
	}
});