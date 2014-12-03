@extends ('template')

@section('content')
<h1>Login Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'updateState')) }}

				<p>
					{{ Form::label('Active_State', 'Active State') }}
					{{ Form::text('Active State') }}
				</p>

				<p>
					{{ Form::label('Carpool', 'Carpool') }}
					{{ Form::text('Carpool') }}
				</p>
				<p>
					{{ Form::label('User_id', 'User ID') }}
					{{ Form::text('User id') }}
				</p>
				<p>
					{{ Form::label('id', ' ID') }}
					{{ Form::text(' id') }}
				</p>
				<p>
					{{ Form::label('Taxi_loc', ' taxi Loc') }}
					{{ Form::text('Taxi loc') }}
				</p>

				<!--test-->
				<!--<p>
					{{ Form::label('first_name', 'First Name') }}
					{{ Form::text('first_name') }}
				</p>

				<p>
					{{ Form::label('last_name', 'Last Name') }}
					{{ Form::text('last_name') }}
				</p> -->

				<p>{{ Form::submit('Submit!') }}</p>
			{{ Form::close() }}

		</div>
	</div>
@stop