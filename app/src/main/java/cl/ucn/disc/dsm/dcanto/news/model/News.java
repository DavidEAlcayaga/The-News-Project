/*
 * Copyright 2020 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.news.model;

import cl.ucn.disc.dsm.dcanto.news.utils.Validation;
import net.openhft.hashing.LongHashFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZonedDateTime;

/**
 * The Domain model: News.
 *
 * @author David Canto-Alcayaga.
 */
public class News {
  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(News.class);

  /**
   * Unique ID.
   */
  private final Long id;

  /**
   * The Title.
   * Restrictions: not null, size > 2.
   */
  private final String title;

  /**
   * The Source.
   */
  private final String source;

  /**
   * The Author.
   */
  private final String author;

  /**
   * The URL.
   */
  private final String url;

  /**
   * The URL of image.
   */
  private final String urlImage;

  /**
   * The Description.
   */
  private final String description;

  /**
   * The Content.
   */
  private final String content;

  /**
   * The Date of publish.
   */
  private final org.threeten.bp.ZonedDateTime publishedAt;

  /**
   * The Constructor.
   * @param title
   * @param source
   * @param author
   * @param url
   * @param urlImage
   * @param description
   * @param content
   * @param publishedAt
   */
  public News(String title, String source, String author, String url,
      String urlImage, String description, String content, org.threeten.bp.ZonedDateTime
      publishedAt) {

    // Title validation
    Validation.minSize(title,2,"title");
    this.title = title;

    //Source validation
    Validation.minSize(source,2,"source");
    this.source = source;

    //Author validation
    Validation.minSize(author,2,"author");
    this.author = author;

    //Url validation
    Validation.notNull(url,"url");
    this.url = url;

    //Url Image validation
    Validation.notNull(urlImage,"urlImage");
    this.urlImage = urlImage;

    //Description validation
    Validation.minSize(description,2,"description");
    this.description = description;

    //Content validation
    Validation.notNull(content,"content");
    this.content = content;

    //Date validation
    Validation.notNull(publishedAt,"published date");
    this.publishedAt = publishedAt;

    //Hashing for id assignation
    StringBuilder strBuild = new StringBuilder();
    strBuild.append(this.title);
    strBuild.append(this.source);
    strBuild.append(this.author);
    this.id = LongHashFunction.xx().hashChars(strBuild);
  }

  /**
   * The null Constructor.
   */
  public News(){
    this.id = null;
    this.title = null;
    this.source = null;
    this.author = null;
    this.url = null;
    this.urlImage = null;
    this.description = null;
    this.content = null;
    this.publishedAt = null;
  }

  /**
   * @return the ID.
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the Title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the Source.
   */
  public String getSource() {
    return source;
  }

  /**
   * @return the Author.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @return the News URL.
   */
  public String getUrl() {
    return url;
  }

  /**
   * @return the Image URL.
   */
  public String getUrlImage() {
    return urlImage;
  }

  /**
   * @return the Description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the Content.
   */
  public String getContent() {
    return content;
  }

  /**
   * @return the Publication Date.
   */
  public ZonedDateTime getPublishedAt() {
    return publishedAt;
  }
}
