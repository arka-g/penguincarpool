@extends ('template')

@section('content')
<h1>Login Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'login')) }}

				<!-- if there are login errors, show them here -->
				<p>
					{{ $errors->first('email') }}
					{{ $errors->first('password') }}
				</p>

				<p>
					{{ Form::label('email', 'Email Address') }}
					{{ Form::text('email', Input::old('email'), array('placeholder' => 'awesome@awesome.com')) }}
				</p>

				<p>
					{{ Form::label('password', 'Password') }}
					{{ Form::text('password') }}
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