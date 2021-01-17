<?php

namespace Tests\Feature\News;

use App\Models\News;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class SortNewsTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function it_can_sort_news_by_last_date()
    {
        News::factory()->create(['published_at' => '2019-12-02T20:01:00.283041Z']);
        News::factory()->create(['published_at' => '2020-12-02T20:01:00.283041Z']);
        News::factory()->create(['published_at' => '2018-12-02T20:01:00.283041Z']);

        $url = route('api.v1.news.index', ['sort' => 'published_at']);
        $this->getJson($url)->assertSeeInOrder([
            '2018-12-02T20:01:00.283041Z',
            '2019-12-02T20:01:00.283041Z',
            '2020-12-02T20:01:00.283041Z',
        ]);
    }

    /** @test */
    public function it_can_sort_news_by_recent_date()
    {
        News::factory()->create(['published_at' => '2019-12-02T20:01:00.283041Z']);
        News::factory()->create(['published_at' => '2020-12-02T20:01:00.283041Z']);
        News::factory()->create(['published_at' => '2018-12-02T20:01:00.283041Z']);

        $url = route('api.v1.news.index', ['sort' => '-published_at']);
        $this->getJson($url)->assertSeeInOrder([
            '2020-12-02T20:01:00.283041Z',
            '2019-12-02T20:01:00.283041Z',
            '2018-12-02T20:01:00.283041Z',
        ]);
    }

    /** @test */
    public function it_cannot_sort_news_by_unknown_fields(){
        News::factory()->times(3)->create();

        $url = route('api.v1.news.index').'?sort=unknown';

        $this->getJson($url)->assertStatus(400);
    }
}
