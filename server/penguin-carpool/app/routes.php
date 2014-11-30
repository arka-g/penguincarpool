<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
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









// Route::get('/test', 'UserController@loginPage');
// Route::get('/auth', 'UserController@authpage');
// Route::post('/check_login',array('uses'=>'LoginController@loginPost'));