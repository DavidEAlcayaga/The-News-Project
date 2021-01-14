<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\News;
use App\Http\Resources\NewsResource;
use App\Http\Resources\NewsCollection;
use Illuminate\Http\Request;


//TODO agregar php docs y definir controllers distintos para api y webpage o un solo controlador
class NewsController extends Controller
{
    public function index()
    {
        return NewsCollection::make(
            News::orderBy('id')->get()
        );
    }

    public function show(News $news)
    {
        return NewsResource::make($news);
    }
}
