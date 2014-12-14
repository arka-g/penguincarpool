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
//delete it everywhere
	public function deleteUser(){
		$input= Input::all();
		$input_user_id = (int)$input['id'];

		DB::table('users')->where('id', '=', $input)->delete();
		DB::table('orders')->where('user_id','=',$input_user_id)->delete();
		DB::table('schedule')->where('user_id','=',$input_user_id)->delete();
		DB::table('taxis')->where('user_id','=',$input_user_id)->delete();

		return Response::json(['status'=>200]);
	}


	public function rateUser(){
		$input_rating = Input::all();

		$rate = (int)$input_rating['rate'];
		//this is wrong. must get user id of person u just shared taxi with
		// $id = (int) $input_rating['User_ID'];

		$taxi_id = (int)$input_rating['taxi_id'];
		$id = DB::table('taxis')->where('id', '=', $taxi_id)->pluck('user_id');

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

	public function editUser(){
		$input_edit = Input::all();
		$id = (int)$input_edit['id'];

		$userTable = new User;
		//get Id

		$find_user = $userTable::find($id);

		$find_user->first_name = $input_edit['first_name'];
		$find_user->last_name = $input_edit['last_name'];
		$find_user->email = $input_edit['email'];
		$find_user->password = $input_edit['password'];
		$find_user->save();
	}


}