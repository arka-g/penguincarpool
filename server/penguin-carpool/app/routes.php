<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------

*/
//default 
Route::get('/', function()
{
	return View::make('hello');
});

//for testing
Route::get('auth', function(){
	return View::make('authpage');
});
/*
| REGISTRATION ROUTES |
*/

//get registered users
Route::get('users', array('uses' => 'UserController@loadUsers'));
//register post
Route::post('save', array('uses' => 'UserController@addUsers'));

/*
| LOGIN ROUTES |
*/

//show the login form
Route::get('login', array('uses' => 'LoginController@showLogin'));

//process the form
Route::post('login', array('uses' => 'LoginController@doLogin'));

//logout
Route::get('logout', array('uses' => 'LoginController@doLogout'));

/*
| USER PROFILE |
*/
//get Profile Page
Route::get('userpage',array('uses' => 'UserController@getUserPage'));

/*
| ORDER ROUTES |
*/
Route::post('neworder', array('uses'=>'OrderController@create'));
//for testing
Route::get('order', function(){
	return View::make('order');
});
/*
| TAXI ROUTE |
*/
//get available taxis
Route::get('taxiList', array('uses'=>'TaxiController@getTaxi'));
//get active taxis
Route::get('activeTaxi', array('uses'=>'TaxiController@ActiveTaxis'));
//get carpool taxis
Route::get('carpool', array('uses'=>'TaxiController@carpoolTaxis'));
//update taxi state
Route::post('updateState', array('uses'=>'TaxiController@changeState'));
//for testing
Route::get('update', function(){
	return View::make('update');
});

/*
| MESSAGING|
*/

Route::get('sendmsg', function(){
	return View::make('message');
});

Route::post('messagePost',array('uses'=>'OrderController@sendMessage'));
//accept carpool
Route::post('msgaccept', array('uses'=>'OrderController@acceptCarpool'));
//decline carpool
Route::post('msgdecline', array('uses'=>'OrderController@declineCarpool'));
/*
| SCHEDULING |
*/
//save schedule
Route::post('saveschedule', array('uses'=>'ScheduleController@addSchedule'));
//get schedule
Route::get('getschedule', array('uses'=>'ScheduleController@getSchedule'));
//testing schedule
Route::get('testsched', function(){
	return View::make('schedule');
});

//test google maps
// Route::get('gmaps', array('uses'=>'OrderController@getLoc'));

/*
| RATING USERS |
*/
//get rating 
Route::get('getrate', array('uses'=>'UserController@getRating'));
//save rating
Route::post('rate', array('uses'=>'UserController@rateUser'));
//to test
Route::get('testrating', function(){
	return View::make('rating');
});

Route::post('delete', array('uses'=>'UserController@deleteUser'));
Route::post('edit', array('uses'=>'UserController@editUser'));