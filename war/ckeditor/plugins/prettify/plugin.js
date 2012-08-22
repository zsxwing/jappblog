CKEDITOR.plugins.add('prettify',  
{   
	requires: ['dialog'],     
	init:function(a) {  
		a.addCommand('prettify',new CKEDITOR.dialogCommand('prettify'));  
		a.ui.addButton('prettify',  
				{
			label:"prettify",
			command:'prettify',
			icon:this.path+'images/code.gif'}  
		);  
		CKEDITOR.dialog.add('prettify',this.path+'dialogs/prettify.js');  
 	}
});