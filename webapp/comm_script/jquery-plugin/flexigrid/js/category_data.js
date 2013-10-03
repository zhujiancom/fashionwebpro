$(function(){
	$("#flex1").flexigrid({
		url:"../test/test_getAllByPagination.action",
		dataType:"json",
		showcheckbox: true,
		colModel:[{
			display:'ID',
			name:'id',
			width:50,
			sortable:true,
			align:'center',
			hide:true
		},{
			display:'中文名称',
			name:"cname",
			width:150,
			align:'center'
		},{
			display:'英文名称',
			name:"ename",
			width:150,
			align:'center'
		},{
			display:'状态',
			name:"is_enable",
			width:100,
			align:'center',
			process:convertStatus
		}],
		buttons:[{
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
		title:'字典类型信息',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10, 15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:500,
		height:160,
		sortname:'ID'
	});
	function convertStatus(value,pid){
			//pid  :就是这列所在行的id截取row后面的数字得到的结果,tr的id格式：row123,pid的数值为123
		var _this = $(value);
		if(_this.html() == "1"){
			_this.html("<a href='javascript:disable("+pid+")'>禁用</a>");
		}else{
			_this.html("<a href='javascript:enable("+pid+")'>启用</a>");
		}
	};
	
	var actions = "";
	function action(com,grid){
		switch(com){
			case '删除':
				selected_count = $('.trSelected', grid).length; 
				if (selected_count == 0) {  
                    alert('请选择一条记录!');  
                    return;  
                }
				names = "";
				$('.trSelected td:nth-child(3) div', grid).each(function(i){
					if(i){
						names += ",";
					}
					names += $(this).text();
				});
				ids = '';
				$('.trSelected td:nth-child(2) div', grid).each(function(i){
					if(i){
						ids += ",";
					}
					ids += $(this).text();
				});
				if(confirm("确定删除字典项[" + names + "]?")){
					delItem(ids);
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
				data = new Array();  
			    $('.trSelected td', grid).each(function(i){
				   data[i] = $(this).children('div').text();  
			    });
				
			    /*$('#savegoods input[name="id"]').val(data[0]).attr("disabled",true);
			    $('#savegoods input[name="name"]').val(data[1]);  
                $('#savegoods input[name="stand"]').val(data[2]);  
                $('#savegoods input[name="money"]').val(data[3]);  
                $('#savegoods input[name="leavings"]').val(data[4]);  
                $('#savegoods input[name="orders"]').val(data[5]);*/
                actions = "test_modify.action";
                break;
		}
	}
});