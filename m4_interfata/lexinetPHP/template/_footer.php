	
		

</body>
<script type="text/javascript">
	jQuery = $;
	$(window).on('load', function () {
		$('.header-info').css('transform', 'scale(1)')
		$('.header-info').css('opacity', '1')

		setTimeout(function(){ 
			$('.menu').css('top', 0);
			$('.menu').css('opacity', 1);
		},1000);

		setTimeout(function(){
			$('.search-input, .search-input-edit').css('opacity', '1');
			$('.search-input').css('margin-top', '30px');
		},1500)

		setTimeout(function(){
			$('.submit-button').css('opacity', '1');
			$('.submit-button').css('margin-top', '20px');
		},2000)
	})
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

	$('.submit-button').on('click', function(){
		    $('html, body').animate({
        scrollTop: $(window).height()
    }, 700);
	})

	$('.menu-buttons-item--login').on('click', function(){
		$('.initial-menu').css('margin-top','-100px')
		setTimeout(function(){
			$('.prompt-menu').css('display', 'initial');
		},200)
		setTimeout(function(){
			$('.prompt-menu').css('margin-top', '0');
		},750)
	})

	$('.log-out').on('click', function(){
		$('.prompt-menu').css('margin-top', '-100px');
		setTimeout(function(){
			$('.initial-menu').css('margin-top','0px')
			$('.prompt-menu').css('display', 'none');
		},750)
	})
</script>
</html>

