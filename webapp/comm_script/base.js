/**
 * 获取项目名称
 */
function getProjectName(){
	var projectpath = window.location.pathname;
	var index = projectpath.indexOf("/", 2);
	return projectpath.substring(1, index);
}

/**
 
* @file flexgrid.js
 */
/*
 * Flexigrid for jQuery - v1.1
 * 
 * Copyright (c) 2008 Paulo P. Marinas (code.google.com/p/flexigrid/) Dual
 * licensed under the MIT or GPL Version 2 licenses. http://jquery.org/license
 * 
 */
(function($) {    // 21 - 1793 line
	$.addFlex = function(t, p) {
		if (t.grid)
			return false; // return if already exist
		p = $.extend({ // apply default properties
			height : 200, // default height
			width : 'auto', // auto width
			striped : true, // apply odd even stripes
			novstripe : false,
			minwidth : 30, // min width of columns
			minheight : 80, // min height of columns
			resizable : true, // allow table resizing
			url : false, // URL if using data from AJAX
			searchUrl:false, // url for seach function when using data from AJAX
			method : 'POST', // data sending method
			dataType : 'xml', // type of data for AJAX, either xml or json
			errormsg : 'Connection Error',
			usepager : false,
			nowrap : true,
			page : 1, // current page
			total : 1, // total pages
			useRp : true, // use the results per page select box
			rp : 15, // results per page
			rpOptions : [ 10, 15, 20, 30, 50 ], // allowed per-page values
			title : false,
			idProperty : 'id',
			pagestat : 'Displaying {from} to {to} of {total} items',
			pagetext : 'Page',
			outof : 'of',
			findtext : 'Find',
			params : [], // allow optional parameters to be passed around
			procmsg : 'Processing, please wait ...',
			query : '',
			qtype : '',
			nomsg : 'No items',
			minColToggle : 1, // minimum allowed column to be hidden
			showToggleBtn : true, // show or hide column toggle popup
			hideOnSubmit : true,
			autoload : true,
			blockOpacity : 0.5,
			preProcess : false,
			addTitleToCell : false, // add a title attr to cells with truncated
									// contents
			dblClickResize : false, // auto resize column by double clicking
			onDragCol : false,
			onToggleCol : false,
			onChangeSort : false,
			onDoubleClick : false,
			onSuccess : false,
			onError : false,
			onSubmit : false
		// using a custom populate function
		}, p);
		$(t).show() // show if hidden
		.attr({
			cellPadding : 0,
			cellSpacing : 0,
			border : 0
		}) // remove padding and spacing
		.removeAttr('width'); // remove width properties
		// create grid class
		var g = {
			hset : {},
			rePosDrag : function() {
				var cdleft = 0 - this.hDiv.scrollLeft;
				if (this.hDiv.scrollLeft > 0)
					cdleft -= Math.floor(p.cgwidth / 2);
				$(g.cDrag).css({
					top : g.hDiv.offsetTop + 1
				});
				var cdpad = this.cdpad;
				$('div', g.cDrag).hide();
				$('thead tr:first th:visible', this.hDiv).each(function() {
					var n = $('thead tr:first th:visible', g.hDiv).index(this);
					var cdpos = parseInt($('div', this).width());
					if (cdleft == 0)
						cdleft -= Math.floor(p.cgwidth / 2);
					cdpos = cdpos + cdleft + cdpad;
					if (isNaN(cdpos)) {
						cdpos = 0;
					}
					// 添加多选框 [修改后代码
					// 时间：2012-08-07]------------开始----------第1处-----
					// flexigrid.js 83行
					if (p.showcheckbox) {
						$('div:eq(' + n + ')', g.cDrag).css({
							'left' : cdpos + 22 + 'px'
						}).show();
					} else {
						$('div:eq(' + n + ')', g.cDrag).css({
							'left' : cdpos + 'px'
						}).show();
					}
					// 添加多选框 [修改后代码
					// 时间：2012-08-07]------------结束----------第1处-----
					$('div:eq(' + n + ')', g.cDrag).css({
						'left' : cdpos + 'px'
					}).show();
					cdleft = cdpos;
				});
			},
			fixHeight : function(newH) {
				newH = false;
				if (!newH)
					newH = $(g.bDiv).height();
				var hdHeight = $(this.hDiv).height();
				$('div', this.cDrag).each(function() {
					$(this).height(newH + hdHeight);
				});
				var nd = parseInt($(g.nDiv).height());
				if (nd > newH)
					$(g.nDiv).height(newH).width(200);
				else
					$(g.nDiv).height('auto').width('auto');
				$(g.block).css({
					height : newH,
					marginBottom : (newH * -1)
				});
				var hrH = g.bDiv.offsetTop + newH;
				if (p.height != 'auto' && p.resizable)
					hrH = g.vDiv.offsetTop;
				$(g.rDiv).css({
					height : hrH
				});
			},
			dragStart : function(dragtype, e, obj) { // default drag function
														// start
				if (dragtype == 'colresize') {// column resize
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					var n = $('div', this.cDrag).index(obj);
					var ow = $('th:visible div:eq(' + n + ')', this.hDiv)
							.width();
					$(obj).addClass('dragging').siblings().hide();
					$(obj).prev().addClass('dragging').show();
					this.colresize = {
						startX : e.pageX,
						ol : parseInt(obj.style.left),
						ow : ow,
						n : n
					};
					$('body').css('cursor', 'col-resize');
				} else if (dragtype == 'vresize') {// table resize
					var hgo = false;
					$('body').css('cursor', 'row-resize');
					if (obj) {
						hgo = true;
						$('body').css('cursor', 'col-resize');
					}
					this.vresize = {
						h : p.height,
						sy : e.pageY,
						w : p.width,
						sx : e.pageX,
						hgo : hgo
					};
				} else if (dragtype == 'colMove') {// column header drag
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					this.hset = $(this.hDiv).offset();
					this.hset.right = this.hset.left
							+ $('table', this.hDiv).width();
					this.hset.bottom = this.hset.top
							+ $('table', this.hDiv).height();
					this.dcol = obj;
					this.dcoln = $('th', this.hDiv).index(obj);
					this.colCopy = document.createElement("div");
					this.colCopy.className = "colCopy";
					this.colCopy.innerHTML = obj.innerHTML;
					if ($.browser.msie) {
						this.colCopy.className = "colCopy ie";
					}
					$(this.colCopy).css({
						position : 'absolute',
						float : 'left',
						display : 'none',
						textAlign : obj.align
					});
					$('body').append(this.colCopy);
					$(this.cDrag).hide();
				}
				$('body').noSelect();
			},
			dragMove : function(e) {
				if (this.colresize) {// column resize
					var n = this.colresize.n;
					var diff = e.pageX - this.colresize.startX;
					var nleft = this.colresize.ol + diff;
					var nw = this.colresize.ow + diff;
					if (nw > p.minwidth) {
						$('div:eq(' + n + ')', this.cDrag).css('left', nleft);
						this.colresize.nw = nw;
					}
				} else if (this.vresize) {// table resize
					var v = this.vresize;
					var y = e.pageY;
					var diff = y - v.sy;
					if (!p.defwidth)
						p.defwidth = p.width;
					if (p.width != 'auto' && !p.nohresize && v.hgo) {
						var x = e.pageX;
						var xdiff = x - v.sx;
						var newW = v.w + xdiff;
						if (newW > p.defwidth) {
							this.gDiv.style.width = newW + 'px';
							p.width = newW;
						}
					}
					var newH = v.h + diff;
					if ((newH > p.minheight || p.height < p.minheight)
							&& !v.hgo) {
						this.bDiv.style.height = newH + 'px';
						p.height = newH;
						this.fixHeight(newH);
					}
					v = null;
				} else if (this.colCopy) {
					$(this.dcol).addClass('thMove').removeClass('thOver');
					if (e.pageX > this.hset.right || e.pageX < this.hset.left
							|| e.pageY > this.hset.bottom
							|| e.pageY < this.hset.top) {
						// this.dragEnd();
						$('body').css('cursor', 'move');
					} else {
						$('body').css('cursor', 'pointer');
					}
					$(this.colCopy).css({
						top : e.pageY + 10,
						left : e.pageX + 20,
						display : 'block'
					});
				}
			},
			dragEnd : function() {
				if (this.colresize) {
					var n = this.colresize.n;
					var nw = this.colresize.nw;
					$('th:visible div:eq(' + n + ')', this.hDiv).css('width',
							nw);
					$('tr', this.bDiv).each(function() {
						var $tdDiv = $('td:visible div:eq(' + n + ')', this);
						$tdDiv.css('width', nw);
						g.addTitleToCell($tdDiv);
					});
					this.hDiv.scrollLeft = this.bDiv.scrollLeft;
					$('div:eq(' + n + ')', this.cDrag).siblings().show();
					$('.dragging', this.cDrag).removeClass('dragging');
					this.rePosDrag();
					this.fixHeight();
					this.colresize = false;
					var name = p.colModel[n].name; // Store the widths in the
													// cookies
//					$.cookie('flexiwidths/' + name, nw);
				} else if (this.vresize) {
					this.vresize = false;
				} else if (this.colCopy) {
					$(this.colCopy).remove();
					if (this.dcolt != null) {
						if (this.dcoln > this.dcolt)
							$('th:eq(' + this.dcolt + ')', this.hDiv).before(
									this.dcol);
						else
							$('th:eq(' + this.dcolt + ')', this.hDiv).after(
									this.dcol);
						this.switchCol(this.dcoln, this.dcolt);
						$(this.cdropleft).remove();
						$(this.cdropright).remove();
						this.rePosDrag();
						if (p.onDragCol) {
							p.onDragCol(this.dcoln, this.dcolt);
						}
					}
					this.dcol = null;
					this.hset = null;
					this.dcoln = null;
					this.dcolt = null;
					this.colCopy = null;
					$('.thMove', this.hDiv).removeClass('thMove');
					$(this.cDrag).show();
				}
				$('body').css('cursor', 'default');
				$('body').noSelect(false);
			},
			toggleCol : function(cid, visible) {
				var ncol = $("th[axis='col" + cid + "']", this.hDiv)[0];
				var n = $('thead th', g.hDiv).index(ncol);
				var cb = $('input[value=' + cid + ']', g.nDiv)[0];
				if (visible == null) {
					visible = ncol.hidden;
				}
				if ($('input:checked', g.nDiv).length < p.minColToggle
						&& !visible) {
					return false;
				}
				if (visible) {
					ncol.hidden = false;
					$(ncol).show();
					cb.checked = true;
				} else {
					ncol.hidden = true;
					$(ncol).hide();
					cb.checked = false;
				}
				$('tbody tr', t).each(function() {
					if (visible) {
						$('td:eq(' + n + ')', this).show();
					} else {
						$('td:eq(' + n + ')', this).hide();
					}
				});
				this.rePosDrag();
				if (p.onToggleCol) {
					p.onToggleCol(cid, visible);
				}
				return visible;
			},
			switchCol : function(cdrag, cdrop) { // switch columns
				$('tbody tr', t).each(
						function() {
							if (cdrag > cdrop)
								$('td:eq(' + cdrop + ')', this).before(
										$('td:eq(' + cdrag + ')', this));
							else
								$('td:eq(' + cdrop + ')', this).after(
										$('td:eq(' + cdrag + ')', this));
						});
				// switch order in nDiv
				if (cdrag > cdrop) {
					$('tr:eq(' + cdrop + ')', this.nDiv).before(
							$('tr:eq(' + cdrag + ')', this.nDiv));
				} else {
					$('tr:eq(' + cdrop + ')', this.nDiv).after(
							$('tr:eq(' + cdrag + ')', this.nDiv));
				}
				if ($.browser.msie && $.browser.version < 7.0) {
					$('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;
				}
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
			},
			scroll : function() {
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				this.rePosDrag();
			},
			addData : function(data) { // parse data
				if (p.dataType == 'json') {
					data = $.extend({
						rows : [],
						page : 0,
						total : 0
					}, data);
				}
				if (p.preProcess) {
					data = p.preProcess(data);
				}
				$('.pReload', this.pDiv).removeClass('loading');
				this.loading = false;
				if (!data) {
					$('.pPageStat', this.pDiv).html(p.errormsg);
					return false;
				}
				if (p.dataType == 'xml') {
					p.total = +$('rows total', data).text();
				} else {
					p.total = data.total;
				}
				if (p.total == 0) {
					$('tr, a, td, div', t).unbind();
					$(t).empty();
					p.pages = 1;
					p.page = 1;
					this.buildpager();
					$('.pPageStat', this.pDiv).html(p.nomsg);
					return false;
				}
				p.pages = Math.ceil(p.total / p.rp);
				if (p.dataType == 'xml') {
					p.page = +$('rows page', data).text();
				} else {
					p.page = data.page;
				}
				this.buildpager();
				// build new body
				var tbody = document.createElement('tbody');
				if (p.dataType == 'json') {
					$
							.each(
									data.rows,
									function(i, row) {
										var tr = document.createElement('tr');
										if (row.name)
											tr.name = row.name;
										if (row.color) {
											$(tr).css('background', row.color);
										} else {
											if (i % 2 && p.striped)
												tr.className = 'erow';
										}
										// modify by zhujian 修改获取cell数据中的id
										if (row['cell'].id) {
											tr.id = 'row' + row['cell'].id;
										}
										// 添加多选框 [修改后代码
										// 时间：2012-08-07]------------开始----第2处-----------
										if (p.showcheckbox) {

											var cth = $('<th/>');
											var objTr = $(tr);
											var idLength = objTr.attr("id").length;
											var cthch = $('<input type="checkbox" value="'
													+ objTr.attr("id") + '"/>');
											if(p.checkboxName){
												cthch = $('<input type="checkbox" name="'+p.checkboxName+'" value="'
														+ objTr.attr("id").substring(idLength-1) + '"/>');
											}
											if(row['cell'].checked){
												cthch.attr("checked","checked");
											}

											cthch
													.addClass("noborder")
													.click(
															function() {
																if (this.checked) {
																	objTr
																			.addClass('trSelected');
																} else {
																	objTr
																			.removeClass('trSelected');
																}
															});

											cth.addClass("cth").attr({
												width : "22"
											}).append(cthch);

											$(tr).prepend(cth);

										}
										// 添加多选框 [修改后代码
										// 时间：2012-08-07]------------结束----第2处-----------
										$('thead tr:first th', g.hDiv)
												.each(
														// add cell
														function() {
															var td = document
																	.createElement('td');
															var idx = $(this)
																	.attr(
																			'axis')
																	.substr(3);
															td.align = this.align;
															// If each row is
															// the object itself
															// (no 'cell' key)
															if (typeof row.cell == 'undefined') {
																td.innerHTML = row[p.colModel[idx].name];
															} else {
																// If the json
																// elements
																// aren't named
																// (which is
																// typical), use
																// numeric order
																if (typeof row.cell[idx] != "undefined") {
																	td.innerHTML = (row.cell[idx] != null) ? row.cell[idx]
																			: '';// null-check
																					// for
																					// Opera-browser
																} else {
																	td.innerHTML = row.cell[p.colModel[idx].name];
																}
															}
															// If the content
															// has a
															// <BGCOLOR=nnnnnn>
															// option, decode
															// it.
															var offs = td.innerHTML
																	.indexOf('<BGCOLOR=');
															if (offs > 0) {
																$(td)
																		.css(
																				'background',
																				text
																						.substr(
																								offs + 7,
																								7));
															}

															$(td)
																	.attr(
																			'abbr',
																			$(
																					this)
																					.attr(
																							'abbr'));
															$(tr).append(td);
															td = null;
														});
										if ($('thead', this.gDiv).length < 1) {// handle
																				// if
																				// grid
																				// has
																				// no
																				// headers
											for (idx = 0; idx < cell.length; idx++) {
												var td = document
														.createElement('td');
												// If the json elements aren't
												// named (which is typical), use
												// numeric order
												if (typeof row.cell[idx] != "undefined") {
													td.innerHTML = (row.cell[idx] != null) ? row.cell[idx]
															: '';// null-check
																	// for
																	// Opera-browser
												} else {
													td.innerHTML = row.cell[p.colModel[idx].name];
												}
												$(tr).append(td);
												td = null;
											}
										}
										$(tbody).append(tr);
										tr = null;
									});
				} else if (p.dataType == 'xml') {
					var i = 1;
					$("rows row", data)
							.each(
									function() {
										i++;
										var tr = document.createElement('tr');
										if ($(this).attr('name'))
											tr.name = $(this).attr('name');
										if ($(this).attr('color')) {
											$(tr).css('background',
													$(this).attr('id'));
										} else {
											if (i % 2 && p.striped)
												tr.className = 'erow';
										}
										var nid = $(this).attr('id');
										if (nid) {
											tr.id = 'row' + nid;
										}
										nid = null;
										var robj = this;
										$('thead tr:first th', g.hDiv)
												.each(
														function() {
															var td = document
																	.createElement('td');
															var idx = $(this)
																	.attr(
																			'axis')
																	.substr(3);
															td.align = this.align;

															var text = $(
																	"cell:eq("
																			+ idx
																			+ ")",
																	robj)
																	.text();
															var offs = text
																	.indexOf('<BGCOLOR=');
															if (offs > 0) {
																$(td)
																		.css(
																				'background',
																				text
																						.substr(
																								offs + 7,
																								7));
															}
															td.innerHTML = text;
															$(td)
																	.attr(
																			'abbr',
																			$(
																					this)
																					.attr(
																							'abbr'));
															$(tr).append(td);
															td = null;
														});
										if ($('thead', this.gDiv).length < 1) {// handle
																				// if
																				// grid
																				// has
																				// no
																				// headers
											$('cell', this)
													.each(
															function() {
																var td = document
																		.createElement('td');
																td.innerHTML = $(
																		this)
																		.text();
																$(tr)
																		.append(
																				td);
																td = null;
															});
										}
										$(tbody).append(tr);
										tr = null;
										robj = null;
									});
				}
				$('tr', t).unbind();
				$(t).empty();
				$(t).append(tbody);
				this.addCellProp();
				this.addRowProp();
				this.rePosDrag();
				tbody = null;
				data = null;
				i = null;
				if (p.onSuccess) {
					p.onSuccess(this);
				}
				if (p.hideOnSubmit) {
					$(g.block).remove();
				}
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				if ($.browser.opera) {
					$(t).css('visibility', 'visible');
				}
			},
			changeSort : function(th) { // change sortorder
				if (this.loading) {
					return true;
				}
				$(g.nDiv).hide();
				$(g.nBtn).hide();
				if (p.sortname == $(th).attr('abbr')) {
					if (p.sortorder == 'asc') {
						p.sortorder = 'desc';
					} else {
						p.sortorder = 'asc';
					}
				}
				$(th).addClass('sorted').siblings().removeClass('sorted');
				$('.sdesc', this.hDiv).removeClass('sdesc');
				$('.sasc', this.hDiv).removeClass('sasc');
				$('div', th).addClass('s' + p.sortorder);
				p.sortname = $(th).attr('abbr');
				if (p.onChangeSort) {
					p.onChangeSort(p.sortname, p.sortorder);
				} else {
					this.populate();
				}
			},
			buildpager : function() { // rebuild pager based on new properties
				$('.pcontrol input', this.pDiv).val(p.page);
				$('.pcontrol span', this.pDiv).html(p.pages);
				var r1 = (p.page - 1) * p.rp + 1;
				var r2 = r1 + p.rp - 1;
				if (p.total < r2) {
					r2 = p.total;
				}
				var stat = p.pagestat;
				stat = stat.replace(/{from}/, r1);
				stat = stat.replace(/{to}/, r2);
				stat = stat.replace(/{total}/, p.total);
				$('.pPageStat', this.pDiv).html(stat);
			},
			populate : function() { // get latest data
				if (this.loading) {
					return true;
				}
				if (p.onSubmit) {
					var gh = p.onSubmit();
					if (!gh) {
						return false;
					}
				}
				this.loading = true;
				if (!p.url) {
					return false;
				}
				$('.pPageStat', this.pDiv).html(p.procmsg);
				$('.pReload', this.pDiv).addClass('loading');
				$(g.block).css({
					top : g.bDiv.offsetTop
				});
				if (p.hideOnSubmit) {
					$(this.gDiv).prepend(g.block);
				}
				if ($.browser.opera) {
					$(t).css('visibility', 'hidden');
				}
				if (!p.newp) {
					p.newp = 1;
				}
				if (p.page > p.pages) {
					p.page = p.pages;
				}
				var param = [ {
					name : 'page',
					value : p.newp
				}, {
					name : 'rp',
					value : p.rp
				}, {
					name : 'sortname',
					value : p.sortname
				}, {
					name : 'sortorder',
					value : p.sortorder
				}, {
					name : 'query',
					value : p.query
				}, {
					name : 'qtype',
					value : p.qtype
				} ];
				if (p.params.length) {
					for ( var pi = 0; pi < p.params.length; pi++) {
						param[param.length] = p.params[pi];
					}
				}
				$.ajax({
					type : p.method,
					url : p.url,
					data : param,
					dataType : p.dataType,
					success : function(data) {
						g.addData(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						try {
							if (p.onError)
								p.onError(XMLHttpRequest, textStatus,
										errorThrown);
						} catch (e) {
						}
					}
				});
			},
			doSearch : function() {
				p.query = $('input[name=q]', g.sDiv).val();
				p.qtype = $('select[name=qtype]', g.sDiv).val();
				p.newp = 1;
				this.searchPopulate();  // this.populate() , modify by zhujian 2013-07-07
			},
			searchPopulate:function(){
				if (this.loading) {
					return true;
				}
				if (p.onSubmit) {
					var gh = p.onSubmit();
					if (!gh) {
						return false;
					}
				}
				this.loading = true;
				if (!p.searchUrl) {
					return false;
				}
				$('.pPageStat', this.pDiv).html(p.procmsg);
				$('.pReload', this.pDiv).addClass('loading');
				$(g.block).css({
					top : g.bDiv.offsetTop
				});
				if (p.hideOnSubmit) {
					$(this.gDiv).prepend(g.block);
				}
				if ($.browser.opera) {
					$(t).css('visibility', 'hidden');
				}
				if (!p.newp) {
					p.newp = 1;
				}
				if (p.page > p.pages) {
					p.page = p.pages;
				}
				var param = [ {
					name : 'page',
					value : p.newp
				}, {
					name : 'rp',
					value : p.rp
				}, {
					name : 'sortname',
					value : p.sortname
				}, {
					name : 'sortorder',
					value : p.sortorder
				}, {
					name : 'query',
					value : p.query
				}, {
					name : 'qtype',
					value : p.qtype
				} ];
				if (p.params.length) {
					for ( var pi = 0; pi < p.params.length; pi++) {
						param[param.length] = p.params[pi];
					}
				}
				$.ajax({
					type : p.method,
					url : p.searchUrl,
					data : param,
					dataType : p.dataType,
					success : function(data) {
						g.addData(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						try {
							if (p.onError)
								p.onError(XMLHttpRequest, textStatus,
										errorThrown);
						} catch (e) {
						}
					}
				});
			},
			changePage : function(ctype) { // change page
				if (this.loading) {
					return true;
				}
				switch (ctype) {
				case 'first':
					p.newp = 1;
					break;
				case 'prev':
					if (p.page > 1) {
						p.newp = parseInt(p.page) - 1;
					}
					break;
				case 'next':
					if (p.page < p.pages) {
						p.newp = parseInt(p.page) + 1;
					}
					break;
				case 'last':
					p.newp = p.pages;
					break;
				case 'input':
					var nv = parseInt($('.pcontrol input', this.pDiv).val());
					if (isNaN(nv)) {
						nv = 1;
					}
					if (nv < 1) {
						nv = 1;
					} else if (nv > p.pages) {
						nv = p.pages;
					}
					$('.pcontrol input', this.pDiv).val(nv);
					p.newp = nv;
					break;
				}
				if (p.newp == p.page) {
					return false;
				}
				if (p.onChangePage) {
					p.onChangePage(p.newp);
				} else {
					this.populate();
				}
			},
			addCellProp : function() {
				$('tbody tr td', g.bDiv).each(function() {
					var tdDiv = document.createElement('div');
					var n = $('td', $(this).parent()).index(this);
					var pth = $('th:eq(' + n + ')', g.hDiv).get(0);
					if (pth != null) {
						if (p.sortname == $(pth).attr('abbr') && p.sortname) {
							this.className = 'sorted';
						}
						$(tdDiv).css({
							textAlign : pth.align,
							width : $('div:first', pth)[0].style.width
						});
						if (pth.hidden) {
							$(this).css('display', 'none');
						}
					}
					if (p.nowrap == false) {
						$(tdDiv).css('white-space', 'normal');
					}
					if (this.innerHTML == '') {
						this.innerHTML = '&nbsp;';
					}
					tdDiv.innerHTML = this.innerHTML;
					var prnt = $(this).parent()[0];
					var pid = false;
					if (prnt.id) {
						pid = prnt.id.substr(3);
					}
					if (pth != null) {
						if (pth.process)
							pth.process(tdDiv, pid);
					}
					$(this).empty().append(tdDiv).removeAttr('width'); // wrap
																		// content
					g.addTitleToCell(tdDiv);
				});
			},
			getCellDim : function(obj) {// get cell prop for editable event
				var ht = parseInt($(obj).height());
				var pht = parseInt($(obj).parent().height());
				var wt = parseInt(obj.style.width);
				var pwt = parseInt($(obj).parent().width());
				var top = obj.offsetParent.offsetTop;
				var left = obj.offsetParent.offsetLeft;
				var pdl = parseInt($(obj).css('paddingLeft'));
				var pdt = parseInt($(obj).css('paddingTop'));
				return {
					ht : ht,
					wt : wt,
					top : top,
					left : left,
					pdl : pdl,
					pdt : pdt,
					pht : pht,
					pwt : pwt
				};
			},
			addRowProp : function() {
				$('tbody tr', g.bDiv).each(function() {
					$(this).click(function(e) {
						var obj = (e.target || e.srcElement);
						if (obj.href || obj.type)
							return true;
						$(this).toggleClass('trSelected');
						// 添加多选框 [修改后代码
						// 时间：2012-08-07]------------开始-------第3处--------
						if (p.showcheckbox) {
							if ($(this).hasClass('trSelected')) {
								$(this).find('input')[0].checked = true;
							} else {
								$(this).find('input')[0].checked = false;
							}
						}
						// 添加多选框 [修改后代码
						// 时间：2012-08-07]------------结束-------第3处--------
						if (p.singleSelect && !g.multisel) {
							$(this).siblings().removeClass('trSelected');
							$(this).toggleClass('trSelected');
						}
					}).mousedown(function(e) {
						if (e.shiftKey) {
							$(this).toggleClass('trSelected');
							g.multisel = true;
							this.focus();
							$(g.gDiv).noSelect();
						}
						if (e.ctrlKey) {
							$(this).toggleClass('trSelected');
							g.multisel = true;
							this.focus();
						}
					}).mouseup(function() {
						if (g.multisel && !e.ctrlKey) {
							g.multisel = false;
							$(g.gDiv).noSelect(false);
						}
					}).dblclick(function() {
						if (p.onDoubleClick) {
							p.onDoubleClick(this, g, p);
						}
					}).hover(function(e) {
						if (g.multisel && e.shiftKey) {
							$(this).toggleClass('trSelected');
						}
					}, function() {
					});
					if ($.browser.msie && $.browser.version < 7.0) {
						$(this).hover(function() {
							$(this).addClass('trOver');
						}, function() {
							$(this).removeClass('trOver');
						});
					}
				});
			},

			combo_flag : true,
			combo_resetIndex : function(selObj) {
				if (this.combo_flag) {
					selObj.selectedIndex = 0;
				}
				this.combo_flag = true;
			},
			combo_doSelectAction : function(selObj) {
				eval(selObj.options[selObj.selectedIndex].value);
				selObj.selectedIndex = 0;
				this.combo_flag = false;
			},
			// Add title attribute to div if cell contents is truncated
			addTitleToCell : function(tdDiv) {
				if (p.addTitleToCell) {
					var $span = $('<span />').css('display', 'none'), $div = (tdDiv instanceof jQuery) ? tdDiv
							: $(tdDiv), div_w = $div.outerWidth(), span_w = 0;

					$('body').children(':first').before($span);
					$span.html($div.html());
					$span.css('font-size', '' + $div.css('font-size'));
					$span.css('padding-left', '' + $div.css('padding-left'));
					span_w = $span.innerWidth();
					$span.remove();

					if (span_w > div_w) {
						$div.attr('title', $div.text());
					} else {
						$div.removeAttr('title');
					}
				}
			},
			autoResizeColumn : function(obj) {
				if (!p.dblClickResize) {
					return;
				}
				var n = $('div', this.cDrag).index(obj), $th = $(
						'th:visible div:eq(' + n + ')', this.hDiv), ol = parseInt(obj.style.left), ow = $th
						.width(), nw = 0, nl = 0, $span = $('<span />');
				$('body').children(':first').before($span);
				$span.html($th.html());
				$span.css('font-size', '' + $th.css('font-size'));
				$span.css('padding-left', '' + $th.css('padding-left'));
				$span.css('padding-right', '' + $th.css('padding-right'));
				nw = $span.width();
				$('tr', this.bDiv)
						.each(
								function() {
									var $tdDiv = $('td:visible div:eq(' + n
											+ ')', this), spanW = 0;
									$span.html($tdDiv.html());
									$span.css('font-size', ''
											+ $tdDiv.css('font-size'));
									$span.css('padding-left', ''
											+ $tdDiv.css('padding-left'));
									$span.css('padding-right', ''
											+ $tdDiv.css('padding-right'));
									spanW = $span.width();
									nw = (spanW > nw) ? spanW : nw;
								});
				$span.remove();
				nw = (p.minWidth > nw) ? p.minWidth : nw;
				nl = ol + (nw - ow);
				$('div:eq(' + n + ')', this.cDrag).css('left', nl);
				this.colresize = {
					nw : nw,
					n : n
				};
				g.dragEnd();
			},
			pager : 0
		};
		if (p.colModel) { // create model if any
			thead = document.createElement('thead');
			var tr = document.createElement('tr');
			for ( var i = 0; i < p.colModel.length; i++) {
				var cm = p.colModel[i];
				var th = document.createElement('th');
				$(th).attr('axis', 'col' + i);
				if (cm) { // only use cm if its defined
					var cookie_width = 'flexiwidths/' + cm.name; // Re-Store
																	// the
																	// widths in
																	// the
																	// cookies
//					if ($.cookie(cookie_width) != undefined) {
//						cm.width = $.cookie(cookie_width);
//					}
					if (cm.display != undefined) {
						th.innerHTML = cm.display;
					}
					if (cm.name && cm.sortable) {
						$(th).attr('abbr', cm.name);
					}
					if (cm.align) {
						th.align = cm.align;
					}
					if (cm.width) {
						$(th).attr('width', cm.width);
					}
					if ($(cm).attr('hide')) {
						th.hidden = true;
					}
					if (cm.process) {
						th.process = cm.process;
					}
				} else {
					th.innerHTML = "";
					$(th).attr('width', 30);
				}
				$(tr).append(th);
			}
			$(thead).append(tr);
			$(t).prepend(thead);
		} // end if p.colmodel
		// init divs
		g.gDiv = document.createElement('div'); // create global container
		g.mDiv = document.createElement('div'); // create title container
		g.hDiv = document.createElement('div'); // create header container
		g.bDiv = document.createElement('div'); // create body container
		g.vDiv = document.createElement('div'); // create grip
		g.rDiv = document.createElement('div'); // create horizontal resizer
		g.cDrag = document.createElement('div'); // create column drag
		g.block = document.createElement('div'); // creat blocker
		g.nDiv = document.createElement('div'); // create column show/hide popup
		g.nBtn = document.createElement('div'); // create column show/hide
												// button
		g.iDiv = document.createElement('div'); // create editable layer
		g.tDiv = document.createElement('div'); // create toolbar
		g.sDiv = document.createElement('div');
		g.pDiv = document.createElement('div'); // create pager container
		if (!p.usepager) {
			g.pDiv.style.display = 'none';
		}
		g.hTable = document.createElement('table');
		g.gDiv.className = 'flexigrid';
		if (p.width != 'auto') {
			g.gDiv.style.width = p.width + 'px';
		}
		// add conditional classes
		if ($.browser.msie) {
			$(g.gDiv).addClass('ie');
		}
		if (p.novstripe) {
			$(g.gDiv).addClass('novstripe');
		}
		$(t).before(g.gDiv);
		$(g.gDiv).append(t);
		// set toolbar
		if (p.buttons) {
			g.tDiv.className = 'tDiv';
			var tDiv2 = document.createElement('div');
			tDiv2.className = 'tDiv2';
			for ( var i = 0; i < p.buttons.length; i++) {
				var btn = p.buttons[i];
				if (!btn.separator) {
					var btnDiv = document.createElement('div');
					btnDiv.className = 'fbutton';
					btnDiv.innerHTML = ("<div><span>")
							+ (btn.hidename ? "&nbsp;" : btn.name)
							+ ("</span></div>");
					if (btn.bclass)
						$('span', btnDiv).addClass(btn.bclass).css({
							paddingLeft : 20
						});
					if (btn.bimage) // if bimage defined, use its string as an
									// image url for this buttons style (RS)
						$('span', btnDiv)
								.css(
										'background',
										'url(' + btn.bimage
												+ ') no-repeat center left');
					$('span', btnDiv).css('paddingLeft', 20);

					if (btn.tooltip) // add title if exists (RS)
						$('span', btnDiv)[0].title = btn.tooltip;

					btnDiv.onpress = btn.onpress;
					btnDiv.name = btn.name;
					if (btn.id) {
						btnDiv.id = btn.id;
					}
					if (btn.onpress) {
						$(btnDiv).click(function() {
							this.onpress(this.id || this.name, g.gDiv);
						});
					}
					$(tDiv2).append(btnDiv);
					if ($.browser.msie && $.browser.version < 7.0) {
						$(btnDiv).hover(function() {
							$(this).addClass('fbOver');
						}, function() {
							$(this).removeClass('fbOver');
						});
					}
				} else {
					$(tDiv2).append("<div class='btnseparator'></div>");
				}
			}
			$(g.tDiv).append(tDiv2);
			$(g.tDiv).append("<div style='clear:both'></div>");
			$(g.gDiv).prepend(g.tDiv);
		}
		g.hDiv.className = 'hDiv';

		// Define a combo button set with custom action'ed calls when clicked.
		if (p.combobuttons && $(g.tDiv2)) {
			var btnDiv = document.createElement('div');
			btnDiv.className = 'fbutton';

			var tSelect = document.createElement('select');
			$(tSelect).change(function() {
				g.combo_doSelectAction(tSelect);
			});
			$(tSelect).click(function() {
				g.combo_resetIndex(tSelect);
			});
			tSelect.className = 'cselect';
			$(btnDiv).append(tSelect);

			for (i = 0; i < p.combobuttons.length; i++) {
				var btn = p.combobuttons[i];
				if (!btn.separator) {
					var btnOpt = document.createElement('option');
					btnOpt.innerHTML = btn.name;

					if (btn.bclass)
						$(btnOpt).addClass(btn.bclass).css({
							paddingLeft : 20
						});
					if (btn.bimage) // if bimage defined, use its string as an
									// image url for this buttons style (RS)
						$(btnOpt)
								.css(
										'background',
										'url(' + btn.bimage
												+ ') no-repeat center left');
					$(btnOpt).css('paddingLeft', 20);

					if (btn.tooltip) // add title if exists (RS)
						$(btnOpt)[0].title = btn.tooltip;

					if (btn.onpress) {
						btnOpt.value = btn.onpress;
					}
					$(tSelect).append(btnOpt);
				}
			}
			$('.tDiv2').append(btnDiv);
		}

		$(t).before(g.hDiv);
		g.hTable.cellPadding = 0;
		g.hTable.cellSpacing = 0;
		$(g.hDiv).append('<div class="hDivBox"></div>');
		$('div', g.hDiv).append(g.hTable);
		var thead = $("thead:first", t).get(0);
		if (thead)
			$(g.hTable).append(thead);
		thead = null;
		if (!p.colmodel)
			var ci = 0;
		$('thead tr:first th', g.hDiv)
				.each(
						function() {
							var thdiv = document.createElement('div');
							if ($(this).attr('abbr')) {
								$(this).click(function(e) {
									if (!$(this).hasClass('thOver'))
										return false;
									var obj = (e.target || e.srcElement);
									if (obj.href || obj.type)
										return true;
									g.changeSort(this);
								});
								if ($(this).attr('abbr') == p.sortname) {
									this.className = 'sorted';
									thdiv.className = 's' + p.sortorder;
								}
							}
							if (this.hidden) {
								$(this).hide();
							}
							if (!p.colmodel) {
								$(this).attr('axis', 'col' + ci++);
							}
							$(thdiv).css({
								textAlign : this.align,
								width : this.width + 'px'
							});
							thdiv.innerHTML = this.innerHTML;
							$(this)
									.empty()
									.append(thdiv)
									.removeAttr('width')
									.mousedown(function(e) {
										g.dragStart('colMove', e, this);
									})
									.hover(
											function() {
												if (!g.colresize
														&& !$(this).hasClass(
																'thMove')
														&& !g.colCopy) {
													$(this).addClass('thOver');
												}
												if ($(this).attr('abbr') != p.sortname
														&& !g.colCopy
														&& !g.colresize
														&& $(this).attr('abbr')) {
													$('div', this).addClass(
															's' + p.sortorder);
												} else if ($(this).attr('abbr') == p.sortname
														&& !g.colCopy
														&& !g.colresize
														&& $(this).attr('abbr')) {
													var no = (p.sortorder == 'asc') ? 'desc'
															: 'asc';
													$('div', this).removeClass(
															's' + p.sortorder)
															.addClass('s' + no);
												}
												if (g.colCopy) {
													var n = $('th', g.hDiv)
															.index(this);
													if (n == g.dcoln) {
														return false;
													}
													if (n < g.dcoln) {
														$(this).append(
																g.cdropleft);
													} else {
														$(this).append(
																g.cdropright);
													}
													g.dcolt = n;
												} else if (!g.colresize) {
													var nv = $('th:visible',
															g.hDiv).index(this);
													var onl = parseInt($(
															'div:eq(' + nv
																	+ ')',
															g.cDrag)
															.css('left'));
													var nw = jQuery(g.nBtn)
															.outerWidth();
													var nl = onl
															- nw
															+ Math
																	.floor(p.cgwidth / 2);
													$(g.nDiv).hide();
													$(g.nBtn).hide();
													$(g.nBtn).css({
														'left' : nl,
														top : g.hDiv.offsetTop
													}).show();
													var ndw = parseInt($(g.nDiv)
															.width());
													$(g.nDiv).css({
														top : g.bDiv.offsetTop
													});
													if ((nl + ndw) > $(g.gDiv)
															.width()) {
														$(g.nDiv).css('left',
																onl - ndw + 1);
													} else {
														$(g.nDiv).css('left',
																nl);
													}
													if ($(this).hasClass(
															'sorted')) {
														$(g.nBtn).addClass(
																'srtd');
													} else {
														$(g.nBtn).removeClass(
																'srtd');
													}
												}
											},
											function() {
												$(this).removeClass('thOver');
												if ($(this).attr('abbr') != p.sortname) {
													$('div', this).removeClass(
															's' + p.sortorder);
												} else if ($(this).attr('abbr') == p.sortname) {
													var no = (p.sortorder == 'asc') ? 'desc'
															: 'asc';
													$('div', this).addClass(
															's' + p.sortorder)
															.removeClass(
																	's' + no);
												}
												if (g.colCopy) {
													$(g.cdropleft).remove();
													$(g.cdropright).remove();
													g.dcolt = null;
												}
											}); // wrap content
						});
		// set bDiv
		g.bDiv.className = 'bDiv';
		$(t).before(g.bDiv);
		$(g.bDiv).css({
			height : (p.height == 'auto') ? 'auto' : p.height + "px"
		}).scroll(function(e) {
			g.scroll();
		}).append(t);
		if (p.height == 'auto') {
			$('table', g.bDiv).addClass('autoht');
		}
		// add td & row properties
		g.addCellProp();
		g.addRowProp();
		// set cDrag
		var cdcol = $('thead tr:first th:first', g.hDiv).get(0);
		if (cdcol != null) {
			g.cDrag.className = 'cDrag';
			g.cdpad = 0;
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderLeftWidth'))) ? 0
					: parseInt($('div', cdcol).css('borderLeftWidth')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderRightWidth'))) ? 0
					: parseInt($('div', cdcol).css('borderRightWidth')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingLeft'))) ? 0
					: parseInt($('div', cdcol).css('paddingLeft')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingRight'))) ? 0
					: parseInt($('div', cdcol).css('paddingRight')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('borderLeftWidth'))) ? 0
					: parseInt($(cdcol).css('borderLeftWidth')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('borderRightWidth'))) ? 0
					: parseInt($(cdcol).css('borderRightWidth')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('paddingLeft'))) ? 0
					: parseInt($(cdcol).css('paddingLeft')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('paddingRight'))) ? 0
					: parseInt($(cdcol).css('paddingRight')));
			$(g.bDiv).before(g.cDrag);
			var cdheight = $(g.bDiv).height();
			var hdheight = $(g.hDiv).height();
			$(g.cDrag).css({
				top : -hdheight + 'px'
			});
			$('thead tr:first th', g.hDiv).each(function() {
				var cgDiv = document.createElement('div');
				$(g.cDrag).append(cgDiv);
				if (!p.cgwidth) {
					p.cgwidth = $(cgDiv).width();
				}
				$(cgDiv).css({
					height : cdheight + hdheight
				}).mousedown(function(e) {
					g.dragStart('colresize', e, this);
				}).dblclick(function(e) {
					g.autoResizeColumn(this);
				});
				if ($.browser.msie && $.browser.version < 7.0) {
					g.fixHeight($(g.gDiv).height());
					$(cgDiv).hover(function() {
						g.fixHeight();
						$(this).addClass('dragging');
					}, function() {
						if (!g.colresize)
							$(this).removeClass('dragging');
					});
				}
			});
		}
		// add strip
		if (p.striped) {
			$('tbody tr:odd', g.bDiv).addClass('erow');
		}
		if (p.resizable && p.height != 'auto') {
			g.vDiv.className = 'vGrip';
			$(g.vDiv).mousedown(function(e) {
				g.dragStart('vresize', e);
			}).html('<span></span>');
			$(g.bDiv).after(g.vDiv);
		}
		if (p.resizable && p.width != 'auto' && !p.nohresize) {
			g.rDiv.className = 'hGrip';
			$(g.rDiv).mousedown(function(e) {
				g.dragStart('vresize', e, true);
			}).html('<span></span>').css('height', $(g.gDiv).height());
			if ($.browser.msie && $.browser.version < 7.0) {
				$(g.rDiv).hover(function() {
					$(this).addClass('hgOver');
				}, function() {
					$(this).removeClass('hgOver');
				});
			}
			$(g.gDiv).append(g.rDiv);
		}
		// add pager
		if (p.usepager) {
			g.pDiv.className = 'pDiv';
			g.pDiv.innerHTML = '<div class="pDiv2"></div>';
			$(g.bDiv).after(g.pDiv);
			var html = ' <div class="pGroup"> <div class="pFirst pButton"><span></span></div><div class="pPrev pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">'
					+ p.pagetext
					+ ' <input type="text" size="4" value="1" /> '
					+ p.outof
					+ ' <span> 1 </span></span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton"><span></span></div><div class="pLast pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
			$('div', g.pDiv).html(html);
			$('.pReload', g.pDiv).click(function() {
				g.populate();
			});
			$('.pFirst', g.pDiv).click(function() {
				g.changePage('first');
			});
			$('.pPrev', g.pDiv).click(function() {
				g.changePage('prev');
			});
			$('.pNext', g.pDiv).click(function() {
				g.changePage('next');
			});
			$('.pLast', g.pDiv).click(function() {
				g.changePage('last');
			});
			$('.pcontrol input', g.pDiv).keydown(function(e) {
				if (e.keyCode == 13)
					g.changePage('input');
			});
			if ($.browser.msie && $.browser.version < 7)
				$('.pButton', g.pDiv).hover(function() {
					$(this).addClass('pBtnOver');
				}, function() {
					$(this).removeClass('pBtnOver');
				});
			if (p.useRp) {
				var opt = '', sel = '';
				for ( var nx = 0; nx < p.rpOptions.length; nx++) {
					if (p.rp == p.rpOptions[nx])
						sel = 'selected="selected"';
					else
						sel = '';
					opt += "<option value='" + p.rpOptions[nx] + "' " + sel
							+ " >" + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
				}
				$('.pDiv2', g.pDiv)
						.prepend(
								"<div class='pGroup'><select name='rp'>"
										+ opt
										+ "</select></div> <div class='btnseparator'></div>");
				$('select', g.pDiv).change(function() {
					if (p.onRpChange) {
						p.onRpChange(+this.value);
					} else {
						p.newp = 1;
						p.rp = +this.value;
						g.populate();
					}
				});
			}
			// add search button
			if (p.searchitems) {
				$('.pDiv2', g.pDiv)
						.prepend(
								"<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
				$('.pSearch', g.pDiv).click(
						function() {
							$(g.sDiv).slideToggle(
									'fast',
									function() {
										$('.sDiv:visible input:first', g.gDiv)
												.trigger('focus');
									});
						});
				// add search box
				g.sDiv.className = 'sDiv';
				var sitems = p.searchitems;
				var sopt = '', sel = '';
				for ( var s = 0; s < sitems.length; s++) {
					if (p.qtype == '' && sitems[s].isdefault == true) {
						p.qtype = sitems[s].name;
						sel = 'selected="selected"';
					} else {
						sel = '';
					}
					sopt += "<option value='" + sitems[s].name + "' " + sel
							+ " >" + sitems[s].display
							+ "&nbsp;&nbsp;</option>";
				}
				if (p.qtype == '') {
					p.qtype = sitems[0].name;
				}
				$(g.sDiv).append(
						"<div class='sDiv2'>" + p.findtext
								+ " <input type='text' value='" + p.query
								+ "' size='30' name='q' class='qsbox' /> "
								+ " <select name='qtype'>" + sopt
								+ "</select></div>");
				// Split into separate selectors because of bug in jQuery 1.3.2
				$('input[name=q]', g.sDiv).keydown(function(e) {
					if (e.keyCode == 13) {
						g.doSearch();
					}
				});
				$('select[name=qtype]', g.sDiv).keydown(function(e) {
					if (e.keyCode == 13) {
						g.doSearch();
					}
				});
				$('input[value=Clear]', g.sDiv).click(function() {
					$('input[name=q]', g.sDiv).val('');
					p.query = '';
					g.doSearch();
				});
				$(g.bDiv).after(g.sDiv);
			}
		}
		$(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");
		// add title
		if (p.title) {
			g.mDiv.className = 'mDiv';
			g.mDiv.innerHTML = '<div class="ftitle">' + p.title + '</div>';
			$(g.gDiv).prepend(g.mDiv);
			if (p.showTableToggleBtn) {
				$(g.mDiv)
						.append(
								'<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
				$('div.ptogtitle', g.mDiv).click(function() {
					$(g.gDiv).toggleClass('hideBody');
					$(this).toggleClass('vsble');
				});
			}
		}
		// setup cdrops
		g.cdropleft = document.createElement('span');
		g.cdropleft.className = 'cdropleft';
		g.cdropright = document.createElement('span');
		g.cdropright.className = 'cdropright';
		// add block
		g.block.className = 'gBlock';
		var gh = $(g.bDiv).height();
		var gtop = g.bDiv.offsetTop;
		$(g.block).css({
			width : g.bDiv.style.width,
			height : gh,
			background : 'white',
			position : 'relative',
			marginBottom : (gh * -1),
			zIndex : 1,
			top : gtop,
			left : '0px'
		});
		$(g.block).fadeTo(0, p.blockOpacity);
		// add column control
		if ($('th', g.hDiv).length) {
			g.nDiv.className = 'nDiv';
			g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
			$(g.nDiv).css({
				marginBottom : (gh * -1),
				display : 'none',
				top : gtop
			}).noSelect();
			var cn = 0;
			$('th div', g.hDiv).each(
					function() {
						var kcol = $("th[axis='col" + cn + "']", g.hDiv)[0];
						var chk = 'checked="checked"';
						if (kcol.style.display == 'none') {
							chk = '';
						}
						$('tbody', g.nDiv).append(
								'<tr><td class="ndcol1"><input type="checkbox" '
										+ chk + ' class="togCol" value="' + cn
										+ '" /></td><td class="ndcol2">'
										+ this.innerHTML + '</td></tr>');
						cn++;
					});
			// 添加多选框 [修改后代码 时间：2012-08-07]------------开始--------第4处-------
			if (p.showcheckbox) {
				$('tr', g.hDiv)
						.each(
								function() {
									var cth = $('<td/>');

									var cthch = $('<input type="checkbox"/>');

									cthch
											.click(function() {
												if (this.checked) {
													$('tbody tr', g.bDiv)
															.each(
																	function() {
																		$(this)
																				.addClass(
																						'trSelected')
																				.find(
																						'input')[0].checked = true;
																	});
												} else {

													$('tbody tr', g.bDiv)
															.each(
																	function() {
																		$(this)
																				.removeClass(
																						'trSelected')
																				.find(
																						'input')[0].checked = false;
																	});
												}
											});

									cth.addClass("cth").attr({
										width : "22"
									}).append(cthch);

									$(this).prepend(cth);

								});
			}
			;
			// 添加多选框 [修改后代码 时间：2012-08-07]------------结束--------第4处-------modify
			// by zhujian
			if ($.browser.msie && $.browser.version < 7.0)
				$('tr', g.nDiv).hover(function() {
					$(this).addClass('ndcolover');
				}, function() {
					$(this).removeClass('ndcolover');
				});
			$('td.ndcol2', g.nDiv).click(
					function() {
						if ($('input:checked', g.nDiv).length <= p.minColToggle
								&& $(this).prev().find('input')[0].checked)
							return false;
						return g.toggleCol($(this).prev().find('input').val());
					});
			$('input.togCol', g.nDiv).click(
					function() {
						if ($('input:checked', g.nDiv).length < p.minColToggle
								&& this.checked == false)
							return false;
						$(this).parent().next().trigger('click');
					});
			$(g.gDiv).prepend(g.nDiv);
			$(g.nBtn).addClass('nBtn').html('<div></div>').attr('title',
					'Hide/Show Columns').click(function() {
				$(g.nDiv).toggle();
				return true;
			});
			if (p.showToggleBtn) {
				$(g.gDiv).prepend(g.nBtn);
			}
		}
		// add date edit layer
		$(g.iDiv).addClass('iDiv').css({
			display : 'none'
		});
		$(g.bDiv).append(g.iDiv);
		// add flexigrid events
		$(g.bDiv).hover(function() {
			$(g.nDiv).hide();
			$(g.nBtn).hide();
		}, function() {
			if (g.multisel) {
				g.multisel = false;
			}
		});
		$(g.gDiv).hover(function() {
		}, function() {
			$(g.nDiv).hide();
			$(g.nBtn).hide();
		});
		// add document events
		$(document).mousemove(function(e) {
			g.dragMove(e);
		}).mouseup(function(e) {
			g.dragEnd();
		}).hover(function() {
		}, function() {
			g.dragEnd();
		});
		// browser adjustments
		if ($.browser.msie && $.browser.version < 7.0) {
			$('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv', g.gDiv).css({
				width : '100%'
			});
			$(g.gDiv).addClass('ie6');
			if (p.width != 'auto') {
				$(g.gDiv).addClass('ie6fullwidthbug');
			}
		}
		g.rePosDrag();
		g.fixHeight();
		// make grid functions accessible
		t.p = p;
		t.grid = g;
		// load data
		if (p.url && p.autoload) {
			g.populate();
		}
		return t;
	};
	var docloaded = false;
	$(document).ready(function() {
		docloaded = true;
	});
	$.fn.flexigrid = function(p) {
		return this.each(function() {
			if (!docloaded) {
				$(this).hide();
				var t = this;
				$(document).ready(function() {
					$.addFlex(t, p);
				});
			} else {
				$.addFlex(this, p);
			}
		});
	}; // end flexigrid
	$.fn.flexReload = function(p) { // function to reload grid
		return this.each(function() {
			if (this.grid && this.p.url)
				this.grid.populate();
		});
	}; // end flexReload
	$.fn.flexOptions = function(p) { // function to update general options
		return this.each(function() {
			if (this.grid)
				$.extend(this.p, p);
		});
	}; // end flexOptions
	$.fn.flexToggleCol = function(cid, visible) { // function to reload grid
		return this.each(function() {
			if (this.grid)
				this.grid.toggleCol(cid, visible);
		});
	}; // end flexToggleCol
	$.fn.flexAddData = function(data) { // function to add data to grid
		return this.each(function() {
			if (this.grid)
				this.grid.addData(data);
		});
	};
	$.fn.noSelect = function(p) { // no select plugin by me :-)
		var prevent = (p == null) ? true : p;
		if (prevent) {
			return this.each(function() {
				if ($.browser.msie || $.browser.safari)
					$(this).bind('selectstart', function() {
						return false;
					});
				else if ($.browser.mozilla) {
					$(this).css('MozUserSelect', 'none');
					$('body').trigger('focus');
				} else if ($.browser.opera)
					$(this).bind('mousedown', function() {
						return false;
					});
				else
					$(this).attr('unselectable', 'on');
			});
		} else {
			return this.each(function() {
				if ($.browser.msie || $.browser.safari)
					$(this).unbind('selectstart');
				else if ($.browser.mozilla)
					$(this).css('MozUserSelect', 'inherit');
				else if ($.browser.opera)
					$(this).unbind('mousedown');
				else
					$(this).removeAttr('unselectable', 'on');
			});
		}
	}; // end noSelect
	$.fn.flexSearch = function(p) { // function to search grid
		return this.each(function() {
			if (this.grid && this.p.searchitems)
				this.grid.doSearch();
		});
	}; // end flexSearch
})(jQuery);

// ///////////////////////////// The END
// ////////////////////////////////////////////////////////

/**
 * @file jquery.jqtransform.js
 */
/*******************************************************************************
 * 
 * jqTransform by mathieu vilaplana mvilaplana@dfc-e.com Designer ghyslain
 * armand garmand@dfc-e.com
 * 
 * 
 * Version 1.0 25.09.08 Version 1.1 06.08.09 Add event click on Checkbox and
 * Radio Auto calculate the size of a select element Can now, disabled the
 * elements Correct bug in ff if click on select (overflow=hidden) No need any
 * more preloading !!
 * 
 ******************************************************************************/

(function($) {
	var defaultOptions = {
		preloadImg : true
	};
	var jqTransformImgPreloaded = false;

	var jqTransformPreloadHoverFocusImg = function(strImgUrl) {
		// guillemets to remove for ie
		strImgUrl = strImgUrl.replace(/^url\((.*)\)/, '$1').replace(
				/^\"(.*)\"$/, '$1');
		var imgHover = new Image();
		imgHover.src = strImgUrl.replace(/\.([a-zA-Z]*)$/, '-hover.$1');
		var imgFocus = new Image();
		imgFocus.src = strImgUrl.replace(/\.([a-zA-Z]*)$/, '-focus.$1');
	};

	/***************************************************************************
	 * Labels
	 **************************************************************************/
	var jqTransformGetLabel = function(objfield) {
		var selfForm = $(objfield.get(0).form);
		var oLabel = objfield.next();
		if (!oLabel.is('label')) {
			oLabel = objfield.prev();
			if (oLabel.is('label')) {
				var inputname = objfield.attr('id');
				if (inputname) {
					oLabel = selfForm.find('label[for="' + inputname + '"]');
				}
			}
		}
		if (oLabel.is('label')) {
			return oLabel.css('cursor', 'pointer');
		}
		return false;
	};

	/* Hide all open selects */
	var jqTransformHideSelect = function(oTarget) {
		var ulVisible = $('.jqTransformSelectWrapper ul:visible');
		ulVisible.each(function() {
			var oSelect = $(this).parents(".jqTransformSelectWrapper:first")
					.find("select").get(0);
			// do not hide if click on the label object associated to the select
			if (!(oTarget && oSelect.oLabel && oSelect.oLabel.get(0) == oTarget
					.get(0))) {
				$(this).hide();
			}
		});
	};
	/* Check for an external click */
	var jqTransformCheckExternalClick = function(event) {
		if ($(event.target).parents('.jqTransformSelectWrapper').length === 0) {
			jqTransformHideSelect($(event.target));
		}
	};

	/* Apply document listener */
	var jqTransformAddDocumentListener = function() {
		$(document).mousedown(jqTransformCheckExternalClick);
	};

	/* Add a new handler for the reset action */
	var jqTransformReset = function(f) {
		var sel;
		$('.jqTransformSelectWrapper select', f).each(function() {
			sel = (this.selectedIndex < 0) ? 0 : this.selectedIndex;
			$('ul', $(this).parent()).each(function() {
				$('a:eq(' + sel + ')', this).click();
			});
		});
		$('a.jqTransformCheckbox, a.jqTransformRadio', f).removeClass(
				'jqTransformChecked');
		$('input:checkbox, input:radio', f).each(function() {
			if (this.checked) {
				$('a', $(this).parent()).addClass('jqTransformChecked');
			}
		});
	};
	
	/***************************************************************************
	 * Buttons
	 **************************************************************************/
	$.fn.jqTransInputButton = function() {
		return this.each(function() {
			var buttonClass;
			var _this = $(this);
			if (this.className != "") {
				buttonClass = this.className;
			} else {
				buttonClass = "jqTransformButton";
			}
			var newBtn = $(
					'<button id="' + this.id + '" name="' + this.name
							+ '" type="' + this.type + '" class="'
							+ buttonClass + '"><span><span>'
							+ $(this).attr('value') + '</span></span>').hover(
					function() {
						newBtn.addClass('jqTransformButton_hover');
					}, function() {
						newBtn.removeClass('jqTransformButton_hover');
					}).mousedown(function() {
				newBtn.addClass('jqTransformButton_click');
			}).mouseup(function() {
				newBtn.removeClass('jqTransformButton_click');
			});
			$(this).replaceWith(newBtn);
		});
	};

	/***************************************************************************
	 * Input Text Fields
	 **************************************************************************/
	$.fn.jqTransInputText = function() {
		return this.each(function() {
					var $input = $(this);
					var projectName = getProjectName();
					if ($input.hasClass('jqtranformdone')
							|| !$input.is('input')) {
						return;
					}
					$input.addClass('jqtranformdone');

					var oLabel = jqTransformGetLabel($(this));
					oLabel && oLabel.bind('click', function() {
						$input.focus();
					});

					var inputSize = $input.width();
					if ($input.attr('size')) {
						inputSize = $input.attr('size') * 10;
						$input.css('width', inputSize);
					}

					$input
							.addClass("jqTransformInput")
							.wrap(
									'<div class="jqTransformInputWrapper"><div class="jqTransformInputInner"><div></div></div></div>');
					var $wrapper = $input.parent().parent().parent();
					$wrapper.css("width", inputSize + 10);
					$input.focus(function() {
						$wrapper.addClass("jqTransformInputWrapper_focus");
						$wrapper.next("span").remove();
						$input.removeAttr("result");
					}).blur(function() {
						$wrapper.removeClass("jqTransformInputWrapper_focus");
						/**
						 * 必填项判断
						 */
						if($input.hasClass("require")){
							var value = $input.val();
							if(value == ""){
								$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width='16px' height='16px'/>&nbsp;&nbsp;this field is required!</span>");
								$input.attr("result",false);
								return;
							}
						}
						/**
						 * 正则表达式判断
						 */
						if($input.attr("rex")){
							var rexvalue = $input.attr("rex");
							var re = new RegExp(rexvalue);
							var value = $input.val();
							if(!re.test(value)){
								$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width='16px' height='16px'/>&nbsp;&nbsp; The format is not correct!</span>");
								$input.attr("result",false);
								return;
							}
						}
						/**
						 * 唯一性判断
						 */
						if($input.hasClass("unique")){
							var action = $input.attr("action");
							if(action == undefined || action == ""){
								$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width='16px' height='16px'/>&nbsp;&nbsp;There is no action attribute!</span>");
								$input.attr("result",false);
								return;
							}
							var param = $input.val();
							var key = $input.attr("name");
							$.ajax({
								url:action,
								dataType:"json",
								data:key+"="+param,
								success:function(data){
									if(data.msg == "true"){
										$wrapper.after("<span style='color:green'><img src=/"+projectName+"/comm_images/correct.png width='16px' height='16px'/>&nbsp;&nbsp;The account can be registered!</span>");
									}else if(data.msg == "false"){
										$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width='16px' height='16px'/>&nbsp;&nbsp;The same account has been used!</span>");
										$input.attr("result",false);
									}
								}
							});
						}
					}).hover(function() {
						$wrapper.addClass("jqTransformInputWrapper_hover");
					}, function() {
						$wrapper.removeClass("jqTransformInputWrapper_hover");
					});

					/* If this is safari we need to add an extra class */
					$.browser.safari && $wrapper.addClass('jqTransformSafari');
					$.browser.safari
							&& $input.css('width', $wrapper.width() + 16);
					this.wrapper = $wrapper;

				});
	};

	/***************************************************************************
	 * Check Boxes
	 **************************************************************************/
	$.fn.jqTransCheckBox = function() {
		return this.each(function() {
			if ($(this).hasClass('jqTransformHidden')) {
				return;
			}

			var $input = $(this);
			var inputSelf = this;

			// set the click on the label
			var oLabel = jqTransformGetLabel($input);
			oLabel && oLabel.click(function() {
				aLink.trigger('click');
			});

			var aLink = $('<a href="#" class="jqTransformCheckbox"></a>');
			// wrap and add the link
			$input.addClass('jqTransformHidden').wrap(
					'<span class="jqTransformCheckboxWrapper"></span>')
					.parent().prepend(aLink);
			// on change, change the class of the link
			$input.change(function() {
				this.checked && aLink.addClass('jqTransformChecked')
						|| aLink.removeClass('jqTransformChecked');
				return true;
			});
			// Click Handler, trigger the click and change event on the input
			aLink.click(function() {
				// do nothing if the original input is disabled
				if ($input.attr('disabled')) {
					return false;
				}
				// trigger the envents on the input object
				$input.trigger('click').trigger("change");
				return false;
			});

			// set the default state
			this.checked && aLink.addClass('jqTransformChecked');
		});
	};
	/***************************************************************************
	 * Radio Buttons
	 **************************************************************************/
	$.fn.jqTransRadio = function() {
		return this.each(function() {
			if ($(this).hasClass('jqTransformHidden')) {
				return;
			}

			var $input = $(this);
			var inputSelf = this;

			oLabel = jqTransformGetLabel($input);
			oLabel && oLabel.click(function() {
				aLink.trigger('click');
			});

			var aLink = $('<a href="#" class="jqTransformRadio" rel="'
					+ this.name + '"></a>');
			$input.addClass('jqTransformHidden').wrap(
					'<span class="jqTransformRadioWrapper"></span>').parent()
					.prepend(aLink);

			$input.change(function() {
				inputSelf.checked && aLink.addClass('jqTransformChecked')
						|| aLink.removeClass('jqTransformChecked');
				return true;
			});
			// Click Handler
			aLink.click(function() {
				if ($input.attr('disabled')) {
					return false;
				}
				$input.trigger('click').trigger('change');

				// uncheck all others of same name input radio elements
				$('input[name="' + $input.attr('name') + '"]', inputSelf.form)
						.not($input).each(
								function() {
									$(this).attr('type') == 'radio'
											&& $(this).trigger('change');
								});

				return false;
			});
			// set the default state
			inputSelf.checked && aLink.addClass('jqTransformChecked');
		});
	};

	/***************************************************************************
	 * TextArea
	 **************************************************************************/
	$.fn.jqTransTextarea = function() {
		return this
				.each(function() {
					var textarea = $(this);

					if (textarea.hasClass('jqtransformdone')) {
						return;
					}
					textarea.addClass('jqtransformdone');

					oLabel = jqTransformGetLabel(textarea);
					oLabel && oLabel.click(function() {
						textarea.focus();
					});

					var strTable = '<table cellspacing="0" cellpadding="0" border="0" class="jqTransformTextarea">';
					strTable += '<tr><td id="jqTransformTextarea-tl"></td><td id="jqTransformTextarea-tm"></td><td id="jqTransformTextarea-tr"></td></tr>';
					strTable += '<tr><td id="jqTransformTextarea-ml">&nbsp;</td><td id="jqTransformTextarea-mm"><div></div></td><td id="jqTransformTextarea-mr">&nbsp;</td></tr>';
					strTable += '<tr><td id="jqTransformTextarea-bl"></td><td id="jqTransformTextarea-bm"></td><td id="jqTransformTextarea-br"></td></tr>';
					strTable += '</table>';
					var oTable = $(strTable)
							.insertAfter(textarea)
							.hover(
									function() {
										!oTable
												.hasClass('jqTransformTextarea-focus')
												&& oTable
														.addClass('jqTransformTextarea-hover');
									},
									function() {
										oTable
												.removeClass('jqTransformTextarea-hover');
									});

					textarea.focus(
							function() {
								oTable.removeClass('jqTransformTextarea-hover')
										.addClass('jqTransformTextarea-focus');
							}).blur(function() {
						oTable.removeClass('jqTransformTextarea-focus');
					}).appendTo($('#jqTransformTextarea-mm div', oTable));
					this.oTable = oTable;
					if ($.browser.safari) {
						$('#jqTransformTextarea-mm', oTable).addClass(
								'jqTransformSafariTextarea').find('div').css(
								'height', textarea.height()).css('width',
								textarea.width());
					}
				});
	};

	// start of selectCallback
	var selectCallback = function($select){
		if ($select.hasClass('jqTransformHidden')) {
			return;
		}
		if ($select.attr('multiple')) {
			return;
		}

		var oLabel = jqTransformGetLabel($select);
		/* First thing we do is Wrap it */
		var $wrapper = $select.addClass('jqTransformHidden').wrap(
				'<div class="jqTransformSelectWrapper"></div>')
				.parent()
		// .css({zIndex: 10-index})
		;

		/* Now add the html for the select */
		$wrapper
				.prepend('<div><span></span><a href="#" class="jqTransformSelectOpen"></a></div><ul></ul>');
		var $ul = $('ul', $wrapper).css('width', $select.width())
				.hide();
		/* Now we add the options , initializtion option */
		$('option', $select).each(
				function(i) {
					var oLi = $('<li><a href="#" index="' + i
							+ '">' + $(this).html() + '</a></li>');
					$ul.append(oLi);
				});

		/* Add click handler to the a */
		$ul.find('a').click(
				function() {
					$('a.selected', $wrapper).removeClass(
							'selected');
					$(this).addClass('selected');
					/* Fire the onchange event */
					if ($select[0].selectedIndex != $(this).attr(
							'index')
							&& $select[0].onchange) {
						$select[0].selectedIndex = $(this).attr(
								'index');
						$select[0].onchange();
					}
					$select[0].selectedIndex = $(this)
							.attr('index');
					$('span:eq(0)', $wrapper).html($(this).html());
					$ul.hide();
					return false;
				});
		/* Set the default */
		$('a:eq(' + $select[0].selectedIndex + ')', $ul).click();
		$('span:first', $wrapper).click(
				function() {
					$("a.jqTransformSelectOpen", $wrapper).trigger(
							'click');
				});
		oLabel
				&& oLabel.click(function() {
					$("a.jqTransformSelectOpen", $wrapper).trigger(
							'click');
				});
		this.oLabel = oLabel;

		/* Apply the click handler to the Open */
		var oLinkOpen = $('a.jqTransformSelectOpen', $wrapper)
				.click(
						function() {
							// Check if box is already open to still
							// allow toggle, but close all other
							// selects
							if ($ul.css('display') == 'none') {
								jqTransformHideSelect();
							}
							if ($select.attr('disabled')) {
								return false;
							}
							$ul.slideToggle('fast', function() {
								var offSet = ($('a.selected', $ul)
										.offset().top - $ul
										.offset().top);
								$ul.animate({
									scrollTop : offSet
								});
							});
							return false;
						});

		// Set the new width
		var iSelectWidth = $select.outerWidth();
		var oSpan = $('span:first', $wrapper);
		var newWidth = (iSelectWidth > oSpan.innerWidth()) ? iSelectWidth
				+ oLinkOpen.outerWidth()
				: $wrapper.width();
		$wrapper.css('width', newWidth);
		$ul.css('width', newWidth - 2);
		oSpan.css({
			width : iSelectWidth
		});

		// Calculate the height if necessary, less elements that the
		// default height
		// show the ul to calculate the block, if ul is not
		// displayed li height value is 0
		$ul.css({
			display : 'block',
			visibility : 'hidden'
		});
		$('li:first', $ul).addClass('selected');
		var iSelectHeight = ($('li', $ul).length)
				* ($('li:first', $ul).height());// +1 else bug ff
		(iSelectHeight < $ul.height()) && $ul.css({
			height : iSelectHeight,
			'overflow' : 'hidden'
		});// hidden else bug with ff
		$ul.css({
			display : 'none',
			visibility : 'visible'
		});
	};
	// end of selectCallback
	
	/***************************************************************************
	 * Select
	 **************************************************************************/
	$.fn.jqTransSelect = function() {
		return this
				.each(function(index) {
					var $select = $(this);
					//////////////////异步获取数据
					if($select.attr("referenceKey") != undefined){
	    				var key = $select.attr("referenceKey");
	    				$.ajax({
	    					url:"category_getValue.action",
							dataType:"json",
							method:"POST",
							data:{"referenceKey":key},
							success:function(data){
								$.each(data,function(index,value){
									var _option = document.createElement("option");
									if($select.attr('lang') == 'ZH'){
										$(_option).attr("value",value.categoryCd).text(value.cname);
									}else{
										$(_option).attr("value",value.categoryCd).text(value.ename);
									}
									$select.append(_option);
								});
								selectCallback($select);
							}
	    				});
	    			}
					else{
						selectCallback($select);
					}
				  //////////////////异步获取数据
					
					
//					if ($select.hasClass('jqTransformHidden')) {
//						return;
//					}
//					if ($select.attr('multiple')) {
//						return;
//					}
//
//					var oLabel = jqTransformGetLabel($select);
//					/* First thing we do is Wrap it */
//					var $wrapper = $select.addClass('jqTransformHidden').wrap(
//							'<div class="jqTransformSelectWrapper"></div>')
//							.parent()
//					// .css({zIndex: 10-index})
//					;
//
//					/* Now add the html for the select */
//					$wrapper
//							.prepend('<div><span></span><a href="#" class="jqTransformSelectOpen"></a></div><ul></ul>');
//					var $ul = $('ul', $wrapper).css('width', $select.width())
//							.hide();
//					/* Now we add the options , initializtion option */
//					$('option', this).each(
//							function(i) {
//								var oLi = $('<li><a href="#" index="' + i
//										+ '">' + $(this).html() + '</a></li>');
//								$ul.append(oLi);
//							});
//
//					/* Add click handler to the a */
//					$ul.find('a').click(
//							function() {
//								$('a.selected', $wrapper).removeClass(
//										'selected');
//								$(this).addClass('selected');
//								/* Fire the onchange event */
//								if ($select[0].selectedIndex != $(this).attr(
//										'index')
//										&& $select[0].onchange) {
//									$select[0].selectedIndex = $(this).attr(
//											'index');
//									$select[0].onchange();
//								}
//								$select[0].selectedIndex = $(this)
//										.attr('index');
//								$('span:eq(0)', $wrapper).html($(this).html());
//								$ul.hide();
//								return false;
//							});
//					/* Set the default */
//					$('a:eq(' + this.selectedIndex + ')', $ul).click();
//					$('span:first', $wrapper).click(
//							function() {
//								$("a.jqTransformSelectOpen", $wrapper).trigger(
//										'click');
//							});
//					oLabel
//							&& oLabel.click(function() {
//								$("a.jqTransformSelectOpen", $wrapper).trigger(
//										'click');
//							});
//					this.oLabel = oLabel;
//
//					/* Apply the click handler to the Open */
//					var oLinkOpen = $('a.jqTransformSelectOpen', $wrapper)
//							.click(
//									function() {
//										// Check if box is already open to still
//										// allow toggle, but close all other
//										// selects
//										if ($ul.css('display') == 'none') {
//											jqTransformHideSelect();
//										}
//										if ($select.attr('disabled')) {
//											return false;
//										}
//
//										$ul.slideToggle('fast', function() {
//											var offSet = ($('a.selected', $ul)
//													.offset().top - $ul
//													.offset().top);
//											$ul.animate({
//												scrollTop : offSet
//											});
//										});
//										return false;
//									});
//
//					// Set the new width
//					var iSelectWidth = $select.outerWidth();
//					var oSpan = $('span:first', $wrapper);
//					var newWidth = (iSelectWidth > oSpan.innerWidth()) ? iSelectWidth
//							+ oLinkOpen.outerWidth()
//							: $wrapper.width();
//					$wrapper.css('width', newWidth);
//					$ul.css('width', newWidth - 2);
//					oSpan.css({
//						width : iSelectWidth
//					});
//
//					// Calculate the height if necessary, less elements that the
//					// default height
//					// show the ul to calculate the block, if ul is not
//					// displayed li height value is 0
//					$ul.css({
//						display : 'block',
//						visibility : 'hidden'
//					});
//					var iSelectHeight = ($('li', $ul).length)
//							* ($('li:first', $ul).height());// +1 else bug ff
//					(iSelectHeight < $ul.height()) && $ul.css({
//						height : iSelectHeight,
//						'overflow' : 'hidden'
//					});// hidden else bug with ff
//					$ul.css({
//						display : 'none',
//						visibility : 'visible'
//					});

					//end of select action
				});
	};
	$.fn.jqTransform = function(options) {
		var opt = $.extend({}, defaultOptions, options);
		/* each form */
		return this.each(function() {
			var selfForm = $(this);
			if (selfForm.hasClass('jqtransformdone')) {
				return;
			}
			selfForm.addClass('jqtransformdone');

			$('input:submit, input:reset, input[type="button"]', this)
					.jqTransInputButton();
			$('input:text, input:password', this).jqTransInputText();
			$('input:checkbox', this).jqTransCheckBox();
			$('input:radio', this).jqTransRadio();
			$('textarea', this).jqTransTextarea();

			if ($('select', this).jqTransSelect().length > 0) {
				jqTransformAddDocumentListener();
			}
			selfForm.bind('reset', function() {
				var action = function() {
					jqTransformReset(this);
				};
				window.setTimeout(action, 10);
			});
			selfForm.bind('submit',function(){
				var flag = true;
				if($("input[result]",selfForm).length > 0 ){
					flag = false;
				}
				return flag;
			});
		}); /* End Form each */

	};/* End the Plugin */

})(jQuery);

/////////////////////////////// The END
//////////////////////////////////////////////////////////

$(function() {
//	if ($("#fm").length > 0) { // 判断元素是否存在
//		$("#fm").jqTransform();
//	}
	$("#fm").each(function(){
		$(this).jqTransform();
	});
	
	var dateformat="yy-mm-dd";
	$(".datepicker").each(function(){
		$(this).datepicker({
			dateFormat : dateformat,
			changeMonth:true,
			changeYear:true
		});
	});
//	if ($(".datepicker1").length > 0) {
//		$(".datepicker1").datepicker({
//			dateFormat : "yyyy",
//			changeMonth:true,
//			changeYear:true
//		});
//	}
	$(".datepicker1").each(function(){
		$(this).datepicker({
			dateFormat : "yy",
			changeMonth:true,
			changeYear:true
		});
	});
});

function feedbackInfo(msg,title){
	title=(title == null || title == "")?"INFO":title.toUpperCase();
	$.dialog({
		title:title,
		content:msg,
		width : 390,
		height : 40,
		lock : true,
		fixed : true,
		ok:function(){
			return true;
		}
	});
}

/**
 * 打开一个对话框
 */
function openDialog(title, url, width, height, parentWin) {
	if (parentWin) {
		parentWin.$.dialog({
			title : title,
			content : 'url:' + url,
			width : width,
			height : height,
			lock : false,
			fixed : true
		});
	} else {
		$.dialog({
			title : title,
			content : 'url:' + url,
			width : width,
			height : height,
			lock : true,
			fixed : true
		});
	}
};
/**
 * 返回页面刷新提示
 */
function refreshTips(msg, menu_win, dest_win) {
	if ("" != msg) {
		menu_win.location.reload();
		dest_win.$.dialog.tips(msg, 2);
	}
}

/**
 * 选项启用函数
 * 
 * @param {Object}
 *            itemid
 */
function enable(itemid,url) {
	$.ajax({
		type : 'POST',
		url : url,
		data : {
			id : itemid
		},
		dataType : 'json',
		success : function(data) {
			$.dialog.tips(data.msg);
			$("#flex1").flexReload();
		}
	});
}
/**
 * 
 * @param itemid
 * @param url
 */
function disable(itemid,url) {
	$.ajax({
		type : 'POST',
		url : url,
		data : {
			id : itemid
		},
		dataType : 'json',
		success : function(data) {
			$.dialog.tips(data.msg);
			$("#flex1").flexReload();
		}
	});
};

function delItem(ids,url,flexTableName,winFrame){
	$.ajax({
		type:'POST',
		url:url,
		data:{
			ids:ids
		},
		dataType:'json',
		success:function(data){
//			$.dialog.tips(data.msg);
			feedbackInfo(data.msg);
			$(flexTableName).flexReload();
			if(winFrame){
				winFrame.location.reload();
			}
		}
	});
}

/**
 * 表格样式控制js
 */
$(function(){
	$(".tableClass tbody tr").each(function(i){
		var _this = $(this);
		if((i+1)%2 == 0){
			_this.addClass("eventr");
		}
	}).mouseover(function(){
		$(this).addClass("hovertr");
	}).mouseout(function(){
		$(this).removeClass("hovertr");
	});
});


(function($){
    var _sleeptimer;
    $.sleep = function( time2sleep, callback )
    {
        $.sleep._sleeptimer = time2sleep;
        $.sleep._cback = callback;
        $.sleep.timer = setInterval('$.sleep.count()', 1000);
    };
    $.extend ($.sleep, {
        current_i : 1,
        _sleeptimer : 0,
        _cback : null,
        timer : null,
        count : function()
        {
            if ( $.sleep.current_i === $.sleep._sleeptimer )
            {
                clearInterval($.sleep.timer);
                $.sleep._cback.call(this);
            }
            $.sleep.current_i++;
        }
    });
})(jQuery);
