/*
 * Copyright 2021 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.news.model.newsjson;

import java.util.List;

/**
 * The meta array
 */
public class JsonNewsPagination {
  private Integer current_page;
  private Integer from;
  private Integer last_page;
  private List<JsonMetaLinks> links;
  private String path;
  private Integer per_page;
  private Integer to;
  private Integer total;

  public Integer getCurrent_page() {
    return current_page;
  }

  public void setCurrent_page(Integer current_page) {
    this.current_page = current_page;
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Integer getLast_page() {
    return last_page;
  }

  public void setLast_page(Integer last_page) {
    this.last_page = last_page;
  }

  public List<JsonMetaLinks> getLinks() { return links; }

  public void setLinks(List<JsonMetaLinks> links) { this.links = links; }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Integer getPer_page() {
    return per_page;
  }

  public void setPer_page(Integer per_page) {
    this.per_page = per_page;
  }

  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }
}
