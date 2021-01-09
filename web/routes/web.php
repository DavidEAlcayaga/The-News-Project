<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\NewsController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('registernews');
});

// Route to the news form
Route::view('registernews', 'registernews');

// Route of the news controller to store the posted news
Route::post('registernews', [NewsController::class, 'store']);

// Route of the news controller to show the stored news
Route::get('listadonoticias', [NewsController::class, 'show']);

// Route to edit the news
Route::get('edit/{id}', [NewsController::class, 'edit']);

// Route to update the news
Route::post('edit', [NewsController::class, 'update']);
