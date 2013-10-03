$(function(){
		var groupCd = $("#categorySubValue").attr("groupCd");
		$("#categorySubValue").flexigrid({
			url:"category_showItems.action",
			searchUrl:'category_doItemSearch.action',
			dataType:"json",
			showcheckbox: true,
			resizable:false,
			colModel:[{
				display:'Chinese Name',
				name:"cname",
				width:120,
				align:'center'
			},{
				display:'English Name',
				name:"ename",
				width:120,
				align:'center',
				sortable:true
			},{
				display:'Status',
				name:"isEnable",
				width:60,
				align:'center',
				process:convertEnable
			},{
				display:'Code',
				name:"categoryCd",
				width:60,
				align:'center'
			}],
			params:[{name:'groupCd',value:groupCd}],
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
				display:'English Name',
				name:'ename'
			},{
				display:'Status',
				name:"isEnable"
			}],
			usepager:true,
			title:'Category Item List Table', //字典项组列表信息
			userRp:true,
			rp:10,
			rpOptions: [2,5,10,15], //可选择设定的每页结果数
			showTableToggleBtn:true,
			width:900,
			height:350,
			sortname:'ename',
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
					openDialog("Add New Category Item Information",'/'+projectname+'/backend/modules/CategoryAdmin/category_sub_add.jsp?groupCd='+groupCd,800,300);
	                break;
				case 'Delete':
					selected_count = $('.trSelected', grid).length; 
					if (selected_count == 0) {  
	                    $.dialog.alert('Please select one record at least !');  
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
						$.ajax({
							type:'POST',
							url:'/'+projectname+'/backend/category_deleteItems.action',
							data:{
								ids:ids,
								referenceKey:groupCd
							},
							dataType:'json',
							success:function(data){
//								$.dialog.tips(data.msg);
								feedbackInfo(data.msg);
								$("#categorySubValue").flexReload();
							}
						});
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
					openDialog("Modify User Info",'/'+projectname+'/backend/category_modifyItemForward.action?id='+id,800,300);
	                break;
				case 'Search':
					break;
			}
		}
});