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

import android.util.Log;
import cl.ucn.disc.dsm.dcanto.news.model.News;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Testing the Contracts with NewsApi service.
 *
 * @author David Canto-Alcayaga.
 */
public class TestContractsImplNewsApi {

  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(TestContractsImplNewsApi.class);

  /**
   * The Test.
   */
  @Test
  public void testRetrieveNews() {
    log.debug("Testing..");

    // The apiKey from: https://newsapi.org/account
    // TODO insert apiKey
    String apiKey = "...";

    Contracts contracts = new ContractsImplNewsApi(apiKey);

    List<News> news = contracts.retrieveNews(10);

    Assertions.assertNotNull(news,"List null:(");
    Assertions.assertEquals(10, news.size(), "Wrong size! :(");

    log.debug(".. Done.");
  }
}
