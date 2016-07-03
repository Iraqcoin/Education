(function ($) {
	"use strict";
	$.avia_utilities = $.avia_utilities || {};
	$.avia_utilities.supported = {};
	$.avia_utilities.supports = (function () {
		var div = document.createElement('div'),
			vendors = ['Khtml', 'Ms', 'Moz', 'Webkit', 'O'];  // vendors   = ['Khtml', 'Ms','Moz','Webkit','O'];  exclude opera for the moment. stil to buggy
		return function (prop, vendor_overwrite) {
			if (div.style.prop !== undefined) {
				return "";
			}
			if (vendor_overwrite !== undefined) {
				vendors = vendor_overwrite;
			}
			prop = prop.replace(/^[a-z]/, function (val) {
				return val.toUpperCase();
			});

			var len = vendors.length;
			while (len--) {
				if (div.style[vendors[len] + prop] !== undefined) {
					return "-" + vendors[len].toLowerCase() + "-";
				}
			}
			return false;
		};
	}());

	/* Smartresize */
	(function ($, sr) {
		var debounce = function (func, threshold, execAsap) {
			var timeout;
			return function debounced() {
				var obj = this, args = arguments;

				function delayed() {
					if (!execAsap)
						func.apply(obj, args);
					timeout = null;
				}

				if (timeout)
					clearTimeout(timeout);
				else if (execAsap)
					func.apply(obj, args);

				timeout = setTimeout(delayed, threshold || 100);
			}
		}
		// smartresize
		jQuery.fn[sr] = function (fn) {
			return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
		};
	})(jQuery, 'smartresize');

	//Back To top
	var back_to_top = function () {
		jQuery(window).scroll(function () {
			if (jQuery(this).scrollTop() > 100) {
				jQuery('#back-to-top').css({bottom: "15px"});
			} else {
				jQuery('#back-to-top').css({bottom: "-100px"});
			}
		});
		jQuery('#back-to-top').click(function () {
			jQuery('html, body').animate({scrollTop: '0px'}, 800);
			return false;
		});
	}

	//// stick header
	$(document).ready(function () {
		var $header = $('#masthead.sticky-header.header_default');
		var $content_pusher = $('#wrapper-container .content-pusher');
		$header.imagesLoaded(function () {
			var height_sticky_header = $header.outerHeight(true);
			$content_pusher.css({"padding-top": height_sticky_header + 'px'})
			$(window).resize(function () {
				var height_sticky_header = $header.outerHeight(true);
				$content_pusher.css({"padding-top": height_sticky_header + 'px'})
			});
		});
	});
	//header overlay
	$(document).ready(function () {
		var $header = $('#masthead.sticky-header.header_overlay');
		var $content_pusher = $('#wrapper-container .top_site_main');
		$header.imagesLoaded(function () {
			var height_sticky_header = $header.outerHeight(true);
			$content_pusher.css({"padding-top": height_sticky_header + 'px'})
			$(window).resize(function () {
				var height_sticky_header = $header.outerHeight(true);
				$content_pusher.css({"padding-top": height_sticky_header + 'px'})
			});
		});
	});

	$(window).scroll(function () {
		var $header = $('#masthead.sticky-header');
		var $height_stick = $header.attr('data-height-sticky');
		if (!$height_stick) {
			$height_stick = '2';
		}
		if ($(this).scrollTop() > $height_stick) {
			$header.addClass('affix');
			$header.removeClass('affix-top');
		} else {
			$header.removeClass('affix');
			$header.addClass('affix-top');
		}
	});
	////end  stick header
	/* audio post */
	/* ****** jp-jplayer  ******/
	var post_audio = function () {
		$('.jp-jplayer').each(function () {
			var $this = $(this),
				url = $this.data('audio'),
				type = url.substr(url.lastIndexOf('.') + 1),
				player = '#' + $this.data('player'),
				audio = {};
			audio[type] = url;

			$this.jPlayer({
				ready              : function () {
					$this.jPlayer('setMedia', audio);
				},
				swfPath            : 'jplayer/',
				cssSelectorAncestor: player
			});
		});
	}

	var post_gallery = function () {
		$('article.format-gallery .flexslider').imagesLoaded(function () {
			$('.flexslider').flexslider({
				slideshow     : true,
				animation     : 'fade',
				pauseOnHover  : true,
				animationSpeed: 400,
				smoothHeight  : true,
				directionNav  : true,
				controlNav    : false
			});
		});
	}

	/* Product Grid, List Switch */
	var cookie_name = jQuery('.grid-list-switch').data('cookie');
	if (cookie_name == 'product-switch') {
		var gridClass = 'products-grid';
		var listClass = 'products-list';
	} else if (cookie_name == 'lpr_course-switch') {
		var gridClass = 'course-grid';
		var listClass = 'course-list';
	} else {
		var gridClass = 'blog-grid';
		var listClass = 'blog-list';
	}

	var listSwitcher = function () {
		var activeClass = 'switcher-active';
		jQuery('.switchToList').click(function () {
			if (!jQuery.cookie(cookie_name) || jQuery.cookie(cookie_name) == 'grid') {
				switchToList();
			}
		});
		jQuery('.switchToGrid').click(function () {
			if (!jQuery.cookie(cookie_name) || jQuery.cookie(cookie_name) == 'list') {
				switchToGrid();
			}
		});

		function switchToList() {
			jQuery('.switchToList').addClass(activeClass);
			jQuery('.switchToGrid').removeClass(activeClass);
			jQuery('.archive_switch').fadeOut(300, function () {
				jQuery(this).removeClass(gridClass).addClass(listClass).fadeIn(300);
				jQuery.cookie(cookie_name, 'list', {expires: 3, path: '/'});
			});
		}

		function switchToGrid() {
			jQuery('.switchToGrid').addClass(activeClass);
			jQuery('.switchToList').removeClass(activeClass);
			jQuery('.archive_switch').fadeOut(300, function () {
				jQuery(this).removeClass(listClass).addClass(gridClass).fadeIn(300);
				jQuery.cookie(cookie_name, 'grid', {expires: 3, path: '/'});
			});
		}
	}

	var check_view_mod = function () {
		var activeClass = 'switcher-active';
		if (jQuery.cookie(cookie_name) == 'grid') {
			jQuery('.archive_switch').removeClass(listClass).addClass(gridClass);
			jQuery('.switchToGrid').addClass(activeClass);
			jQuery('.switchToList').removeClass(activeClass);
		} else if (jQuery.cookie(cookie_name) == 'list') {
			jQuery('.archive_switch').removeClass(gridClass).addClass(listClass);
			jQuery('.switchToList').addClass(activeClass);
			jQuery('.switchToGrid').removeClass(activeClass);
		}
		else {
			jQuery('.switchToList').addClass(activeClass);
			jQuery('.switchToGrid').removeClass(activeClass);
			jQuery('.archive_switch').removeClass(gridClass).addClass(listClass);
		}
	}

	/* ****** PRODUCT QUICK VIEW  ******/
	var quick_view = function () {
		$('.quick-view').click(function (e) {
			/* add loader  */
			$(this).find("i").before('<div class="loading dark"></div>');
			$(this).find("i").css('display', 'none');
			var product_id = $(this).attr('data-prod');
			var data = {action: 'jck_quickview', product: product_id};
			$.post(ajaxurl, data, function (response) {
				$.magnificPopup.open({
					mainClass: 'my-mfp-zoom-in',
					items    : {
						src : '<div class="product-lightbox">' + response + '</div>',
						type: 'inline'
					}
				});
				$('.loading').remove();
				$(this).find("i").css('display', 'inline-block');
				setTimeout(function () {
					if ( typeof wc_add_to_cart_variation_params !== 'undefined' ) {
						$( '.product-info .variations_form' ).each( function() {
							$( this ).wc_variation_form().find('.variations select:eq(0)').change();
						});
					}
				}, 600);
			});
			e.preventDefault();
		});
	}
	quick_view();

	// menu landing courses
	var menu_landing = function () {
		var $window = jQuery(window);
		if (jQuery(".menu-scoll-landing").length) {
			var $scrollOffset = $("#landing-desc").length ? $("#landing-desc").offset().top : $("#main").offset().top ;

			$window.scroll(function () {
				if ($window.scrollTop() > $scrollOffset ) {
					$('.menu-scoll-landing').addClass('slideDown');
					$('#masthead').addClass('slideUp');
				} else {
					$('.menu-scoll-landing').removeClass('slideDown');
					$('#masthead').removeClass('slideUp');
				}
			});
			$('.tab-btns a[href*="#"]').on('click', function (event) {
				event.preventDefault();
				var t = $(this);
				$('html, body').animate({scrollTop: $(this.hash).offset().top - $('.menu-scoll-landing').height()}, 500, function () {
					$('.tab-btns li a').removeClass('active');
					t.addClass('active');
				});
			});
		}
	}

	var scrollTimer = false,
		scrollHandler = function () {
			var scrollPosition = parseInt(jQuery(window).scrollTop(), 10);
			jQuery('.tab-btns a[href*="#"]').each(function () {
				var thisHref = jQuery(this).attr('href'),
					tab = 	jQuery(thisHref);
				if (tab.length) {
					var thisTruePosition = parseInt( tab.offset().top, 10);
					if (jQuery("#wpadminbar").length) {
						var admin_height = jQuery("#wpadminbar").height();
					} else admin_height = 0;

					var thisPosition = thisTruePosition - (jQuery("#masthead").height() + admin_height);
					if (scrollPosition <= parseInt(jQuery(jQuery('.tab-btns a[href*="#"]').first().attr('href')).height(), 10)) {
						if (scrollPosition >= thisPosition) {
							jQuery('.tab-btns a[href^="#"]').removeClass('active');
							jQuery('.tab-btns a[href="' + thisHref + '"]').addClass('active');
						}
					} else {
						if (scrollPosition >= thisPosition || scrollPosition >= thisPosition) {
							jQuery('.tab-btns  a[href^="#"]').removeClass('active');
							jQuery('.tab-btns  a[href="' + thisHref + '"]').addClass('active');
						}
					}
				}
			});
		}

	window.clearTimeout(scrollTimer);
	scrollHandler();
	jQuery(window).scroll(function () {
		window.clearTimeout(scrollTimer);
		scrollTimer = window.setTimeout(function () {
			scrollHandler();
		}, 150);
	});

	$(function () {
		menu_landing();
		check_view_mod();
		listSwitcher();
		/* Back to top */
		back_to_top();
		/* Menu Sidebar */
		jQuery('.sliderbar-menu-controller').click(function (e) {
			e.stopPropagation();
			jQuery('.slider-sidebar').toggleClass('opened');
			jQuery('html,body').toggleClass('slider-bar-opened');
		});
		jQuery('#wrapper-container').click(function () {
			jQuery('.slider-sidebar').removeClass('opened');
			jQuery('html,body').removeClass('slider-bar-opened');
		});
		jQuery(document).keyup(function (e) {
			if (e.keyCode === 27) {
				jQuery('.slider-sidebar').removeClass('opened');
				jQuery('html,body').removeClass('slider-bar-opened');
			}
		});
		/* Blog */
		$(document).ready(function () {
			post_audio();
			post_gallery();
		});

		$('.parallax_effect').each(function () {
			var $bgobj = $(this); // assigning the object
			$(window).scroll(function () {
				var yPos = -($(window).scrollTop() / 4);
				var coords = '50%' + (yPos + 0) + 'px';
				$bgobj.css({backgroundPosition: coords});
			}); // window scroll Ends
		});

		/* Waypoints magic
		 ---------------------------------------------------------- */
		if (typeof jQuery.fn.waypoint !== 'undefined') {
			jQuery('.wpb_animate_when_almost_visible:not(.wpb_start_animation)').waypoint(function () {
				jQuery(this).addClass('wpb_start_animation');
			}, {offset: '85%'});
		}
		// widget courses slider
		$(document).ready(function () {
			$(".courses-slider").each(function () {
				var $this = jQuery(this);
				var $column = $this.attr("data-column");
				var $show_page_nav = $this.attr("data-show-page-nav");
				var $show_nav = $this.attr("data-show-nav");
				if ($show_page_nav == '1') {
					$show_page_nav = true;
				} else {
					$show_page_nav = false;
				}
				if ($show_nav == '1') {
					$show_nav = true;
				} else {
					$show_nav = false;
				}
				$this.owlCarousel({
					items         : $column,
					pagination    : $show_page_nav,
					autoPlay      : false,
					navigation    : $show_nav,
					navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"]
				});
			});
		});
	});
	//menu mobile toggle
	jQuery(document).ready(function ($) {
		jQuery(document).on('click', '.menu-mobile-effect', function (e) {
			e.stopPropagation();
			jQuery('.wrapper-container').toggleClass('mobile-menu-open');
		});

		jQuery(document).on('click', '.content-pusher', function () {
			jQuery('.wrapper-container.mobile-menu-open').removeClass('mobile-menu-open');
		});
	});

	// perload
	jQuery(document).ready(function ($) {
		$(window).load(function () {
			$('#preload').delay(100).fadeOut(500, function () {
				$(this).remove();
			});
		});
	});
})(jQuery);