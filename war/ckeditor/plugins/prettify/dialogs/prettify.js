CKEDITOR.dialog.add(
		'prettify',  
		function(editor){    
			return {
				title : 'Code Dialog',  
				resizable : CKEDITOR.DIALOG_RESIZE_BOTH,  
				minWidth : 500,  
				minHeight : 400,  
				contents : [  
				            {  
				            	id : 'cb',  
				            	name : 'cb',  
				            	label : 'cb',  
				            	title : 'cb',  
				            	elements : [  
				            	  {  
				            		type : 'select',  
				            		label : 'language',  
				            		id : 'lang',  
				            		items :  
				            			[  
				            			 [ 'java' ],  
				            			 [ 'html' ],  
				            			 [ 'python' ],  
				            			 [ 'c' ],  
				            			 [ 'cpp' ],  
				            			 [ 'ruby'],  
				            			 ['php'],  
				            			 ['javascript']  
				            			]  
				            		},  
				            		{  
				            			type : 'textarea',  
				            			style : '',  
				            			label : 'code',  
				            			id : 'code',  
				            			rows: 30,  
				            			'default' : ''  
				            		}	  
				            	]  
				            }  
				           ],  
				 onOk: function() {  
					code=this.getValueOf('cb','code');  
					lang=this.getValueOf('cb','lang');  
					html = code;
					editor.insertHtml("<pre class='prettyprint lang-"+lang+"'>"+html+"</pre>");
					prettyPrint();
				}, 
				onLoad: function() { } };
	}
);  
					
