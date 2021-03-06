/*
 * Copyright 2020 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.news.services;

// newsapilib imports
import cl.ucn.disc.dsm.dcanto.news.model.newsjson.JsonNewsData;
import cl.ucn.disc.dsm.dcanto.news.model.newsjson.JsonNewsItem;
import cl.ucn.disc.dsm.dcanto.news.network.APILaravelClient;
import cl.ucn.disc.dsm.dcanto.news.network.APILaravelService;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;
import com.kwabenaberko.newsapilib.network.APIService;

// laravelapi imports

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cl.ucn.disc.dsm.dcanto.news.utils.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

/**
 * Naive syncronic NewsApi implementation.
 *
 * @author David Canto-Alcayaga
 */
public final class NewsApiService {

  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(NewsApiService.class);

  /**
   * The Key.
   */
  private final String apiKey;

  /**
   * The sub-service.
   */
  private final APIService apiService;

  /**
   * The laravelApi sub-service.
   */
  private final APILaravelService laravelApiService;


  /**
   * The Constructor
   *
   * @param apiKey to use
   */
  public NewsApiService(String apiKey) {
    Validation.notNull(apiKey,"apiKey");
    this.apiKey = apiKey;
    this.apiService = APIClient.getAPIService();
    this.laravelApiService = APILaravelClient.getAPIService();
  }

  /**
   * The getTopHeadLines adaptor.
   *
   * @param category to search.
   * @param pageSize
   * @return the List of Article.
   * @throws IOException in case of error.
   */
  public List<Article> getTopHeadlines(final String category, final Integer pageSize) throws IOException {
    Validation.notNull(category,"category");
    Validation.notNull(pageSize, "pageSize");
    if(pageSize < 1) {
      throw  new IllegalArgumentException("Error: pageSize need to be > 0");
    }
    // TODO: Implements the correct map to request parameters.
    // https://newsapi.org/docs/endpoints/top-headlines


    // The map of parameters.
    Map<String, String> query = new HashMap<>();
    query.put("apiKey", this.apiKey);
    // query.put("country", topHeadlinesRequest.getCountry());
    // query.put("language", topHeadlinesRequest.getLanguage());
    query.put("category", category);
    // query.put("sources", topHeadlinesRequest.getSources());
    // query.put("q", topHeadlinesRequest.getQ());
    query.put("pageSize", pageSize.toString());
    // query.put("page", topHeadlinesRequest.getPage());

    // The response (sincronic!)
    Response<ArticleResponse> response = apiService.getTopHeadlines(query).execute();

    // All ok, return the data
    if (response.isSuccessful()) {
      return response.body().getArticles();
    }

    throw new RuntimeException("Error: " + response.code() + " --> " + response.errorBody().string());
  }


  /**
   * The getLaravelNews adaptor.
   *
   * @param sort to make ascendant o descendant list.
   * @param sortParameter to order by.
   * @param pageSize
   * @return the List of LaravelNews.
   * @throws IOException in case of error.
   */
  public List<JsonNewsItem> getLaravelNews(final String sort, final String sortParameter, final Integer pageSize) throws IOException {

    Validation.notNull(pageSize, "pageSize");
    if(pageSize < 1) {
      throw  new IllegalArgumentException("Error: pageSize need to be > 0");
    }

    ArrayList<String> parameters;
    parameters = this.parametersToQuery(sort,sortParameter,pageSize,1);
    // The map of parameters.
    Map<String, String> query = new HashMap<>();
    log.debug(String.valueOf(parameters));
    query.put("sort", parameters.get(0)+parameters.get(1));
    query.put("page[number]", parameters.get(3));
    query.put("page[size]", parameters.get(2));
    log.debug(query.toString());

    // The response (sincronic!)
    Response<JsonNewsData> response = laravelApiService.getLaravelNews(query).execute();

    // All ok, return the data
    if (response.isSuccessful()) {
      return response.body().getData();
    }

    throw new RuntimeException("Error: " + response.code() + " --> " + response.errorBody().string());
  }

  private ArrayList<String> parametersToQuery(final String sort, final String sortParameter, final Integer pageSize, Integer pageNumber){

    ArrayList<String> parameters = new ArrayList<String>();
    if(sort=="asc"){

      parameters.add("");
      log.debug("added void");
    }
    else if(sort=="desc")
    {

      parameters.add("-");
      log.debug("added -");
    }
    else
    {
      //TODO Throw new exception parametro invalido
    }

    parameters.add("published_at");
    log.debug("added published_at");

    if(pageSize == null || pageSize < 1){
      //TODO Throw new exception parametro invalido
    }
    else
    {
      parameters.add(pageSize.toString());
      log.debug("added pageSize");
    }

    if(pageNumber < 1)
    {
      //TODO Throw new exception parametro invalido
    }
    else
    {
      parameters.add(pageNumber.toString());
      log.debug("added pageNumber");
    }
    return parameters;
  }
}