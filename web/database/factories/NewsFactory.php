<?php

namespace Database\Factories;

/** @var \Illuminate\Database\Eloquent\Factory $factory */
use App\Models\News;
use Illuminate\Database\Eloquent\Factories\Factory;

//TODO agregar php docs
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
        return [
            'id' => (string)$this->faker->isbn13,
            'title' => $this->faker->catchPhrase,
            'author' => $this->faker->name($gender = 'male'|'female'),
            'source' => $this->faker->company,
            'url' => $this->faker->url,
            'url_image' => $this->faker->imageUrl($width = 640, $height = 480),
            'description' => $this->faker->text($maxNbChars = 100),
            'content' => $this->faker->jobTitle,
            'published_at' => $this->faker->time( 'H:i:s', '15:00:00' ),
        ];
    }
}
