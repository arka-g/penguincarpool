<?php

use Illuminate\Auth\UserTrait;
use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableTrait;
use Illuminate\Auth\Reminders\RemindableInterface;
class User extends Eloquent implements UserInterface, RemindableInterface {

	use UserTrait, RemindableTrait;
	// protected $fillable = [];

	/**
	 * The database table used by the model.
	 *
	 * @var string
	 */
	protected $table = 'users';
	//public $timestamps = false;

	protected $hidden = array('password','created_at','updated_at','remember_token');
	protected $fillable = ['first_name','last_name','email','password'];

	public function setPasswordAttribute($value)
	{
	    $this->attributes['password'] = Hash::make($value);
	}
}

