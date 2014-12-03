<?php

class ScheduleController extends \BaseController{
	// $input_sched_id = Input::only('User_ID');

	public function addSchedule(){
		$input_sched = Input::all();
		// return $input_sched;
		// return $input_sched;

		$scheduleTable = new Schedule;

		$scheduleTable->user_id = $input_sched['User_ID'];
		$scheduleTable->date = $input_sched['Date'];	
		$scheduleTable->time = $input_sched['Time'];	
		$scheduleTable->pickup_location = $input_sched['Pickup_Location'];	
		$scheduleTable->dropoff_location = $input_sched['destination'];	
		
		$scheduleTable->save();
	}

	public function getSchedule(){
		// $input_user = Input::all();

		// $user = $input_user['User_ID'];
		$user = Auth::user()->id;
		// return $user;
		$user_find = DB::table('schedule')->where('user_id','=', $user)->get();
		return Response::json(['status'=>200,'user'=>$user_find]);

	}
}