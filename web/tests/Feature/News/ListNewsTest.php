<?php

namespace Tests\Feature\News;

use App\Models\News;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

//TODO agregar php docs
class ListNewsTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function can_fetch_single_news()
    {
        $this->withoutExceptionHandling();

        $news = News::factory()->create();

        $response = $this->getJson(route('api.v1.news.show', $news));

        $response->assertJson([
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
                    'self' => route('api.v1.news.show', $news)
                ]
            ]
        ]);
    }

    /** @test */
    public function can_fetch_all_news()
    {

        $news = News::factory()->times(3)->create();

        $response = $this->getJson(route('api.v1.news.index'));

        $response->assertJson([
            'data' => [
                [
                    'type' => 'news',
                    'id' => (int)$news[0]->id,
                    'attributes' => [
                        'id' => (int)$news[0]->id,
                        'title' => $news[0]->title,
                        'author' => $news[0]->author,
                        'source' => $news[0]->source,
                        'url' => $news[0]->url,
                        'url_image' => $news[0]->url_image,
                        'description' => $news[0]->description,
                        'content' => $news[0]->content,
                        'published_at' => $news[0]->published_at,
                    ],
                    'links' => [
                        'self' => route('api.v1.news.show', $news[0])
                    ]
                ],
                [
                    'type' => 'news',
                    'id' => (int)$news[1]->id,
                    'attributes' => [
                        'id' => (int)$news[1]->id,
                        'title' => $news[1]->title,
                        'author' => $news[1]->author,
                        'source' => $news[1]->source,
                        'url' => $news[1]->url,
                        'url_image' => $news[1]->url_image,
                        'description' => $news[1]->description,
                        'content' => $news[1]->content,
                        'published_at' => $news[1]->published_at,
                    ],
                    'links' => [
                        'self' => route('api.v1.news.show', $news[1])
                    ]
                ],
                [
                    'type' => 'news',
                    'id' => (int)$news[2]->id,
                    'attributes' => [
                        'id' => (int)$news[2]->id,
                        'title' => $news[2]->title,
                        'author' => $news[2]->author,
                        'source' => $news[2]->source,
                        'url' => $news[2]->url,
                        'url_image' => $news[2]->url_image,
                        'description' => $news[2]->description,
                        'content' => $news[2]->content,
                        'published_at' => $news[2]->published_at,
                    ],
                    'links' => [
                        'self' => route('api.v1.news.show', $news[2])
                    ]
                ],
            ]
        ]);
    }
}
