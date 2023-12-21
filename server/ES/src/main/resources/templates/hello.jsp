<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<span th:text="${men}"></span>
</body>
<script>
    var a=[1,2,3]
    a.map((element)=>{
        console.log(`${element}`)
    })
    var men =`${men}`
</script>
</html>