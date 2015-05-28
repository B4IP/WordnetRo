<?php require_once('_header.php'); ?>
<div class="header-intro" >
		<div class="header-info">

			<h1 class="header-title">Salut, bun venit in wordnet-ul romanesc.</h1>
			<h2 class="header-subtitle">
				Introdu un cuvant pentru a afla mai multe informatii despre el.
			</h2>
			<form action="?page=afisare" method="post" enctype="multipart/form-data">
			<input name="search" class="search-input" type="text" placeholder="Afla mai multe despre...">
			<input id="searchsubmit" class="submit-button"type="submit" value="Descopera">
			</form>
		</div>
	</div>
<?php require_once('_footer.php'); ?>

