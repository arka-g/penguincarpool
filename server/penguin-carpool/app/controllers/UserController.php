<?php

class UserController extends BaseController{

	public function loadUsers(){
		//loading users db
		$user = User::all();
		return Response::json(['status' => 200, 'user' => $user->toArray()]);
	}

	public function addUsers(){

		$input = Input::only(['first_name','last_name','email','password']);
		$user = User::create($input);
	}

	public function getUserPage(){
		if (Auth::check()){
			return Response::json(['status'=>200, Auth::user()->first_name, Auth::user()->last_name, Auth::user()->email]);
		}
		else{
			return Response::json(['status'=>400]);
		}
	}

	public function deleteUser(){
		DB::table('users')->where('id', '=', Auth::user()->id)->delete();
	}


	public function rateUser(){
		$input_rating = Input::all();

		$rate = (int) $input_rating['rate'];

		$id = (int) $input_rating['User_ID'];

		$userTable = new User;

		$user = $userTable::find($id);
		$previous_rating = DB::table('users')->where('id','=',$id)->pluck('rating');
		// return $previous_rating;
		//get times rated
		$rate_count = DB::table('users')->where('id','=',$id)->pluck('times_rated');
		// return $rate_count;
		$new_rate = ($previous_rating*$rate_count + $rate)/($rate_count+1);
		// return $new_rate;
		$rate_count = $rate_count + 1;
		// return $rate_count;
		
		$user->rating = $new_rate;
		$user->times_rated = $rate_count;

		$user->save();
	}

	public function getRating(){
		$rating = DB::table('users')->where('id','=',Auth::user()->id)->pluck('rating');
		return Response::json(['status'=>200, 'rating'=>$rating]);
	}


}