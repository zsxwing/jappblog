<?php get_header(); ?>
<div id="content">
			<div class="postsingle" id="post-<?php the_ID(); ?>">
			<h2 class="posttitle">
			<?php echo "Oops! Nothing Found" ?>
			</h2>

			<div class="postentry">
            <p>Sorry but something you are looking for either does not exist or has been moved or deleted.</p>
            <p>It is advised to use the search bar to find the required content. Thanks!</p>
            </div>
			</div>
</div> <!-- end of #content -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>
