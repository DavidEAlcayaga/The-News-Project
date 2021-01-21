<?php
/**
 * Copyright 2021 David Canto <davidcanto01@gmail.com>, Pablo Castillo <pablo.castillo01@alumnos.ucn.cl>, Ricardo Ortiz <ricardo.ortiz@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

namespace App\JsonApi;

use Closure;
use Illuminate\Support\Str;

/**
 * The json api builder with the paginate, sort and filters functions for index.
 *
 * Class JsonApiBuilder
 * @package App\JsonApi
 */
class JsonApiBuilder
{

    /**
     * Paginate the json api response.
     *
     * @return Closure
     */
    public function jsonPaginate()
    {
        return function () {
            return $this->paginate(
                $perPage = request('page.size'),
                $columns = ['*'],
                $pageName = 'page[number]',
                $page = request('page.number')
            )->appends(request()->except('page.number'));
        };
    }

    /**
     * Apply sorts to the json api response
     *
     * @return Closure
     */
    public function applySorts()
    {
        return function() {
            if(!property_exists($this->model, 'allowedSorts')) {
                abort(500, 'Por favor indica la propiedad pública $allowedSorts en la clase que está
                utilizando este trait '.get_class($this->model));
            }

            if(is_null($sort = request('sort'))) {
                return $this;
            }

            $sortFields = Str::of($sort)->explode(',');

            foreach($sortFields as $sortField){
                $direction = 'asc';

                if(Str::of($sortField)->startsWith('-')){
                    $direction = 'desc';
                    $sortField = Str::of($sortField)->substr(1);
                }

                if(!collect($this->model->allowedSorts)->contains($sortField)) {
                    abort(400, "Parametro inválido, {$sortField} no está permitido.");
                }
                $this->orderBy($sortField, $direction);
            }

            return $this;
        };
    }

    /**
     * Apply the filters to the json api response.
     *
     * @return Closure
     */
    public function applyFilters(){

        return function () {
            foreach (request('filter', []) as $filter => $value) {
                if($filter === 'year') {
                    $this->whereYear('published_at', $value);
                }
                else {
                    $this->where($filter, 'LIKE', "%{$value}%");
                }
            }
            return $this;
        };
    }
}
