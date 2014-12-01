<?php

class UserTableSeeder extends Seeder
{

	public function run()
	{
		DB::table('users')->truncate();

		$users = [
		            [
		                'first_name' => 'Emily',
		                'last_name' => 'Thorn',
		                'email' => 'emily.thorn@gmail.com',
		                'password' => Hash::make('emilythorn')
		            ],
		            [
		                'first_name' => 'Fred',
		                'last_name' => 'Jackson',
		                'email' => 'fred.jackson@gmail.com',
		                'password' => Hash::make('fredjackson')
		            ],
		           	[
		                'first_name' => 'Arka',
		                'last_name' => 'Ganguli',
		                'email' => 'ganguli.arka@gmail.com',
		                'password' => Hash::make('wtflaravel');
		            ]
		        ];

		        foreach($users as $user){
		            User::create($user);
		        }

		DB::table('taxis')->truncate();

		$taxis_insert = [
			[
				'taxi_location'=>'fff',
				'active_state'=>''
			],

		];
	}
}