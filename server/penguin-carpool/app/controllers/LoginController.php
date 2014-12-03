<?php
// use Illuminate\Contracts\Auth\Authenticator;
class LoginController extends BaseController{
	

	public function showLogin(){
		if (Auth::check()){
			return Response::json(['status'=>200,Auth::user()->first_name, Auth::user()->last_name, Auth::user()->email, Auth::user()->id, Auth::user()->message, Auth::user()->rating]);
		}
		else{
			return Response::json(['status'=>400, "User not logged in"]);
		}
	//	return View::make('login');
	}

	public function doLogin(){
		//must be valid email and password required
		$rules = array(
			'email' => 'required',
			'password' => 'required'
			);

		//run validator rules on the inputs
		$validator = Validator::make(Input::all(),$rules);

		if($validator->fails()){
			//if not valid
			return Response::json(['status'=>200, "error validating inputs"]);
		} 
		else{
			//get userdata for login
			$userdata = array(
				'email'=>Input::get('email'),
				'password'=>Input::get('password')
				);
			if(Auth::attempt($userdata)){
				return Response::json(['status'=>200]);
			}
			else{
				//validation failed
				return Response::json(['status'=>400]);
			}
		}
	}

	public function doLogout(){
		if(Auth::logout()){
			return Response::json(['status'=>200, "logout successful"]);
		}
		else{
			return Response::json(['status'=>400, "logout unsuccessful"]);
		}
	}

}