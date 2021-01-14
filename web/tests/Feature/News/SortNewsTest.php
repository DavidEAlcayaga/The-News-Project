<?php

namespace Tests\Feature\News;

use App\Models\News;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class SortNewsTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function it_can_sort_news_by_recent_date()
    {
        News::factory()->create(['published_at' => '09:00:00']);
        News::factory()->create(['published_at' => '10:00:00']);
        News::factory()->create(['published_at' => '08:00:00']);

        $url = route('api.v1.news.index', ['sort' => 'published_at']);
        $this->getJson($url)->assertSeeInOrder([
            '10:00:00',
            '09:00:00',
            '08:00:00',
        ]);

    }
}
