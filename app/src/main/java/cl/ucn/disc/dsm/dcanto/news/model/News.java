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
      String urlImage, String description, String content, org.threeten.bp.ZonedDateTime publishedAt) {

    //Title validation
    if(validateNullEmpty(title)){
      if (title.length() >= 2) {
        this.title = title;
      } else {
        log.debug("News: title length cannot be less than 2");
        //FIXME complete validation
        this.title = null;
      }
    }else{
      log.debug("News: title is null or empty");
      //FIXME complete validation
      this.title = null;
    }

    //Source validation
    if(validateNullEmpty(source)){
      if (source.length() >= 2) {
        this.source = source;
      } else {
        log.debug("News: source length cannot be less than 2");
        //FIXME complete validation
        this.source = null;
      }
    }else{
      log.debug("News: source is null or empty");
      //FIXME complete validation
      this.source = null;
    }

    //Author validation
    if(validateNullEmpty(author)){
      if(validateWords(author)){
        if(author.length()>=2){
          this.author = author;
        }else{
          log.debug("News: author length cannot be less than 2");
          //FIXME complete validation
          this.author = null;
        }
      }else{
        log.debug("News: author must have only letters");
        //FIXME complete validation
        this.author = null;
      }
    }else{
      log.debug("News: author is null or empty");
      //FIXME complete validation
      this.author = null;
    }

    //Url validation
    if(validateNullEmpty(url)){
      this.url = url;
    }else{
      log.debug("News: url is null or empty");
      //FIXME complete validation
      this.url = null;
    }

    //Url Image validation
    if(validateNullEmpty(urlImage)){
      this.urlImage = urlImage;
    }else{
      log.debug("News: image is null or empty");
      //FIXME complete validation
      this.urlImage = null;
    }

    //Description validation
    if(validateNullEmpty(description)){
      if(description.length()>=2){
        this.description = description;
      }else{
        log.debug("News: description length cannot be less than 2");
        //FIXME complete validation
        this.description = null;
      }
    }else{
      log.debug("News: description is null or empty");
      //FIXME complete validation
      this.description = null;
    }

    //Content validation
    if(validateNullEmpty(content)){
      this.content = content;
    }else{
      log.debug("News: content is null or empty");
      //FIXME complete validation
      this.content = null;
    }

    //Date validation
    if(validateNullEmpty(publishedAt.toString())){
      if(validateDateTime(publishedAt)){
        this.publishedAt = publishedAt;
      }else{
        log.debug("News: date doesn't comply with the format");
        //FIXME complete validation
        this.publishedAt = null;
      }
    }else{
      log.debug("News: date is null or empty");
      //FIXME complete validation
      this.publishedAt = null;
    }

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

  /**
   * Validate if the text only has words
   * @param text
   * @return state
   */
  private boolean validateWords(String text){
    try{
      if((!text.equals(""))
          && (text != null)
          && (text.matches("^[a-zA-Z]*$"))){
        return true;
      }else{
        throw new Exception("News: hasn't alphabetical letters");
      }
    }
    catch (Exception e){
      log.debug("News: contains no letters char or null value");
      return false;
    }
  }

  /**
   * Validate if number only has numbers
   * @param number
   * @return
   */
  private boolean validateNumbers(String number){
    try{
      if (number!=null){}else{throw new Exception("null value");}
      Integer numberAux = Integer.parseInt(number);
      return true;
    }
    catch (Exception e){
      System.out.println("News: contains letters or null value");
      return false;
    }
  }

  /**
   * Validate if date has format ZonedDateTime
   * @param date
   * @return
   */
  private boolean validateDateTime(ZonedDateTime date){
    try{
      if (!(this.publishedAt instanceof org.threeten.bp.ZonedDateTime)){
        throw new Exception("News: not format ZonedDateTime");
      }else{
        return true;
      }
    }
    catch (Exception e){
      System.out.println("News: not ZonedDateTime format");
      return false;
    }
  }

  private boolean validateNullEmpty(String aux){
    try{
      if(aux != null && aux != ""){
        return true;
      }else{
        throw new Exception("News: is empty or null");
      }
    }
    catch (Exception e){
      System.out.println("News: null or empty value");
      return false;
    }
  }
}
