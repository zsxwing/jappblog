(function($) {
	$.fn.combobox = function(options) {
		var defaults = {};
		var options = $.extend(defaults, options);

		var input = $(this);
		input.wrap("<span id='_combobox' ></span>");

		var values = options.values;
		var select_html = "<table id='_combobox_select'>";
		for ( var i = 0; i < values.length; i++) {
			var value = values[i];
			select_html += "<tr><td>" + value + "</td></tr>";
		}
		select_html += "</table>";

		input.after(select_html);

		var container = $("#_combobox");

		var select = $("#_combobox_select");

		select.hide();

		input.css({
			"margin-bottom" : "0"
		});
		
		select.css({
			"position" : "absolute",
			"width" : input[0].offsetWidth + "px",
			"border-left" : "1px solid #A2BFF0",
			"border-right" : "1px solid #558BE3",
			"border-top" : "1px solid #A2BFF0",
			"border-bottom" : "1px solid #558BE3",
			"background-color" : "#FFFFFF",
			"left" : input.offset().left + "px",
			"margin" : "0",
			"padding" : "0",
			"z-index" : "1000"
		});

		var inputActive = false;
		var containerActive = false;

		input.focus(function() {
			inputActive = true;
			select.slideDown();
		});
		input.blur(function() {
			inputActive = false;
			if (!containerActive) {
				select.hide();
			}
		});

		container.mouseover(function() {
			containerActive = true;
		});
		container.mouseout(function() {
			containerActive = false;
			if (!inputActive) {
				select.hide();
			}
		});

		$("#_combobox_select td").each(function() {
			var td = $(this);
			td.mouseover(function() {
				td.css({
					"background-color" : "#D5E2FF"
				});
			});
			td.mouseout(function() {
				td.css({
					"background-color" : "#FFFFFF"
				});
			});
			td.click(function() {
				input.val(td.html());
				select.hide();
			});
		});
	};
	return $(this);
})(jQuery);