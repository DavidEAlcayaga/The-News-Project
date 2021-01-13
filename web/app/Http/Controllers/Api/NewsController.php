<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\News;
use Illuminate\Http\Request;


//TODO agregar php docs y definir controllers distintos para api y webpage o un solo controlador
class NewsController extends Controller
{
    public function show(News $news)
    {
        return response()->json([
            'data' => [
                'type' => 'news',
                'id' => $news->id,
                'attributes' => [
                    'id' => (string)$news->id,
                    'title' => $news->title,
                    'author' => $news->author,
                    'source' => $news->source,
                    'url' => $news->url,
                    'url_image' => $news->url_image,
                    'description' => $news->description,
                    'content' => $news->content,
                    'published_at' => $news->published_at,
                ],
                'links' => [
                    'self' => url('/api/v1/news/'.$news->getRouteKey())
                ]
            ]
        ]);
    }
}
