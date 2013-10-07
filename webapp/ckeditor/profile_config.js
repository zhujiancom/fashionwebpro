CKEDITOR.editorConfig = function( config ) {
	config.defaultLanguage = 'en';
	config.language='en';
	config.magicline_color = '#0000FF';
	config.width=680;
	config.height=500;
	config.readOnly = true;
	config.toolbarGroups = [
//		{ name: 'document',    groups: [ 'mode', 'document', 'doctools' ] },
//		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
//		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
//		{ name: 'forms' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
//		{ name: 'links' },
		{ name: 'insert' },
		'/',
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'tools' }
	];
//	config.toolbar='Basic';
	config.removePlugins = 'elementspath';
	config.resize_enabled = false;
//	config.extraPlugins = "ajax";
	config.toolbarCanCollapse = true;
	config.toolbarStartupExpanded = false;
};