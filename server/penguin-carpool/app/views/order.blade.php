@extends ('template')

@section('content')
<h1>Login Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'neworder')) }}

				<p>
					{{ Form::label('user_location', 'User Location') }}
					{{ Form::text('User Location') }}
				</p>

				<p>
					{{ Form::label('user_destination', 'User Destination') }}
					{{ Form::text('User destination') }}
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