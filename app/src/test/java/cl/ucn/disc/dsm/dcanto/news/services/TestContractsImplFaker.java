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

import cl.ucn.disc.dsm.dcanto.news.model.News;
import com.github.javafaker.Faker;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testing of ContractsImplFaker.
 *
 * @author David Canto-Alcayaga.
 */
public class TestContractsImplFaker {

  /**
   * The logger.
   */
  private static final Logger log= LoggerFactory.getLogger(TestContractsImplFaker.class);

  /**
   * The test of Retrieve news.
   */
  @Test
  public void testRetrieveNews() {
    log.debug("Testing RetrieveNews...");

    // The concrete implementation.
    Contracts contracts = new ContractsImplFaker();

    // Call the method.
    List<News> news = contracts.retrieveNews(5);

    // The list can't be null.
    Assertions.assertNotNull(news,"List was null.");

    // The list can't be empty.
    Assertions.assertFalse(news.isEmpty(),"Empty list.");

    // The size(list) == 5.
    Assertions.assertEquals(5,news.size(),"List size != 5.");

    // Debug to log
    for(News n: news){
      log.debug("News: {}", n);
    }

    // Size = 0
    Assertions.assertEquals(0,contracts.retrieveNews(0).size(),"List != 0");

    // Size = 3
    Assertions.assertEquals(3,contracts.retrieveNews(3).size(),"List != 3");

    // Size = 10
    Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10,"List != 10");

    log.debug("Done.");
  }

  /**
   * The test of Save news.
   */
  @Test
  public void testSaveNews() {
    log.debug("Testing SaveNews...");

    // The concrete implementation.
    Contracts contracts = new ContractsImplFaker();

    // Create a faker.
    final Faker faker = Faker.instance();

    // Create a news.
    News news = new News(
        Integer.toUnsignedLong((contracts.retrieveNews(5).size())+1),
        faker.book().title(),
        faker.name().username(),
        faker.name().fullName(),
        faker.internet().url(),
        faker.internet().avatar(),
        faker.harryPotter().quote(),
        faker.lorem().paragraph(3),
        org.threeten.bp.ZonedDateTime.now(org.threeten.bp.ZoneId.of("-3"))
    );

    // Call the method.
    contracts.saveNews(news);

    // Size = 6 -> 5 previous news + 1 new news.
    Assertions.assertEquals(6,contracts.retrieveNews(6).size(),"New news wasn't added | size != 6");

    // Create null news.
    News nullNews = new News();

    // Call the method for test null news.
    contracts.saveNews(nullNews);

    log.debug("Done.");
  }
}
