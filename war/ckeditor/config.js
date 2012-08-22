/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.stylesSet.add( 'my_styles',
		[
			/* Block Styles */
			{ name : 'Div Example', element : 'div', styles : { 'background-color' : '#F5F5F5', 'border': '1px solid #CCC', 'padding': '0 10px 0 10px' } },
            { name : 'Dashed Dark Background', element : 'div', styles : { 'background-color' : '#F1F5F6', 'border': '1px dashed #B0BEC7', 'padding': '8px 10px', 'margin' : '5px 0' } },
	
			{ name : 'Blue Title'		, element : 'h3', styles : { 'color' : 'Blue' } },
			{ name : 'Red Title'		, element : 'h3', styles : { 'color' : 'Red' } },

			/* Inline Styles */
			{ name : 'Marker: Yellow'	, element : 'span', styles : { 'background-color' : 'Yellow' } },
			{ name : 'Marker: Green'	, element : 'span', styles : { 'background-color' : 'Lime' } },

			{ name : 'Big'				, element : 'big' },
			{ name : 'Small'			, element : 'small' },
			{ name : 'Typewriter'		, element : 'tt' },

			{ name : 'Computer Code'	, element : 'code' },
			{ name : 'Keyboard Phrase'	, element : 'kbd' },
			{ name : 'Sample Text'		, element : 'samp' },
			{ name : 'Variable'			, element : 'var' },

			{ name : 'Deleted Text'		, element : 'del' },
			{ name : 'Inserted Text'	, element : 'ins' },

			{ name : 'Cited Work'		, element : 'cite' },
			{ name : 'Inline Quotation'	, element : 'q' },

			{ name : 'Language: RTL'	, element : 'span', attributes : { 'dir' : 'rtl' } },
			{ name : 'Language: LTR'	, element : 'span', attributes : { 'dir' : 'ltr' } },

			/* Object Styles */
			{
				name : 'Image on Left',
				element : 'img',
				attributes :
				{
					'style' : 'padding: 5px; margin-right: 5px',
					'border' : '2',
					'align' : 'left'
				}
			},

			{
				name : 'Image on Right',
				element : 'img',
				attributes :
				{
					'style' : 'padding: 5px; margin-left: 5px',
					'border' : '2',
					'align' : 'right'
				}
			},

			{ name : 'Borderless Table', element : 'table', styles: { 'border-style': 'hidden', 'background-color' : '#E6E6FA' } },
			{ name : 'Square Bulleted List', element : 'ul', styles : { 'list-style-type' : 'square' } }
		]);


CKEDITOR.editorConfig = function(config) {
	config.extraPlugins = 'syntaxhighlight';
	config.toolbar_Full.push( [ 'syntaxhighlight' ]);
	config.stylesCombo_stylesSet = 'my_styles';
	config.disableNativeSpellChecker = false;
	config.scayt_autoStartup = false;
};




