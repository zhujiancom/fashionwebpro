$(function(){
	$("#designerGrid").flexigrid({
		url:"designer_showAll.action",
		searchUrl:'designer_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		checkboxName: "designerlist.designerId",
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
		searchitems:[{
			display:'English Name(EN)',
			name:'ename',
			isdefault:true
		},{
			display:'Chinese Name(ZH)',
			name:"cname"
		},{
			display:'Rank',
			name:"rank"
		},{
			display:'Gender',
			name:"gender"
		}],
		usepager:true,
		title:'Designer List Table (* select 3 designers only)',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10, 15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1000,
		height:168,
		sortname:'ename',
		sortorder:'desc'
	});
	
	//news grid
	$("#newsGrid").flexigrid({
		url:"news_showAll.action",
		searchUrl:'news_doSearch.action',
		dataType:"json",
		showcheckbox: true,
		checkboxName:"newslist.newsId",
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
		searchitems:[{
			display:'Title(EN)',
			name:'titleEn',
			isdefault:true
		},{
			display:'Title(ZH)',
			name:"titleCh"
		}],
		usepager:true,
		title:'News List Table (* select 5 News only)',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10,15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1000,
		height:168,
		sortname:'titleEn',
		sortorder:'desc'
	});
	
	//lookbook data
	$('#lookbookGrid').flexigrid({
		url:'lookbook_showAll.action',
		searchUrl:'lookbook_doSearch.action',
		dataType:'json',
		showcheckbox: true,
		checkboxName: "lookbooklist.lookbookid",
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
		}],searchitems:[{
			display:'Lookbook Name(EN)',
			name:'lookbookEname',
			isdefault:true
		},{
			display:'Lookbook Name(ZH)',
			name:'lookbookCname'
		}],
		usepager:true,
		title:'Lookbook Image List Table (* select 2 Lookbooks only)',
		userRp:true,
		rp:10,
		rpOptions: [2,5,10,15], //可选择设定的每页结果数
		showTableToggleBtn:true,
		width:1000,
		height:168,
		sortname:'lookbookEname',
		sortorder:'desc'
	});
});