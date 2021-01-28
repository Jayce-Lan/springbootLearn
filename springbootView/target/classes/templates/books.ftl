<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <#if books ??&&(books?size>0)>
        <#list books as book>
            <p>图书编号：${book.id}</p>
            <p>图书名称：${book.name}</p>
            <p>图书作者：${book.author}</p>
        </#list>
    </#if>
</body>
</html>