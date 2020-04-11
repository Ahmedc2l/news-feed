# News Feed


News Feed is a very straightforward basic android application showcasing:

  - RecyclerViews and ViewHolders :zap:
  - DrawerLayout :star:
  - Navigation components :ok_hand:
  - Safe args :running:
  - Parcelable :raised_hand:
  - ViewBinding :heart_eyes:
  - Retrofit and OkHttp :boom:
  - Network Errors handling :raised_hand:
  - Combining RxJava Observables :clap:
  - Callbacks with Generics :fire:
  - Glide :thumbsup:
  - Styling and Themes :blush:
  - DateTime formating :ok_hand:

## Screenshots

Home            |  Drawer          |  Article
:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/Ahmedc2l/news-feed/blob/master/Screenshot_1586253377.png)  |  ![](https://github.com/Ahmedc2l/news-feed/blob/master/Screenshot_1586598967.png) | ![](https://github.com/Ahmedc2l/news-feed/blob/master/Screenshot_1586253402.png)


>**Note:** I found a way to use custom background without custom 'RecyclerView' or 'ListView'
```
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:gravity="start">
        <shape android:shape="rectangle">
            <size android:width="4dp"       />
            <solid android:color="@android:color/black"/>
        </shape>
    </item>
</layer-list>
```
then creating a drawable selector
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:drawable="@drawable/item_background"/>
    <item android:drawable="@android:color/transparent" />
</selector>
```
and last adding the drawbale as item background in the 'NavigationView'
```
app:itemBackground="@drawable/drawer_selected_item"
```



## Libraries

| Library | README |
| ------ | ------ |
| Glide | https://github.com/bumptech/glide/blob/master/README.md |
| RxJava | https://github.com/ReactiveX/RxAndroid |
| Retrofit | https://github.com/square/retrofit |

License
----

MIT
