@section('content')

    <!doctype html>
<html>
<!-- Image and text navbar-->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img
                src="https://www.ucn.cl/wp-content/uploads/2018/05/Escudo-UCN-Full-Color.png"
                height="70"
                alt=""
                loading="lazy"
            />
            Universidad Cátolica del Norte
        </a>
    </div>
</nav>

<br/>

<head>
    <div class="container">
        <!-- Content here -->
        <br>
    </div>
    <meta charset="utf-8">
    <title>Register News </title>
    <meta name="description" content="from to register news">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <div class="col-sm-8">

        <h1>List News</h1>

        <table border = "1">
            <tr>
                <td>Id</td>
                <td>Title</td>
                <td>Author</td>
                <td>Source</td>
                <td>URL</td>
                <td>Image URL</td>
                <td>Description</td>
                <td>Content</td>
                <td>Published At</td>
                <td>Operation</td>
            </tr>
            @foreach($newsList as $news)
                <tr>
                    <td>{{$news['id']}}</td>
                    <td>{{$news['title']}}</td>
                    <td>{{$news['author']}}</td>
                    <td>{{$news['source']}}</td>
                    <td>{{$news['url']}}</td>
                    <td>{{$news['url_image']}}</td>
                    <td>{{$news['description']}}</td>
                    <td>{{$news['content']}}</td>
                    <td>{{$news['published_at']}}</td>
                    <td>
                        <a href={{"edit/".$news['id']}}>Edit</a>
                        <a href={{"delete/".$news['id']}}>Delete</a>
                    </td>
                </tr>
            @endforeach
        </table>
    </div>
</div>
</body>
</div>

<br/>
<br/>
<br/>

<!-- footer -->
<footer class="bg-light text-center text-lg-start">
    <!-- Grid container -->
    <div class="container p-4">
        <!--Grid row-->

        <div>
            <h5 class="text-uppercase">The News Project</h5>

            <p>
                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iste atque ea quis
                molestias. Fugiat pariatur maxime quis culpa corporis vitae repudiandae
                aliquam voluptatem veniam, est atque cumque eum delectus sint!
            </p>

            <!--Grid row-->
        </div>
        <!-- Grid container -->

        <!-- Copyright -->
        <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
            © 2020 Copyright:
            <a class="text-dark" href="https://mdbootstrap.com/">Desarrollo de soluciones móviles</a>
        </div>
        <!-- Copyright -->
</footer>
</html>
