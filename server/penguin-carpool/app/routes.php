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
Route::get('neworder', array('uses'=>'OrderController@create'));
// Route::get('/test', 'UserController@loginPage');
// Route::get('/auth', 'UserController@authpage');
// Route::post('/check_login',array('uses'=>'LoginController@loginPost'));