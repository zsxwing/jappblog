<div id="sidebar">
<ul class="widgets">

<?php if ( !function_exists('dynamic_sidebar') || !dynamic_sidebar() ) : ?>

			<li class="widget widget_recent_entries">		
	        <h2>Recent Posts</h2>		
	        <ul>
            <?php $myposts = get_posts('numberposts=10');
            foreach($myposts as $post) : ?>
            <li><a href="<?php the_permalink(); ?>"><?php the_title();?></a></li>
            <?php endforeach; ?>
	        </ul>
	        </li>
		
<?php endif; ?>

</ul>
</div>