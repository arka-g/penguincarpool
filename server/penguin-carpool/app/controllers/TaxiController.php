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

	public function changeState(){
		$input_taxi = Input::all();
		// return $input_taxi;
		$taxiTable = new Taxi;
		// $OrderTable->user_id = Auth::user()->id;
		$int = (int)$input_taxi['id'];
		// $int = 2;
		$taxi = $taxiTable::find($int);
		// $taxi->taxi_location=$input_taxi['taxi_Location'];
		// $taxi->taxi_location=$input_taxi['Taxi_loc'];

		$taxi->active_state=(int)$input_taxi['Active_State'];
		$taxi->carpool=(int)$input_taxi['Carpool'];
		$taxi->user_id=(int)$input_taxi['User_id'];
		$taxi->save();

	}


}
