/*
 * Copyright 2021 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.news.model.request;

//127.0.0.1:8000/api/v1/news?sort=published_at&page[number]=1&page[size]=10

/**
 *
 * Makes the pagination requests of the news
 */
public class LaravelNewsRequest {

  private String sort,sortParameter,pageNumber,pageSize;

  private LaravelNewsRequest(Builder builder){

    this.sort = builder.sort;
    this.sortParameter = builder.sortParameter;
    this.pageNumber = builder.pageNumber;
    this.pageSize = builder.pageSize;
  }

  public String getSort() {
    return sort;
  }

  public String getSortParameter() {
    return sortParameter;
  }

  public String getPageNumber() { return pageNumber; }

  public String getPageSize() {
    return pageSize;
  }

  public static class Builder{

    private String sort, sortParameter, pageNumber, pageSize;

    public Builder sort(String sort){

      this.sort = String.valueOf(sort);
      return this;
    }

    public Builder sortParameter(String sortParameter){

      this.sortParameter = String.valueOf(sortParameter);
      return this;
    }

    public Builder pageNumber(int pageNumber){

      this.pageNumber = String.valueOf(pageNumber);
      return this;
    }

    public Builder pageSize(int pageSize){

      this.pageSize = String.valueOf(pageSize);
      return this;
    }

    public LaravelNewsRequest build(){
      return new LaravelNewsRequest(this);
    }
  }
}
