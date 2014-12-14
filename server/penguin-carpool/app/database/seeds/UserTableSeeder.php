<?php

class UserTableSeeder extends Seeder
{

	public function run()
	{
		DB::table('orders')->truncate();

	}
}