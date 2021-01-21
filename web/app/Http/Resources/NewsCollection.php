<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\ResourceCollection;

/**
 * The resource collection to represent a list of news
 *
 * Class NewsCollection
 * @package App\Http\Resources
 */
class NewsCollection extends ResourceCollection
{

    /**
     * A collection of news.
     *
     * @var string
     */
    public $collects = NewsResource::class;

    /**
     * Transform the resource collection into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'data' => $this->collection
        ];
    }
}
