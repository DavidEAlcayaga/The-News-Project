# News UCN

  Retrieve news from two diferents sources, NewsApi and LaravelApi.

  **UCN  -  Android Studio  -  Laravel**


## Description
  Connects an android app to a NewsApi and a Laravel web server that works as an Api to get
  different types of news.


##  Status
  In full development.

##  Implementation
  1. Clone this repository in your local device using git clone https://github.com/DavidEAlcayaga/The-News-Project.git"
  2. With a php IDE in the web folder:
    - php ../composer.phar install or upgrade
    - copy the .env.example file and rename as .env, then configure the .env with your specifications and the following parameters:
      - APP_URL= [Your local ip in the device, not the loopback]
      - DB_CONNECTION=sqlite
      - DB_DATABASE= [The path to the database]
      - DB_HOST=[Your local ip in the device, not the loopback]
    - create a new file named "database.sqlite" in the database folder
    - execute php artisan key:generate
    - execute php artisan serve --host [Your device ip]
  3. Configure and install the app using the IDE android studio:
    - Change the baseurl to connect the app with the api in the class APILaravelClient.java located at ..\app\src\main\java\cl\ucn\disc\dsm\dcanto\news\network
    - Install the app using the Android Studio IDE
    - Run the app


##  Technologies
  - PHP 8.0
  - PhpStorm 2020.3
  - Laravel 8
  - Bootstrap 4
  - Android Studio 4.2 Cannary 16, Android Studio 4.1.2
  - HTML, CSS
  - SQLite 3.8.8+
  - Room 2.2.5


##  Contact
  Created by engineering students:
  - [David Canto](https://github.com/DavidEAlcayaga)	-	<davidcanto01@gmail.com>
  - [Pablo Castillo](https://github.com/Pablo-Castillo)	-	<pablo.castillo01@alumnos.ucn.cl>
  - [Ricardo Ortiz](https://github.com/ricardoOrtizUCN)	-	<ricardo.ortiz@alumnos.ucn.cl>


##  License
  This project is open-sourced software licensed under the [MIT License](https://www.mit.edu/~amini/LICENSE.md)
