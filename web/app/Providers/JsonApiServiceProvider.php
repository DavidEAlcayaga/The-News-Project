<?php

namespace App\Providers;

use App\JsonApi\JsonApiBuilder;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Support\ServiceProvider;

/**
 * The service provider for the index functions.
 *
 * Class JsonApiServiceProvider
 * @package App\Providers
 */
class JsonApiServiceProvider extends ServiceProvider
{

    /**
     * Mixin method to use functions like sort, pagination and filter.
     *
     * @return void
     */
    public function boot()
    {
        Builder::mixin(new JsonApiBuilder);
    }
}
