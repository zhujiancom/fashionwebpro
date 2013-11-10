$(function(){
		$("#accountGrid").flexigrid({
			url:"account_showAllAccounts.action",
			searchUrl:'account_doSearch.action',
			dataType:"json",
			showcheckbox: true,
			resizable:false,
			colModel:[{
				display:'Account ID',
				name:"id",
				width:80,
				align:'center',
				sortable:true,
				hide:false
			},{
				display:'Account Name',
				name:"accountname",
				width:90,
				align:'center'
			},{
				display:'Know Approach',
				name:"knowapproach",
				width:90,
				align:'center'
			},{
				display:'E-Mail',
				name:"email",
				width:150,
				align:'center'
			}],
			buttons:[{
				name:'Delete',
				bclass:'delete',
				onpress:action
			}],
			searchitems:[{
				display:'Account ID',
				name:'id',
				isdefault:true
			},{
				display:'Account Name',
				name:"accountname"
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
		var projectname = getProjectName();
		function action(com,grid){
			switch(com){
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
						delItem(ids,'/'+projectname+'/backend/account_deleteAccounts.action','#accountGrid');
					}
					break;
			}
		}
});