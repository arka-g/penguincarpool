<?php

class UserController extends BaseController{

	public function loadUsers(){
		//loading users db
		$user = User::all();
		return Response::json(['status' => 200, 'user' => $user->toArray()]);
	}

	public function addUsers(){
		// $input = Input::all();
		// return $input;
		// $userTable = new User;
		
		// $userTable->first_name = $input['first_name'];
		// $userTable->last_name = $input['last_name'];
		// $userTable->email = $input['email'];

		// $userTable->password = $input->['password'];

		// $userTable->save();

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

}