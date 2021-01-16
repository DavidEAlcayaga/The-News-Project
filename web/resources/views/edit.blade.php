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
                <label for="title" class="col-sm-2 col-form-label">Título</label>
                <div>
                    <!-- News title input box (required) -->
                    <input type="text" class="form-control" name="title" placeholder="Ingresar un título para la noticia"
                           value = "{{$newsEdit['title']}}">
                </div>

            </div>

            <div class="form-group">
                <label for="author" class="col-sm-2 col-form-label">Autor</label>
                <div>
                    <!-- News author input box (required) -->
                    <input type="text" class="form-control" name="author" placeholder="Ingresar el autor de la noticia"
                           value = "{{$newsEdit['author']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="source" class="col-sm-2 col-form-label">Fuente</label>
                <div>
                    <!-- News author input box (required) -->
                    <input type="text" class="form-control" name="source" placeholder="Ingresar la fuente de la noticia"
                           value = "{{$newsEdit['source']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url" class="col-sm-2 col-form-label">URL</label>
                <div>
                    <!-- News URL input box (required) -->
                    <input type="hidden" class="form-control" name="url" placeholder="Ingresar una url para la imagen"
                           value = "{{$newsEdit['url']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="url_image" class="col-sm-2 col-form-label">Url_Imagen</label>

                <div>
                    <!-- News image URL input box (optional) -->
                    <input type="text" class="form-control" name="url_image"
                           placeholder="Ingresar una url para la imagen de la noticia value = "{{$newsEdit['url_image']}}">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 col-form-label">Descripción</label>
                <div>
                    <!-- News description input box (required) -->
                    <input type="text" class="form-control" name="description"
                           placeholder="Ingresar una breve descripción de la noticia" value = "{{$newsEdit['description']}}">

                </div>
            </div>

            <div class="form-group ">
                <label for="content" class="col-sm-6 col-form-label">Contenido</label>
                <div>
                    <textarea id ="content" class="form-control"name="content" placeholder="Ingresar el contenido de la noticia" rows="6" cols="50">{{$newsEdit->content}}
                    </textarea>
                </div>



                <input type="hidden" name="published_at" value = "{{$newsEdit['published_at']}}">

                <br/>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</div>
</html>
@endsection
