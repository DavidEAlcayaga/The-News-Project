<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * The news model.
 *
 * Class News
 * @package App\Models
 */
class News extends Model
{

    use HasFactory;

    /**
     * Indicates the allowed params to sort the json response
     *
     * @var string[]
     */
    public $allowedSorts = ['published_at', 'title'];

    /**
     * The attributes to be filled.
     *
     * @var string[]
     */
    protected $fillable = [
        'title', 'author', 'source', 'url', 'url_image', 'description', 'content','published_at',
    ];

    /**
     * Indicates if the model's ID is auto-incrementing.
     *
     * @var bool
     */
    public $incrementing = false;

    /**
     * The database connection that should be used by the model.
     *
     * @var string
     */
    protected $connection = 'sqlite';

    /**
     * @var string[]
     */
    protected $guarded = ["id"];

    /**
     * The cast of attributes.
     *
     * @var array
     */
    protected $casts = [
        'id' => 'integer',
        'published_at' => 'string',
    ];
}
