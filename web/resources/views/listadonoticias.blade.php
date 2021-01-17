@extends('layouts.app')

@section('content')
    @auth
        <div class="container">
            <h1>Lista de Noticias </h1>
            <br>
        </div>
        <div>
            <div class="col-sm-25">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Título</th>
                        <th scope="col">Autor</th>
                        <th scope="col">Fuente</th>
                        <th scope="col">URL</th>
                        <th scope="col">Imagen URL</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Contenido</th>
                        <th scope="col">Fecha de publicación</th>
                        <th scope="col">Operación</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach($newsList as $news)
                        <tr>
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
                                   aria-pressed="true">Editar</a>
                                <a href="{{"delete/".$news['id']}}" class="btn btn-danger btn-sm active"
                                   onclick="return confirm('¿Está seguro de eliminar esta noticia?')"
                                   role="button"
                                   aria-pressed="true">Eliminar</a>
                            </td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>
                <div class="text-xs-center">
                    <ul class="pagination justify-content-center">
                        {{$newsList->onEachSide(5)->links()}}
                    </ul>
                </div>
            </div>
        </div>
        </div>
    @endauth
@endsection
