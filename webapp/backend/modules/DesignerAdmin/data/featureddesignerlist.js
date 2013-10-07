$(function(){
	$("#DesignerGrid").flexigrid({
		url:"designer_showAll.action",
		searchUrl:'designer_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'English Name',
			name:"ename",
			width:100,
			align:'center'
		},{
			display:'Chinese Name',
			name:"cname",
			width:100,
			align:'center'
		},{
			display:'Rank',
			name:"rank",
			width:50,
			align:'center'
		},{
			display:'Gender',
			name:"gender",
			width:50,
			align:'center'
		},{
			display:'Born in',
			name:"bornyear",
			width:50,
			align:'center'
		},{
			display:'Nationality',
			name:"nationality_en",
			width:100,
			align:'center'
		},{
			display:'Living in Country',
			name:"livingcountry_en",
			width:100,
			align:'center'
		},{
			display:'Living in City',
			name:"livingcity_en",
			width:100,
			align:'center'
		}],
		buttons:[{
			name:'Select Featured Designer',
			bclass:'add',
			onpress:action
		}],
		searchitems:[{
			display:'English Name(EN)',
			name:'ename',
			isdefault:true
		},{
			display:'Chinese Name(ZH)',
			name:"ename"
		}],
		usepager:true,
		title:'Designer List Table',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10,15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1061,
		height:376,
		sortname:'ename',
		sortorder:'desc'
	});
	var projectname = getProjectName();
	function action(com,grid){
		switch(com){
			case 'Select Featured Designer':
				var featuredDesigner = $("#featuredDesigner",parent.document);
				selected_count = $('.trSelected', grid).length; 
				if (selected_count == 0) {  
                    $.dialog.alert('Please select at least  one record!');  
                    return;  
                }
				if (selected_count > 1) {  
                    feedbackInfo('Featured Designer just can select one record!');  
                    return;  
                }
				$('.trSelected', grid).each(function(i){
					var id = $(this).attr("id").substring(3);
					$('td:nth-child(1) input',featuredDesigner).val(id);
				});
				$('.trSelected td:nth-child(2) div', grid).each(function(i){
					var ename = $(this).text();
					$('td:nth-child(2)',featuredDesigner).text(ename);
				});
				$('.trSelected td:nth-child(3) div', grid).each(function(i){
					var cname = $(this).text();
					$('td:nth-child(3)',featuredDesigner).text(cname);
				});
				feedbackInfo("select success, please close the popup manually!");
                break;
		}
	}
});