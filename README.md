# ProgressCircula

[ ![Download](https://api.bintray.com/packages/2hamed/maven/ProgressCircula/images/download.svg) ](https://bintray.com/2hamed/maven/ProgressCircula/_latestVersion)[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ProgressCircula-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7523)

ProgressCircula is a lightweight customisable circular ProgressBar view for Android. It has been written entirely in Kotlin and it includes only one file. The purpose was to mimic the behavior of [Telegram](https://telegram.org/) ProgressBar for messages.

![Showcase](showcase.gif)

### Usage
ProgressCircula is available through jCenter and you can easily include it in your `build.gradle` file.

```groovy
implementation 'com.hmomeni.progresscircula:progresscircula:0.2'
```

There are a number of customisation parameters which can be used either in xml or programmatically:

* `pgc_rimColor`: This specifies the color of ProgressBar ring
* `pgc_rimWidth`: The width of the progress ring in pixels
* `pgc_textColor`: Color of the progress text
* `pgc_showProgressText`: Whether to show the progress text or not
* `pgc_indeterminate`: This makes the progress bar indeterminate
* `pgc_progress`: With this you can update the progress of the progress bar, should be >=0 and <= 100
* `pgc_speed`: this is the speed factor. The default is set to 4, the more the faster

```xml
<com.hmomeni.progresscircula.ProgressCircula
        android:id="@+id/progressBar"
        android:layout_width="90dp"
        android:layout_height="90dp"
        app:pgc_indeterminate="true"
        app:pgc_progress="40"
        app:pgc_rimColor="@color/colorAccent"
        app:pgc_rimWidth="3dp"
        app:pgc_showProgress="true"
        app:pgc_textColor="#00FF00" />
```
