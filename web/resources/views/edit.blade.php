@extends('layouts.app')

@section('content')

<body>
<div class="container">

    <div class="col-sm-8">

        <h1>Edit News</h1>

    <!-- form start -->
        <form action="/edit" method="POST">

            @csrf
            <input type="hidden" name="id" value = "{{$newsEdit['id']}}">

            <div class="form-group">
                <label for="title" class="col-sm-2 col-form-label">Title</label>
                <div>
                    <!-- News title input box (required) -->
                    <input type="text" class="form-control" name="title" placeholder="Enter title"
                           value = "{{$newsEdit['title']}}">
                </div>

            </div>

            <div class="form-group">
                <label for="author" class="col-sm-2 col-form-label">Author</label>
                <div>
                    <!-- News author input box (required) -->
                    <input type="text" class="form-control" name="author" placeholder="Enter Author"
                           value = "{{$newsEdit['author']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="source" class="col-sm-2 col-form-label">Source</label>
                <div>
                    <!-- News author input box (required) -->
                    <input type="text" class="form-control" name="source" placeholder="Enter Source"
                           value = "{{$newsEdit['source']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url" class="col-sm-2 col-form-label">URL</label>
                <div>
                    <!-- News URL input box (required) -->
                    <input type="hidden" class="form-control" name="url" placeholder="Enter the url of the news"
                           value = "{{$newsEdit['url']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url_image" class="col-sm-2 col-form-label">Url_Image</label>

                <div>
                    <!-- News image URL input box (optional) -->
                    <input type="text" class="form-control" name="url_image"
                           placeholder="Enter the url of the image for the news" value = "{{$newsEdit['url_image']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 col-form-label">Description</label>
                <div>
                    <!-- News description input box (required) -->
                    <input type="text" class="form-control" name="description"
                           placeholder="Enter the description of the news" value = "{{$newsEdit['description']}}">

                </div>
            </div>

            <div class="form-group ">
                <label for="content" class="col-sm-6 col-form-label">Contenido de la noticia</label>
                <div>
                    <textarea id ="content" class="form-control"name="content" rows="6" cols="50">{{$newsEdit->content}}
                    </textarea>
                </div>

                <!-- News content input box (required) -->
                <small id="inputContent" class="form-text text-muted">Enter content for the news.</small>

                <input type="hidden" name="published_at" value = "{{$newsEdit['published_at']}}">

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
