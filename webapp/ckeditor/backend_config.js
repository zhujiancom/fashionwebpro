CKEDITOR.editorConfig = function( config ) {
	config.uiColor = '#9AB8F3';
	config.defaultLanguage = 'en';
	config.language='en';
	config.filebrowserBrowseUrl = '../ckfinder/ckfinder.html';
	config.filebrowserImageBrowseUrl = '../ckfinder/ckfinder.html?type=Images';
//	config.filebrowserFlashBrowseUrl = '/ckfinder/ckfinder.html?type=Flash';
	config.filebrowserUploadUrl = '../ckfinder/core/connector/java/connector.java?command=FILEUPLOAD&type=Files';
	config.filebrowserImageUploadUrl = '../ckfinder/core/connector/java/connector.java?command=FILEUPLOAD&type=Images';
//	config.filebrowserFlashUploadUrl = 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
//	config.disableReadonlyStyling = true;
//	config.fullPage = true;
//	config.image_previewText = CKEDITOR.tools.repeat( '___ ', 100 );
	config.toolbarGroups = [
        { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
        { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
        { name: 'links' },
        { name: 'insert'},
        { name: 'forms' },
        { name: 'tools' },
        { name: 'document',    groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'others' },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
        { name: 'styles' },
        { name: 'colors' }
    ];
//	config.toolbar = [
//          { name: 'document', items: [ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ] },
//          { name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
//          '/',
//          { name: 'basicstyles', items: [ 'Bold', 'Italic' ] }
//      ];
	config.magicline_color = '#0000FF';
	config.removeDialogTabs = 'flash:advanced;image:Link;image:advanced';
	config.removeButtons = 'Source';
	config.startupShowBorders = false;
	config.toolbarCanCollapse = true;
	config.width=900;
	config.height=500;
	config.removePlugins = 'elementspath';
	config.resize_enabled = false;
};