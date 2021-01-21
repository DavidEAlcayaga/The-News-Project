# News UCN

  Retrieve news from two diferents sources, NewsApi and LaravelApi.

  **UCN  -  Android Studio  -  Laravel**


## Description
  Connects an android app to a NewsApi and a Laravel web server that works as an Api to get
  different types of news.


##  Status
  In full development.

##  Implementation
  1. Clone this repository in your local device using git clone https://github.com/DavidEAlcayaga/The-News-Project.git
  2. With a php IDE in the web folder:
      - Execute php ../composer.phar install or upgrade
      - Copy the .env.example file and rename as .env, then configure the .env with the following parameters:
          - APP_URL= [Your local ip in the device, not the loopback]
          - DB_CONNECTION=sqlite
          - DB_DATABASE= [The path to the database]
          - DB_HOST=[Your local ip in the device, not the loopback]
      - Create a new file named "database.sqlite" in the database folder
      - Execute php artisan key:generate
      - Execute php artisan serve --host [Your device ip]
  3. Configure and install the app using the IDE android studio:
      - Change the baseurl to "http://[your hosting ip]:8000/api/" to connect the app with the api in the class APILaravelClient.java located at ..\app\src\main\java\cl\ucn\disc\dsm\dcanto\news\network
      - Install the app using the Android Studio IDE
      - Run the app
##  Using the Web
  - In your browser go to "http://[your hosting ip]:8000" to register the news
  - Also you can use php artisan db:seed --class=NewsSeeder to fill the database with faker data
  - If you are not registered, the system will ask you to do it with your email and a password
  - Please use the format indicated in the form

##  Using the App
  - Launch the app
  - If you want to refresh the news you can do a pull to refresh
  - The news displayed are from two different API, NewsApi (external), and the LaravelApi developed by us
##  Technologies
  - PHP 8.0
  - PhpStorm 2020.3
  - Laravel 8
  - Bootstrap 4
  - Android Studio 4.2 Cannary 16, Android Studio 4.1.2
  - HTML, CSS
  - SQLite 3.8.8+
  - Room 2.2.5
## The Class Diagram

![](ClassDiagram.svg)

<details><summary>PlantUML</summary>
<p>

@startuml

package "cl.ucn.disc.dsm.news" {

    package "model" #c1f77f {

        Class News <<Entity>>{

            - id: Long
            - title: String
            - source: String
            - author: String
            - url: String
            - urlImage: String
            - description: String
            - content: String
            - PublishedAt: ZonedDateTime

            + News(..)
            + getId(): String
            + getTitle(): String
            + getSource(): String
            + getAuthor(): String
            + getUrl(): String
            + getUrlImage(): String
            + getDescription(): String
            + getContent(): String
            + getPublishedAt(): ZonedDateTime
        }

        Interface NewsDao {

            + insertNews(news: News): Long
            + wipeData(): void
        }

        package "newsjson" #abffe3 {

            Class JsonDataLinks {

                - first: String
                - last: String
                - prev: String
                - next: String

                + getFirst(): String
                + setFirst(first: String): Void
                + getLast(): String
                + setLast(last: String): Void
                + getPrev(): String
                + setPrev(Prev: String): Void
                + getNext(): String
                + setNext(Next: String): Void
            }

            Class JsonMetaLinks {

                - url: String
                - label: String
                - active: boolean

                + getUrl(): String
                + setUrl(first: String): Void
                + getLabel(): String
                + setLabel(label: String): Void
                + getActive(): boolean
                + setActive(active: boolean): Void
            }

            Class JsonNewsAttributes {

                - id: Long
                - title: String
                - source: String
                - author: String
                - url: String
                - urlImage: String
                - description: String
                - content: String
                - Published_at: String

                + getId(): String
                + setId(id: String): Void
                + getTitle(): String
                + setTitle(title: String): Void
                + getSource(): String
                + setSource(source: String): Void
                + getAuthor(): String
                + setAuthor(author: String): Void
                + getUrl(): String
                + setUrl(url: String): Void
                + getUrlImage(): String
                + setUrlImage(urlImage: String): Void
                + getDescription(): String
                + setDescription(description: String): Void
                + getContent(): String
                + setContent(content: String): Void
                + getPublished_at(): String
                + setPublished_at(Published_at: String): Void
            }

            Class JsonNewsData {

                - data: List<JsonNewsItem>
                - links: JsonDataLinks
                - meta: JsonNewsPagination

                + getData(): List
                + setData(data: String): Void
                + getLinks(): JsonDataLinks
                + setLinks(links: String): Void
                + getMeta(): JsonNewsPagination
                + setMeta(meta: String): Void
            }
            Class JsonNewsItem {

                - type: String
                - id: String
                - attributes: JsonNewsAttributes
                - links: JsonNewsLinks

                + getType(): String
                + setType(type: String): Void
                + getId(): String
                + setId(id: String): Void
                + getAttributes(): JsonNewsAttributes
                + setAttributes(attributes: String): Void
                + getLinks(): JsonNewsLinks
                + setLinks(links: String): Void

            }

            Class JsonNewsLinks {

                - newsLinks: String

                + getNewsLinks(): String
                + setNewsLinks(type: String): Void
            }

            Class JsonNewsPagination {

                - current_page: Integer
                - from: Integer
                - last_page: Integer
                - links: JsonMetaLinks
                - path: String
                - per_page: Integer
                - to: Integer
                - total: Integer

                + getCurrent_page(): Integer
                + setCurrent_page(id: Integer): Void
                + getFrom(): Integer
                + setFrom(title: Integer): Void
                + getLast_page(): Integer
                + setLast_page(source: Integer): Void
                + getLinks(): JsonMetaLinks
                + setLinks(author: JsonMetaLinks): Void
                + getPath(): String
                + setPath(url: String): Void
                + getPer_page(): Integer
                + setPer_page(urlImage: Integer): Void
                + getTo(): Integer
                + setTo(description: Integer): Void
                + getTotal(): Integer
                + setTotal(content: Integer): Void
            }
        }

        package "request" #9a9670 {

           Class LaravelNewsRequest {

              - sort: String
              - sortParameter: String
              - pageNumber: String
              - pageSize: String

              + getSort(): String
              + setSort(id: String): Void
              + getSortParameter(): String
              + setSortParameter(sortParameter: String): Void
              + getPageNumber(): String
              + setPageNumber(pageNumber: String): Void
              + getPageSize(): String
              + setPageSize(pageSize: String): Void
           }
        }

        package "response" #a932ca {

           Class LaravelNewsResponse {

              - jsonNewsData: List

              + getJsonNewsData(): List
              + setJsonNewsData(news: List): Void
           }
        }
    }

    package "adapters" #7dacff {

        Class NewsItem {

            - FORMATTER: DateTimeFormatter

            + NewsItem(news: news)
            + getViewHolder(view: View): ViewHolder
            + getType(): int
            + getLayoutRes(): int
            + bindView(holder: ViewHolder, payloads: List<Object>): void
        }
    }

    package "activities" #F7C56D {

        Class MainActivity {

            + onCreate(savedInstance: Bundle): void
            + onRefresh(): void
            + onCreateOptionsMenu(menu: Menu): boolean
            + onOptionsItemSelected(item: MenuItem): boolean
        }
    }

    package "services" #daabfa {

        Interface Contracts {

            + retrieveNews(size: Integer): List<News>
            + saveNews(news: News): void
        }

        Class ContractsImplFaker {

            - theNews: List<News>

            + ContractsImplFaker()
            + retrieveNews(size: Integer): List
            + saveNews(news: News): void
        }

        Class AppDatabase {

           + getInstance(context: Context): AppDatabase
           + newsDao(): NewsDao
        }

        Class ContractsImplNewsApi {

           - newsApiService: NewsApiService

           + ContractsImplNewsApi(theApiKey: String)
           + toNews(article: Article): News
           + laravelNewstoNews(jsonNewsAttributes: JsonNewsAttributes): News
           + retrieveNews(size: Integer): List
           + resultToListOfNews(articles: List, laravelNews: List): List
           + articleToListOfNews(articles: List): List
           + laravelNewsToListOfNews(laravelNews: List): List
        }

        Class NewsApiService {

           - apiKey: String
           - apiService: APIService
           - laravelApiService: ApiLaravelService

           + NewsApiService(apiKey: String)
           + getTopHeadlines(category: String, pageSize: Integer): List
           + getLaravelNews(sort: String, sortParameter: String, pageSize: Integer): List
           + parametersToQuery(sort: String, sortParameter: String, pageSize: Integer, pageNumber: Integer): ArrayList
           + resultToListOfNews(articles: List, laravelNews: List): List
           + articleToListOfNews(articles: List): List
           + laravelNewsToListOfNews(laravelNews: List): List
        }
    }

    package "utils" #c1f6fc {

        Class Validation {

            + notNull(o: Object, message: String): void
            + minSize(value: String, minSize: int, message: String): void
        }

        Class Converters {

            + toDate(dateString: String): ZonedDateTime
            + toDateString(date: ZonedDateTime): String
        }
    }

    package "network" #4dd1ff {

       Class ApiLaravelClient {

          - Retrofit mRetrofit

          + getRetrofit(): Retrofit
          + getApiService(): ApiLaravelService
       }

       Interface ApiLaravelService {

          + getLaravelNews(Map <String, String> query): JsonNewsData
       }
    }
}

package "externals" #fcc1ea {

    package "com.github.javafaker" #fcc1ea {

        Class Faker {
           ...
        }
    }

    package "org.slf4j" #fcc1ea {

        Class Logger {
           ...
        }
    }

    package "org.threeten.bp" #fcc1ea {

        Class ZonedDateTime {
           ...
        }
    }

    package "net.openhft.hashing" #fcc1ea {

       Class LongHashFunction {
           ...
       }
    }

    package "android.room." #fcc1ea {

       Class Room {
           ...
       }
    }
}

ContractsImplFaker ..|> Contracts
Contracts ..>  News : <<use>>
ContractsImplFaker ..> Faker : <<use>>
News *--> ZonedDateTime : -PublishedAt
ContractsImplFaker *--> Logger : -log
News ..> Validation
News ..> LongHashFunction : <<use>>
News *--> Room : <<use>>
AppDatabase *..> NewsDao : <<use>>
AppDatabase --> Converters : <<use>>
ApiLaravelClient ..> ApiLaravelService : <<use>>
NewsApiService ..> ApiLaravelService : <<use>>
ContractsImplNewsApi --> NewsApiService : <<use>>
@enduml

</p>
</details>

##  Contact
  Created by engineering students:
  - [David Canto](https://github.com/DavidEAlcayaga)	-	<davidcanto01@gmail.com>
  - [Pablo Castillo](https://github.com/Pablo-Castillo)	-	<pablo.castillo01@alumnos.ucn.cl>
  - [Ricardo Ortiz](https://github.com/ricardoOrtizUCN)	-	<ricardo.ortiz@alumnos.ucn.cl>


##  License
  This project is open-sourced software licensed under the [MIT License](https://www.mit.edu/~amini/LICENSE.md)
