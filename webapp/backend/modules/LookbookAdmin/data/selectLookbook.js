$(function(){
	$('#lookbookGrid').flexigrid({
		url:'lookbook_showAll.action',
		searchUrl:'lookbook_doSearch.action',
		dataType:'json',
		showcheckbox: true,
		colModel:[{
			display:'Lookbook Name(EN)',
			name:'lookbookEname',
			width:250,
			align:'center'
		},{
			display:'Lookbook Name(ZH)',
			name:'lookbookCname',
			width:250,
			align:'center'
		},{
			display:'Date',
			name:'lookbookdate',
			width:150,
			align:'center'
		}],
		buttons:[{
			name:'Select Lookbook',
			bclass:'add',
			onpress:action
		}],
		searchitems:[{
			display:'Lookbook Name(EN)',
			name:'lookbookEname',
			isdefault:true
		},{
			display:'Lookbook Name(ZH)',
			name:'lookbookCname'
		}],
		usepager:true,
		title:'Lookbook List Info',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10,15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1061,
		height:376,
		sortname:'lookbookEname',
		sortorder:'desc'
	});
	var projectname = getProjectName();
	function action(com,grid){
		switch(com){
			case 'Select Lookbook':
				selected_count = $('.trSelected', grid).length; 
				if (selected_count == 0) {  
                    feedbackInfo('Please select at least one record!');  
                    return;  
                }
				if(selected_count >2){
					feedbackInfo('select two items at most!');  
                    return;
				}
				var lookbooks = $("#lookbooks",parent.document);
				$("tr:gt(0)",lookbooks).remove();
				$('.trSelected', grid).each(function(i){
					var id = $(this).attr("id").substring(3);
					var ename= $("td:nth-child(2) div",this).text();
					var cname=$("td:nth-child(3) div",this).text();
					$(lookbooks).append("<tr><td><input type='hidden' value='"+id+"' name='lookbooklist.lookbookid'/></td><td>"+ename+"</td><td colspan='2'>"+cname+"</td></tr>");
				});
				feedbackInfo("select success, please close the popup manually!");
                break;
		}
	}
});