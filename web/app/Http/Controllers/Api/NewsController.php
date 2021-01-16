<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\News;
use App\Http\Resources\NewsResource;
use App\Http\Resources\NewsCollection;
use Illuminate\Http\Request;
use Illuminate\Support\Str;


//TODO agregar php docs y definir controllers distintos para api y webpage o un solo controlador
class NewsController extends Controller
{
    public function index()
    {
        $news = News::applySorts(request('sort'))
            ->paginate(
                $perPage = request('page.size'),
                $columns = ['*'],
                $pageName = 'page[number]',
                $page = request('page.number')
            )->appends(request()->except('page.number'));

        return NewsCollection::make($news);
    }

    public function show(News $news)
    {
        return NewsResource::make($news);
    }
}
