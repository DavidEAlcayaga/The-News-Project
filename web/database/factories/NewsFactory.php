<?php

namespace Database\Factories;

use App\Models\News;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * The factory of news.
 *
 * Class NewsFactory
 * @package Database\Factories
 */
class NewsFactory extends Factory
{
    /**
     * The name of the factory's corresponding model.
     *
     * @var string
     */
    protected $model = News::class;

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        static $order = 1;
        return [
            'id' => $order++,
            'title' => $this->faker->catchPhrase,
            'author' => $this->faker->name($gender = 'male'|'female'),
            'source' => $this->faker->company,
            'url' => $this->faker->url,
            'url_image' => $this->faker->imageUrl($width = 640, $height = 480),
            'description' => $this->faker->text($maxNbChars = 100),
            'content' => $this->faker->jobTitle,
            'published_at' => ($this->faker->dateTime($max = 'now', $timezone = null)->getTimestamp()),
        ];
    }
}
