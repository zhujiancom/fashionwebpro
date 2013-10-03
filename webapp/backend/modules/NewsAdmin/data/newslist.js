$(function(){
	$("#newsGrid").flexigrid({
		url:"news_showAll.action",
		searchUrl:'news_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'Title(EN)',
			name:"titleEn",
			width:200,
			align:'center'
		},{
			display:'Title(ZH)',
			name:"titleZh",
			width:200,
			align:'center'
		},{
			display:'PublishDate',
			name:"publishDate",
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
			display:'Title(EN)',
			name:'titleEn',
			isdefault:true
		},{
			display:'Title(ZH)',
			name:"titleCh"
		}],
		usepager:true,
		title:'News List Table',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10,15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1061,
		height:376,
		sortname:'titleEn',
		sortorder:'desc'
	});
	var projectname = getProjectName();
	function action(com,grid){
		switch(com){
			case 'Add':
				openDialog("Add New News Infomation",'/'+projectname+'/backend/modules/NewsAdmin/news_add.jsp',800,500);
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
					delItem(ids,'/'+projectname+'/backend/news_delete.action','#newsGrid');
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
				openDialog("Modify News Information",'/'+projectname+'/backend/news_modifyForward.action?id='+id,800,500);
                break;
		}
	}
});