<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?php
global $options;
foreach ($options as $value) {
    if (get_settings( $value['id'] ) === FALSE) { $$value['id'] = $value['std']; } else { $$value['id'] = get_settings( $value['id'] ); }
}
$cat1 = wp_list_categories('echo=0&show_count=0&title_li=&number=1');
$cat1 = strip_tags($cat1,'<a>');
$cat1 = str_replace(array('Categories'), '<li>', $cat1);
$cat1 = str_replace(array('</a>'), '</a>', $cat1);
$cat1 = str_replace(array('<a'), '<a', $cat1);
$catmax = $mcp_number_categories-1;
$pagesmax = $mcp_number_pages;
$excats = $mcp_exclude_cats;
$expages = $mcp_exclude_pages;
$catarguments = array( 'show_option_all' => '', 'orderby' => 'name', 'order' => 'ASC','show_last_update' => 0,'style' => 'list', 'show_count' => 0, 'hide_empty' => 1, 'use_desc_for_title' => 1, 'child_of' => 0, 'feed' => '', 'feed_type'  => '', 'feed_image' => '', 'exclude'  => $excats, 'exclude_tree'       => '', 'include' => '', 'current_category' => 0, 'hierarchical' => false, 'title_li' => __( '' ), 'number' => $catmax, 'echo' => 1, 'offset' => 1, 'depth' => 1 );
$pagesargs = array( 'depth' => 1, 'show_date' => '', 'date_format' => get_option('date_format'), 'child_of' => 0, 'exclude' => $expages, 'include' => '', 'title_li' => __(''), 'echo' => 1, 'authors' => '', 'sort_column' => 'post_title', 'link_before' => '', 'link_after' => '', 'number' => $pagesmax, 'exclude_tree' => '' );
?>

<head profile="http://gmpg.org/xfn/11">
	<meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />
	<link rel="shortcut icon" href="<?php bloginfo('template_directory'); ?>/favicon.ico" />
	<link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="screen" />
	<link rel="alternate" type="application/rss+xml" title="<?php bloginfo('name'); ?> RSS 2.0" href="<?php bloginfo('rss2_url'); ?>" />
	<link rel="alternate" type="application/atom+xml" title="<?php bloginfo('name'); ?> Atom 0.3" href="<?php bloginfo('atom_url'); ?>" />
	<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
	<title><?php wp_title('|', true, 'right'); ?> <?php bloginfo('name'); ?></title>
	<link rel="shortcut icon" href="<?php bloginfo('template_url'); ?>/images/favicon.ico" type="image/x-icon" />
	<link rel="icon" href="<?php bloginfo('template_url'); ?>/images/favicon.ico" type="image/x-icon" />
    <?php if ( is_singular() ) wp_enqueue_script( 'comment-reply' ); ?>
    <style type="text/css">
    .description, .postindex, .postsingle, #sidebar, .pages, #footer, textarea, input#author, input#email, input#url { font-family: <?php echo $mcp_font_family; ?>; }
    </style>
    <?php wp_head(); ?>
</head>

<?php if ($mcp_custom_background == "false") { ?>
<body>
<?php } else { ?>
<body style="background:url(<?php bloginfo('template_directory'); ?>/background/<?php echo $mcp_background_img; ?>) top left no-repeat #fff;">
<?php } ?>
<div class="menuwrap">
	 <div class="menubar">
	       <ul class="pagelist">
	       <?php wp_list_pages( $pagesargs ); ?>
	       </ul>
	       <ul class="subscribe">
	       <li><strong><a href="<?php bloginfo('rss2_url'); ?>" rel="alternate" type="application/rss+xml"><img class="sub-button" src="<?php bloginfo('template_directory'); ?>/images/feedicon.png" alt="" />Subscribe to the Blog</a></strong></li>
	       </ul>
	 </div>
</div> <!-- end of #menuwrap -->

<div id="outline">
	<div id="header">
	     <div class="logo">
         <?php if ($mcp_enable_logo == "false") { ?>
         <h1 class="blogtitle">{<a href="<?php bloginfo('url'); ?>/"><?php bloginfo('name'); ?></a>}</h1>
         <?php } else { ?>
         <a href="<?php bloginfo('url'); ?>/"><img src="<?php bloginfo('template_directory'); ?>/logo/<?php echo $mcp_logo_url; ?>" alt="<?php bloginfo('name'); ?>" /></a>
         <?php } ?>
         <p class="description"><?php bloginfo('description'); ?></p>
         </div>
	</div> <!-- end of #header -->

<div class="left-category-bar">
</div>
<div class="category-bar">
     <div class="cat-menubar">
          <ul class="menu">
          <li class="start"><?php echo $cat1; ?></li>
          <?php wp_list_categories( $catarguments ); ?> 
          <li class="end">&nbsp;</li>
	      </ul>
          <?php if ($mcp_disable_search == "false") { ?>
	      <ul class="search">
	      <li>
	      <form method="get" action="<?php bloginfo('home'); ?>/">
          <p><input class="searchbox" type="text" value="Search here..." onfocus="if(this.value==this.defaultValue)this.value='';" onblur="if(this.value=='')this.value=this.defaultValue;" name="s" /></p>
          </form>
          </li>
	      </ul>
          <?php } else { ?>
          <?php } ?> 
	 </div>
</div> <!-- end of #category-bar -->
<div class="right-category-bar">
</div>

<div id="wrapper">