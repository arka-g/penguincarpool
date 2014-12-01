<?php

class OrderController extends \BaseController {
	public function create(){
		$input_order = Input::all();
		//return $input_order;
		$OrderTable = new Order;
		
		$OrderTable->user_location = $input_order['User_Location'];
		$OrderTable->user_destination = $input_order['User_destination'];
		// $OrderTable->user_id = Auth::user()->id;
		$OrderTable->user_id= (int)$input_order['user_id'];
		// if (Auth::check()){
		// 	$OrderTable->user_id = Auth::user()->id;
		// }
		// else{
		// 	$orderTable->user_id = 5;
		// }
		//$OrderTable->user_id = $input_order['user_id'];

		//put logic here for taxi picking

		//id 1 taxi
		$taxiTable = Taxi::find(1);
		$OrderTable->taxi_id = $taxiTable->id;

		$OrderTable->save();

		// return Response::json(['status'=>200]);
	}

}
