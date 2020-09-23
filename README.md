# Marvel Characters
An application which shows an infinity scroll grid of Marvel characters, for each character it's possible to see the details of the character and the most expensive comic for that character.

The fetched values are cached during the requests to avoid unnecessary calls to the Marvel API.

## Before you start
Please, notice the two `TODOs` on `gradle.properties` to use your public and private keys. You can get these keys on the [Marvel's website](https://developer.marvel.com/). You must provide these two keys to be able to properly run the code.

## Technologies
- [x] [Paging](https://developer.android.com/topic/libraries/architecture/paging)
- [x] [RxJava2](https://github.com/ReactiveX/RxAndroid/tree/2.x)
- [x] [Koin](https://github.com/InsertKoinIO/koin)
- [x] [ViewModel/LiveData](https://developer.android.com/topic/libraries/architecture)
- [x] [Retrofit](https://square.github.io/retrofit/)
- [x] [Glide](https://github.com/bumptech/glide)

The architecture pattern used is MVVM

## Images
### Phone
![phone_home](https://user-images.githubusercontent.com/6085389/78518396-c01ac100-7796-11ea-9464-3f3e72046aeb.png)
![phone_details](https://user-images.githubusercontent.com/6085389/78518393-bf822a80-7796-11ea-8ade-2e57171c0962.png)
![phone_comic](https://user-images.githubusercontent.com/6085389/78518390-bee99400-7796-11ea-84b7-055074c4bad3.png)
![phone_error](https://user-images.githubusercontent.com/6085389/78518395-c01ac100-7796-11ea-96a3-e36549922637.png)

### Tablet
![tablet_home](https://user-images.githubusercontent.com/6085389/78518414-c872fc00-7796-11ea-8240-ce78ae218999.png)
![tablet_details](https://user-images.githubusercontent.com/6085389/78518411-c7da6580-7796-11ea-96b5-805457eebe86.png)
![tablet_comic](https://user-images.githubusercontent.com/6085389/78518408-c6a93880-7796-11ea-9d17-7f3deeb615f1.png)
![tablet_error](https://user-images.githubusercontent.com/6085389/78518413-c872fc00-7796-11ea-8fcc-88068d3e6830.png)
