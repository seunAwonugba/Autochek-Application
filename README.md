# Autochek-Application

An application designed for autochek.africa/ng

I made api call to the popular makes end point, to fetch popular manufacturers name and logo
popular makes -> https://api.staging.myautochek.com/v1/inventory/make?popular=true
Which i used to populate the top recycler view in the home page and its orientation is horizontal

I made api call to list of cars end point, to fetch data, containing list of cars, image of the cars etc, which i then used to populate the second recyclerview in the home page
List of cars -> https://api.staging.myautochek.com/v1/inventory/car/search 

When a user clicks on the list of cars item, user can navigate to details page with the help of nav controller

On the details page i made an api call to get car media api to display list of cars on a view pager, so users can access all the image to a particular car
Car media -> https://api.staging.myautochek.com/v1/inventory/car_media?carId={carId} 

Also on the details page, user can access every details of the particular car, by getting the details from 
car details api ->  https://api.staging.myautochek.com/v1/inventory/car/{carId}

Fetching data from api was achieved with the help retrofit, 
To ensure response is received on the background thread, i took advantage of coroutines
TO ensure data received on the background thread is immediately visible to the user, i took advantage of kotlin live data
To ensure navigation between fragmnents, i took advantage of the navigation controller

Technologies used include

1. Kotlin for Android development
2. Android studio
3. kotlin coroutines 
4. Navigation controller
5. View binding
6. retrofit service
7. Room persistent library
8. Coil image loader
9. view pager 2


Android architectural pattern followed
1. MVVM
