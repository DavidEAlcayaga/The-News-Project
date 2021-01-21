<?php
/**
 * Copyright 2021 David Canto <davidcanto01@gmail.com>, Pablo Castillo <pablo.castillo01@alumnos.ucn.cl>, Ricardo Ortiz <ricardo.ortiz@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

namespace Tests\Feature\News;

use App\Models\News;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

/**
 * The test to paginate the json api response.
 *
 * Class PaginateNewsTest
 * @package Tests\Feature\News
 */
class PaginateNewsTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function can_fetch_paginated_news()
    {
        $news = News::factory()->times(10)->create();

        $url = route('api.v1.news.index', ['page[size]' => 2, 'page[number]' => 3]);

        $response = $this->getJson($url);

        $response->assertJsonCount(2, 'data')
            ->assertDontSee($news[0]->title)
            ->assertDontSee($news[1]->title)
            ->assertDontSee($news[2]->title)
            ->assertDontSee($news[3]->title)
            ->assertSee($news[4]->title)
            ->assertSee($news[5]->title)
            ->assertDontSee($news[6]->title)
            ->assertDontSee($news[7]->title)
            ->assertDontSee($news[8]->title)
            ->assertDontSee($news[9]->title)
        ;

        $response->assertJsonStructure([
            'links' => ['first','last','prev','next']
        ]);

        $response->assertJsonFragment([
            'first' => route('api.v1.news.index', ['page[size]' => 2, 'page[number]' => 1]),
            'last' => route('api.v1.news.index', ['page[size]' => 2, 'page[number]' => 5]),
            'prev' => route('api.v1.news.index', ['page[size]' => 2, 'page[number]' => 2]),
            'next' => route('api.v1.news.index', ['page[size]' => 2, 'page[number]' => 4]),
        ]);
    }
}
