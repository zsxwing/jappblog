var TagCloud = function(cats, sizes) {
	this.cats = cats;
	this.sizes = sizes;

	this.init = function() {
		var so = new SWFObject("/wp-cumulus/tagcloud.swf?t=3", "tagcloud",
				"300", "200", "7", "#336699");
		so.addParam("wmode", "transparent");
		so.addVariable("mode", "tags");
		so.addVariable("distr", "true");
		so.addVariable("tcolor", "0x0000ff");
		so.addVariable("hicolor", "0x0000ff");
		so.addVariable("tagcloud", this.getTagCloud());
		so.write("tagclound_content");
	};

	this.getTagCloud = function() {
		var max = 0;
		var min = 999999;
		for ( var i = 0; i < sizes.length; i++) {
			if (sizes[i] > max)
				max = sizes[i];
			if (sizes[i] < min)
				min = sizes[i];
		}
		// 15 - 30
		var scale = (30.0 - 10) / (max - min);
		var tagclound = "<tags>";
		var tagclound = "<tags>";
		for ( var i = 0; i < cats.length; i++) {
			var displaySize = Math.floor((sizes[i] - min) * scale + 15)
			tagclound += "<a action='tagCloud.onTagClick(" + i + ")' style='"
					+ displaySize + "'>" + cats[i] + "</a>"
		}
		tagclound += "</tags>";
		return tagclound;
	};

	this.onTagClick = function(i) {
		window.location.href = "/?catelog=" + cats[i];
	};
};