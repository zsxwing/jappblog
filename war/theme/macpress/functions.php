<?php
if ( function_exists('register_sidebar') )
	register_sidebar(array(
        'name' => 'MacPress Sidebar',
        'before_widget' => '<li id="%1$s" class="widget %2$s">',
        'after_widget' => '</li>',
        'before_title' => '<h2>',
        'after_title' => '</h2>',
    ));
?>
<?php
function list_pings($comment, $args, $depth) {
       $GLOBALS['comment'] = $comment;
?>
        <li id="comment-<?php comment_ID(); ?>"><?php comment_author_link(); ?>
<?php } ?>
<?php remove_filter('the_content', 'wptexturize'); ?>
<?php
function check_referrer() {
    if (!isset($_SERVER['HTTP_REFERER']) || $_SERVER['HTTP_REFERER'] == “”) {
        wp_die( __('Please enable referrers in your browser, or, if you\'re a spammer, bugger off!') );
    }
}

add_action('check_comment_flood', 'check_referrer');

/*Start of Theme Options*/

$themename = "MacPress";
$shortname = "mcp";
$options = array (
 
array( "type" => "open"),

array( "name" => "General",
	"type" => "title"),

array(  "name" => "Font family",
        "desc" => "Choose the Font Family for the blog theme.",
        "id" => $shortname."_font_family",
        "type" => "select",
        "std" => "Helvetica, Arial, sans-serif",
        "options" => array("Helvetica, Arial, sans-serif", "Georgia, serif")),

array( "type" => "close"),
 
array( "type" => "open"),

array( "name" => "Features",
	"type" => "title"),

array(  "name" => "Enable logo image?",
        "desc" => "Checking this box will enable the logo, replacing the blog title in text.",
        "id" => $shortname."_enable_logo",
        "type" => "checkbox",
        "std" => "false"),

array( "name" => "Logo file name",
	"desc" => "Please upload the logo in the 'logo' folder within the theme and enter the name + extension here.",
	"id" => $shortname."_logo_url",
	"type" => "text",
	"std" => ""),

array(  "name" => "Custom background?",
        "desc" => "Checking this box will enable a custom background image, replacing the default one.",
        "id" => $shortname."_custom_background",
        "type" => "checkbox",
        "std" => "false"),

array( "name" => "Background file name",
	"desc" => "Please upload the background in the 'backgrond' folder within the theme and enter the name + extension here.",
	"id" => $shortname."_background_img",
	"type" => "text",
	"std" => ""),
	
array(  "name" => "Remove search-bar?",
        "desc" => "Checking this box will remove the search form from the navigation bar.",
        "id" => $shortname."_disable_search",
        "type" => "checkbox",
        "std" => "false"),

array( "name" => "No. of pages in navigation",
	"desc" => "How many pages do you want to be displayed in the top navigation bar? Enter a number.",
	"id" => $shortname."_number_pages",
	"type" => "text",
	"std" => "4"),
	
array( "name" => "Exclude pages from navigation",
	"desc" => "Enter the Page ID(s) in the format <code>4,11,29</code>.",
	"id" => $shortname."_exclude_pages",
	"type" => "text",
	"std" => ""),
        
array( "name" => "No. of categories in navigation",
	"desc" => "How many categories do you want to be displayed in the navigation bar? Enter a number.",
	"id" => $shortname."_number_categories",
	"type" => "text",
	"std" => "4"),
	
array( "name" => "Exclude categories from navigation",
	"desc" => "Enter the category ID(s) in the format <code>4,11,29</code>.",
	"id" => $shortname."_exclude_cats",
	"type" => "text",
	"std" => ""),

array(  "name" => "Disable comments on pages?",
        "desc" => "Checking this box will remove the comments system from pages.",
        "id" => $shortname."_remove_comments",
        "type" => "checkbox",
        "std" => "false"),
	
array( "type" => "close")
 
);

function mytheme_add_admin() {
 
global $themename, $shortname, $options;
 
if ( $_GET['page'] == basename(__FILE__) ) {
 
if ( 'save' == $_REQUEST['action'] ) {
 
foreach ($options as $value) {
update_option( $value['id'], $_REQUEST[ $value['id'] ] ); }
 
foreach ($options as $value) {
if( isset( $_REQUEST[ $value['id'] ] ) ) { update_option( $value['id'], $_REQUEST[ $value['id'] ]  ); } else { delete_option( $value['id'] ); } }
 
header("Location: themes.php?page=functions.php&saved=true");
die;
 
} else if( 'reset' == $_REQUEST['action'] ) {
 
foreach ($options as $value) {
delete_option( $value['id'] ); }
 
header("Location: themes.php?page=functions.php&reset=true");
die;
 
}
}
 
add_theme_page($themename." Options", "".$themename." Options", 'edit_themes', basename(__FILE__), 'mytheme_admin');
 
}
 
function mytheme_admin() {
 
global $themename, $shortname, $options;
 
if ( $_REQUEST['saved'] ) echo '<div id="message" class="updated fade"><p><strong>'.$themename.' settings saved.</strong></p></div>';
if ( $_REQUEST['reset'] ) echo '<div id="message" class="updated fade"><p><strong>'.$themename.' settings reset.</strong></p></div>';
 
?>
<div class="wrap">
<h2><?php echo $themename; ?> Settings</h2>
<div id="poststuff" class="metabox-holder">
<form method="post">
 
<?php foreach ($options as $value) {
switch ( $value['type'] ) {
 
case "open":
?>
<div class="stuffbox">
<?php break;
 
case "close":
?>
</table>
</div> 
</div>
 
<?php break;
 
case "title":
?>
<h3><label for="link_url"><?php echo $value['name']; ?></label></h3>
<div class="inside">
<table width="100%" border="0" style="padding:10px;">
<?php break;
 
case 'text':
?>
 
<tr><td width="25%" rowspan="2" valign="middle"><strong style="font-size:12px;"><?php echo $value['name']; ?></strong></td>
<td width="75%"><input style="width:300px;" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>" type="<?php echo $value['type']; ?>" value="<?php if ( get_settings( $value['id'] ) != "") { echo get_settings( $value['id'] ); } else { echo $value['std']; } ?>" /></td>
</tr><tr><td><small><?php echo $value['desc']; ?></small></td>
</tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px solid #E1E1E1;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>
 
<?php
break;
 
case 'textarea':
?>
 
<tr>
<td width="20%" rowspan="2" valign="middle"><strong><?php echo $value['name']; ?></strong></td>
<td width="80%"><textarea name="<?php echo $value['id']; ?>" style="width:400px; height:120px;" type="<?php echo $value['type']; ?>" cols="" rows=""><?php if ( get_settings( $value['id'] ) != "") { echo get_settings( $value['id'] ); } else { echo $value['std']; } ?></textarea></td>
 
</tr>
 
<tr>
<td><small><?php echo $value['desc']; ?></small></td>
</tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px dotted #000000;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>
 
<?php
break;
 
case 'select':
?>
<tr>
<td width="25%" rowspan="2" valign="middle"><strong style="font-size:12px;"><?php echo $value['name']; ?></strong></td>
<td width="75%"><select style="width:200px;" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>"><?php foreach ($value['options'] as $option) { ?><option<?php if ( get_option( $value['id'] ) == $option) { echo ' selected="selected"'; } elseif ($option == $value['std']) { echo ' selected="selected"'; } ?> value="<?php echo $option; ?>"><?php echo $option; ?></option><?php } ?></select></td>
</tr><tr><td><small><?php echo $value['desc']; ?></small></td>
</tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px solid #E1E1E1;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>
 
<?php
break;
 
case "checkbox":
?>
<tr>
<td width="25%" rowspan="2" valign="middle"><strong style="font-size:12px;"><?php echo $value['name']; ?></strong></td>
<td width="75%"><?php if(get_option($value['id'])){ $checked = "checked=\"checked\""; }else{ $checked = ""; } ?>
<input type="checkbox" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>" value="true" <?php echo $checked; ?> />   <small><?php echo $value['desc']; ?></small>
</td></tr><tr></tr><tr><td colspan="2" style="margin-bottom:5px;border-bottom:1px solid #E1E1E1;">&nbsp;</td></tr><tr><td colspan="2">&nbsp;</td></tr>
 
<?php break;
 
}
}
?>
<p class="submit">
<input name="save" type="submit" value="Save changes" class="button-primary" />
<input type="hidden" name="action" value="save" />
</p>
</form>
<form method="post">
<p class="submit">
<input name="reset" type="submit" value="Reset" />
<input type="hidden" name="action" value="reset" />
</p>
</form>
 
<?php
}

add_action('admin_menu', 'mytheme_add_admin');
?>