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
}
