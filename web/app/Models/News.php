<?php

namespace App\Models;

use Eloquent;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Str;

//TODO agregar php docs
class News extends Model
{

    public $allowedSorts = ['published_at', 'title'];
    /**
     * Indicates if the model's ID is auto-incrementing.
     *
     * @var bool
     */

    protected $fillable = [
        'title', 'author', 'source', 'url', 'url_image', 'description', 'content','published_at',
    ];

    public $incrementing = false;

    /**
     * The database connection that should be used by the model.
     *
     * @var string
     */
    protected $connection = 'sqlite';

    use HasFactory;

    protected $guarded = ["id"];

    /**
     * @var array
     */
    protected $casts = [
        'id' => 'integer',
        'published_at' => 'string',
    ];

}
