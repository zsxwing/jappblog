<?php get_header(); ?>
<div id="content">
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
			<div class="postsingle" id="post-<?php the_ID(); ?>">
		    <h2 class="posttitle"><span class="intitle"><?php the_title(); ?></span></h2>
            <p class="postmeta"><?php echo "Posted by <strong>" ?><?php the_author(); ?><?php echo "</strong> on "?><?php the_time('F j, Y'); ?> <?php edit_post_link('{ Edit }'); ?></p>
		    <div class="postentry">
		    <?php the_content(); ?>
		    <?php wp_link_pages(); ?>
            </div>
            <div class="metadata">
            <p class="categories">Posted in: <?php the_category(', '); ?></p>
            <p class="tags">Tags: <?php the_tags(', '); ?></p>
            </div>
            </div>
            <div class="postsingle">
            <?php comments_template('', true); ?>
		    </div>

<?php endwhile; else : ?>

			<div class="postsingle" id="post-<?php the_ID(); ?>">
		    <h2 class="posttitle">
			<?php echo "Oops! Nothing Found" ?>
			</h2>
			<div class="postentry">
            <p>Something you are looking for either does not exist or has been moved or deleted.</p>
            <p>Please search for the required content using the search bar in the sidebar or navigate through the blog using the archives.</p>
            </div>
			</div>
<?php endif; ?>
</div> <!-- end of #content -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>