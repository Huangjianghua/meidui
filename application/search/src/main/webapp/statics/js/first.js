$(function() {
	
	var a = $(".brandlist p a:gt(18):not(:last)");
	a.hide();
	$(".add_more a").click(function() {
		if (a.is(':visible')) {
			a.toggle()
		} else {
			a.toggle()
		}
	});
	var barh = $(window).height();
	x = barh - '180' + 'px';
	$('.cart_box .neirong').css("height", x);
	var intitNum = 0;
	var imgTimer = null;
	var intstart = 0;
	var $scroll_container = $("#scroll_container");
	var $scrollUl = $scroll_container.find("ul");
	var $scrollLi = $scrollUl.find("li");
	var $liSizes = $scrollLi.size();
	var $scrollNum = $("#scroll_num span");
	var $liWidth = $scrollLi.width();
	var $prevBtn = $scroll_container.find(".prevBtn");
	var $nextBtn = $scroll_container.find(".nextBtn");
	$scrollNum.mouseenter(function(event) {
		var _this = $(this).index();
		intitNum = _this;
		changeImg(_this)
	});
	function changeImg(obj) {
		$scrollLi.css('opacity', '0');
		$scrollNum.eq(obj).addClass('act').siblings().removeClass('act');
		$scrollLi.eq(obj).show().stop().animate({
			opacity: 1
		},
		600).siblings('li').hide()
	}
	$prevBtn.click(function() {
		intitNum <= 0 ? intitNum = $liSizes - 1 : intitNum--;
		changeImg(intitNum)
	});
	$nextBtn.click(function() {
		intitNum >= $liSizes - 1 ? intitNum = 0 : intitNum++;
		changeImg(intitNum)
	});
	$scroll_container.hover(function(event) {
		clearTimeout(imgTimer);
		$(".clickBtn").show()
	},
	function() {
		imgAutoMove();
		$(".clickBtn").hide()
	});
	$(".prevBtn").hover(function() {
		$(this).addClass('preH')
	},
	function() {
		$(this).removeClass('preH')
	});
	$(".nextBtn").hover(function() {
		$(this).addClass('nextH')
	},
	function() {
		$(this).removeClass('nextH')
	});
	imgAutoMove();
	function imgAutoMove() {
		clearTimeout(imgTimer);
		if (intstart == 0) {
			imgTimer = setTimeout(function() {
				intitNum >= $liSizes - 1 ? intitNum = 0 : intitNum++;
				changeImg(intitNum);
				imgAutoMove()
			},
			4);
			intstart++
		} else {
			imgTimer = setTimeout(function() {
				intitNum >= $liSizes - 1 ? intitNum = 0 : intitNum++;
				changeImg(intitNum);
				imgAutoMove()
			},
			4000)
		}
	}
	$(window).resize(function() {
		var barh = $(window).height();
		x = barh - '180' + 'px';
		$('.cart_box .neirong').css("height", x)
	});
	var label_01 = $('.shoppingcart thead tr td label');
	label_01.click(function() {
		if ($(this).find('input').attr('checked')) {
			$(this).parent().parent().parent().parent().find('tbody tr td label input').attr('checked', 'checked')
		} else {
			$(this).parent().parent().parent().parent().find('tbody tr td label input').removeAttr('checked', 'checked')
		}
	});
	$(".icon_selectall").click(function() {
		if ($(this).find('input').attr('checked')) {
			$(this).parent().parent().parent().parent().find('tr td label input').attr('checked', 'checked')
		} else {
			$(this).parent().parent().parent().parent().find('tr td label input').removeAttr('checked', 'checked')
		}
	});
	$(".member_box dl dt").click(function() {
		$(this).next(".member_box dl dd").slideToggle("slow");
		$(this).toggleClass("current");
		return false
	});
	$(".pay_banklist ul li label").each(function() {
		if ($(this).prev()[0].checked) $(this).addClass("checked")
	}).hover(function() {
		$(this).addClass("over")
	},
	function() {
		$(this).removeClass("over")
	}).click(function() {
		var contents = $(this).parent().parent();
		$(":input[type=radio] + label", contents).each(function() {
			$(this).prev()[0].checked = false;
			$(this).removeClass("checked");
			$(this).parent().removeClass("current")
		});
		$(this).prev()[0].checked = true;
		$(this).addClass("checked");
		$(this).parent().addClass("current")
	}).prev().hide();
	$(".relatecategory dl  dt i").click(function() {
		$(this).parent().next(".relatecategory dl dd").slideToggle("slow");
		$(this).parent().toggleClass("current");
		return false
	});
	jQuery('.product_detail .hd ul li').click(function() {
		TabSelect(".product_detail .hd ul li", "div.items", "current", jQuery(this))
	});
	jQuery('.product_detail .hd ul li').eq(0).trigger("click");
	jQuery('.reg_boxs .hd1 ul li').click(function() {
		TabSelect(".reg_boxs .hd1 ul li", "div.form_1", "current", jQuery(this))
	});
	jQuery('.reg_boxs .hd1 ul li').eq(0).trigger("click");
	jQuery('.pay_nav_con ul li').click(function() {
		TabSelect(".pay_nav_con ul li", "div.bank_box", "current", jQuery(this))
	});
	jQuery('.pay_nav_con ul li').eq(0).trigger("click");
	jQuery('.pjnav ul li').click(function() {
		TabSelect(".pjnav ul li", "div.plcon", "current", jQuery(this))
	});
	jQuery('.pjnav ul li').eq(0).trigger("click");
	jQuery('#duice').click(function() {
		jQuery('.wthpop').show()
	});
	jQuery('.wthpop>p>span').click(function() {
		jQuery('.wthpop').hide()
	});
	function TabSelect(tab, con, addClass, obj) {
		var jQuery_self = obj;
		var jQuery_nav = jQuery(tab);
		jQuery_nav.removeClass(addClass),
		jQuery_self.addClass(addClass);
		var jQuery_index = jQuery_nav.index(jQuery_self);
		var jQuery_con = jQuery(con);
		jQuery_con.hide(),
		jQuery_con.eq(jQuery_index).show()
	}
	$('.google_box dl dd input,.google_rzbox dl dt input').click(function() {
		$('#google_yz').toggle()
	});
	$('.tx_form dl dt input.inputbtns,.tx_box2_2 h3 a').click(function() {
		$('#tx_box').toggle()
	});
	$('.zx_nav p a').click(function() {
		$('.add_zx,.zx_con2').toggle();
		var xx = $('.zx_nav input[type^=radio]:checked').val();
		var yy = $('.zx_form input[type^=radio]');
		for (var i = 0; i <= yy.length; i++) {
			if (yy.eq(i).val() == xx) {
				yy.eq(i).attr('checked', 'checked')
			}
		}
	});
	$('.address_list ul li h4 a.icons').click(function() {
		$(this).next().toggle()
	});
	$('.address_list ul li h4  span a').click(function() {
		$(this).parent().toggle()
	});
	$('.sizepart ul li').click(function() {
		$(this).toggleClass("current");
		$(this).siblings(".sizepart ul li").removeClass("current")
	});
	$('.colorpart ul li').click(function() {
		$(this).toggleClass("current");
		$(this).siblings(".colorpart ul li").removeClass("current")
	});
	$('.cart_part a').click(function() {
		$('.cartbox').toggle();
		$(this).parent().toggleClass('current');
		var isEmpty = $(this).attr("data-cart-num");
		if (parseInt(isEmpty) == 0) {
			$(".total_cart").hide();
			$(".total_cart_empty").show()
		}
	});
	$(window).scroll(function() {
		$scrollTo = $(window).scrollTop();
		if ($scrollTo > 100) {
			$('#fixedfloor').show().addClass('fixedfloor');
			$('#index_nav3').show().addClass('index_nav3')
		} else {
			$('#fixedfloor').hide();
			$('#index_nav3').hide()
		}
	}).trigger('scroll');
	$('#fixedfloor ul li a').click(function() {
		$.dot_not_scoll = true;
		$idx = $(this).parents('#fixedfloor').find('a').index($(this));
		$target = $('.hd').find('b').eq($idx);
		$top = parseInt($target.offset().top);
		$('body,html').animate({
			scrollTop: $top
		},
		1000);
		$.dot_not_scoll = undefined
	})
});
$('#fixedfloor li').hover(function() {
	$(this).addClass('current')
},
function() {
	$(this).removeClass('current')
});
$(window).bind("scroll",
function(event) {
	var s = undefined;
	$('.hd h3 b').each(function() {
		var thisTop = parseInt($(window).scrollTop());
		var thisButtomTop = parseInt($(window).height()) + thisTop;
		var currTop = parseInt($(this).offset().top);
		if (currTop >= thisTop && currTop <= thisButtomTop && $.dot_not_scoll == undefined) {
			$idx = $('.hd h3 b').index($(this));
			if (s == undefined) {
				s = true;
				$('#fixedfloor li').removeClass('current');
				$('#fixedfloor').find('li').eq($idx).addClass('current')
			}
		}
	})
});

; (function($) {
	var ver = "2.65";
	if ($.support == undefined) {
		$.support = {
			opacity: !($.browser.msie)
		};
	}
	function log() {
		if (window.console && window.console.log) {}
	}
	$.fn.cycle = function(options, arg2) {
		var o = {
			s: this.selector,
			c: this.context
		};
		if (this.length == 0 && options != "stop") {
			if (!$.isReady && o.s) {
				log("DOM not ready, queuing slideshow");
				$(function() {
					$(o.s, o.c).cycle(options, arg2);
				});
				return this;
			}
			log("terminating; zero elements found by selector" + ($.isReady ? "": " (DOM not ready)"));
			return this;
		}
		return this.each(function() {
			options = handleArguments(this, options, arg2);
			if (options === false) {
				return;
			}
			if (this.cycleTimeout) {
				clearTimeout(this.cycleTimeout);
			}
			this.cycleTimeout = this.cyclePause = 0;
			var $cont = $(this);
			var $slides = options.slideExpr ? $(options.slideExpr, this) : $cont.children();
			var els = $slides.get();
			if (els.length < 2) {
				log("terminating; too few slides: " + els.length);
				return;
			}
			var opts = buildOptions($cont, $slides, els, options, o);
			if (opts === false) {
				return;
			}
			if (opts.timeout || opts.continuous) {
				this.cycleTimeout = setTimeout(function() {
					go(els, opts, 0, !opts.rev);
				},
				opts.continuous ? 10 : opts.timeout + (opts.delay || 0));
			}
		});
	};
	function handleArguments(cont, options, arg2) {
		if (cont.cycleStop == undefined) {
			cont.cycleStop = 0;
		}
		if (options === undefined || options === null) {
			options = {};
		}
		if (options.constructor == String) {
			switch (options) {
			case "stop":
				cont.cycleStop++;
				if (cont.cycleTimeout) {
					clearTimeout(cont.cycleTimeout);
				}
				cont.cycleTimeout = 0;
				$(cont).removeData("cycle.opts");
				return false;
			case "pause":
				cont.cyclePause = 1;
				return false;
			case "resume":
				cont.cyclePause = 0;
				if (arg2 === true) {
					options = $(cont).data("cycle.opts");
					if (!options) {
						log("options not found, can not resume");
						return false;
					}
					if (cont.cycleTimeout) {
						clearTimeout(cont.cycleTimeout);
						cont.cycleTimeout = 0;
					}
					go(options.elements, options, 1, 1);
				}
				return false;
			default:
				options = {
					fx: options
				};
			}
		} else {
			if (options.constructor == Number) {
				var num = options;
				options = $(cont).data("cycle.opts");
				if (!options) {
					log("options not found, can not advance slide");
					return false;
				}
				if (num < 0 || num >= options.elements.length) {
					log("invalid slide index: " + num);
					return false;
				}
				options.nextSlide = num;
				if (cont.cycleTimeout) {
					clearTimeout(cont.cycleTimeout);
					cont.cycleTimeout = 0;
				}
				if (typeof arg2 == "string") {
					options.oneTimeFx = arg2;
				}
				go(options.elements, options, 1, num >= options.currSlide);
				return false;
			}
		}
		return options;
	}
	function removeFilter(el, opts) {
		if (!$.support.opacity && opts.cleartype && el.style.filter) {
			try {
				el.style.removeAttribute("filter");
			} catch(smother) {}
		}
	}
	function buildOptions($cont, $slides, els, options, o) {
		var opts = $.extend({},
		$.fn.cycle.defaults, options || {},
		$.metadata ? $cont.metadata() : $.meta ? $cont.data() : {});
		if (opts.autostop) {
			opts.countdown = opts.autostopCount || els.length;
		}
		var cont = $cont[0];
		$cont.data("cycle.opts", opts);
		opts.$cont = $cont;
		opts.stopCount = cont.cycleStop;
		opts.elements = els;
		opts.before = opts.before ? [opts.before] : [];
		opts.after = opts.after ? [opts.after] : [];
		opts.after.unshift(function() {
			opts.busy = 0;
		});
		if (!$.support.opacity && opts.cleartype) {
			opts.after.push(function() {
				removeFilter(this, opts);
			});
		}
		if (opts.continuous) {
			opts.after.push(function() {
				go(els, opts, 0, !opts.rev);
			});
		}
		saveOriginalOpts(opts);
		if (!$.support.opacity && opts.cleartype && !opts.cleartypeNoBg) {
			clearTypeFix($slides);
		}
		if ($cont.css("position") == "static") {
			$cont.css("position", "relative");
		}
		if (opts.width) {
			$cont.width(opts.width);
		}
		if (opts.height && opts.height != "auto") {
			$cont.height(opts.height);
		}
		if (opts.startingSlide) {
			opts.startingSlide = parseInt(opts.startingSlide);
		}
		if (opts.random) {
			opts.randomMap = [];
			for (var i = 0; i < els.length; i++) {
				opts.randomMap.push(i);
			}
			opts.randomMap.sort(function(a, b) {
				return Math.random() - 0.5;
			});
			opts.randomIndex = 0;
			opts.startingSlide = opts.randomMap[0];
		} else {
			if (opts.startingSlide >= els.length) {
				opts.startingSlide = 0;
			}
		}
		opts.currSlide = opts.startingSlide = opts.startingSlide || 0;
		var first = opts.startingSlide;
		$slides.css({
			position: "absolute",
			top: 0,
			left: 0
		}).hide().each(function(i) {
			var z = first ? i >= first ? els.length - (i - first) : first - i: els.length - i;
			$(this).css("z-index", z);
		});
		$(els[first]).css("opacity", 1).show();
		removeFilter(els[first], opts);
		if (opts.fit && opts.width) {
			$slides.width(opts.width);
		}
		if (opts.fit && opts.height && opts.height != "auto") {
			$slides.height(opts.height);
		}
		var reshape = opts.containerResize && !$cont.innerHeight();
		if (reshape) {
			var maxw = 0,
			maxh = 0;
			for (var i = 0; i < els.length; i++) {
				var $e = $(els[i]),
				e = $e[0],
				w = $e.outerWidth(),
				h = $e.outerHeight();
				if (!w) {
					w = e.offsetWidth;
				}
				if (!h) {
					h = e.offsetHeight;
				}
				maxw = w > maxw ? w: maxw;
				maxh = h > maxh ? h: maxh;
			}
			if (maxw > 0 && maxh > 0) {
				$cont.css({
					width: maxw + "px",
					height: maxh + "px"
				});
			}
		}
		if (opts.pause) {
			$cont.hover(function() {
				this.cyclePause++;
			},
			function() {
				this.cyclePause--;
			});
		}
		if (supportMultiTransitions(opts) === false) {
			return false;
		}
		if (!opts.multiFx) {
			var init = $.fn.cycle.transitions[opts.fx];
			if ($.isFunction(init)) {
				init($cont, $slides, opts);
			} else {
				if (opts.fx != "custom" && !opts.multiFx) {
					log("unknown transition: " + opts.fx, "; slideshow terminating");
					return false;
				}
			}
		}
		var requeue = false;
		options.requeueAttempts = options.requeueAttempts || 0;
		$slides.each(function() {
			var $el = $(this);
			this.cycleH = (opts.fit && opts.height) ? opts.height: $el.height();
			this.cycleW = (opts.fit && opts.width) ? opts.width: $el.width();
			if ($el.is("img")) {
				var loadingIE = ($.browser.msie && this.cycleW == 28 && this.cycleH == 30 && !this.complete);
				var loadingOp = ($.browser.opera && this.cycleW == 42 && this.cycleH == 19 && !this.complete);
				var loadingOther = (this.cycleH == 0 && this.cycleW == 0 && !this.complete);
				if (loadingIE || loadingOp || loadingOther) {
					if (o.s && opts.requeueOnImageNotLoaded && ++options.requeueAttempts < 100) {
						log(options.requeueAttempts, " - img slide not loaded, requeuing slideshow: ", this.src, this.cycleW, this.cycleH);
						setTimeout(function() {
							$(o.s, o.c).cycle(options);
						},
						opts.requeueTimeout);
						requeue = true;
						return false;
					} else {
						log("could not determine size of image: " + this.src, this.cycleW, this.cycleH);
					}
				}
			}
			return true;
		});
		if (requeue) {
			return false;
		}
		opts.cssBefore = opts.cssBefore || {};
		opts.animIn = opts.animIn || {};
		opts.animOut = opts.animOut || {};
		$slides.not(":eq(" + first + ")").css(opts.cssBefore);
		if (opts.cssFirst) {
			$($slides[first]).css(opts.cssFirst);
		}
		if (opts.timeout) {
			opts.timeout = parseInt(opts.timeout);
			if (opts.speed.constructor == String) {
				opts.speed = $.fx.speeds[opts.speed] || parseInt(opts.speed);
			}
			if (!opts.sync) {
				opts.speed = opts.speed / 2;
			}
			while ((opts.timeout - opts.speed) < 250) {
				opts.timeout += opts.speed;
			}
		}
		if (opts.easing) {
			opts.easeIn = opts.easeOut = opts.easing;
		}
		if (!opts.speedIn) {
			opts.speedIn = opts.speed;
		}
		if (!opts.speedOut) {
			opts.speedOut = opts.speed;
		}
		opts.slideCount = els.length;
		opts.currSlide = opts.lastSlide = first;
		if (opts.random) {
			opts.nextSlide = opts.currSlide;
			if (++opts.randomIndex == els.length) {
				opts.randomIndex = 0;
			}
			opts.nextSlide = opts.randomMap[opts.randomIndex];
		} else {
			opts.nextSlide = opts.startingSlide >= (els.length - 1) ? 0 : opts.startingSlide + 1;
		}
		var e0 = $slides[first];
		if (opts.before.length) {
			opts.before[0].apply(e0, [e0, e0, opts, true]);
		}
		if (opts.after.length > 1) {
			opts.after[1].apply(e0, [e0, e0, opts, true]);
		}
		if (opts.next) {
			$(opts.next).click(function() {
				return advance(opts, opts.rev ? -1 : 1);
			});
		}
		if (opts.prev) {
			$(opts.prev).click(function() {
				return advance(opts, opts.rev ? 1 : -1);
			});
		}
		if (opts.pager) {
			buildPager(els, opts);
		}
		exposeAddSlide(opts, els);
		return opts;
	}
	function saveOriginalOpts(opts) {
		opts.original = {
			before: [],
			after: []
		};
		opts.original.cssBefore = $.extend({},
		opts.cssBefore);
		opts.original.cssAfter = $.extend({},
		opts.cssAfter);
		opts.original.animIn = $.extend({},
		opts.animIn);
		opts.original.animOut = $.extend({},
		opts.animOut);
		$.each(opts.before,
		function() {
			opts.original.before.push(this);
		});
		$.each(opts.after,
		function() {
			opts.original.after.push(this);
		});
	}
	function supportMultiTransitions(opts) {
		var txs = $.fn.cycle.transitions;
		if (opts.fx.indexOf(",") > 0) {
			opts.multiFx = true;
			opts.fxs = opts.fx.replace(/\s*/g, "").split(",");
			for (var i = 0; i < opts.fxs.length; i++) {
				var fx = opts.fxs[i];
				var tx = txs[fx];
				if (!tx || !txs.hasOwnProperty(fx) || !$.isFunction(tx)) {
					log("discarding unknown transition: ", fx);
					opts.fxs.splice(i, 1);
					i--;
				}
			}
			if (!opts.fxs.length) {
				log("No valid transitions named; slideshow terminating.");
				return false;
			}
		} else {
			if (opts.fx == "all") {
				opts.multiFx = true;
				opts.fxs = [];
				for (p in txs) {
					var tx = txs[p];
					if (txs.hasOwnProperty(p) && $.isFunction(tx)) {
						opts.fxs.push(p);
					}
				}
			}
		}
		if (opts.multiFx && opts.randomizeEffects) {
			var r1 = Math.floor(Math.random() * 20) + 30;
			for (var i = 0; i < r1; i++) {
				var r2 = Math.floor(Math.random() * opts.fxs.length);
				opts.fxs.push(opts.fxs.splice(r2, 1)[0]);
			}
			log("randomized fx sequence: ", opts.fxs);
		}
		return true;
	}
	function exposeAddSlide(opts, els) {
		opts.addSlide = function(newSlide, prepend) {
			var $s = $(newSlide),
			s = $s[0];
			if (!opts.autostopCount) {
				opts.countdown++;
			}
			els[prepend ? "unshift": "push"](s);
			if (opts.els) {
				opts.els[prepend ? "unshift": "push"](s);
			}
			opts.slideCount = els.length;
			$s.css("position", "absolute");
			$s[prepend ? "prependTo": "appendTo"](opts.$cont);
			if (prepend) {
				opts.currSlide++;
				opts.nextSlide++;
			}
			if (!$.support.opacity && opts.cleartype && !opts.cleartypeNoBg) {
				clearTypeFix($s);
			}
			if (opts.fit && opts.width) {
				$s.width(opts.width);
			}
			if (opts.fit && opts.height && opts.height != "auto") {
				$slides.height(opts.height);
			}
			s.cycleH = (opts.fit && opts.height) ? opts.height: $s.height();
			s.cycleW = (opts.fit && opts.width) ? opts.width: $s.width();
			$s.css(opts.cssBefore);
			if (opts.pager) {
				$.fn.cycle.createPagerAnchor(els.length - 1, s, $(opts.pager), els, opts);
			}
			if ($.isFunction(opts.onAddSlide)) {
				opts.onAddSlide($s);
			} else {
				$s.hide();
			}
		};
	}
	$.fn.cycle.resetState = function(opts, fx) {
		fx = fx || opts.fx;
		opts.before = [];
		opts.after = [];
		opts.cssBefore = $.extend({},
		opts.original.cssBefore);
		opts.cssAfter = $.extend({},
		opts.original.cssAfter);
		opts.animIn = $.extend({},
		opts.original.animIn);
		opts.animOut = $.extend({},
		opts.original.animOut);
		opts.fxFn = null;
		$.each(opts.original.before,
		function() {
			opts.before.push(this);
		});
		$.each(opts.original.after,
		function() {
			opts.after.push(this);
		});
		var init = $.fn.cycle.transitions[fx];
		if ($.isFunction(init)) {
			init(opts.$cont, $(opts.elements), opts);
		}
	};
	function go(els, opts, manual, fwd) {
		if (manual && opts.busy && opts.manualTrump) {
			$(els).stop(true, true);
			opts.busy = false;
		}
		if (opts.busy) {
			return;
		}
		var p = opts.$cont[0],
		curr = els[opts.currSlide],
		next = els[opts.nextSlide];
		if (p.cycleStop != opts.stopCount || p.cycleTimeout === 0 && !manual) {
			return;
		}
		if (!manual && !p.cyclePause && ((opts.autostop && (--opts.countdown <= 0)) || (opts.nowrap && !opts.random && opts.nextSlide < opts.currSlide))) {
			if (opts.end) {
				opts.end(opts);
			}
			return;
		}
		if (manual || !p.cyclePause) {
			var fx = opts.fx;
			curr.cycleH = curr.cycleH || $(curr).height();
			curr.cycleW = curr.cycleW || $(curr).width();
			next.cycleH = next.cycleH || $(next).height();
			next.cycleW = next.cycleW || $(next).width();
			if (opts.multiFx) {
				if (opts.lastFx == undefined || ++opts.lastFx >= opts.fxs.length) {
					opts.lastFx = 0;
				}
				fx = opts.fxs[opts.lastFx];
				opts.currFx = fx;
			}
			if (opts.oneTimeFx) {
				fx = opts.oneTimeFx;
				opts.oneTimeFx = null;
			}
			$.fn.cycle.resetState(opts, fx);
			if (opts.before.length) {
				$.each(opts.before,
				function(i, o) {
					if (p.cycleStop != opts.stopCount) {
						return;
					}
					o.apply(next, [curr, next, opts, fwd]);
				});
			}
			var after = function() {
				$.each(opts.after,
				function(i, o) {
					if (p.cycleStop != opts.stopCount) {
						return;
					}
					o.apply(next, [curr, next, opts, fwd]);
				});
			};
			if (opts.nextSlide != opts.currSlide) {
				opts.busy = 1;
				if (opts.fxFn) {
					opts.fxFn(curr, next, opts, after, fwd);
				} else {
					if ($.isFunction($.fn.cycle[opts.fx])) {
						$.fn.cycle[opts.fx](curr, next, opts, after);
					} else {
						$.fn.cycle.custom(curr, next, opts, after, manual && opts.fastOnEvent);
					}
				}
			}
			opts.lastSlide = opts.currSlide;
			if (opts.random) {
				opts.currSlide = opts.nextSlide;
				if (++opts.randomIndex == els.length) {
					opts.randomIndex = 0;
				}
				opts.nextSlide = opts.randomMap[opts.randomIndex];
			} else {
				var roll = (opts.nextSlide + 1) == els.length;
				opts.nextSlide = roll ? 0 : opts.nextSlide + 1;
				opts.currSlide = roll ? els.length - 1 : opts.nextSlide - 1;
			}
			if (opts.pager) {
				$.fn.cycle.updateActivePagerLink(opts.pager, opts.currSlide);
			}
		}
		var ms = 0;
		if (opts.timeout && !opts.continuous) {
			ms = getTimeout(curr, next, opts, fwd);
		} else {
			if (opts.continuous && p.cyclePause) {
				ms = 10;
			}
		}
		if (ms > 0) {
			p.cycleTimeout = setTimeout(function() {
				go(els, opts, 0, !opts.rev);
			},
			ms);
		}
	}
	$.fn.cycle.updateActivePagerLink = function(pager, currSlide) {
		$(pager).find("a").removeClass("activeSlide").filter("a:eq(" + currSlide + ")").addClass("activeSlide");
	};
	function getTimeout(curr, next, opts, fwd) {
		if (opts.timeoutFn) {
			var t = opts.timeoutFn(curr, next, opts, fwd);
			if (t !== false) {
				return t;
			}
		}
		return opts.timeout;
	}
	$.fn.cycle.next = function(opts) {
		advance(opts, opts.rev ? -1 : 1);
	};
	$.fn.cycle.prev = function(opts) {
		advance(opts, opts.rev ? 1 : -1);
	};
	function advance(opts, val) {
		var els = opts.elements;
		var p = opts.$cont[0],
		timeout = p.cycleTimeout;
		if (timeout) {
			clearTimeout(timeout);
			p.cycleTimeout = 0;
		}
		if (opts.random && val < 0) {
			opts.randomIndex--;
			if (--opts.randomIndex == -2) {
				opts.randomIndex = els.length - 2;
			} else {
				if (opts.randomIndex == -1) {
					opts.randomIndex = els.length - 1;
				}
			}
			opts.nextSlide = opts.randomMap[opts.randomIndex];
		} else {
			if (opts.random) {
				if (++opts.randomIndex == els.length) {
					opts.randomIndex = 0;
				}
				opts.nextSlide = opts.randomMap[opts.randomIndex];
			} else {
				opts.nextSlide = opts.currSlide + val;
				if (opts.nextSlide < 0) {
					if (opts.nowrap) {
						return false;
					}
					opts.nextSlide = els.length - 1;
				} else {
					if (opts.nextSlide >= els.length) {
						if (opts.nowrap) {
							return false;
						}
						opts.nextSlide = 0;
					}
				}
			}
		}
		if ($.isFunction(opts.prevNextClick)) {
			opts.prevNextClick(val > 0, opts.nextSlide, els[opts.nextSlide]);
		}
		go(els, opts, 1, val >= 0);
		return false;
	}
	function buildPager(els, opts) {
		var $p = $(opts.pager);
		$.each(els,
		function(i, o) {
			$.fn.cycle.createPagerAnchor(i, o, $p, els, opts);
		});
		$.fn.cycle.updateActivePagerLink(opts.pager, opts.startingSlide);
	}
	$.fn.cycle.createPagerAnchor = function(i, el, $p, els, opts) {
		var a = ($.isFunction(opts.pagerAnchorBuilder)) ? opts.pagerAnchorBuilder(i, el) : '<a href="#">' + (i + 1) + "</a>";
		if (!a) {
			return;
		}
		var $a = $(a);
		if ($a.parents("body").length == 0) {
			var arr = [];
			if ($p.length > 1) {
				$p.each(function() {
					var $clone = $a.clone(true);
					$(this).append($clone);
					arr.push($clone);
				});
				$a = $(arr);
			} else {
				$a.appendTo($p);
			}
		}
		$a.bind(opts.pagerEvent,
		function() {
			opts.nextSlide = i;
			var p = opts.$cont[0],
			timeout = p.cycleTimeout;
			if (timeout) {
				clearTimeout(timeout);
				p.cycleTimeout = 0;
			}
			if ($.isFunction(opts.pagerClick)) {
				opts.pagerClick(opts.nextSlide, els[opts.nextSlide]);
			}
			go(els, opts, 1, opts.currSlide < i);
			return false;
		});
		if (opts.pauseOnPagerHover) {
			$a.hover(function() {
				opts.$cont[0].cyclePause++;
			},
			function() {
				opts.$cont[0].cyclePause--;
			});
		}
	};
	$.fn.cycle.hopsFromLast = function(opts, fwd) {
		var hops, l = opts.lastSlide,
		c = opts.currSlide;
		if (fwd) {
			hops = c > l ? c - l: opts.slideCount - l;
		} else {
			hops = c < l ? l - c: l + opts.slideCount - c;
		}
		return hops;
	};
	function clearTypeFix($slides) {
		function hex(s) {
			s = parseInt(s).toString(16);
			return s.length < 2 ? "0" + s: s;
		}
		function getBg(e) {
			for (; e && e.nodeName.toLowerCase() != "html"; e = e.parentNode) {
				var v = $.css(e, "background-color");
				if (v.indexOf("rgb") >= 0) {
					var rgb = v.match(/\d+/g);
					return "#" + hex(rgb[0]) + hex(rgb[1]) + hex(rgb[2]);
				}
				if (v && v != "transparent") {
					return v;
				}
			}
			return "#ffffff";
		}
		$slides.each(function() {
			$(this).css("background-color", getBg(this));
		});
	}
	$.fn.cycle.commonReset = function(curr, next, opts, w, h, rev) {
		$(opts.elements).not(curr).hide();
		opts.cssBefore.opacity = 1;
		opts.cssBefore.display = "block";
		if (w !== false && next.cycleW > 0) {
			opts.cssBefore.width = next.cycleW;
		}
		if (h !== false && next.cycleH > 0) {
			opts.cssBefore.height = next.cycleH;
		}
		opts.cssAfter = opts.cssAfter || {};
		opts.cssAfter.display = "none";
		$(curr).css("zIndex", opts.slideCount + (rev === true ? 1 : 0));
		$(next).css("zIndex", opts.slideCount + (rev === true ? 0 : 1));
	};
	$.fn.cycle.custom = function(curr, next, opts, cb, speedOverride) {
		var $l = $(curr),
		$n = $(next);
		var speedIn = opts.speedIn,
		speedOut = opts.speedOut,
		easeIn = opts.easeIn,
		easeOut = opts.easeOut;
		$n.css(opts.cssBefore);
		if (speedOverride) {
			if (typeof speedOverride == "number") {
				speedIn = speedOut = speedOverride;
			} else {
				speedIn = speedOut = 1;
			}
			easeIn = easeOut = null;
		}
		var fn = function() {
			$n.animate(opts.animIn, speedIn, easeIn, cb);
		};
		$l.animate(opts.animOut, speedOut, easeOut,
		function() {
			if (opts.cssAfter) {
				$l.css(opts.cssAfter);
			}
			if (!opts.sync) {
				fn();
			}
		});
		if (opts.sync) {
			fn();
		}
	};
	$.fn.cycle.transitions = {
		fade: function($cont, $slides, opts) {
			$slides.not(":eq(" + opts.currSlide + ")").css("opacity", 0);
			opts.before.push(function(curr, next, opts) {
				$.fn.cycle.commonReset(curr, next, opts);
				opts.cssBefore.opacity = 0;
			});
			opts.animIn = {
				opacity: 1
			};
			opts.animOut = {
				opacity: 0
			};
			opts.cssBefore = {
				top: 0,
				left: 0
			};
		}
	};
	$.fn.cycle.ver = function() {
		return ver;
	};
	$.fn.cycle.defaults = {
		fx: "fade",
		timeout: 4000,
		timeoutFn: null,
		continuous: 0,
		speed: 1000,
		speedIn: null,
		speedOut: null,
		next: null,
		prev: null,
		prevNextClick: null,
		pager: null,
		pagerClick: null,
		pagerEvent: "click",
		pagerAnchorBuilder: null,
		before: null,
		after: null,
		end: null,
		easing: null,
		easeIn: null,
		easeOut: null,
		shuffle: null,
		animIn: null,
		animOut: null,
		cssBefore: null,
		cssAfter: null,
		fxFn: null,
		height: "auto",
		startingSlide: 0,
		sync: 1,
		random: 0,
		fit: 0,
		containerResize: 1,
		pause: 1,
		pauseOnPagerHover: 0,
		autostop: 0,
		autostopCount: 0,
		delay: 0,
		slideExpr: null,
		cleartype: !$.support.opacity,
		nowrap: 0,
		fastOnEvent: 0,
		randomizeEffects: 1,
		rev: 0,
		manualTrump: true,
		requeueOnImageNotLoaded: true,
		requeueTimeout: 250
	};
})(jQuery);

; (function($) {
	$.fn.cycle.transitions.scrollUp = function($cont, $slides, opts) {
		$cont.css("overflow", "hidden");
		opts.before.push($.fn.cycle.commonReset);
		var h = $cont.height();
		opts.cssBefore = {
			top: h,
			left: 0
		};
		opts.cssFirst = {
			top: 0
		};
		opts.animIn = {
			top: 0
		};
		opts.animOut = {
			top: -h
		};
	};
	$.fn.cycle.transitions.scrollDown = function($cont, $slides, opts) {
		$cont.css("overflow", "hidden");
		opts.before.push($.fn.cycle.commonReset);
		var h = $cont.height();
		opts.cssFirst = {
			top: 0
		};
		opts.cssBefore = {
			top: -h,
			left: 0
		};
		opts.animIn = {
			top: 0
		};
		opts.animOut = {
			top: h
		};
	};
	$.fn.cycle.transitions.scrollLeft = function($cont, $slides, opts) {
		$cont.css("overflow", "hidden");
		opts.before.push($.fn.cycle.commonReset);
		var w = $cont.width();
		opts.cssFirst = {
			left: 0
		};
		opts.cssBefore = {
			left: w,
			top: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			left: 0 - w
		};
	};
	$.fn.cycle.transitions.scrollRight = function($cont, $slides, opts) {
		$cont.css("overflow", "hidden");
		opts.before.push($.fn.cycle.commonReset);
		var w = $cont.width();
		opts.cssFirst = {
			left: 0
		};
		opts.cssBefore = {
			left: -w,
			top: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			left: w
		};
	};
	$.fn.cycle.transitions.scrollHorz = function($cont, $slides, opts) {
		$cont.css("overflow", "hidden").width();
		opts.before.push(function(curr, next, opts, fwd) {
			$.fn.cycle.commonReset(curr, next, opts);
			opts.cssBefore.left = fwd ? (next.cycleW - 1) : (1 - next.cycleW);
			opts.animOut.left = fwd ? -curr.cycleW: curr.cycleW;
		});
		opts.cssFirst = {
			left: 0
		};
		opts.cssBefore = {
			top: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			top: 0
		};
	};
	$.fn.cycle.transitions.scrollVert = function($cont, $slides, opts) {
		$cont.css("overflow", "hidden");
		opts.before.push(function(curr, next, opts, fwd) {
			$.fn.cycle.commonReset(curr, next, opts);
			opts.cssBefore.top = fwd ? (1 - next.cycleH) : (next.cycleH - 1);
			opts.animOut.top = fwd ? curr.cycleH: -curr.cycleH;
		});
		opts.cssFirst = {
			top: 0
		};
		opts.cssBefore = {
			left: 0
		};
		opts.animIn = {
			top: 0
		};
		opts.animOut = {
			left: 0
		};
	};
	$.fn.cycle.transitions.slideX = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$(opts.elements).not(curr).hide();
			$.fn.cycle.commonReset(curr, next, opts, false, true);
			opts.animIn.width = next.cycleW;
		});
		opts.cssBefore = {
			left: 0,
			top: 0,
			width: 0
		};
		opts.animIn = {
			width: "show"
		};
		opts.animOut = {
			width: 0
		};
	};
	$.fn.cycle.transitions.slideY = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$(opts.elements).not(curr).hide();
			$.fn.cycle.commonReset(curr, next, opts, true, false);
			opts.animIn.height = next.cycleH;
		});
		opts.cssBefore = {
			left: 0,
			top: 0,
			height: 0
		};
		opts.animIn = {
			height: "show"
		};
		opts.animOut = {
			height: 0
		};
	};
	$.fn.cycle.transitions.shuffle = function($cont, $slides, opts) {
		var w = $cont.css("overflow", "visible").width();
		$slides.css({
			left: 0,
			top: 0
		});
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, true, true);
		});
		opts.speed = opts.speed / 2;
		opts.random = 0;
		opts.shuffle = opts.shuffle || {
			left: -w,
			top: 15
		};
		opts.els = [];
		for (var i = 0; i < $slides.length; i++) {
			opts.els.push($slides[i]);
		}
		for (var i = 0; i < opts.currSlide; i++) {
			opts.els.push(opts.els.shift());
		}
		opts.fxFn = function(curr, next, opts, cb, fwd) {
			var $el = fwd ? $(curr) : $(next);
			$(next).css(opts.cssBefore);
			var count = opts.slideCount;
			$el.animate(opts.shuffle, opts.speedIn, opts.easeIn,
			function() {
				var hops = $.fn.cycle.hopsFromLast(opts, fwd);
				for (var k = 0; k < hops; k++) {
					fwd ? opts.els.push(opts.els.shift()) : opts.els.unshift(opts.els.pop());
				}
				if (fwd) {
					for (var i = 0,
					len = opts.els.length; i < len; i++) {
						$(opts.els[i]).css("z-index", len - i + count);
					}
				} else {
					var z = $(curr).css("z-index");
					$el.css("z-index", parseInt(z) + 1 + count);
				}
				$el.animate({
					left: 0,
					top: 0
				},
				opts.speedOut, opts.easeOut,
				function() {
					$(fwd ? this: curr).hide();
					if (cb) {
						cb();
					}
				});
			});
		};
		opts.cssBefore = {
			display: "block",
			opacity: 1,
			top: 0,
			left: 0
		};
	};
	$.fn.cycle.transitions.turnUp = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, false);
			opts.cssBefore.top = next.cycleH;
			opts.animIn.height = next.cycleH;
		});
		opts.cssFirst = {
			top: 0
		};
		opts.cssBefore = {
			left: 0,
			height: 0
		};
		opts.animIn = {
			top: 0
		};
		opts.animOut = {
			height: 0
		};
	};
	$.fn.cycle.transitions.turnDown = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, false);
			opts.animIn.height = next.cycleH;
			opts.animOut.top = curr.cycleH;
		});
		opts.cssFirst = {
			top: 0
		};
		opts.cssBefore = {
			left: 0,
			top: 0,
			height: 0
		};
		opts.animOut = {
			height: 0
		};
	};
	$.fn.cycle.transitions.turnLeft = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, false, true);
			opts.cssBefore.left = next.cycleW;
			opts.animIn.width = next.cycleW;
		});
		opts.cssBefore = {
			top: 0,
			width: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			width: 0
		};
	};
	$.fn.cycle.transitions.turnRight = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, false, true);
			opts.animIn.width = next.cycleW;
			opts.animOut.left = curr.cycleW;
		});
		opts.cssBefore = {
			top: 0,
			left: 0,
			width: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			width: 0
		};
	};
	$.fn.cycle.transitions.zoom = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, false, false, true);
			opts.cssBefore.top = next.cycleH / 2;
			opts.cssBefore.left = next.cycleW / 2;
			opts.animIn = {
				top: 0,
				left: 0,
				width: next.cycleW,
				height: next.cycleH
			};
			opts.animOut = {
				width: 0,
				height: 0,
				top: curr.cycleH / 2,
				left: curr.cycleW / 2
			};
		});
		opts.cssFirst = {
			top: 0,
			left: 0
		};
		opts.cssBefore = {
			width: 0,
			height: 0
		};
	};
	$.fn.cycle.transitions.fadeZoom = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, false, false);
			opts.cssBefore.left = next.cycleW / 2;
			opts.cssBefore.top = next.cycleH / 2;
			opts.animIn = {
				top: 0,
				left: 0,
				width: next.cycleW,
				height: next.cycleH
			};
		});
		opts.cssBefore = {
			width: 0,
			height: 0
		};
		opts.animOut = {
			opacity: 0
		};
	};
	$.fn.cycle.transitions.blindX = function($cont, $slides, opts) {
		var w = $cont.css("overflow", "hidden").width();
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts);
			opts.animIn.width = next.cycleW;
			opts.animOut.left = curr.cycleW;
		});
		opts.cssBefore = {
			left: w,
			top: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			left: w
		};
	};
	$.fn.cycle.transitions.blindY = function($cont, $slides, opts) {
		var h = $cont.css("overflow", "hidden").height();
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts);
			opts.animIn.height = next.cycleH;
			opts.animOut.top = curr.cycleH;
		});
		opts.cssBefore = {
			top: h,
			left: 0
		};
		opts.animIn = {
			top: 0
		};
		opts.animOut = {
			top: h
		};
	};
	$.fn.cycle.transitions.blindZ = function($cont, $slides, opts) {
		var h = $cont.css("overflow", "hidden").height();
		var w = $cont.width();
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts);
			opts.animIn.height = next.cycleH;
			opts.animOut.top = curr.cycleH;
		});
		opts.cssBefore = {
			top: h,
			left: w
		};
		opts.animIn = {
			top: 0,
			left: 0
		};
		opts.animOut = {
			top: h,
			left: w
		};
	};
	$.fn.cycle.transitions.growX = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, false, true);
			opts.cssBefore.left = this.cycleW / 2;
			opts.animIn = {
				left: 0,
				width: this.cycleW
			};
			opts.animOut = {
				left: 0
			};
		});
		opts.cssBefore = {
			width: 0,
			top: 0
		};
	};
	$.fn.cycle.transitions.growY = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, false);
			opts.cssBefore.top = this.cycleH / 2;
			opts.animIn = {
				top: 0,
				height: this.cycleH
			};
			opts.animOut = {
				top: 0
			};
		});
		opts.cssBefore = {
			height: 0,
			left: 0
		};
	};
	$.fn.cycle.transitions.curtainX = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, false, true, true);
			opts.cssBefore.left = next.cycleW / 2;
			opts.animIn = {
				left: 0,
				width: this.cycleW
			};
			opts.animOut = {
				left: curr.cycleW / 2,
				width: 0
			};
		});
		opts.cssBefore = {
			top: 0,
			width: 0
		};
	};
	$.fn.cycle.transitions.curtainY = function($cont, $slides, opts) {
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, false, true);
			opts.cssBefore.top = next.cycleH / 2;
			opts.animIn = {
				top: 0,
				height: next.cycleH
			};
			opts.animOut = {
				top: curr.cycleH / 2,
				height: 0
			};
		});
		opts.cssBefore = {
			left: 0,
			height: 0
		};
	};
	$.fn.cycle.transitions.cover = function($cont, $slides, opts) {
		var d = opts.direction || "left";
		var w = $cont.css("overflow", "hidden").width();
		var h = $cont.height();
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts);
			if (d == "right") {
				opts.cssBefore.left = -w;
			} else {
				if (d == "up") {
					opts.cssBefore.top = h;
				} else {
					if (d == "down") {
						opts.cssBefore.top = -h;
					} else {
						opts.cssBefore.left = w;
					}
				}
			}
		});
		opts.animIn = {
			left: 0,
			top: 0
		};
		opts.animOut = {
			opacity: 1
		};
		opts.cssBefore = {
			top: 0,
			left: 0
		};
	};
	$.fn.cycle.transitions.uncover = function($cont, $slides, opts) {
		var d = opts.direction || "left";
		var w = $cont.css("overflow", "hidden").width();
		var h = $cont.height();
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, true, true);
			if (d == "right") {
				opts.animOut.left = w;
			} else {
				if (d == "up") {
					opts.animOut.top = -h;
				} else {
					if (d == "down") {
						opts.animOut.top = h;
					} else {
						opts.animOut.left = -w;
					}
				}
			}
		});
		opts.animIn = {
			left: 0,
			top: 0
		};
		opts.animOut = {
			opacity: 1
		};
		opts.cssBefore = {
			top: 0,
			left: 0
		};
	};
	$.fn.cycle.transitions.toss = function($cont, $slides, opts) {
		var w = $cont.css("overflow", "visible").width();
		var h = $cont.height();
		opts.before.push(function(curr, next, opts) {
			$.fn.cycle.commonReset(curr, next, opts, true, true, true);
			if (!opts.animOut.left && !opts.animOut.top) {
				opts.animOut = {
					left: w * 2,
					top: -h / 2,
					opacity: 0
				};
			} else {
				opts.animOut.opacity = 0;
			}
		});
		opts.cssBefore = {
			left: 0,
			top: 0
		};
		opts.animIn = {
			left: 0
		};
	};
	$.fn.cycle.transitions.wipe = function($cont, $slides, opts) {
		var w = $cont.css("overflow", "hidden").width();
		var h = $cont.height();
		opts.cssBefore = opts.cssBefore || {};
		var clip;
		if (opts.clip) {
			if (/l2r/.test(opts.clip)) {
				clip = "rect(0px 0px " + h + "px 0px)";
			} else {
				if (/r2l/.test(opts.clip)) {
					clip = "rect(0px " + w + "px " + h + "px " + w + "px)";
				} else {
					if (/t2b/.test(opts.clip)) {
						clip = "rect(0px " + w + "px 0px 0px)";
					} else {
						if (/b2t/.test(opts.clip)) {
							clip = "rect(" + h + "px " + w + "px " + h + "px 0px)";
						} else {
							if (/zoom/.test(opts.clip)) {
								var t = parseInt(h / 2);
								var l = parseInt(w / 2);
								clip = "rect(" + t + "px " + l + "px " + t + "px " + l + "px)";
							}
						}
					}
				}
			}
		}
		opts.cssBefore.clip = opts.cssBefore.clip || clip || "rect(0px 0px 0px 0px)";
		var d = opts.cssBefore.clip.match(/(\d+)/g);
		var t = parseInt(d[0]),
		r = parseInt(d[1]),
		b = parseInt(d[2]),
		l = parseInt(d[3]);
		opts.before.push(function(curr, next, opts) {
			if (curr == next) {
				return;
			}
			var $curr = $(curr),
			$next = $(next);
			$.fn.cycle.commonReset(curr, next, opts, true, true, false);
			opts.cssAfter.display = "block";
			var step = 1,
			count = parseInt((opts.speedIn / 13)) - 1; (function f() {
				var tt = t ? t - parseInt(step * (t / count)) : 0;
				var ll = l ? l - parseInt(step * (l / count)) : 0;
				var bb = b < h ? b + parseInt(step * ((h - b) / count || 1)) : h;
				var rr = r < w ? r + parseInt(step * ((w - r) / count || 1)) : w;
				$next.css({
					clip: "rect(" + tt + "px " + rr + "px " + bb + "px " + ll + "px)"
				}); (step++<=count) ? setTimeout(f, 13) : $curr.css("display", "none");
			})();
		});
		opts.cssBefore = {
			display: "block",
			opacity: 1,
			top: 0,
			left: 0
		};
		opts.animIn = {
			left: 0
		};
		opts.animOut = {
			left: 0
		};
	};
})(jQuery);
$(function() {
	$('.Slides').before('<div class="SlideTriggers">').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 5000,
		pager: '.SlideTriggers'
	});
	$('#slideshow1').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev1',
		next: '#next1',
		pager: '#nav1',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav1 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow_1').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		pager: '#nav_1',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav_1 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow2').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev2',
		next: '#next2',
		pager: '#nav2',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav2 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow3').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev3',
		next: '#next3',
		pager: '#nav3',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav3 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow4').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev4',
		next: '#next4',
		pager: '#nav4',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav4 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow5').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev5',
		next: '#next5',
		pager: '#nav5',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav5 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow6').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev6',
		next: '#next6',
		pager: '#nav6',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav6 a:eq(' + (idx) + ')';
		}
	});

	$('#slideshow11').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev11',
		next: '#next11',
		pager: '#nav11',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav11 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow12').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev12',
		next: '#next12',
		pager: '#nav12',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav12 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow13').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev13',
		next: '#next13',
		pager: '#nav13',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav13 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow14').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev14',
		next: '#next14',
		pager: '#nav14',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav14 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow15').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev15',
		next: '#next15',
		pager: '#nav15',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav15 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshow16').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		prev: '#prev16',
		next: '#next16',
		pager: '#nav16',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#nav16 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshows_11').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		pager: '#navs_11',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#navs_11 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshows_12').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		pager: '#navs_12',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#navs_12 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshows_13').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		pager: '#navs_13',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#navs_13 a:eq(' + (idx) + ')';
		}
	});
	$('#slideshows_14').cycle({
		fx: 'scrollHorz',
		speed: '800',
		timeout: 4000,
		pager: '#navs_14',
		pagerAnchorBuilder: function(idx, slide) {
			// return sel string for existing anchor
			return '#navs_14 a:eq(' + (idx) + ')';
		}
	});

});
// nav.js
;
$(function() {
	initNavFix()
});
function initNavFix() {
	new touchNav({
		navBlock: 'nav'
	});
	new touchNav({
		navBlock: 'navs'
	});
	new touchNav({
		navBlock: 'navs9'
	})
}
function touchNav(options) {
	this.options = {
		mobileReg: /(ipad|iphone|ipod|android|blackberry|iemobile)/gi,
		hoverClass: 'hover',
		followLink: false,
		menuItems: 'li',
		menuOpener: 'a',
		menuDrop: 'div',
		navBlock: null
	};
	for (var p in options) {
		this.options[p] = options[p]
	}
	this.init()
}
touchNav.prototype = {
	init: function() {
		this.isMobile = (this.options.mobileReg).test(navigator.userAgent);
		if (typeof this.options.navBlock === 'string') {
			this.menu = document.getElementById(this.options.navBlock)
		} else if (typeof this.options.navBlock === 'object') {
			this.menu = this.options.navBlock
		}
		if (this.menu) {
			this.getElements();
			this.addEvents()
		}
	},
	getElements: function() {
		this.menuItems = this.menu.getElementsByTagName(this.options.menuItems)
	},
	hideActiveDropdown: function() {
		if (this.activeParent) {
			for (var i = 0; i < this.menuItems.length; i++) {
				this.removeClass(this.menuItems[i], this.options.hoverClass)
			}
			this.activeParent = null
		}
	},
	getOpener: function(obj) {
		for (var i = 0; i < obj.childNodes.length; i++) {
			if (obj.childNodes[i].tagName && obj.childNodes[i].tagName.toLowerCase() == this.options.menuOpener.toLowerCase()) {
				return obj.childNodes[i]
			}
		}
		return false
	},
	getDrop: function(obj) {
		for (var i = 0; i < obj.childNodes.length; i++) {
			if (obj.childNodes[i].tagName && obj.childNodes[i].tagName.toLowerCase() == this.options.menuDrop.toLowerCase()) {
				return obj.childNodes[i]
			}
		}
		return false
	},
	addEvents: function() {
		if (this.isMobile) {
			for (var i = 0; i < this.menuItems.length; i++) {
				this.menuItems[i].touchNav = this;
				if (this.getDrop(this.menuItems[i])) {
					this.addHandler(this.getOpener(this.menuItems[i]), 'click', this.bind(this.clickHandler, this.menuItems[i]))
				}
			}
			this.addHandler(document.body, 'click', this.bind(this.outsideHandler, this));
			this.addHandler(document.body, 'touchstart', this.bind(this.outsideHandler, this))
		} else {
			for (var i = 0; i < this.menuItems.length; i++) {
				this.menuItems[i].touchNav = this;
				this.addHandler(this.menuItems[i], 'mouseover', this.mouseoverHandler);
				this.addHandler(this.menuItems[i], 'mouseout', this.mouseoutHandler)
			}
		}
	},
	outsideHandler: function(e) {
		var childFlag = false;
		if (this.activeParent) {
			this.outsideTarget = e.target || e.currentTarget || e.srcElement;
			while (this.outsideTarget.parentNode) {
				if (this.activeParent == this.outsideTarget) {
					childFlag = true;
					break
				}
				this.outsideTarget = this.outsideTarget.parentNode
			}
			if (!childFlag) {
				this.hideActiveDropdown()
			}
		}
	},
	mouseoverHandler: function() {
		this.touchNav.addClass(this, this.touchNav.options.hoverClass)
	},
	mouseoutHandler: function() {
		this.touchNav.removeClass(this, this.touchNav.options.hoverClass)
	},
	clickHandler: function(e) {
		var tNav = this.touchNav;
		tNav.currentElement = e.currentTarget || e.srcElement;
		tNav.currentParent = tNav.currentElement.parentNode;
		if (tNav.activeParent && !tNav.isParent(tNav.activeParent, tNav.currentParent) && tNav.currentParent != tNav.activeParent) {
			tNav.hideActiveDropdown()
		}
		if (tNav.hasClass(tNav.currentParent, tNav.options.hoverClass)) {
			tNav.removeClass(tNav.currentParent, tNav.options.hoverClass);
			if (tNav.options.followLink) {
				window.location.href = tNav.currentElement.href
			}
		} else {
			tNav.addClass(tNav.currentParent, tNav.options.hoverClass);
			tNav.activeParent = tNav.currentParent;
			return tNav.preventEvent(e)
		}
	},
	preventEvent: function(e) {
		if (!e) e = window.event;
		if (e.preventDefault) e.preventDefault();
		if (e.stopPropagation) e.stopPropagation();
		e.cancelBubble = true;
		return false
	},
	isParent: function(parent, child) {
		while (child.parentNode) {
			if (child.parentNode == parent) {
				return true
			}
			child = child.parentNode
		}
		return false
	},
	addHandler: function(object, event, handler) {
		if (typeof object.addEventListener != 'undefined') object.addEventListener(event, this.bind(handler, object), false);
		else if (typeof object.attachEvent != 'undefined') object.attachEvent('on' + event, this.bind(handler, object))
	},
	removeHandler: function(object, event, handler) {
		if (typeof object.removeEventListener != 'undefined') object.removeEventListener(event, handler, false);
		else if (typeof object.detachEvent != 'undefined') object.detachEvent('on' + event, handler)
	},
	hasClass: function(obj, cname) {
		return (obj.className ? obj.className.match(new RegExp('(\\s|^)' + cname + '(\\s|$)')) : false)
	},
	addClass: function(obj, cname) {
		if (!this.hasClass(obj, cname)) obj.className += " " + cname
	},
	removeClass: function(obj, cname) {
		if (this.hasClass(obj, cname)) obj.className = obj.className.replace(new RegExp('(\\s|^)' + cname + '(\\s|$)'), ' ')
	},
	bind: function(func, scope) {
		return function() {
			return func.apply(scope, arguments)
		}
	}
};
//jqzoom.js
; (function($) {
	$.fn.jqueryzoom = function(options) {
		var settings = {
			xzoom: 200,
			yzoom: 200,
			offset: 10,
			position: "right",
			lens: 1,
			preload: 1
		};
		if (options) {
			$.extend(settings, options)
		}
		var noalt = '';
		$(this).hover(function() {
			var imageLeft = $(this).offset().left;
			var imageTop = $(this).offset().top;
			var imageWidth = $(this).children('img').get(0).offsetWidth;
			var imageHeight = $(this).children('img').get(0).offsetHeight;
			noalt = $(this).children("img").attr("alt");
			var bigimage = $(this).children("img").attr("title");
			$(this).children("img").attr("alt", '');
			if ($("div.zoomdiv").get().length == 0) {
				$(this).after("<div class='zoomdiv'><img class='bigimg' src='" + bigimage + "'/></div>");
				$(this).append("<div class='jqZoomPup'>&nbsp;</div>")
			}
			if (settings.position == "right") {
				if (imageLeft + imageWidth + settings.offset + settings.xzoom > screen.width) {
					leftpos = imageLeft - settings.offset - settings.xzoom
				} else {
					leftpos = imageLeft + imageWidth + settings.offset
				}
			} else {
				leftpos = imageLeft - settings.xzoom - settings.offset;
				if (leftpos < 0) {
					leftpos = imageLeft + imageWidth + settings.offset
				}
			}
			$("div.zoomdiv").css({
				top: imageTop,
				left: leftpos
			});
			$("div.zoomdiv").width(settings.xzoom);
			$("div.zoomdiv").height(settings.yzoom);
			$("div.zoomdiv").show();
			if (!settings.lens) {
				$(this).css('cursor', 'crosshair')
			}
			$(document.body).mousemove(function(e) {
				mouse = new MouseEvent(e);
				var bigwidth = $(".bigimg").get(0).offsetWidth;
				var bigheight = $(".bigimg").get(0).offsetHeight;
				var scaley = 'x';
				var scalex = 'y';
				if (isNaN(scalex) | isNaN(scaley)) {
					var scalex = (bigwidth / imageWidth);
					var scaley = (bigheight / imageHeight);
					$("div.jqZoomPup").width((settings.xzoom) / scalex);
					$("div.jqZoomPup").height((settings.yzoom) / scaley);
					if (settings.lens) {
						$("div.jqZoomPup").css('visibility', 'visible')
					}
				}
				xpos = mouse.x - $("div.jqZoomPup").width() / 2 - imageLeft;
				ypos = mouse.y - $("div.jqZoomPup").height() / 2 - imageTop;
				if (settings.lens) {
					xpos = (mouse.x - $("div.jqZoomPup").width() / 2 < imageLeft) ? 0 : (mouse.x + $("div.jqZoomPup").width() / 2 > imageWidth + imageLeft) ? (imageWidth - $("div.jqZoomPup").width() - 2) : xpos;
					ypos = (mouse.y - $("div.jqZoomPup").height() / 2 < imageTop) ? 0 : (mouse.y + $("div.jqZoomPup").height() / 2 > imageHeight + imageTop) ? (imageHeight - $("div.jqZoomPup").height() - 2) : ypos
				}
				if (settings.lens) {
					$("div.jqZoomPup").css({
						top: ypos,
						left: xpos
					})
				}
				scrolly = ypos;
				$("div.zoomdiv").get(0).scrollTop = scrolly * scaley;
				scrollx = xpos;
				$("div.zoomdiv").get(0).scrollLeft = (scrollx) * scalex
			})
		},
		function() {
			$(this).children("img").attr("alt", noalt);
			$(document.body).unbind("mousemove");
			if (settings.lens) {
				$("div.jqZoomPup").remove()
			}
			$("div.zoomdiv").remove()
		});
		count = 0;
		if (settings.preload) {
			$('body').append("<div style='display:none;' class='jqPreload" + count + "'>sdsdssdsd</div>");
			$(this).each(function() {
				var imagetopreload = $(this).children("img").attr("jqimg");
				var content = jQuery('div.jqPreload' + count + '').html();
				jQuery('div.jqPreload' + count + '').html(content + '<img src=\"' + imagetopreload + '\">')
			})
		}
	}
})(jQuery);
function MouseEvent(e) {
	this.x = e.pageX;
	this.y = e.pageY
};
//location.js
; !
function($) {
	$.extend({
		_jsonp: {
			scripts: {},
			counter: 1,
			charset: "gb2312",
			head: document.getElementsByTagName("head")[0],
			name: function(callback) {
				var name = "_jsonp_" + (new Date).getTime() + "_" + this.counter;
				this.counter++;
				var cb = function(json) {
					eval("delete " + name),
					callback(json),
					$._jsonp.head.removeChild($._jsonp.scripts[name]),
					delete $._jsonp.scripts[name]
				};
				return eval(name + " = cb"),
				name
			},
			load: function(a, b) {
				var c = document.createElement("script");
				c.type = "text/javascript",
				c.charset = this.charset,
				c.src = a,
				this.head.appendChild(c),
				this.scripts[b] = c
			}
		},
		getJSONP: function(a, b) {
			var c = $._jsonp.name(b),
			a = a.replace(/{callback};/, c);
			return $._jsonp.load(a, c),
			this
		}
	})
} (jQuery);

//superslide2.11.js
; !
function(a) {
	a.fn.slide = function(b) {
		return a.fn.slide.defaults = {
			type: "slide",
			effect: "fade",
			autoPlay: !1,
			delayTime: 500,
			interTime: 2500,
			triggerTime: 150,
			defaultIndex: 0,
			titCell: ".hd li",
			mainCell: ".bd",
			targetCell: null,
			trigger: "mouseover",
			scroll: 1,
			vis: 1,
			titOnClassName: "on",
			autoPage: !1,
			prevCell: ".prev",
			nextCell: ".next",
			pageStateCell: ".pageState",
			opp: !1,
			pnLoop: !0,
			easing: "swing",
			startFun: null,
			endFun: null,
			switchLoad: null,
			playStateCell: ".playState",
			mouseOverStop: !0,
			defaultPlay: !0,
			returnDefault: !1
		},
		this.each(function() {
			var c = a.extend({},
			a.fn.slide.defaults, b),
			d = a(this),
			e = c.effect,
			f = a(c.prevCell, d),
			g = a(c.nextCell, d),
			h = a(c.pageStateCell, d),
			i = a(c.playStateCell, d),
			j = a(c.titCell, d),
			k = j.size(),
			l = a(c.mainCell, d),
			m = l.children().size(),
			n = c.switchLoad,
			o = a(c.targetCell, d),
			p = parseInt(c.defaultIndex),
			q = parseInt(c.delayTime),
			r = parseInt(c.interTime);
			parseInt(c.triggerTime);
			var Q, t = parseInt(c.scroll),
			u = parseInt(c.vis),
			v = "false" == c.autoPlay || 0 == c.autoPlay ? !1 : !0,
			w = "false" == c.opp || 0 == c.opp ? !1 : !0,
			x = "false" == c.autoPage || 0 == c.autoPage ? !1 : !0,
			y = "false" == c.pnLoop || 0 == c.pnLoop ? !1 : !0,
			z = "false" == c.mouseOverStop || 0 == c.mouseOverStop ? !1 : !0,
			A = "false" == c.defaultPlay || 0 == c.defaultPlay ? !1 : !0,
			B = "false" == c.returnDefault || 0 == c.returnDefault ? !1 : !0,
			C = 0,
			D = 0,
			E = 0,
			F = 0,
			G = c.easing,
			H = null,
			I = null,
			J = null,
			K = c.titOnClassName,
			L = j.index(d.find("." + K)),
			M = p = -1 == L ? p: L,
			N = p,
			O = p,
			P = m >= u ? 0 != m % t ? m % t: t: 0,
			R = "leftMarquee" == e || "topMarquee" == e ? !0 : !1,
			S = function() {
				a.isFunction(c.startFun) && c.startFun(p, k, d, a(c.titCell, d), l, o, f, g)
			},
			T = function() {
				a.isFunction(c.endFun) && c.endFun(p, k, d, a(c.titCell, d), l, o, f, g)
			},
			U = function() {
				j.removeClass(K),
				A && j.eq(N).addClass(K)
			};
			if ("menu" == c.type) return A && j.removeClass(K).eq(p).addClass(K),
			j.hover(function() {
				Q = a(this).find(c.targetCell);
				var b = j.index(a(this));
				I = setTimeout(function() {
					switch (p = b, j.removeClass(K).eq(p).addClass(K), S(), e) {
					case "fade":
						Q.stop(!0, !0).animate({
							opacity: "show"
						},
						q, G, T);
						break;
					case "slideDown":
						Q.stop(!0, !0).animate({
							height: "show"
						},
						q, G, T)
					}
				},
				c.triggerTime)
			},
			function() {
				switch (clearTimeout(I), e) {
				case "fade":
					Q.animate({
						opacity:
						"hide"
					},
					q, G);
					break;
				case "slideDown":
					Q.animate({
						height:
						"hide"
					},
					q, G)
				}
			}),
			B && d.hover(function() {
				clearTimeout(J)
			},
			function() {
				J = setTimeout(U, q)
			}),
			void 0;
			if (0 == k && (k = m), R && (k = 2), x) {
				if (m >= u) if ("leftLoop" == e || "topLoop" == e) k = 0 != m % t ? (0 ^ m / t) + 1 : m / t;
				else {
					var V = m - u;
					k = 1 + parseInt(0 != V % t ? V / t + 1 : V / t),
					0 >= k && (k = 1)
				} else k = 1;
				j.html("");
				var W = "";
				if (1 == c.autoPage || "true" == c.autoPage) for (var X = 0; k > X; X++) W += "<li>" + (X + 1) + "</li>";
				else for (var X = 0; k > X; X++) W += c.autoPage.replace("$", X + 1);
				j.html(W);
				var j = j.children()
			}
			if (m >= u) {
				l.children().each(function() {
					a(this).width() > E && (E = a(this).width(), D = a(this).outerWidth(!0)),
					a(this).height() > F && (F = a(this).height(), C = a(this).outerHeight(!0))
				});
				var Y = l.children(),
				Z = function() {
					for (var a = 0; u > a; a++) Y.eq(a).clone().addClass("clone").appendTo(l);
					for (var a = 0; P > a; a++) Y.eq(m - a - 1).clone().addClass("clone").prependTo(l)
				};
				switch (e) {
				case "fold":
					l.css({
						position:
						"relative",
						width: D,
						height: C
					}).children().css({
						position: "absolute",
						width: E,
						left: 0,
						top: 0,
						display: "none"
					});
					break;
				case "top":
					l.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:' + u * C + 'px"></div>').css({
						top: -(p * t) * C,
						position: "relative",
						padding: "0",
						margin: "0"
					}).children().css({
						height: F
					});
					break;
				case "left":
					l.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:' + u * D + 'px"></div>').css({
						width: m * D,
						left: -(p * t) * D,
						position: "relative",
						overflow: "hidden",
						padding: "0",
						margin: "0"
					}).children().css({
						"float": "left",
						width: E
					});
					break;
				case "leftLoop":
				case "leftMarquee":
					Z(),
					l.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:' + u * D + 'px"></div>').css({
						width: (m + u + P) * D,
						position: "relative",
						overflow: "hidden",
						padding: "0",
						margin: "0",
						left: -(P + p * t) * D
					}).children().css({
						"float": "left",
						width: E
					});
					break;
				case "topLoop":
				case "topMarquee":
					Z(),
					l.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:' + u * C + 'px"></div>').css({
						height: (m + u + P) * C,
						position: "relative",
						padding: "0",
						margin: "0",
						top: -(P + p * t) * C
					}).children().css({
						height: F
					})
				}
			}
			var $ = function(a) {
				var b = a * t;
				return a == k ? b = m: -1 == a && 0 != m % t && (b = -m % t),
				b
			},
			_ = function(b) {
				var c = function(c) {
					for (var d = c; u + c > d; d++) b.eq(d).find("img[" + n + "]").each(function() {
						var b = a(this);
						if (b.attr("src", b.attr(n)).removeAttr(n), l.find(".clone")[0]) for (var c = l.children(), d = 0; d < c.size(); d++) c.eq(d).find("img[" + n + "]").each(function() {
							a(this).attr(n) == b.attr("src") && a(this).attr("src", a(this).attr(n)).removeAttr(n)
						})
					})
				};
				switch (e) {
				case "fade":
				case "fold":
				case "top":
				case "left":
				case "slideDown":
					c(p * t);
					break;
				case "leftLoop":
				case "topLoop":
					c(P + $(O));
					break;
				case "leftMarquee":
				case "topMarquee":
					var d = "leftMarquee" == e ? l.css("left").replace("px", "") : l.css("top").replace("px", ""),
					f = "leftMarquee" == e ? D: C,
					g = P;
					if (0 != d % f) {
						var h = Math.abs(0 ^ d / f);
						g = 1 == p ? P + h: P + h - 1
					}
					c(g)
				}
			},
			ab = function(a) {
				if (!A || M != p || a || R) {
					if (R ? p >= 1 ? p = 1 : 0 >= p && (p = 0) : (O = p, p >= k ? p = 0 : 0 > p && (p = k - 1)), S(), null != n && _(l.children()), o[0] && (Q = o.eq(p), null != n && _(o), "slideDown" == e ? (o.not(Q).stop(!0, !0).slideUp(q), Q.slideDown(q, G,
					function() {
						l[0] || T()
					})) : (o.not(Q).stop(!0, !0).hide(), Q.animate({
						opacity: "show"
					},
					q,
					function() {
						l[0] || T()
					}))), m >= u) switch (e) {
					case "fade":
						l.children().stop(!0, !0).eq(p).animate({
							opacity: "show"
						},
						q, G,
						function() {
							T()
						}).siblings().hide();
						break;
					case "fold":
						l.children().stop(!0, !0).eq(p).animate({
							opacity: "show"
						},
						q, G,
						function() {
							T()
						}).siblings().animate({
							opacity: "hide"
						},
						q, G);
						break;
					case "top":
						l.stop(!0, !1).animate({
							top: -p * t * C
						},
						q, G,
						function() {
							T()
						});
						break;
					case "left":
						l.stop(!0, !1).animate({
							left: -p * t * D
						},
						q, G,
						function() {
							T()
						});
						break;
					case "leftLoop":
						var b = O;
						l.stop(!0, !0).animate({
							left: -($(O) + P) * D
						},
						q, G,
						function() { - 1 >= b ? l.css("left", -(P + (k - 1) * t) * D) : b >= k && l.css("left", -P * D),
							T()
						});
						break;
					case "topLoop":
						var b = O;
						l.stop(!0, !0).animate({
							top: -($(O) + P) * C
						},
						q, G,
						function() { - 1 >= b ? l.css("top", -(P + (k - 1) * t) * C) : b >= k && l.css("top", -P * C),
							T()
						});
						break;
					case "leftMarquee":
						var c = l.css("left").replace("px", "");
						0 == p ? l.animate({
							left: ++c
						},
						0,
						function() {
							l.css("left").replace("px", "") >= 0 && l.css("left", -m * D)
						}) : l.animate({
							left: --c
						},
						0,
						function() {
							l.css("left").replace("px", "") <= -(m + P) * D && l.css("left", -P * D)
						});
						break;
					case "topMarquee":
						var d = l.css("top").replace("px", "");
						0 == p ? l.animate({
							top: ++d
						},
						0,
						function() {
							l.css("top").replace("px", "") >= 0 && l.css("top", -m * C)
						}) : l.animate({
							top: --d
						},
						0,
						function() {
							l.css("top").replace("px", "") <= -(m + P) * C && l.css("top", -P * C)
						})
					}
					j.removeClass(K).eq(p).addClass(K),
					M = p,
					y || (g.removeClass("nextStop"), f.removeClass("prevStop"), 0 == p && f.addClass("prevStop"), p == k - 1 && g.addClass("nextStop")),
					h.html("<span>" + (p + 1) + "</span>/" + k)
				}
			};
			A && ab(!0),
			B && d.hover(function() {
				clearTimeout(J)
			},
			function() {
				J = setTimeout(function() {
					p = N,
					A ? ab() : "slideDown" == e ? Q.slideUp(q, U) : Q.animate({
						opacity: "hide"
					},
					q, U),
					M = p
				},
				300)
			});
			var bb = function(a) {
				H = setInterval(function() {
					w ? p--:p++,
					ab()
				},
				a ? a: r)
			},
			cb = function(a) {
				H = setInterval(ab, a ? a: r)
			},
			db = function() {
				z || (clearInterval(H), bb())
			},
			eb = function() { (y || p != k - 1) && (p++, ab(), R || db())
			},
			fb = function() { (y || 0 != p) && (p--, ab(), R || db())
			},
			gb = function() {
				clearInterval(H),
				R ? cb() : bb(),
				i.removeClass("pauseState")
			},
			hb = function() {
				clearInterval(H),
				i.addClass("pauseState")
			};
			if (v ? R ? (w ? p--:p++, cb(), z && l.hover(hb, gb)) : (bb(), z && d.hover(hb, gb)) : (R && (w ? p--:p++), i.addClass("pauseState")), i.click(function() {
				i.hasClass("pauseState") ? gb() : hb()
			}), "mouseover" == c.trigger ? j.hover(function() {
				var a = j.index(this);
				I = setTimeout(function() {
					p = a,
					ab(),
					db()
				},
				c.triggerTime)
			},
			function() {
				clearTimeout(I)
			}) : j.click(function() {
				p = j.index(this),
				ab(),
				db()
			}), R) {
				if (g.mousedown(eb), f.mousedown(fb), y) {
					var ib, jb = function() {
						ib = setTimeout(function() {
							clearInterval(H),
							cb(0 ^ r / 10)
						},
						150)
					},
					kb = function() {
						clearTimeout(ib),
						clearInterval(H),
						cb()
					};
					g.mousedown(jb),
					g.mouseup(kb),
					f.mousedown(jb),
					f.mouseup(kb)
				}
				"mouseover" == c.trigger && (g.hover(eb,
				function() {}), f.hover(fb,
				function() {}))
			} else g.click(eb),
			f.click(fb)
		})
	}
} (jQuery),
jQuery.easing.jswing = jQuery.easing.swing,
jQuery.extend(jQuery.easing, {
	def: "easeOutQuad",
	swing: function(a, b, c, d, e) {
		return jQuery.easing[jQuery.easing.def](a, b, c, d, e)
	},
	easeInQuad: function(a, b, c, d, e) {
		return d * (b /= e) * b + c
	},
	easeOutQuad: function(a, b, c, d, e) {
		return - d * (b /= e) * (b - 2) + c
	},
	easeInOutQuad: function(a, b, c, d, e) {
		return (b /= e / 2) < 1 ? d / 2 * b * b + c: -d / 2 * (--b * (b - 2) - 1) + c
	},
	easeInCubic: function(a, b, c, d, e) {
		return d * (b /= e) * b * b + c
	},
	easeOutCubic: function(a, b, c, d, e) {
		return d * ((b = b / e - 1) * b * b + 1) + c
	},
	easeInOutCubic: function(a, b, c, d, e) {
		return (b /= e / 2) < 1 ? d / 2 * b * b * b + c: d / 2 * ((b -= 2) * b * b + 2) + c
	},
	easeInQuart: function(a, b, c, d, e) {
		return d * (b /= e) * b * b * b + c
	},
	easeOutQuart: function(a, b, c, d, e) {
		return - d * ((b = b / e - 1) * b * b * b - 1) + c
	},
	easeInOutQuart: function(a, b, c, d, e) {
		return (b /= e / 2) < 1 ? d / 2 * b * b * b * b + c: -d / 2 * ((b -= 2) * b * b * b - 2) + c
	},
	easeInQuint: function(a, b, c, d, e) {
		return d * (b /= e) * b * b * b * b + c
	},
	easeOutQuint: function(a, b, c, d, e) {
		return d * ((b = b / e - 1) * b * b * b * b + 1) + c
	},
	easeInOutQuint: function(a, b, c, d, e) {
		return (b /= e / 2) < 1 ? d / 2 * b * b * b * b * b + c: d / 2 * ((b -= 2) * b * b * b * b + 2) + c
	},
	easeInSine: function(a, b, c, d, e) {
		return - d * Math.cos(b / e * (Math.PI / 2)) + d + c
	},
	easeOutSine: function(a, b, c, d, e) {
		return d * Math.sin(b / e * (Math.PI / 2)) + c
	},
	easeInOutSine: function(a, b, c, d, e) {
		return - d / 2 * (Math.cos(Math.PI * b / e) - 1) + c
	},
	easeInExpo: function(a, b, c, d, e) {
		return 0 == b ? c: d * Math.pow(2, 10 * (b / e - 1)) + c
	},
	easeOutExpo: function(a, b, c, d, e) {
		return b == e ? c + d: d * ( - Math.pow(2, -10 * b / e) + 1) + c
	},
	easeInOutExpo: function(a, b, c, d, e) {
		return 0 == b ? c: b == e ? c + d: (b /= e / 2) < 1 ? d / 2 * Math.pow(2, 10 * (b - 1)) + c: d / 2 * ( - Math.pow(2, -10 * --b) + 2) + c
	},
	easeInCirc: function(a, b, c, d, e) {
		return - d * (Math.sqrt(1 - (b /= e) * b) - 1) + c
	},
	easeOutCirc: function(a, b, c, d, e) {
		return d * Math.sqrt(1 - (b = b / e - 1) * b) + c
	},
	easeInOutCirc: function(a, b, c, d, e) {
		return (b /= e / 2) < 1 ? -d / 2 * (Math.sqrt(1 - b * b) - 1) + c: d / 2 * (Math.sqrt(1 - (b -= 2) * b) + 1) + c
	},
	easeInElastic: function(a, b, c, d, e) {
		var f = 1.70158,
		g = 0,
		h = d;
		if (0 == b) return c;
		if (1 == (b /= e)) return c + d;
		if (g || (g = .3 * e), h < Math.abs(d)) {
			h = d;
			var f = g / 4
		} else var f = g / (2 * Math.PI) * Math.asin(d / h);
		return - (h * Math.pow(2, 10 * (b -= 1)) * Math.sin((b * e - f) * 2 * Math.PI / g)) + c
	},
	easeOutElastic: function(a, b, c, d, e) {
		var f = 1.70158,
		g = 0,
		h = d;
		if (0 == b) return c;
		if (1 == (b /= e)) return c + d;
		if (g || (g = .3 * e), h < Math.abs(d)) {
			h = d;
			var f = g / 4
		} else var f = g / (2 * Math.PI) * Math.asin(d / h);
		return h * Math.pow(2, -10 * b) * Math.sin((b * e - f) * 2 * Math.PI / g) + d + c
	},
	easeInOutElastic: function(a, b, c, d, e) {
		var f = 1.70158,
		g = 0,
		h = d;
		if (0 == b) return c;
		if (2 == (b /= e / 2)) return c + d;
		if (g || (g = e * .3 * 1.5), h < Math.abs(d)) {
			h = d;
			var f = g / 4
		} else var f = g / (2 * Math.PI) * Math.asin(d / h);
		return 1 > b ? -.5 * h * Math.pow(2, 10 * (b -= 1)) * Math.sin((b * e - f) * 2 * Math.PI / g) + c: .5 * h * Math.pow(2, -10 * (b -= 1)) * Math.sin((b * e - f) * 2 * Math.PI / g) + d + c
	},
	easeInBack: function(a, b, c, d, e, f) {
		return void 0 == f && (f = 1.70158),
		d * (b /= e) * b * ((f + 1) * b - f) + c
	},
	easeOutBack: function(a, b, c, d, e, f) {
		return void 0 == f && (f = 1.70158),
		d * ((b = b / e - 1) * b * ((f + 1) * b + f) + 1) + c
	},
	easeInOutBack: function(a, b, c, d, e, f) {
		return void 0 == f && (f = 1.70158),
		(b /= e / 2) < 1 ? d / 2 * b * b * (((f *= 1.525) + 1) * b - f) + c: d / 2 * ((b -= 2) * b * (((f *= 1.525) + 1) * b + f) + 2) + c
	},
	easeInBounce: function(a, b, c, d, e) {
		return d - jQuery.easing.easeOutBounce(a, e - b, 0, d, e) + c
	},
	easeOutBounce: function(a, b, c, d, e) {
		return (b /= e) < 1 / 2.75 ? d * 7.5625 * b * b + c: 2 / 2.75 > b ? d * (7.5625 * (b -= 1.5 / 2.75) * b + .75) + c: 2.5 / 2.75 > b ? d * (7.5625 * (b -= 2.25 / 2.75) * b + .9375) + c: d * (7.5625 * (b -= 2.625 / 2.75) * b + .984375) + c
	},
	easeInOutBounce: function(a, b, c, d, e) {
		return e / 2 > b ? .5 * jQuery.easing.easeInBounce(a, 2 * b, 0, d, e) + c: .5 * jQuery.easing.easeOutBounce(a, 2 * b - e, 0, d, e) + .5 * d + c
	}
});
//banner.js
;
jQuery('.indexBanner').slide({
	mainCell: ".bd ul",
	titCell: ".hd ul",
	autoPage: "<li></li>",
	autoPlay: true,
	effect: "fold",
	interTime: 5000,
	delayTime: 1500,
	easing: "easeInSine",
	mouseOverStop: false,
	startFun: function(i, c, s, t, m) {
		var previ = (i + 1 <= c && i + 1 > 1) ? i - 1 : c - 1,
		nexti = (i + 1 < c) ? i + 1 : 0,
		oLi = m.find('li');
		if (i === 0) {
			oLi.eq(previ).find('.pic1').stop(true, true).animate({
				'marginLeft': '500px',
				'top': '170px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'marginLeft': '150px',
				'opacity': 0
			},
			800).next('.pic3').delay(500).animate({
				'marginLeft': '150px',
				'opacity': 0
			},
			800).next('.pic4').delay(500).animate({
				'marginLeft': '0px',
				'opacity': 0
			},
			800);
			oLi.eq(nexti).find('.pic1').stop(true, true).animate({
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				marginLeft: '75px',
				'opacity': 0
			},
			800).next('.pic3').delay(500).animate({
				marginLeft: '75px',
				'opacity': 0
			},
			800).next('.pic4').delay(500).animate({
				marginLeft: '0px',
				'opacity': 0
			},
			800);
			oLi.eq(i).find('.pic1').css({
				'marginLeft': '50px',
				'top': '-50px',
				'opacity': 0
			}).next('.pic2').css({
				'top': 0,
				'opacity': 0
			}).next('.pic3').css({
				'top': 0,
				'opacity': 0
			}).next('.pic4').css({
				'top': 0,
				'opacity': 0
			})
		}
		if (i == 1) {
			oLi.eq(previ).find('.pic1').stop(true, true).animate({
				'marginLeft': '50px',
				'top': '-50px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'top': 0,
				'opacity': 0
			},
			800);
			oLi.eq(nexti).find('.pic1').stop(true, true).animate({
				marginLeft: '-600px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				marginLeft: '200px',
				top: '120px',
				'opacity': 0
			},
			800).next('.pic3').delay(500).animate({
				marginLeft: '200px',
				top: '120px',
				'opacity': 0
			},
			800).next('.pic4').delay(500).animate({
				marginLeft: '200px',
				top: '120px',
				'opacity': 0
			},
			800);
			oLi.eq(i).find('.pic1').css({
				'opacity': 0
			}).next('.pic2').css({
				marginLeft: '75px',
				'opacity': 0
			}).next('.pic3').css({
				marginLeft: '75px',
				'opacity': 0
			}).next('.pic4').css({
				marginLeft: '75px',
				'opacity': 0
			})
		}
		if (i == 2) {
			oLi.eq(previ).find('.pic1').stop(true, true).animate({
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'opacity': 0
			},
			800);
			oLi.eq(nexti).find('.pic1').stop(true, true).animate({
				'bottom': '-470px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'top': 0,
				'opacity': 0
			},
			800);
			oLi.eq(i).find('.pic1').css({
				marginLeft: '-600px',
				'opacity': 0
			}).next('.pic2').css({
				marginLeft: '200px',
				top: '120px',
				'opacity': 0
			})
		}
		if (i == 3) {
			oLi.eq(previ).find('.pic1').stop(true, true).animate({
				marginLeft: '-600px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				marginLeft: '200px',
				top: '120px',
				'opacity': 0
			},
			800);
			oLi.eq(nexti).find('.pic1').stop(true, true).animate({
				'top': '-300px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'marginLeft': '150px',
				'opacity': 0
			},
			800);
			oLi.eq(i).find('.pic1').css({
				'bottom': '-470px',
				'opacity': 0
			}).next('.pic2').css({
				'top': 0,
				'opacity': 0
			})
		}
		if (i == 4) {
			oLi.eq(previ).find('.pic1').stop(true, true).animate({
				'bottom': '-470px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'top': 0,
				'opacity': 0
			},
			800);
			oLi.eq(nexti).find('.pic1').stop(true, true).animate({
				'marginLeft': '50px',
				'top': '-50px',
				'opacity': 0
			},
			600).next('.pic2').delay(500).animate({
				'top': 0,
				'opacity': 0
			},
			800);
			oLi.eq(i).find('.pic1').css({
				'marginLeft': '-525px',
				'top': '-300px',
				'opacity': 0
			}).next('.pic2').css({
				'marginLeft': '150px',
				'opacity': 0
			})
		}
	},
	endFun: function(i, c, s, t, m) {
		var oLi = m.find('li');
		if (i === 0) {
			oLi.eq(0).find('.pic1').stop(true, true).animate({
				marginLeft: '-251px',
				top: '80px',
				opacity: 1
			},
			800);
			oLi.eq(0).find('.pic2').stop(true, true).animate({
				marginLeft: '-223px',
				top: '140px',
				opacity: 1
			},
			800);
			oLi.eq(0).find('.pic3').stop(true, true).animate({
				marginLeft: '-88px',
				top: '180px',
				opacity: 1
			},
			800);
			oLi.eq(0).find('.pic4').stop(true, true).animate({
				marginLeft: '-237px',
				top: '275px',
				opacity: 1
			},
			800)
		}
		if (i === 1) {
			oLi.eq(i).find('.pic1').stop(true, true).animate({
				marginLeft: '-251px',
				top: '80px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic2').stop(true, true).animate({
				marginLeft: '-223px',
				top: '140px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic3').stop(true, true).animate({
				marginLeft: '-88px',
				top: '180px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic4').stop(true, true).animate({
				marginLeft: '-237px',
				top: '275px',
				opacity: 1
			},
			800)
		}
		if (i === 2) {
			oLi.eq(i).find('.pic1').stop(true, true).animate({
				marginLeft: '-251px',
				top: '80px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic2').stop(true, true).animate({
				marginLeft: '-223px',
				top: '140px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic3').stop(true, true).animate({
				marginLeft: '-88px',
				top: '180px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic4').stop(true, true).animate({
				marginLeft: '-237px',
				top: '275px',
				opacity: 1
			},
			800)
		}
		if (i === 3) {
			oLi.eq(i).find('.pic1').stop(true, true).animate({
				marginLeft: '-251px',
				top: '80px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic2').stop(true, true).animate({
				marginLeft: '-223px',
				top: '140px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic3').stop(true, true).animate({
				marginLeft: '-88px',
				top: '180px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic4').stop(true, true).animate({
				marginLeft: '-237px',
				top: '275px',
				opacity: 1
			},
			800)
		}
		if (i === 4) {
			oLi.eq(i).find('.pic1').stop(true, true).animate({
				marginLeft: '-251px',
				top: '80px',
				opacity: 1
			},
			800, 'easeOutBounce');
			oLi.eq(i).find('.pic2').stop(true, true).animate({
				marginLeft: '-223px',
				top: '140px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic3').stop(true, true).animate({
				marginLeft: '-88px',
				top: '180px',
				opacity: 1
			},
			800);
			oLi.eq(i).find('.pic4').stop(true, true).animate({
				marginLeft: '-237px',
				top: '275px',
				opacity: 1
			},
			800)
		}
	}
});
//xsth.js
;
$(function() {
	var interval = 1000;
	function ShowCountDown(year, month, day, divname) {
		var now = new Date();
		var endDate = new Date(year, month - 1, day);
		var leftTime = endDate.getTime() - now.getTime();
		var leftsecond = parseInt(leftTime / 1000);
		var day1 = Math.floor(leftsecond / (60 * 60 * 24));
		var hour = Math.floor((leftsecond - day1 * 24 * 60 * 60) / 3600);
		var minute = Math.floor((leftsecond - day1 * 24 * 60 * 60 - hour * 3600) / 60);
		var second = Math.floor(leftsecond - day1 * 24 * 60 * 60 - hour * 3600 - minute * 60);
		var cc = document.getElementById(divname);
		cc.innerHTML = "倒计时：" + day1 + "天 " + hour + "：" + minute + "：" + second + ""
	}
	var year = document.getElementById("activity_year") == null ? "": document.getElementById("activity_year").value;
	var month = document.getElementById("activity_month") == null ? "": document.getElementById("activity_month").value;
	var day = document.getElementById("activity_day") == null ? "": document.getElementById("activity_day").value;
	var num = document.getElementById("activity_list_size") == null ? "": document.getElementById("activity_list_size").value;
	for (var i = 1; i <= num; i++) {
		jisuanTime(year, month, i)
	}
	function jisuanTime(year, month, index) {
		window.setInterval(function() {
			ShowCountDown(year, parseInt(month), day, 'djsdown' + index)
		},
		interval)
	}
});