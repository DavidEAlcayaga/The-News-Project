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

import java.time.ZonedDateTime;

/**
 * The Domain model: News.
 *
 * @author David Canto-Alcayaga.
 */
public class News {

  /**
   * Unique ID.
   */
  private Long id;

  /**
   * The Title.
   * Restrictions: not null, size > 2.
   */
  private String title;

  /**
   * The Source.
   */
  private String source;

  /**
   * The Author.
   */
  private String author;

  /**
   * The URL.
   */
  private String url;

  /**
   * The URL of image.
   */
  private String urlImage;

  /**
   * The Description.
   */
  private String description;

  /**
   * The Content.
   */
  private String content;

  /**
   * The Date of publish.
   */
  private ZonedDateTime publishedAt;

  /**
   * The Constructor.
   * @param id
   * @param title
   * @param source
   * @param author
   * @param url
   * @param urlImage
   * @param description
   * @param content
   * @param publishedAt
   */
  public News(Long id, String title, String source, String author, String url,
      String urlImage, String description, String content, ZonedDateTime publishedAt) {
    this.id = id;
    this.title = title;
    this.source = source;
    this.author = author;
    this.url = url;
    this.urlImage = urlImage;
    this.description = description;
    this.content = content;
    this.publishedAt = publishedAt;
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
