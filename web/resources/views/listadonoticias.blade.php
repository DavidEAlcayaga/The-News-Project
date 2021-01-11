@extends('layouts.app')

@section('content')
@auth
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

    <div class="col-sm-16">

        <h1>List News</h1>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">Source</th>
                <th scope="col">URL</th>
                <th scope="col">Image URL</th>
                <th scope="col">Description</th>
                <th scope="col">Content</th>
                <th scope="col">Published At</th>
                <th scope="col">Operation</th>
            </tr>
            </thead>
            <tbody>
            @foreach($newsList as $news)
                <tr>
                    <th scope="row">{{$news['id']}}</th>
                    <td>{{$news['title']}}</td>
                    <td>{{$news['author']}}</td>
                    <td>{{$news['source']}}</td>
                    <td>{{$news['url']}}</td>
                    <td>{{$news['url_image']}}</td>
                    <td>{{$news['description']}}</td>
                    <td>{{$news['content']}}</td>
                    <td>{{$news['published_at']}}</td>
                    <td>
                        <a href="{{"edit/".$news['id']}}" class="btn btn-primary btn-sm active" role="button"
                           aria-pressed="true">Edit</a>
                        <a href="{{"delete/".$news['id']}}" class="btn btn-secondary btn-sm active" role="button"
                           aria-pressed="true">Delete</a>

                    </td>
                </tr>
            @endforeach
            </tbody>
        </table>
    </div>
</div>
</body>
</div>
</html>
@endauth
@endsection
