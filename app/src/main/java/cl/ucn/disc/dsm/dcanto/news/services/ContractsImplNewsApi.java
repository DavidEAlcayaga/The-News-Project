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

import cl.ucn.disc.dsm.dcanto.news.model.newsjson.JsonNewsAttributes;
import cl.ucn.disc.dsm.dcanto.news.model.newsjson.JsonNewsItem;
import com.kwabenaberko.newsapilib.models.Article;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import cl.ucn.disc.dsm.dcanto.news.model.News;
import cl.ucn.disc.dsm.dcanto.news.utils.Validation;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * The NewsAPI implementation via Retrofit.
 *
 * @author David Canto-Alcayaga
 */
public class ContractsImplNewsApi implements Contracts {

  /**
   * The logger.
   */
  private static final Logger log = LoggerFactory.getLogger(ContractsImplNewsApi.class);

  /**
   * The connection to NewsApi
   */
  private final NewsApiService newsApiService;

  /**
   * The Constructor
   *
   * @param theApiKey to use.
   */
  public ContractsImplNewsApi(final String theApiKey) {
    //Validation.minSize(theApiKey,10,"ApiKey !!");
    this.newsApiService = new NewsApiService(theApiKey);
  }

  /**
   * The Assembler/Transformer pattern!
   *
   * @param article used to source
   * @return the News
   */
  private static News toNews(final Article article) {

    Validation.notNull(article, "Article null !?!");

    // Warning message?
    boolean needFix = false;

    // Fix the author null :(
    if(article.getAuthor() == null || article.getAuthor().length() == 0) {
      article.setAuthor("No author.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (article.getDescription() == null || article.getDescription().length() == 0) {
      article.setDescription("No description.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (article.getPublishedAt() == null || article.getPublishedAt().toString().length() == 0) {
      article.setPublishedAt("No datetime.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (article.getUrlToImage() == null || article.getUrlToImage().toString().length() == 0) {
      article.setUrlToImage("No image.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (article.getUrl() == null || article.getUrl().toString().length() == 0) {
      article.setUrl("No url.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (article.getTitle() == null || article.getTitle().toString().length() == 0) {
      article.setTitle("No title.");
      needFix = true;
    }

    // Warning message.
    if (needFix) {

      // Debug of Article
      log.warn("Article with invalid restrictions: {}.", ToStringBuilder.reflectionToString(
          article, ToStringStyle.MULTI_LINE_STYLE
      ));
    }

    // The date
    ZonedDateTime publishedAt = ZonedDateTime
        .parse(article.getPublishedAt())
        .withZoneSameInstant(ZoneId.of("-3"));

    // The News
    return new News(
        article.getTitle(),
        article.getSource().getName(),
        article.getAuthor(),
        article.getUrl(),
        article.getUrlToImage(),
        article.getDescription(),
        article.getDescription(), //FIXME: Where is the content?
        publishedAt
    );
  }

  /**
   * The Assembler/Transformer pattern!
   *
   * @param jsonNewsAttributes used to source
   * @return the News
   */
  private static News laravelNewstoNews(final JsonNewsAttributes jsonNewsAttributes) {

    Validation.notNull(jsonNewsAttributes, "Laravel News null !?!");

    // Warning message?
    boolean needFix = false;

    // Fix the author null :(
    if(jsonNewsAttributes.getAuthor() == null || jsonNewsAttributes.getAuthor().length() == 0) {
      jsonNewsAttributes.setAuthor("No author.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (jsonNewsAttributes.getDescription() == null || jsonNewsAttributes.getDescription().length() == 0) {
      jsonNewsAttributes.setDescription("No description.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (jsonNewsAttributes.getPublished_at() == null || jsonNewsAttributes.getPublished_at().toString().length() == 0) {
      jsonNewsAttributes.setPublished_at("No datetime.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (jsonNewsAttributes.getUrl_image() == null || jsonNewsAttributes.getUrl_image().toString().length() == 0) {
      jsonNewsAttributes.setUrl_image("No image.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (jsonNewsAttributes.getUrl() == null || jsonNewsAttributes.getUrl().toString().length() == 0) {
      jsonNewsAttributes.setUrl("No url.");
      needFix = true;
    }

    // Fix more restrictions :(
    if (jsonNewsAttributes.getTitle() == null || jsonNewsAttributes.getTitle().toString().length() == 0) {
      jsonNewsAttributes.setTitle("No title.");
      needFix = true;
    }

    // Warning message.
    if (needFix) {

      // Debug of Article
      log.warn("Article with invalid restrictions: {}.", ToStringBuilder.reflectionToString(
          jsonNewsAttributes, ToStringStyle.MULTI_LINE_STYLE
      ));
    }

    String published_at = jsonNewsAttributes.getPublished_at();
    long unixTimestamp = Long.parseLong(published_at);

    Date date = new java.util.Date(unixTimestamp*1000L);
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
    String formattedDate = sdf.format(date);

    log.debug("UnixTimestamp: "+String.valueOf(unixTimestamp));
    log.debug("DateTime: "+String.valueOf(formattedDate));

    org.threeten.bp.format.DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    ZonedDateTime publishedAt = ZonedDateTime.parse(formattedDate, formatter).withZoneSameInstant(ZoneId.of("-3"));

    return new News(
        jsonNewsAttributes.getTitle(),
        jsonNewsAttributes.getSource(),
        jsonNewsAttributes.getAuthor(),
        jsonNewsAttributes.getUrl(),
        jsonNewsAttributes.getUrl_image(),
        jsonNewsAttributes.getDescription(),
        jsonNewsAttributes.getContent(),
        publishedAt
    );
  }

  /**
   * Get the list of News.
   *
   * @param size of the list.
   */
  @Override
  public List<News> retrieveNews(final Integer size) {

    try {

      //Request to NewsApi
      List<Article> articles = newsApiService.getTopHeadlines(
          "technology", size
      );

      //Request to LaravelNewsApi
      List<JsonNewsItem> laravelNews = newsApiService.getLaravelNews(
          "desc", "date", size
      );

      // return new list
      return(this.resultToListOfNews(articles, laravelNews));

    } catch (IOException ex) {

      log.error("Error", ex);
      return null;
    }
  }

  /**
   *
   * Join List
   *
   * @param articles The list api news
   * @param laravelNews the list larave lnews
   * @return
   */
  public List<News> resultToListOfNews(List<Article> articles, List<JsonNewsItem> laravelNews ) {

    // isEmpty
    if(laravelNews.isEmpty()){

      // return article list
      return(this.articlesToListOfNews(articles));
    }else{

      // create new articles list and add laravelNewsList
      List<News> resultList = new ArrayList<News>(this.articlesToListOfNews(articles));
      resultList.addAll(this.laravelNewsToListOfNews(laravelNews));

      // return new list
      return(resultList);
    }
  }

  private List<News> articlesToListOfNews(List<Article> articles){
    // The List of Articles to List of News
    List<News> news = new ArrayList<>();
    for (Article article : articles) {
      //log.debug("Article: {}", ToStringBuilder.reflectionToString(article, ToStringStyle.
      // MULTI_LINE_STYLE));
      news.add(toNews(article));
    }
    return news.stream()
        // Remote the duplicates (by id)
        .filter(distintById(News::getId))
        // Sort the stream by publishedAt
        .sorted((k1,k2)->k2.getPublishedAt().compareTo(k1.getPublishedAt()))
        // Return the stream to list
        .collect(Collectors.toList());
  }

  private List<News> laravelNewsToListOfNews(List<JsonNewsItem> laravelNews){
    // The List of Articles to List of News
    List<News> news = new ArrayList<>();

    for(JsonNewsItem jsonItem : laravelNews){
      log.debug("The News Title: "+jsonItem.getAttributes().getTitle());
      news.add(laravelNewstoNews(jsonItem.getAttributes()));
    }
      //log.debug("Article: {}", ToStringBuilder.reflectionToString(article, ToStringStyle.
      // MULTI_LINE_STYLE));

    if(news.size()==0){
      log.debug("Empty list of news");
    }
    log.debug("The size of list of news: "+String.valueOf(news.size()));
    return news.stream()
        // Remote the duplicates (by id)
        .filter(distintById(News::getId))
        // Sort the stream by publishedAt
        .sorted((k1,k2)->k2.getPublishedAt().compareTo(k1.getPublishedAt()))
        // Return the stream to list
        .collect(Collectors.toList());
  }
  //

  /**
   * Filter the stream.
   *
   * @param idExtractor
   * @param <T> news to filter
   * @return true if the news already exists.
   */
  private static <T>Predicate<T> distintById(Function<? super T, ?> idExtractor){
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(idExtractor.apply(t), Boolean.TRUE) == null;
  }


  /**
   * Save one News into the System.
   *
   * @param news to save.
   */
  @Override
  public void saveNews(final News news) {
    throw new NotImplementedException("Can't save in NewsAPI");
  }
}