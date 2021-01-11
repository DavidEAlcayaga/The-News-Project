@extends('layouts.app')

@section('content')


    <!doctype html>
<html>
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
                    <!-- News title input box (required) -->
                    <input type="text" class="form-control" name="title" placeholder="Enter title"
                           value="{{old('title')}}">
                </div>

            </div>

            <div class="form-group">
                <label for="author" class="col-sm-2 col-form-label">Author</label>
                <div>
                    <!-- News author input box (required) -->
                    <input type="text" class="form-control" name="author" placeholder="Enter Author"
                           value="{{old('author')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="source" class="col-sm-2 col-form-label">Source</label>
                <div>
                    <!-- News author input box (required) -->
                    <input type="text" class="form-control" name="source" placeholder="Enter Source"
                           value="{{old('source')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url" class="col-sm-2 col-form-label">URL</label>
                <div>
                    <!-- News URL input box (required) -->
                    <input type="text" class="form-control" name="url" placeholder="Enter the url of the news"
                           value="{{old('url')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url_image" class="col-sm-2 col-form-label">Url_Image</label>

                <div>
                    <!-- News image URL input box (optional) -->
                    <input type="text" class="form-control" name="url_image"
                           placeholder="Enter the url of the image for the news" value="{{old('url_image')}}">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 col-form-label">Description</label>
                <div>
                    <!-- News description input box (required) -->
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
                <!-- News content input box (required) -->
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
</html>

@endsection
