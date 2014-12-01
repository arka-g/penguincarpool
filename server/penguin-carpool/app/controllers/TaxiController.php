<?php

class TaxiController extends \BaseController {

	// public function getTaxi(){
	// 	if(Auth::check()){
	// 		Auth::user()->id;
	// 	}
	// }

	public function getTaxi(){
		// $taxis = Taxi::where('active_state','=','1');
		$taxis = DB::table('taxis')->where('active_state', '=', 0)->get();
		return Response::json(['status' => 200, 'taxi' => $taxis]);
	}

	public function ActiveTaxis(){
		$taxis = DB::table('taxis')->where('active_state', '=', 1)->get();
		return Response::json(['status' => 200, 'taxi' => $taxis]);
	}

	public function carpoolTaxis(){
		$taxis = DB::table('taxis')->where('carpool', '=', 1)->get();
		return Response::json(['status' => 200, 'taxi' => $taxis]);
	}


}
