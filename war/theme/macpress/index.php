<?php get_header(); ?>
<div id="content">
<h2 class="latest">Latest Articles</h2>
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
			<div class="postindex" id="post-<?php the_ID(); ?>">
	        <h2 class="posttitle">
			<a href="<?php the_permalink() ?>" rel="bookmark" title="<?php echo "Permanent link to" ?> <?php the_title(); ?>"><?php the_title(); ?></a>
			</h2>
            <p class="postmeta"><?php echo "Posted by <strong>"?><?php the_author(); ?><?php echo "</strong> on "?><?php the_time('F j, Y'); ?></p>
            <p class="postcomments">{ <?php comments_number('No Comment','Only 1 Comment','% Comments'); ?> }</p>             
			<div class="postentry">
			<?php if (is_home()) { ?>
            <?php the_content('{ Read more... }'); ?>
            <?php } else { ?>
            <?php the_excerpt(); ?>
            <?php } ?>
            </div>
            <div class="metadata">
            <p class="categories">Posted in: <?php the_category(', '); ?></p>
            <p class="tags"><?php the_tags('Tags: '); ?></p>
            </div>
			</div>
<?php endwhile; ?>
		    <div class="pages">
            <div class="alignleft"><?php next_posts_link(__('&laquo; Older Entries','fusion')) ?></div>
            <div class="alignright"><?php previous_posts_link(__('Newer Entries &raquo;','fusion')) ?></div>
            <div class="clear"></div>
		    </div>
<?php else : ?>

			<div class="postindex" id="post-<?php the_ID(); ?>">
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