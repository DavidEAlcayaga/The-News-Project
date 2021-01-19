<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        // \App\Models\User::factory(10)->create();
        //php artisan db:seed para llenar con el faker
        //factory(News::class, 30)->create();
        \App\Models\News::factory(20)->create();
    }
}
