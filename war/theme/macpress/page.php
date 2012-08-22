<?php get_header(); ?>
<div id="content">
<?php
global $options;
foreach ($options as $value) {
    if (get_settings( $value['id'] ) === FALSE) { $$value['id'] = $value['std']; } else { $$value['id'] = get_settings( $value['id'] ); }
}
?>
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
			<div class="postsingle" id="post-<?php the_ID(); ?>">
	        <h2 class="posttitle" id="post-<?php the_ID(); ?>"><?php the_title(); ?></h2>
			<div class="postentry">
			<?php the_content(); ?>
		    <?php wp_link_pages(); ?>
            </div>
	        </div>
	        <?php if ( comments_open() && $mcp_remove_comments == "false" ) { ?>
            <div class="postsingle">
            <?php comments_template('', true); ?>
		    </div>
		    <?php } else { ?>
		    <?php } ?>
<?php endwhile; else : ?>

			<div class="postsingle" id="post-<?php the_ID(); ?>">
			<h2 class="posttitle">
			<?php echo "Oops! Nothing Found" ?>
			</h2>
			<div class="postentry">
            <p>Sorry but something you are looking for either does not exist or has been moved or deleted.</p>
            <p>It is advised to use the search bar to find the required content. Thanks!</p>
            </div>
			</div>
			
<?php endif; ?>
</div> <!-- end of #content -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>