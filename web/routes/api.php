<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

// Route to an specified news
Route::get('news/{news}', 'NewsController@show')->name('api.v1.news.show');

// Route to the news index
Route::get('news', 'NewsController@index')->name('api.v1.news.index');
