	jQuery = $;
	$('.header-intro').css('height', $(window).height());
	$(window).on('resize', function(){
	$('.header-intro').css('height', $(window).height());		
	})
	$(window).scroll(function(){
		if($(window).scrollTop() >= $(window).height()) {
			$('.menu').css('background-color', 'rgba(84,87,129,0.9)');
		}
		else {
			$('.menu').css('background-color', 'transparent');
		}
	})