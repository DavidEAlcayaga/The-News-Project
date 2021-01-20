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

public class JsonNewsData {

  private List<JsonNewsItem> data;
  private JsonDataLinks links;
  private JsonNewsPagination meta;

  public List<JsonNewsItem> getData() {
    return data;
  }

  public void setData(List<JsonNewsItem> data) {
    this.data = data;
  }

  public JsonDataLinks getLinks() {
    return links;
  }

  public void setLinks(JsonDataLinks links) {
    this.links = links;
  }

  public JsonNewsPagination getMeta() {
    return meta;
  }

  public void setMeta(JsonNewsPagination meta) {
    this.meta = meta;
  }

}
