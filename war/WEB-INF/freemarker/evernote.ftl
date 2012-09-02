<#import "/spring.ftl" as spring>

<#include "header.ftl">

<#escape x as x?html>
<div id="content">
    <script type="text/javascript">
        function confirmBeforePost(guid) {
            var note = document.getElementById(guid);
            if(note && confirm("确认发布文章《"+note.innerHTML+"》?")) {
                window.location.href='/admin/post_evernote_note.html?guid='+guid;
            }
        }
    </script>
    <div class="postsingle">
            <#list notes as note>
            <div>
                <a href='javascript:void(0)' onclick="confirmBeforePost('${note.guid}');" id="${note.guid}">${note.title}</a>
            </div>
            </#list>
    </div>

</div> <!-- end of #content -->
</#escape>

<#include "sidebar.ftl">

<#include "footer.ftl">