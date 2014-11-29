<?php

class OrderController extends \BaseController {
	public function create(){
		// $input = Input::only(['user_location','user_destination']);
		
		$OrderTable = new Order;
		
		$OrderTable->user_location = 43.2324;
		$OrderTable->user_destination = 24.4242;
		$OrderTable->user_id = Auth::user()->id;

		//id 1 taxi
		$taxiTable = Taxi::find(1);
		$OrderTable->taxi_id = $taxiTable->id;

		$OrderTable->save();
	}

}
