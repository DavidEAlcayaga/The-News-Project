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

        <h1>Register News</h1>
        <!-- validation, error messages and news entered correctly. -->
        @if($errors -> any())

            <div class="alert alert-danger">

                <ul>
                    @foreach($errors-> all() as $error)
                        <li>{{ $error }}</li>
                    @endforeach

                </ul>

            </div>

        @endif
        @if(session('message'))
            <div class="alert alert-success">

                <p>{{session('message')}}</p>

            </div>
    @endif

    <!-- form start -->
        <form action="registernews" method="POST">

            @csrf

            <div class="form-group">
                <label for="title" class="col-sm-2 col-form-label">Title</label>
                <div>
                    <input type="text" class="form-control" name="title" placeholder="Enter title"
                           value="{{old('title')}}">
                </div>

            </div>

            <div class="form-group">
                <label for="author" class="col-sm-2 col-form-label">Author</label>
                <div>
                    <input type="text" class="form-control" name="author" placeholder="Enter Author"
                           value="{{old('author')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url" class="col-sm-2 col-form-label">URL</label>
                <div>
                    <input type="text" class="form-control" name="url" placeholder="Enter the url of the news"
                           value="{{old('url')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url_image" class="col-sm-2 col-form-label">Url_Image</label>

                <div>
                    <input type="text" class="form-control" name="url_image"
                           placeholder="Enter the url of the image for the news" value="{{old('url_image')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 col-form-label">Description</label>
                <div>
                    <input type="text" class="form-control" name="description"
                           placeholder="Enter the description of the news" value="{{old('description')}}">

                </div>
            </div>

            <div class="form-group ">
                <label for="content" class="col-sm-2 col-form-label">Content</label>
                <div>
                    <textarea class="form-control" name="content" rows="5" cols="50"
                              value="{{old('description')}}"></textarea>
                </div>
                <small id="inputContent" class="form-text text-muted">Enter content for the news.</small>


            </div>
            <!-- date management -->
            <?php
            date_default_timezone_set('America/Santiago');
            $date_actual = date("d-m-Y");
            ?>
            <div class="form-group">
                <label for="date" class="col-sm-2 col-form-label">Published_at</label>
                <div>
                    <input type="text" class="form-control" name="date" type="datetime" value="<?= $date_actual?>">
                </div>
            </div>

            <div>
                <br/>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">Save News</button>
                </div>
            </div>
        </form>
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
