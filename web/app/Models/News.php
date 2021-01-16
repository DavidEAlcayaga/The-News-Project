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

    /**
     * @param Builder $query
     * @param $sort
     * @mixin Eloquent
     */
    public function scopeApplySorts(Builder $query, $sort){

        $sortFields = Str::of($sort)->explode(',');

        foreach($sortFields as $sortField){
            $direction = 'asc';
            if(Str::of($sortField)->startsWith('-')){
                $direction = 'desc';
                $sortField = Str::of($sortField)->substr(1);
            }
            $query->orderBy($sortField, $direction);
        }
    }

}
