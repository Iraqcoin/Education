"use strict";
jQuery('.search-link').click(function (e) {
	e.stopPropagation();
	jQuery('.search-layout-02').fadeOut('400').show();
	jQuery('.search-layout-02 #s').focus();
	jQuery('.width-navigation-left,.width-logo, nav li a, nav li span,.menu-right .widget,.search-link').css({'opacity': 0});
	jQuery('.menu-right .widget_courses-searching,.widget-search-close').css({'opacity': 1});

});
jQuery('.widget-search-close').click(function () {
	jQuery('.search-layout-02').hide();
	jQuery('.width-navigation-left,.width-logo, nav li a, nav li span,.menu-right .widget,.search-link').css({'opacity': 1});
 });

jQuery(document).ready(function () {
	jQuery('.courses-search-input').on('keyup', function (event) {
		clearTimeout(jQuery.data(this, 'timer'));
		if (event.which == 13) {
			event.preventDefault();
			jQuery(this).stop();
		} else if (event.which == 38) {
 			if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
				var selected = jQuery(".ob-selected");
				if (jQuery(".courses-list-search li").length > 1) {
					jQuery(".courses-list-search li").removeClass("ob-selected");
 					// if there is no element before the selected one, we select the last one
					if (selected.prev().length == 0) {
						selected.siblings().last().addClass("ob-selected");
					} else { // otherwise we just select the next one
						selected.prev().addClass("ob-selected");
					}
				}
			}
		} else if (event.which == 40) {
			if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
				var selected = jQuery(".ob-selected");
				if (jQuery(".courses-list-search li").length > 1) {
					jQuery(".courses-list-search li").removeClass("ob-selected");

					// if there is no element before the selected one, we select the last one
					if (selected.next().length == 0) {
						selected.siblings().first().addClass("ob-selected");
					} else { // otherwise we just select the next one
						selected.next().addClass("ob-selected");
					}
				}
			}
		} else if (event.which == 27) {
			jQuery('.search-layout-02').hide();
			jQuery('.width-navigation-left,.width-logo, nav li a, nav li span,.menu-right .widget,.search-link').css({'opacity': 1});
			jQuery('.courses-list-search').html('');
			jQuery(this).val('');
			jQuery(this).stop();
		} else if (event.which == 8) {
			jQuery('.courses-list-search').html('');
		} else {
			jQuery(this).data('timer', setTimeout(livesearch, 700));
		}
	});
	jQuery('.courses-search-input').on('keypress', function (event) {
		if (event.keyCode == 13) {
			var selected = jQuery(".ob-selected");
			if (selected.length > 0) {
				var ob_href = selected.find('a').first().attr('href');
				window.location.href = ob_href;
			}
			event.preventDefault();
		}
		if (event.keyCode == 27) {
			jQuery('.search-layout-02').hide();
			jQuery('.width-navigation-left,.width-logo, nav li a, nav li span,.menu-right .widget,.search-link').css({'opacity': 1});
		}
		if (event.keyCode == 38) {
			var selected = jQuery(".ob-selected");
			// if there is no element before the selected one, we select the last one
			if (jQuery(".courses-list-search li").length > 1) {
				jQuery(".courses-list-search li").removeClass("ob-selected");
				if (selected.prev().length == 0) {
					selected.siblings().last().addClass("ob-selected");
				} else { // otherwise we just select the next one
					selected.prev().addClass("ob-selected");
				}
			}
		}
		if (event.keyCode == 40) {
			var selected = jQuery(".ob-selected");
			if (jQuery(".courses-list-search li").length > 1) {
				jQuery(".courses-list-search li").removeClass("ob-selected");
				// if there is no element before the selected one, we select the last one
				if (selected.next().length == 0) {
					selected.siblings().first().addClass("ob-selected");
				} else { // otherwise we just select the next one
					selected.next().addClass("ob-selected");
				}
			}
		}
	});

	jQuery('.courses-list-search,.courses-search-input').click(function (event) {
		event.stopPropagation();
	});

	jQuery(document).click(function () {
		jQuery(".courses-list-search li").remove();
	});
});

function livesearch(waitKey) {
	var keyword = jQuery('.courses-search-input').val();
	if (keyword) {
		if (!waitKey && keyword.length < 3) {
			return;
		}
		jQuery('.fa-search,.fa-times').addClass('fa-spinner fa-spin');
		jQuery.ajax({
			type   : 'POST',
			data   : 'action=courses_searching&keyword=' + keyword + '&from=search',
			url    : ajaxurl,
			success: function (html) {
				var data_li = '';
				var items = jQuery.parseJSON(html);
				if (!items.error) {
					jQuery.each(items, function (index) {
						if (index == 0) {
							data_li += '<li class="ui-menu-item' + this['id'] + ' ob-selected"><a class="ui-corner-all" href="' + this['guid'] + '">' + this['title'] + '</a></li>';
						} else {
							data_li += '<li class="ui-menu-item' + this['id'] + '"><a class="ui-corner-all" href="' + this['guid'] + '">' + this['title'] + '</a></li>';
						}
					});
					jQuery('.courses-list-search').html('').append(data_li);
				}
				searchHover();
				jQuery('.fa-search,.fa-times').removeClass('fa-spinner fa-spin');
			},
			error  : function (html) {
			}
		});
	}
}

function searchHover() {
	jQuery('.courses-list-search li').on('hover', function () {
		jQuery('.courses-list-search li').removeClass('ob-selected');
		jQuery(this).addClass('ob-selected');
	});
}