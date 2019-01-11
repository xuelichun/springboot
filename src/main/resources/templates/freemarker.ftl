<#if list??>
    <#list list as chartType>
    "${chartType}"
        <#if chartType_has_next>,</#if>
    </#list>
</#if>