<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\News;
use App\Http\Resources\NewsResource;
use App\Http\Resources\NewsCollection;

/**
 * The api controller.
 *
 * Class NewsController
 * @package App\Http\Controllers\Api
 */
class NewsController extends Controller
{

    /**
     * The index function to return a list of news in json format
     *
     * @return NewsCollection a list of news in json format
     */
    public function index()
    {

        $news = News::applyFilters()->applySorts()->jsonPaginate();

        return NewsCollection::make($news);
    }

    /**
     * The show function to return a specified news in json format.
     *
     * @param News $news the news to search
     * @return NewsResource in json format
     */
    public function show(News $news)
    {

        return NewsResource::make($news);
    }
}
