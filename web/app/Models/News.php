<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class News extends Model
{

    /**
     * Indicates if the model's ID is auto-incrementing.
     *
     * @var bool
     */

    protected $fillable = [
        'title', 'author', 'url', 'url_image', 'description', 'content','published_at',
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

}
