# MoviesChallenge [![CircleCI](https://circleci.com/gh/WeekendWars/MoviesChallenge/tree/master.svg?style=svg)](https://circleci.com/gh/WeekendWars/MoviesChallenge/tree/master)
This is an Android Challenge for Android Ssr. role interview.


# Requirements

They've requested for an Android TV shows application **Production ready code**. Which should display a list of the most popular TV shows on a list with
endless scroll (pagination) and a detail screen which displays the TV show's image, it's description and an horizontal list with
similar TV shows.

# API
They wanted us to use the <a href="https://developers.themoviedb.org">TheMovieDB</a> API consuming the <a href="https://developers.themoviedb.org/3/tv/get-popular-tv-shows">popular TV shows endpoint </a>
and the <a href="https://developers.themoviedb.org/3/tv/get-similar-tv-shows">similiar TV shows endpoint</a>

# Nice to have
* Nice ui
* Animations

# What did I do?

I already had an <a href="https://github.com/WeekendWars/mvp-core">MVP library</a> which allowed me to not rewrite all the
base architecture. It was a hard decision to add libraries to the project since they're normally looking for simple and concise
base code but I decided to show them that I've worked with a few well known libraries. Even when every API call and Image handling could have been easily made using `AsyncTask` I've included the next libraries:

* `io.reactivex.rxjava2:rxandroid:2.1.0` For handling Rx subscription on Android's thread
* `com.squareup.retrofit2:converter-gson:2.4.0` For parsing Json responses
* `com.github.weekendwars:core-mvp:1.0.1` For base MVP architecture which also includes <a href="https://github.com/square/retrofit/">Retrofit</a> and <a href="https://github.com/ReactiveX/RxJava">RxJava</a>
* `com.squareup.picasso:picasso:2.71828` For image loading.

## Developing

I also added **CI** including the <a href="https://github.com/Monits/static-code-analysis-plugin">Monits's static code analysis</a> library since it was
intended to be an open source project and I wanted to keep a clean code contribution by running *Checkstyle, Findbugs, PMD, etc.*

## Testing

There are actually no tests made. I didn't find anything worth to be tested. Some UI testing could've been added but it would've been a walk-through the app instead of real UI Tests since there isn't any weird UI behaviour worth of being tested

# Results

### App Information

**For debug compiled apk we have something like this:**

```
Total methods in app-debug.apk: 37592 (57,36% used)
Total fields in app-debug.apk:  26048 (39,75% used)
Total classes in app-debug.apk:  4865 (7,42% used)
Methods remaining in app-debug.apk: 27943
Fields remaining in app-debug.apk:  39487
Classes remaining in app-debug.apk:  60670
```

**(There're like 15k methods only from Android and Support Library)**
<img src="https://files.fm/thumb_show.php?i=b67tyx59&view">


Since they've requested **Production ready code** I've added the apk signin, the release variant and it's proguard configuration ending up with something like this:

```
Total methods in app-release.apk: 16986 (25,92% used)
Total fields in app-release.apk:  9958 (15,19% used)
Total classes in app-release.apk:  2229 (3,40% used)
Methods remaining in app-release.apk: 48549
Fields remaining in app-release.apk:  55577
Classes remaining in app-release.apk:  63306
```

### Screenshots
<img src="https://files.fm/thumb_show.php?i=k3bw3nzw&view">


<img src="https://files.fm/thumb_show.php?i=4d8rzas9&view">

