<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUserTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('UserInfoTable',function($table)
		{
			
			$table->increments('id');
			$table->string('firstname');
			$table->string('lastname');
			$table->string('password');
			$table->string('username');
			$table->string('email')->unique();
			$table->string('remember_token', 100)->nullable();

		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('UserInfoTable');
	}


}
