<?php

namespace App\Http\Controllers;

use App\Models\News;
use DateTime;
use Illuminate\Http\Request;

/**
 * The news controller for the web.
 *
 * Class NewsController
 * @package App\Http\Controllers
 */
class NewsController extends Controller
{

    /**
     * The array to convert month in spanish to english language
     *
     * @var string[]
     */
    private $months = [
        "enero" => "January",
        "febrero" => "February",
        "marzo" => "March",
        "abril" => "April",
        "mayo" => "May",
        "junio" => "June",
        "julio" => "July",
        "agosto" => "August",
        "septiembre" => "September",
        "octubre" => "October",
        "noviembre" => "November",
        "diciembre" => "December",
    ];

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

        // SELECT * FROM News
        $news = News::all();

        // Return the get Request
        return response([
            'message' => 'Retrieved Succesfully', 'news' => $news
        ], 200);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

        // The validation of the required fields of the form
        $this->validate($request, [
            'title' => 'required',
            'author' => 'required',
            'source' => 'required',
            'url' => 'required',
            'description' => 'required',
            'content' => 'required',
            'date' => 'required'
        ]);

        // Creates a News object
        $news = new News();

        // Get's the title of the news from the form
        $news->title = $request->input('title');

        // Get's the author of the news from the form
        $news->author = $request->input('author');

        // Get's the source of the news from the form
        $news->source = $request->input('source');

        // Get's the URL of the news from the form
        $news->url = $request->input('url');

        // Get's the URL image of the news from the form
        $news->url_image = $request->input('url_image');

        // Get's the description of the news from the form
        $news->description = $request->input('description');

        // Get's the content of the news from the form
        $news->content = $request->input('content');

        // Get's the publish date of the news from the form
        $published_at_input = $this->mesToMonth($request->input('date'));
        $news->published_at = $this->parsePublishedAttoDateTime($published_at_input);

        // Save the news into the Db
        $news->save();
        return back()->with('message', 'You have added a news successfully!');
    }

    /**
     * Display the specified resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function show()
    {

        $data = News::paginate(10);
        return view('listadonoticias', ['newsList'=>$data]);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {

        $data = News::find($id);
        return view('edit', ['newsEdit'=>$data]);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {

        $data = News::find($request->id);
        $data -> title = $request -> input('title');
        $data -> author = $request -> input('author');
        $data -> source = $request -> input('source');
        $data -> url = $request -> input('url');
        $data -> url_image = $request -> input('url_image');
        $data -> description = $request -> input('description');
        $data -> content = $request -> input('content');
        $data -> published_at = $request -> input('published_at');

        $data -> save();
        return redirect('listadonoticias');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {

        $data = News::find($id);
        $data -> delete();
        return redirect('listadonoticias');
    }

    /**
     * The published_at format in the view to a dateTime format.
     *
     * @param $published_at_input
     */
    private function parsePublishedAttoDateTime($published_at_input){

        date_default_timezone_set('America/Santiago');
        setlocale(LC_TIME,"spanish");
        $inputFormat = "*, j * F * Y H:i:s";
        $dateTimeValue = DateTime::createFromFormat($inputFormat, $published_at_input);

        return (string)$dateTimeValue->format('U');
    }

    /**
     * The mes to month function using the array at the top of this class.
     *
     * @param $published_at
     * @return string|string[]
     */
    private function mesToMonth($published_at){

        foreach($this->months as $key => $data){
            $published_at = str_replace($key,$data,$published_at);
        }
        return $published_at;
    }
}
