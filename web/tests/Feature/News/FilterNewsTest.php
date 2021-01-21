<?php

namespace Tests\Feature\News;

use App\Models\News;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

/**
 * The test to filter the json api response.
 *
 * Class FilterNewsTest
 * @package Tests\Feature\News
 */
class FilterNewsTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function can_filter_news_by_source()
    {
        News::factory()->create(['source' => 'ucn']);
        News::factory()->create(['source' => 'harvard']);

        $url = route('api.v1.news.index', ['filter[source]' => 'ucn']);

        $this->getJson($url)
            ->assertJsonCount(1, 'data')
            ->assertSee('ucn')
            ->assertDontSee('harvard')
        ;
    }

    /** @test */
    public function can_filter_news_by_author()
    {
        News::factory()->create(['author' => 'Stephen_Hawking']);
        News::factory()->create(['author' => 'Bill_Gates']);

        $url = route('api.v1.news.index', ['filter[author]' => 'Stephen_Hawking']);

        $this->getJson($url)
            ->assertJsonCount(1, 'data')
            ->assertSee('Stephen_Hawking')
            ->assertDontSee('Bill_Gates')
        ;
    }

    /** @test */
    public function can_filter_news_by_year()
    {
        News::factory()->create([
            'title' => 'News del 2021',
            'published_at' => now()->year(2021)
        ]);
        News::factory()->create([
            'title' => 'News del 2020',
            'published_at' => now()->year(2020)
        ]);

        $url = route('api.v1.news.index', ['filter[year]' => 2020]);

        $this->getJson($url)
            ->assertJsonCount(1, 'data')
            ->assertSee('News del 2020')
            ->assertDontSee('News del 2021')
        ;
    }
}
