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

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Testing of News.
 *
 * @author David Canto-Alcayaga
 */
public class TestNews {
  /**
   * The Logger.
   */
  private static final Logger log = LoggerFactory.getLogger(TestNews.class);

  // Create a faker.
  static final Faker faker = Faker.instance();
  /**
   * The test of create a news.
   */
  @Test
  public static void testNews(){
    News news = new News(
        faker.book().title(),
        faker.name().username(),
        faker.name().fullName(),
        faker.internet().url(),
        faker.internet().avatar(),
        faker.harryPotter().quote(),
        faker.lorem().paragraph(3),
        org.threeten.bp.ZonedDateTime.now(org.threeten.bp.ZoneId.of("-3"))
    );

    News news2 = new News(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    );
  }

}
