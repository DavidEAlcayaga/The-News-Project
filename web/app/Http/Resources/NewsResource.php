<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class NewsResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'type' => 'news',
            'id' => (string) $this->resource->id,
            'attributes' => [
                'id' => (string)$this->resource->id,
                'title' => $this->resource->title,
                'author' => $this->resource->author,
                'source' => $this->resource->source,
                'url' => $this->resource->url,
                'url_image' => $this->resource->url_image,
                'description' => $this->resource->description,
                'content' => $this->resource->content,
                'published_at' => $this->resource->published_at,
            ],
            'links' => [
                'self' => route('api.v1.news.show', $this->resource)
            ]
        ];
    }
}
