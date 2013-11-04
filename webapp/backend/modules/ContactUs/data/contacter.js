$(function(){
	$("#ContactGrid").flexigrid({
		url:"contact_showAll.action",
		searchUrl:'contact_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'Contact Name',
			name:"contactName",
			width:150,
			align:'center'
		},{
			display:'Email',
			name:"email",
			width:300,
			align:'center'
		},{
			display:'Status',
			name:"handled",
			width:100,
			align:'center',
			process:convertStatus
		}],
		buttons:[{
			name:'Show Message',
			bclass:'edit',
			onpress:detail
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
	var projectname = getProjectName();
	function convertStatus(value,pid){
			//pid  :就是这列所在行的id截取row后面的数字得到的结果,tr的id格式：row123,pid的数值为123
		var _this = $(value);
		if(_this.html() == "false"){
			var url = "contact_handleMsg.action";
			_this.html("<a href='javascript:handleMsg(\""+url+"\","+pid+")' style='color:red'>[Untreated]</a>");
		}else{
			_this.html("<span style='color:#000000'>Complete</span>");
		}
	};
	function detail(com,grid){
		var id = $(".trSelected",grid).attr("id").substring(3);
		openDialog("Message Information",'/'+projectname+'/backend/contact_showMsg.action?id='+id,600,350);
	}
});